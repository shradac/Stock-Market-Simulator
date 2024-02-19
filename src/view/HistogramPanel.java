package view;

import java.awt.Component;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * Class representing the drawing of the (histogram) bar graph to the view.
 * It contains methods to create a bar, set layouts, sizes, etc.
 * Citation: https://stackoverflow.com/questions/29708147/custom-graph-java-swing.
 * Edited code from above link.
 */
public class HistogramPanel extends JPanel {
  private final JPanel barPanel;
  private final JPanel labelPanel;

  private final List<Bar> bars = new ArrayList<>();

  /**
   * Constructor for HistogramPanel class. Draws the border around the graph.
   */
  public HistogramPanel() {
    setBorder(new EmptyBorder(10, 10, 10, 10));
    setLayout(new BorderLayout());

    int barGap = 10;
    barPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
    Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
    Border inner = new EmptyBorder(10, 10, 0, 10);
    Border compound = new CompoundBorder(outer, inner);
    barPanel.setBorder(compound);

    labelPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
    labelPanel.setBorder(new EmptyBorder(5, 10, 0, 10));

    add(barPanel, BorderLayout.CENTER);
    add(labelPanel, BorderLayout.PAGE_END);
  }

  /**
   * Creates bars based on the values provided.
   *
   * @param label label of the bar column
   * @param value value of the bar
   * @param color color of the bar
   */
  public void addHistogramColumn(String label, int value, Color color) {
    Bar bar = new Bar(label, value, color);
    bars.add(bar);
  }

  /**
   * Draws the histogram to the view.
   */
  public void layoutHistogram() {
    barPanel.removeAll();
    labelPanel.removeAll();

    int maxValue = 0;

    for (Bar bar : bars) {
      maxValue = Math.max(maxValue, bar.getValue());
      if (maxValue == 0) {
        maxValue = 1;
      }
    }

    for (Bar bar : bars) {
      JLabel label = new JLabel(bar.getValue() + "");
      label.setHorizontalTextPosition(JLabel.CENTER);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setVerticalTextPosition(JLabel.TOP);
      label.setVerticalAlignment(JLabel.BOTTOM);
      int histogramHeight = 200;
      int barHeight = (bar.getValue() * histogramHeight) / maxValue;
      int barWidth = 50;
      Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
      label.setIcon(icon);
      barPanel.add(label);

      JLabel barLabel = new JLabel(bar.getLabel());
      barLabel.setHorizontalAlignment(JLabel.CENTER);
      labelPanel.add(barLabel);
    }
  }

  private static class Bar {
    private final String label;
    private final int value;
    private final Color color;

    private Bar(String label, int value, Color color) {
      this.label = label;
      this.value = value;
      this.color = color;
    }

    private String getLabel() {
      return label;
    }

    private int getValue() {
      return value;
    }

    private Color getColor() {
      return color;
    }
  }

  private static class ColorIcon implements Icon {

    private final Color color;
    private final int width;
    private final int height;

    public ColorIcon(Color color, int width, int height) {
      this.color = color;
      this.width = width;
      this.height = height;
    }

    public int getIconWidth() {
      return width;
    }

    public int getIconHeight() {
      return height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
      g.setColor(color);
      int shadow = 3;
      g.fillRect(x, y, width - shadow, height);
      g.setColor(Color.GRAY);
      g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
    }
  }
}