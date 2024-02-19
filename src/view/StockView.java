package view;

import java.util.List;

import model.Portfolio;

/**
 * This interface represents the stock view.
 * It is a text interface.
 * This is the view of the application. Contains several methods based on which , accordingly
 * the message will be displayed to the user.
 */
public interface StockView {
  /**
   * Prints to the user interface asking for the name of the portfolio.
   */
  void addPortfolioGetName();

  /**
   * Prints to the user interface asking for the stock symbol.
   */
  void addPortfolioGetStockSymbol();

  /**
   * Prints to the user interface asking for the quantity.
   */

  void addPortfolioGetQuantity();

  /**
   * Prints to the user interface asking for the purchase date in yyyy-mm-dd format.
   */
  //void addPortfolioGetPurchaseDate();

  void getDateFor(String task);

  void endDateNotAvailable();

  /**
   * Prints to the user interface asking for the number of investments to be added to the stock.
   */
  void getNumberOfStocks();

  /**
   * Prints to the user interface asking for which portfolio is to be examined.
   */
  void examinePortfolio();

  /**
   * Prints to the user interface asking for the date in yyyy-mm-dd on which the total value is
   * to be calculated.
   */
  void totalValueGetDate();

  /**
   * Prints to the user interface asking for the menu of options the user can possibly enter,
   * such as enter the data as a file, add a portfolio, examine a portfolio and so on.
   */
  void showOptions();

  void showFlexibleOptions();

  /**
   * Prints to the user interface stating that the input is invalid.
   */
  void showInvalidInputError();

  /**
   * Prints to the user interface asking for the user to enter a yes or no.
   * If yes give the file location. If no enter the data manually.
   */
  void input();

  /**
   * Prints to the user interface asking for the file location.
   */
  void inputAsFile();

  /**
   * Prints to the user interface asking for the username.
   */
  void inputManuallyGetUserName();

  /**
   * Prints to the user interface asking for the number of portfolios.
   */
  void inputManuallyGetNumberOfPortfolios();

  /**
   * Prints to the user interface asking for the details such as stock symbol, stock name,
   * quantity, purchase date, purchase price.
   *
   * @param p Portfolio object
   */
  void printPortfolio(Portfolio p);

  /**
   * Prints to the user interface asking for the user to choose an option.
   *
   * @param portfolioList portfolioList
   */
  void chooseFromPortfolioList(List<Portfolio> portfolioList);

  void selectFromAboveMessage();

  /**
   * Prints to the user interface the total value of the portfolio.
   *
   * @param totalValue value of the portfolio
   */
  void showTotalValue(double totalValue, String apiName);

  /**
   * Prints to the user interface asking the user to enter another portfolio name as the one
   * entered by the user already exists.
   */
  void duplicatePortfolioNameShowError();

  /**
   * Prints to the user interface asking the user to enter a valid symbol that is supported
   * by the application.
   */
  void stockNoSupportShowError();

  /**
   * Prints to the user interface of successful saving of file and
   * the name of the saved file.
   */
  void saveData();

  /**
   * Prints to the user interface a bye message when quit.
   */

  void quit();

  /**
   * Prints to the user interface asking them to try again as file not found.
   */

  void fileNotFound();

  /**
   * Prints to the user interface asking the user to check if the contents of the file are
   * in the right format.
   */
  void fileValidationShowError();

  /**
   * Prints to the user interface that that weights entered by the user are invalid.
   */
  void invalidWeightShowError();

  /**
   * Prints to the user interface that the date entered by the user is invalid.
   */
  void invalidDateShowError();

  /**
   * Prints to the user interface that the portfolio was successfully created.
   */
  void portfolioCreated();

  /**
   * Prints to the user interface that the length of name cannot exceed 30 characters.
   */
  void userNameShowError();

  /**
   * Prints to the user interface to try again since the number is invalid.
   */

  void invalidNumberShowError();

  void showPortfolioOptions();

  void cantSell();

  void showCostBasis(double cost);

  void drawChart(Draw chartDrawer);

  void stockAdded();

  void addPortfolioGetCommission();

  void getStockWeight();

  void invalidCommissionShowError();

  void getAmount();

  void getFrequency();

  void stockSymbolExistsShowError();

  void showInvalidWeightError();

  void createNewPortfolioThroughStrategy(int index);
}
