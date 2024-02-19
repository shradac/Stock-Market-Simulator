package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import model.api.ApiCalling;
import model.api.ApiCallingAlphaVantageImpl;


/**
 * This class implements the user interface.
 */
public class UserImpl implements User {

  private final String userName;
  private final String portfolioType;
  private final List<Portfolio> portfolioList;
  private final List<Transaction> transactionList;
  private final List<Strategy> strategyList;

  private UserImpl(String name, String portfolioType, List<Portfolio> portfolioList,
                   List<Transaction> transactionList, List<Strategy> strategyList) {
    this.userName = name;
    this.portfolioType = portfolioType;
    this.portfolioList = portfolioList;
    this.transactionList = transactionList;
    this.strategyList = strategyList;
  }

  @Override
  public void addPortfolio(Portfolio obj) {
    if (obj.getName() == null) {
      throw new IllegalArgumentException("Null object");
    }
    for (Portfolio portfolio : portfolioList) {
      if (obj.getName().equals(portfolio.getName())) {
        throw new IllegalArgumentException("Duplicate portfolio name.");
      }
    }
    this.portfolioList.add(obj);
  }

  @Override
  public String getUserName() {
    return this.userName;
  }

  @Override
  public String getPortfolioType() {
    return portfolioType;
  }


  @Override
  public double totalValue(String portfolioName, LocalDate date) {
    List<Stock> stockList = new ArrayList<>();
    ApiCalling apiAlpha = new ApiCallingAlphaVantageImpl();
    for (Portfolio portfolio : this.portfolioList) {
      if (portfolio.getName().equals(portfolioName)) {
        stockList = portfolio.getStockList();
      }
    }
    double totalValue = 0;
    if (getPortfolioType().equals("flexible")) {
      totalValue = 0;
      for (Stock stock : stockList) {
        try {
          if (date.isAfter(LocalDate.parse(stock.getPurchaseDate()))) {
            totalValue += apiAlpha.getPrice(stock.getStockSymbol(), date, false)
                    * stock.getQuantity();
          }
        } catch (Exception e) {
          totalValue += 0;
        }
      }
      for (Transaction t : transactionList) {
        if (t.getPortfolioName().equals(portfolioName) && date.isAfter(t.getDate())) {
          if (t.getType().equals("buy")) {
            try {
              totalValue += apiAlpha.getPrice(t.getStockSymbol(), date, false)
                      * t.getQuantity();
            } catch (Exception e) {
              totalValue += 0;
            }
          } else if (t.getType().equals("sell")) {
            try {
              totalValue -= apiAlpha.getPrice(t.getStockSymbol(), date, false)
                      * t.getQuantity();
            } catch (Exception e) {
              totalValue -= 0;
            }
          }
        }
      }
    } else {
      for (Stock stock : stockList) {
        if (date.isAfter(LocalDate.parse(stock.getPurchaseDate()))) {
          try {
            totalValue += apiAlpha.getPrice(stock.getStockSymbol(), date, false)
                    * stock.getQuantity();
          } catch (Exception e) {
            totalValue += 0;
          }
        }
      }

    }
    return totalValue;
  }

  @Override
  public List<Portfolio> getPortfolioList() {
    return this.portfolioList;
  }

  @Override
  public List<Transaction> getTransactionList() {
    return this.transactionList;
  }


  @Override
  public List<Strategy> getStrategyList() {
    return this.strategyList;
  }


  /**
   * Builder class for initialising the UserImpl class step by step.
   * If no value is assigned default values will be assigned.
   * UserImpl object cannot be created unless a valid userName is provided.
   * The default value for portfolioList is Empty ArrayList.
   */
  public static class UserBuilder {
    private String userName;
    private String portfolioType;
    private List<Portfolio> portfolioList;
    private List<Transaction> transactionList;
    private List<Strategy> strategyList;

    /**
     * Constructor for UserBuilder.
     */
    public UserBuilder() {
      this.userName = null;
      this.portfolioType = null;
      this.portfolioList = new ArrayList<>();
      this.transactionList = new ArrayList<>();
      this.strategyList = new ArrayList<>();
    }

    /**
     * Initialises userName.
     *
     * @param name userName
     * @return the current object
     */
    public UserBuilder userName(String name) {
      this.userName = name;
      return this;
    }

    /**
     * Initialises portfolioType.
     *
     * @param type type
     * @return the current object
     */

    public UserBuilder portfolioType(String type) {
      this.portfolioType = type;
      return this;
    }

    /**
     * Initialises portfolioList.
     *
     * @param portfolioList portfolioList
     * @return the current object
     */
    public UserBuilder portfolioList(List<Portfolio> portfolioList) {
      if (portfolioList.size() != 0) {
        if (portfolioList.get(0).getStockList().size() == 0) {
          //throw new IllegalArgumentException("Null Portfolio object");
          return this;
        }
        for (Portfolio p : portfolioList) {
          if (p.getClass() == FlexiblePortfolioImpl.class) {
            this.portfolioList.add(new FlexiblePortfolioImpl.FlexiblePortfolioBuilder()
                    .name(p.getName()).stockList(p.getStockList()).build());
          } else {
            this.portfolioList.add(new PortfolioImpl.PortfolioBuilder().name(p.getName())
                    .stockList(p.getStockList()).build());
          }
        }
      }
      return this;
    }

    /**
     * Initialises transactionList.
     *
     * @param transactionList transactionImplList
     * @return the current object
     */
    public UserBuilder transactionList(List<Transaction> transactionList) {
      this.transactionList.addAll(transactionList);
      return this;
    }

    /**
     * Initialises strategyList.
     *
     * @param strategyList strategyList
     * @return the current object
     */
    public UserBuilder strategyList(List<Strategy> strategyList) {
      this.strategyList.addAll(strategyList);
      return this;
    }

    /**
     * It creates the UserImpl object with the details given at each step using the builder.
     * Object cannot be created if userName is null.
     *
     * @return User object
     */
    public User build() {
      if (userName == null) {
        throw new IllegalArgumentException("No username");
      }
      return new UserImpl(userName, portfolioType, portfolioList, transactionList, strategyList);
    }
  }
}
