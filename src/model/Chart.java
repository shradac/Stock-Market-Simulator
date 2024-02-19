package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a chart or a graph.
 * It takes start date, end date from the user and draws the graph.
 * If the timestamps are dates then the value is computed at the end of that day.
 * If the timestamps are months then the value is computed at the last working day of that month.
 * If the timestamp are years then the value is computed on the last working day of that year.
 */
public class Chart {
  private double scale = 0;

  /**
   * It will generate a Map with keys as date/month/year and value as stars.
   * Number of stars is determined by scale.
   * Scale is the maximum value of the list of values divide by 50.
   *
   * @param start         startDate as string
   * @param end           endDate as string
   * @param portfolioName name of the portfolio for which the chart will be drawn
   * @param user          User object to get data and call totalValue method.
   * @return chart Map object with all the data to draw
   */
  public Map<String, String> generateChart(String start, String end,
                                           String portfolioName, User user, int length) {
    Map<String, String> chart = generateChartYaxis(start, end, length);
    List<Double> valueList = generateChartXaxis(chart, portfolioName, user);
    double maximum = Collections.max(valueList);
    scale = maximum / 50;
    chart = generateStars(chart, valueList, scale);
    return chart;
  }

  private Map<String, String> generateChartYaxis(String start, String end, int length) {

    Map<String, String> chart = new LinkedHashMap<>();
    String[] tokens = start.split("-");
    LocalDate date1 = LocalDate.of(Integer.parseInt(tokens[0]),
            Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    tokens = end.split("-");
    LocalDate date2 = LocalDate.of(Integer.parseInt(tokens[0]),
            Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    Period age = Period.between(date1, date2);
    int years = age.getYears();
    int months = years * 12 + age.getMonths();
    if (months <= length) {
      if (months == 1 || months == 0) {
        showDays(chart, date1, date2, length);
      } else {
        if (months < 5) {
          showDays(chart, date1, date2, length);
        } else {
          showMonths(chart, date1, date2, months, length);
        }
      }
    } else {
      if (years < 5) {
        showMonths(chart, date1, date2, months, length);
      } else {
        while (date1.isBefore(date2)) {
          String formattedDate = date1.format(DateTimeFormatter.ofPattern("yyyy"));
          chart.put(formattedDate, "");
          date1 = date1.plusYears(1);
        }
      }
    }
    return chart;
  }

  private List<Double> generateChartXaxis(Map<String, String> chart,
                                          String portfolioName, User user) {
    List<Double> valueList = new ArrayList<>();
    for (String key : chart.keySet()) {
      LocalDate date3 = null;
      if (key.matches("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\-{1}\\d{4}")) {
        DateTimeFormatter monthYearPattern = DateTimeFormatter.ofPattern("MMM-yyyy");
        YearMonth date = YearMonth.parse(key, monthYearPattern);
        date3 = getLastWorkingDayOfMonth(date.atEndOfMonth());
      }
      if (key.matches("\\d{2}\\-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\-{1}\\d{4}")) {
        DateTimeFormatter dayMonthPattern = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate localDate = LocalDate.parse(key, dayMonthPattern);
        date3 = getLastWorkingDayOfMonth(localDate);
      }
      if (key.matches("\\d{4}")) {
        int year = Integer.parseInt(key);
        if (year == 2022) {
          date3 = LocalDate.now().minusDays(1);
        } else {
          date3 = getLastWorkingDayOfMonth(LocalDate.of(year, 12, 31));
        }
      }
      valueList.add(user.totalValue(portfolioName, date3));
    }
    return valueList;
  }

  private Map<String, String> generateStars(Map<String, String> chart,
                                            List<Double> valueList, double scale) {
    if (scale == 0) {
      scale = 1;
    }
    int i = 0;
    for (String key : chart.keySet()) {
      double q = valueList.get(i) / scale;
      String output = "";
      for (int j = 0; j < q; j++) {
        output += "*";
      }
      chart.put(key, output);
      i += 1;
    }
    return chart;
  }

  private void showDays(Map<String, String> chart, LocalDate date1, LocalDate date2, int length) {
    long d = ChronoUnit.DAYS.between(date1, date2);
    double q = skipFactor(d, length);
    while (date1.isBefore(date2)) {
      String formattedDate = date1.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
      chart.put(formattedDate, "");
      date1 = date1.plusDays((long) q);
    }
  }

  private double skipFactor(long d, int length) {
    double q = 0;
    if (d > length) {
      q = d / (float) length;
    }
    q = Math.ceil(q);
    if (q == 0) {
      q = 1;
    }
    return q;
  }


  private void showMonths(Map<String, String> chart, LocalDate date1, LocalDate date2,
                          int months, int length) {
    long d = months;
    double q = skipFactor(d, length);
    while (date1.isBefore(date2)) {
      String formattedDate = date1.format(DateTimeFormatter.ofPattern("MMM-yyyy"));
      chart.put(formattedDate, "");
      date1 = date1.plusMonths((long) q);
    }
  }

  private LocalDate getLastWorkingDayOfMonth(LocalDate lastDayOfMonth) {
    LocalDate lastWorkingDayofMonth;
    switch (DayOfWeek.of(lastDayOfMonth.get(ChronoField.DAY_OF_WEEK))) {
      case SATURDAY:
        lastWorkingDayofMonth = lastDayOfMonth.minusDays(1);
        break;
      case SUNDAY:
        lastWorkingDayofMonth = lastDayOfMonth.minusDays(2);
        break;
      default:
        lastWorkingDayofMonth = lastDayOfMonth;
    }
    return lastWorkingDayofMonth;
  }

  /**
   * Getter method for scale of the chart.
   *
   * @return scale of the chart
   */
  public double getScale() {
    return scale;
  }
}
