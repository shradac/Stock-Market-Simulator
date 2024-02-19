package view;

import java.io.PrintStream;
import java.util.Map;

/**
 * This class implements Draw interface.
 * It defines the draw methods.
 */
public class ChartDrawImpl implements Draw {
  PrintStream out;
  private Map<String, String> chart;
  private double scale;
  private String startDate;
  private String endDate;
  private String portfolioName;

  /**
   * Constructor for ChartDrawImpl class.
   *
   * @param chart         Map object containing x-axis and y-axis details to draw.
   * @param scale         scale of the chart.
   * @param startDate     start date of the chart.
   * @param endDate       end date of the chart.
   * @param portfolioName the performance of the portfolio to be drawn.
   */
  public ChartDrawImpl(Map<String, String> chart, double scale, String startDate,
                       String endDate, String portfolioName) {
    this.chart = chart;
    this.scale = scale;
    this.startDate = startDate;
    this.endDate = endDate;
    this.portfolioName = portfolioName;
  }

  @Override
  public void setOutputStream(PrintStream out) {
    this.out = out;
  }

  @Override
  public void draw() {
    out.println("Performance of portfolio " + portfolioName + " from " + startDate
            + " to " + endDate + "\n");
    for (String key : chart.keySet()) {
      String value = chart.get(key);
      out.println(key + ": " + value);
    }
    out.println();
    out.println("Absolute Scale: * = $" + scale);
    out.println();
  }

  @Override
  public Map<String, String> getChart() {
    return chart;
  }

  @Override
  public double getScale() {
    return scale;
  }

  @Override
  public String getStartDate() {
    return startDate;
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public String getEndDate() {
    return endDate;
  }

}
