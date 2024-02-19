package model.validation;

import java.io.File;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import model.ModelHandlerImpl;
import model.ModelHandler;
import model.User;

/**
 * This class represents the validation. It handles all the validation required for the user input.
 */
public class Validation {
  /**
   * It validates if the input option number entered is one among the options available
   * and valid. Based on its matches with the regular expression, the input is validated.
   * It checks if the option enters is in 1-6.
   *
   * @param option option number
   * @return true or false based on whether the input is valid or not
   */
  public static boolean checkValidOption(String option) {
    return option.matches("[123456]");
  }

  public static boolean checkValidOptionForFlexible(String option) {
    return option.matches("[1-9]|10|11");
  }

  /**
   * It validates for the yes if the enter input as a file option is a yes.
   * It accepts any form of "yes" such as "YES", "yes" "Yes", "yES", so on.
   *
   * @param answer given by the user
   * @return true or false based on the matching with regex
   */
  public static boolean checkInputAsFile(String answer) {
    return answer.matches("[Yy][Ee][Ss]");
  }

  /**
   * It validates for the no if the enter input as a file option is a no. The user decides
   * to enter the data manually.
   * It accepts any form of "no" such as "NO", "no" "No", "nO", so on.
   *
   * @param answer given by the user
   * @return true or false based on the matching with the regex
   */
  public static boolean checkInputManually(String answer) {
    return answer.matches("[Nn][Oo]");
  }

  /**
   * It validates for the existence of a file in the specified location.
   *
   * @param location location of the file.
   * @return true if file exists false if the file does not exist
   */
  public static boolean checkFileExists(String location) {
    File f = new File(location);
    return f.exists() && !f.isDirectory();
  }

  /**
   * It validates if the file is parsed successfully.
   *
   * @param location of the file to be parsed
   * @return true or false
   */
  public static boolean validateFile(String location, String portfolioMenuOption) {

    try {
      ModelHandler model = new ModelHandlerImpl();
      User user = model.initializeUserData(location, portfolioMenuOption);
      if (user == null) {
        return false;
      }

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * It validates if the input is an int number.
   * Returns true of it is a number or returns false if it is not.
   * It also checks if the number overflows double.
   *
   * @param number number entered
   * @return true or false
   */
  public static boolean checkIfNumber(String number) {
    if (!number.matches("[0-9]+")) {
      return false;
    }
    if (checkIntOverflow(number)) {
      return false;
    }
    return Integer.parseInt(number) >= 1;
  }

  /**
   * It validates if the number option entered by the user is a valid one for fetching the
   * correct object from the list. Example a portfolio from the portfolio list.
   * Returns true if valid, returns false if out of index
   *
   * @param choice index
   * @param length length of the list
   * @return true or false
   */

  public static boolean checkIndexLessThanSize(String choice, int length) {
    int n = Integer.parseInt(choice);
    return n > 0 && n <= length;
  }

  /**
   * It validates the date entered by the user for valid date.
   * It splits the given string with "-". index0 is the year, index1 is the month,
   * index2 is the day.
   * It checks if the provided date is after 1995/1/1. Any previous date is not supported.
   * It also checks if it is a future date which is also not valid.
   * Returns true if it is valid else returns false
   *
   * @param stringDate date
   * @return true or false
   */
  public static boolean validateDate(String stringDate) {
    LocalDate date;
    try {
      String[] tokens = stringDate.split("-");
      date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]));
    } catch (Exception e) {
      return false;
    }
    if (!date.isAfter(LocalDate.of(1995, 1, 1))) {
      return false;
    }

