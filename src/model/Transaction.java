package model;

import java.time.LocalDate;

/**
 * This interface represents the transactions occurred such as buying and selling of stocks.
 */
public interface Transaction {
  /**
   * The getPortfolioName method gets the name of the portfolio on which the transaction has
   * occurred.
   *
   * @return name of the portfolio
   */
  String getPortfolioName();

  /**
   * The getStockSymbol method gets the unique symbol(ticker symbol) of the stock for which the
   * transaction is done.
   *
   * @return stockSymbol(ticker symbol)
   */
  String getStockSymbol();

  /**
   * The getQuantity gets the number of stocks (quantity) involved in the transaction.
   *
   * @return number of stocks
   */
  double getQuantity();

  /**
   * The getDate method gets the date on which the transaction occurred.
   *
   * @return the date of transaction
   */
  LocalDate getDate();

  /**
   * The getType method gets the type of the transaction such as buy or sell.
   *
   * @return the type of transaction
   */
  String getType();

  /**
   * The getPrice method gets the value/price of the stock.
   *
   * @return price of the stock
   */
  double getPrice();

  /**
   * The getCommission method gets the commission fee of the transaction.
   *
   * @return commission fee
   */
  double getCommission();
}
