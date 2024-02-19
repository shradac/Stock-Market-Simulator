package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test file for FlexiblePortfolioImplTest class.
 * It contains various tests on the FlexiblePortfolioImplTest class.
 */
public class FlexiblePortfolioImplTest {

  FlexiblePortfolio p1;
  FlexiblePortfolio p2;
  Stock s1;
  Stock s2;
  Stock s3;
  List<Stock> stockList1;
  List<Stock> stockList2;
  List<Stock> stockList3;


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
    stockList3 = new ArrayList<>();

  }

  @Test
  public void testPortfolio() {

    stockList1.add(s1);
    stockList1.add(s3);

    p1 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Investments")
            .stockList(stockList1).build();
    assertEquals(p1.getName(), "Investments");
    assertEquals(p1.getStockList(), stockList1);

    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Health")
            .stockList(stockList2).build();
    assertEquals(p2.getName(), "Health");
    assertEquals(p2.getStockList(), stockList2);


  }

  @Test
  public void getName() {
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("College").build();
    assertEquals(p2.getName(), "College");
  }

  @Test
  public void getStockList() {

    stockList2.add(s2);
    stockList2.add(s3);
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Tech")
            .stockList(stockList2).build();
    assertEquals(p2.getName(), "Tech");
    assertEquals(p2.getStockList(), stockList2);
  }

  @Test
  public void checkStockOnePortfolioOneStock() {

    stockList2.add(s2);
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Tech")
            .stockList(stockList2).build();
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
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Tech")
            .stockList(stockList2).build();
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
    p1 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Investments")
            .stockList(stockList1).build();
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
    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Tech")
            .stockList(stockList2).build();
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
  public void testAddStock() {

    p1 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Investments")
            .stockList(stockList1).build();
    p1.addStock(s1);
    stockList1.add(s1);
    p1.addStock(s2);
    stockList1.add(s2);
    assertEquals(p1.getStockList().size(), stockList1.size());
  }


  @Test
  public void testRemoveStock() {
    p1 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Investments")
            .stockList(stockList1).build();
    p1.removeStock(s1);
    p1.removeStock(s2);
    p1.removeStock(s1);
    stockList1.remove(s1);
    stockList1.remove(s2);
    stockList1.remove(s1);
    assertEquals(p1.getStockList(), stockList1);

  }

  @Test
  public void testAddRemoveStock() {

    p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Books")
            .stockList(stockList2).build();
    p2.addStock(s2);
    stockList2.add(s2);
    Stock s = new StockImpl.StockBuilder()
            .stockSymbol("aapl")
            .stockName("Apple Inc.")
            .quantity(3)
            .purchaseDate(LocalDate.of(2021, 4, 23))
            .purchasePrice(156.2)
            .build();
    p2.removeStock(s);
    stockList2.remove(0);
    assertEquals(p2.getStockList(), stockList2);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testNoPortfolioNameBuild() {
    p1 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().build();
  }
}

