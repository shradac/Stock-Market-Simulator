import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import controller.StockController;
import controller.StockControllerImpl;
import model.ModelHandlerImpl;
import view.StockView;
import view.StockViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test file for application.
 * It contains various tests on the application after integrating Mode, View, Controller.
 */

public class RunTest {
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
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 200.0\n" +
            "1. Stock purchase date: 2022-10-08\n" +
            "1. Stock purchase price: $19914.0\n" +
            "\n" +
            "2. Stock symbol: AAPL\n" +
            "2. Stock name: Apple Inc.\n" +
            "2. Stock quantity: 201.0\n" +
            "2. Stock purchase date: 2022-10-08\n" +
            "2. Stock purchase price: $28158.09\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 200.0\n" +
            "1. Stock purchase date: 2022-10-08\n" +
            "1. Stock purchase price: $19914.0\n" +
            "\n" +
            "2. Stock symbol: AAPL\n" +
            "2. Stock name: Apple Inc.\n" +
            "2. Stock quantity: 201.0\n" +
            "2. Stock purchase date: 2022-10-08\n" +
            "2. Stock purchase price: $28158.09\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $47966.42. Calculated using AlphaVantage API.\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $47966.42. Calculated using AlphaVantage API.\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $47966.42. Calculated using AlphaVantage API.\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "4 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 200.0\n" +
            "1. Stock purchase date: 2022-10-08\n" +
            "1. Stock purchase price: $19914.0\n" +
            "\n" +
            "2. Stock symbol: AAPL\n" +
            "2. Stock name: Apple Inc.\n" +
            "2. Stock quantity: 201.0\n" +
            "2. Stock purchase date: 2022-10-08\n" +
            "2. Stock purchase price: $28158.09\n" +
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
    String inputString = "1\n2\nSchool\n1\nIBM\n20\n2022-1-1\n4\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "1 School\n" +
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
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestInputManuallyOneStock() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2673.2\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2673.2\n" +
            "\n" +
            "2. Stock symbol: GOOG\n" +
            "2. Stock name: Google Inc.\n" +
            "2. Stock quantity: 100.0\n" +
            "2. Stock purchase date: 2022-02-02\n" +
            "2. Stock purchase price: $296073.0\n" +
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
    String inputString = "1\n1\nyes\ninput.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nHealth\n1" +
            "\nGOOG\n2022-1-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "Given portfolio name already exists. Portfolio name should be unique. " +
            "Please try again.\n" +
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
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "4 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 200.0\n" +
            "1. Stock purchase date: 2022-10-08\n" +
            "1. Stock purchase price: $19914.0\n" +
            "\n" +
            "2. Stock symbol: AAPL\n" +
            "2. Stock name: Apple Inc.\n" +
            "2. Stock quantity: 201.0\n" +
            "2. Stock purchase date: 2022-10-08\n" +
            "2. Stock purchase price: $28158.09\n" +
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
    controller.connect(new ModelHandlerImpl());
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
            "Given portfolio name already exists. Portfolio name should be unique. " +
            "Please try again.\n" +
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
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void inflexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\nIBM\n5\n" +
            "2022-10-1\n3\n1\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 25.0\n" +
            "1. Stock purchase date: 2022-10-01\n" +
            "1. Stock purchase price: $2970.25\n" +
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
  public void inflexibleTestTotalValueWeekend() {
    String inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2673.2\n" +
            "\n" +
            "Menu: \n" +
            "1. Enter data as a file: Yes/No\n" +
            "2. Add a portfolio\n" +
            "3. Examine a portfolio\n" +
            "4. Get the total value of a portfolio\n" +
            "5. Save Data\n" +
            "6. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $2770.2. Calculated using AlphaVantage API.\n" +
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
    controller.connect(new ModelHandlerImpl());
    String totalValueOnSaturday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "1\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n4\n1" +
            "\n2022-10-30\n5\n6";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
    controller.connect(new ModelHandlerImpl());
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
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: GOOG\n" +
            "1. Stock name: Google Inc.\n" +
            "1. Stock quantity: 200.0\n" +
            "1. Stock purchase date: 2022-10-08\n" +
            "1. Stock purchase price: $19914.0\n" +
            "\n" +
            "2. Stock symbol: AAPL\n" +
            "2. Stock name: Apple Inc.\n" +
            "2. Stock quantity: 201.0\n" +
            "2. Stock purchase date: 2022-10-08\n" +
            "2. Stock purchase price: $28158.09\n" +
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
    String inputString = "2\n1\nyes\ninput2.xml\n3\n1\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: AAPL\n" +
            "1. Stock name: Apple Inc.\n" +
            "1. Stock quantity: 1.6482610845557937\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $300.0\n" +
            "\n" +
            "2. Stock symbol: AMZN\n" +
            "2. Stock name: Amazon.Com\n" +
            "2. Stock quantity: 0.20539363690512866\n" +
            "2. Stock purchase date: 2022-01-01\n" +
            "2. Stock purchase price: $700.0\n" +
            "\n" +
            "3. Stock symbol: AAPL\n" +
            "3. Stock name: Apple Inc.\n" +
            "3. Stock quantity: 1.7164435290078957\n" +
            "3. Stock purchase date: 2022-01-31\n" +
            "3. Stock purchase price: $300.0\n" +
            "\n" +
            "4. Stock symbol: AMZN\n" +
            "4. Stock name: Amazon.Com\n" +
            "4. Stock quantity: 0.23399866955042173\n" +
            "4. Stock purchase date: 2022-01-31\n" +
            "4. Stock purchase price: $700.0\n" +
            "\n" +
            "5. Stock symbol: AAPL\n" +
            "5. Stock name: Apple Inc.\n" +
            "5. Stock quantity: 1.8011527377521614\n" +
            "5. Stock purchase date: 2022-03-02\n" +
            "5. Stock purchase price: $300.0\n" +
            "\n" +
            "6. Stock symbol: AMZN\n" +
            "6. Stock name: Amazon.Com\n" +
            "6. Stock quantity: 0.23018365367225135\n" +
            "6. Stock purchase date: 2022-03-02\n" +
            "6. Stock purchase price: $700.0\n" +
            "\n" +
            "7. Stock symbol: AAPL\n" +
            "7. Stock name: Apple Inc.\n" +
            "7. Stock quantity: 1.7210716539498594\n" +
            "7. Stock purchase date: 2022-04-01\n" +
            "7. Stock purchase price: $300.0\n" +
            "\n" +
            "8. Stock symbol: AMZN\n" +
            "8. Stock name: Amazon.Com\n" +
            "8. Stock quantity: 0.21398875030569822\n" +
            "8. Stock purchase date: 2022-04-01\n" +
            "8. Stock purchase price: $700.0\n" +
            "\n" +
            "9. Stock symbol: AAPL\n" +
            "9. Stock name: Apple Inc.\n" +
            "9. Stock quantity: 1.8992149911369967\n" +
            "9. Stock purchase date: 2022-05-01\n" +
            "9. Stock purchase price: $300.0\n" +
            "\n" +
            "10. Stock symbol: AMZN\n" +
            "10. Stock name: Amazon.Com\n" +
            "10. Stock quantity: 0.28112449799196787\n" +
            "10. Stock purchase date: 2022-05-01\n" +
            "10. Stock purchase price: $700.0\n" +
            "\n" +
            "11. Stock symbol: AAPL\n" +
            "11. Stock name: Apple Inc.\n" +
            "11. Stock quantity: 2.0155872077398547\n" +
            "11. Stock purchase date: 2022-05-31\n" +
            "11. Stock purchase price: $300.0\n" +
            "\n" +
            "12. Stock symbol: AMZN\n" +
            "12. Stock name: Amazon.Com\n" +
            "12. Stock quantity: 0.29115835270922846\n" +
            "12. Stock purchase date: 2022-05-31\n" +
            "12. Stock purchase price: $700.0\n" +
            "\n" +
            "13. Stock symbol: AAPL\n" +
            "13. Stock name: Apple Inc.\n" +
            "13. Stock quantity: 2.194265652428321\n" +
            "13. Stock purchase date: 2022-06-30\n" +
            "13. Stock purchase price: $300.0\n" +
            "\n" +
            "14. Stock symbol: AMZN\n" +
            "14. Stock name: Amazon.Com\n" +
            "14. Stock quantity: 6.590716505037191\n" +
            "14. Stock purchase date: 2022-06-30\n" +
            "14. Stock purchase price: $700.0\n" +
            "\n" +
            "15. Stock symbol: AAPL\n" +
            "15. Stock name: Apple Inc.\n" +
            "15. Stock quantity: 1.857470125688812\n" +
            "15. Stock purchase date: 2022-07-30\n" +
            "15. Stock purchase price: $300.0\n" +
            "\n" +
            "16. Stock symbol: AMZN\n" +
            "16. Stock name: Amazon.Com\n" +
            "16. Stock quantity: 5.170248910554695\n" +
            "16. Stock purchase date: 2022-07-30\n" +
            "16. Stock purchase price: $700.0\n" +
            "\n" +
            "17. Stock symbol: AAPL\n" +
            "17. Stock name: Apple Inc.\n" +
            "17. Stock quantity: 1.8589664146734417\n" +
            "17. Stock purchase date: 2022-08-29\n" +
            "17. Stock purchase price: $300.0\n" +
            "\n" +
            "18. Stock symbol: AMZN\n" +
            "18. Stock name: Amazon.Com\n" +
            "18. Stock quantity: 5.393327683180523\n" +
            "18. Stock purchase date: 2022-08-29\n" +
            "18. Stock purchase price: $700.0\n" +
            "\n" +
            "19. Stock symbol: AAPL\n" +
            "19. Stock name: Apple Inc.\n" +
            "19. Stock quantity: 2.00213561131874\n" +
            "19. Stock purchase date: 2022-09-28\n" +
            "19. Stock purchase price: $300.0\n" +
            "\n" +
            "20. Stock symbol: AMZN\n" +
            "20. Stock name: Amazon.Com\n" +
            "20. Stock quantity: 5.931700703330226\n" +
            "20. Stock purchase date: 2022-09-28\n" +
            "20. Stock purchase price: $700.0\n" +
            "\n" +
            "21. Stock symbol: AAPL\n" +
            "21. Stock name: Apple Inc.\n" +
            "21. Stock quantity: 1.9262874020803902\n" +
            "21. Stock purchase date: 2022-10-28\n" +
            "21. Stock purchase price: $300.0\n" +
            "\n" +
            "22. Stock symbol: AMZN\n" +
            "22. Stock name: Amazon.Com\n" +
            "22. Stock quantity: 6.769171260032879\n" +
            "22. Stock purchase date: 2022-10-28\n" +
            "22. Stock purchase price: $700.0\n" +
            "\n" +
            "23. Stock symbol: F\n" +
            "23. Stock name: Ford Motor Company\n" +
            "23. Stock quantity: 18.373909049150207\n" +
            "23. Stock purchase date: 2022-01-01\n" +
            "23. Stock purchase price: $400.0\n" +
            "\n" +
            "24. Stock symbol: TSLA\n" +
            "24. Stock name: Tesla Motors\n" +
            "24. Stock quantity: 0.5000916834753039\n" +
            "24. Stock purchase date: 2022-01-01\n" +
            "24. Stock purchase price: $600.0\n" +
            "\n" +
            "25. Stock symbol: F\n" +
            "25. Stock name: Ford Motor Company\n" +
            "25. Stock quantity: 19.704433497536947\n" +
            "25. Stock purchase date: 2022-01-31\n" +
            "25. Stock purchase price: $400.00000000000006\n" +
            "\n" +
            "26. Stock symbol: TSLA\n" +
            "26. Stock name: Tesla Motors\n" +
            "26. Stock quantity: 0.6405329233922623\n" +
            "26. Stock purchase date: 2022-01-31\n" +
            "26. Stock purchase price: $600.0\n" +
            "\n" +
            "27. Stock symbol: F\n" +
            "27. Stock name: Ford Motor Company\n" +
            "27. Stock quantity: 22.09944751381215\n" +
            "27. Stock purchase date: 2022-03-02\n" +
            "27. Stock purchase price: $400.0\n" +
            "\n" +
            "28. Stock symbol: TSLA\n" +
            "28. Stock name: Tesla Motors\n" +
            "28. Stock quantity: 0.68190341974565\n" +
            "28. Stock purchase date: 2022-03-02\n" +
            "28. Stock purchase price: $600.0\n" +
            "\n" +
            "29. Stock symbol: F\n" +
            "29. Stock name: Ford Motor Company\n" +
            "29. Stock quantity: 24.024024024024026\n" +
            "29. Stock purchase date: 2022-04-01\n" +
            "29. Stock purchase price: $400.0\n" +
            "\n" +
            "30. Stock symbol: TSLA\n" +
            "30. Stock name: Tesla Motors\n" +
            "30. Stock quantity: 0.5532044366995824\n" +
            "30. Stock purchase date: 2022-04-01\n" +
            "30. Stock purchase price: $600.0\n" +
            "\n" +
            "31. Stock symbol: F\n" +
            "31. Stock name: Ford Motor Company\n" +
            "31. Stock quantity: 28.030833917309042\n" +
            "31. Stock purchase date: 2022-05-01\n" +
            "31. Stock purchase price: $400.0\n" +
            "\n" +
            "32. Stock symbol: TSLA\n" +
            "32. Stock name: Tesla Motors\n" +
            "32. Stock quantity: 0.6644959797993222\n" +
            "32. Stock purchase date: 2022-05-01\n" +
            "32. Stock purchase price: $600.0\n" +
            "\n" +
            "33. Stock symbol: F\n" +
            "33. Stock name: Ford Motor Company\n" +
            "33. Stock quantity: 29.239766081871345\n" +
            "33. Stock purchase date: 2022-05-31\n" +
            "33. Stock purchase price: $400.0\n" +
            "\n" +
            "34. Stock symbol: TSLA\n" +
            "34. Stock name: Tesla Motors\n" +
            "34. Stock quantity: 0.7912853111070082\n" +
            "34. Stock purchase date: 2022-05-31\n" +
            "34. Stock purchase price: $600.0\n" +
            "\n" +
            "35. Stock symbol: F\n" +
            "35. Stock name: Ford Motor Company\n" +
            "35. Stock quantity: 35.93890386343216\n" +
            "35. Stock purchase date: 2022-06-30\n" +
            "35. Stock purchase price: $399.99999999999994\n" +
            "\n" +
            "36. Stock symbol: TSLA\n" +
            "36. Stock name: Tesla Motors\n" +
            "36. Stock quantity: 0.8909744290338868\n" +
            "36. Stock purchase date: 2022-06-30\n" +
            "36. Stock purchase price: $600.0\n" +
            "\n" +
            "37. Stock symbol: F\n" +
            "37. Stock name: Ford Motor Company\n" +
            "37. Stock quantity: 26.07561929595828\n" +
            "37. Stock purchase date: 2022-07-30\n" +
            "37. Stock purchase price: $400.0\n" +
            "\n" +
            "38. Stock symbol: TSLA\n" +
            "38. Stock name: Tesla Motors\n" +
            "38. Stock quantity: 0.6727739591626206\n" +
            "38. Stock purchase date: 2022-07-30\n" +
            "38. Stock purchase price: $600.0\n" +
            "\n" +
            "39. Stock symbol: F\n" +
            "39. Stock name: Ford Motor Company\n" +
            "39. Stock quantity: 25.690430314707772\n" +
            "39. Stock purchase date: 2022-08-29\n" +
            "39. Stock purchase price: $400.0\n" +
            "\n" +
            "40. Stock symbol: TSLA\n" +
            "40. Stock name: Tesla Motors\n" +
            "40. Stock quantity: 2.106593638087213\n" +
            "40. Stock purchase date: 2022-08-29\n" +
            "40. Stock purchase price: $600.0\n" +
            "\n" +
            "41. Stock symbol: F\n" +
            "41. Stock name: Ford Motor Company\n" +
            "41. Stock quantity: 32.84072249589491\n" +
            "41. Stock purchase date: 2022-09-28\n" +
            "41. Stock purchase price: $400.0\n" +
            "\n" +
            "42. Stock symbol: TSLA\n" +
            "42. Stock name: Tesla Motors\n" +
            "42. Stock quantity: 2.0847086619644903\n" +
            "42. Stock purchase date: 2022-09-28\n" +
            "42. Stock purchase price: $600.0\n" +
            "\n" +
            "43. Stock symbol: F\n" +
            "43. Stock name: Ford Motor Company\n" +
            "43. Stock quantity: 30.165912518853695\n" +
            "43. Stock purchase date: 2022-10-28\n" +
            "43. Stock purchase price: $400.0\n" +
            "\n" +
            "44. Stock symbol: TSLA\n" +
            "44. Stock name: Tesla Motors\n" +
            "44. Stock quantity: 2.625590757920532\n" +
            "44. Stock purchase date: 2022-10-28\n" +
            "44. Stock purchase price: $600.0\n" +
            "\n" +
            "45. Stock symbol: IBM\n" +
            "45. Stock name: International Business Machines Corp.\n" +
            "45. Stock quantity: 55.0\n" +
            "45. Stock purchase date: 2021-03-04\n" +
            "45. Stock purchase price: $6606.05\n" +
            "\n" +
            "46. Stock symbol: IBM\n" +
            "46. Stock name: International Business Machines Corp.\n" +
            "46. Stock quantity: 50.0\n" +
            "46. Stock purchase date: 2021-05-17\n" +
            "46. Stock purchase price: $7255.500000000001\n" +
            "\n" +
            "47. Stock symbol: IBM\n" +
            "47. Stock name: International Business Machines Corp.\n" +
            "47. Stock quantity: 80.0\n" +
            "47. Stock purchase date: 2021-06-02\n" +
            "47. Stock purchase price: $11657.6\n" +
            "\n" +
            "48. Stock symbol: IBM\n" +
            "48. Stock name: International Business Machines Corp.\n" +
            "48. Stock quantity: 30.0\n" +
            "48. Stock purchase date: 2021-08-12\n" +
            "48. Stock purchase price: $4292.099999999999\n" +
            "\n" +
            "49. Stock symbol: IBM\n" +
            "49. Stock name: International Business Machines Corp.\n" +
            "49. Stock quantity: 110.0\n" +
            "49. Stock purchase date: 2021-09-07\n" +
            "49. Stock purchase price: $15186.6\n" +
            "\n" +
            "50. Stock symbol: IBM\n" +
            "50. Stock name: International Business Machines Corp.\n" +
            "50. Stock quantity: 75.0\n" +
            "50. Stock purchase date: 2021-10-13\n" +
            "50. Stock purchase price: $10557.0\n" +
            "\n" +
            "51. Stock symbol: IBM\n" +
            "51. Stock name: International Business Machines Corp.\n" +
            "51. Stock quantity: 500.0\n" +
            "51. Stock purchase date: 2021-12-23\n" +
            "51. Stock purchase price: $65315.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestParseInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n11";
    Readable i = new StringReader(inputString);
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestInvalidExamine() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n4\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSaveData() {
    String inputString = "2\n1\nyes\ninput2.xml\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 55.0\n" +
            "1. Stock purchase date: 2021-03-04\n" +
            "1. Stock purchase price: $6606.05\n" +
            "\n" +
            "2. Stock symbol: IBM\n" +
            "2. Stock name: International Business Machines Corp.\n" +
            "2. Stock quantity: 50.0\n" +
            "2. Stock purchase date: 2021-05-17\n" +
            "2. Stock purchase price: $7255.500000000001\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 80.0\n" +
            "3. Stock purchase date: 2021-06-02\n" +
            "3. Stock purchase price: $11657.6\n" +
            "\n" +
            "4. Stock symbol: IBM\n" +
            "4. Stock name: International Business Machines Corp.\n" +
            "4. Stock quantity: 30.0\n" +
            "4. Stock purchase date: 2021-08-12\n" +
            "4. Stock purchase price: $4292.099999999999\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 110.0\n" +
            "5. Stock purchase date: 2021-09-07\n" +
            "5. Stock purchase price: $15186.6\n" +
            "\n" +
            "6. Stock symbol: IBM\n" +
            "6. Stock name: International Business Machines Corp.\n" +
            "6. Stock quantity: 75.0\n" +
            "6. Stock purchase date: 2021-10-13\n" +
            "6. Stock purchase price: $10557.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 500.0\n" +
            "7. Stock purchase date: 2021-12-23\n" +
            "7. Stock purchase price: $65315.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestTotalValue() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $105975.0. Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestToalValueFutureDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2025-10-10\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $105975.0. Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestTotalValueInvalidDate() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n4\n1\n2022\n2022-10-10\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $105975.0. Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestAddPortfolioAfterInputFile() {
    String inputString = "2\n1\nyes\n" +
            "input2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestAddPortfolio() {
    String inputString = "2\n2\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }


  @Test
  public void flexibleTestInputManuallyOneStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());


  }

