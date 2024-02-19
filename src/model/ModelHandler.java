package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import view.Draw;

/**
 * This interface represents the modelHandler. This class acts as the link between the model and
 * the controller.
 * The modelHandler is the only way to communicate between the model and the
 * controller.
 * Any communication to be done from the controller to the model, the modelHandler is
 * the one and only path for communication.
 * The modelHandler has several functionalities such as
 * initialising the user data, saving the data, adding stocks and so on.
 */
public interface ModelHandler {

  /**
   * It initialises the user object with all the portfolios and other details
   * present in the file(XML file).
   * It in turn calls the reader parser class.
   *
   * @param location of the input file
   * @return the user object
   */
  User initializeUserData(String location, String portfolioMenuOption);

  /**
   * It adds the stock to the stockList of a portfolio.
   * It checks if the stock symbol already exists the stockList. If yes, it adds the present stock
   * quantity to the old one by creating a new stock object with the desired quantity and replacing
   * it with the old stock object.
   *
   * @param stockSymbol        unique symbol of a company (ticker symbol)
   * @param stringQuantity     number of stocks of a company
   * @param stringPurchaseDate date on which the stock is purchased
   * @return the stock object
   */
  Stock addStock(String stockSymbol, String stringQuantity, String stringPurchaseDate,
                 String portfolioName, String stringCommission);

  /**
   * It validates if a new portfolio name already exits.
   * It iterates through the portfolioList of the user and checks if any portfolio name matches
   * with the current portfolio entered.
   *
   * @param portfolioName name of the portfolio
   * @return true or false for the existence of the name of the portfolio
   */
  boolean checkPortfolioNameDuplicate(String portfolioName);

  /**
   * It adds the list of stocks to the portfolio.
   * Once all the stock details are given. it adds the stockList to the portfolio of the user.
   *
   * @param portfolioName name of the portfolio
   * @return the new Portfolio object
   */
  Portfolio addStockListToPortfolio(String portfolioName);

  /**
   * It returns a list of the portfolios objects created.
   *
   * @return the list of Portfolio objects of a user
   */
  List<Portfolio> getPortfolioList();

  /**
   * It returns the total value of a particular portfolio on a specific date.
   * It calls the api for stock data for each stock and adds the value.
   *
   * @param portfolioName name of the portfolio
   * @param stringDate    date on which the value of the portfolio has to be calculated
   * @return the total value of the portfolio
   */
  double getTotalValue(String portfolioName, String stringDate);

  /**
   * It validates whether the data has been saved successfully.
   * It calls the writerParser class which writes the user data including portfolio details and
   * stock details in a xml file.
   *
   * @return true or false
   * @throws ParserConfigurationException if parser fails to create a parser object
   * @throws TransformerException         an exception during the transformation process
   */
  boolean saveData(String fileSaveLocation) throws ParserConfigurationException,
          TransformerException;

  /**
   * It validates if the stock symbol is valid and exists in the list of stocks supported
   * by the application.
   *
   * @param stockSymbol unique stock symbol(ticker symbol) for each company
   * @return true or false
   */
  boolean checkStockSupport(String stockSymbol);

  /**
   * When a user chooses the option to enter the data manually, this method will be called.
   * It lets the user enter username. It will create a new user object with the username provided.
   * If any portfolios were added previously. It adds them to the user as well.
   *
   * @param userName username which identifies a user
   * @return true or false based on if it was able to create an object with this userName
   */
  boolean inputManuallySetUserName(String userName);

  /**
   * The addStockToPortfolio provides the functionality for the user to add a stock to the
   * flexible portfolio.
   * The method has been added to the ModelHandler to support the addition of
   * stocks to a FlexiblePortfolio.
   *
   * @param stockSymbol        unique stock symbol
   * @param quantity           number of stocks
   * @param stringPurchaseDate purchase date
   * @param portfolioName      portfolioName
   * @param stringCommission   commission
   * @return a FlexiblePortfolio object
   */
  FlexiblePortfolio addStockToPortfolio(String stockSymbol, String quantity,
                                        String stringPurchaseDate, String portfolioName,
                                        String stringCommission);

