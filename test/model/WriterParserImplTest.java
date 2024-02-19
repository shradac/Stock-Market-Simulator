package model;

import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;

import model.parser.ReaderParser;
import model.parser.ReaderParserImpl;
import model.parser.WriterParser;
import model.parser.WriterParserImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test file for WriterParserImpl class.
 * It contains various tests on the WriterParserImpl class.
 */
public class WriterParserImplTest {

  WriterParser writer;
  ReaderParser reader;
  User user1;
  User user2;

  @Before
  public void setUp() {
    writer = new WriterParserImpl();
    reader = new ReaderParserImpl();
    user1 = reader.readData("input.xml");
    user2 = reader.readData("input2.xml");
  }

  @Test
  public void writeDataInflexiblePortfolio() throws ParserConfigurationException {
    writer.writeData(user1, "SavedPortfolios.xml");
    User obj = reader.readData("SavedPortfolios.xml");
    assertEquals(obj.getUserName(), user1.getUserName());
    assertEquals(obj.getPortfolioList().size(), user1.getPortfolioList().size());
    assertEquals(obj.getPortfolioList().get(0).getName(),
            user1.getPortfolioList().get(0).getName());

    for (int i = 0; i < obj.getPortfolioList().size(); i++) {
      Portfolio p1 = obj.getPortfolioList().get(i);
      Portfolio p2 = user1.getPortfolioList().get(i);
      assertEquals(p1.getName(), p2.getName());
      for (int j = 0; j < p1.getStockList().size(); j++) {
        Stock s1 = p1.getStockList().get(j);
        Stock s2 = p2.getStockList().get(j);
        assertEquals(s1.getStockSymbol(), s2.getStockSymbol());
        assertEquals(s1.getStockName(), s2.getStockName());
        assertEquals(s1.getQuantity(), s2.getQuantity(), 0);
        assertEquals(s1.getPurchasePrice(), s2.getPurchasePrice(), 0);
        assertEquals(s1.getPurchaseDate(), s2.getPurchaseDate());
      }
    }
  }

  @Test
  public void writeDataFlexiblePortfolio() throws ParserConfigurationException {
    writer.writeData(user2, "SavedPortfolios.xml");
    User obj = reader.readData("SavedPortfolios.xml");
    assertEquals(obj.getUserName(), user2.getUserName());
    assertEquals(obj.getPortfolioList().size(), user2.getPortfolioList().size());
    assertEquals(obj.getPortfolioList().get(0).getName(),
            user2.getPortfolioList().get(0).getName());

    for (int i = 0; i < obj.getPortfolioList().size(); i++) {
      Portfolio p1 = obj.getPortfolioList().get(i);
      Portfolio p2 = user2.getPortfolioList().get(i);
      assertEquals(p1.getName(), p2.getName());
      for (int j = 0; j < p1.getStockList().size(); j++) {
        Stock s1 = p1.getStockList().get(j);
        Stock s2 = p2.getStockList().get(j);
        assertEquals(s1.getStockSymbol(), s2.getStockSymbol());
        assertEquals(s1.getStockName(), s2.getStockName());
        assertEquals(s1.getQuantity(), s2.getQuantity(), 0);
        assertEquals(s1.getPurchasePrice(), s2.getPurchasePrice(), 0);
        assertEquals(s1.getPurchaseDate(), s2.getPurchaseDate());
      }
    }
    for (int i = 0; i < obj.getTransactionList().size(); i++) {
      Transaction t1 = obj.getTransactionList().get(i);
      Transaction t2 = user2.getTransactionList().get(i);
      assertEquals(t1.getPortfolioName(), t2.getPortfolioName());
      assertEquals(t1.getStockSymbol(), t2.getStockSymbol());
      assertEquals(t1.getType(), t2.getType());
      assertEquals(t1.getQuantity(), t2.getQuantity(), 0);
      assertEquals(t1.getDate(), t2.getDate());
      assertEquals(t1.getCommission(), t2.getCommission(), 0);
    }
  }
}