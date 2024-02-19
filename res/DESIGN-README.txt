
DESIGN

Our application is based on the MVC (Model View Controller) architecture pattern.
Model contains all the calculation, User, Portfolio, Stock classes, Reader parser, Writer parser,
Validation.
View contains StockView which contains functions which prints text asking for data from the user or
reporting something to the user.
Controller controls both the model and the view. The controller takes input from the user and gives
it to the model for calculation. The controller uses the view to display messages to the user.

*** We have added a new view to our application that uses a graphical user interface(GUI).

Our GUI has the following features:

It enables the user to create a new flexible portfolio, buy/sell stocks by specifying the number of
 shares , and date,
query the cost basis and value of a flexible portfolio at a certain date.

The user can save and retrieve flexible portfolios from files

Also the user can invest a specific amount in an existing flexible portfolio on a specific date by
specifying the weights of how that money should be
invested in each stock inside that portfolio.

The ability to create a portfolio using dollar-cost averaging as specified above, and query cost
 basis and value of such a portfolio at a specific date.


For implementing the GUI we have made the following design changes:
*** Created the "Features" interface in the Controller.
*** Created a Controller class that implements the "Features" interface.
*** Created a new "JView" interface and its implementation "JFrameView" which contains methods to
draw the GUI.
*** For the user to easily select the dates we have added the "DatePicker" class.
*** For the user to view the bar graph of the portfolio we have created the "HistogramPanel" class.
*** We have added some methods in ModelHandler interface to support implementing Strategy and GUI.
*** We have added strategyList field to the User class which contains the list of strategies.
*** For the user to perform the Dollar-cost averaging, we have created a "Strategy" interface in
the model and "StrategyImpl" class.
*** Methods added in ModelHandler:
- addStrategy() - adds a strategy to the strategyList of the user with the below fields.
- addStockToCache() - adds the stock symbol as soon as it is entered by the user to the cache.
                        This has been done to reduce the number of API calls.
- getStockList() - getter method for stockList in the modelHandler to display the stocks entered
by the user in the gui.



In our program - ModelHandler class is the class that links the Model to the Controller.
The ModelHandler manages all the other models such as User, Portfolio, Stock.

1. Builder Pattern - Lets us construct complex objects step by step.
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

2. Visitor Pattern
We have implemented Visitor Pattern for Transaction and Strategy classes.
Instead of editing the Portfolio class to support Transactions and dollar-cost-averaging, we place
it into separate classes called Transaction and Strategy. Once we create objects of Transaction and
Strategy, we apply it on the User object in ModelHandlerImpl class.

3. Observer Pattern
We have implemented Observer Pattern for action listeners in the GUI.
The listener interface(Features interface) is for receiving action events.
The class(Controller class) that is interested in processing an action event implements this
interface, and the object created with that class is registered with a component(Submit button),
using the components action listener method. when the action event occurs, that object
actionPerformed method is invoked.

In this iteration of the assignment:

*** We have implemented Flexible Portfolios where we can buy and sell stocks from the portfolios.

*** To implement this, we have created an interface which extends the Portfolio (Inflexible).
Now, FlexiblePortfolioImpl class implements the FlexiblePortfolio interface and extends the
PortfolioImpl class (Inflexible).

*** FlexiblePortfolio interface consists of two methods addStocks() and removeStocks().
These methods have been defined in the FlexiblePortfolioImpl class.

*** We have used Builder Pattern to create FlexiblePortfolioImpl object.

*** To accommodate the above features, we begin the program by asking the user if they want to
work with inflexible portfolio or flexible portfolio.

*** We have created Transaction interface which represents a transaction (a buy or a sell).
We have implemented this interface in the TransactionImpl class.
Each transaction object consists of portfolioName, stockSymbol, quantity, purchase/selling date,
type of transaction(buy or sell), price, commission fee for the transaction.

*** Our application is compatible with both the AlphaVantage API and the YahooFinance API.
To implement these, we have created an interface "ApiCalling" and AlphaVantageAPI and
YahooFinanceAPI are classes which implement the ApiCalling interface.

