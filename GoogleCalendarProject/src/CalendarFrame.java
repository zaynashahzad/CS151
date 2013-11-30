
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CalendarFrame extends JFrame {

    private Controller controller;
    private Events events; // model
    private JPanel curView;

    public CalendarFrame() {

        events = new Events();
        testEvents();
        curView = new DayView(events);
        controller = new Controller(events);

        SmallMonthCalendar smallCal = new SmallMonthCalendar(controller, events);

//        ArrayList<JLabel> daysLabel = new ArrayList<JLabel>();

        // leftPanel holds the small month calendar and "today", <, > buttons
        JPanel leftPanel = new JPanel();
//        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setLayout(new BorderLayout());

        JPanel tempPanel = new JPanel(new FlowLayout());
        tempPanel.add(smallCal);
        smallCal.setPreferredSize(new Dimension(250, 250));
        leftPanel.setBackground(Color.white);
        leftPanel.add(tempPanel, BorderLayout.CENTER);

        // rightPanel holds day, week, month, agenda views and the current view, file
        final JPanel rightPanel = new JPanel();
//        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setLayout(new BorderLayout());

        final JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayout(1, 5));

        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(1, 3));

        JButton todayButton = new JButton("Today");
        JButton preMonthButton = new JButton("<<");
        JButton nextMonthButton = new JButton(">>");
        leftButtons.add(todayButton);
        leftButtons.add(preMonthButton);
        leftButtons.add(nextMonthButton);

        final JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(leftButtons, BorderLayout.WEST);
        buttonsPanel.add(rightButtons, BorderLayout.EAST);

        JButton dayButton = new JButton("Day");
        dayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curView = new DayView(events);
                rightPanel.removeAll();
                rightPanel.invalidate();
                rightPanel.add(buttonsPanel, BorderLayout.NORTH);
                rightPanel.add(curView, BorderLayout.CENTER);
                rightPanel.validate();
                rightPanel.repaint();
            }
        });
        rightButtons.add(dayButton);

        JButton weekButton = new JButton("Week");
        weekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                            curView = new WeekView(events);
                rightPanel.removeAll();
                rightPanel.invalidate();
                rightPanel.add(buttonsPanel, BorderLayout.NORTH);
                rightPanel.add(curView, BorderLayout.CENTER);
                rightPanel.validate();
                rightPanel.repaint();
            }
        });
        rightButtons.add(weekButton);

        JButton monthButton = new JButton("Month");
        monthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curView = new MonthView(events);
                rightPanel.removeAll();
                rightPanel.invalidate();
                rightPanel.add(buttonsPanel, BorderLayout.NORTH);
                rightPanel.add(curView, BorderLayout.CENTER);
                rightPanel.validate();
                rightPanel.repaint();
            }
        });
        rightButtons.add(monthButton);

        JButton agendaButton = new JButton("Agenda");
        agendaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                             curView = new AgendaView(events);
                rightPanel.removeAll();
                rightPanel.invalidate();
                rightPanel.add(buttonsPanel, BorderLayout.NORTH);
                rightPanel.add(curView, BorderLayout.CENTER);
                rightPanel.validate();
                rightPanel.repaint();
            }
        });

        rightButtons.add(agendaButton);

        JButton fromFileButton = new JButton("From File");
        fromFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // something happens here.
            }
        });
        rightButtons.add(weekButton);
        rightButtons.add(fromFileButton);

        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(curView, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    /*
     * For testing purposes only, populate
     * the events treemap with events to display in calendar.
     */
    private void testEvents() {

        // year is from 0 to 8099. Month is from 0 to 11. Day is from 1 to 31
        Date date = new Date(2013-1900, 10, 29);
        DayEvents tempEvent = new DayEvents("Dentist Appointment", 8, 10, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Take cat to the vet", 13, 14, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Submit physics report", 19, 22, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Zayna's surprise party", 15, 17, date);
        events.addEvent(date, tempEvent);

//        tempEvent = new DayEvents("Test duplicate", 11, 12, date);
//        events.addEvent(date, tempEvent);

        date = new Date(2013-1900, 10, 30);
        tempEvent = new DayEvents("Concert w/ Robert", 12, 13, date);
        events.addEvent(date, tempEvent);

        date = new Date(2013-1900, 11, 25);
        tempEvent = new DayEvents("Christmas Party", 16, 20, date);
        events.addEvent(date, tempEvent);

        date = new Date(2014-1900, 1, 14);
        tempEvent = new DayEvents("Valentine's Day Dinner with Teresa", 16, 17, date);
        events.addEvent(date, tempEvent);
    }
}
