package model;

/**
 * This interface represents a stock. The user will be able to add number of stocks to the
 * portfolio as desired.
 * The user can add many stocks of the same company or multiple stocks of different companies
 * or both by entering the stock symbol and the number of stocks bought by the user.
 * All the stocks should be added while creating the portfolio as the portfolio cannot be
 * modified once created.
 */

public interface Stock {

  /**
   * It returns the stock symbol of a particular stock in the portfolio.
   *
   * @return stockSymbol (ticker symbol)
   */
  String getStockSymbol();

  /**
   * It returns the quantity or the number of stocks of a particular stock.
   *
   * @return the number of stocks
   */
  double getQuantity();

  /**
   * It returns the stockName.
   *
   * @return the name of the stock
   */
  String getStockName();

  /**
   * It returns the date on which a particular stock is purchased in "YYYY-MM-DD" format.
   *
   * @return the date of purchase of the stock
   */
  String getPurchaseDate();

  /**
   * It returns the Purchase price of a particular stock.
   *
   * @return the purchase price of the stock
   */
  double getPurchasePrice();

  /**
   * It returns the commission for a particular stock.
   *
   * @return the commission fee for the stock
   */
  double getCommission();
}
