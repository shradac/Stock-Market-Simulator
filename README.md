# Stock-Market-Simulator

## This is a stock market simulator application that allows users to create portfolios, buy/sell stocks, and track the performance of their portfolios over time.

## Features

- Create and manage portfolios
- Add/remove stocks
- Flexible (can buy/sell stocks) and inflexible portfolios
- Save/load portfolios to XML files
- Get stock quotes and information using AlphaVantage and Yahoo Finance APIs
- Buy and sell stocks on specific dates
- View cost basis and total value of portfolio on a specific date
- View performance chart showing value of portfolio over time
- Customizable stock transaction fees
- Validate stock symbols against list of supported stocks

## Architecture
- The application follows the MVC (Model-View-Controller) architecture pattern:
- Model: Contains the business logic - classes for User, Portfolio, Stock, parsers, validation, etc.
- View: User interface (text-based or GUI) that displays messages and gets input from user
- Controller: Handles communication between Model and View

## Key Design Patterns

### Builder Pattern - Lets us construct complex objects step by step.
We have implemented Builder Pattern for User, Portfolio, Stock classes. This lets us create these
objects step by step.

First we create an object of stock by providing details one by one as the user provides.
Then the stock object is added one by one to the stockList which  is then added to portfolio.
Similarly multiple portfolios gets created step by step and gets added
to the portfolioList which is added to the User.

We use builder pattern because, user provides details step by step and creating objects is
prone to make errors with multiple data. With builder pattern we can assign fields one at a time by
specifying which field we want to assign it to, which reduces the error of creating an object
with a constructor with all the fields at once.

### Visitor Pattern
We have implemented Visitor Pattern for Transaction and Strategy classes.
Instead of editing the Portfolio class to support Transactions and dollar-cost-averaging, we place
it into separate classes called Transaction and Strategy. Once we create objects of Transaction and
Strategy, we apply it on the User object in ModelHandlerImpl class.

### Observer Pattern
We have implemented Observer Pattern for action listeners in the GUI.
The listener interface(Features interface) is for receiving action events.
The class(Controller class) that is interested in processing an action event implements this
interface, and the object created with that class is registered with a component(Submit button),
using the components action listener method. when the action event occurs, that object
actionPerformed method is invoked.


## Workflow

- If the user wants to work on a  portfolio then the below holds true:
- The user interface provides a menu of options to the user. It is a text based interface.

  
### Option 1 -
	The user can either enter the data manually or upload an XML file. This feature enables the user
	to type 'yes' or 'no'.The 'yes' or 'no' answer can be provided of any case(upper or lower) such
	as 'YES', 'Yes', 'yes', 'NO','No','no', so on.
	If yes - then the user has to give only the location of the file. The file should be of type
	(.xml).
		The parser will read the data from the file.
		No need to type the user name when given 'Yes' as parser will extract it from the file since
		userName is part of the xml file.
	If no- then the user has to enter the details manually. The user has to enter the userName when
	    they chose 'no'. A User object cannot be created if userName has not been provided. At any
	    stage if the user wants to save the data, then they will have to provide userName either at
	    option 1 - Enter data manually or at option 5 - Save data.

 ### Option 2 - Add a portfolio
	User can add a portfolio by providing the name of portfolio, number of stocks (Investments) in
	the portfolio, details of each stock. A portfolio is identified by the name of the portfolio.
	Therefore, it should be unique. User will not be allowed to create two portfolios with same
	names. They can add any number of stocks into each portfolio. While providing the stock details,
	the first data required is the stock symbol (ticker symbol). Once its provided, the program
	checks if the symbol is valid and the program supports that stock. If yes, it continues to take
	other inputs. If no, it asks to enter a valid symbol. Valid and supported stock symbols can be
	found in SupportedListOfStocks.csv file. The program won't let the user create a stock without
	a stock symbol.

### Option 3 - Examine a portfolio
    User can examine a portfolio by choosing the index from the list of portfolios displayed to
    the user. It displays all the stocks and its details in the portfolio.
    Details include stock symbol (ticker symbol), stock name, quantity, purchase date, purchase
    price of each stock in the portfolio.

### Option 4 - Get the total value of a portfolio
    User can determine the total value of a portfolio by choosing the index from the list of
    portfolios displayed to the user. User much also provide the desired date for which total value
    must be calculated. Once provided with these details, the model will call the api to get price
    of the stock on that date, multiply with the quantity and add it to the total value of the
    portfolio which is then displayed on the user interface. Date provided must be after 1995/01/01.
    It should also not be a future date or the present date. These values are considered invalid.
    The value for a portfolio before the date of its first purchase would be 0, since each stock in
    the portfolio now was purchased at a specific point in time. If the date is of a Weekend
    (Saturday or Sunday) then, the stock price of Friday is used to calculate the total value.

### Option 5 - Save Data
    User can wish to save the data they added in the application to an XML file which can then be
    used to load the data to the application. If the user hasn't provided userName when calling this
    option, then the program requests for username as it is needed to parse an XML file
    successfully.
    We have used XML because XML offers the capability to display data because it is a markup
    language.
    It also supports various data types such as number, text, images, charts, graphs, etc which can
    be used in future versions of the application. Also, JDK provides us help in parsing XML.


### Option 6 - Buy Stock
    User can buy stock of their choice of a specific stock on a particular date for a certain price.
    The user has to enter a commission fee between 2 and 30 dollars per transaction.
    Our application provides the user with the option of entering the stock commission fee.
    Every transaction comes with a commission fee. The user should enter the fee manually within a
    range between 2 and 30 dollars.

### Option 7 - Sell Stock
    User can sell stock from a specific portfolio on a certain date. The selling too has a
    fee since it is a transaction and will require the user to enter a fee.
    Our application provides the user with the option of entering the stock commission fee.
    Every transaction comes with a commission fee. The user should enter the fee manually within a
    range between 2 and 30 dollars.

### Option 8 - Cost Basis
    Displays the total amount of money invested in a portfolio until a specific date.
    This would include all the purchases made in that portfolio till that date and the commission
    paid for these transactions.

### Option 9 - Performance Chart
    Provides the user with a chart to view the performance of a particular portfolio over a time
    span. A gauge of the portfolio's value at that timestamp is shown by the number of asterisks on
    each line. The value is computed at the end of that day if the timestamps are dates.
    The value is computed on the final working day of the month if the timestamps are in the form
    of months. The value is computed on the final business day of the year, if the timestamp is in
    years.


### Option 10 - Quit the program.


## Instructions
To run the application:

- Clone the repository
- Import the project into your IDE (e.g. Eclipse)
- Run the main class to launch the application
- Follow menu prompts to create portfolio, buy/sell stocks etc.

  
