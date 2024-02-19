package controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.ModelHandler;
import model.validation.Validation;
import view.Draw;
import view.StockView;

/**
 * This class implements StockController interface. It defines the options presented to the user.
 * It takes input from the user and sends it to the model and calls methods from the
 * view to display the output to the user.
 */
public class StockControllerImpl implements StockController {

  private final Scanner sc;
  private final StockView view;

  /**
   * Constructor for the StockControllerImpl class.
   *
   * @param in  InputStream that takes input from the user through user-interface
   * @param out StockView that contains methods that show the output to the user.
   */
  public StockControllerImpl(InputStream in, StockView out) {
    this.sc = new Scanner(in);
    this.view = out;
  }

  @Override
  public void connect(ModelHandler model) {
    Objects.requireNonNull(model);
    boolean quit = false;
    boolean exit;
    String option = "";
    String portfolioMenuOption = "";
    String portfolioOption = "";
    exit = false;
    while (!exit) {
      view.showPortfolioOptions();
      portfolioMenuOption = sc.nextLine().strip();
      if (Validation.checkValidPortfolioOption(portfolioMenuOption)) {
        exit = true;
      } else {
        view.showInvalidInputError();
      }
    }
    while (!quit) {
      exit = false;
      while (portfolioMenuOption.equals("1") && !exit) {
        model.setPortfolioType("inflexible");
        view.showOptions();
        option = sc.nextLine().strip();
        if (!Validation.checkValidOption(option)) {
          view.showInvalidInputError();
          break;
        }
        exit = true;
      }
      switch (option) {
        case "1":
          enterData(model, portfolioMenuOption);
          break;

        case "2":
          this.addInflexiblePortfolio(model, portfolioMenuOption);
          break;

        case "3":
          examinePortfolio(model);
          break;

        case "4":
          totalValue(model);
          break;

        case "5":
          saveData(model);
          break;

        case "6":
          quit = true;
          view.quit();
          break;

        default:
          break;
      }

      exit = false;
      while (portfolioMenuOption.equals("2") && !exit) {
        model.setPortfolioType("flexible");
        view.showFlexibleOptions();
        portfolioOption = sc.nextLine().strip();
        if (!Validation.checkValidOptionForFlexible(portfolioOption)) {
          view.showInvalidInputError();
          break;
        }
        exit = true;
      }
      switch (portfolioOption) {
        case "1":
          enterData(model, portfolioMenuOption);
          break;

        case "2":
          this.addFlexiblePortfolio(model, portfolioMenuOption);
          break;

        case "3":
          examinePortfolio(model);
          break;

        case "4":
          totalValue(model);
          break;

        case "5":
          saveData(model);
          break;

        case "6":
          buyStock(model, portfolioOption, portfolioMenuOption);
          break;

        case "7":
          sellStock(model, portfolioOption, portfolioMenuOption);
          break;

        case "8":
          getCostBasis(model);
          break;

        case "9":
          getChart(model);
          break;

        case "10":
          createStrategy(model);
          break;

        case "11":
          quit = true;
          view.quit();
          break;

        default:
          break;
      }
    }
  }


