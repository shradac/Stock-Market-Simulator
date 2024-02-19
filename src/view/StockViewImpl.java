package view;

import java.io.PrintStream;
import java.util.List;

import model.Portfolio;
import model.Stock;

/**
 * This class implements the stockView interface.
 * It defines all the messages to be printed to the view.
 */
public class StockViewImpl implements StockView {
  PrintStream out;

  public StockViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void addPortfolioGetName() {
    out.println("Enter the name of the portfolio:");
  }

  @Override
  public void addPortfolioGetStockSymbol() {
    out.println("Enter Stock symbol:");
  }

  @Override
  public void addPortfolioGetQuantity() {
    out.println("Enter quantity:");
  }

  @Override
  public void getDateFor(String task) {
    out.println("Enter " + task + " Date in YYYY-MM-DD format:");
  }

  @Override
  public void endDateNotAvailable() {
    out.println("Enter N/A for Ongoing Strategy.");
  }

  @Override
  public void getNumberOfStocks() {
    out.println("Enter number of Investments to be added to the portfolio:");
  }

  @Override
  public void examinePortfolio() {
    out.println("Enter the option of the portfolio to examine:");
  }

  @Override
  public void totalValueGetDate() {
    out.println("Enter the date in YYYY-MM-DD format:");

  }

  @Override
  public void showOptions() {
    out.println("Menu: ");
    out.println("1. Enter data as a file: Yes/No");
    out.println("2. Add a portfolio");
    out.println("3. Examine a portfolio");
    out.println("4. Get the total value of a portfolio");
    out.println("5. Save Data");
    out.println("6. Quit");
    out.println();
  }

  @Override
  public void showFlexibleOptions() {
    out.println("Menu: ");
    out.println("1. Enter data as a file: Yes/No");
    out.println("2. Add a portfolio");
    out.println("3. Examine a portfolio");
    out.println("4. Get the total value of a portfolio");
    out.println("5. Save Data");
    out.println("6. Buy stock");
    out.println("7. Sell stock");
    out.println("8. Get the cost basis for a portfolio");
    out.println("9. Get the Performance chart for a portfolio");
    out.println("10. Create a Strategy");
    out.println("11. Quit");
    out.println();
  }

  @Override
  public void showInvalidInputError() {
    out.println("Invalid input. Please try again.");
  }

  @Override
  public void input() {
    out.println("Type 'Yes': Provide file location");
    out.println("Type 'No': Enter data manually");
  }

  @Override
  public void inputAsFile() {
    out.println("Provide file location: ");
  }

  @Override
  public void inputManuallyGetUserName() {
    out.println("Enter user name: ");
  }

  @Override
  public void inputManuallyGetNumberOfPortfolios() {
    out.println("Enter the number of portfolios: ");
  }

  @Override
  public void printPortfolio(Portfolio p) {
    List<Stock> stockList = p.getStockList();
    for (int i = 0; i < stockList.size(); i++) {
      Stock one = stockList.get(i);
      String index = String.valueOf(i + 1);
      out.println(index + ". Stock symbol: " + one.getStockSymbol());
      out.println(index + ". Stock name: " + one.getStockName());
      out.println(index + ". Stock quantity: " + one.getQuantity());
      out.println(index + ". Stock purchase date: " + one.getPurchaseDate());
      out.println(index + ". Stock purchase price: $" + one.getPurchasePrice());
      out.println();
    }
  }

  @Override
  public void chooseFromPortfolioList(List<Portfolio> portfolioList) {
    if (portfolioList == null) {
      return;
    }
    for (int i = 0; i < portfolioList.size(); i++) {
      String index = String.valueOf(i + 1);
      out.println(index + " " + portfolioList.get(i).getName());
    }
  }

  @Override
  public void selectFromAboveMessage() {
    out.println("Select one option from above");
  }

  @Override
  public void showTotalValue(double totalValue, String apiName) {
    out.println("Total Value of the portfolio is $" + totalValue + ". Calculated using "
            + apiName + ".");
    out.println();
  }

  @Override
  public void duplicatePortfolioNameShowError() {
    out.println("Given portfolio name already exists. Portfolio name should be unique." +
            " Please try again.");
    out.println();
  }

  @Override
  public void stockNoSupportShowError() {
    out.println("Stock not supported. Choose a stock from SupportedListOfStocks.csv file.");
    out.println();
  }

  @Override
  public void saveData() {
    out.println("Data saved to  SavedPortfolios.xml file.");
    out.println();
  }

  @Override
  public void quit() {
    out.println("Bye Bye.. ");
    out.println();
  }

  @Override
  public void fileNotFound() {
    out.println("File not found. Try again.");
    out.println();
  }

  @Override
  public void fileValidationShowError() {
    out.println("File Validation failed. Check if the content is correct.");
    out.println();
  }

  @Override
  public void invalidNumberShowError() {
    out.println("Invalid number. Try again.");
    out.println();
  }

  @Override
  public void showPortfolioOptions() {
    out.println("Menu:");
    out.println("1. Work on Inflexible Portfolio");
    out.println("2. Work on Flexible Portfolio");
    out.println();
  }

  @Override
  public void cantSell() {
    out.println("Cannot sell. Have less quantity");
    out.println();
  }

  @Override
  public void showCostBasis(double cost) {
    out.println("Cost Basis is: $" + cost);
    out.println();
  }

  @Override
  public void drawChart(Draw chartDrawer) {
    chartDrawer.setOutputStream(out);
    chartDrawer.draw();
  }

  @Override
  public void stockAdded() {
    out.println("Stock added to portfolio.");
    out.println();
  }

  @Override
  public void addPortfolioGetCommission() {
    out.println("Enter commission fee: ");
  }

  @Override
  public void getStockWeight() {
    out.println("Enter weight: ");
  }

  @Override
  public void invalidCommissionShowError() {
    out.println("Invalid commission fee. Please enter between $2 and $30.");
    out.println();
  }

  @Override
  public void getAmount() {
    out.println("Enter amount: ");
  }

  @Override
  public void getFrequency() {
    out.println("Enter frequency in days: ");
  }

  @Override
  public void stockSymbolExistsShowError() {
    out.println("Stock symbol already exists. Enter a different stock.");
  }

  @Override
  public void showInvalidWeightError() {
    out.println("Invalid weights. Didn't add to 100%. Try again.");
  }

  @Override
  public void createNewPortfolioThroughStrategy(int index) {
    out.println(index + " Create new portfolio");
  }

  @Override
  public void invalidWeightShowError() {
    out.println("Invalid weight. Please enter weight of stock in percentage(%).");
    out.println();
  }

  @Override
  public void invalidDateShowError() {
    out.println("Date is invalid");
    out.println();
  }

  @Override
  public void portfolioCreated() {
    out.println("Portfolio successfully created.");
    out.println();
  }

  @Override
  public void userNameShowError() {
    out.println("Username length should be less thn 30 characters.");
    out.println();
  }


}
