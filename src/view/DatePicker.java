package view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;

import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents a date picker frame where user can span across months and years
 * and pick a date.
 * Citation: https://examples.javacodegeeks.com/desktop-java/swing/java-swing-date-picker-example/.
 * Edited code from above link.
 */
public class DatePicker {
  private final JLabel l1 = new JLabel("", JLabel.CENTER);
  private final JLabel l2 = new JLabel("", JLabel.CENTER);
  private final JDialog d;
  private final JButton[] button = new JButton[49];
  private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
  private String day = "";

  /**
   * The DatePicker constructor.
   *
   * @param parent frame
   */
  public DatePicker(JFrame parent) {
    d = new JDialog();
    d.setModal(true);
    String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 200));

    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(true);
      button[x].setBackground(Color.white);
      if (x > 6) {
        button[x].addActionListener(ae -> {
          day = button[selection].getActionCommand();
          d.dispose();
        });
      }
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
    JButton previous = new JButton("<< Previous");
    previous.addActionListener(ae -> {
      month--;
      displayDate();
    });
    p2.add(previous);
    p2.add(l1);
    JButton next = new JButton("Next >>");
    next.addActionListener(ae -> {
      month++;
      displayDate();
    });
    p2.add(next);

    JPanel p3 = new JPanel(new GridLayout(1, 3));
    JButton yearPrevious = new JButton("<<<< Previous");
    yearPrevious.addActionListener(ae -> {
      year--;
      displayDate();
    });
    p3.add(yearPrevious);
    p3.add(l2);
    JButton yearNext = new JButton("Next >>>>");
    yearNext.addActionListener(ae -> {
      year++;
      displayDate();
    });
    p3.add(yearNext);
    d.add(p1, BorderLayout.CENTER);
    JPanel pan = new JPanel();
    pan.setLayout(new BorderLayout());
    pan.add(p2, BorderLayout.NORTH);
    pan.add(p3, BorderLayout.SOUTH);
    d.add(pan, BorderLayout.SOUTH);
    d.pack();
    d.setLocationRelativeTo(parent);
    displayDate();
    d.setVisible(true);
  }

  private void displayDate() {
    for (int x = 7; x < button.length; x++) {
      button[x].setText("");
    }
    java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("MMMM");
    java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    d.pack();
    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
      try {
        TimeUnit.MICROSECONDS.sleep(100);
      } catch (Exception e) {
        // do nothing
      }
      button[x].setText("" + day);
    }
    d.pack();
    l1.setText(sdf1.format(cal.getTime()));
    l2.setText(sdf2.format(cal.getTime()));
    d.setTitle("Date Picker");
    d.pack();
  }

  /**
   * The setPickedDate method sets the date selected by the user.
   *
   * @return the selected date
   */
  public String setPickedDate() {
    if (day.equals("")) {
      return day;
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
            "yyyy-MM-dd");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }
}
