package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.api.ApiCalling;
import model.api.ApiCallingAlphaVantageImpl;
import model.parser.CsvParser;
import model.parser.ReaderParser;
import model.parser.ReaderParserImpl;
import model.parser.WriterParser;
import model.parser.WriterParserImpl;
import view.ChartDrawImpl;
import view.Draw;


/**
 * This class implements the modelHandler interface. This class acts as the link between the
 * model and the controller.
 * The modelHandler is the only way to communicate between the model and the
 * controller.
 * Any communication to be done from the controller to the model, the modelHandler is
 * the one and only path for communication.
 * The modelHandler has several functionalities such as
 * initialising the user data, saving the data, adding stocks and so on.
 */

public class ModelHandlerImpl implements ModelHandler {

  String userName = "";
  List<Stock> stockList = new ArrayList<>();
  String portfolioType = "";
  User user = new UserImpl.UserBuilder().userName("").portfolioType(portfolioType).build();
  User userInitialState = new UserImpl.UserBuilder().userName("")
          .portfolioType(portfolioType).build();
  List<Transaction> transactionList = new ArrayList<>();
  List<Strategy> strategyList = new ArrayList<>();
  Map<String, Double> stockMap = new LinkedHashMap<>();

  @Override
  public User initializeUserData(String location, String portfolioMenuOption) {

    ReaderParser reader = new ReaderParserImpl();
    if (this.getPortfolioList().size() == 0) {
      user = reader.readData(location);
      for (Strategy strategy : user.getStrategyList()) {
        Strategy s = convertStrategyToTransactions(strategy);
        if (s != null) {
          strategyList.add(s);
        }
      }
      this.userName = user.getUserName();
      userInitialState = new UserImpl.UserBuilder()
              .userName(userName)
              .portfolioType(portfolioType)
              .portfolioList(user.getPortfolioList())
              .transactionList(user.getTransactionList())
              .strategyList(strategyList)
              .build();
    } else {
      User temp = reader.readData(location);
      user = new UserImpl.UserBuilder()
              .userName(temp.getUserName())
              .portfolioType(portfolioType)
              .portfolioList(user.getPortfolioList())
              .transactionList(this.getTransactionList())
              .strategyList(strategyList)
              .build();
      List<Portfolio> list = temp.getPortfolioList();
      for (Portfolio portfolio : list) {
        try {
          user.addPortfolio(portfolio);
        } catch (Exception e) {
          //do nothing
        }
      }
      for (Strategy strategy : temp.getStrategyList()) {
        Strategy s = convertStrategyToTransactions(strategy);
        if (s != null) {
          strategyList.add(s);
        }
      }
    }

    if (user.getPortfolioType().equals("flexible") && portfolioMenuOption.equals("1")) {
      return null;
    }
    if (user.getPortfolioType().equals("inflexible") && portfolioMenuOption.equals("2")) {
      return null;
    }

    applyTransactions(user.getTransactionList());
    return user;
  }


  private void applyTransactions(List<Transaction> transactionList) {
    for (Transaction t : transactionList) {
      if (t.getType().equals("buy")) {
        if (this.getPortfolioIndex(t.getPortfolioName()) == -1) {
          Stock s = new StockImpl.StockBuilder()
                  .stockSymbol(t.getStockSymbol())
                  .quantity(t.getQuantity())
                  .purchaseDate(t.getDate())
                  .commission(t.getCommission())
                  .build();
          stockList.add(s);
          this.addStockListToFlexiblePortfolio(t.getPortfolioName());
        } else {
          addStockToPortfolio(t.getStockSymbol(), String.valueOf(t.getQuantity()),
                  t.getDate().toString(),
                  t.getPortfolioName(),
                  String.valueOf(t.getCommission()));
        }

      }
      if (t.getType().equals("sell")) {
        sellStockFromPortfolio(t.getStockSymbol(), String.valueOf(t.getQuantity()),
                t.getDate().toString(),
                t.getPortfolioName(),
                String.valueOf(t.getCommission()));
      }
    }
  }

  private int getPortfolioIndex(String portfolioName) {
    Portfolio portfolio = getPortfolioList().stream()
            .filter(p -> portfolioName.equals(p.getName()))
            .findAny()
            .orElse(null);
    if (portfolio == null) {
      return -1;
    }
    return getPortfolioList().indexOf(portfolio);
  }

