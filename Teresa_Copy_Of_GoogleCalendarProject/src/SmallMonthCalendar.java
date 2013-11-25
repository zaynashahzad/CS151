import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmallMonthCalendar extends JPanel {

    private Controller controller;
    private GregorianCalendar calendar;
    private JPanel monthCal;
    private ArrayList<JLabel> daysLabels;
    private JLabel monthTitle;

    public final static String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    SmallMonthCalendar(Controller c) {
        controller = c;
        calendar = new GregorianCalendar();
        monthCal = new JPanel();
        monthCal.setLayout(new GridLayout(0, 7));
        daysLabels = new ArrayList();
        monthTitle = new JLabel();

        setLayout(new BorderLayout());
        add(monthTitle, BorderLayout.NORTH);
        add(monthCal, BorderLayout.CENTER);
        showSmallMonthCal();
    }

    private void showSmallMonthCal() {
        ArrayList<String> days = showSmallCalendar();
        GregorianCalendar todaysDate = new GregorianCalendar();

        daysLabels.clear();
        monthCal.removeAll();

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

        monthTitle.setText(months[calendar.get(GregorianCalendar.MONTH)] + " " + calendar.get(GregorianCalendar.YEAR));

        monthCal.validate();
        monthCal.repaint();
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
//        while (calendar.get(GregorianCalendar.DAY_OF_WEEK) != 1) {
        while (arr.size() < 42) {
            arr.add("*" + String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)));
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        calendar.add(GregorianCalendar.MONTH, -1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, current);
        return arr;
    }

    /**
     * Increments the current calendar by one month
     */
    private void next() {
        calendar.add(GregorianCalendar.MONTH, 1);
        System.out.println(calendar.get(GregorianCalendar.MONTH));
    }

    /**
     * Decrements the current calendar by one month
     */
    private void prev() {
        calendar.add(GregorianCalendar.MONTH, -1);
        System.out.println(calendar.get(GregorianCalendar.MONTH));
    }

    private void today() {
        calendar.set(GregorianCalendar.YEAR, Calendar.getInstance().get(GregorianCalendar.YEAR));
        calendar.set(GregorianCalendar.MONTH, Calendar.getInstance().get(GregorianCalendar.MONTH));
        System.out.println(calendar.get(GregorianCalendar.MONTH));
    }

    public void addActionListener(final JButton button) {
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.getText().equals("Today")) {
                            today();
                        }
                        else if (button.getText().equals("<<")) {
                            prev();
                        }
                        else {
                            next();
                        }
                        showSmallMonthCal();
                    }
                }
        );
    }
}



