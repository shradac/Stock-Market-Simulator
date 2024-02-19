package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the Strategy interface.
 */
public class StrategyImpl implements Strategy {
  private final double amount;
  private final Map<String, Double> stockMap;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final int frequency;
  private final String portfolioName;
  private final boolean isOnGoing;
  private final double commission;

  /**
   * Constructor for StrategyImpl class.
   * @param amount amount
   * @param stockMap stockMap
   * @param startDate startDate
   * @param endDate endDate
   * @param frequency frequency
   * @param portfolioName portfolioName
   * @param isOnGoing isOnGoing
   * @param commission commission
   */
  public StrategyImpl(double amount, Map<String, Double> stockMap, LocalDate startDate,
                      LocalDate endDate, int frequency, String portfolioName, boolean isOnGoing,
                      double commission) {
    this.amount = amount;
    this.stockMap = stockMap;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.portfolioName = portfolioName;
    this.isOnGoing = isOnGoing;
    this.commission = commission;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public int getFrequency() {
    return frequency;
  }

  @Override
  public LocalDate getEndDate() {
    return endDate;
  }

  @Override
  public LocalDate getStartDate() {
    return startDate;
  }

  @Override
  public Map<String, Double> getStockMap() {
    return stockMap;
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public double getCommission() {
    return commission;
  }

  @Override
  public boolean getIsOnGoing() {
    return isOnGoing;
  }

  @Override
  public Map<String, Double> addStockToMap(String stockSymbol, Double weight) {
    if (stockMap.containsKey(stockSymbol)) {
      throw new IllegalArgumentException("Stock symbol already added to plan.");
    }
    stockMap.put(stockSymbol, weight);
    return stockMap;
  }

  /**
   * Builder class for initialising the StrategyImpl class step by step.
   * If no value is assigned default values will be assigned.
   * StrategyImpl object cannot be created unless the weight associated sum upto 100 percent.
   */

  public static class StrategyImplBuilder {

    private double amount;
    private Map<String, Double> stockMap;
    private LocalDate startDate;
    private LocalDate endDate;
    private int frequency;
    private String portfolioName;
    private boolean isOnGoing;
    private double commission;

    /**
     * Constructor for the Strategy Impl.
     */
    public StrategyImplBuilder() {
      this.amount = 0;
      this.stockMap = new HashMap<>();
      this.startDate = null;
      this.endDate = null;
      this.frequency = 0;
      this.portfolioName = null;
      this.isOnGoing = false;
      this.commission = 2;
    }

    /**
     * Initialises the amount.
     *
     * @param amount amount
     * @return the current object
     */

    public StrategyImplBuilder amount(double amount) {
      this.amount = amount;
      return this;
    }

    /**
     * Initialises the endDate.
     *
     * @param endDate end date
     * @return the current object
     */

    public StrategyImplBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    /**
     * Initialises the frequency.
     *
     * @param frequency frequency
     * @return the current object
     */
    public StrategyImplBuilder frequency(int frequency) {
      this.frequency = frequency;
      return this;
    }

    /**
     * Initialises the startDate.
     *
     * @param startDate start date
     * @return the current object
     */

    public StrategyImplBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    /**
     * Initialises the stockMap.
     *
     * @param stockMap stock map
     * @return the current object
     */

    public StrategyImplBuilder stockMap(Map<String, Double> stockMap) {
      this.stockMap.putAll(stockMap);
      return this;
    }

    /**
     * Initialises the portfolioName.
     *
     * @param portfolioName Portfolio Name
     * @return the current object
     */
    public StrategyImplBuilder portfolioName(String portfolioName) {
      this.portfolioName = portfolioName;
      return this;
    }

    /**
     * Initialises the isOnGoing.
     *
     * @param onGoing isOnGoing
     * @return the current object
     */
    public StrategyImplBuilder isOnGoing(boolean onGoing) {
      isOnGoing = onGoing;
      return this;
    }

    /**
     * Initialises the commission.
     *
     * @param commission commission
     * @return the current object
     */
    public StrategyImplBuilder commission(double commission) {
      this.commission = commission;
      return this;
    }

    /**
     * Initialises the object with all the data from another Strategy object.
     *
     * @param strategy strategy
     * @return the current object
     */
    public StrategyImplBuilder strategy(Strategy strategy) {
      this.amount = strategy.getAmount();
      this.stockMap = strategy.getStockMap();
      this.startDate = strategy.getStartDate();
      this.endDate = strategy.getEndDate();
      this.frequency = strategy.getFrequency();
      this.portfolioName = strategy.getPortfolioName();
      this.isOnGoing = strategy.getIsOnGoing();
      this.commission = strategy.getCommission();
      return this;
    }

    /**
     * It creates the StrategyImpl object with the details given at each step using the builder.
     *
     * @return Strategy object
     */

    public Strategy build() {
      return new StrategyImpl(amount, stockMap, startDate, endDate, frequency, portfolioName,
              isOnGoing, commission);
    }


  }


}