  private Portfolio getPortfolioObjectFromName(String portfolioName,
                                               List<Portfolio> portfolioList) {
    Portfolio portfolio = portfolioList.stream()
            .filter(p -> portfolioName.equals(p.getName()))
            .findAny()
            .orElse(null);
    return portfolio;
  }

  @Override
  public Stock addStock(String stockSymbol, String stringQuantity, String stringPurchaseDate,
                        String portfolioName, String stringCommission) {
    int quantity = Integer.parseInt(stringQuantity);
    double commission = 0;
    if (stringCommission != null) {
      commission = Double.parseDouble(stringCommission);
    }
    LocalDate purchaseDate = stringToLocalDate(stringPurchaseDate);
    StockImpl.StockBuilder sBuilder = new StockImpl.StockBuilder();
    sBuilder.stockSymbol(stockSymbol.toUpperCase());
    sBuilder.purchaseDate(purchaseDate);
    sBuilder.commission(commission);
    boolean flag = false;
    if (portfolioName == null) {
      for (int i = 0; i < stockList.size(); i++) {
        if (stockList.get(i).getStockSymbol().equals(stockSymbol.toUpperCase())) {
          flag = true;
          sBuilder.quantity(stockList.get(i).getQuantity() + quantity);
          stockList.set(i, sBuilder.build());
          break;
        }
      }
      if (!flag) {
        sBuilder.quantity(quantity);
        stockList.add(sBuilder.build());
      }
      return sBuilder.build();
    } else {
      sBuilder.quantity(quantity);
      stockList.add(sBuilder.build());
    }
    return null;

  }

  @Override
  public FlexiblePortfolio addStockToPortfolio(String stockSymbol, String stringQuantity,
                                               String stringPurchaseDate, String portfolioName,
                                               String stringCommission) {
    double quantity = Double.parseDouble(stringQuantity);
    double commission = Double.parseDouble(stringCommission);
    LocalDate purchaseDate = stringToLocalDate(stringPurchaseDate);
    transactionList.add(new TransactionImpl.TransactionBuilder()
            .portfolioName(portfolioName)
            .type("buy")
            .stockSymbol(stockSymbol)
            .quantity(quantity)
            .date(purchaseDate)
            .commission(commission)
            .build());
    StockImpl.StockBuilder sBuilder = new StockImpl.StockBuilder();
    sBuilder.stockSymbol(stockSymbol.toUpperCase());
    sBuilder.purchaseDate(purchaseDate);
    sBuilder.quantity(quantity);
    sBuilder.commission(commission);
    Stock s = sBuilder.build();

    if (user.getPortfolioList().size() != 0) {
      if (user.getPortfolioList().get(0).getClass() == FlexiblePortfolioImpl.class) {
        for (Portfolio portfolio : user.getPortfolioList()) {
          if (portfolioName.equals(portfolio.getName())) {
            FlexiblePortfolio p = (FlexiblePortfolio) portfolio;
            p.addStock(s);
//            p.getStockList().set(i,s);
            return p;
          }
        }
      }
    }
    return null;
  }

