package model.parser;

import model.User;

/**
 * This interface represents the read parser. It contains methods that are required to read data
 * from a file.
 */
public interface ReaderParser {

  /**
   * It is used to parse and read the data from the input file (Example: XML file).
   * It parses the data on a specific xml format and loads it to the user object.
   * The XML file contains username, portfolio details such as portfolio name, stock list,
   * stock details.
   * It catches Exception if parsing failed.
   *
   * @param filename name of the file.
   * @return User object with all the data
   */
  User readData(String filename);
}
