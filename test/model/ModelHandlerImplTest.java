package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import static org.junit.Assert.assertEquals;

/**
 * Test file for ModelHandlerImpl class.
 * It contains various tests on the ModelHandlerImpl class.
 */
public class ModelHandlerImplTest {

  ModelHandler model;
  ModelHandler model2;

  @Before
  public void setUp() {
    model = new ModelHandlerImpl();
    model.initializeUserData("input.xml", "1");
    model2 = new ModelHandlerImpl();
    model2.initializeUserData("input.xml", "2");
  }

  @Test
  public void initializeUserData() {
    ModelHandler obj = new ModelHandlerImpl();
    User user = obj.initializeUserData("input.xml", "1");
    assertEquals(user.getUserName(), "Karthik");
  }

  @Test(expected = IllegalArgumentException.class)
  public void initializeUserDataFileNotFound() {
    ModelHandler obj = new ModelHandlerImpl();
    User user = obj.initializeUserData("input.x", "1");
    assertEquals(user.getUserName(), "Karthik");
  }

  @Test(expected = IllegalArgumentException.class)
  public void initializeUserDataInvalidFile() {
    ModelHandler obj = new ModelHandlerImpl();
    User user = obj.initializeUserData("SupportedListOfStocks.csv", "1");
    assertEquals(user.getUserName(), "Karthik");
  }

  @Test
  public void addStock() {
    Stock s = model.addStock("F", "20", "2022-2-2", null, null);
    assertEquals(s.getStockSymbol(), "F");
    assertEquals(s.getStockName(), "Ford Motor Company");
    assertEquals(s.getQuantity(), 20, 0.0001);
    assertEquals(s.getPurchasePrice(), 412.59999999999997, 0.0001);
    assertEquals(s.getPurchaseDate(), "2022-02-02");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addStockPurchaseDateFutureDate() {
    model.addStock("F", "20", "2023-2-2", null, null);
  }

  @Test
  public void checkPortfolioNameDuplicate() {
    assertEquals(model.checkPortfolioNameDuplicate("Investments"), true);
  }

  @Test
  public void checkPortfolioNameNotDuplicate() {
    assertEquals(model.checkPortfolioNameDuplicate("School"), false);
  }

  @Test
  public void getPortfolioList() {
    String[] portfolioList = new String[3];
    portfolioList[0] = "Investments";
    portfolioList[1] = "College";
    portfolioList[2] = "Health";
    List<Portfolio> pList = model.getPortfolioList();
    for (int i = 0; i < pList.size(); i++) {
      assertEquals(pList.get(i).getName(), portfolioList[i]);
    }
  }


  @Test
  public void saveData() throws ParserConfigurationException, TransformerException {
    boolean success = model.saveData("SavedPortfolios.xml");
    assertEquals(success, true);

  }

  @Test
  public void checkStockSupport() {
    assertEquals(model.checkStockSupport("GOOG"), true);
  }

  @Test
  public void checkStockSupportFail() {
    assertEquals(model.checkStockSupport("DASH"), false);
  }

  @Test
  public void inputManuallySetUserName() {
    assertEquals(model.inputManuallySetUserName("Karthik"), true);
  }

  @Test
  public void inputManuallySetUserName2() {
    assertEquals(model.inputManuallySetUserName("Shrada"), true);
  }


}