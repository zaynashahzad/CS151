
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MonthView extends JPanel implements ChangeListener {

    private ArrayList<JLabel> weeksTitle;
    private Events events;
    private MonthController controller;
    private JPanel monthCal;
    private JLabel monthTitle;
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public MonthView(Events events) {
        this.events = events;
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

        setLayout(new BorderLayout());
        add(monthTitle, BorderLayout.NORTH);
        add(monthCal, BorderLayout.CENTER);
        showMonthCal();
    }

    private void showMonthCal() {
        final Color[] colors = {new Color(135, 150, 255), new Color(150, 206, 250)};

        int colorToUse = 0;
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
            tempPanel.setLayout(new GridLayout(0,1));

            JLabel label1 = new JLabel(s);
            JLabel[] eventLabels = new JLabel[3];
            for (int i = 0; i < eventLabels.length; i++) {
                eventLabels[i] = new JLabel("", JLabel.LEFT);
            }

            if (!s.equals(" ")) {
                colorToUse++;
                if (Integer.parseInt(s) == todaysDate.get(GregorianCalendar.DAY_OF_MONTH)
                        && todaysDate.get(GregorianCalendar.MONTH) == controller.getCurMonth()
                        && todaysDate.get(GregorianCalendar.YEAR) == controller.getCurYear()) {
                    tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));

                }
                Date d = new Date(cal.get(GregorianCalendar.YEAR) - 1900, cal.get(GregorianCalendar.MONTH), Integer.parseInt(s));

                eventsForOneDay = events.getEventsForDate(d);
                if (eventsForOneDay != null && eventsForOneDay.size() > 0) {

                    if (eventsForOneDay.size() >= 3) {
                        for (int i = 0; i < eventLabels.length; i++) {
                            eventLabels[i].setText(eventsForOneDay.get(i).getName());
                            eventLabels[i].setHorizontalTextPosition(JLabel.LEFT);
                            eventLabels[i].setBackground(colors[colorToUse%2]);
                            eventLabels[i].setOpaque(true);
                        }
                    } else {
                        for (int i = 0; i < eventsForOneDay.size(); i++) {
                            eventLabels[i].setText(eventsForOneDay.get(i).getName());
                            eventLabels[i].setHorizontalTextPosition(JLabel.LEFT);
                            eventLabels[i].setBackground(colors[colorToUse%2]);
                            eventLabels[i].setOpaque(true);
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

    public void stateChanged(ChangeEvent e) {
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