package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import javax.swing.BorderFactory;
import javax.swing.SpringLayout;


import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

import controller.Features;
import model.Portfolio;
import model.Stock;

/**
 * GUI View.
 * Implementation of JView Interface.
 * It defines all the methods to draw layout outs, buttons, text fields on
 * the view(screen). It contains various layouts such as Border Layout, Box Layout,
 * Spring Layout, etc.
 */
public class JFrameView extends JFrame implements JView {

  private final JPanel mainPanel;
  private final JButton saveButton;
  private final JButton sendDataToTotalValueFunction;
  private final JButton sendDataToExamineFunction;
  private final JButton sendDataToEnterDataFunction;
  private final JButton sendDataToBuyStockFunction;
  private final JButton sendDataToSellStockFunction;
  private final JButton sendDataToCostBasisFunction;
  private final JButton sendDataToAddStockFunction;
  private final JButton sendDataToGetPerformanceOfAPortfolioFunction;
  private final JButton addToStockMap;
  private final JButton sendDataToCreateStrategyFunction;
  private final JButton sendDataToAddPortfolioFunction;
  private final JButton fileOpenButton;
  private final JComboBox<String> dropDownMenuPortfolioList;
  private final ButtonGroup group = new ButtonGroup();
  String submitButtonAction = "0";
  private JButton menuOptionSubmitButton;
  private JRadioButton enterDataFileButton;
  private JRadioButton addPortfolioButton;
  private JRadioButton examinePortfolioButton;
  private JRadioButton getTotalValueButton;
  private JRadioButton buyStockButton;
  private JRadioButton sellStockButton;
  private JRadioButton getCostBasisButton;
  private JRadioButton getPerformanceOfAPortfolioButton;
  private JRadioButton createStrategyButton;
  private JButton quitButton;
  private JButton saveDataButton;
  private JTextField inputDate;
  private JTextField inputStartDate;
  private JTextField inputEndDate;
  private JTextField inputLocation;
  private JTextField inputStockSymbol;
  private JTextField inputStockQuantity;
  private JTextField inputStockWeight;
  private JTextField inputCommissionFee;
  private JTextField inputPortfolioName;
  private JTextField inputUserName;
  private JTextField inputNumberOfInvestments;
  private JTextField inputStrategyFrequency;
  private JTextField inputStockAmount;
  private Integer editButton = null;
  private String fileSaveLocation;

  /**
   * Constructor for the JFrameView class.
   * It sets the title, size, location, mainPanel for the screen.
   */
  public JFrameView() {

    setTitle("STOCKS");
    setSize(1000, 600);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(new Dimension(1050, 600));

    add(mainPanel);
    createMenuPanel();
    createOperationPanel();

    sendDataToTotalValueFunction = new JButton("Submit");
    sendDataToExamineFunction = new JButton("Submit");
    sendDataToEnterDataFunction = new JButton("Submit");
    sendDataToBuyStockFunction = new JButton("Submit");
    sendDataToSellStockFunction = new JButton("Submit");
    sendDataToCostBasisFunction = new JButton("Submit");
    sendDataToAddStockFunction = new JButton("Add Stock");
    addToStockMap = new JButton("Add Stock");
    sendDataToAddPortfolioFunction = new JButton("Create Portfolio");
    sendDataToCreateStrategyFunction = new JButton("Create Strategy");
    fileOpenButton = new JButton("Choose a File");
    sendDataToGetPerformanceOfAPortfolioFunction = new JButton("Generate Chart");
    saveButton = new JButton("Save");
    dropDownMenuPortfolioList = new JComboBox<>();
  }

  private void createMenuPanel() {

    JPanel menuPanel = new JPanel(new BorderLayout());
    JPanel menuOptionPanel = new JPanel();
    menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
    menuOptionPanel.setLayout(new BoxLayout(menuOptionPanel, BoxLayout.PAGE_AXIS));
    menuOptionPanel.setSize(new Dimension(350, 600));

    enterDataFileButton = new JRadioButton("Enter Data as File");
    addPortfolioButton = new JRadioButton("Add Portfolio");
    examinePortfolioButton = new JRadioButton("Examine a portfolio");
    getTotalValueButton = new JRadioButton("Get the total value of a portfolio");
    saveDataButton = new JButton("Save Data");
    buyStockButton = new JRadioButton("Buy Stock");
    sellStockButton = new JRadioButton("Sell Stock");
    getCostBasisButton = new JRadioButton("Get the cost basis for a portfolio");
    getPerformanceOfAPortfolioButton = new JRadioButton("Get the Performance " +
            "chart for a portfolio");
    createStrategyButton = new JRadioButton("Create a Strategy");

    quitButton = new JButton("Quit");
    groupButtons();
    addButtonsToMenu(menuOptionPanel);
    menuOptionSubmitButton = new JButton("Submit");
    menuOptionPanel.add(Box.createVerticalStrut(20));
    menuOptionPanel.add(menuOptionSubmitButton);
    menuPanel.add(menuOptionPanel, BorderLayout.NORTH);
    JPanel menuSouthPanel = new JPanel();
    JPanel savePanel = new JPanel();
    savePanel.add(saveDataButton);
    menuSouthPanel.add(quitButton);
    menuSouthPanel.add(Box.createHorizontalStrut(80));
    menuSouthPanel.add(savePanel);
    menuPanel.add(menuSouthPanel, BorderLayout.SOUTH);
    mainPanel.add(menuPanel, BorderLayout.WEST);
  }