  /**
   * It adds list of stocks accumulated in the ModelHandler to a flexible portfolio.
   * Once all the stock details are given. It adds the stockList to the portfolio of the user.
   * It mimics addStockListToPortfolio method above but for flexible portfolio.
   *
   * @param portfolioName name of the portfolio
   */
  void addStockListToFlexiblePortfolio(String portfolioName);

  /**
   * Checks the quantity/number of stocks to be sold. If the quantity is less than the quantity in
   * the portfolio.
   * It has been added to check if the portfolio contains the quantity entered by the user.
   *
   * @param stockSymbol        unique stock symbol
   * @param stringQuantity     number of stocks to be sold
   * @param stringPurchaseDate purchase date
   * @param portfolioName      portfolioName
   * @return true or false
   */
  boolean checkSellQuantity(String stockSymbol, String stringQuantity,
                            String stringPurchaseDate, String portfolioName);

  /**
   * The sellStockFromPortfolio method removes a stock from a portfolio.
   * It has been added to remove stock from the portfolio once it is confirmed that the portfolio
   * consists the desired quantity.
   *
   * @param stockSymbol        unique stock symbol
   * @param quantity           number of stocks to be sold
   * @param stringPurchaseDate purchase date
   * @param portfolioName      portfolioName
   * @param stringCommission   commission
   */
  void sellStockFromPortfolio(String stockSymbol, String quantity,
                              String stringPurchaseDate, String portfolioName,
                              String stringCommission);


  /**
   * Sets the type of portfolio (flexible or inflexible).
   *
   * @param type of portfolio
   */
  void setPortfolioType(String type);

  /**
   * Gets the cost basis, the total amount of money invested in a portfolio) by a specific date.
   * This would include all the purchases made in that portfolio till that date.
   *
   * @param portfolioName portfolioName
   * @return the total amount
   */
  Double getCostBasis(String portfolioName, String stringDate);

  /**
   * The composition of the portfolio is sent to the view to display to the user.
   *
   * @param portfolioName portfolio name
   */
  Portfolio examine(String portfolioName);

  /**
   * The getChart method returns the performance chart for a portfolio represented by the
   * portfolioName.
   * It has been added to provide the user with the performance chart over a desired time span.
   *
   * @param start         startDate of the chart
   * @param end           endDate of the chart
   * @param portfolioName name of the portfolio
   * @return Draw object which is a chart
   */
  Draw getChart(String start, String end, String portfolioName, int length);

  /**
   * The addStrategy method adds a strategy to the strategyList of the user with the below fields.
   *
   * @param portfolioName    portfolio name
   * @param stockMap         stock map
   * @param stringAmount     amount
   * @param stringCommission commission
   * @param startDate        start date
   * @param endDate          end date
   * @param stringFrequency  frequency
   */
  void addStrategy(String portfolioName, Map<String, Double> stockMap, String stringAmount,
                   String stringCommission, String startDate, String endDate,
                   String stringFrequency);

  /**
   * The addStockToCache method adds the stock symbol entered by the user to the cache.
   * This has been done to reduce the number of API calls.
   *
   * @param stockSymbol stock symbol
   */
  void addStockToCache(String stockSymbol);

  /**
   * The stringToLocalDate method converts the string date into the local date.
   *
   * @param stringDate string date
   * @return date
   */
  LocalDate stringToLocalDate(String stringDate);

  /**
   * The getStockListToAddPortfolio method returns the stockList.
   *
   * @return stockList
   */
  List<Stock> getStockListToAddPortfolio();

  /**
   * The getStockMap method return the stock map containing the stock symbol and its corresponding
   * weight.
   *
   * @return the stock map
   */
  Map<String, Double> getStockMap();
}
