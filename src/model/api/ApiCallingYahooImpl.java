package model.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

/**
 * This class represents the api call.
 * This class contains the yahoo finance api link which fetches the stock data which is used for
 * various purposes.
 * An important calculation done on the data fetched by the api is calculating the price
 * of the stock at a desired date. The time to fetch the data on a desired date is set to 12PM.
 */
public class ApiCallingYahooImpl implements ApiCalling {

  private String api(String stockSymbol, LocalDate date) {
    URL url;
    long period1 = date.atTime(12, 00, 00).toEpochSecond(ZoneOffset.ofHours(-5));
    try {
      url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" + stockSymbol + "?symbol=" + stockSymbol + "&period1=" + period1 + "&period2=" + period1 + "&interval=1d");
    } catch (MalformedURLException e) {
      throw new RuntimeException("API has either changed or no longer works");
    }
    InputStream in;
    StringBuilder output = new StringBuilder();
    try {
      in = url.openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      // do nothing
    }
    return output.toString();
  }

  @Override
  public double getPrice(String stockSymbol, LocalDate date, boolean plusDays) {
    if (date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Future date, cannot get price");
    }
    if (plusDays) {
      date = date.plusDays(ifSaturdayOrSunday(date));
    } else {
      date = date.minusDays(ifSaturdayOrSunday(date));
    }
    String output = "";
    double price = 0;
    String[] tokens;
    try {
      output = api(stockSymbol, date);
      tokens = output.split(",");
      while (price == 0) {
        price = Double.parseDouble(tokens[11]);
      }
    } catch (Exception e) {
      while (price == 0) {
        if (plusDays) {
          date = date.plusDays(1);
        } else {
          date = date.minusDays(1);
        }
        output = api(stockSymbol, date);
        tokens = output.split(",");
        price = Double.parseDouble(tokens[11]);
      }
    }
    return price;
  }

  private int ifSaturdayOrSunday(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    if (day == DayOfWeek.SUNDAY) {
      return 2;
    } else if (day == DayOfWeek.SATURDAY) {
      return 1;
    } else {
      return 0;
    }
  }
}
