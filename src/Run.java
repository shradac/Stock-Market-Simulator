import controller.Controller;
import controller.StockController;
import controller.StockControllerImpl;
import model.ModelHandler;
import model.ModelHandlerImpl;
import view.JFrameView;
import view.JView;
import view.StockView;
import view.StockViewImpl;

/**
 * The main class which runs the application.
 * The application is designed using MVC pattern (Model View Controller).
 * The controller takes input from the user and gives it to the model for calculation.
 * The controller uses the view to display messages to the user.
 */
public class Run {
  /**
   * The main function.
   * Model is an object of ModelHandler. It is the only link between all the model class and
   * the controller.
   * controller is an object of stockController which controls the application. It takes input
   * from the user and gives it to the model for calculation. It uses the view to display
   * messages to the user.
   * view is an object of StockView which contains all the methods to display the right
   * message at the right time.
   *
   * @param args args if any, given when the application is run
   */
  public static void main(String[] args) {
    ModelHandler model = new ModelHandlerImpl();

    if (args.length == 1) {
      if (args[0].matches("[Tt][Ee][Xx][Tt][Uu][Ii]")) {
        StockView textView = new StockViewImpl(System.out);
        StockController textuiController = new StockControllerImpl(System.in, textView);
        textuiController.connect(model);
      } else if (args[0].matches("[Gg][Uu][Ii]")) {
        Controller guiController = new Controller(model);
        JView guiView = new JFrameView();
        guiController.setView(guiView);
      }
    } else {
      System.out.println("\nNo args found.\nRun file with args: \ntextui - to run " +
              "text interface or \ngui - to run graphical user interface." +
              "\nHint: java -jar A6Stock.jar gui\n");
    }
  }
}
