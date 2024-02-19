package model;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface represents a user of the Stock application and has
 * operations that can be performed such as adding a portfolio, getting the total
 * value of a portfolio.
 * A user will be able to add many portfolios as he/she wishes.
 * Each portfolio can have multiple stocks.
 */
public interface User {
  /**
   * It adds a portfolio abject to the portfolioList of the user.
   * Portfolio name should be unique.
   *
   * @param obj portfolio object to be added
   */
  void addPortfolio(Portfolio obj);

  /**
   * Getter method that returns the userName.
   *
   * @return userName
   */
  String getUserName();

  /**
   * It returns the total value of a portfolio on a desired date.
   *
   * @param portfolioName name of the portfolio of which the total value to be calculated
   * @param date          date on which the portfolio's value has to be calculated
   * @return the totalValue of a specific portfolio.
   */
  double totalValue(String portfolioName, LocalDate date);

  /**
   * It returns the users portfolioList. It contains all the portfolio objects.
   * Each object represents a single portfolio. The name of the portfolios are unique.
   *
   * @return the PortfolioList with objects of type portfolio
   */
  List<Portfolio> getPortfolioList();

  /**
   * It returns the users transactionList. It contains all the transaction objects.
   * Each object represents a single transaction. The transactions are of type buy or sell.
   *
   * @return the TransactionList with objects of type transaction
   */
  List<Transaction> getTransactionList();

  /**
   * It returns the type of portfolio. Flexible or Inflexible.
   *
   * @return portfolioType
   */
  String getPortfolioType();

  /**
   * It returns the users strategyList. It contains all the strategy objects.
   *
   * @return the StrategyList with objects of type strategy
   */
  List<Strategy> getStrategyList();
}
