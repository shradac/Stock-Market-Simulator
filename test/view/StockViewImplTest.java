package view;

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

import controller.StockController;
import controller.StockControllerImpl;
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

import static org.junit.Assert.assertEquals;

/**
 * Test file for application.
 * It contains various tests on the application after integrating Mode, View, Controller.
 */
public class StockViewImplTest {
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

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestExamine() {
    String inputString = "1\n1\nyes\ninput.xml\n3\n1\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));


    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestInvalidExamine() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n3\n4\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestSaveData() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestTotalValue() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestToalValueFutureDate() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2025-10-10\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestTotalValueInvalidDate() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n4\n1\n2022\n2022-10-10\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestAddPortfolioAfterInputFile() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestAddPortfolio() {
    String inputString = "1\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void inflexibleTestInputManuallyOneStock() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestInputManuallyTwoStocks() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\nGOOG\n100\n" +
            "2022-2-2\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestAdd2PortfoliosAfterInputFile() {
    String inputString = "1\n1\nyes\n" +
            "input.xml\n2\nSchool\n2\nIBM\n20\n2022-1-1\n2\nHealth\n1\nGOOG\n2022-1-1\n3\n1" +
            "\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestAdd2PortfoliosSameNameAfterInputFile() {
    String inputString = "1\n1\nyes\ninput.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nSchool\n1" +
            "\nIBM\n20\n2022-1-2\n6\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\nIBM\n5\n" +
            "2022-10-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestInputManuallyAddPortfolio() {
    String inputString = "1\n1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n2\n" +
            "Investments\n2\ngoog\n5\n2021-2-1\nAapl\n10\n2021-3-3\n3\n2\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestTotalValueWeekend() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
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

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestExamine() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n1\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));


    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestParseInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n10";
    Readable i = new StringReader(inputString);
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestInvalidExamine() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n4\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSaveData() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValue() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022-10-10\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestToalValueFutureDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2025-10-10\n2022-10-10\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueInvalidDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022\n2022-10-10\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestAddPortfolioAfterInputFile() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestAddPortfolio() {
    String inputString = "2\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestInputManuallyOneStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestInputManuallyTwoStocks() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n3\nGOOG\n100\n" +
            "2022-2-2\n4.5\n3\n1\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestAdd2PortfoliosAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nHealth\n1\n" +
            "GOOG\n2022-1-1\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestAdd2PortfoliosSameNameAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nSchool" +
            "\n1\nIBM\n20\n2022-1-2\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n4.5\nIBM\n5\n" +
            "2022-10-1\n4.5\n3\n1\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestInputManuallyAddPortfolio() {
    String inputString = "2\n1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n2\n" +
            "Investments\n2\ngoog\n5\n2021-2-1\n4.5\nAapl\n10\n2021-3-3\n4.5\n3\n2\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestTotalValueWeekend() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Data saved to  SavedPortfolios.xml file.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

    String totalValueOnFriday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-29\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSaturday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-30\n5\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));
    String totalValueOnSunday = out.toString();
    assertEquals(totalValueOnSaturday, totalValueOnSunday);
    assertEquals(totalValueOnFriday, totalValueOnSunday);

  }

  @Test
  public void flexibleTestInputFilesMultipleTimesSameFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n1\nyes\ninput2.xml\n3\n1\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 2.0\n" +
            "1. Stock purchase date: 2022-02-02\n" +
            "1. Stock purchase price: $5921.46\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuyStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestBuyStockOverFlowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n10000000000000000000000000000000000000000000000" +
            "00000000000000" +
            "00000000000000000000\n10\n2020-1-20\n10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestBuyStockQuantityZero() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestBuyStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n202-1-20\n2021-1-20\n4.7" +
            "\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestSellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n7\n1\nf\n6\n2022-01-01\n4.7\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestSellStockLowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nf\n20\n2022-10-10\n4.7\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestSellStockFutureDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n4.6\n7\n1\nibm\n2\n2028-10-10\n" +
            "2022-11-11\n4.7\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Cannot sell. Have less quantity\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestSellStockBeforeDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nibm\n2\n2018-10-10\n4.7\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestSellStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nibm\n2\n201-10-10\n" +
            "2018-10-10\n4.7\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Stock added to portfolio.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestBuySellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n7\n1\nibm\n10\n2022-10-10\n3\n7\n1\nnflx\n3\n2022-07-10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestBuyAndSellEverything() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n7\n1\nibm\n20\n2022-10-10\n3\n7\n1\nnflx\n10\n2022-07-10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasis() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n8\n1\n2022-01-01\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestNegativeCommission() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n-10\n10\n8\n1" +
            "\n2022-11-17\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Invalid commission fee. Please enter between $2 and $30.\n" +
            "\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $5.1\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCommissionGreaterThan30() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n-10\n10\n8\n1" +
            "\n2022-11-17\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Invalid commission fee. Please enter between $2 and $30.\n" +
            "\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $5.1\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCommissionGreaterThan0LessThan2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n1\n20.9\n8\n1" +
            "\n2022-11-17\n10";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Invalid commission fee. Please enter between $2 and $30.\n" +
            "\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $5.1\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasisBetweenBuyAndSell() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n8\n1\n2020-12-12\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasisBeforeBuying() {
    // COST BASIS IN BEFORE BUYING ANYTHING
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n8\n1\n2019-9-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueNormal() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4\n1\n2022-10-10\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueBeforeBuying() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4\n1\n2019-9-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueBetweenBuying2Stocks() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n4\n1\n2021-12-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestChartMonths() {
    String inputString = "2\n1\nyes\ninput2.xml\n9\n1\n2021-2-11\n2021-12-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Provide file location: \n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Investments from 2021-2-11 to 2021-12-11\n" +
            "\n" +
            "\n" +
            "Absolute Scale: * = $100.0\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestChartDays() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n10\n" +
            "2021-02-10\n5.5\n9\n1\n2021-2-11\n2021-12-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Investments from 2021-2-11 to 2021-12-11\n" +
            "\n" +
            "\n" +
            "Absolute Scale: * = $100.0\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestChartYears() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2016-1-20\n4.7\nnflx\n10\n" +
            "2018-02-10\n5.5\n9\n1\n2015-2-11\n2021-12-11\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Type 'Yes': Provide file location\n" +
            "Type 'No': Enter data manually\n" +
            "Enter user name: \n" +
            "Enter the number of portfolios: \n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Investments from 2015-2-11 to 2021-12-11\n" +
            "\n" +
            "\n" +
            "Absolute Scale: * = $100.0\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestStartWithOption2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n6\nnflx\n21\n" +
            "2022-1-1\n8\n1\n2022-11-17\n4\n1\n2022-11-17\n10\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new MockModel(log));

    assertEquals("Menu:\n" +
            "1. Work on Inflexible Portfolio\n" +
            "2. Work on Flexible Portfolio\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Portfolio successfully created.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Invalid input. Please try again.\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $5.1\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $0.0. Calculated using AlphaVantage API.\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Buy stock\n" +
            "7. Sell stock\n" +
            "8. Get the cost basis for a portfolio\n" +
            "9. Get the Performance chart for a portfolio\n" +
            "10. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
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
      chartObj1 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11", "Investments");
      chartObj2 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11", "Investments");
      chartObj3 = new ChartDrawImpl(chart1, 100, "2022-01-01", "2022-11-11", "Investments");
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
      // nothing

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