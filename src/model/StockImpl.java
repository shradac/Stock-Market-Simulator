package model;

import java.time.LocalDate;
import java.util.Map;

import model.api.ApiCalling;
import model.api.ApiCallingAlphaVantageImpl;
import model.parser.CsvParser;


/**
 * This class implements the stock interface.
 */
public class StockImpl implements Stock {

  private final String stockSymbol;
  private final String stockName;
  private final double quantity;
  private final LocalDate purchaseDate;
  private final double purchasePrice;
  private final double commission;

  private StockImpl(String stockSymbol, String stockName, double quantity, LocalDate purchaseDate,
                    double purchasePrice, double commission) {
    this.stockSymbol = stockSymbol;
    this.stockName = stockName;
    this.quantity = quantity;
    this.purchaseDate = purchaseDate;
    this.purchasePrice = purchasePrice;
    this.commission = commission;
  }

  @Override
  public String getStockSymbol() {
    return this.stockSymbol;
  }

  @Override
  public double getQuantity() {
    return this.quantity;
  }

  @Override
  public String getStockName() {
    return this.stockName;
  }

  @Override
  public String getPurchaseDate() {
    return this.purchaseDate.toString();
  }

  @Override
  public double getPurchasePrice() {
    return this.purchasePrice;
  }

  @Override
  public double getCommission() {
    return this.commission;
  }

  /**
   * Builder class for initialising the StockImpl class step by step.
   * If no value is assigned default values will be assigned.
   * StockImpl object cannot be created unless a valid stockSymbol is provided.
   * The default value for quantity is 0, purchaseDate is today, purchasePrice is 0.
   */
  public static class StockBuilder {
    private String stockSymbol;
    private String stockName;
    private double quantity;
    private LocalDate purchaseDate;
    private double purchasePrice;
    private double commission;

    /**
     * Constructor for the StockBuilder.
     * Assigns default values if not given.
     */
    public StockBuilder() {
      stockName = null;
      stockSymbol = null;
      quantity = 0;
      purchaseDate = LocalDate.now();
      purchasePrice = 0;
      commission = 2;
    }

    /**
     * Initialises stockName.
     *
     * @param name stockName
     * @return the current object
     */
    public StockBuilder stockName(String name) {
      this.stockName = name;
      return this;
    }

    /**
     * Initialises stockSymbol.
     *
     * @param symbol stockSymbol
     * @return the current object
     */
    public StockBuilder stockSymbol(String symbol) {
      this.stockSymbol = symbol.toUpperCase();
      return this;
    }

    /**
     * Initialises quantity.
     *
     * @param number quantity
     * @return the current object
     */
    public StockBuilder quantity(double number) {
      if (number < 0) {
        throw new IllegalArgumentException("Negative number");
      }
      this.quantity = number;
      return this;
    }

    /**
     * Initialises purchasePrice.
     *
     * @param purchasePrice purchasePrice
     * @return the current object
     */
    public StockBuilder purchasePrice(double purchasePrice) {
      this.purchasePrice = purchasePrice;
      return this;
    }

    /**
     * Initialises purchaseDate.
     *
     * @param date purchaseDate
     * @return the current object
     */
    public StockBuilder purchaseDate(LocalDate date) {
      if (date.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("Purchase date cannot be future date.");
      }
      this.purchaseDate = date;
      return this;
    }

    /**
     * Initialises commission.
     *
     * @param commission commission
     * @return the current object
     */
    public StockBuilder commission(double commission) {
      this.commission = commission;
      return this;
    }


    /**
     * It creates the StockImpl object with the details given at each step using the builder.
     * Object cannot be created if stockSymbol is null.
     *
     * @return Stock object
     */
    public Stock build() {
      if (stockSymbol == null) {
        throw new IllegalArgumentException("No Stock Symbol");
      }
      if (commission < 2) {
        commission = 0;
      }
      ApiCalling apiAlpha = new ApiCallingAlphaVantageImpl();
      purchasePrice = apiAlpha.getPrice(stockSymbol, purchaseDate, true) * quantity;
      Map<String, String> supportStockList = CsvParser.getListOfStocks(
              System.getProperty("user.dir") + "/SupportedListOfStocks.csv");
      stockName = supportStockList.get(stockSymbol.toUpperCase());
      return new StockImpl(stockSymbol, stockName, quantity, purchaseDate, purchasePrice,
              commission);
    }
  }
}