  @Override
  public boolean checkPortfolioNameDuplicate(String portfolioName) {
    List<Portfolio> portfolioList = user.getPortfolioList();
    for (Portfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Portfolio addStockListToPortfolio(String portfolioName) {
    Portfolio p = new PortfolioImpl.
            PortfolioBuilder().
            name(portfolioName).
            stockList(stockList).
            build();
    user.addPortfolio(p);
    stockList.clear();
    return p;
  }


  @Override
  public void addStockListToFlexiblePortfolio(String portfolioName) {
    FlexiblePortfolio p = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().
            name(portfolioName).
            stockList(stockList).
            build();
    user.addPortfolio(p);
    for (Stock s : stockList) {
      transactionList.add(new TransactionImpl.TransactionBuilder()
              .portfolioName(portfolioName)
              .type("buy")
              .stockSymbol(s.getStockSymbol())
              .quantity(s.getQuantity())
              .date(LocalDate.parse(s.getPurchaseDate()))
              .commission(s.getCommission())
              .build());
    }
    stockList.clear();
  }

  @Override
  public boolean checkSellQuantity(String stockSymbol, String stringQuantity,
                                   String stringSellingDate, String portfolioName) {
    try {
      double quantity = Double.parseDouble(stringQuantity);
      Portfolio p = getPortfolioObjectFromName(portfolioName, user.getPortfolioList());
      double totalQuantity = 0;
      for (Stock s : p.getStockList()) {
        if (s.getStockSymbol().equals(stockSymbol.toUpperCase())) {
          LocalDate purchaseDate = stringToLocalDate(s.getPurchaseDate());
          LocalDate sellingDate = stringToLocalDate(stringSellingDate);
          if (purchaseDate.isBefore(sellingDate)) {
            totalQuantity += s.getQuantity();
          }
        }
      }
      return totalQuantity < quantity;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void sellStockFromPortfolio(String stockSymbol, String stringQuantity,
                                     String stringPurchaseDate, String portfolioName,
                                     String stringCommission) {
    double quantity = Double.parseDouble(stringQuantity);
    double commission = Double.parseDouble(stringCommission);
    if (user.getPortfolioList().size() != 0) {
      if (user.getPortfolioList().get(0).getClass() == FlexiblePortfolioImpl.class) {
        FlexiblePortfolio p = (FlexiblePortfolio) getPortfolioObjectFromName(portfolioName,
                user.getPortfolioList());
        LocalDate purchaseDate = stringToLocalDate(stringPurchaseDate);
        transactionList.add(new TransactionImpl.TransactionBuilder()
                .portfolioName(portfolioName)
                .type("sell")
                .stockSymbol(stockSymbol)
                .quantity(quantity)
                .commission(commission)
                .date(purchaseDate)
                .build());
        Stock s = new StockImpl.StockBuilder()
                .stockSymbol(stockSymbol)
                .quantity(quantity)
                .purchaseDate(purchaseDate)
                .build();
        p.removeStock(s);
      }
    }
  }


  private List<Transaction> getTransactionList() {
    return this.transactionList;
  }

  @Override
  public void setPortfolioType(String type) {
    portfolioType = type;
    user = new UserImpl.UserBuilder()
            .userName(userName)
            .portfolioType(portfolioType)
            .portfolioList((user.getPortfolioList()))
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
    userInitialState = new UserImpl.UserBuilder()
            .userName(userName)
            .portfolioType(portfolioType)
            .portfolioList((userInitialState.getPortfolioList()))
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
  }

  @Override
  public Double getCostBasis(String portfolioName, String stringDate) {
    FlexiblePortfolio p;
    double cost = 0;
    LocalDate date = stringToLocalDate(stringDate);

    if (getPortfolioIndex(portfolioName) < userInitialState.getPortfolioList().size()) {
      if (userInitialState.getPortfolioList().size() != 0) {
        if (userInitialState.getPortfolioList().get(0).getClass() == FlexiblePortfolioImpl.class) {
          p = (FlexiblePortfolio) getPortfolioObjectFromName(portfolioName,
                  userInitialState.getPortfolioList());
          for (Stock s : p.getStockList()) {
            if (date.isAfter(LocalDate.parse(s.getPurchaseDate()))) {
              cost += s.getCommission();
              cost += s.getPurchasePrice();
            }
          }
        }
      }
    }
    for (Transaction t : getTransactionList()) {
      if (portfolioName.equals(t.getPortfolioName())) {
        if (t.getType().equals("buy") && date.isAfter(t.getDate())) {
          cost += t.getPrice();
          cost += t.getCommission();
        } else if (t.getType().equals("sell") && date.isAfter(t.getDate())) {
          cost += t.getCommission();
        }
      }
    }

    for (Strategy strategy : strategyList) {
      LocalDate currentDate = LocalDate.now();
      if (strategy.getEndDate().isAfter(currentDate) && !strategy.getIsOnGoing() &&
              strategy.getPortfolioName().equals(portfolioName)) {
        LocalDate endDate = strategy.getEndDate();
        while (currentDate.plusDays(strategy.getFrequency()).isBefore(date)
                && currentDate.isBefore(endDate)) {
          cost += strategy.getAmount();
          currentDate = currentDate.plusDays(strategy.getFrequency());
        }
      }
    }
    return cost;
  }

  @Override
  public Portfolio examine(String portfolioName) {
    for (Portfolio p : getPortfolioList()) {
      if (p.getName().equals(portfolioName)) {
        return p;
      }
    }
    return null;
  }

  @Override
  public List<Portfolio> getPortfolioList() {
    return user.getPortfolioList();
  }

  @Override
  public double getTotalValue(String portfolioName, String stringDate) {
    LocalDate date = stringToLocalDate(stringDate);
    userInitialState = new UserImpl.UserBuilder().userName(userName)
            .portfolioType(portfolioType)
            .portfolioList(userInitialState.getPortfolioList())
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
    if (portfolioType.equals("flexible")) {
      return userInitialState.totalValue(portfolioName, date);
    } else {
      return user.totalValue(portfolioName, date);

    }
  }

  @Override
  public boolean saveData(String fileSaveLocation) {
    WriterParser writer = new WriterParserImpl();
    if (userName.equals("")) {
      return false;
    }
    if (portfolioType.equals("flexible")) {
      List<Transaction> tempTranList = new ArrayList<>();
      tempTranList.addAll(transactionList);

      if (userInitialState.getPortfolioList().size() == 0) {
        List<String> pNames = new ArrayList<>();
        List<Portfolio> pList = new ArrayList<>();
        for (int i = 0; i < tempTranList.size(); i++) {
          Transaction t = tempTranList.get(i);
          if (!pNames.contains(t.getPortfolioName()) && t.getType().equals("buy")) {
            Stock s = new StockImpl.StockBuilder()
                    .stockSymbol(t.getStockSymbol())
                    .quantity(t.getQuantity())
                    .purchaseDate(t.getDate())
                    .commission(t.getCommission())
                    .build();
            List<Stock> sList = new ArrayList<>();
            sList.add(s);
            pList.add(new FlexiblePortfolioImpl.FlexiblePortfolioBuilder()
                    .name(t.getPortfolioName())
                    .stockList(sList).build());
            pNames.add(t.getPortfolioName());
            sList.clear();
            tempTranList.remove(i);
          }
        }
        userInitialState = new UserImpl.UserBuilder().userName(userName)
                .portfolioType(portfolioType).portfolioList(pList)
                .transactionList(tempTranList)
                .strategyList(strategyList)
                .build();
        pList.clear();
        pNames.clear();
      } else {
        userInitialState = new UserImpl.UserBuilder().userName(userName)
                .portfolioType(portfolioType)
                .portfolioList(userInitialState.getPortfolioList())
                .transactionList(tempTranList)
                .strategyList(strategyList)
                .build();
      }
      try {
        writer.writeData(userInitialState, fileSaveLocation);
      } catch (Exception e) {
        System.out.println("thrown");
        // do nothing
      }
    } else {
      try {
        writer.writeData(user, fileSaveLocation);
      } catch (Exception e) {
        System.out.println("thrown");
        // do nothing
      }
    }
    return true;
  }

  @Override
  public boolean checkStockSupport(String stockSymbol) {
    Map<String, String> supportStockList = CsvParser.getListOfStocks(
            System.getProperty("user.dir") + "/SupportedListOfStocks.csv");
    return supportStockList.containsKey(stockSymbol.toUpperCase());
  }

  @Override
  public boolean inputManuallySetUserName(String userName) {
    this.userName = userName;
    List<Portfolio> list = this.getPortfolioList();
    user = new UserImpl.UserBuilder()
            .userName(userName)
            .portfolioType(portfolioType)
            .portfolioList((user.getPortfolioList()))
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
    userInitialState = new UserImpl.UserBuilder()
            .userName(userName)
            .portfolioType(portfolioType)
            .portfolioList(userInitialState.getPortfolioList())
            .transactionList(getTransactionList())
            .strategyList(strategyList)
            .build();
    for (Portfolio portfolio : list) {
      try {
        user.addPortfolio(portfolio);
        List<Portfolio> pList = userInitialState.getPortfolioList();
        pList.add(portfolio);
        userInitialState = new UserImpl.UserBuilder()
                .userName(userName)
                .portfolioType(portfolioType)
                .portfolioList(pList)
                .transactionList(getTransactionList())
                .strategyList(strategyList)
                .build();
      } catch (Exception e) {
        //do nothing
      }
    }
    return this.userName.equals(userName);
  }

  @Override
  public Draw getChart(String start, String end, String portfolioName, int length) {
    Chart draw = new Chart();
    Map<String, String> chart;
    userInitialState = new UserImpl.UserBuilder().userName(userName)
            .portfolioType(portfolioType)
            .portfolioList(userInitialState.getPortfolioList())
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
    chart = draw.generateChart(start, end, portfolioName, userInitialState, length);
    double scale = draw.getScale();
    return new ChartDrawImpl(chart, scale, start, end, portfolioName);
  }

  @Override
  public void addStrategy(String portfolioName, Map<String, Double> stockMap, String stringAmount,
                          String stringCommission, String stringStartDate, String stringEndDate,
                          String stringFrequency) {
    double amount = Double.parseDouble(stringAmount);
    double commission = Double.parseDouble(stringCommission);
    int frequency = 1;
    if (stringFrequency != null) {
      frequency = Integer.parseInt(stringFrequency);
    }
    boolean isOnGoing = true;
    LocalDate endDate = LocalDate.now().plusYears(10);
    LocalDate startDate = stringToLocalDate(stringStartDate);
    if (!stringEndDate.equals("")) {
      endDate = stringToLocalDate(stringEndDate);
      isOnGoing = false;
    }
    int length = stockMap.size();
    amount = amount - commission * length;
    Strategy strategy = new StrategyImpl.StrategyImplBuilder()
            .portfolioName(portfolioName)
            .stockMap(stockMap)
            .commission(commission)
            .amount(amount)
            .startDate(startDate)
            .endDate(endDate)
            .isOnGoing(isOnGoing)
            .frequency(frequency)
            .build();
    strategy = convertStrategyToTransactions(strategy);
    if (strategy != null) {
      strategyList.add(strategy);
    }
  }

  @Override
  public void addStockToCache(String stockSymbol) {
    ApiCalling apiAlpha = new ApiCallingAlphaVantageImpl();
    apiAlpha.getPrice(stockSymbol, LocalDate.now().minusDays(5), false);
  }

  private Strategy convertStrategyToTransactions(Strategy strategy) {
    LocalDate startDate = strategy.getStartDate();
    LocalDate currentDate = startDate;
    LocalDate endDate = strategy.getEndDate();
    LocalDate transactionEndDate = endDate;
    List<Transaction> transactionList = new ArrayList<>();
    if (endDate.isAfter(LocalDate.now())) {
      transactionEndDate = LocalDate.now().minusDays(1);
    }
    double amount = strategy.getAmount();
    ApiCalling apiAlpha = new ApiCallingAlphaVantageImpl();
    int frequency = strategy.getFrequency();
    while (currentDate.isBefore(transactionEndDate.plusDays(1))) {
      Transaction t;
      for (String stockSymbol : strategy.getStockMap().keySet()) {
        double buyAmount = amount * (strategy.getStockMap().get(stockSymbol) / 100.0);
        double currentPrice = apiAlpha.getPrice(stockSymbol, currentDate, true);
        double quantity = buyAmount / currentPrice;
        t = new TransactionImpl.TransactionBuilder()
                .portfolioName(strategy.getPortfolioName())
                .stockSymbol(stockSymbol)
                .type("buy")
                .quantity(quantity)
                .date(currentDate)
                .commission(strategy.getCommission())
                .build();
        transactionList.add(t);
      }
      currentDate = currentDate.plusDays(frequency);
    }
    applyTransactions(transactionList);
    if (!transactionEndDate.equals(endDate) || strategy.getIsOnGoing()) {
      if (startDate.equals(endDate) && startDate.isAfter(LocalDate.now())) {
        strategy = new StrategyImpl.StrategyImplBuilder()
                .strategy(strategy)
                .startDate(startDate)
                .build();
      } else if (endDate.isAfter(LocalDate.now())) {
        strategy = new StrategyImpl.StrategyImplBuilder()
                .strategy(strategy)
                .startDate(transactionEndDate.plusDays(frequency))
                .build();
      }
    } else {
      return null;
    }
    return strategy;
  }

  @Override
  public LocalDate stringToLocalDate(String stringDate) {
    String[] tokens = stringDate.split("-");
    LocalDate date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
            Integer.parseInt(tokens[2]));
    return date;
  }

  @Override
  public List<Stock> getStockListToAddPortfolio() {
    return stockList;
  }

  @Override
  public Map<String, Double> getStockMap() {
    return stockMap;
  }


}