*** We also use Lazy Execution for getting the cost basis, total value and performance chart
of a portfolio.

*** In the view, we have created an interface Draw which draws an object. To implement drawing a
chart, ChartDrawImpl class implements the Draw interface.


*** Once an API call has been made for a particular stock, our application will store the data in a
hashMap. The reason being, once again if the user enters the same stock, the API call
will not be made instead the data will be fetched from the hashMap.


The user menu provides the user with the option to create an inflexible or flexible portfolio.
Based on which type of portfolio the user wants to create, the user interface provides a menu
accordingly.

If the user wants to work on a inflexible portfolio then the below holds true:

1. The user interface provides a menu of options to the user. It is a text based interface.
2. Option 1 -
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

3. Option 2 - Add a portfolio
	User can add a portfolio by providing the name of portfolio, number of stocks (Investments) in
	the portfolio, details of each stock. A portfolio is identified by the name of the portfolio.
	Therefore, it should be unique. User will not be allowed to create two portfolios with same
	names. They can add any number of stocks into each portfolio. While providing the stock details,
	the first data required is the stock symbol (ticker symbol). Once its provided, the program
	checks if the symbol is valid and the program supports that stock. If yes, it continues to take
	other inputs. If no, it asks to enter a valid symbol. Valid and supported stock symbols can be
	found in SupportedListOfStocks.csv file. The program won't let the user create a stock without
	a stock symbol.

4 Option 3 - Examine a portfolio
    User can examine a portfolio by choosing the index from the list of portfolios displayed to
    the user. It displays all the stocks and its details in the portfolio.
    Details include stock symbol (ticker symbol), stock name, quantity, purchase date, purchase
    price of each stock in the portfolio.

5 Option 4 - Get the total value of a portfolio
    User can determine the total value of a portfolio by choosing the index from the list of
    portfolios displayed to the user. User much also provide the desired date for which total value
    must be calculated. Once provided with these details, the model will call the api to get price
    of the stock on that date, multiply with the quantity and add it to the total value of the
    portfolio which is then displayed on the user interface. Date provided must be after 1995/01/01.
    It should also not be a future date or the present date. These values are considered invalid.
    The value for a portfolio before the date of its first purchase would be 0, since each stock in
    the portfolio now was purchased at a specific point in time. If the date is of a Weekend
    (Saturday or Sunday) then, the stock price of Friday is used to calculate the total value.

6 Option 5 - Save Data
    User can wish to save the data they added in the application to an XML file which can then be
    used to load the data to the application. If the user hasn't provided userName when calling this
    option, then the program requests for username as it is needed to parse an XML file
    successfully.
    We have used XML because XML offers the capability to display data because it is a markup
    language.
    It also supports various data types such as number, text, images, charts, graphs, etc which can
    be used in future versions of the application. Also, JDK provides us help in parsing XML.


7 Option 6 - Buy Stock
    User can buy stock of their choice of a specific stock on a particular date for a certain price.
    The user has to enter a commission fee between 2 and 30 dollars per transaction.
    Our application provides the user with the option of entering the stock commission fee.
    Every transaction comes with a commission fee. The user should enter the fee manually within a
    range between 2 and 30 dollars.

8 Option 7 - Sell Stock
    User can sell stock from a specific portfolio on a certain date. The selling too has a
    fee since it is a transaction and will require the user to enter a fee.
    Our application provides the user with the option of entering the stock commission fee.
    Every transaction comes with a commission fee. The user should enter the fee manually within a
    range between 2 and 30 dollars.

9 Option 8 - Cost Basis
    Displays the total amount of money invested in a portfolio until a specific date.
    This would include all the purchases made in that portfolio till that date and the commission
    paid for these transactions.

10 Option 9 - Performance Chart
    Provides the user with a chart to view the performance of a particular portfolio over a time
    span. A gauge of the portfolio's value at that timestamp is shown by the number of asterisks on
    each line. The value is computed at the end of that day if the timestamps are dates.
    The value is computed on the final working day of the month if the timestamps are in the form
    of months. The value is computed on the final business day of the year, if the timestamp is in
    years.


11 Option 10 - Quit the program.





