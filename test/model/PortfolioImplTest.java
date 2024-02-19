package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test file for PortfolioImpl class.
 * It contains various tests on the PortfolioImpl class.
 */
public class PortfolioImplTest {

  Portfolio p1;
  Portfolio p2;
  Stock s1;
  Stock s2;
  Stock s3;
  List<Stock> stockList1;
  List<Stock> stockList2;

  @Before
  public void setUp() {
    s1 = new StockImpl.StockBuilder()
            .stockSymbol("goog")
            .stockName("Google Inc.")
            .quantity(2)
            .purchaseDate(LocalDate.of(2022, 2, 2))
            .purchasePrice(123.123)
            .build();
    s2 = new StockImpl.StockBuilder()
            .stockSymbol("aapl")
            .stockName("Apple Inc.")
            .quantity(3)
            .purchaseDate(LocalDate.of(2021, 3, 23))
            .purchasePrice(156.2)
            .build();
    s3 = new StockImpl.StockBuilder()
            .stockSymbol("msft")
            .stockName("Microsoft Corporation")
            .quantity(4)
            .purchaseDate(LocalDate.of(2022, 3, 23))
            .purchasePrice(100.76)
            .build();
    stockList1 = new ArrayList<>();
    stockList2 = new ArrayList<>();

  }

  @Test
  public void testPortfolio() {

    p1 = new PortfolioImpl.PortfolioBuilder().name("Investments").build();
    assertEquals(p1.getName(), "Investments");
    assertEquals(p1.getStockList(), stockList1);

    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new PortfolioImpl.PortfolioBuilder().name("Health").stockList(stockList2).build();
    assertEquals(p2.getName(), "Health");
    assertEquals(p2.getStockList(), stockList2);

  }

  @Test
  public void getName() {
    p2 = new PortfolioImpl.PortfolioBuilder().name("College").build();
    assertEquals(p2.getName(), "College");
  }

  @Test
  public void getStockList() {

    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new PortfolioImpl.PortfolioBuilder().name("Tech").stockList(stockList2).build();
    assertEquals(p2.getName(), "Tech");
    assertEquals(p2.getStockList(), stockList2);
  }

  @Test
  public void checkStockOnePortfolioOneStock() {

    stockList2.add(s2);
    p2 = new PortfolioImpl.PortfolioBuilder().name("Tech").stockList(stockList2).build();
    assertEquals(p2.getName(), "Tech");
    assertEquals(p2.getStockList(), stockList2);
    assertEquals(p2.getStockList().get(0).getStockSymbol(), "AAPL");
    assertEquals(p2.getStockList().get(0).getStockName(), "Apple Inc.");
    assertEquals(p2.getStockList().get(0).getQuantity(), 3, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchasePrice(), 367.62, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchaseDate(), "2021-03-23");

  }

  @Test
  public void checkStockOnePortfolioTwoStock() {
    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new PortfolioImpl.PortfolioBuilder().name("Tech").stockList(stockList2).build();
    assertEquals(p2.getName(), "Tech");
    assertEquals(p2.getStockList(), stockList2);
    assertEquals(p2.getStockList().get(0).getStockSymbol(), "AAPL");
    assertEquals(p2.getStockList().get(0).getStockName(), "Apple Inc.");
    assertEquals(p2.getStockList().get(0).getQuantity(), 3, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchasePrice(), 367.62, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchaseDate(), "2021-03-23");

    assertEquals(p2.getStockList().get(1).getStockSymbol(), "MSFT");
    assertEquals(p2.getStockList().get(1).getStockName(), "Microsoft Corp.");
    assertEquals(p2.getStockList().get(1).getQuantity(), 4, 0.0001);
    assertEquals(p2.getStockList().get(1).getPurchasePrice(), 1197.96, 0.0001);
    assertEquals(p2.getStockList().get(1).getPurchaseDate(), "2022-03-23");

  }

  @Test
  public void checkStockTwoPortfolioTwoStocks() {
    stockList1.add(s1);
    stockList1.add(s3);
    p1 = new PortfolioImpl.PortfolioBuilder().name("Investments").stockList(stockList1).build();
    assertEquals(p1.getName(), "Investments");
    assertEquals(p1.getStockList(), stockList1);
    assertEquals(p1.getStockList().get(0).getStockSymbol(), "GOOG");
    assertEquals(p1.getStockList().get(0).getStockName(), "Google Inc.");
    assertEquals(p1.getStockList().get(0).getQuantity(), 2, 0.0001);
    assertEquals(p1.getStockList().get(0).getPurchasePrice(), 5921.46, 0.0001);
    assertEquals(p1.getStockList().get(0).getPurchaseDate(), "2022-02-02");

    assertEquals(p1.getStockList().get(1).getStockSymbol(), "MSFT");
    assertEquals(p1.getStockList().get(1).getStockName(), "Microsoft Corp.");
    assertEquals(p1.getStockList().get(1).getQuantity(), 4, 0.0001);
    assertEquals(p1.getStockList().get(1).getPurchasePrice(), 1197.96, 0.0001);
    assertEquals(p1.getStockList().get(1).getPurchaseDate(), "2022-03-23");


    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new PortfolioImpl.PortfolioBuilder().name("Tech").stockList(stockList2).build();
    assertEquals(p2.getName(), "Tech");
    assertEquals(p2.getStockList(), stockList2);
    assertEquals(p2.getStockList().get(0).getStockSymbol(), "AAPL");
    assertEquals(p2.getStockList().get(0).getStockName(), "Apple Inc.");
    assertEquals(p2.getStockList().get(0).getQuantity(), 3, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchasePrice(), 367.62, 0.0001);
    assertEquals(p2.getStockList().get(0).getPurchaseDate(), "2021-03-23");

    assertEquals(p2.getStockList().get(1).getStockSymbol(), "MSFT");
    assertEquals(p2.getStockList().get(1).getStockName(), "Microsoft Corp.");
    assertEquals(p2.getStockList().get(1).getQuantity(), 4, 0.0001);
    assertEquals(p2.getStockList().get(1).getPurchasePrice(), 1197.96, 0.0001);
    assertEquals(p2.getStockList().get(1).getPurchaseDate(), "2022-03-23");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoPortfolioNameBuild() {
    p1 = new PortfolioImpl.PortfolioBuilder().build();
  }
}