    return !date.isAfter(LocalDate.now());
  }

  /**
   * It validates for the number of characters in the username.
   * Length of the userName cannot be above 30.
   *
   * @param s username
   * @return true or false
   */
  public static boolean userNameLength(String s) {
    return s.length() <= 30;
  }

  /**
   * It validates for the integer overflow by converting it to bignumber and comparing that with
   * int max value.
   *
   * @param number integer number
   * @return true or false
   */
  public static boolean checkIntOverflow(String number) {
    BigInteger maxInt;
    BigInteger value;

    try {
      maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
      value = new BigInteger(number);
    } catch (Exception e) {
      // do nothing
      return false;
    }
    return value.compareTo(maxInt) > 0;

  }

  /**
   * It checks if the given option in switch case is either "1" or "2".
   *
   * @param option switch case option
   * @return true or false
   */
  public static boolean checkValidPortfolioOption(String option) {
    return option.matches("[12]");
  }

  /**
   * It checks if the start date and end date provided for getting chart are valid.
   * start should be before end.
   * Time span should be at least 5 days.
   * Time span should be less than 30 years.
   *
   * @param startDate startDate as a string
   * @param endDate   endDate as a string
   * @return true or false
   */
  public static boolean validateChartDate(String startDate, String endDate) {
    if (!validateDate(startDate) || !validateDate(endDate)) {
      return false;
    }
    LocalDate start;
    LocalDate end;
    String[] tokens = startDate.split("-");
    try {
      start = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]));
      tokens = endDate.split("-");
      end = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]));
    } catch (Exception e) {
      return false;
    }
    if (start.isAfter(end)) {
      return false;
    }
    if (ChronoUnit.DAYS.between(start, end) < 5) {
      return false;
    }
    return ChronoUnit.YEARS.between(start, end) < 30;
  }

  /**
   * The validateStrategyDate method validates the date. Checks if the range of years entered are
   * not more than 30 years.
   *
   * @param startDate start date
   * @param endDate   end date
   * @return true or false
   */
  public static boolean validateStrategyDate(String startDate, String endDate) {
    LocalDate start;
    LocalDate end;
    String[] tokens = startDate.split("-");
    try {
      start = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]));
      if (!endDate.equals("")) {
        tokens = endDate.split("-");
        end = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]));
        if (start.isAfter(end)) {
          return false;
        }
        return ChronoUnit.YEARS.between(start, end) < 30;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * The checkFutureDate method checks if the entered date is not in the future.
   *
   * @param stringDate date
   * @return true or false
   */
  public static boolean checkFutureDate(String stringDate) {
    try {
      LocalDate date;
      String[] tokens = stringDate.split("-");
      date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if the given commission is valid.
   * If commission is above 2$ and below 30$.
   *
   * @param commission commission fee of a transaction
   * @return true or false
   */
  public static boolean checkValidCommission(String commission) {
    if (!commission.matches("[0-9]+(\\.[0-9]+)?")) {
      return false;
    }
    if (checkIntOverflow(commission)) {
      return false;
    }
    double fee = Double.parseDouble(commission);
    return (fee >= 2) && (fee <= 30);
  }

  /**
   * The checkValidWeight method checks if the entered weight is valid. The weight should not be
   * more than 100 or less than 100.
   *
   * @param stringWeight weight of the stock
   * @return
   */
  public static boolean checkValidWeight(String stringWeight) {
    if (!stringWeight.matches("[0-9]+(\\.[0-9]+)?")) {
      return true;
    }
    if (checkIntOverflow(stringWeight)) {
      return true;
    }
    return Double.parseDouble(stringWeight) <= 0;
  }

  /**
   * The validateWeight method checks if the weight is valid. The weight together should
   * sum upto 100.
   *
   * @param valueList value list
   * @return true or false
   */
  public static boolean validateWeight(List<Double> valueList) {
    double sum = 0;
    for (Double v : valueList) {
      sum += v;
    }
    return sum == 100;
  }

  /**
   * Checks if strategy has valid amount, commission and number of investments.
   *
   * @param stockAmount         amount
   * @param commission          commissionFee
   * @param numberOfInvestments number of stocks
   * @return true or false
   */
  public static boolean checkStrategyAmount(String stockAmount, String commission,
                                            String numberOfInvestments) {
    if (!Validation.checkIfNumber(stockAmount)) {
      return false;
    }
    double amount;
    double commissionFee;
    int numberOfStocks;
    try {
      amount = Double.parseDouble(stockAmount);
      commissionFee = Double.parseDouble(commission);
      numberOfStocks = Integer.parseInt(numberOfInvestments);
    } catch (Exception e) {
      return false;
    }
    return (amount > commissionFee * numberOfStocks);
  }

  /**
   * Checks if the given portfolioName is valid.
   * @param portfolioName portfolioName
   * @return true or false
   */
  public static boolean checkPortfolioName(String portfolioName) {
    return portfolioName.strip().equals("");
  }
}
