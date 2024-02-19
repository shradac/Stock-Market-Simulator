package model;

import java.time.LocalDate;

import model.api.ApiCalling;
import model.api.ApiCallingAlphaVantageImpl;

/**
 * The TransactionImpl class implements the Transaction interface.
 */
public class TransactionImpl implements Transaction {
  private final String portfolioName;
  private final String type;
  private final String stockSymbol;
  private final double quantity;
  private final LocalDate date;
  private final double price;
  private final double commission;

  /**
   * Constructor for the TransactionImpl class.
   *
   * @param portfolioName unique name of the portfolio
   * @param type          type of the transaction(buy or sell)
   * @param stockSymbol   unique stock symbol(ticker symbol)
   * @param quantity      number of stocks
   * @param date          date of transaction
   * @param price         price of the stock
   * @param commission    commission fee for the transaction
   */
  TransactionImpl(String portfolioName, String type, String stockSymbol,
                  double quantity, LocalDate date, double price, double commission) {
    this.portfolioName = portfolioName;
    this.type = type;
    this.stockSymbol = stockSymbol.toUpperCase();
    this.quantity = quantity;
    this.date = date;
    this.price = price;
    this.commission = commission;
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public double getQuantity() {
    return quantity;
  }

  @Override
  public LocalDate getDate() {
    return date;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public double getCommission() {
    return commission;
  }

  /**
   * Builder class for initialising the TransactionImpl class step by step.
   * All the fields should be provided before creating a Transaction object.
   */
  public static class TransactionBuilder {
    private String portfolioName;
    private String type;
    private String stockSymbol;
    private double quantity;
    private double price;
    private double commission;
    private LocalDate date;

    /**
     * Constructor for TransactionBuilder class.
     * Assigns the default values to the fields.
     */
    public TransactionBuilder() {
      this.portfolioName = null;
      this.type = null;
      this.stockSymbol = null;
      this.quantity = 0;
      this.price = 0;
      this.date = null;
      this.commission = 5;
    }

    /**
     * Initialises portfolioName.
     *
     * @param name portfolioName
     * @return the current object
     */
    public TransactionBuilder portfolioName(String name) {
      this.portfolioName = name;
      return this;
    }

    /**
     * Initialises transaction type (buy or sell).
     *
     * @param type type
     * @return the current object
     */
    public TransactionBuilder type(String type) {
      this.type = type;
      return this;
    }

    /**
     * Initialises stockSymbol.
     *
     * @param stockSymbol stockSymbol
     * @return the current object
     */
    public TransactionBuilder stockSymbol(String stockSymbol) {
      this.stockSymbol = stockSymbol;
      return this;
    }

    /**
     * Initialises quantity.
     *
     * @param quantity quantity
     * @return the current object
     */
    public TransactionBuilder quantity(double quantity) {
      this.quantity = quantity;
      return this;
    }

    /**
     * Initialises commission.
     *
     * @param commission commission
     * @return the current object
     */
    public TransactionBuilder commission(double commission) {
      this.commission = commission;
      return this;
    }

    /**
     * Initialises date (buying or selling date).
     *
     * @param date date
     * @return the current object
     */
    public TransactionBuilder date(LocalDate date) {
      if (date.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("Purchase date cannot be future date.");
      }
      this.date = date;
      return this;
    }

    /**
     * It creates the TransactionImpl object with the details given at each step using the builder.
     * Object cannot be created if all fields are not given.
     *
     * @return Transaction object
     */
    public TransactionImpl build() {
      if (portfolioName == null) {
        throw new IllegalArgumentException("No username");
      }
      if (type == null) {
        throw new IllegalArgumentException("No TransactionImpl type");
      }
      if (stockSymbol == null) {
        throw new IllegalArgumentException("No Stock Symbol");
      }
      if (date == null) {
        throw new IllegalArgumentException("No date");
      }
      if (quantity == 0) {
        throw new IllegalArgumentException("No quantity");
      }
      ApiCalling apiAlpha = new ApiCallingAlphaVantageImpl();
      price = apiAlpha.getPrice(stockSymbol, date, true) * quantity;
      return new TransactionImpl(portfolioName, type, stockSymbol, quantity, date, price,
              commission);
    }
  }

}
