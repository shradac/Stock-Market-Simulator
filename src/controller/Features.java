package controller;


/**
 * It is the Controller of the GUI.
 * It makes sure that the flow of the data is in the right sequence and enables communication
 * between the components. It takes input from the user and sends it
 * to the model and calls methods from the view to display the output to the user.
 */
public interface Features {

  /**
   * The enterData method allows the user to enter data.
   *
   * @param location location of the file
   */
  void enterData(String location);

  /**
   * Adds a stock to the stock list to show it to the GUI.
   *
   * @param portfolioName       name of the portfolio
   * @param numberOfInvestments number of investments
   * @param stockSymbol         stock symbol
   * @param quantity            number of stocks
   * @param purchaseDate        purchase date
   * @param commissionFee       commission fee
   */
  void addStock(String portfolioName, String numberOfInvestments, String stockSymbol,
                String quantity, String purchaseDate, String commissionFee);

  /**
   * It displays the composition of the portfolio to the GUI.
   *
   * @param portfolioName portfolio name
   */
  void examinePortfolio(String portfolioName);

  /**
   * It displays the total value of a particular portfolio on a specified date to the GUI.
   *
   * @param portfolioName portfolio name
   * @param stringDate    date
   */
  void getTotalValue(String portfolioName, String stringDate);

  /**
   * Checks if the username exists. If it does not exist, the user will be asked to enter the
   * username.
   *
   * @return true or false
   */
  boolean checkSaveData();

  /**
   * Saves the data to the provided location.
   */
  void saveData();

  /**
   * The setUserName method sets the username in the model handler entered by the user in the GUI.
   * It contains validation of all the fields provided.
   *
   * @param userName name of the user
   */
  void setUserName(String userName);

  /**
   * Adds a stock to the stockList of a portfolio.
   *
   * @param portfolioName name of the portfolio
   * @param stockSymbol   stock symbol
   * @param quantity      number of stocks
   * @param date          date
   * @param commissionFee commission fee
   */
  void buyStock(String portfolioName, String stockSymbol, String quantity, String date,
                String commissionFee);

  /**
   * Removes a stock from the stockList of a portfolio.
   *
   * @param portfolioName name of the portfolio
   * @param stockSymbol   stock symbol
   * @param quantity      number of stocks
   * @param date          date
   * @param commissionFee commission fee
   */
  void sellStock(String portfolioName, String stockSymbol, String quantity, String date,
                 String commissionFee);

  /**
   * It displays the cost basis of a portfolio on a specific date in the GUI.
   * The commission fee is added while calculating cost basis.
   *
   * @param portfolioName portfolio name
   * @param stringDate    date
   */
  void getCostBasis(String portfolioName, String stringDate);

  /**
   * It displays the performance of a portfolio during a time period in the GUI.
   */
  void getPerformanceOfAPortfolio(String startDate, String endDate, String portfolioName);

  /**
   * Creates the strategy. Adds the stock symbol and the weight of that stocks to the stock map.
   * A strategy cannot be created until all the weights add upto 100.
   *
   * @param portfolioName  portfolio name
   * @param stockAmount    amount
   * @param commission     commission fee
   * @param stockStartDate start date
   * @param stockEndDate   end date
   * @param frequency      frequency
   */
  void createStrategy(String portfolioName, String stockAmount, String commission,
                      String stockStartDate, String stockEndDate, String frequency,
                      String numberOfInvestments);

  /**
   * The submit button in the GUI ensures that the corresponding operation is performed by
   * running over the switch case used for the submitButtonAction when
   * clicked on, for some operation.
   *
   * @param submitButtonAction submit button
   */
  void submitButton(String submitButtonAction);

  /**
   * It adds stock symbol and weight to the stock map(linked hash map).
   * It contains validation of all the fields provided.
   *
   * @param setPortfolioName       portfolio name
   * @param setNumberOfInvestments number of investments
   * @param setStockSymbol         stock symbol
   * @param setStockWeight         stock weight
   * @param setAmount              amount to be invested
   * @param setCommissionFee       commission fee
   * @param setStockStartDate      start date
   * @param setStockEndDate        end date
   * @param setFrequency           frequency
   */
  void addToStockMapFunction(String setPortfolioName, String setNumberOfInvestments,
                             String setStockSymbol, String setStockWeight, String setAmount,
                             String setCommissionFee, String setStockStartDate,
                             String setStockEndDate, String setFrequency);

  /**
   * Adds the portfolio with the given stocks.
   *
   * @param portfolioName portfolio name
   */
  void addPortfolio(String portfolioName);

  /**
   * Sets the file save location.
   *
   * @param fileSaveLocation file location
   */
  void setFileSaveLocation(String fileSaveLocation);

  /**
   * Checks if the username exits.
   *
   * @return true or false
   */
  boolean userNameExists();
}