  @Test
  public void flexibleTestInputManuallyTwoStocks() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n3\nGOOG\n100\n" +
            "2022-2-2\n4.5\n3\n1\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2673.2\n" +
            "\n" +
            "2. Stock symbol: GOOG\n" +
            "2. Stock name: Google Inc.\n" +
            "2. Stock quantity: 100.0\n" +
            "2. Stock purchase date: 2022-02-02\n" +
            "2. Stock purchase price: $296073.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());


  }

  @Test
  public void flexibleTestAdd2PortfoliosAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nHealth\n1" +
            "\nGOOG\n2022-1-1\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "4 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 55.0\n" +
            "1. Stock purchase date: 2021-03-04\n" +
            "1. Stock purchase price: $6606.05\n" +
            "\n" +
            "2. Stock symbol: IBM\n" +
            "2. Stock name: International Business Machines Corp.\n" +
            "2. Stock quantity: 50.0\n" +
            "2. Stock purchase date: 2021-05-17\n" +
            "2. Stock purchase price: $7255.500000000001\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 80.0\n" +
            "3. Stock purchase date: 2021-06-02\n" +
            "3. Stock purchase price: $11657.6\n" +
            "\n" +
            "4. Stock symbol: IBM\n" +
            "4. Stock name: International Business Machines Corp.\n" +
            "4. Stock quantity: 30.0\n" +
            "4. Stock purchase date: 2021-08-12\n" +
            "4. Stock purchase price: $4292.099999999999\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 110.0\n" +
            "5. Stock purchase date: 2021-09-07\n" +
            "5. Stock purchase price: $15186.6\n" +
            "\n" +
            "6. Stock symbol: IBM\n" +
            "6. Stock name: International Business Machines Corp.\n" +
            "6. Stock quantity: 75.0\n" +
            "6. Stock purchase date: 2021-10-13\n" +
            "6. Stock purchase price: $10557.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 500.0\n" +
            "7. Stock purchase date: 2021-12-23\n" +
            "7. Stock purchase price: $65315.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }


  @Test
  public void flexibleTestAdd2PortfoliosSameNameAfterInputFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n2\nSchool\n1\nIBM\n20\n2022-1-1\n2\nSchool" +
            "\n1\nIBM\n20\n2022-1-2\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestQuantityWhenSameStocksAddedTwice() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n2\nIBM\n20\n2022-1-1\n4.5\nIBM\n5\n" +
            "2022-10-1\n4.5\n3\n1\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2720.7999999999997\n" +
            "\n" +
            "2. Stock symbol: IBM\n" +
            "2. Stock name: International Business Machines Corp.\n" +
            "2. Stock quantity: 5.0\n" +
            "2. Stock purchase date: 2022-10-01\n" +
            "2. Stock purchase price: $607.5500000000001\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());


  }


  @Test
  public void flexibleTestTotalValueWeekend() {
    String inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-28\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 20.0\n" +
            "1. Stock purchase date: 2022-01-01\n" +
            "1. Stock purchase price: $2673.2\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $2770.2. Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

    String totalValueOnFriday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-29\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
    String totalValueOnSaturday = out.toString();
    out = new ByteArrayOutputStream();
    view = new StockViewImpl(new PrintStream(out));
    log = new StringBuilder();
    inputString = "2\n1\nno\nkarthik\n1\nSchool\n1\nIBM\n20\n2022-1-1\n4.5\n3\n1\n4\n1\n" +
            "2022-10-30\n5\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
    String totalValueOnSunday = out.toString();
    assertEquals(totalValueOnSaturday, totalValueOnSunday);
    assertEquals(totalValueOnFriday, totalValueOnSunday);

  }

  @Test
  public void flexibleTestInputFilesMultipleTimesSameFile() {
    String inputString = "2\n1\nyes\ninput2.xml\n1\nyes\ninput2.xml\n3\n1\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 40.0\n" +
            "1. Stock purchase date: 2021-06-02\n" +
            "1. Stock purchase price: $5828.8\n" +
            "\n" +
            "2. Stock symbol: IBM\n" +
            "2. Stock name: International Business Machines Corp.\n" +
            "2. Stock quantity: 30.0\n" +
            "2. Stock purchase date: 2021-08-12\n" +
            "2. Stock purchase price: $4292.099999999999\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 110.0\n" +
            "3. Stock purchase date: 2021-09-07\n" +
            "3. Stock purchase price: $15186.6\n" +
            "\n" +
            "4. Stock symbol: IBM\n" +
            "4. Stock name: International Business Machines Corp.\n" +
            "4. Stock quantity: 75.0\n" +
            "4. Stock purchase date: 2021-10-13\n" +
            "4. Stock purchase price: $10557.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 500.0\n" +
            "5. Stock purchase date: 2021-12-23\n" +
            "5. Stock purchase price: $65315.0\n" +
            "\n" +
            "6. Stock symbol: IBM\n" +
            "6. Stock name: International Business Machines Corp.\n" +
            "6. Stock quantity: 150.0\n" +
            "6. Stock purchase date: 2021-03-04\n" +
            "6. Stock purchase price: $18016.5\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 50.0\n" +
            "7. Stock purchase date: 2021-05-17\n" +
            "7. Stock purchase price: $7255.500000000001\n" +
            "\n" +
            "8. Stock symbol: IBM\n" +
            "8. Stock name: International Business Machines Corp.\n" +
            "8. Stock quantity: 80.0\n" +
            "8. Stock purchase date: 2021-06-02\n" +
            "8. Stock purchase price: $11657.6\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 30.0\n" +
            "9. Stock purchase date: 2021-08-12\n" +
            "9. Stock purchase price: $4292.099999999999\n" +
            "\n" +
            "10. Stock symbol: IBM\n" +
            "10. Stock name: International Business Machines Corp.\n" +
            "10. Stock quantity: 110.0\n" +
            "10. Stock purchase date: 2021-09-07\n" +
            "10. Stock purchase price: $15186.6\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 75.0\n" +
            "11. Stock purchase date: 2021-10-13\n" +
            "11. Stock purchase price: $10557.0\n" +
            "\n" +
            "12. Stock symbol: IBM\n" +
            "12. Stock name: International Business Machines Corp.\n" +
            "12. Stock quantity: 500.0\n" +
            "12. Stock purchase date: 2021-12-23\n" +
            "12. Stock purchase price: $65315.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());


  }

  @Test
  public void flexibleTestBuyStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuyStockOverFlowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\n" +
            "nflx\n10\n2021-02-10\n4.7\n6\n1\nf\n100000000000000000000000000000000" +
            "0000000000000000000000000000" +
            "00000000000000000000\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuyStockQuantityZero() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuyStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n202-1-20\n" +
            "1994-1-1\n2021-1-20" +
            "\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n0\n10\n2020-1-20\n10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n12\n2019-05-24\n7\n1\nf\n6\n2022-01-01\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStockLowQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nf\n20\n2022-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStockFutureDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\n" +
            "nflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n4.6\n7\n1\nibm\n2\n2028-10-10\n" +
            "2022-11-11\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStockBeforeDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n7\n1\nibm\n2\n2018-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStockInvalidDate() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n2.5\n7\n1\nnflx\n2\n201-10-10\n2023-1-1\n" +
            "2022-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestSellStockLessQuantity() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4.7\n6\n1\nf\n20\n2019-05-24\n2.5\n7\n1\nibm\n30\n201-10-10\n2023-1-1\n" +
            "2022-10-10\n4.7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Selling Date in YYYY-MM-DD format:\n" +
            "Date is invalid\n" +
            "\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuySellStock() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\n" +
            "nflx\n11\n" +
            "2021-02-10\n7\n1\nibm\n10\n2022-10-10\n3\n7\n1\nnflx\n3\n2022-07-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestBuyAndSellEverything() {
    String inputString = "2\n1\nno\nkarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n7\n1\nibm\n20\n2022-10-10\n3\n7\n1\nnflx\n10\n2022-07-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "2 Create new portfolio\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }


  @Test
  public void flexibleTestCostBasis() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n" +
            "4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2022-01-01\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestNegativeCommission() {
    String inputString = "2\n1\nno\nShrada\n1\nHealth\n1\nibm\n20\n2019-1-20\n-10\n10\n8" +
            "\n1\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Health\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $2486.3999999999996\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestCommissionGreaterThan30() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n-10\n10\n8\n1" +
            "\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Shrada\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $2486.3999999999996\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestCommissionGreaterThan0LessThan2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n1\n20.9\n8\n1" +
            "\n2022-11-17\n11";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Shrada\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $2497.2999999999997\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestCostBasisBetweenBuyAndSell() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2020-12-12\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }


  @Test
  public void flexibleTestCostBasisAfterTransaction() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n6\n1\nIBM\n5\n2020-2-20\n3.2\n8\n1\n2020-4-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $3530.1999999999994\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }


  @Test
  public void flexibleTestCostBasisAfterStrategy() {
    String inputString = "2\n10\n1\nCollege\n2\nIBM\n40\nnflx\n60\n1000\n5\n" +
            "2021-01-01\n2022-01-01\n30\n3\n1\n8\n1\n2021-2-2\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Create new portfolio\n" +
            "Select one option from above\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter amount: \n" +
            "Enter commission fee: \n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Enter N/A for Ongoing Strategy.\n" +
            "Enter frequency in days: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 College\n" +
            "Select one option from above\n" +
            "1. Stock symbol: IBM\n" +
            "1. Stock name: International Business Machines Corp.\n" +
            "1. Stock quantity: 3.195094400516379\n" +
            "1. Stock purchase date: 2021-01-01\n" +
            "1. Stock purchase price: $396.0\n" +
            "\n" +
            "2. Stock symbol: NFLX\n" +
            "2. Stock name: Netflix\n" +
            "2. Stock quantity: 1.1360593657958153\n" +
            "2. Stock purchase date: 2021-01-01\n" +
            "2. Stock purchase price: $594.0\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 3.285216525634644\n" +
            "3. Stock purchase date: 2021-01-31\n" +
            "3. Stock purchase price: $396.0\n" +
            "\n" +
            "4. Stock symbol: NFLX\n" +
            "4. Stock name: Netflix\n" +
            "4. Stock quantity: 1.101959038290294\n" +
            "4. Stock purchase date: 2021-01-31\n" +
            "4. Stock purchase price: $594.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 3.2909498878085266\n" +
            "5. Stock purchase date: 2021-03-02\n" +
            "5. Stock purchase price: $396.0\n" +
            "\n" +
            "6. Stock symbol: NFLX\n" +
            "6. Stock name: Netflix\n" +
            "6. Stock quantity: 1.0842977620386258\n" +
            "6. Stock purchase date: 2021-03-02\n" +
            "6. Stock purchase price: $594.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 2.9723035352398113\n" +
            "7. Stock purchase date: 2021-04-01\n" +
            "7. Stock purchase price: $396.0\n" +
            "\n" +
            "8. Stock symbol: NFLX\n" +
            "8. Stock name: Netflix\n" +
            "8. Stock quantity: 1.1011827518445738\n" +
            "8. Stock purchase date: 2021-04-01\n" +
            "8. Stock purchase price: $594.0\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 2.7357512953367875\n" +
            "9. Stock purchase date: 2021-05-01\n" +
            "9. Stock purchase price: $396.0\n" +
            "\n" +
            "10. Stock symbol: NFLX\n" +
            "10. Stock name: Netflix\n" +
            "10. Stock quantity: 1.1667419614621595\n" +
            "10. Stock purchase date: 2021-05-01\n" +
            "10. Stock purchase price: $594.0\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 2.746376309036688\n" +
            "11. Stock purchase date: 2021-05-31\n" +
            "11. Stock purchase price: $396.00000000000006\n" +
            "\n" +
            "12. Stock symbol: NFLX\n" +
            "12. Stock name: Netflix\n" +
            "12. Stock quantity: 1.1901899495070931\n" +
            "12. Stock purchase date: 2021-05-31\n" +
            "12. Stock purchase price: $594.0\n" +
            "\n" +
            "13. Stock symbol: IBM\n" +
            "13. Stock name: International Business Machines Corp.\n" +
            "13. Stock quantity: 2.701412101780476\n" +
            "13. Stock purchase date: 2021-06-30\n" +
            "13. Stock purchase price: $396.0\n" +
            "\n" +
            "14. Stock symbol: NFLX\n" +
            "14. Stock name: Netflix\n" +
            "14. Stock quantity: 1.1245527347077866\n" +
            "14. Stock purchase date: 2021-06-30\n" +
            "14. Stock purchase price: $594.0\n" +
            "\n" +
            "15. Stock symbol: IBM\n" +
            "15. Stock name: International Business Machines Corp.\n" +
            "15. Stock quantity: 2.8093076049943244\n" +
            "15. Stock purchase date: 2021-07-30\n" +
            "15. Stock purchase price: $396.0\n" +
            "\n" +
            "16. Stock symbol: NFLX\n" +
            "16. Stock name: Netflix\n" +
            "16. Stock quantity: 1.1476708464555518\n" +
            "16. Stock purchase date: 2021-07-30\n" +
            "16. Stock purchase price: $594.0\n" +
            "\n" +
            "17. Stock symbol: IBM\n" +
            "17. Stock name: International Business Machines Corp.\n" +
            "17. Stock quantity: 2.849535871051306\n" +
            "17. Stock purchase date: 2021-08-29\n" +
            "17. Stock purchase price: $396.0\n" +
            "\n" +
            "18. Stock symbol: NFLX\n" +
            "18. Stock name: Netflix\n" +
            "18. Stock quantity: 1.0491363170723091\n" +
            "18. Stock purchase date: 2021-08-29\n" +
            "18. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "19. Stock symbol: IBM\n" +
            "19. Stock name: International Business Machines Corp.\n" +
            "19. Stock quantity: 2.880628500763803\n" +
            "19. Stock purchase date: 2021-09-28\n" +
            "19. Stock purchase price: $396.0\n" +
            "\n" +
            "20. Stock symbol: NFLX\n" +
            "20. Stock name: Netflix\n" +
            "20. Stock quantity: 1.0173846022094715\n" +
            "20. Stock purchase date: 2021-09-28\n" +
            "20. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "21. Stock symbol: IBM\n" +
            "21. Stock name: International Business Machines Corp.\n" +
            "21. Stock quantity: 3.1468531468531467\n" +
            "21. Stock purchase date: 2021-10-28\n" +
            "21. Stock purchase price: $396.0\n" +
            "\n" +
            "22. Stock symbol: NFLX\n" +
            "22. Stock name: Netflix\n" +
            "22. Stock quantity: 0.8812402640753654\n" +
            "22. Stock purchase date: 2021-10-28\n" +
            "22. Stock purchase price: $594.0\n" +
            "\n" +
            "23. Stock symbol: IBM\n" +
            "23. Stock name: International Business Machines Corp.\n" +
            "23. Stock quantity: 3.3417721518987342\n" +
            "23. Stock purchase date: 2021-11-27\n" +
            "23. Stock purchase price: $396.0\n" +
            "\n" +
            "24. Stock symbol: NFLX\n" +
            "24. Stock name: Netflix\n" +
            "24. Stock quantity: 0.8947939262472885\n" +
            "24. Stock purchase date: 2021-11-27\n" +
            "24. Stock purchase price: $594.0\n" +
            "\n" +
            "25. Stock symbol: IBM\n" +
            "25. Stock name: International Business Machines Corp.\n" +
            "25. Stock quantity: 3.0086612976751255\n" +
            "25. Stock purchase date: 2021-12-27\n" +
            "25. Stock purchase price: $396.0\n" +
            "\n" +
            "26. Stock symbol: NFLX\n" +
            "26. Stock name: Netflix\n" +
            "26. Stock quantity: 0.9688152400835073\n" +
            "26. Stock purchase date: 2021-12-27\n" +
            "26. Stock purchase price: $594.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 College\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $2000.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasisBeforeBuying() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n8\n1\n2019-9-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasisAfterStrategyExistingPortfolio() {
    String inputString = "2\n2\nSchool\n2\nTSLA\n10\n2021-1-1\n5\nt\n\n5\n2021-2-2\n6\n10\n1" +
            "\n2\nIBM\n40\nnflx\n60\n1000\n5\n2021-01-01\n2022-01-01\n30\n3\n1\n8\n1" +
            "\n2021-2-2\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Enter Purchase Date in YYYY-MM-DD format:\n" +
            "Enter commission fee: \n" +
            "Enter Stock symbol:\n" +
            "Enter quantity:\n" +
            "Invalid number. Try again.\n" +
            "\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "2 Create new portfolio\n" +
            "Select one option from above\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter amount: \n" +
            "Enter commission fee: \n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Enter N/A for Ongoing Strategy.\n" +
            "Enter frequency in days: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: TSLA\n" +
            "1. Stock name: Tesla Motors\n" +
            "1. Stock quantity: 10.0\n" +
            "1. Stock purchase date: 2021-01-01\n" +
            "1. Stock purchase price: $7297.7\n" +
            "\n" +
            "2. Stock symbol: T\n" +
            "2. Stock name: AT&T Inc.\n" +
            "2. Stock quantity: 5.0\n" +
            "2. Stock purchase date: 2021-02-02\n" +
            "2. Stock purchase price: $142.7\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 3.195094400516379\n" +
            "3. Stock purchase date: 2021-01-01\n" +
            "3. Stock purchase price: $396.0\n" +
            "\n" +
            "4. Stock symbol: NFLX\n" +
            "4. Stock name: Netflix\n" +
            "4. Stock quantity: 1.1360593657958153\n" +
            "4. Stock purchase date: 2021-01-01\n" +
            "4. Stock purchase price: $594.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 3.285216525634644\n" +
            "5. Stock purchase date: 2021-01-31\n" +
            "5. Stock purchase price: $396.0\n" +
            "\n" +
            "6. Stock symbol: NFLX\n" +
            "6. Stock name: Netflix\n" +
            "6. Stock quantity: 1.101959038290294\n" +
            "6. Stock purchase date: 2021-01-31\n" +
            "6. Stock purchase price: $594.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 3.2909498878085266\n" +
            "7. Stock purchase date: 2021-03-02\n" +
            "7. Stock purchase price: $396.0\n" +
            "\n" +
            "8. Stock symbol: NFLX\n" +
            "8. Stock name: Netflix\n" +
            "8. Stock quantity: 1.0842977620386258\n" +
            "8. Stock purchase date: 2021-03-02\n" +
            "8. Stock purchase price: $594.0\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 2.9723035352398113\n" +
            "9. Stock purchase date: 2021-04-01\n" +
            "9. Stock purchase price: $396.0\n" +
            "\n" +
            "10. Stock symbol: NFLX\n" +
            "10. Stock name: Netflix\n" +
            "10. Stock quantity: 1.1011827518445738\n" +
            "10. Stock purchase date: 2021-04-01\n" +
            "10. Stock purchase price: $594.0\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 2.7357512953367875\n" +
            "11. Stock purchase date: 2021-05-01\n" +
            "11. Stock purchase price: $396.0\n" +
            "\n" +
            "12. Stock symbol: NFLX\n" +
            "12. Stock name: Netflix\n" +
            "12. Stock quantity: 1.1667419614621595\n" +
            "12. Stock purchase date: 2021-05-01\n" +
            "12. Stock purchase price: $594.0\n" +
            "\n" +
            "13. Stock symbol: IBM\n" +
            "13. Stock name: International Business Machines Corp.\n" +
            "13. Stock quantity: 2.746376309036688\n" +
            "13. Stock purchase date: 2021-05-31\n" +
            "13. Stock purchase price: $396.00000000000006\n" +
            "\n" +
            "14. Stock symbol: NFLX\n" +
            "14. Stock name: Netflix\n" +
            "14. Stock quantity: 1.1901899495070931\n" +
            "14. Stock purchase date: 2021-05-31\n" +
            "14. Stock purchase price: $594.0\n" +
            "\n" +
            "15. Stock symbol: IBM\n" +
            "15. Stock name: International Business Machines Corp.\n" +
            "15. Stock quantity: 2.701412101780476\n" +
            "15. Stock purchase date: 2021-06-30\n" +
            "15. Stock purchase price: $396.0\n" +
            "\n" +
            "16. Stock symbol: NFLX\n" +
            "16. Stock name: Netflix\n" +
            "16. Stock quantity: 1.1245527347077866\n" +
            "16. Stock purchase date: 2021-06-30\n" +
            "16. Stock purchase price: $594.0\n" +
            "\n" +
            "17. Stock symbol: IBM\n" +
            "17. Stock name: International Business Machines Corp.\n" +
            "17. Stock quantity: 2.8093076049943244\n" +
            "17. Stock purchase date: 2021-07-30\n" +
            "17. Stock purchase price: $396.0\n" +
            "\n" +
            "18. Stock symbol: NFLX\n" +
            "18. Stock name: Netflix\n" +
            "18. Stock quantity: 1.1476708464555518\n" +
            "18. Stock purchase date: 2021-07-30\n" +
            "18. Stock purchase price: $594.0\n" +
            "\n" +
            "19. Stock symbol: IBM\n" +
            "19. Stock name: International Business Machines Corp.\n" +
            "19. Stock quantity: 2.849535871051306\n" +
            "19. Stock purchase date: 2021-08-29\n" +
            "19. Stock purchase price: $396.0\n" +
            "\n" +
            "20. Stock symbol: NFLX\n" +
            "20. Stock name: Netflix\n" +
            "20. Stock quantity: 1.0491363170723091\n" +
            "20. Stock purchase date: 2021-08-29\n" +
            "20. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "21. Stock symbol: IBM\n" +
            "21. Stock name: International Business Machines Corp.\n" +
            "21. Stock quantity: 2.880628500763803\n" +
            "21. Stock purchase date: 2021-09-28\n" +
            "21. Stock purchase price: $396.0\n" +
            "\n" +
            "22. Stock symbol: NFLX\n" +
            "22. Stock name: Netflix\n" +
            "22. Stock quantity: 1.0173846022094715\n" +
            "22. Stock purchase date: 2021-09-28\n" +
            "22. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "23. Stock symbol: IBM\n" +
            "23. Stock name: International Business Machines Corp.\n" +
            "23. Stock quantity: 3.1468531468531467\n" +
            "23. Stock purchase date: 2021-10-28\n" +
            "23. Stock purchase price: $396.0\n" +
            "\n" +
            "24. Stock symbol: NFLX\n" +
            "24. Stock name: Netflix\n" +
            "24. Stock quantity: 0.8812402640753654\n" +
            "24. Stock purchase date: 2021-10-28\n" +
            "24. Stock purchase price: $594.0\n" +
            "\n" +
            "25. Stock symbol: IBM\n" +
            "25. Stock name: International Business Machines Corp.\n" +
            "25. Stock quantity: 3.3417721518987342\n" +
            "25. Stock purchase date: 2021-11-27\n" +
            "25. Stock purchase price: $396.0\n" +
            "\n" +
            "26. Stock symbol: NFLX\n" +
            "26. Stock name: Netflix\n" +
            "26. Stock quantity: 0.8947939262472885\n" +
            "26. Stock purchase date: 2021-11-27\n" +
            "26. Stock purchase price: $594.0\n" +
            "\n" +
            "27. Stock symbol: IBM\n" +
            "27. Stock name: International Business Machines Corp.\n" +
            "27. Stock quantity: 3.0086612976751255\n" +
            "27. Stock purchase date: 2021-12-27\n" +
            "27. Stock purchase price: $396.0\n" +
            "\n" +
            "28. Stock symbol: NFLX\n" +
            "28. Stock name: Netflix\n" +
            "28. Stock quantity: 0.9688152400835073\n" +
            "28. Stock purchase date: 2021-12-27\n" +
            "28. Stock purchase price: $594.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $9302.7\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestCostBasisAfterStrategyExistingPortfolioMultipleDates() {
    String inputString = "2\n2\nSchool\n2\nTSLA\n10\n2021-1-1\n5\nt\n5\n2021-2-2\n6\n10\n1" +
            "\n2\nIBM\n40\nnflx\n60\n1000\n5\n2021-03-01\n2022-03-01\n30\n3\n1\n8\n1" +
            "\n2021-2-5\n8\n1\n2021-7-7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "2 Create new portfolio\n" +
            "Select one option from above\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter amount: \n" +
            "Enter commission fee: \n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Enter N/A for Ongoing Strategy.\n" +
            "Enter frequency in days: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: TSLA\n" +
            "1. Stock name: Tesla Motors\n" +
            "1. Stock quantity: 10.0\n" +
            "1. Stock purchase date: 2021-01-01\n" +
            "1. Stock purchase price: $7297.7\n" +
            "\n" +
            "2. Stock symbol: T\n" +
            "2. Stock name: AT&T Inc.\n" +
            "2. Stock quantity: 5.0\n" +
            "2. Stock purchase date: 2021-02-02\n" +
            "2. Stock purchase price: $142.7\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 3.2797747225443104\n" +
            "3. Stock purchase date: 2021-03-01\n" +
            "3. Stock purchase price: $396.0\n" +
            "\n" +
            "4. Stock symbol: NFLX\n" +
            "4. Stock name: Netflix\n" +
            "4. Stock quantity: 1.0787447334011333\n" +
            "4. Stock purchase date: 2021-03-01\n" +
            "4. Stock purchase price: $594.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 2.971634398919406\n" +
            "5. Stock purchase date: 2021-03-31\n" +
            "5. Stock purchase price: $396.0\n" +
            "\n" +
            "6. Stock symbol: NFLX\n" +
            "6. Stock name: Netflix\n" +
            "6. Stock quantity: 1.1386726986926352\n" +
            "6. Stock purchase date: 2021-03-31\n" +
            "6. Stock purchase price: $594.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 2.791091062870031\n" +
            "7. Stock purchase date: 2021-04-30\n" +
            "7. Stock purchase price: $396.0\n" +
            "\n" +
            "8. Stock symbol: NFLX\n" +
            "8. Stock name: Netflix\n" +
            "8. Stock quantity: 1.1568348686388688\n" +
            "8. Stock purchase date: 2021-04-30\n" +
            "8. Stock purchase price: $594.0\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 2.746376309036688\n" +
            "9. Stock purchase date: 2021-05-30\n" +
            "9. Stock purchase price: $396.00000000000006\n" +
            "\n" +
            "10. Stock symbol: NFLX\n" +
            "10. Stock name: Netflix\n" +
            "10. Stock quantity: 1.1901899495070931\n" +
            "10. Stock purchase date: 2021-05-30\n" +
            "10. Stock purchase price: $594.0\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 2.7207145310889724\n" +
            "11. Stock purchase date: 2021-06-29\n" +
            "11. Stock purchase price: $395.99999999999994\n" +
            "\n" +
            "12. Stock symbol: NFLX\n" +
            "12. Stock name: Netflix\n" +
            "12. Stock quantity: 1.1134020618556701\n" +
            "12. Stock purchase date: 2021-06-29\n" +
            "12. Stock purchase price: $594.0\n" +
            "\n" +
            "13. Stock symbol: IBM\n" +
            "13. Stock name: International Business Machines Corp.\n" +
            "13. Stock quantity: 2.7901077996195305\n" +
            "13. Stock purchase date: 2021-07-29\n" +
            "13. Stock purchase price: $396.0\n" +
            "\n" +
            "14. Stock symbol: NFLX\n" +
            "14. Stock name: Netflix\n" +
            "14. Stock quantity: 1.1550802139037433\n" +
            "14. Stock purchase date: 2021-07-29\n" +
            "14. Stock purchase price: $594.0\n" +
            "\n" +
            "15. Stock symbol: IBM\n" +
            "15. Stock name: International Business Machines Corp.\n" +
            "15. Stock quantity: 2.849535871051306\n" +
            "15. Stock purchase date: 2021-08-28\n" +
            "15. Stock purchase price: $396.0\n" +
            "\n" +
            "16. Stock symbol: NFLX\n" +
            "16. Stock name: Netflix\n" +
            "16. Stock quantity: 1.0491363170723091\n" +
            "16. Stock purchase date: 2021-08-28\n" +
            "16. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "17. Stock symbol: IBM\n" +
            "17. Stock name: International Business Machines Corp.\n" +
            "17. Stock quantity: 2.8579676674364896\n" +
            "17. Stock purchase date: 2021-09-27\n" +
            "17. Stock purchase price: $396.0\n" +
            "\n" +
            "18. Stock symbol: NFLX\n" +
            "18. Stock name: Netflix\n" +
            "18. Stock quantity: 1.0022948164146868\n" +
            "18. Stock purchase date: 2021-09-27\n" +
            "18. Stock purchase price: $594.0\n" +
            "\n" +
            "19. Stock symbol: IBM\n" +
            "19. Stock name: International Business Machines Corp.\n" +
            "19. Stock quantity: 3.1636973715746586\n" +
            "19. Stock purchase date: 2021-10-27\n" +
            "19. Stock purchase price: $396.0\n" +
            "\n" +
            "20. Stock symbol: NFLX\n" +
            "20. Stock name: Netflix\n" +
            "20. Stock quantity: 0.8960357207506185\n" +
            "20. Stock purchase date: 2021-10-27\n" +
            "20. Stock purchase price: $594.0\n" +
            "\n" +
            "21. Stock symbol: IBM\n" +
            "21. Stock name: International Business Machines Corp.\n" +
            "21. Stock quantity: 3.419393834729298\n" +
            "21. Stock purchase date: 2021-11-26\n" +
            "21. Stock purchase price: $396.0\n" +
            "\n" +
            "22. Stock symbol: NFLX\n" +
            "22. Stock name: Netflix\n" +
            "22. Stock quantity: 0.8923742563547864\n" +
            "22. Stock purchase date: 2021-11-26\n" +
            "22. Stock purchase price: $594.0\n" +
            "\n" +
            "23. Stock symbol: IBM\n" +
            "23. Stock name: International Business Machines Corp.\n" +
            "23. Stock quantity: 3.0086612976751255\n" +
            "23. Stock purchase date: 2021-12-26\n" +
            "23. Stock purchase price: $396.0\n" +
            "\n" +
            "24. Stock symbol: NFLX\n" +
            "24. Stock name: Netflix\n" +
            "24. Stock quantity: 0.9688152400835073\n" +
            "24. Stock purchase date: 2021-12-26\n" +
            "24. Stock purchase price: $594.0\n" +
            "\n" +
            "25. Stock symbol: IBM\n" +
            "25. Stock name: International Business Machines Corp.\n" +
            "25. Stock quantity: 2.909625275532697\n" +
            "25. Stock purchase date: 2022-01-25\n" +
            "25. Stock purchase price: $396.0\n" +
            "\n" +
            "26. Stock symbol: NFLX\n" +
            "26. Stock name: Netflix\n" +
            "26. Stock quantity: 1.6210905518257737\n" +
            "26. Stock purchase date: 2022-01-25\n" +
            "26. Stock purchase price: $594.0\n" +
            "\n" +
            "27. Stock symbol: IBM\n" +
            "27. Stock name: International Business Machines Corp.\n" +
            "27. Stock quantity: 3.2467000081987374\n" +
            "27. Stock purchase date: 2022-02-24\n" +
            "27. Stock purchase price: $396.0\n" +
            "\n" +
            "28. Stock symbol: NFLX\n" +
            "28. Stock name: Netflix\n" +
            "28. Stock quantity: 1.522959772325206\n" +
            "28. Stock purchase date: 2022-02-24\n" +
            "28. Stock purchase price: $594.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $7451.4\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $12451.4\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueAfterStrategyExistingPortfolio() {
    String inputString = "2\n2\nSchool\n2\nTSLA\n10\n2021-1-1\n5\nt\n5\n2021-2-2\n6\n10\n1" +
            "\n2\nIBM\n40\nnflx\n60\n1000\n5\n2021-03-01\n2022-03-01\n30\n3\n1\n4\n1" +
            "\n2021-2-5\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "2 Create new portfolio\n" +
            "Select one option from above\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter amount: \n" +
            "Enter commission fee: \n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Enter N/A for Ongoing Strategy.\n" +
            "Enter frequency in days: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: TSLA\n" +
            "1. Stock name: Tesla Motors\n" +
            "1. Stock quantity: 10.0\n" +
            "1. Stock purchase date: 2021-01-01\n" +
            "1. Stock purchase price: $7297.7\n" +
            "\n" +
            "2. Stock symbol: T\n" +
            "2. Stock name: AT&T Inc.\n" +
            "2. Stock quantity: 5.0\n" +
            "2. Stock purchase date: 2021-02-02\n" +
            "2. Stock purchase price: $142.7\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 3.2797747225443104\n" +
            "3. Stock purchase date: 2021-03-01\n" +
            "3. Stock purchase price: $396.0\n" +
            "\n" +
            "4. Stock symbol: NFLX\n" +
            "4. Stock name: Netflix\n" +
            "4. Stock quantity: 1.0787447334011333\n" +
            "4. Stock purchase date: 2021-03-01\n" +
            "4. Stock purchase price: $594.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 2.971634398919406\n" +
            "5. Stock purchase date: 2021-03-31\n" +
            "5. Stock purchase price: $396.0\n" +
            "\n" +
            "6. Stock symbol: NFLX\n" +
            "6. Stock name: Netflix\n" +
            "6. Stock quantity: 1.1386726986926352\n" +
            "6. Stock purchase date: 2021-03-31\n" +
            "6. Stock purchase price: $594.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 2.791091062870031\n" +
            "7. Stock purchase date: 2021-04-30\n" +
            "7. Stock purchase price: $396.0\n" +
            "\n" +
            "8. Stock symbol: NFLX\n" +
            "8. Stock name: Netflix\n" +
            "8. Stock quantity: 1.1568348686388688\n" +
            "8. Stock purchase date: 2021-04-30\n" +
            "8. Stock purchase price: $594.0\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 2.746376309036688\n" +
            "9. Stock purchase date: 2021-05-30\n" +
            "9. Stock purchase price: $396.00000000000006\n" +
            "\n" +
            "10. Stock symbol: NFLX\n" +
            "10. Stock name: Netflix\n" +
            "10. Stock quantity: 1.1901899495070931\n" +
            "10. Stock purchase date: 2021-05-30\n" +
            "10. Stock purchase price: $594.0\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 2.7207145310889724\n" +
            "11. Stock purchase date: 2021-06-29\n" +
            "11. Stock purchase price: $395.99999999999994\n" +
            "\n" +
            "12. Stock symbol: NFLX\n" +
            "12. Stock name: Netflix\n" +
            "12. Stock quantity: 1.1134020618556701\n" +
            "12. Stock purchase date: 2021-06-29\n" +
            "12. Stock purchase price: $594.0\n" +
            "\n" +
            "13. Stock symbol: IBM\n" +
            "13. Stock name: International Business Machines Corp.\n" +
            "13. Stock quantity: 2.7901077996195305\n" +
            "13. Stock purchase date: 2021-07-29\n" +
            "13. Stock purchase price: $396.0\n" +
            "\n" +
            "14. Stock symbol: NFLX\n" +
            "14. Stock name: Netflix\n" +
            "14. Stock quantity: 1.1550802139037433\n" +
            "14. Stock purchase date: 2021-07-29\n" +
            "14. Stock purchase price: $594.0\n" +
            "\n" +
            "15. Stock symbol: IBM\n" +
            "15. Stock name: International Business Machines Corp.\n" +
            "15. Stock quantity: 2.849535871051306\n" +
            "15. Stock purchase date: 2021-08-28\n" +
            "15. Stock purchase price: $396.0\n" +
            "\n" +
            "16. Stock symbol: NFLX\n" +
            "16. Stock name: Netflix\n" +
            "16. Stock quantity: 1.0491363170723091\n" +
            "16. Stock purchase date: 2021-08-28\n" +
            "16. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "17. Stock symbol: IBM\n" +
            "17. Stock name: International Business Machines Corp.\n" +
            "17. Stock quantity: 2.8579676674364896\n" +
            "17. Stock purchase date: 2021-09-27\n" +
            "17. Stock purchase price: $396.0\n" +
            "\n" +
            "18. Stock symbol: NFLX\n" +
            "18. Stock name: Netflix\n" +
            "18. Stock quantity: 1.0022948164146868\n" +
            "18. Stock purchase date: 2021-09-27\n" +
            "18. Stock purchase price: $594.0\n" +
            "\n" +
            "19. Stock symbol: IBM\n" +
            "19. Stock name: International Business Machines Corp.\n" +
            "19. Stock quantity: 3.1636973715746586\n" +
            "19. Stock purchase date: 2021-10-27\n" +
            "19. Stock purchase price: $396.0\n" +
            "\n" +
            "20. Stock symbol: NFLX\n" +
            "20. Stock name: Netflix\n" +
            "20. Stock quantity: 0.8960357207506185\n" +
            "20. Stock purchase date: 2021-10-27\n" +
            "20. Stock purchase price: $594.0\n" +
            "\n" +
            "21. Stock symbol: IBM\n" +
            "21. Stock name: International Business Machines Corp.\n" +
            "21. Stock quantity: 3.419393834729298\n" +
            "21. Stock purchase date: 2021-11-26\n" +
            "21. Stock purchase price: $396.0\n" +
            "\n" +
            "22. Stock symbol: NFLX\n" +
            "22. Stock name: Netflix\n" +
            "22. Stock quantity: 0.8923742563547864\n" +
            "22. Stock purchase date: 2021-11-26\n" +
            "22. Stock purchase price: $594.0\n" +
            "\n" +
            "23. Stock symbol: IBM\n" +
            "23. Stock name: International Business Machines Corp.\n" +
            "23. Stock quantity: 3.0086612976751255\n" +
            "23. Stock purchase date: 2021-12-26\n" +
            "23. Stock purchase price: $396.0\n" +
            "\n" +
            "24. Stock symbol: NFLX\n" +
            "24. Stock name: Netflix\n" +
            "24. Stock quantity: 0.9688152400835073\n" +
            "24. Stock purchase date: 2021-12-26\n" +
            "24. Stock purchase price: $594.0\n" +
            "\n" +
            "25. Stock symbol: IBM\n" +
            "25. Stock name: International Business Machines Corp.\n" +
            "25. Stock quantity: 2.909625275532697\n" +
            "25. Stock purchase date: 2022-01-25\n" +
            "25. Stock purchase price: $396.0\n" +
            "\n" +
            "26. Stock symbol: NFLX\n" +
            "26. Stock name: Netflix\n" +
            "26. Stock quantity: 1.6210905518257737\n" +
            "26. Stock purchase date: 2022-01-25\n" +
            "26. Stock purchase price: $594.0\n" +
            "\n" +
            "27. Stock symbol: IBM\n" +
            "27. Stock name: International Business Machines Corp.\n" +
            "27. Stock quantity: 3.2467000081987374\n" +
            "27. Stock purchase date: 2022-02-24\n" +
            "27. Stock purchase price: $396.0\n" +
            "\n" +
            "28. Stock symbol: NFLX\n" +
            "28. Stock name: Netflix\n" +
            "28. Stock quantity: 1.522959772325206\n" +
            "28. Stock purchase date: 2022-02-24\n" +
            "28. Stock purchase price: $594.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $8666.949999999999. " +
            "Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }

  @Test
  public void flexibleTestTotalValueAfterStrategyExistingPortfolioMultipleDates() {
    String inputString = "2\n2\nSchool\n2\nTSLA\n10\n2021-1-1\n5\nt\n5\n2021-2-2\n6\n10\n1" +
            "\n2\nIBM\n40\nnflx\n60\n1000\n5\n2021-03-01\n2022-03-01\n30\n3\n1\n4\n1" +
            "\n2021-2-5\n4\n1\n2021-7-7\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "2 Create new portfolio\n" +
            "Select one option from above\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter Stock symbol:\n" +
            "Enter weight: \n" +
            "Enter amount: \n" +
            "Enter commission fee: \n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Enter N/A for Ongoing Strategy.\n" +
            "Enter frequency in days: \n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the option of the portfolio to examine:\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "1. Stock symbol: TSLA\n" +
            "1. Stock name: Tesla Motors\n" +
            "1. Stock quantity: 10.0\n" +
            "1. Stock purchase date: 2021-01-01\n" +
            "1. Stock purchase price: $7297.7\n" +
            "\n" +
            "2. Stock symbol: T\n" +
            "2. Stock name: AT&T Inc.\n" +
            "2. Stock quantity: 5.0\n" +
            "2. Stock purchase date: 2021-02-02\n" +
            "2. Stock purchase price: $142.7\n" +
            "\n" +
            "3. Stock symbol: IBM\n" +
            "3. Stock name: International Business Machines Corp.\n" +
            "3. Stock quantity: 3.2797747225443104\n" +
            "3. Stock purchase date: 2021-03-01\n" +
            "3. Stock purchase price: $396.0\n" +
            "\n" +
            "4. Stock symbol: NFLX\n" +
            "4. Stock name: Netflix\n" +
            "4. Stock quantity: 1.0787447334011333\n" +
            "4. Stock purchase date: 2021-03-01\n" +
            "4. Stock purchase price: $594.0\n" +
            "\n" +
            "5. Stock symbol: IBM\n" +
            "5. Stock name: International Business Machines Corp.\n" +
            "5. Stock quantity: 2.971634398919406\n" +
            "5. Stock purchase date: 2021-03-31\n" +
            "5. Stock purchase price: $396.0\n" +
            "\n" +
            "6. Stock symbol: NFLX\n" +
            "6. Stock name: Netflix\n" +
            "6. Stock quantity: 1.1386726986926352\n" +
            "6. Stock purchase date: 2021-03-31\n" +
            "6. Stock purchase price: $594.0\n" +
            "\n" +
            "7. Stock symbol: IBM\n" +
            "7. Stock name: International Business Machines Corp.\n" +
            "7. Stock quantity: 2.791091062870031\n" +
            "7. Stock purchase date: 2021-04-30\n" +
            "7. Stock purchase price: $396.0\n" +
            "\n" +
            "8. Stock symbol: NFLX\n" +
            "8. Stock name: Netflix\n" +
            "8. Stock quantity: 1.1568348686388688\n" +
            "8. Stock purchase date: 2021-04-30\n" +
            "8. Stock purchase price: $594.0\n" +
            "\n" +
            "9. Stock symbol: IBM\n" +
            "9. Stock name: International Business Machines Corp.\n" +
            "9. Stock quantity: 2.746376309036688\n" +
            "9. Stock purchase date: 2021-05-30\n" +
            "9. Stock purchase price: $396.00000000000006\n" +
            "\n" +
            "10. Stock symbol: NFLX\n" +
            "10. Stock name: Netflix\n" +
            "10. Stock quantity: 1.1901899495070931\n" +
            "10. Stock purchase date: 2021-05-30\n" +
            "10. Stock purchase price: $594.0\n" +
            "\n" +
            "11. Stock symbol: IBM\n" +
            "11. Stock name: International Business Machines Corp.\n" +
            "11. Stock quantity: 2.7207145310889724\n" +
            "11. Stock purchase date: 2021-06-29\n" +
            "11. Stock purchase price: $395.99999999999994\n" +
            "\n" +
            "12. Stock symbol: NFLX\n" +
            "12. Stock name: Netflix\n" +
            "12. Stock quantity: 1.1134020618556701\n" +
            "12. Stock purchase date: 2021-06-29\n" +
            "12. Stock purchase price: $594.0\n" +
            "\n" +
            "13. Stock symbol: IBM\n" +
            "13. Stock name: International Business Machines Corp.\n" +
            "13. Stock quantity: 2.7901077996195305\n" +
            "13. Stock purchase date: 2021-07-29\n" +
            "13. Stock purchase price: $396.0\n" +
            "\n" +
            "14. Stock symbol: NFLX\n" +
            "14. Stock name: Netflix\n" +
            "14. Stock quantity: 1.1550802139037433\n" +
            "14. Stock purchase date: 2021-07-29\n" +
            "14. Stock purchase price: $594.0\n" +
            "\n" +
            "15. Stock symbol: IBM\n" +
            "15. Stock name: International Business Machines Corp.\n" +
            "15. Stock quantity: 2.849535871051306\n" +
            "15. Stock purchase date: 2021-08-28\n" +
            "15. Stock purchase price: $396.0\n" +
            "\n" +
            "16. Stock symbol: NFLX\n" +
            "16. Stock name: Netflix\n" +
            "16. Stock quantity: 1.0491363170723091\n" +
            "16. Stock purchase date: 2021-08-28\n" +
            "16. Stock purchase price: $593.9999999999999\n" +
            "\n" +
            "17. Stock symbol: IBM\n" +
            "17. Stock name: International Business Machines Corp.\n" +
            "17. Stock quantity: 2.8579676674364896\n" +
            "17. Stock purchase date: 2021-09-27\n" +
            "17. Stock purchase price: $396.0\n" +
            "\n" +
            "18. Stock symbol: NFLX\n" +
            "18. Stock name: Netflix\n" +
            "18. Stock quantity: 1.0022948164146868\n" +
            "18. Stock purchase date: 2021-09-27\n" +
            "18. Stock purchase price: $594.0\n" +
            "\n" +
            "19. Stock symbol: IBM\n" +
            "19. Stock name: International Business Machines Corp.\n" +
            "19. Stock quantity: 3.1636973715746586\n" +
            "19. Stock purchase date: 2021-10-27\n" +
            "19. Stock purchase price: $396.0\n" +
            "\n" +
            "20. Stock symbol: NFLX\n" +
            "20. Stock name: Netflix\n" +
            "20. Stock quantity: 0.8960357207506185\n" +
            "20. Stock purchase date: 2021-10-27\n" +
            "20. Stock purchase price: $594.0\n" +
            "\n" +
            "21. Stock symbol: IBM\n" +
            "21. Stock name: International Business Machines Corp.\n" +
            "21. Stock quantity: 3.419393834729298\n" +
            "21. Stock purchase date: 2021-11-26\n" +
            "21. Stock purchase price: $396.0\n" +
            "\n" +
            "22. Stock symbol: NFLX\n" +
            "22. Stock name: Netflix\n" +
            "22. Stock quantity: 0.8923742563547864\n" +
            "22. Stock purchase date: 2021-11-26\n" +
            "22. Stock purchase price: $594.0\n" +
            "\n" +
            "23. Stock symbol: IBM\n" +
            "23. Stock name: International Business Machines Corp.\n" +
            "23. Stock quantity: 3.0086612976751255\n" +
            "23. Stock purchase date: 2021-12-26\n" +
            "23. Stock purchase price: $396.0\n" +
            "\n" +
            "24. Stock symbol: NFLX\n" +
            "24. Stock name: Netflix\n" +
            "24. Stock quantity: 0.9688152400835073\n" +
            "24. Stock purchase date: 2021-12-26\n" +
            "24. Stock purchase price: $594.0\n" +
            "\n" +
            "25. Stock symbol: IBM\n" +
            "25. Stock name: International Business Machines Corp.\n" +
            "25. Stock quantity: 2.909625275532697\n" +
            "25. Stock purchase date: 2022-01-25\n" +
            "25. Stock purchase price: $396.0\n" +
            "\n" +
            "26. Stock symbol: NFLX\n" +
            "26. Stock name: Netflix\n" +
            "26. Stock quantity: 1.6210905518257737\n" +
            "26. Stock purchase date: 2022-01-25\n" +
            "26. Stock purchase price: $594.0\n" +
            "\n" +
            "27. Stock symbol: IBM\n" +
            "27. Stock name: International Business Machines Corp.\n" +
            "27. Stock quantity: 3.2467000081987374\n" +
            "27. Stock purchase date: 2022-02-24\n" +
            "27. Stock purchase price: $396.0\n" +
            "\n" +
            "28. Stock symbol: NFLX\n" +
            "28. Stock name: Netflix\n" +
            "28. Stock quantity: 1.522959772325206\n" +
            "28. Stock purchase date: 2022-02-24\n" +
            "28. Stock purchase price: $594.0\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $8666.949999999999. " +
            "Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 School\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $11662.978454550563. " +
            "Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }


  @Test
  public void flexibleTestTotalValueNormal() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n" +
            "2020-1-20\n4.7\nnflx\n11\n2021-02-10\n4\n1\n2022-10-10\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestTotalValueBeforeBuying() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n1\n2019-9-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }


  @Test
  public void flexibleTestTotalValueBetweenBuying2Stocks() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n4\n1\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestChartMonths() {
    String inputString = "2\n1\nyes\ninput2.xml\n9\n1\n2021-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Investments\n" +
            "2 College\n" +
            "3 Health\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Investments from 2021-2-11 to 2021-12-11\n" +
            "\n" +
            "Feb-2021: *****\n" +
            "Mar-2021: **********************\n" +
            "Apr-2021: ********************\n" +
            "May-2021: **************************\n" +
            "Jun-2021: ************************************\n" +
            "Jul-2021: ********************************\n" +
            "Aug-2021: ************************************\n" +
            "Sep-2021: ************************************************\n" +
            "Oct-2021: **************************************************\n" +
            "Nov-2021: **************************************\n" +
            "\n" +
            "Absolute Scale: * = $1238.49\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestChartDays() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2020-1-20\n4.7\nnflx\n11\n" +
            "2021-02-10\n5.5\n9\n1\n2021-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Insurance from 2021-2-11 to 2021-12-11\n" +
            "\n" +
            "Feb-2021: ******************************************\n" +
            "Mar-2021: ******************************************\n" +
            "Apr-2021: *******************************************\n" +
            "May-2021: *******************************************\n" +
            "Jun-2021: ********************************************\n" +
            "Jul-2021: *******************************************\n" +
            "Aug-2021: **********************************************\n" +
            "Sep-2021: ************************************************\n" +
            "Oct-2021: **************************************************\n" +
            "Nov-2021: ***********************************************\n" +
            "\n" +
            "Absolute Scale: * = $188.10199999999998\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestChartYears() {
    String inputString = "2\n1\nno\nKarthik\n1\nInsurance\n2\nIBM\n20\n2016-1-20\n4.7\nnflx\n11\n" +
            "2018-02-10\n5.5\n9\n1\n2015-2-11\n2021-12-11\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Insurance\n" +
            "Select one option from above\n" +
            "Enter Start Date in YYYY-MM-DD format:\n" +
            "Enter End Date in YYYY-MM-DD format:\n" +
            "Performance of portfolio Insurance from 2015-2-11 to 2021-12-11\n" +
            "\n" +
            "2015: \n" +
            "2016: ********************\n" +
            "2017: ******************\n" +
            "2018: *****************************\n" +
            "2019: ***********************************\n" +
            "2020: **********************************************\n" +
            "2021: **************************************************\n" +
            "\n" +
            "Absolute Scale: * = $173.952\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());

  }

  @Test
  public void flexibleTestStartWithOption2() {
    String inputString = "2\n2\nShrada\n1\nHealth\n1\nIBM\n20\n2019-1-20\n6\nnflx\n21\n" +
            "2022-1-1\n8\n1\n2022-11-17\n4\n1\n2022-11-17\n11\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    controller = new StockControllerImpl(in, view);
    controller.connect(new ModelHandlerImpl());
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Enter the name of the portfolio:\n" +
            "Enter number of Investments to be added to the portfolio:\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
            "Enter Stock symbol:\n" +
            "Stock not supported. Choose a stock from SupportedListOfStocks.csv file.\n" +
            "\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Shrada\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Cost Basis is: $2482.3999999999996\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "1 Shrada\n" +
            "Select one option from above\n" +
            "Enter the date in YYYY-MM-DD format:\n" +
            "Total Value of the portfolio is $2921.8. Calculated using AlphaVantage API.\n" +
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
            "10. Create a Strategy\n" +
            "11. Quit\n" +
            "\n" +
            "Bye Bye.. \n" +
            "\n", out.toString());
  }


}