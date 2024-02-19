package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements FlexiblePortfolioImpl and extends PortfolioImpl.
 * It contains definition of all methods declared in interface.
 * Users can now add and remove stocks from a portfolio.
 */
public class FlexiblePortfolioImpl extends PortfolioImpl implements FlexiblePortfolio {

  private final List<Stock> stockList;

  /**
   * Constructor for FlexiblePortfolioImpl class.
   *
   * @param name      unique name of the portfolio
   * @param stockList list of stocks in the portfolio
   */
  public FlexiblePortfolioImpl(String name, List<Stock> stockList) {
    super(name, stockList);
    this.stockList = stockList;
  }

  @Override
  public void addStock(Stock s) {
    this.stockList.add(new StockImpl.StockBuilder().stockSymbol(s.getStockSymbol())
            .quantity(s.getQuantity()).purchaseDate(LocalDate.parse(s.getPurchaseDate())).build());
  }

  @Override
  public void removeStock(Stock s) {
    String stockSymbol = s.getStockSymbol();
    Double quantity = s.getQuantity();
    int i = 0;
    while (i < stockList.size() && quantity != 0) {
      if (stockSymbol.equals(stockList.get(i).getStockSymbol())) {
        Stock temp = stockList.get(i);
        String[] tokens = temp.getPurchaseDate().split("-");
        LocalDate purchaseDate = LocalDate.of(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        tokens = s.getPurchaseDate().split("-");
        LocalDate sellingDate = LocalDate.of(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        if (purchaseDate.isBefore(sellingDate)) {
          if (quantity >= temp.getQuantity()) {
            quantity -= temp.getQuantity();
            this.stockList.remove(i);
            i--;
            if (quantity == 0) {
              break;
            }
          } else {

            Stock s1 = new StockImpl.StockBuilder().stockSymbol(stockSymbol)
                    .quantity(temp.getQuantity() - quantity)
                    .purchaseDate(LocalDate.parse(temp.getPurchaseDate()))
                    .stockName(temp.getStockName())
                    .purchasePrice(temp.getPurchasePrice())
                    .build();
            stockList.set(i, s1);
            break;
          }
        }
      }
      i++;
    }
  }

  /**
   * Builder class for initialising the FlexiblePortfolioImpl class step by step.
   * If no value is assigned default values will be assigned.
   * FlexiblePortfolioImpl object cannot be created unless a valid unique
   * portfolio name is provided.
   * The default value for stockList is an empty ArrayList.
   */
  public static class FlexiblePortfolioBuilder {
    private String name;
    private List<Stock> stockList;

    public FlexiblePortfolioBuilder() {
      this.stockList = new ArrayList<>();
      this.name = null;
    }

    public FlexiblePortfolioBuilder name(String name) {
      this.name = name;
      return this;
    }

    public FlexiblePortfolioBuilder stockList(List<Stock> stockList) {
      this.stockList.addAll(stockList);
      return this;
    }

    /**
     * It creates the PortfolioImpl object with the details given at each step using the builder.
     * Object cannot be created if portfolioName is null.
     *
     * @return Portfolio object
     */
    public FlexiblePortfolio build() {
      if (name == null) {
        throw new IllegalArgumentException("No Portfolio name");
      }
      return new FlexiblePortfolioImpl(name, stockList);
    }
  }
}
