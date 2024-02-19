package model;

import java.util.ArrayList;
import java.util.List;


/**
 * This class implements the Portfolio interface.
 */
public class PortfolioImpl implements Portfolio {

  private final String name;

  private final List<Stock> stockList;

  /**
   * Constructor for PortfolioImpl class.
   *
   * @param name      unique name of the portfolio
   * @param stockList list of stocks in the portfolio
   */
  public PortfolioImpl(String name, List<Stock> stockList) {
    this.name = name;
    this.stockList = stockList;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<Stock> getStockList() {
    return stockList;
  }

  /**
   * Builder class for initialising the PortfolioImpl class step by step.
   * If no value is assigned default values will be assigned.
   * PortfolioImpl object cannot be created unless a valid unique portfolio name is provided.
   * The default value for stockList is an empty ArrayList.
   */
  public static class PortfolioBuilder {
    private String name;
    private List<Stock> stockList;

    public PortfolioBuilder() {
      this.stockList = new ArrayList<>();
      this.name = null;
    }

    public PortfolioBuilder name(String name) {
      this.name = name;
      return this;
    }

    public PortfolioBuilder stockList(List<Stock> stockList) {
      this.stockList.addAll(stockList);
      return this;
    }

    /**
     * It creates the PortfolioImpl object with the details given at each step using the builder.
     * Object cannot be created if portfolioName is null.
     *
     * @return Portfolio object
     */
    public Portfolio build() {
      if (name == null) {
        throw new IllegalArgumentException("No Portfolio name");
      }
      return new PortfolioImpl(name, stockList);
    }

  }

}
