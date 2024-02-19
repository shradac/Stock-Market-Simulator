package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.ModelHandler;
import model.Portfolio;

import model.User;
import model.validation.Validation;
import view.Draw;
import view.JView;

/**
 * This class implements the Features interface. It is the controller for GUI. It takes input
 * It takes input from the user and sends it to the model and calls methods from the
 * view to display the output to the user.
 */
public class Controller implements Features {

  private final ModelHandler model;
  private JView view;
  private String fileSaveLocation = "";
  private String userName = "";

  /**
   * Constructor for Controller.
   * Sets the portfolioType to flexible.
   *
   * @param m ModelHandler
   */
  public Controller(ModelHandler m) {
    model = m;
    model.setPortfolioType("flexible");

  }

  public void setView(JView v) {
    view = v;
    view.setFeatures(this);
  }


  @Override
  public void enterData(String location) {

    if (!Validation.checkFileExists(location)) {
      view.showPopUp("File not found. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
    } else if (Validation.validateFile(location, "2")) {
      User user = model.initializeUserData(location, "2");
      userName = user.getUserName();
      view.showPopUp("File upload successful", "Success", JOptionPane.PLAIN_MESSAGE);
    } else {
      view.showPopUp("File Validation failed. Check if the content is correct.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }


  @Override
  public void addStock(String portfolioName, String numberOfInvestments,
                       String stockSymbol, String quantity, String purchaseDate,
                       String commissionFee) {
    if (model.checkPortfolioNameDuplicate(portfolioName)) {
      view.showPopUp("Given portfolio name already exists. Portfolio name should" +
              " be unique.Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (Validation.checkPortfolioName(portfolioName)) {
      view.showPopUp("Invalid PortfolioName", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (!Validation.checkIfNumber(numberOfInvestments)) {
      view.showPopUp("Invalid number. Try again.", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    boolean status = checkIfValidStockDetails(stockSymbol.toUpperCase(), quantity, purchaseDate,
            commissionFee);
    if (status) {
      model.addStock(stockSymbol, quantity, purchaseDate, portfolioName, commissionFee);
      view.addFlexiblePortfolio(model.getStockListToAddPortfolio(), portfolioName,
              numberOfInvestments, null, null, null, null);
    } else {
      view.addFlexiblePortfolio(model.getStockListToAddPortfolio(), portfolioName,
              numberOfInvestments, stockSymbol, quantity, purchaseDate, commissionFee);
    }
  }

  @Override
  public void createStrategy(String portfolioName, String stockAmount, String commission,
                             String stockStartDate, String stockEndDate, String frequency,
                             String numberOfInvestments) {

    List<Double> valueList = new ArrayList<>(model.getStockMap().values());
    if (!Validation.validateWeight(valueList)) {
      view.showPopUp("Invalid weights. Didn't add to 100%. Try again.", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (strategyValidation(stockAmount, commission, stockStartDate, stockEndDate, frequency,
            numberOfInvestments)) {
      return;
    }
    if (frequency.equals("")) {
      frequency = null;
    }
    model.addStrategy(portfolioName, model.getStockMap(), stockAmount, commission, stockStartDate,
            stockEndDate, frequency);
    view.showPopUp("Created Strategy.", "Success", JOptionPane.PLAIN_MESSAGE);
    model.getStockMap().clear();
    view.createStrategy(model.getStockMap(), model.getPortfolioList(), null, null,
            null, null, null, null,
            null, null, null);

  }

  private boolean strategyValidation(String stockAmount, String commission, String stockStartDate,
                                     String stockEndDate, String frequency,
                                     String numberOfInvestments) {
    if (!Validation.checkStrategyAmount(stockAmount, commission, numberOfInvestments)) {
      view.showPopUp("Invalid amount. Try again.", "Error",
              JOptionPane.ERROR_MESSAGE);
      return true;
    }
    if (!Validation.checkValidCommission(commission)) {
      view.showPopUp("Invalid commission fee. Please enter between $2 and $30.",
              "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    if (!Validation.validateStrategyDate(stockStartDate, stockEndDate)) {
      view.showPopUp("Date is invalid.",
              "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    if (!stockStartDate.equals(stockEndDate)) {
      if (!Validation.checkIfNumber(frequency)) {
        view.showPopUp("Invalid frequency. Try again.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return true;
      }
    }
    return false;
  }

  @Override
  public void addToStockMapFunction(String portfolioName, String numberOfInvestments,
                                    String stockSymbol, String stockWeight, String amount,
                                    String commissionFee, String stockStartDate,
                                    String stockEndDate, String stockFrequency) {

    if (!Validation.checkIfNumber(numberOfInvestments)) {
      view.showPopUp("Invalid number of stocks. Try again.", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (Validation.checkPortfolioName(portfolioName)) {
      view.showPopUp("Invalid PortfolioName", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (!model.checkStockSupport(stockSymbol)) {
      view.showPopUp("Invalid stock symbol", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (model.getStockMap().containsKey(stockSymbol)) {
      view.showPopUp("Stock already exists.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (Validation.checkValidWeight(stockWeight)) {
      view.showPopUp("Invalid weight. Please enter weight of stock in percentage(%).",
              "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (strategyValidation(amount, commissionFee, stockStartDate, stockEndDate, stockFrequency,
            numberOfInvestments)) {
      return;
    }

    model.getStockMap().put(stockSymbol.toUpperCase(), Double.parseDouble(stockWeight));

    view.createStrategy(model.getStockMap(), model.getPortfolioList(), portfolioName,
            numberOfInvestments, null, null, amount, commissionFee,
            stockStartDate, stockEndDate, stockFrequency);
  }

  @Override
  public void addPortfolio(String portfolioName) {
    model.addStockListToFlexiblePortfolio(portfolioName);
    view.showPopUp("Portfolio successfully created.", "Success",
            JOptionPane.PLAIN_MESSAGE);
    view.addFlexiblePortfolio(model.getStockListToAddPortfolio(), null,
            null, null, null,
            null, null);
  }

  @Override
  public void setFileSaveLocation(String fileSaveLocation) {
    this.fileSaveLocation = fileSaveLocation;
  }

  @Override
  public boolean userNameExists() {
    return !userName.equals("");
  }

  @Override
  public void examinePortfolio(String portfolioName) {
    Portfolio portfolio = null;
    for (Portfolio p : model.getPortfolioList()) {
      if (p.getName().equals(portfolioName)) {
        portfolio = p;
        break;
      }
    }
    view.examinePortfolio(model.getPortfolioList(), portfolio, portfolioName);
  }

  @Override
  public void getTotalValue(String portfolioName, String stringDate) {
    if (!Validation.validateDate(stringDate)) {
      view.showPopUp("Invalid Date", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    view.showTotalValueScreen(model.getPortfolioList(),
            String.valueOf(model.getTotalValue(portfolioName.strip(),
                    stringDate.strip())), portfolioName);

  }

  @Override
  public void getCostBasis(String portfolioName, String stringDate) {
    if (!Validation.checkFutureDate(stringDate)) {
      view.showPopUp("Invalid Date", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    view.getCostBasis(model.getPortfolioList(),
            String.valueOf(model.getCostBasis(portfolioName, stringDate)), portfolioName);
  }

  @Override
  public boolean checkSaveData() {
    if (userName.equals("")) {
      view.getUserName();
    }
    return true;
  }

  @Override
  public void saveData() {
    try {
      model.saveData(fileSaveLocation);
    } catch (Exception e) {
      // do nothing
    }

  }


  @Override
  public void setUserName(String userName) {
    model.inputManuallySetUserName(userName);
    System.out.println("yes" + userName);
  }

  @Override
  public void buyStock(String portfolioName, String stockSymbol, String quantity,
                       String stringPurchaseDate, String stringCommission) {
    stockSymbol = stockSymbol.toUpperCase();
    boolean status = checkIfValidStockDetails(stockSymbol, quantity, stringPurchaseDate,
            stringCommission);
    if (status) {
      model.addStockToPortfolio(stockSymbol, quantity, stringPurchaseDate, portfolioName,
              stringCommission);
      view.showPopUp("Stock added to Portfolio", "Success",
              JOptionPane.PLAIN_MESSAGE);
      view.buyStock(model.getPortfolioList());
    }

  }

  @Override
  public void sellStock(String portfolioName, String stockSymbol, String quantity,
                        String stringPurchaseDate, String stringCommission) {

    stockSymbol = stockSymbol.toUpperCase();
    boolean status = checkIfValidStockDetails(stockSymbol.toUpperCase(), quantity,
            stringPurchaseDate, stringCommission);
    if (model.checkSellQuantity(stockSymbol, quantity, stringPurchaseDate, portfolioName)) {
      view.showPopUp("Cannot sell. Have less quantity", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (status) {
      model.sellStockFromPortfolio(stockSymbol, quantity, stringPurchaseDate, portfolioName,
              stringCommission);
      view.showPopUp("Stock sold from Portfolio", "Success",
              JOptionPane.PLAIN_MESSAGE);
      view.sellStock(model.getPortfolioList());
    }


  }

  private boolean checkIfValidStockDetails(String stockSymbol, String quantity,
                                           String stringPurchaseDate, String stringCommission) {
    if (!Validation.validateDate(stringPurchaseDate)) {
      view.showPopUp("Invalid Date", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    } else if (!Validation.checkIfNumber(quantity)) {
      view.showPopUp("Invalid quantity", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    } else if (!model.checkStockSupport(stockSymbol)) {
      view.showPopUp("Invalid stock symbol", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    } else if (!Validation.checkValidCommission(stringCommission)) {
      view.showPopUp("Enter commission fee between 2 and 30 dollars",
              "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }


  @Override
  public void getPerformanceOfAPortfolio(String startDate, String endDate, String portfolioName) {
    if (!Validation.validateChartDate(startDate, endDate)) {
      view.showPopUp("Date is invalid.",
              "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Draw chartDrawer = model.getChart(startDate, endDate, portfolioName, 20);
    view.getPerformanceOfAPortfolio(model.getPortfolioList(), chartDrawer, portfolioName,
            startDate, endDate);

  }


  @Override
  public void submitButton(String submitButtonAction) {
    model.getStockListToAddPortfolio().clear();
    model.getStockMap().clear();
    switch (submitButtonAction) {
      case "1":
        view.enterData(null);
        break;
      case "2":
        view.addFlexiblePortfolio(model.getStockListToAddPortfolio(), null,
                null, null, null,
                null, null);
        break;

      case "3":
        view.examinePortfolio(model.getPortfolioList(), null, null);
        break;

      case "4":
        view.showTotalValueScreen(model.getPortfolioList(), null, null);
        break;

      case "5":
        saveData();
        break;

      case "6":
        view.buyStock(model.getPortfolioList());
        break;

      case "7":
        view.sellStock(model.getPortfolioList());
        break;

      case "8":
        view.getCostBasis(model.getPortfolioList(), null, null);
        break;

      case "9":
        view.getPerformanceOfAPortfolio(model.getPortfolioList(), null, null,
                null, null);
        break;

      case "10":
        view.createStrategy(model.getStockMap(), model.getPortfolioList(), null, null,
                null, null, null, null,
                null, null, null);
        break;
      default:
        break;
    }

  }


}
