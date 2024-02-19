package model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test file for StockImpl class.
 * It contains various tests on the StockImpl class.
 */
public class StockImplTest {

  Stock s1;
  Stock s2;


  @Test
  public void testOneStock() {
    s1 = new StockImpl.StockBuilder().stockSymbol("goog")
            .stockName("Google Inc.")
            .purchaseDate(LocalDate.of(2022, 2, 2))
            .quantity(5)
            .purchasePrice(123.123)
            .build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-02-02");
    assertEquals(s1.getPurchasePrice(), 14803.65, 0.0001);
  }

  @Test
  public void AttributeBuilder() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .quantity(5)
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 479.15, 0.0001);

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(5)
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 479.15, 0.0001);

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(100)
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 100.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 9899.0, 0.0001);

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(100)
            .purchaseDate(LocalDate.of(2022, 10, 28))
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 100.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-28");
    assertEquals(s1.getPurchasePrice(), 9658.0, 0.0001);

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(100)
            .purchaseDate(LocalDate.of(2022, 10, 28))
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 100.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-28");
    assertEquals(s1.getPurchasePrice(), 9658.0, 0.0001);

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(100)
            .purchaseDate(LocalDate.of(2022, 10, 28))
            .purchasePrice(1000)
            .build();

    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 100.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-28");
    assertEquals(s1.getPurchasePrice(), 9658.0, 0.0001);


  }

  @Test
  public void getStockSymbol() {
    s1 = new StockImpl.StockBuilder().stockSymbol("goog").build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 0.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 0.0, 0.0001);
  }

  @Test
  public void getQuantity() {
    s1 = new StockImpl.StockBuilder().stockSymbol("Goog").quantity(5).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 494.15, 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getQuantityNegative() {
    s1 = new StockImpl.StockBuilder().stockSymbol("GOOG").quantity(-5).build();
    assertEquals(s1.getStockSymbol(), "goog");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 0.0, 0.0001);
  }

  @Test
  public void getStockName() {
    s1 = new StockImpl.StockBuilder().stockSymbol("GOOG").stockName("Google Inc.")
            .quantity(5).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 479.15, 0.0001);
  }

  @Test
  public void getPurchaseDateWeekday() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("GOOG")
            .quantity(5)
            .purchaseDate(LocalDate.of(2022, 10, 27)).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-27");
    assertEquals(s1.getPurchasePrice(), 463.0, 0.0001);
  }

  @Test
  public void getPurchaseDateSunday() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("GOOG")
            .quantity(5)
            .purchaseDate(LocalDate.of(2022, 10, 30)).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-30");
    assertEquals(s1.getPurchasePrice(), 482.9, 0.0001);
  }

  @Test
  public void getPurchaseDateSaturday() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("GOOG")
            .quantity(5)
            .purchaseDate(LocalDate.of(2022, 10, 29)).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-10-29");
    assertEquals(s1.getPurchasePrice(), 482.9, 0.0001);
  }

  @Test
  public void getPurchaseDate() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("GOOG")
            .purchaseDate(LocalDate.of(2022, 11, 11)).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 0.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-11-11");
    assertEquals(s1.getPurchasePrice(), 0.0, 0.0001);
  }

  @Test
  public void getPurchasePrice() {
    s1 = new StockImpl.StockBuilder().stockSymbol("GOOG")
            .quantity(5).purchasePrice(1000.234).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 499.15, 0.0001);
  }

  @Test
  public void getPurchasePriceNegative() {
    s1 = new StockImpl.StockBuilder().stockSymbol("GOOG").quantity(5)
            .purchasePrice(-1000.234).build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), LocalDate.now().toString());
    assertEquals(s1.getPurchasePrice(), 494.15, 0.0001);
  }

  @Test
  public void twoStocks() {

    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(5)
            .purchaseDate(LocalDate.of(2022, 2, 2))
            .purchasePrice(123.123)
            .build();
    assertEquals(s1.getStockSymbol(), "GOOG");
    assertEquals(s1.getQuantity(), 5.0, 0.0001);
    assertEquals(s1.getStockName(), "Google Inc.");
    assertEquals(s1.getPurchaseDate(), "2022-02-02");
    assertEquals(s1.getPurchasePrice(), 14803.65, 0.0001);

    s2 = new StockImpl.StockBuilder()
            .stockSymbol("aapl")
            .stockName("Apple Inc.")
            .quantity(5)
            .purchaseDate(LocalDate.of(2021, 3, 23))
            .purchasePrice(156.123)
            .build();
    assertEquals(s2.getStockSymbol(), "AAPL");
    assertEquals(s2.getQuantity(), 5.0, 0.0001);
    assertEquals(s2.getStockName(), "Apple Inc.");
    assertEquals(s2.getPurchaseDate(), "2021-03-23");
    assertEquals(s2.getPurchasePrice(), 612.7, 0.0001);

  }

  @Test(expected = IllegalArgumentException.class)
  public void NoStockSymbol() {
    s1 = new StockImpl.StockBuilder().build();
    assertEquals(s1.getStockSymbol(), null);
  }
}