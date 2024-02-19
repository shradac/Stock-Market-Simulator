package model;

import org.junit.Before;
import org.junit.Test;

import model.parser.ReaderParser;
import model.parser.ReaderParserImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test file for ReaderParserImpl class.
 * It contains various tests on the ReaderParserImpl class.
 */
public class ReaderParserImplTest {

  ReaderParser reader;

  @Before
  public void setUp() {
    reader = new ReaderParserImpl();
  }

  @Test
  public void readData() {
    User user = reader.readData("input.xml");
    assertEquals(user.getPortfolioList().size(), 3);
    String[] portfolioList = new String[3];
    portfolioList[0] = "Investments";
    portfolioList[1] = "College";
    portfolioList[2] = "Health";
    String[] stockList = new String[5];
    stockList[0] = "GOOG";
    stockList[1] = "AAPL";
    stockList[2] = "IBM";
    stockList[3] = "TSLA";
    stockList[4] = "NFLX";

    int k = 0;
    for (int i = 0; i < user.getPortfolioList().size(); i++) {
      assertEquals(user.getPortfolioList().get(i).getName(), portfolioList[i]);
      for (int j = 0; j < user.getPortfolioList().get(i).getStockList().size(); j++) {
        assertEquals(user.getPortfolioList().get(i).getStockList().get(j).getStockSymbol(),
                stockList[k]);
        k++;
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void FileNotFound() {
    User user = reader.readData("input.x");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidFile() {
    User user = reader.readData("SupportedListOfStocks.csv");
  }
}