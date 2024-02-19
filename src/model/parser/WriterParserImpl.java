package model.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Portfolio;
import model.Stock;
import model.Strategy;
import model.Transaction;
import model.User;

/**
 * This class implements WriterParser interface. It contains methods to write data from the user
 * object to the xml file so that it can be loaded at a later stage.
 * This method will be called when a user chooses an option to save data.
 */
public class WriterParserImpl implements WriterParser {

  @Override
  public boolean writeData(User user, String fileSaveLocation) throws ParserConfigurationException {

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("user");
    doc.appendChild(rootElement);
    rootElement.setAttribute("name", user.getUserName());
    rootElement.setAttribute("portfolioType", user.getPortfolioType());

    List<Portfolio> portfolioList = user.getPortfolioList();

    for (int i = 0; i < portfolioList.size(); i++) {

      Element portfolio = doc.createElement("portfolio");
      portfolio.setAttribute("name", portfolioList.get(i).getName());
      rootElement.appendChild(portfolio);

      List<Stock> stockList = user.getPortfolioList().get(i).getStockList();
      for (Stock stock : stockList) {
        Element stocks = doc.createElement("stock");
        portfolio.appendChild(stocks);

        Element symbol = doc.createElement("symbol");
        symbol.setTextContent(stock.getStockSymbol());
        stocks.appendChild(symbol);

        Element name = doc.createElement("name");
        name.setTextContent(stock.getStockName());
        stocks.appendChild(name);

        Element quantity = doc.createElement("quantity");
        quantity.setTextContent(String.valueOf(stock.getQuantity()));
        stocks.appendChild(quantity);

        Element purchaseDate = doc.createElement("purchaseDate");
        purchaseDate.setTextContent(String.valueOf(stock.getPurchaseDate()));
        stocks.appendChild(purchaseDate);

        Element purchasePrice = doc.createElement("purchasePrice");
        purchasePrice.setTextContent(String.valueOf(stock.getPurchasePrice()));
        stocks.appendChild(purchasePrice);

        Element commission = doc.createElement("commission");
        commission.setTextContent(String.valueOf(stock.getCommission()));
        stocks.appendChild(commission);
      }
    }

    if (user.getPortfolioType().equals("flexible")) {
      List<Transaction> transactionList = user.getTransactionList();
      Element transaction = doc.createElement("transaction");
      rootElement.appendChild(transaction);

      for (Transaction t : transactionList) {
        Element portfolio = doc.createElement("portfolio");
        portfolio.setAttribute("name", t.getPortfolioName());
        transaction.appendChild(portfolio);

        Element type = doc.createElement("type");
        type.setTextContent(t.getType());
        portfolio.appendChild(type);

        Element stockSymbol = doc.createElement("stockSymbol");
        stockSymbol.setTextContent(t.getStockSymbol());
        portfolio.appendChild(stockSymbol);

        Element stockQuantity = doc.createElement("stockQuantity");
        stockQuantity.setTextContent(String.valueOf(t.getQuantity()));
        portfolio.appendChild(stockQuantity);

        Element date = doc.createElement("date");
        date.setTextContent(String.valueOf(t.getDate()));
        portfolio.appendChild(date);

        Element commission = doc.createElement("commission");
        commission.setTextContent(String.valueOf(t.getCommission()));
        portfolio.appendChild(commission);

      }

      Element strategy = doc.createElement("strategy");
      rootElement.appendChild(strategy);
      List<Strategy> strategyList = user.getStrategyList();
      for (Strategy s : strategyList) {

        Element portfolio = doc.createElement("portfolio");
        portfolio.setAttribute("name", s.getPortfolioName());
        strategy.appendChild(portfolio);

        Element amount = doc.createElement("amount");
        amount.setTextContent(String.valueOf(s.getAmount()));
        portfolio.appendChild(amount);

        Element stockMapElement = doc.createElement("stockMap");
        portfolio.appendChild(stockMapElement);

        Map<String, Double> stockMap = s.getStockMap();
        for (String stockSymbol : stockMap.keySet()) {

          Element stock = doc.createElement("stock");
          stockMapElement.appendChild(stock);

          Element symbol = doc.createElement("symbol");
          symbol.setTextContent(stockSymbol);
          stock.appendChild(symbol);

          Element weight = doc.createElement("weight");
          weight.setTextContent(String.valueOf(stockMap.get(stockSymbol)));
          stock.appendChild(weight);

        }


        Element startDate = doc.createElement("startDate");
        startDate.setTextContent(s.getStartDate().toString());
        portfolio.appendChild(startDate);

        Element endDate = doc.createElement("endDate");
        endDate.setTextContent(s.getEndDate().toString());
        portfolio.appendChild(endDate);

        Element frequency = doc.createElement("frequency");
        frequency.setTextContent(String.valueOf(s.getFrequency()));
        portfolio.appendChild(frequency);

        Element isOnGoing = doc.createElement("isOnGoing");
        isOnGoing.setTextContent(String.valueOf(s.getIsOnGoing()));
        portfolio.appendChild(isOnGoing);

        Element commission = doc.createElement("commission");
        commission.setTextContent(String.valueOf(s.getCommission()));
        portfolio.appendChild(commission);

      }


    }


    try (FileOutputStream output = new FileOutputStream(fileSaveLocation)) {
      writeXml(doc, output);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private void writeXml(Document doc, OutputStream output) throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);
    transformer.transform(source, result);
  }
}



