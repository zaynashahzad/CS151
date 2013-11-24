
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class SmallMonthCalendar extends JPanel {

    private Controller controller;
    private GregorianCalendar calendar;
    private JPanel monthCal;
    private ArrayList<JLabel> daysLabels;

    SmallMonthCalendar(Controller c) {
        controller = c;
        calendar = new GregorianCalendar();
        monthCal = new JPanel();
        monthCal.setLayout(new GridLayout(0, 7));
        daysLabels = new ArrayList();

        add(monthCal);
        showSmallMonthCal();
    }

    private void showSmallMonthCal() {
        ArrayList<String> days = showSmallCalendar();
        GregorianCalendar todaysDate = new GregorianCalendar();

        daysLabels.add(new JLabel("S", JLabel.CENTER));
        daysLabels.add(new JLabel("M", JLabel.CENTER));
        daysLabels.add(new JLabel("T", JLabel.CENTER));
        daysLabels.add(new JLabel("W", JLabel.CENTER));
        daysLabels.add(new JLabel("T", JLabel.CENTER));
        daysLabels.add(new JLabel("F", JLabel.CENTER));
        daysLabels.add(new JLabel("S", JLabel.CENTER));

        for (String s : days) {
            if (s.contains("*")) {
                JLabel tempLabel = new JLabel(s.substring(1), JLabel.CENTER);
                tempLabel.setForeground(Color.lightGray);
                Font font = tempLabel.getFont();
                tempLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
                daysLabels.add(tempLabel);
            } else {

                if (Integer.parseInt(s) == todaysDate.get(GregorianCalendar.DAY_OF_MONTH)
                        && todaysDate.get(GregorianCalendar.MONTH) == calendar.get(GregorianCalendar.MONTH)
                        && todaysDate.get(GregorianCalendar.YEAR) == calendar.get(GregorianCalendar.YEAR)) {
                    JLabel tempLabel = new JLabel(s, JLabel.CENTER);
                    Font font = tempLabel.getFont();
                    tempLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
                    daysLabels.add(tempLabel);

                } else {
                    JLabel tempLabel = new JLabel(s, JLabel.CENTER);
                    Font font = tempLabel.getFont();
                    daysLabels.add(tempLabel);
                }
            }
        }
        for (JLabel jl : daysLabels) {
            monthCal.add(jl);
        }
    }

    private ArrayList<String> showSmallCalendar() {

        ArrayList<String> arr = new ArrayList();
        int current = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

        /**
         * append days from previous month
         */
        int blanks = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        calendar.add(GregorianCalendar.MONTH, -1);
        for (int i = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - blanks + 1;
                i <= calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                i++) {
            arr.add("*" + String.valueOf(i));
        }

        /**
         * append days from current month
         */
        calendar.add(GregorianCalendar.MONTH, 1);
        int numDays = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        for (int i = 1; i <= numDays; i++) {
            arr.add(String.valueOf(i));
        }

        /**
         * append days from next month
         */
        calendar.add(GregorianCalendar.MONTH, 1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
        while (calendar.get(GregorianCalendar.DAY_OF_WEEK) != 1) {
            arr.add("*" + String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)));
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        calendar.add(GregorianCalendar.MONTH, -1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, current);
        return arr;
    }
}
