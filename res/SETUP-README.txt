HOW TO RUN PROGRAM FROM THE JAR FILE

1. Open the command prompt or terminal and change directory to the location in which the jar file
exists.
2. Make sure to keep the SupportedListOfStocks.csv file and the defaultPanel.jpg in the same
 location as jar file.
3. Enter the command 'java -jar A6_Stock.jar'. See the below options for commands.
*** Run file with args:
** textui - to run text interface or
** gui - to run graphical user interface.
** Hint: java -jar A6Stock.jar gui

* If the user enters "java -jar A6Stock.jar gui" command then the GUI shows up.
* If the user enters "java -jar A6Stock.jar textui" command then the TextUI shows up.

TO RUN GUI FROM THE JAR FILE

1. Open the command prompt or terminal and change directory to the location in which the jar
file exists.
2. Make sure to keep the SupportedListOfStocks.csv file and the defaultPanel.jpg in the same
 location as jar file.
3. Enter the command 'java -jar A6_Stock.jar gui'.

* The GUI shows all the options in the menu to the user and the user can click on the option
they wish to and click the submit button.
* When each button is clicked and pressed submit, the corresponding form is displayed on the
right side of the screen.
* The user need to fill up all the details in the form.
* In the form, if the user enters any invalid details, they receive a pop-up message based on
which field was invalid.



** Two sample xml files has been included in the res folder where
1. InflexiblePortfolioInput.xml- which contains a portfolio with 3 stocks and another portfolio
with 2 stocks.
2. FlexiblePortfolioInput.xml- which contains a portfolio with stocks of 3 different companies
 bought on 3 different dates.
3. FlexiblePortfolioWithStrategy.xml ----------------------------------------------------------------------------------------------


LIST OF LIBRARIES USED
* Our application does not use any external library.

LIST OF STOCKS SUPPORTED
* Check for the SupportedListOfStocks.csv file.
* Stock data supported after the date "1995-01-02".
* Any future date along with present date is invalid since stock data is not available.
* If any stock did not exist on any desired date the value of the stock will be 0.


* This application is an end to end model-view-controller for the stock data.
* The application provides several features to the user.
* Our application enables the user to create inflexible and flexible portfolios and maintain their stock data.
* A portfolio is simply a collection of stocks of different companies. An investor may create multiple
 portfolios and track them:
 a retirement portfolio, a college-savings portfolio, health savings portfolio, etc.
* Each stock has a unique symbol known as the stock or ticker symbol for a specific company.
* For the application to be user-friendly and handy, we create a menu for the user.
* Our application supports 2 types of portfolios:
1.Flexible Portfolio
2.Inflexible Portfolio

* In a flexible portfolio, the user can modify the portfolio and perform several operations.
A flexible portfolio provides the user with several features such as buying stocks, selling stocks
from a portfolio, getting the cost basis of a portfolio(i.e. the total amount of money invested
in a portfolio) by a specific date. This would include all the purchases made in that portfolio
till that date), viewing the performance of a portfolio between different time durations,
creating a strategy for dollar-cost averaging. The strategy can be one time strategy,
start-to-finish strategy or ongoing strategy.

* An inflexible portfolio provides the user with several features such as creating a portfolio with
number of desired stocks, examining the portfolio, getting the total value of a particular portfolio,
saving the data and quit. Here the user cannot modify the portfolio once created.








