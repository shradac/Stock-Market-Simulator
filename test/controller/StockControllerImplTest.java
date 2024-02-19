package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.FlexiblePortfolioImpl;
import model.ModelHandler;
import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;
import model.Transaction;
import model.TransactionImpl;
import model.User;
import model.UserImpl;
import view.ChartDrawImpl;
import view.Draw;
import view.StockView;
import view.StockViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test file for controller.
 * Test by isolating the controller by creating a mock model.
 */
public class StockControllerImplTest {
  OutputStream out;
  InputStream in;
  StockView view;
  StringBuilder log;
  StockController controller;

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();

  }

  @Test
  public void inflexibleTestParseInputFile() {
    String inputString = "1\n1\nyes\ninput.xml\n6\n";
    Readable i = new StringReader(inputString);
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestExamine() {
    String inputString = "1\n1\nyes\ninput.xml\n3\n1\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestInvalidExamine() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n3\n4\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestSaveData() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestTotalValue() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestToalValueFutureDate() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2025-10-10\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestTotalValueInvalidDate() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2022\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestAddPortfolioAfterInputFile() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestAddPortfolio() {
    String inputString = "1\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void inflexibleTestInputManuallyOneStock() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());
  }

  @Test
  public void inflexibleTestInputManuallyTwoStocks() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\nGOOG\n100\n" +
            "2022-2-2\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to check if given stock symbol is supported: GOOG\n" +
            "Add stock called with Stock details: GOOG 100 2022-2-2\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestAdd2PortfoliosAfterInputFile() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n2\nSchool\n2\nIBM\n20\n2022-1-1\n2\nHealth\n1\nGOOG\n2022-1-1\n3\n1" +
            "\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to check if given stock symbol is supported: 2\n" +
            "Add stock called with Stock details: 2 1 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestAdd2PortfoliosSameNameAfterInputFile() {
    String inputString = "1\n1\nyes\ninput.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nSchool\n1" +
            "\nIBM\n20\n2022-1-2\n6\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-2\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\nIBM\n5\n" +
            "2022-10-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 5 2022-10-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestInputManuallyAddPortfolio() {
    String inputString = "1\n1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n2\n" +
            "Investments\n2\ngoog\n5\n2021-2-1\nAapl\n10\n2021-3-3\n3\n2\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to check if given Portfolio name already exists: Investments\n" +
            "Asked to check if given stock symbol is supported: goog\n" +
            "Add stock called with Stock details: goog 5 2021-2-1\n" +
            "Asked to check if given stock symbol is supported: Aapl\n" +
            "Add stock called with Stock details: Aapl 10 2021-3-3\n" +
            "Asked to add stock list to the given portfolio: Investments\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());


  }

  @Test
  public void inflexibleTestTotalValueWeekend() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to add stock list to the given portfolio: School\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-28\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

    String totalValueOnFriday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1" +
            "\n2022-10-29\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSaturday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1" +
            "\n2022-10-30\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSunday = out.toString();
    assertEquals(totalValueOnSaturday, totalValueOnSunday);
    assertEquals(totalValueOnFriday, totalValueOnSunday);

  }

  @Test
  public void inflexibleTestInputFilesMultipleTimesSameFile() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n1\nyes\n" +
            "input.xml\n3\n1\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Passing Location to initializeUserData: input.xml\n" +
            "setPortfolioType method called with data: inflexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: inflexible\n", log.toString());

  }

  @Test
  public void flexibleTestExamine() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n1\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestParseInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n11";
    Readable i = new StringReader(inputString);
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestInvalidExamine() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n4\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestSaveData() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestTotalValue() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestToalValueFutureDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2025-10-10\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestTotalValueInvalidDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-10\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestAddPortfolioAfterInputFile() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestAddPortfolio() {
    String inputString = "2\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestInputManuallyOneStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestInputManuallyTwoStocks() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n3\nGOOG\n100\n" +
            "2022-2-2\n4.5\n3\n1\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to check if given stock symbol is supported: GOOG\n" +
            "Add stock called with Stock details: GOOG 100 2022-2-2\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestAdd2PortfoliosAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nHealth\n1\n" +
            "GOOG\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestAdd2PortfoliosSameNameAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nSchool" +
            "\n1\nIBM\n20\n2022-1-2\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n4.5\nIBM\n5\n" +
            "2022-10-1\n4.5\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 5 2022-10-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestInputManuallyAddPortfolio() {
    String inputString = "2\n1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n2\n" +
            "Investments\n2\ngoog\n5\n2021-2-1\n4.5\nAapl\n10\n2021-3-3\n4.5\n3\n2\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: Investments\n" +
            "Asked to check if given stock symbol is supported: goog\n" +
            "Add stock called with Stock details: goog 5 2021-2-1\n" +
            "Asked to check if given stock symbol is supported: Aapl\n" +
            "Add stock called with Stock details: Aapl 10 2021-3-3\n" +
            "addStockListToFlexiblePortfolio method called with data: Investments\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestTotalValueWeekend() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: School\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2022-1-1\n" +
            "addStockListToFlexiblePortfolio method called with data: School\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-10-28\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to save the data.\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


    String totalValueOnFriday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-29\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSaturday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-30\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSunday = out.toString();
    assertEquals(totalValueOnSaturday, totalValueOnSunday);
    assertEquals(totalValueOnFriday, totalValueOnSunday);

  }

  @Test
  public void flexibleTestInputFilesMultipleTimesSameFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n1\nyes\ninput2.xml\n3\n1\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());


  }

  @Test
  public void flexibleTestBuyStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 12, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestBuyStockOverFlowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n10000000000000000000000000000000000000000000000" +
            "00000000000000" +
            "00000000000000000000\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 10, 2020-1-20, 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestBuyStockQuantityZero() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 10, 2020-1-20, 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestBuyStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n202-1-20\n2021-1-20\n4.7" +
            "\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2021-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 10, 2020-1-20, 1\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestSellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n7\n1\nf\n6\n2022-01-01\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 12, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestSellStockLowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nf\n20\n2022-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 20, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestSellStockFutureDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n4.6\n7\n1\nibm\n2\n2028-10-10\n" +
            "2022-11-11\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 20, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: ibm\n" +
            "checkSellQuantity method called with data: ibm, 2, 2022-11-11, 1setPortfolioType " +
            "method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestSellStockBeforeDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nibm\n2\n2018-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 20, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: 2018-10-10\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestSellStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nibm\n2\n201-10-10\n" +
            "2018-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to check if given stock symbol is supported: f\n" +
            "addStockToPortfolio method called with data: f, 20, 2019-05-24, 1\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: 201-10-10\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestBuySellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n7\n1\nibm\n10\n2022-10-10\n3\n7\n1\nnflx\n3\n2022-07-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestBuyAndSellEverything() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n7\n1\nibm\n20\n2022-10-10\n3\n7\n1\nnflx\n10\n2022-07-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestCostBasis() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2022-01-01\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestNegativeCommission() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n-10\n10\n8\n1" +
            "\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: Shrada\n" +
            "Asked to check if given stock symbol is supported: Health\n" +
            "Add stock called with Stock details: Health 1 2019-1-20\n" +
            "addStockListToFlexiblePortfolio method called with data: Shrada\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "getCostBasis method called with data: 1, 2022-11-17\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestCommissionGreaterThan30() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n-10\n10\n8\n1" +
            "\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: Shrada\n" +
            "Asked to check if given stock symbol is supported: Health\n" +
            "Add stock called with Stock details: Health 1 2019-1-20\n" +
            "addStockListToFlexiblePortfolio method called with data: Shrada\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "getCostBasis method called with data: 1, 2022-11-17\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestCommissionGreaterThan0LessThan2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n1\n20.9\n8\n1" +
            "\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: Shrada\n" +
            "Asked to check if given stock symbol is supported: Health\n" +
            "Add stock called with Stock details: Health 1 2019-1-20\n" +
            "addStockListToFlexiblePortfolio method called with data: Shrada\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "getCostBasis method called with data: 1, 2022-11-17\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestCostBasisBetweenBuyAndSell() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2020-12-12\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestCostBasisBeforeBuying() {
    // COST BASIS IN BEFORE BUYING ANYTHING
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2019-9-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestTotalValueNormal() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n1\n2022-10-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestTotalValueBeforeBuying() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n1\n2019-9-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestTotalValueBetweenBuying2Stocks() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n1\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestChartMonths() {
    String inputString = "2\n1\nyes\ninput2.xml\n9\n1\n2021-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Passing Location to initializeUserData: input2.xml\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 2021-2-11, 2021-12-11, Investments\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestChartDays() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n5.5\n9\n1\n2021-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2020-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2021-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 2021-2-11, 2021-12-11, Investments\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());
  }

  @Test
  public void flexibleTestChartYears() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2016-1-20\n4.7\nnflx\n11\n" +
            "2018-02-10\n5.5\n9\n1\n2015-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Providing userName: Karthik\n" +
            "Asked to check if given Portfolio name already exists: Insurance\n" +
            "Asked to check if given stock symbol is supported: IBM\n" +
            "Add stock called with Stock details: IBM 20 2016-1-20\n" +
            "Asked to check if given stock symbol is supported: nflx\n" +
            "Add stock called with Stock details: nflx 10 2018-02-10\n" +
            "addStockListToFlexiblePortfolio method called with data: Insurance\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "method called with data: 2015-2-11, 2021-12-11, Investments\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  @Test
  public void flexibleTestStartWithOption2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n6\nnflx\n21\n" +
            "2022-1-1\n8\n1\n2022-11-17\n4\n1\n2022-11-17\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    assertEquals("setPortfolioType method called with data: flexible\n" +
            "Asked to check if given Portfolio name already exists: Shrada\n" +
            "Asked to check if given stock symbol is supported: Health\n" +
            "Add stock called with Stock details: Health 1 2019-1-20\n" +
            "addStockListToFlexiblePortfolio method called with data: Shrada\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "getCostBasis method called with data: 1, 2022-11-17\n" +
            "setPortfolioType method called with data: flexible\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked for PortfolioList.\n" +
            "Asked to get total value of a portfolio: Investments at 2022-11-17\n" +
            "setPortfolioType method called with data: flexible\n", log.toString());

  }

  class MockModel implements ModelHandler {

    List<Stock> stockList = new ArrayList<>();
    List<Portfolio> portfolioList1 = new ArrayList<>();
    List<Portfolio> portfolioList2 = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();
    User user;
    Stock s1;
    Portfolio p1;
    FlexiblePortfolio p2;
    Map<String, String> chart1;
    Draw chartObj1;
    Draw chartObj2;
    Draw chartObj3;
    private StringBuilder log;

    public MockModel(StringBuilder log) {
      this.log = log;
      s1 = new StockImpl.StockBuilder()
              .stockSymbol("goog")
              .stockName("Google Inc.")
              .quantity(2)
              .purchaseDate(LocalDate.of(2022, 2, 2))
              .purchasePrice(123.123)
              .build();
      stockList.add(s1);
      p1 = new PortfolioImpl.PortfolioBuilder().name("Investments").stockList(stockList).build();
      portfolioList1.add(p1);
      user = new UserImpl.UserBuilder().userName("Karthik").portfolioList(portfolioList1).build();

      p2 = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder().name("Investments")
              .stockList(stockList).build();
      portfolioList2.add(p2);
      user = new UserImpl.UserBuilder().userName("Shrada").portfolioList(portfolioList2).build();
      Transaction t = new TransactionImpl.TransactionBuilder()
              .stockSymbol("IBM")
              .date(LocalDate.of(2022, 2, 2))
              .portfolioName("Investments")
              .type("buy")
              .quantity(5)
              .build();
      transactionList.add(t);
      chart1 = new HashMap<>();
      chartObj1 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11",
              "Investments");
      chartObj2 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11",
              "Investments");
      chartObj3 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11",
              "Investments");
    }

    @Override
    public User initializeUserData(String location, String portfolioMenuOption) {
      log.append("Passing Location to initializeUserData: " + location + "\n");
      return user;
    }

    @Override
    public Stock addStock(String stockSymbol, String stringQuantity,
                          String stringPurchaseDate, String choice, String stringCommission) {
      log.append("Add stock called with Stock details: " + stockSymbol + " " + stringQuantity + " "
              + stringPurchaseDate + "\n");
      return s1;
    }

    @Override
    public boolean checkPortfolioNameDuplicate(String portfolioName) {
      log.append("Asked to check if given Portfolio name already exists: " + portfolioName + "\n");
      return false;
    }

    @Override
    public Portfolio addStockListToPortfolio(String portfolioName) {
      log.append("Asked to add stock list to the given portfolio: " + portfolioName + "\n");
      return p1;
    }

    @Override
    public List<Portfolio> getPortfolioList() {
      log.append("Asked for PortfolioList.\n");
      return portfolioList1;
    }

    @Override
    public double getTotalValue(String portfolioName, String stringDate) {
      log.append("Asked to get total value of a portfolio: " + portfolioName + " at " +
              stringDate + "\n");
      return 0;
    }

    @Override
    public boolean saveData(String fileSaveLocation) {
      log.append("Asked to save the data.\n");
      return true;
    }

    @Override
    public boolean checkStockSupport(String stockSymbol) {
      log.append("Asked to check if given stock symbol is supported: " + stockSymbol + "\n");
      return true;
    }

    @Override
    public boolean inputManuallySetUserName(String userName) {
      log.append("Providing userName: " + userName + "\n");
      return true;
    }

    @Override
    public FlexiblePortfolio addStockToPortfolio(String stockSymbol, String quantity,
                                                 String stringPurchaseDate, String portfolioName,
                                                 String stringCommission) {
      log.append("addStockToPortfolio method called with data: " + stockSymbol + ", " + quantity
              + ", " + stringPurchaseDate + ", " + portfolioName + "\n");
      return p2;
    }

    @Override
    public void addStockListToFlexiblePortfolio(String portfolioName) {
      log.append("addStockListToFlexiblePortfolio method called with data: "
              + portfolioName + "\n");
    }

    @Override
    public boolean checkSellQuantity(String stockSymbol, String stringQuantity,
                                     String stringPurchaseDate, String choice) {
      log.append("checkSellQuantity method called with data: " + stockSymbol + ", " + stringQuantity
              + ", " + stringPurchaseDate + ", " + choice);
      return true;
    }

    @Override
    public void sellStockFromPortfolio(String stockSymbol, String quantity,
                                       String stringPurchaseDate, String choice,
                                       String stringCommission) {
      log.append("checkSellQuantity method called with data: " + stockSymbol + ", " + quantity
              + ", " + stringPurchaseDate + ", " + choice + "\n");
    }

    @Override
    public void setPortfolioType(String type) {
      log.append("setPortfolioType method called with data: " + type + "\n");
    }

    @Override
    public Double getCostBasis(String choice, String stringDate) {
      log.append("getCostBasis method called with data: " + choice + ", " + stringDate + "\n");
      return 5.1;
    }

    @Override
    public Portfolio examine(String choice) {
      log.append("method called with data: " + choice + "\n");
      return p2;
    }

    @Override
    public Draw getChart(String start, String end, String portfolioName, int length) {
      log.append("method called with data: " + start + ", " + end + ", " + portfolioName + "\n");
      return new ChartDrawImpl(chart1, 100, start, end, portfolioName);
    }

    @Override
    public void addStrategy(String portfolioName, Map<String, Double> stockMap, String stringAmount,
                            String stringCommission, String startDate, String endDate,
                            String stringFrequency) {
      // do nothing
    }

    @Override
    public void addStockToCache(String stockSymbol) {
      // do nothing
    }

    @Override
    public LocalDate stringToLocalDate(String stringDate) {
      return null;
    }

    @Override
    public List<Stock> getStockListToAddPortfolio() {
      return null;
    }

    @Override
    public Map<String, Double> getStockMap() {
      return null;
    }

  }

}