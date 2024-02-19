package model;

import java.time.LocalDate;
import java.util.Map;

/**
 * This interface represents a strategy. The user can Invest a fixed amount into an existing
 * portfolio containing multiple stocks, using a specified weight for each stock in the portfolio.
 * The user is given an option to create a strategy. The
 * strategy can be either ongoing or a fixed term strategy.
 * While creating a strategy the user mentions the total amount he/she wants to invest, the
 * frequency with which the money should be invested, the startDate, endDate, the list of
 * companies in which the user wishes to invest along with the weight for that company,
 * the commission fee and portfolio name.
 */
public interface Strategy {
  /**
   * It returns the total amount of money the user wants to invest in a portfolio.
   *
   * @return amount of money to be invested
   */

  double getAmount();

  /**
   * It returns the frequency/periodic time with which investment will be done.
   *
   * @return
   */

  int getFrequency();

  /**
   * It returns the end date of a strategy. It can be null for an ongoing strategy.
   *
   * @return the end date
   */
  LocalDate getEndDate();

  /**
   * It returns the start date of a strategy.
   *
   * @return the start date
   */
  LocalDate getStartDate();

  /**
   * It returns the list of companies associated with the weight.
   *
   * @return the stock map
   */
  Map<String, Double> getStockMap();

  /**
   * It returns the name of the portfolio.
   *
   * @return name of the portfolio
   */
  String getPortfolioName();

  /**
   * It returns the commission fee associated with each transaction.
   *
   * @return the commission fee
   */
  double getCommission();

  /**
   * It returns whether the strategy is ongoing or not.
   *
   * @return true or false
   */
  boolean getIsOnGoing();

  Map<String, Double> addStockToMap(String stockSymbol, Double weight);
}
