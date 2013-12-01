
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CalendarFrame extends JFrame implements ChangeListener {
    
    private Controller controller;
    private Events events;
    private JPanel curView;
    private DayView dayView;
    private WeekView weekView;
    private MonthView monthView;
    private AgendaView agendaView;
    final JPanel rightPanel, buttonsPanel;
    
    public CalendarFrame() {
        
        events = new Events();
        testEvents();
        
        dayView = new DayView(events);
        weekView = new WeekView(events);
        monthView = new MonthView(events);
        agendaView = new AgendaView(events);
        
        events.registerListener(this);
        
        controller = new Controller(events);
        curView = dayView;
        controller.setCurView(dayView);
        
        SmallMonthCalendar smallCal = new SmallMonthCalendar(controller, events);
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        
        JPanel tempPanel = new JPanel(new FlowLayout());
        tempPanel.add(smallCal);
        smallCal.setPreferredSize(new Dimension(250, 250));
        leftPanel.setBackground(Color.white);
        leftPanel.add(tempPanel, BorderLayout.CENTER);

        // rightPanel holds day, week, month, agenda views and the current view, file
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        
        final JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayout(1, 5));
        
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(1, 3));
        
        JButton todayButton = new JButton("Today");
        todayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getCurView().showToday();
            }
        });
        JButton preMonthButton = new JButton("<");
        preMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getCurView().showPrev();
            }
        });
        JButton nextMonthButton = new JButton(">");
        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getCurView().showNext();
            }
        });
        leftButtons.add(todayButton);
        leftButtons.add(preMonthButton);
        leftButtons.add(nextMonthButton);
        
        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(leftButtons, BorderLayout.WEST);
        buttonsPanel.add(rightButtons, BorderLayout.EAST);
        
        JButton dayButton = new JButton("Day");
        dayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curView = dayView;
                controller.setCurView(dayView);
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
                curView = weekView;
                controller.setCurView(weekView);
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
                curView = monthView;
                controller.setCurView(monthView);
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
                curView = agendaView;
                controller.setCurView(agendaView);
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
                final JFrame frame = new JFrame();
                frame.setLayout(new GridLayout(0, 1));
                frame.setVisible(true);
                frame.setSize(250,100);
                
                JLabel jl = new JLabel("Enter the filename to use below.");
                final JTextField fileName = new JTextField();
                JButton useFile = new JButton("OK");
                useFile.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent e) {
                        controller.createRecurringEvents(fileName.getText());
                        frame.dispose();
                    }
                });
                
                frame.add(jl);
                frame.add(fileName);
                frame.add(useFile);
                
                
                
            }
        });
        rightButtons.add(fromFileButton);

        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(curView, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setTitle("Google Calendar");
    }

    /*
     * For testing purposes only, populate
     * the events treemap with events to display in calendar.
     */
    private void testEvents() {

        // year is from 0 to 8099. Month is from 0 to 11. Day is from 1 to 31
        Date date = new Date(2013 - 1900, 10, 29);
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

        date = new Date(2013 - 1900, 10, 30);
        tempEvent = new DayEvents("Concert w/ Robert", 12, 13, date);
        events.addEvent(date, tempEvent);
        
        date = new Date(2013 - 1900, 11, 25);
        tempEvent = new DayEvents("Christmas Party", 16, 20, date);
        events.addEvent(date, tempEvent);
        
        date = new Date(2013 - 1900, 11, 31);
        tempEvent = new DayEvents("Test Last day of the Year", 1, 17, date);
        events.addEvent(date, tempEvent);
        
        date = new Date(2014 - 1900, 3, 14);
        tempEvent = new DayEvents("Dinner with Teresa", 16, 17, date);
        events.addEvent(date, tempEvent);
    }
    
    public void stateChanged(ChangeEvent e) {
        rightPanel.removeAll();
        rightPanel.invalidate();
        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(curView, BorderLayout.CENTER);
        rightPanel.validate();
        rightPanel.repaint();
    }
    
    
   
}
