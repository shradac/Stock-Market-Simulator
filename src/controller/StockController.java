package controller;

import model.ModelHandler;

/**
 * This interface represents the stock Controller. It is the Controller of the application.
 * The stockController makes sure that the flow of the data is in the right sequence and
 * enables communication between the components. It takes input from the user and sends it
 * to the model and calls methods from the view to display the output to the user.
 */
public interface StockController {

  /**
   * It runs the controller. The connect method contains several options for the operations
   * to be performed on the stock data such as:
   * 1. Enter data as a file: Yes/No
   * 2. Add a portfolio
   * 3. Examine a portfolio
   * 4. Get the total value of a portfolio
   * 5. Save Data
   * 6. Quit
   * It controls the application for each option above.
   *
   * @param model - the class that is the only link between controller and model
   */
  void connect(ModelHandler model);

}
