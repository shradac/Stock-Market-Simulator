package view;

import java.io.PrintStream;
import java.util.Map;

/**
 * This interface represents a graph or a chart to Draw.
 * It contains methods to draw on the view.
 */
public interface Draw {
  /**
   * Sets the output stream to an PrintStream object.
   *
   * @param out PrintStream object
   */
  void setOutputStream(PrintStream out);

  /**
   * Draws the given data on the view.
   */
  void draw();

  /**
   * Getter method to get chart of type Map.
   * The chart contains X axis and Y axis values of the bar graph.
   *
   * @return chart
   */
  Map<String, String> getChart();

  /**
   * Getter method for scale of the graph.
   *
   * @return scale
   */
  double getScale();

  /**
   * Getter method for startDate of the bar graph.
   *
   * @return startDate.
   */
  String getStartDate();

  /**
   * Getter method for endDate of the bar graph.
   *
   * @return endDate.
   */
  String getEndDate();

  /**
   * Getter method for portfolioName.
   *
   * @return portfolioName
   */
  String getPortfolioName();
}
