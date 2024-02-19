package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test file for StrategyImpl class.
 * It contains various tests on the StrategyImpl class.
 */
public class StrategyImplTest {

  private Strategy s1;
  private Strategy s2;


  @Before
  public void setUp() {

    Map<String, Double> stockMap1 = new HashMap<>();
    stockMap1.put("aapl", 40.0);
    stockMap1.put("nflx", 60.0);

    Map<String, Double> stockMap2 = new HashMap<>();
    stockMap2.put("ibm", 10.0);
    stockMap2.put("nflx", 20.0);
    stockMap2.put("f", 15.0);
    stockMap2.put("aapl", 25.0);
    stockMap2.put("ff", 30.0);

    Map<String, Double> stockMap3 = new HashMap<>();
    stockMap3.put("aapl", 30.0);
    stockMap3.put("amzn", 70.0);


    s1 = new StrategyImpl.StrategyImplBuilder().portfolioName("college")
            .stockMap(stockMap1)
            .amount(1000.0)
            .commission(20.0)
            .startDate(LocalDate.of(2021, 1, 1))
            .frequency(40)
            .build();

    s2 = new StrategyImpl.StrategyImplBuilder().portfolioName("health")
            .stockMap(stockMap2)
            .amount(2000.0)
            .commission(25.0)
            .startDate(LocalDate.of(2019, 5, 24))
            .endDate(LocalDate.of(2022, 5, 24))
            .frequency(50)
            .build();

  }

  @Test
  public void testGetPortfolioName() {

    assertEquals(s1.getPortfolioName(), "college");
    assertEquals(s1.getAmount(), 1000.0, 0.001);
    assertEquals(s1.getCommission(), 20.0, 0.001);
    assertEquals(s1.getStartDate().toString(), "2021-01-01");
    assertEquals(s1.getFrequency(), 40, 0.001);
    //assertEquals(s1.getStockMap(),"aapl",40.0,0.001);
  }

  @Test
  public void testGetAmount() {
    assertEquals(s2.getAmount(), 2000.0, 0.001);

  }

  @Test
  public void testStartDate() {
    assertEquals(s2.getStartDate().toString(), "2019-05-24");
  }

  @Test
  public void testEndDate() {
    assertEquals(s1.getEndDate(), null);
    assertEquals(s2.getEndDate().toString(), "2022-05-24");
  }

  @Test
  public void testFrequency() {
    assertEquals(s1.getFrequency(), 40, 0.001);
    assertEquals(s2.getFrequency(), 50, 0.001);
  }

  @Test
  public void testIsOnGoing() {
    //assertEquals(s1.getIsOnGoing(),true);
    assertEquals(s2.getIsOnGoing(), false);
  }

  @Test
  public void testCommission() {
    assertEquals(s1.getCommission(), 20, 0.01);
    assertEquals(s2.getCommission(), 25, 0.01);
  }

  @Test
  public void testStockMap() {
    Map<String, Double> testStockMap = new HashMap<>();
    testStockMap.put("aapl", 40.0);
    testStockMap.put("nflx", 60.0);

    assertEquals(s1.getStockMap(), testStockMap);
  }


}
