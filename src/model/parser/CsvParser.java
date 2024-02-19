package model.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a parser for the csv file which contains the list of stocks
 * supported by the application. The csv file contains two columns, one with the company name
 * and other with respective stock symbol.
 */
public class CsvParser {

  /**
   * This static method is used to parse the list of stock supported by the application and map the
   * stock(ticker) symbol to the name of the company.
   * It creates a hashmap with stock symbol and company name.
   *
   * @param filename the name of the file
   * @return a map
   */
  public static Map<String, String> getListOfStocks(String filename) {
    HashMap<String, String> map = new HashMap<>();
    String line;
    String splitBy = ",";
    int count = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (count < 5746) {
        line = br.readLine();
        String[] stockData = line.split(splitBy);
        map.put(stockData[0].strip(), stockData[1].strip());
        count += 1;
      }
    } catch (IOException e) {
      // do nothing
    }
    return map;
  }
}