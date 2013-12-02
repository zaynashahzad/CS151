/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MonthView extends JPanel implements CalendarView {

    private ArrayList<JLabel> weeksTitle;
    private Events events;
    private MonthController controller;
    private JPanel monthCal;
    private JLabel monthTitle;
    private ColorInterface color; 
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public MonthView(Events events) {
        this.events = events;
        color = new MonthColorConcrete();
        this.setVisible(true);
        controller = new MonthController();
        weeksTitle = new ArrayList<JLabel>();
        monthCal = new JPanel();
        monthTitle = new JLabel();

        monthCal.setLayout(new GridLayout(0, 7));

        weeksTitle.add(new JLabel("Sun", JLabel.CENTER));
        weeksTitle.add(new JLabel("Mon", JLabel.CENTER));
        weeksTitle.add(new JLabel("Tue", JLabel.CENTER));
        weeksTitle.add(new JLabel("Wed", JLabel.CENTER));
        weeksTitle.add(new JLabel("Thu", JLabel.CENTER));
        weeksTitle.add(new JLabel("Fri", JLabel.CENTER));
        weeksTitle.add(new JLabel("Sat", JLabel.CENTER));

        monthCal.setBorder(BorderFactory.createLineBorder(Color.gray));

        setLayout(new BorderLayout());
        add(monthTitle, BorderLayout.NORTH);
        add(monthCal, BorderLayout.CENTER);
        showMonthCal();
    }

    private void showMonthCal() {

        final int STRLEN_DEFAULT = 15;
        ArrayList<DayEvents> eventsForOneDay = new ArrayList<DayEvents>();
        ArrayList<String> days = controller.showCalendar();
        GregorianCalendar todaysDate = new GregorianCalendar();

        GregorianCalendar cal = controller.getCalendar();

        monthCal.removeAll();

        for (JLabel jl : weeksTitle) {
            monthCal.add(jl);
        }

        for (String s : days) {

            JPanel tempPanel = new JPanel();
            tempPanel.setBackground(Color.WHITE);
            tempPanel.setLayout(new GridLayout(0, 1));
            tempPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

            JLabel label1 = new JLabel(s);
            JLabel[] eventLabels = new JLabel[3];
            for (int i = 0; i < eventLabels.length; i++) {
                eventLabels[i] = new JLabel("", JLabel.LEFT);
            }

            if (!s.equals(" ")) {
                if (Integer.parseInt(s) == todaysDate.get(GregorianCalendar.DAY_OF_MONTH)
                        && todaysDate.get(GregorianCalendar.MONTH) == controller.getCurMonth()
                        && todaysDate.get(GregorianCalendar.YEAR) == controller.getCurYear()) {
                    label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, (int) (label1.getFont().getSize() * 1.2)));

                }
                Date d = new Date(cal.get(GregorianCalendar.YEAR) - 1900, cal.get(GregorianCalendar.MONTH), Integer.parseInt(s));

                eventsForOneDay = events.getEventsForDate(d);
                if (eventsForOneDay != null && eventsForOneDay.size() > 0) {

                    if (eventsForOneDay.size() >= 3) {
                        for (int i = 0; i < eventLabels.length; i++) {
                            eventLabels[i].setText(eventsForOneDay.get(i).getName());
                            eventLabels[i].setHorizontalTextPosition(JLabel.LEFT);
                            eventLabels[i].setBackground(color.getColor());
                            eventLabels[i].setOpaque(true);
                            eventLabels[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
                        }
                    } else {
                        for (int i = 0; i < eventsForOneDay.size(); i++) {
                            eventLabels[i].setText(eventsForOneDay.get(i).getName());
                            eventLabels[i].setHorizontalTextPosition(JLabel.LEFT);
                            eventLabels[i].setBackground(color.getColor());
                            eventLabels[i].setOpaque(true);
                            eventLabels[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));

                        }
                    }
                }
            }

            tempPanel.add(label1);

            for (int i = 0; i < eventLabels.length; i++) {
                tempPanel.add(eventLabels[i]);
            }

            monthCal.add(tempPanel);
        }

        monthTitle.setText(months[controller.getCurMonth()] + " " + controller.getCurYear());
        monthCal.validate();
        monthCal.repaint();
    }

    public void showNext() {
        controller.nextMonth();
        showMonthCal();
    }

    public void showPrev() {
        controller.prevMonth();
        showMonthCal();
    }

    public void showToday() {
        controller.todayDate();
        showMonthCal();
    }

    public void showView(int year, int month, int day) {
        controller.setYear(year);
        controller.setMonth(month);
        controller.setCurDay(day);
        showMonthCal();
    }
}

class MonthController extends Controller {

    public ArrayList<String> showCalendar() {

        ArrayList<String> arr = new ArrayList();
        int current = getCurDay();
        GregorianCalendar calendar = getCalendar();
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

        /**
         * add blanks to line up day of week
         */
        int blanks = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        calendar.add(GregorianCalendar.MONTH, -1);
        for (int i = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - blanks + 1;
                i <= calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                i++) {
            arr.add(" ");
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
        while (arr.size() < 42) {
            arr.add(" ");
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        calendar.add(GregorianCalendar.MONTH, -1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, current);


        return arr;
    }
}