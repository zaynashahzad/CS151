import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CalendarFrame extends JFrame {

    private Controller controller;
    private Events events;
    private JFrame curView;

    public CalendarFrame() {
        controller = new Controller();
        events = new Events();
        testEvents();

        SmallMonthCalendar smallCal = new SmallMonthCalendar(controller);

//        ArrayList<JLabel> daysLabel = new ArrayList<JLabel>();

        // leftPanel holds the small month calendar and "today", <, > buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 200));

        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(1, 3));

        JButton todayButton = new JButton("Today");
        smallCal.addActionListener(todayButton);

        JButton preMonthButton = new JButton("<<");
        smallCal.addActionListener(preMonthButton);

        JButton nextMonthButton = new JButton(">>");
        smallCal.addActionListener(nextMonthButton);

        leftButtons.add(todayButton);
        leftButtons.add(preMonthButton);
        leftButtons.add(nextMonthButton);

        leftPanel.add(leftButtons, BorderLayout.NORTH);
        leftPanel.add(smallCal, BorderLayout.CENTER);

        // rightPanel holds day, week, month, agenda views and the current view, file
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setLayout(new BorderLayout());

        JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayout(1,5));

//        rightButtons.add(new JButton("Day"));
        JButton dayButton = new JButton("Day");
        rightButtons.add(dayButton);
        curView = new DayView(dayButton);
        DayView dayview = (DayView)curView;
        DayController dayController = new DayController(dayview);
        
        rightButtons.add(new JButton("Week"));
        rightButtons.add(new JButton("Month"));
        rightButtons.add(new JButton("Agenda"));
        rightButtons.add(new JButton("From File"));

        rightPanel.add(rightButtons, BorderLayout.NORTH);
        rightPanel.add(dayview.getContentPane(), BorderLayout.CENTER);

        setLayout(new GridLayout(1, 1));
        add(leftPanel);
        add(rightPanel);

    }

    /*
     * For testing purposes only, populate
     * the events treemap with events to display in calendar.
     */
    private void testEvents() {

        // year is from 0 to 8099. Month is from 0 to 11. Day is from 1 to 31
        Date date = new Date(2013, 10, 26);
        DayEvents tempEvent = new DayEvents("Dentist Appointment", 800, 1000, date);
        events.addEvent(date, tempEvent);
        
        tempEvent = new DayEvents("Take cat to the vet", 1300, 1400, date);
        events.addEvent(date, tempEvent);
        
        tempEvent = new DayEvents("Zayna's surprise party", 1500, 1700, date);
        events.addEvent(date, tempEvent);
        
        tempEvent = new DayEvents("Submit physics report", 1900, 2200, date);
        events.addEvent(date, tempEvent);

        date = new Date(2013, 10, 30);
        tempEvent = new DayEvents("Concert w/ Robert", 1100, 1300, date);
        events.addEvent(date, tempEvent);
        
        date = new Date(2013, 11, 25);
        tempEvent = new DayEvents("Christmas Party", 1600, 2000, date);
        events.addEvent(date, tempEvent);

        date = new Date(2014, 1, 14);
        tempEvent = new DayEvents("Valentine's Day Dinner with Teresa", 1600, 1700, date);
        events.addEvent(date, tempEvent);
        
    }
    
    
}
