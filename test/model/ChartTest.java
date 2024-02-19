package model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test file for Chart class.
 * Tests methods such as generateChart and getScale of chart.
 */
public class ChartTest {

  Chart chartObj;

  Map<String, String> chart;
  User user;
  ModelHandler model;

  @Before
  public void setUp() {
    chartObj = new Chart();
    model = new ModelHandlerImpl();
    chart = new HashMap<>();
    user = model.initializeUserData("input2.xml", "2");
    chart = chartObj.generateChart("2021-1-1", "2022-1-1", "Investments",
            user, 30);
  }

  @Test
  public void generateChart() {
    chart = chartObj.generateChart("2021-1-1", "2022-1-1", "Investments",
            user, 30);
    assertEquals(chart.toString(), "{Jan-2021=, Feb-2021=, Mar-2021=*****, Apr-2021=****, " +
            "May-2021=********, Jun-2021=*************, Jul-2021=************, " +
            "Aug-2021=**************, Sep-2021=********************, Oct-2021=****************" +
            "******" +
            ", Nov-2021=*******************, " +
            "Dec-2021=**************************************************}");
  }

  @Test
  public void getScale() {
    chart = chartObj.generateChart("2021-1-1", "2022-1-1", "Investments",
            user, 30);
    assertEquals(chartObj.getScale(), 4544.44, 0.0001);
  }
}