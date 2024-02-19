package model.parser;

import java.io.File;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import model.FlexiblePortfolioImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;
import model.Strategy;
import model.StrategyImpl;
import model.Transaction;
import model.TransactionImpl;
import model.User;
import model.UserImpl;

/**
 * This class implements the read parser.
 * It contains operations on reading data from a file.
 * It uses XPath method to extract data from XML file.
 */
public class ReaderParserImpl implements ReaderParser {

  @Override
  public User readData(String filename) {

    List<Portfolio> portfolioList = new ArrayList<>();
    List<Transaction> transactionList;
    List<Strategy> strategyList;
    String userName;
    String portfolioType;
    try {
      File inputFile = new File(filename);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;

      dBuilder = dbFactory.newDocumentBuilder();

      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();

      String expression = "/user/portfolio/stock";
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);

      Element eElement = (Element) nodeList.item(0).getParentNode().getParentNode();
      userName = eElement.getAttribute("name");
      portfolioType = eElement.getAttribute("portfolioType");

      List<String> portfolioNameList = new ArrayList<>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node nNode = nodeList.item(i);

        List<Stock> stockList = new ArrayList<>();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          eElement = (Element) nNode.getParentNode();

          String portfolioName = eElement.getAttribute("name");

          eElement = (Element) nNode;
          String stockSymbol = eElement
                  .getElementsByTagName("symbol")
                  .item(0)
                  .getTextContent();
          String companyName = eElement
                  .getElementsByTagName("name")
                  .item(0)
                  .getTextContent();
          String quantity = eElement
                  .getElementsByTagName("quantity")
                  .item(0)
                  .getTextContent();
          String commission = eElement
                  .getElementsByTagName("commission")
                  .item(0)
                  .getTextContent();
          String stringPurchaseDate = eElement
                  .getElementsByTagName("purchaseDate")
                  .item(0)
                  .getTextContent();
          String stringPurchasePrice = eElement
                  .getElementsByTagName("purchasePrice")
                  .item(0)
                  .getTextContent();
          String[] tokens = stringPurchaseDate.split("-");
          LocalDate purchaseDate = LocalDate.of(Integer.parseInt(tokens[0]),
                  Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
          double purchasePrice = Double.parseDouble(stringPurchasePrice);
          Stock one = new StockImpl.StockBuilder()
                  .stockSymbol(stockSymbol)
                  .stockName(companyName)
                  .quantity(Double.parseDouble(quantity))
                  .commission(Double.parseDouble(commission))
                  .purchaseDate(purchaseDate)
                  .purchasePrice(purchasePrice)
                  .build();
          stockList.add(one);

          if (portfolioNameList.contains(portfolioName)) {
            for (Portfolio portfolio : portfolioList) {
              if (portfolio.getName().equals(portfolioName)) {
                stockList = portfolio.getStockList();
                stockList.add(one);
              }
            }
          } else {
            Portfolio portfolio;
            if (portfolioType.equals("inflexible")) {
              portfolio = new PortfolioImpl.PortfolioBuilder().name(portfolioName)
                      .stockList(stockList).build();
            } else {
              portfolio = new FlexiblePortfolioImpl.FlexiblePortfolioBuilder()
                      .name(portfolioName).stockList(stockList).build();
            }
            portfolioList.add(portfolio);
            portfolioNameList.add(portfolioName);
          }
        }
      }
      transactionList = readTransaction(filename);
      strategyList = readStrategy(filename);


    } catch (Exception e) {
      throw new IllegalArgumentException("caught it");
    }
    return new UserImpl.UserBuilder()
            .userName(userName)
            .portfolioType(portfolioType)
            .portfolioList(portfolioList)
            .transactionList(transactionList)
            .strategyList(strategyList)
            .build();
  }

  private List<Transaction> readTransaction(String filename) {

    List<Transaction> transactionList = new ArrayList<>();
    try {
      File inputFile = new File(filename);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;
      dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      XPath xPath = XPathFactory.newInstance().newXPath();

      String expression = "/user/transaction/portfolio";
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);

      Element eElement;

      NodeList node = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);
      for (int i = 0; i < node.getLength(); i++) {
        Node nNode = nodeList.item(i);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          eElement = (Element) nNode.getParentNode();

          eElement = (Element) nNode;
          String portfolioName = eElement.getAttribute("name");
          String type = eElement
                  .getElementsByTagName("type")
                  .item(0)
                  .getTextContent();
          String stockSymbol = eElement
                  .getElementsByTagName("stockSymbol")
                  .item(0)
                  .getTextContent();
          String stockQuantity = eElement
                  .getElementsByTagName("stockQuantity")
                  .item(0)
                  .getTextContent();
          String date = eElement
                  .getElementsByTagName("date")
                  .item(0)
                  .getTextContent();
          String commission = eElement
                  .getElementsByTagName("commission")
                  .item(0)
                  .getTextContent();

          String[] tokens = date.split("-");
          LocalDate enteredDate = LocalDate.of(Integer.parseInt(tokens[0]),
                  Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

          Transaction one = new TransactionImpl.TransactionBuilder()
                  .portfolioName(portfolioName)
                  .stockSymbol(stockSymbol)
                  .type(type)
                  .quantity(Double.parseDouble(stockQuantity))
                  .commission(Double.parseDouble(commission))
                  .date(enteredDate).build();
          transactionList.add(one);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("caught it");
    }
    return transactionList;
  }

  private List<Strategy> readStrategy(String filename) {

    List<Strategy> strategyList = new ArrayList<>();
    try {
      File inputFile = new File(filename);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;
      dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      XPath xPath = XPathFactory.newInstance().newXPath();

      String expression = "/user/strategy/portfolio";
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);

      Element eElement;

      NodeList node = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);
      for (int i = 0; i < node.getLength(); i++) {
        Node nNode = nodeList.item(i);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

          eElement = (Element) nNode;
          String portfolioName = eElement.getAttribute("name");
          String amount = eElement
                  .getElementsByTagName("amount")
                  .item(0)
                  .getTextContent();
          String startDate = eElement
                  .getElementsByTagName("startDate")
                  .item(0)
                  .getTextContent();
          String endDate = eElement
                  .getElementsByTagName("endDate")
                  .item(0)
                  .getTextContent();
          String frequency = eElement
                  .getElementsByTagName("frequency")
                  .item(0)
                  .getTextContent();
          String isOnGoing = eElement
                  .getElementsByTagName("isOnGoing")
                  .item(0)
                  .getTextContent();
          String commission = eElement
                  .getElementsByTagName("commission")
                  .item(0)
                  .getTextContent();

          String[] tokens = startDate.split("-");
          LocalDate enteredStartDate = LocalDate.of(Integer.parseInt(tokens[0]),
                  Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

          tokens = endDate.split("-");
          LocalDate enteredEndDate = LocalDate.of(Integer.parseInt(tokens[0]),
                  Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));


          Strategy strategy = new StrategyImpl.StrategyImplBuilder()
                  .portfolioName(portfolioName)
                  .amount(Double.parseDouble(amount))
                  .startDate(enteredStartDate)
                  .endDate(enteredEndDate)
                  .frequency(Integer.parseInt(frequency))
                  .isOnGoing(Boolean.parseBoolean(isOnGoing))
                  .commission(Double.parseDouble(commission))
                  .stockMap(readStockMap(filename, i + 1))
                  .build();
          strategyList.add(strategy);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("caught it");
    }
    return strategyList;
  }

  private Map<String, Double> readStockMap(String filename, int index) {
    Map<String, Double> stockMap = new HashMap<>();

    try {
      File inputFile = new File(filename);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;
      dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      XPath xPath = XPathFactory.newInstance().newXPath();


      String expression = "/user/strategy/portfolio[" + index + "]/stockMap/stock";
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);

      Element eElement;

      NodeList node = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);

      for (int i = 0; i < node.getLength(); i++) {
        Node nNode = nodeList.item(i);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

          eElement = (Element) nNode;
          Element ele = (Element) eElement.getParentNode().getParentNode();
          String stockSymbol = eElement
                  .getElementsByTagName("symbol")
                  .item(0)
                  .getTextContent();
          String stockWeight = eElement
                  .getElementsByTagName("weight")
                  .item(0)
                  .getTextContent();
          stockMap.put(stockSymbol.toUpperCase().strip(), Double.parseDouble(stockWeight.strip()));
        }
      }
    } catch (Exception e) {
      // do nothing
    }
    return stockMap;

  }

}

