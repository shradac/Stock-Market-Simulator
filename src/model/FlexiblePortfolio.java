package model;

/**
 * This interface represents the flexible portfolio where the users can buy and sell stocks.
 */
public interface FlexiblePortfolio extends Portfolio {
  /**
   * The addStock method provides the functionality to the user of adding desired number of stocks
   * to the new or existing portfolio.
   *
   * @param obj stock object to be purchased/added
   */
  void addStock(Stock obj);

  /**
   * The removeStock method provides the functionality to the user of removing desired number of
   * stocks from a portfolio.
   *
   * @param obj stock object to be sold/removed
   */
  void removeStock(Stock obj);
}
