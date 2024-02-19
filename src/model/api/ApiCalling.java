package model.api;

import java.time.LocalDate;

/**
 * This interface represents the Api call done.
 * The Api will return the price of a particular stock on a particular date.
 */

public interface ApiCalling {

  /**
   * The getPrice method is used to calculate the value of a particular stock on a particular date.
   *
   * @param stockSymbol unique symbol(ticker symbol)
   * @param date        on which the value is calculated
   * @return the price of a stock
   */
  double getPrice(String stockSymbol, LocalDate date, boolean plusDays);

}
