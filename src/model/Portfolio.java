package model;

import java.util.List;

/**
 * This interface represents the Portfolio created by the user.
 * The user will be able to create a portfolio with a desired name and add number of stocks to it.
 */
public interface Portfolio {

  /**
   * Getter method returns the name the portfolio.
   * Name of a portfolio should be unique.
   *
   * @return the name of the portfolio.
   */
  String getName();

  /**
   * It returns the users stockList of a portfolio. It contains all the stock objects.
   * Each object represents a single stock.
   *
   * @return the stockList
   */
  List<Stock> getStockList();

}
