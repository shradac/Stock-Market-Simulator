package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test file for UserImpl class.
 * It contains various tests on the UserImpl class.
 */
public class UserImplTest {
  User u1;
  User u2;
  Portfolio p1;
  Portfolio p2;
  Portfolio p3;
  Stock s1;
  Stock s2;
  Stock s3;
  List<Stock> stockList1;
  List<Stock> stockList2;
  List<Stock> stockList3;
  List<Portfolio> portfolioList1;
  List<Portfolio> portfolioList2;


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

    stockList1.add(s1);
    stockList1.add(s2);

    stockList2.add(s2);
    stockList2.add(s3);

    stockList3.add(s1);
    stockList3.add(s2);
    stockList3.add(s3);
    p1 = new PortfolioImpl.PortfolioBuilder().name("Investments").stockList(stockList1).build();
    p2 = new PortfolioImpl.PortfolioBuilder().name("Health").stockList(stockList2).build();
    p3 = new PortfolioImpl.PortfolioBuilder().name("College").stockList(stockList3).build();

    portfolioList1 = new ArrayList<>();
    portfolioList2 = new ArrayList<>();

  }


  @Test
  public void testGetUserName() {
    u1 = new UserImpl.UserBuilder().userName("Karthik").build();
    assertEquals(u1.getUserName(), "Karthik");
  }

  @Test
  public void testOnePortfolioTwoStocks() {

    portfolioList1.add(p1);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1)
            .portfolioType("inflexible").build();
    assertEquals(u1.getUserName(), "Karthik");
    assertEquals(u1.getPortfolioList().get(0).getName(), "Investments");
    assertEquals(u1.getPortfolioList().get(0).getStockList(), stockList1);

    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockSymbol(), "GOOG");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockName(), "Google Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getQuantity(), 2, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchaseDate(),
            "2022-02-02");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchasePrice(),
            5921.46, 0.0001);

    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockSymbol(), "AAPL");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockName(), "Apple Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getQuantity(), 3, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchaseDate(),
            "2021-03-23");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchasePrice(),
            367.62, 0.0001);
  }

  @Test
  public void testTwoPortfolioTwoStocksEach() {

    portfolioList1.add(p1);
    portfolioList1.add(p2);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1).build();
    assertEquals(u1.getUserName(), "Karthik");
    assertEquals(u1.getPortfolioList().get(0).getName(), "Investments");
    assertEquals(u1.getPortfolioList().get(0).getStockList(), stockList1);

    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockSymbol(), "GOOG");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockName(), "Google Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getQuantity(), 2, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchaseDate(),
            "2022-02-02");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchasePrice(),
            5921.46, 0.0001);

    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockSymbol(), "AAPL");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockName(), "Apple Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getQuantity(), 3, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchaseDate(),
            "2021-03-23");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchasePrice(),
            367.62, 0.0001);

    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getStockSymbol(), "AAPL");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getStockName(), "Apple Inc.");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getQuantity(), 3, 0.0001);
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getPurchaseDate(),
            "2021-03-23");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getPurchasePrice(),
            367.62, 0.0001);

    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getStockSymbol(), "MSFT");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getStockName(),
            "Microsoft Corp.");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getQuantity(), 4, 0.0001);
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getPurchaseDate(),
            "2022-03-23");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getPurchasePrice(),
            1197.96, 0.0001);
  }

  @Test
  public void addPortfolio() {

    portfolioList1.add(p1);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1).build();
    assertEquals(u1.getUserName(), "Karthik");
    assertEquals(u1.getPortfolioList().get(0).getName(), "Investments");
    assertEquals(u1.getPortfolioList().get(0).getStockList(), stockList1);


    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockSymbol(), "GOOG");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getStockName(), "Google Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getQuantity(), 2, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchaseDate(),
            "2022-02-02");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(0).getPurchasePrice(),
            5921.46, 0.0001);

    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockSymbol(), "AAPL");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getStockName(), "Apple Inc.");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getQuantity(), 3, 0.0001);
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchaseDate(),
            "2021-03-23");
    assertEquals(u1.getPortfolioList().get(0).getStockList().get(1).getPurchasePrice(),
            367.62, 0.0001);

    u1.addPortfolio(p2);

    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getStockSymbol(), "AAPL");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getStockName(), "Apple Inc.");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getQuantity(), 3, 0.0001);
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getPurchaseDate(),
            "2021-03-23");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(0).getPurchasePrice(),
            367.62, 0.0001);

    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getStockSymbol(), "MSFT");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getStockName(),
            "Microsoft Corp.");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getQuantity(), 4, 0.0001);
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getPurchaseDate(),
            "2022-03-23");
    assertEquals(u1.getPortfolioList().get(1).getStockList().get(1).getPurchasePrice(),
            1197.96, 0.0001);


  }

  @Test(expected = IllegalArgumentException.class)
  public void addPortfolioNull() {

    p3 = new PortfolioImpl.PortfolioBuilder().build();
    portfolioList1.add(p3);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1).build();
  }


  @Test
  public void totalValue() {

    portfolioList1.add(p1);
    portfolioList1.add(p2);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1)
            .portfolioType("inflexible").build();
    assertEquals(u1.totalValue("Investments", LocalDate.of(2022, 10, 10)), 618.679992, 0.0001);
    assertEquals(u1.totalValue("Health",
            LocalDate.of(2022, 10, 10)), 1338.259994, 0.0001);
    assertEquals(u1.totalValue("ABC",
            LocalDate.of(2022, 10, 10)), 0, 0.0001);
  }

  @Test
  public void totalValueFutureDate() {

    portfolioList1.add(p1);
    portfolioList1.add(p2);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1)
            .portfolioType("inflexible").build();
    assertEquals(u1.totalValue("Investments",
            LocalDate.of(2023, 10, 10)), 0, 0.0001);
  }

  @Test
  public void totalValueOldDate() {
    portfolioList1.add(p1);
    portfolioList1.add(p2);
    u1 = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1)
            .portfolioType("inflexible").build();
    assertEquals(u1.totalValue("Investments",
            LocalDate.of(1900, 10, 10)), 0, 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noUserName() {
    portfolioList1.add(p1);
    u1 = new UserImpl.UserBuilder().portfolioList(portfolioList1).build();
  }

  @Test(expected = NullPointerException.class)
  public void UserName() {
    assertEquals(u1.getUserName(), null);
    assertEquals(u1.getPortfolioList(), new ArrayList<>());
  }

}