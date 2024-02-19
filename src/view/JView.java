package view;

import java.util.List;
import java.util.Map;

import controller.Features;
import model.Portfolio;
import model.Stock;

/**
 * GUI View Interface.
 * It contains methods to draw layout outs, buttons, text fields on
 * the view(screen).
 */
public interface JView {

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for adding a flexible
   * portfolio.
   *
   * @param stockList              stockList
   * @param setPortfolioName       portfolioName to set once provided by the user
   * @param setNumberOfInvestments numberOfInvestments to set once provided by the user
   * @param setStockSymbol         stockSymbol to set once provided by the user
   * @param setStockQuantity       stockQuantity to set once provided by the user
   * @param setStockPurchaseDate   stockPurchaseDate to set once provided by the user
   * @param setCommissionFee       commission to set once provided by the user
   */
  void addFlexiblePortfolio(List<Stock> stockList, String setPortfolioName,
                            String setNumberOfInvestments, String setStockSymbol,
                            String setStockQuantity, String setStockPurchaseDate,
                            String setCommissionFee);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for saving a file.
   */
  void saveFile();

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for setting the features.
   *
   * @param features features
   */
  void setFeatures(Features features);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for creating a strategy.
   *
   * @param stockMap               stock map
   * @param portfolioList          portfolio list
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
  void createStrategy(Map<String, Double> stockMap, List<Portfolio> portfolioList,
                      String setPortfolioName, String setNumberOfInvestments, String setStockSymbol,
                      String setStockWeight, String setAmount, String setCommissionFee,
                      String setStockStartDate, String setStockEndDate, String setFrequency);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for examining a specific
   * portfolio.
   *
   * @param portfolioList    portfolio list
   * @param portfolio        portfolio
   * @param setPortfolioName portfolio name
   */
  void examinePortfolio(List<Portfolio> portfolioList, Portfolio portfolio,
                        String setPortfolioName);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for displaying the
   * total value on the screen.
   *
   * @param portfolioList    portfolio list
   * @param totalValue       total value
   * @param setPortfolioName portfolio name
   */
  void showTotalValueScreen(List<Portfolio> portfolioList, String totalValue,
                            String setPortfolioName);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for buying stocks.
   *
   * @param portfolioList portfolio list
   */
  void buyStock(List<Portfolio> portfolioList);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for selling a stock.
   *
   * @param portfolioList portfolio list
   */
  void sellStock(List<Portfolio> portfolioList);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for displaying the
   * cost basis. The cost basis contains the commission fee.
   *
   * @param portfolioList    portfolio list
   * @param cost             cost
   * @param setPortfolioName portfolio name
   */
  void getCostBasis(List<Portfolio> portfolioList, String cost, String setPortfolioName);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for displaying the
   * performance of a portfolio as a chart.
   */
  void getPerformanceOfAPortfolio(List<Portfolio> portfolioList, Draw chartDrawer,
                                  String setPortfolioName, String setStartDate, String setEndDate);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for showing the pop-up
   * messages for Errors or Success messages.
   *
   * @param message     pop up message
   * @param status      status
   * @param messageType error or message
   */
  void showPopUp(String message, String status, int messageType);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for entering the data.
   * the user provides the file location.
   */
  void enterData(String location);

  /**
   * Contains all layouts, buttons, text fields on the screen, in the GUI for getting the username.
   */
  void getUserName();
}
