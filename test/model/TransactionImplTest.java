package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


/**
 * Test file for TransactionImpl class.
 * It contains various tests on the TransactionImpl class.
 */
public class TransactionImplTest {

  private Transaction t1;
  private Transaction t2;

  @Before
  public void setUp() {
    t1 = new TransactionImpl.TransactionBuilder().portfolioName("Health").type("buy")
            .stockSymbol("AMZN").quantity(10)
            .date(LocalDate.of(2021, 3, 23)).build();

    t2 = new TransactionImpl.TransactionBuilder().portfolioName("Vehicle").type("sell")
            .stockSymbol("GOOG").quantity(31)
            .date(LocalDate.of(2020, 8, 13)).build();
  }

  @Test
  public void testTransaction1() {

    assertEquals(t1.getPortfolioName(), "Health");
    assertEquals(t1.getStockSymbol(), "AMZN");
    assertEquals(t1.getQuantity(), 10, 0.0001);
    assertEquals(t1.getDate(), LocalDate.parse("2021-03-23"));
    assertEquals(t1.getPrice(), 31375.0, 0.0001);
    assertEquals(t1.getType(), "buy");


  }

  @Test
  public void testTransaction2() {

    assertEquals(t2.getPortfolioName(), "Vehicle");
    assertEquals(t2.getStockSymbol(), "GOOG");
    assertEquals(t2.getQuantity(), 31, 0.0001);
    assertEquals(t2.getDate(), LocalDate.parse("2020-08-13"));
    assertEquals(t2.getPrice(), 47071.950000000004, 0.0001);
    assertEquals(t2.getType(), "sell");
  }

}