  private void createOperationPanel() {
    JPanel operationPanel = new JPanel();
    operationPanel.setBorder(BorderFactory.createTitledBorder("Welcome to Stocks." +
            " The real earning starts here!"));
    operationPanel.setPreferredSize(new Dimension(750, 600));

    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon("defaultPanel.jpg"));
    JPanel imagePanel = new JPanel();
    imagePanel.add(imageLabel);
    imagePanel.setPreferredSize(new Dimension(730, 570));
    operationPanel.add(imagePanel);

    mainPanel.add(operationPanel, BorderLayout.EAST);
    pack();
    setVisible(true);
  }

  private JPanel replaceOperationPanel(JRadioButton button) {
    JPanel panel = new JPanel();
    JLabel label = new JLabel(button.getText());
    label.setFont(new Font("Serif", Font.PLAIN, 20));
    panel.setPreferredSize(new Dimension(750, 600));
    panel.setLayout(new BorderLayout());
    JPanel flow = new JPanel();
    flow.add(label);
    panel.add(flow, BorderLayout.NORTH);
    BorderLayout layout = (BorderLayout) mainPanel.getLayout();
    mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
    mainPanel.add(panel, BorderLayout.EAST);
    pack();
    return panel;
  }

  @Override
  public void addFlexiblePortfolio(List<Stock> stockList, String setPortfolioName,
                                   String setNumberOfInvestments, String setStockSymbol,
                                   String setStockQuantity, String setStockPurchaseDate,
                                   String setCommissionFee) {
    JPanel addPortfolioPanel = replaceOperationPanel(addPortfolioButton);
    JPanel panel = new JPanel(new SpringLayout());
    JPanel panel2 = new JPanel();

    inputPortfolioName = new JTextField(8);
    if (setPortfolioName != null) {
      inputPortfolioName.setText(setPortfolioName);
      inputPortfolioName.setEnabled(false);
    }
    JPanel portfolioNamePanel = getPanel("Enter Portfolio Name: ", inputPortfolioName, "School");

    inputNumberOfInvestments = new JTextField(8);
    if (setPortfolioName != null) {
      inputNumberOfInvestments.setText(setNumberOfInvestments);
      inputNumberOfInvestments.setEnabled(false);
    }
    JPanel numberOfInvestmentsPanel = getPanel("Enter number of Investments: ",
            inputNumberOfInvestments, "2");

    inputStockSymbol = new JTextField(8);
    if (setStockSymbol != null) {
      inputStockSymbol.setText(setStockSymbol);
    }
    JPanel stockSymbolPanel = getPanel("Enter Stock Symbol: ", inputStockSymbol, "GOOG");

    inputStockQuantity = new JTextField(8);
    if (setStockSymbol != null) {
      inputStockQuantity.setText(setStockQuantity);
    }
    JPanel stockQuantityPanel = getPanel("Enter Stock Quantity: ", inputStockQuantity, "25");

    inputCommissionFee = new JTextField(8);
    if (setStockSymbol != null) {
      inputCommissionFee.setText(setCommissionFee);
    }
    JPanel stockCommissionPanel = getPanel("Enter Commission Fee:", inputCommissionFee, "4");

    inputDate = new JTextField(8);
    JPanel datePanel = getDatePanel(setStockPurchaseDate, "Enter Date: ", inputDate);

    JPanel myPanel = new JPanel();

    myPanel.add(portfolioNamePanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(numberOfInvestmentsPanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(Box.createVerticalStrut(50));
    JLabel label = new JLabel("Add Stock");
    label.setFont(new Font("Serif", Font.PLAIN, 18));
    myPanel.add(label);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(stockSymbolPanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(datePanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(stockCommissionPanel);
    myPanel.add(Box.createHorizontalStrut(80));
    myPanel.add(stockQuantityPanel);
    panel.add(myPanel);

    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToAddStockFunction);
    panel.add(submitPanel);

    JPanel box = new JPanel();
    box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
    if (!stockList.isEmpty()) {
      for (int i = 0; i < stockList.size(); i++) {
        JButton button = new JButton("Edit");
        int finalI = i;
        button.addActionListener(evt -> {
          editButton = finalI;
          getStockDataToEdit(stockList,
                  setPortfolioName, setNumberOfInvestments);
        });
        JPanel stockPanel = new JPanel();
        stockPanel.add(new JLabel("Stock Symbol: " +
                stockList.get(i).getStockSymbol() + ",", JLabel.TRAILING));
        stockPanel.add(new JLabel("Quantity: " + (int) stockList.get(i).getQuantity() + ","));
        stockPanel.add(new JLabel("Purchase Date: " +
                stockList.get(i).getPurchaseDate() + ",", JLabel.TRAILING));
        stockPanel.add(new JLabel("Commission: " + (int) stockList.get(i).getCommission()));
        stockPanel.add(button);
        stockPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        box.add(stockPanel);
      }
      JScrollPane scrollPane = new JScrollPane(box);
      scrollPane.setPreferredSize(new Dimension(400, 120));
      panel.add(scrollPane);

    }

    JButton resetDataAddPortfolioFunction = new JButton("Reset");
    submitPanel = new JPanel();
    submitPanel.add(resetDataAddPortfolioFunction);
    submitPanel.add(sendDataToAddPortfolioFunction);
    resetDataAddPortfolioFunction.addActionListener(evt -> {
      stockList.clear();
      addFlexiblePortfolio(stockList, null, null, null, null, null, null);
    });
    panel.add(submitPanel);

    sendDataToAddPortfolioFunction.setEnabled(false);
    sendDataToAddStockFunction.setEnabled(true);
    if (setNumberOfInvestments != null && stockList.size()
            == Integer.parseInt(setNumberOfInvestments)) {
      sendDataToAddPortfolioFunction.setEnabled(true);
      sendDataToAddStockFunction.setEnabled(false);
    }

    panel2.add(panel);
    addPortfolioPanel.add(panel2);
    int num = 6;
    if (stockList.size() > 0) {
      num = 7;
    }
    SpringUtilities.makeCompactGrid(panel, num, 1, 6, 6, 6, 6);
    pack();
  }

  @Override
  public void createStrategy(Map<String, Double> stockMap, List<Portfolio> portfolioList,
                             String setPortfolioName, String setNumberOfInvestments,
                             String setStockSymbol, String setStockWeight, String setAmount,
                             String setCommissionFee, String setStockStartDate,
                             String setStockEndDate, String setFrequency) {

    JPanel strategyPanel = replaceOperationPanel(createStrategyButton);
    JPanel panel = new JPanel(new SpringLayout());
    JPanel panel2 = new JPanel();
    dropDownMenuPortfolioList.removeAllItems();
    dropDownMenuPortfolioList.setEnabled(true);
    for (Portfolio p : portfolioList) {
      dropDownMenuPortfolioList.addItem(p.getName());
    }
    dropDownMenuPortfolioList.addItem("Create new Portfolio");
    if (setPortfolioName != null) {
      Portfolio portfolio = portfolioList.stream()
              .filter(p -> setPortfolioName.equals(p.getName()))
              .findAny()
              .orElse(null);
      if (portfolio == null) {
        dropDownMenuPortfolioList.setSelectedItem("Create new Portfolio");
      } else {
        dropDownMenuPortfolioList.setSelectedItem(setPortfolioName);
      }
      dropDownMenuPortfolioList.setEnabled(false);
    }

    JPanel portfolioPanel = new JPanel();
    portfolioPanel.add(new JLabel("Select a portfolio: ", JLabel.TRAILING));
    portfolioPanel.add(dropDownMenuPortfolioList);

    inputPortfolioName = new JTextField(8);
    inputPortfolioName.setEnabled(true);

    if (setPortfolioName != null) {
      inputPortfolioName.setText(setPortfolioName);
      inputPortfolioName.setEnabled(false);
    }
    JPanel portfolioNamePanel = getPanel("Enter Portfolio Name: ", inputPortfolioName, "School");

    inputNumberOfInvestments = new JTextField(8);
    if (setPortfolioName != null) {
      inputNumberOfInvestments.setText(setNumberOfInvestments);
      inputNumberOfInvestments.setEnabled(false);
    }
    JPanel numberOfInvestmentsPanel = getPanel("Enter number of Stocks: ",
            inputNumberOfInvestments, "2");

    inputStockSymbol = new JTextField(8);
    if (setStockSymbol != null) {
      inputStockSymbol.setText(setStockSymbol);
    }
    JPanel stockSymbolPanel = getPanel("Enter Stock Symbol: ", inputStockSymbol, "GOOG");

    inputStockWeight = new JTextField(8);
    if (setStockWeight != null) {
      inputStockWeight.setText(setStockWeight);
    }
    JPanel stockWeightPanel = getPanel("Enter Stock Weight: % ", inputStockWeight, "40");

    inputCommissionFee = new JTextField(8);
    if (setPortfolioName != null) {
      inputCommissionFee.setText(setCommissionFee);
      inputCommissionFee.setEnabled(false);
    }
    JPanel stockCommissionPanel = getPanel("Enter Commission Fee: $ ", inputCommissionFee, "4");

    inputStockAmount = new JTextField(8);
    if (setPortfolioName != null) {
      inputStockAmount.setText(setAmount);
      inputStockAmount.setEnabled(false);
    }
    JPanel stockAmountPanel = getPanel("Enter Amount: $ ", inputStockAmount, "2000");

    inputStrategyFrequency = new JTextField(8);
    if (setPortfolioName != null) {
      inputStrategyFrequency.setText(setFrequency);
      inputStrategyFrequency.setEnabled(false);
    }
    JPanel strategyFrequencyPanel = getPanel("Enter Time Span in days: ",
            inputStrategyFrequency, "4");


    inputStartDate = new JTextField(8);
    inputEndDate = new JTextField(8);

    JPanel startDatePanel = getStrategyDatePanel(setStockStartDate, "Enter Start Date: ",
            inputStartDate);
    JPanel endDatePanel = getStrategyDatePanel(setStockEndDate, "Enter End Date: ",
            inputEndDate);

    JPanel myPanel = new JPanel();

    myPanel.add(portfolioPanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(portfolioNamePanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(stockAmountPanel);
    myPanel.add(Box.createHorizontalStrut(80));
    myPanel.add(numberOfInvestmentsPanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(startDatePanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(stockCommissionPanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(endDatePanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(strategyFrequencyPanel);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(Box.createVerticalStrut(20));
    JLabel label = new JLabel("Add Stock");
    label.setFont(new Font("Serif", Font.PLAIN, 18));
    myPanel.add(label);
    panel.add(myPanel);

    myPanel = new JPanel();
    myPanel.add(stockSymbolPanel);
    myPanel.add(Box.createHorizontalStrut(20));
    myPanel.add(stockWeightPanel);
    panel.add(myPanel);


    JPanel submitPanel = new JPanel();
    submitPanel.add(addToStockMap);
    panel.add(submitPanel);

    submitPanel = new JPanel();
    submitPanel.add(sendDataToCreateStrategyFunction);
    panel.add(submitPanel);


    JPanel box = new JPanel();
    box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
    if (!stockMap.isEmpty()) {
      for (int i = 0; i < stockMap.size(); i++) {
        JButton button = new JButton("Edit");
        int finalI = i;
        button.addActionListener(evt -> {
          editButton = finalI;
          getStockMapToEdit(stockMap, portfolioList,
                  setPortfolioName, setNumberOfInvestments,
                  setAmount, setCommissionFee,
                  setStockStartDate, setStockEndDate, setFrequency);
        });
        JPanel stockPanel = new JPanel();
        Set<String> keySet = stockMap.keySet();
        List<String> listKeys = new ArrayList<>(keySet);
        stockPanel.add(new JLabel("Stock Symbol: " + listKeys.get(i) + ",", JLabel.TRAILING));
        stockPanel.add(new JLabel("Weight: " + stockMap.get(listKeys.get(i))));
        stockPanel.add(button);
        stockPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        box.add(stockPanel);
      }
      JScrollPane scrollPane = new JScrollPane(box);
      scrollPane.setPreferredSize(new Dimension(400, 100));
      panel.add(scrollPane);

    }

    JButton resetDataAddPortfolioFunction = new JButton("Reset");
    submitPanel = new JPanel();
    submitPanel.add(resetDataAddPortfolioFunction);
    submitPanel.add(sendDataToCreateStrategyFunction);
    resetDataAddPortfolioFunction.addActionListener(evt -> {
      stockMap.clear();
      createStrategy(stockMap, portfolioList, null, null, null, null, null, null, null, null, null);
    });
    panel.add(submitPanel);

    sendDataToCreateStrategyFunction.setEnabled(false);
    addToStockMap.setEnabled(true);
    if (setNumberOfInvestments != null && stockMap.size()
            == Integer.parseInt(setNumberOfInvestments)) {
      sendDataToCreateStrategyFunction.setEnabled(true);
      addToStockMap.setEnabled(false);
    }

    panel2.add(panel);
    strategyPanel.add(panel2);
    myPanel = new JPanel();
    String text = "<html>Note: Leave EndDate empty for Ongoing Strategy. " +
            "<br>Enter Same Start Date and End Date for One Time Strategy.</html>";
    myPanel.add(new JLabel(text));
    strategyPanel.add(myPanel, BorderLayout.SOUTH);

    int num = 9;
    if (stockMap.size() > 0) {
      num = 10;
    }
    SpringUtilities.makeCompactGrid(panel, num, 1, 2, 2, 2, 2);
    pack();

  }

  private void getStockDataToEdit(List<Stock> stockList, String setPortfolioName,
                                  String setNumberOfInvestments) {
    String stockSymbol = stockList.get(editButton).getStockSymbol();
    String quantity = String.valueOf((int) stockList.get(editButton).getQuantity());
    String stockPurchaseDate = stockList.get(editButton).getPurchaseDate();
    String commission = String.valueOf((int) stockList.get(editButton).getCommission());
    stockList.remove(stockList.get(editButton));
    addFlexiblePortfolio(stockList, setPortfolioName, setNumberOfInvestments, stockSymbol,
            quantity, stockPurchaseDate, commission);
  }

  private void getStockMapToEdit(Map<String, Double> stockMap, List<Portfolio> portfolioList,
                                 String setPortfolioName, String setNumberOfInvestments,
                                 String setAmount, String setCommissionFee,
                                 String setStockStartDate, String setStockEndDate,
                                 String setFrequency) {
    Set<String> keySet = stockMap.keySet();
    List<String> listKeys = new ArrayList<>(keySet);
    String stockSymbol = listKeys.get(editButton);
    Double stockWeight = stockMap.get(stockSymbol);
    stockMap.remove(stockSymbol);
    createStrategy(stockMap, portfolioList, setPortfolioName, setNumberOfInvestments, stockSymbol,
            String.valueOf(stockWeight), setAmount, setCommissionFee, setStockStartDate,
            setStockEndDate, setFrequency);
  }

  @Override
  public void examinePortfolio(List<Portfolio> portfolioList, Portfolio portfolio,
                               String setPortfolioName) {
    JPanel examinePanel = replaceOperationPanel(examinePortfolioButton);
    JPanel panel = new JPanel();
    JPanel portfolioPanel = getPortfolioDropDownPanel(portfolioList, setPortfolioName);
    panel.add(portfolioPanel);
    panel.add(sendDataToExamineFunction);
    if (portfolio != null) {
      JScrollPane table = examineTable(portfolio);
      JPanel scrollPanel = new JPanel();
      scrollPanel.setBorder(BorderFactory.createTitledBorder(
              BorderFactory.createEtchedBorder(), "Portfolio Composition", TitledBorder.CENTER,
              TitledBorder.TOP));

      scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
      scrollPanel.add(table);
      scrollPanel.setPreferredSize(new Dimension(600, 300));
      panel.add(scrollPanel);
    }
    examinePanel.add(panel);
    pack();
  }

  private JScrollPane examineTable(Portfolio portfolio) {

    List<Stock> stockList = portfolio.getStockList();
    String[][] data = new String[stockList.size() + 1][6];
    for (int i = 0; i < stockList.size(); i++) {
      for (int j = 0; j < 6; j++) {
        Stock one = stockList.get(i);
        String[] single = {String.valueOf(i + 1), one.getStockSymbol(), one.getStockName(),
                String.valueOf(one.getQuantity()),
                one.getPurchaseDate(), String.valueOf(one.getPurchasePrice())};
        data[i][j] = single[j];
      }
    }
    List<String> columnNames = new ArrayList<>();
    columnNames.add("Index");
    columnNames.add("Symbol");
    columnNames.add("Name");
    columnNames.add("Quantity");
    columnNames.add("Purchase Date");
    columnNames.add("Purchase Price");

    JTable dataTable = new JTable(data, columnNames.toArray(new String[0]));
    dataTable.setGridColor(Color.black);
    JTableHeader header = dataTable.getTableHeader();
    header.setBackground(Color.decode("#FCE7FC"));
    dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    return new JScrollPane(dataTable);
  }


  @Override
  public void getPerformanceOfAPortfolio(List<Portfolio> portfolioList, Draw chartDrawer,
                                         String setPortfolioName, String setStartDate,
                                         String setEndDate) {
    JPanel performancePanel = replaceOperationPanel(getPerformanceOfAPortfolioButton);

    performancePanel.add(new JLabel("Hello"));
    inputStartDate = new JTextField(8);
    inputEndDate = new JTextField(8);

    JPanel panel = new JPanel(new SpringLayout());
    JPanel panel2 = new JPanel();
    JPanel startDatePanel = getDatePanel(setStartDate, "Enter Start Date: ", inputStartDate);
    JPanel endDatePanel = getDatePanel(setEndDate, "Enter End Date: ", inputEndDate);
    JPanel portfolioPanel = getPortfolioDropDownPanel(portfolioList, setPortfolioName);

    panel.add(portfolioPanel);
    panel.add(startDatePanel);
    panel.add(endDatePanel);
    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToGetPerformanceOfAPortfolioFunction);
    panel.add(submitPanel);

    HistogramPanel histogramPanel = new HistogramPanel();
    JButton enlargeChartButton = new JButton("Enlarge Chart");
    enlargeChartButton.setVisible(false);
    JLabel scaleLabel = new JLabel();
    List<String> colorSet = new ArrayList<>();
    colorSet.add("#003f5c");
    colorSet.add("#2f4b7c");
    colorSet.add("#665191");
    colorSet.add("#a05195");
    colorSet.add("#d45087");
    colorSet.add("#f95d6a");
    colorSet.add("#ff7c43");
    colorSet.add("#ffa600");

    if (chartDrawer != null) {
      Map<String, String> chart = chartDrawer.getChart();
      Double scale = chartDrawer.getScale();
      scaleLabel = new JLabel("Scale: 1 value = $" + String.format("%.2f", scale));
      scaleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      int i = 0;
      for (Map.Entry<String, String> entry : chart.entrySet()) {
        histogramPanel.addHistogramColumn(entry.getKey(), entry.getValue().length(),
                Color.decode(colorSet.get(i++ % 8)));
      }
      histogramPanel.layoutHistogram();
      histogramPanel.setPreferredSize(new Dimension(700, 300));

      enlargeChartButton.addActionListener(evt -> {
        JFrame frame = new JFrame("Histogram Panel");
        frame.add(histogramPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
      });
    }
    submitPanel = new JPanel();
    submitPanel.add(enlargeChartButton);
    panel.add(histogramPanel);
    panel.add(scaleLabel);
    panel.add(submitPanel);

    panel2.add(panel);
    performancePanel.add(panel2);
    int num = 4;
    if (setPortfolioName != null) {
      num = 7;
      enlargeChartButton.setVisible(true);
    }
    SpringUtilities.makeCompactGrid(panel, num, 1, 6, 6, 6, 6);

    pack();
  }

  @Override
  public void showTotalValueScreen(List<Portfolio> portfolioList, String totalValue,
                                   String setPortfolioName) {

    JPanel totalValuePanel = replaceOperationPanel(getTotalValueButton);
    JPanel panel = new JPanel();
    JPanel spring = new JPanel(new SpringLayout());
    JPanel portfolioPanel = getPortfolioDropDownPanel(portfolioList, setPortfolioName);
    inputDate = new JTextField(8);
    JPanel datePanel = getDatePanel(null, "Enter Date: ", inputDate);
    spring.add(portfolioPanel);
    spring.add(datePanel);
    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToTotalValueFunction);
    spring.add(submitPanel);
    JLabel results = new JLabel("<html>Total Value of portfolio" + setPortfolioName + " is $"
            + totalValue + " .<br> Calculated using AlphaVantage API.</html>");

    spring.add(results);
    panel.add(spring);
    totalValuePanel.add(panel, BorderLayout.CENTER);
    results.setVisible(totalValue != null);
    SpringUtilities.makeCompactGrid(spring, 4, 1, 15, 15, 15, 15);
    pack();
  }

  private JPanel getDatePanel(String setDate, String text, JTextField inputDate) {
    JPanel datePanel = new JPanel();
    JButton datePickerPopUpButton = new JButton("Choose a Date");

    datePanel.add(new JLabel(text));
    inputDate.setToolTipText("2022-01-01");
    if (setDate != null) {
      inputDate.setText(setDate);
    }
    datePanel.add(inputDate);
    datePanel.add(datePickerPopUpButton);
    final JFrame datePopUpFrame = new JFrame();
    datePickerPopUpButton.addActionListener(ae ->
            inputDate.setText(new DatePicker(datePopUpFrame).setPickedDate()));

    return datePanel;
  }

  private JPanel getStrategyDatePanel(String setDate, String text, JTextField inputDate) {
    JPanel datePanel = new JPanel();
    JButton datePickerPopUpButton = new JButton("Choose a Date");

    datePanel.add(new JLabel(text));
    inputDate.setToolTipText("2022-01-01");
    if (setDate != null) {
      inputDate.setText(setDate);
      datePickerPopUpButton.setEnabled(false);
      inputDate.setEnabled(false);
      datePanel.setEnabled(false);
    }
    datePanel.add(inputDate);
    datePanel.add(datePickerPopUpButton);
    final JFrame datePopUpFrame = new JFrame();
    datePickerPopUpButton.addActionListener(ae ->
            inputDate.setText(new DatePicker(datePopUpFrame).setPickedDate()));

    return datePanel;
  }


  private JPanel getPortfolioDropDownPanel(List<Portfolio> portfolioList,
                                           String setPortfolioName) {
    dropDownMenuPortfolioList.removeAllItems();
    for (Portfolio p : portfolioList) {
      dropDownMenuPortfolioList.addItem(p.getName());
    }
    if (setPortfolioName != null) {
      dropDownMenuPortfolioList.setSelectedItem(setPortfolioName);
    }
    JPanel portfolioPanel = new JPanel();
    portfolioPanel.add(new JLabel("Select a portfolio: ", JLabel.TRAILING));
    portfolioPanel.add(dropDownMenuPortfolioList);
    return portfolioPanel;
  }

  @Override
  public void buyStock(List<Portfolio> portfolioList) {
    buyAndSellStock(portfolioList, buyStockButton, sendDataToBuyStockFunction);
  }

  private void buyAndSellStock(List<Portfolio> portfolioList, JRadioButton buyStockButton,
                               JButton sendDataToBuySellStockFunction) {
    JPanel buyStockPanel = replaceOperationPanel(buyStockButton);
    JPanel panel = new JPanel(new SpringLayout());
    JPanel panel2 = new JPanel();
    JPanel portfolioPanel = getPortfolioDropDownPanel(portfolioList, null);

    inputStockSymbol = new JTextField(8);
    JPanel stockSymbolPanel = getPanel("Enter Stock Symbol: ", inputStockSymbol, "GOOG");

    inputStockQuantity = new JTextField(8);
    JPanel stockQuantityPanel = getPanel("Enter Stock Quantity: ", inputStockQuantity, "25");

    inputCommissionFee = new JTextField(8);
    JPanel stockCommissionPanel = getPanel("Enter Commission Fee:", inputCommissionFee, "4");
    inputDate = new JTextField(8);
    JPanel datePanel = getDatePanel(null, "Enter Date: ", inputDate);

    panel.add(portfolioPanel);
    panel.add(stockSymbolPanel);
    panel.add(stockQuantityPanel);
    panel.add(stockCommissionPanel);
    panel.add(datePanel);
    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToBuySellStockFunction);
    panel.add(submitPanel);
    panel2.add(panel);
    buyStockPanel.add(panel2);

    SpringUtilities.makeCompactGrid(panel, 6, 1, 15, 15, 15, 15);
    pack();
  }

  private JPanel getPanel(String text, JTextField textField, String toolTipText) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(text));
    textField.setToolTipText(toolTipText);
    panel.add(textField);
    return panel;
  }


  @Override
  public void sellStock(List<Portfolio> portfolioList) {
    buyAndSellStock(portfolioList, sellStockButton, sendDataToSellStockFunction);
  }

  @Override
  public void getCostBasis(List<Portfolio> portfolioList, String cost, String setPortfolioName) {

    JPanel costBasisPanel = replaceOperationPanel(getCostBasisButton);
    JPanel panel = new JPanel();
    JPanel spring = new JPanel(new SpringLayout());
    JPanel portfolioPanel = getPortfolioDropDownPanel(portfolioList, setPortfolioName);
    inputDate = new JTextField(8);
    JPanel datePanel = getDatePanel(null, "Enter Date: ", inputDate);
    spring.add(portfolioPanel);
    spring.add(datePanel);
    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToCostBasisFunction);
    spring.add(submitPanel);
    JLabel results = new JLabel("Cost Basis of portfolio " + setPortfolioName
            + " is $" + cost + " .");

    spring.add(results);
    panel.add(spring);
    costBasisPanel.add(panel, BorderLayout.CENTER);
    results.setVisible(cost != null);
    SpringUtilities.makeCompactGrid(spring, 4, 1, 15, 15, 15, 15);
    pack();

  }

  @Override
  public void getUserName() {

    inputUserName = new JTextField(10);
    String msgString1 = "Enter User Name: ";
    Object[] array = {msgString1, inputUserName};
    Object[] options = {saveButton, "Cancel"};

    int result = JOptionPane.showOptionDialog(null, array, "",
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    if (result == 0) {
      // do nothing
    }
    if (result == 1) {
      createMenuPanel();

    }
  }


  @Override
  public void showPopUp(String message, String status, int messageType) {
    JOptionPane.showMessageDialog(this,
            message, status, messageType);
  }

  @Override
  public void enterData(String location) {
    JPanel enterDataPanel = replaceOperationPanel(enterDataFileButton);
    JPanel panel = new JPanel(new SpringLayout());
    JPanel locationPanel = new JPanel();
    locationPanel.add(new JLabel("Enter file location: "));
    inputLocation = new JTextField(20);
    locationPanel.add(inputLocation);
    locationPanel.add(fileOpenButton);
    panel.add(locationPanel);
    JPanel myPanel = new JPanel();
    myPanel.add(panel);
    JPanel submitPanel = new JPanel();
    submitPanel.add(sendDataToEnterDataFunction);
    myPanel.add(submitPanel);
    enterDataPanel.add(myPanel);
    SpringUtilities.makeCompactGrid(panel, 1, 1, 6, 6, 6, 6);
    pack();
  }

  private void chooseFile() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml", "txt");
    fileChooser.setFileFilter(filter);
    int status = fileChooser.showOpenDialog(this);
    if (status == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      inputLocation.setText(f.getAbsolutePath());
    }
  }

  @Override
  public void saveFile() {

    final JFileChooser fChooser = new JFileChooser(".");
    int returnValue = fChooser.showSaveDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fChooser.getSelectedFile();
      file = new File(file.toString() + ".xml");
      fileSaveLocation = file.getAbsolutePath();
      showPopUp(fileSaveLocation, "Success", JOptionPane.PLAIN_MESSAGE);
    }

  }

  private void addButtonsToMenu(JPanel menuPanel) {
    for (JRadioButton jRadioButton : Arrays.asList(enterDataFileButton, addPortfolioButton,
            examinePortfolioButton, getTotalValueButton, buyStockButton,
            sellStockButton, getCostBasisButton, getPerformanceOfAPortfolioButton,
            createStrategyButton)) {
      menuPanel.add(jRadioButton);
    }
  }

  private void groupButtons() {
    for (JRadioButton jRadioButton : Arrays.asList(enterDataFileButton, addPortfolioButton,
            examinePortfolioButton, getTotalValueButton, buyStockButton,
            sellStockButton, getCostBasisButton, getPerformanceOfAPortfolioButton,
            createStrategyButton)) {
      group.add(jRadioButton);
    }
  }

  @Override
  public void setFeatures(Features features) {
    enterDataFileButton.addActionListener(evt -> submitButtonAction = "1");
    addPortfolioButton.addActionListener(evt -> submitButtonAction = "2");
    examinePortfolioButton.addActionListener(evt -> submitButtonAction = "3");
    getTotalValueButton.addActionListener(evt -> submitButtonAction = "4");
    buyStockButton.addActionListener(evt -> submitButtonAction = "6");
    sellStockButton.addActionListener(evt -> submitButtonAction = "7");
    getCostBasisButton.addActionListener(evt -> submitButtonAction = "8");
    getPerformanceOfAPortfolioButton.addActionListener(evt -> submitButtonAction = "9");
    createStrategyButton.addActionListener(evt -> submitButtonAction = "10");
    menuOptionSubmitButton.addActionListener(evt -> features.submitButton(submitButtonAction));

    sendDataToTotalValueFunction.addActionListener(evt -> features.getTotalValue(
        String.valueOf(dropDownMenuPortfolioList.getSelectedItem()), inputDate.getText()));

    sendDataToExamineFunction.addActionListener(evt ->
        features.examinePortfolio(String.valueOf(dropDownMenuPortfolioList.getSelectedItem())));

    sendDataToEnterDataFunction.addActionListener(evt ->
        features.enterData(inputLocation.getText()));

    sendDataToBuyStockFunction.addActionListener(evt ->
        features.buyStock(String.valueOf(dropDownMenuPortfolioList.getSelectedItem()),
        inputStockSymbol.getText(), inputStockQuantity.getText(), inputDate.getText(),
        inputCommissionFee.getText()));

    sendDataToSellStockFunction.addActionListener(evt ->
        features.sellStock(String.valueOf(dropDownMenuPortfolioList.getSelectedItem()),
        inputStockSymbol.getText(), inputStockQuantity.getText(), inputDate.getText(),
        inputCommissionFee.getText()));

    sendDataToCostBasisFunction.addActionListener(evt ->
        features.getCostBasis(String.valueOf(dropDownMenuPortfolioList.getSelectedItem()),
        inputDate.getText()));

    fileOpenButton.addActionListener(evt -> chooseFile());

    sendDataToAddStockFunction.addActionListener(evt ->
        features.addStock(inputPortfolioName.getText(), inputNumberOfInvestments.getText(),
        inputStockSymbol.getText(), inputStockQuantity.getText(), inputDate.getText(),
        inputCommissionFee.getText()));

    sendDataToAddPortfolioFunction.addActionListener(evt ->
        features.addPortfolio(inputPortfolioName.getText()));

    addToStockMap.addActionListener(evt -> {
      String portfolioName;
      if (Objects.requireNonNull(dropDownMenuPortfolioList.getSelectedItem()).toString()
              .equals("Create new Portfolio")) {
        portfolioName = inputPortfolioName.getText();
      } else {
        portfolioName = dropDownMenuPortfolioList.getSelectedItem().toString();
      }
      features.addToStockMapFunction(portfolioName, inputNumberOfInvestments.getText(),
          inputStockSymbol.getText(), inputStockWeight.getText(), inputStockAmount.getText(),
          inputCommissionFee.getText(), inputStartDate.getText(), inputEndDate.getText(),
          inputStrategyFrequency.getText());
    });

    sendDataToCreateStrategyFunction.addActionListener(evt ->
        features.createStrategy(inputPortfolioName.getText(), inputStockAmount.getText(),
        inputCommissionFee.getText(), inputStartDate.getText(), inputEndDate.getText(),
        inputStrategyFrequency.getText(), inputNumberOfInvestments.getText()));

    sendDataToGetPerformanceOfAPortfolioFunction.addActionListener(evt ->
        features.getPerformanceOfAPortfolio(inputStartDate.getText(), inputEndDate.getText(),
        Objects.requireNonNull(dropDownMenuPortfolioList.getSelectedItem()).toString()));

    saveDataButton.addActionListener(evt -> {
      if (!features.userNameExists()) {
        features.checkSaveData();
      } else {
        saveFile();
        features.setFileSaveLocation(fileSaveLocation);
        features.saveData();
      }
    });

    saveButton.addActionListener(evt -> {
      if (inputUserName.getText().equals("")) {
        showPopUp("Invalid UserName", "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        features.setUserName(inputUserName.getText());
        JOptionPane.getRootFrame().dispose();
        saveFile();
        features.setFileSaveLocation(fileSaveLocation);
        features.saveData();
      }
    });

    quitButton.addActionListener(evt -> System.exit(0));
  }
}
