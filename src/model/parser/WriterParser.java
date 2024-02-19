package model.parser;

import javax.xml.parsers.ParserConfigurationException;

import model.User;

/**
 * This interface represents the writer parser.
 * It contains methods to write the User Object data to a file.
 */
public interface WriterParser {
  /**
   * It is used for writing data to the file in the correct XML format.
   * It takes all the data from the user object and writes data to the file.
   *
   * @param user             user object
   * @param fileSaveLocation location to save the file in
   * @throws ParserConfigurationException error in configuration of the XML file
   */
  boolean writeData(User user, String fileSaveLocation) throws ParserConfigurationException;
}