  private void createStrategy(ModelHandler model) {

    boolean exit = false;
    boolean exit2 = false;
    String stringNumberOfStocks = null;
    String choice;
    String portfolioName = null;
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.createNewPortfolioThroughStrategy(model.getPortfolioList().size() + 1);
      view.selectFromAboveMessage();
      choice = sc.nextLine().strip();
      try {
        portfolioName = model.getPortfolioList().get(Integer.parseInt(choice) - 1).getName();
      } catch (Exception e) {
        // do nothing
      }
      if (dropDownChoiceStrategy(choice, model)) {
        return;
      }
      if (Integer.parseInt(choice) == model.getPortfolioList().size() + 1) {
        view.addPortfolioGetName();
        portfolioName = sc.nextLine().strip();
        if (model.checkPortfolioNameDuplicate(portfolioName)) {
          view.duplicatePortfolioNameShowError();
          return;
        }
      }
      while (!exit2) {
        view.getNumberOfStocks();
        stringNumberOfStocks = sc.nextLine().strip();
        if (!Validation.checkIfNumber(stringNumberOfStocks)) {
          view.invalidNumberShowError();
          return;
        } else {
          exit2 = true;
        }
      }
      String stockSymbol = null;
      String stringWeight = null;
      String stringAmount = null;
      String stringCommission = null;
      String stringFrequency = null;
      String startDate = null;
      String endDate = null;
      Map<String, Double> stockMap = new HashMap<>();
      boolean support = false;
      int numberOfStocks = Integer.parseInt(stringNumberOfStocks);
      for (int i = 0; i < numberOfStocks; i++) {
        support = false;
        while (!support) {
          stockSymbol = setStockSymbol(model);
          if (stockSymbol != null) {
            support = true;
          }
          if (stockMap.containsKey(stockSymbol)) {
            view.stockSymbolExistsShowError();
            support = false;
          }
        }
        boolean exit3 = false;
        while (!exit3) {
          stringWeight = setStockWeight();
          if (stringWeight != null) {
            exit3 = true;
          }
        }
        stockMap.put(stockSymbol, Double.parseDouble(stringWeight));
      }
      List<Double> valueList = new ArrayList<>(stockMap.values());
      if (!Validation.validateWeight(valueList)) {
        view.showInvalidWeightError();
        return;
      }
      boolean exit4 = false;
      while (!exit4) {
        stringAmount = setStrategyAmount();
        if (stringAmount != null) {
          exit4 = true;
        }
      }
      boolean exit5 = false;
      while (!exit5) {
        stringCommission = setStockCommission();
        if (stringCommission != null) {
          exit5 = true;
        }
      }
      boolean exit6 = false;
      while (!exit6) {
        view.getDateFor("Start");
        startDate = sc.nextLine();
        view.getDateFor("End");
        view.endDateNotAvailable();
        endDate = sc.nextLine();
        if (!Validation.validateStrategyDate(startDate, endDate)) {
          view.invalidDateShowError();
        } else {
          exit6 = true;
        }
      }
      if (endDate.equals("N/A")
              || !model.stringToLocalDate(startDate).equals(model.stringToLocalDate(endDate))) {
        boolean exit8 = false;
        while (!exit8) {
          stringFrequency = setStrategyFrequency();
          if (stringFrequency != null) {
            exit8 = true;
          }
        }

      }

      model.addStrategy(portfolioName, stockMap, stringAmount, stringCommission, startDate,
              endDate, stringFrequency);
      exit = true;
    }
  }


  private void getChart(ModelHandler model) {
    boolean exit = false;
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (dropDownChoice(choice, model)) {
        break;
      }
      String startDate = null;
      String endDate = null;
      boolean exit2 = false;
      while (!exit2) {
        view.getDateFor("Start");
        startDate = sc.nextLine();
        view.getDateFor("End");
        endDate = sc.nextLine();
        if (!Validation.validateChartDate(startDate, endDate)) {
          view.invalidDateShowError();
        } else {
          exit2 = true;
        }
      }
      Draw chartDrawer = model.getChart(startDate, endDate, model.getPortfolioList()
              .get(Integer.parseInt(choice) - 1).getName(), 30);
      view.drawChart(chartDrawer);
      exit = true;
    }
  }


  private void getCostBasis(ModelHandler model) {
    String stringDate = null;

    boolean exit = false;
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (!dropDownChoice(choice, model)) {
        boolean exit2 = false;
        while (!exit2) {
          view.totalValueGetDate();
          stringDate = sc.nextLine().strip();
          if (!Validation.checkFutureDate(stringDate)) {
            view.invalidDateShowError();
          } else {
            exit2 = true;
          }
        }
        double cost = model.getCostBasis(model.getPortfolioList()
                .get(Integer.parseInt(choice) - 1).getName(), stringDate);
        view.showCostBasis(cost);
        exit = true;
      } else {
        view.showInvalidInputError();
      }
    }


  }

  private void buyStock(ModelHandler model, String portfolioOption, String portfolioMenuOption) {
    boolean exit = false;
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (dropDownChoice(choice, model)) {
        break;
      }
      String stockSymbol;
      String quantity;
      String stringPurchaseDate;
      String stringCommission;
      List<String> stockData = setStock(model, portfolioOption, portfolioMenuOption);
      stockSymbol = stockData.get(0);
      quantity = stockData.get(1);
      stringPurchaseDate = stockData.get(2);
      stringCommission = stockData.get(3);
      model.addStockToPortfolio(stockSymbol, quantity, stringPurchaseDate,
              model.getPortfolioList().get(Integer.parseInt(choice) - 1).getName(),
              stringCommission);
      view.stockAdded();
      exit = true;
    }
  }

  private void sellStock(ModelHandler model, String portfolioOption, String portfolioMenuOption) {
    boolean exit = false;
    String stockSymbol;
    String quantity;
    String stringPurchaseDate;
    String stringCommission;
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (dropDownChoice(choice, model)) {
        break;
      }
      List<String> stockData = setStock(model, portfolioOption, portfolioMenuOption);
      stockSymbol = stockData.get(0);
      quantity = stockData.get(1);
      stringPurchaseDate = stockData.get(2);
      stringCommission = stockData.get(3);
      if (model.checkSellQuantity(stockSymbol, quantity, stringPurchaseDate,
              model.getPortfolioList().get(Integer.parseInt(choice) - 1).getName())) {
        view.cantSell();
        break;
      }
      model.sellStockFromPortfolio(stockSymbol, quantity, stringPurchaseDate,
              model.getPortfolioList().get(Integer.parseInt(choice) - 1).getName(),
              stringCommission);
      exit = true;

    }
  }


  private boolean dropDownChoice(String choice, ModelHandler model) {

    if (!Validation.checkIfNumber(choice)) {
      view.invalidNumberShowError();
      return true;
    }
    if (!Validation.checkIndexLessThanSize(choice, model.getPortfolioList().size())) {
      view.showInvalidInputError();
      return true;
    }
    return false;
  }

  private boolean dropDownChoiceStrategy(String choice, ModelHandler model) {

    if (!Validation.checkIfNumber(choice)) {
      view.invalidNumberShowError();
      return true;
    }
    if (!Validation.checkIndexLessThanSize(choice, model.getPortfolioList().size() + 1)) {
      view.showInvalidInputError();
      return true;
    }
    return false;
  }

  private String addPortfolio(ModelHandler model, String portfolioOption,
                              String portfolioMenuOption) {
    view.addPortfolioGetName();
    String portfolioName = sc.nextLine().strip();
    if (model.checkPortfolioNameDuplicate(portfolioName)) {
      view.duplicatePortfolioNameShowError();
      return null;
    }
    view.getNumberOfStocks();
    String stringNumberOfStocks = sc.nextLine().strip();
    if (!Validation.checkIfNumber(stringNumberOfStocks)) {
      view.invalidNumberShowError();
      return null;
    }
    String stockSymbol;
    String quantity;
    String stringPurchaseDate;
    String stringCommission;
    int numberOfStocks = Integer.parseInt(stringNumberOfStocks);
    for (int i = 0; i < numberOfStocks; i++) {
      List<String> stockData = setStock(model, portfolioOption, portfolioMenuOption);
      stockSymbol = stockData.get(0);
      quantity = stockData.get(1);
      stringPurchaseDate = stockData.get(2);

      if (portfolioMenuOption.equals("1")) {
        model.addStock(stockSymbol, quantity, stringPurchaseDate, null, null);
      } else {
        stringCommission = stockData.get(3);
        model.addStock(stockSymbol, quantity, stringPurchaseDate,
                String.valueOf(i), stringCommission);
      }
    }
    return portfolioName;
  }


  private List<String> setStock(ModelHandler model, String portfolioOption,
                                String portfolioMenuOption) {
    boolean support = false;
    String stockSymbol = null;
    String quantity = null;
    String stringPurchaseDate = null;
    String stringCommission = null;
    List<String> stockData = new ArrayList<>();

    while (!support) {
      stockSymbol = setStockSymbol(model);
      if (stockSymbol != null) {
        support = true;
      }
    }
    stockData.add(stockSymbol);
    boolean exit3 = false;
    while (!exit3) {
      quantity = setStockQuantity();
      if (quantity != null) {
        exit3 = true;
      }
    }
    stockData.add(quantity);
    boolean exit2 = false;
    while (!exit2) {
      stringPurchaseDate = setStockPurchaseDate(portfolioOption);
      if (stringPurchaseDate != null) {
        exit2 = true;
      }
    }
    stockData.add(stringPurchaseDate);
    if (portfolioMenuOption.equals("2")) {
      boolean exit4 = false;
      while (!exit4) {
        stringCommission = setStockCommission();
        if (stringCommission != null) {
          exit4 = true;
        }
      }
      stockData.add(stringCommission);
    }


    return stockData;
  }

  private void addInflexiblePortfolio(ModelHandler model, String portfolioMenuOption) {
    boolean exit = false;
    while (!exit) {
      String portfolioName = addPortfolio(model, portfolioMenuOption, portfolioMenuOption);
      if (portfolioName == null) {
        break;
      }
      model.addStockListToPortfolio(portfolioName);
      view.portfolioCreated();
      exit = true;
    }
  }

  private void addFlexiblePortfolio(ModelHandler model, String portfolioMenuOption) {
    boolean exit = false;
    while (!exit) {
      String portfolioName = addPortfolio(model, portfolioMenuOption, portfolioMenuOption);
      if (portfolioName == null) {
        break;
      }
      model.addStockListToFlexiblePortfolio(portfolioName);
      view.portfolioCreated();
      exit = true;
    }
  }

  private void enterData(ModelHandler model, String portfolioMenuOption) {
    boolean exit = false;
    boolean exit2;
    boolean exit3;
    String userName = "";
    while (!exit) {
      view.input();
      String answer = sc.nextLine().strip();
      if (Validation.checkInputAsFile(answer)) {
        exit2 = false;
        while (!exit2) {
          view.inputAsFile();
          String location = sc.nextLine().strip();
          if (!Validation.checkFileExists(location)) {
            view.fileNotFound();
            break;
          }
          if (Validation.validateFile(location, portfolioMenuOption)) {
            model.initializeUserData(location, portfolioMenuOption);
            exit2 = true;
          } else {
            view.fileValidationShowError();
          }
        }
      } else if (Validation.checkInputManually(answer)) {
        exit3 = false;
        while (!exit3) {
          view.inputManuallyGetUserName();
          userName = sc.nextLine().strip();
          if (!Validation.userNameLength(userName)) {
            view.userNameShowError();
          } else {
            exit3 = true;
          }
        }
        model.inputManuallySetUserName(userName);
        view.inputManuallyGetNumberOfPortfolios();
        String stringNumberOfPortfolios = sc.nextLine().strip();
        if (!Validation.checkIfNumber(stringNumberOfPortfolios)) {
          view.invalidNumberShowError();
          break;
        }
        int numberOfPortfolios = Integer.parseInt(stringNumberOfPortfolios);
        for (int i = 0; i < numberOfPortfolios; i++) {
          if (portfolioMenuOption.equals("1")) {
            this.addInflexiblePortfolio(model, portfolioMenuOption);
          } else if (portfolioMenuOption.equals("2")) {
            this.addFlexiblePortfolio(model, portfolioMenuOption);
          }
        }
      } else {
        view.showInvalidInputError();
        break;
      }
      exit = true;
    }
  }

  private void examinePortfolio(ModelHandler model) {
    boolean exit = false;
    while (!exit) {
      view.examinePortfolio();
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (dropDownChoice(choice, model)) {
        break;
      }
      view.printPortfolio(model.examine(model.getPortfolioList()
              .get(Integer.parseInt(choice) - 1).getName()));
      exit = true;
    }
  }

  private void totalValue(ModelHandler model) {
    boolean exit = false;
    boolean exit2;
    String stringDate = "";
    while (!exit) {
      view.chooseFromPortfolioList(model.getPortfolioList());
      view.selectFromAboveMessage();
      String choice = sc.nextLine().strip();
      if (dropDownChoice(choice, model)) {
        break;
      }
      exit2 = false;
      while (!exit2) {
        view.totalValueGetDate();
        stringDate = sc.nextLine().strip();
        if (!Validation.validateDate(stringDate)) {
          view.invalidDateShowError();
        } else {
          exit2 = true;
        }
      }
      view.showTotalValue(model.getTotalValue(model.getPortfolioList()
              .get(Integer.parseInt(choice) - 1).getName(), stringDate), "AlphaVantage API");
      exit = true;
    }
  }

  private void saveData(ModelHandler model) {
    boolean exit = false;
    String userName;
    while (!exit) {
      try {
        boolean exit2 = false;
        while (!exit2) {
          boolean name = model.saveData("SavedPortfolios.xml");
          if (!name) {
            view.inputManuallyGetUserName();
            userName = sc.nextLine();
            model.inputManuallySetUserName(userName);
          } else {
            exit2 = true;
          }
        }
      } catch (Exception e) {
        // do nothing;
      }
      view.saveData();
      exit = true;
    }
  }


  private String setStockSymbol(ModelHandler model) {
    view.addPortfolioGetStockSymbol();
    String stockSymbol = sc.nextLine().strip();
    boolean support = model.checkStockSupport(stockSymbol);
    if (!support) {
      view.stockNoSupportShowError();
      return null;
    }
    model.addStockToCache(stockSymbol);
    return stockSymbol;
  }

  private String setStockQuantity() {
    view.addPortfolioGetQuantity();
    String quantity = sc.nextLine().strip();
    if (!Validation.checkIfNumber(quantity)) {
      view.invalidNumberShowError();
      return null;
    }
    return quantity;
  }

  private String setStockWeight() {
    view.getStockWeight();
    String stringWeight = sc.nextLine().strip();
    if (Validation.checkValidWeight(stringWeight)) {
      view.invalidWeightShowError();
      return null;
    }
    return stringWeight;
  }

  private String setStockCommission() {
    view.addPortfolioGetCommission();
    String commission = sc.nextLine().strip();
    if (!Validation.checkValidCommission(commission)) {
      view.invalidCommissionShowError();
      return null;
    }
    return commission;
  }

  private String setStockPurchaseDate(String portfolioOption) {
    if (portfolioOption.equals("7")) {
      view.getDateFor("Selling");
    } else {
      view.getDateFor("Purchase");
    }
    String stringPurchaseDate = sc.nextLine().strip();
    if (!Validation.validateDate(stringPurchaseDate)) {
      view.invalidDateShowError();
      return null;
    }
    return stringPurchaseDate;
  }

  private String setStrategyAmount() {
    view.getAmount();
    String stringAmount = sc.nextLine().strip();
    if (!Validation.checkIfNumber(stringAmount)) {
      view.invalidNumberShowError();
      return null;
    }
    return stringAmount;
  }

  private String setStrategyFrequency() {
    view.getFrequency();
    String stringFrequency = sc.nextLine().strip();
    if (!Validation.checkIfNumber(stringFrequency)) {
      view.invalidNumberShowError();
      return null;
    }
    return stringFrequency;
  }


}
