package model.api;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the api call.
 * This class contains the AlphaVantage api link which fetches the stock data which is used for
 * various purposes.
 * An important calculation done on the data fetched by the api is calculating the price
 * of the stock at a desired date. The time to fetch the data on a desired date is set to 12PM.
 */
public class ApiCallingAlphaVantageImpl implements ApiCalling {

  private static Map<String, StringBuilder> hashMap = new HashMap<>();

  @Override
  public double getPrice(String stockSymbol, LocalDate date, boolean plusDays) {

    while (date.equals(LocalDate.now()) && ifSaturdayOrSunday(date) != 0) {
      date = date.minusDays(ifSaturdayOrSunday(date));
    }
    if (date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Future date, cannot get price");
    }
    if (plusDays) {
      date = date.plusDays(ifSaturdayOrSunday(date));
    } else {
      date = date.minusDays(ifSaturdayOrSunday(date));
    }
    String output = null;
    int count = 0;
    while (output == null && count < 5) {
      output = api(stockSymbol.toUpperCase(), date);
      if (plusDays) {
        date = date.plusDays(1);
      } else {
        date = date.minusDays(1);
      }
      count += 1;
    }
    double price = 0;
    try {
      price = Double.parseDouble(output);
    } catch (Exception e) {
      // do nothing
    }
    return price;

  }

  private String api(String stockSymbol, LocalDate date) {

    while (!hashMap.containsKey(stockSymbol)) {
      String apiKey = "T12WYSKKHV20TAAS";
      URL url;
      try {
        url = new URL("https://www.alphavantage"
                + ".co/query?function=TIME_SERIES_DAILY"
                + "&outputsize=full"
                + "&symbol"
                + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
      } catch (MalformedURLException e) {
        throw new RuntimeException("the alphavantage API has either changed or "
                + "no longer works");
      }

      InputStream in;
      StringBuilder output = new StringBuilder();
      try {
        in = url.openStream();
        int b;
        while ((b = in.read()) != -1) {
          output.append((char) b);
        }
      } catch (Exception e) {
        // do nothing
      }
      hashMap.put(stockSymbol.toUpperCase(), output);
      return closeValue(date.toString(), output);
    }
    return closeValue(date.toString(), hashMap.get(stockSymbol.toUpperCase()));

  }

  private String closeValue(String searchDate, StringBuilder output) {
    String[] strSplit = output.toString().split("[\\r,]");
    List<String> strList = Arrays.asList(strSplit);
    strList.replaceAll(String::trim);
    if (strList.contains(searchDate)) {
      int i = strList.indexOf(searchDate);
      String value = strList.get(i + 4);
      return value;
    }
    return null;
  }

  private int ifSaturdayOrSunday(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    if (day == DayOfWeek.SUNDAY) {
      return 1;
    } else if (day == DayOfWeek.SATURDAY) {
      return 1;
    } else {
      return 0;
    }
  }
}
