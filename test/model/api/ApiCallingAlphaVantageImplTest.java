package model.api;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test file for ApiCallingAlphaVantageImpl class.
 * Tests api calls on different days.
 */
public class ApiCallingAlphaVantageImplTest extends TestCase {

  ApiCalling api;

  @Before
  public void setUp() {
    api = new ApiCallingAlphaVantageImpl();
  }

  @Test
  public void test() {
    assertEquals(api.getPrice("ibm", LocalDate.parse("2022-11-11"), true), 143.17, 0.0001);
    assertEquals(api.getPrice("ibm", LocalDate.parse("2022-11-10"), true), 141.23, 0.0001);
  }

  @Test
  public void testWeekend() {
    assertEquals(api.getPrice("ibm", LocalDate.parse("2022-11-11"), true), 143.17, 0.0001);
    assertEquals(api.getPrice("ibm", LocalDate.parse("2022-11-12"), true), 143.17, 0.0001);
    assertEquals(api.getPrice("ibm", LocalDate.parse("2022-11-13"), true), 143.17, 0.0001);
  }

  @Test
  public void testHoliday() {
    assertEquals(api.getPrice("ibm", LocalDate.parse("2021-12-24"), true), 130.63, 0.0001);
    assertEquals(api.getPrice("ibm", LocalDate.parse("2021-12-25"), true), 130.63, 0.0001);
  }

  @Test
  public void testBefore1995() {
    assertEquals(api.getPrice("ibm", LocalDate.parse("1994-08-12"), true), 0, 0.0001);
    assertEquals(api.getPrice("ibm", LocalDate.parse("1994-09-10"), true), 0, 0.0001);
  }

}