
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

/**
 * CalendarFrame is the main JFrame that holds all views and main buttons
 *
 */
public class CalendarFrame extends JFrame implements ChangeListener {

    private Controller controller;
    private Events events;  // all the events currently scheduled
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

        // register this file as a listener for when events are added and removed
        events.registerListener(this);

        controller = new Controller(events);
        curView = dayView;
        controller.setCurView(dayView);

        final SmallMonthCalendar smallCal = new SmallMonthCalendar(controller, events);
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

        //today button action depends on the current view
        JButton todayButton = new JButton("Today");
        todayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smallCal.showToday();
                controller.getCurView().showToday();
            }
        });

        // < button action depends on current view
        JButton preMonthButton = new JButton("<");
        preMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getCurView().showPrev();
            }
        });

        // > button action depends on current view
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

        // change the current view to day -- default
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

        // change the current view to week
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

        // change the current view to month
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

        // change the current view to agenda
        JButton agendaButton = new JButton("Agenda");
        agendaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgendaView av = new AgendaView(events);
                curView = av;
                controller.setCurView(av);
                rightPanel.removeAll();
                rightPanel.invalidate();
                rightPanel.add(buttonsPanel, BorderLayout.NORTH);
                rightPanel.add(curView, BorderLayout.CENTER);
                rightPanel.validate();
                rightPanel.repaint();
            }
        });

        rightButtons.add(agendaButton);

        // allow the user to import recurring events from file
        final JButton fromFileButton = new JButton("From File");
        fromFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setLayout(new GridLayout(0, 1));
                frame.setVisible(true);
                frame.setSize(250, 100);
                final JLabel errMsg = new JLabel();
                JLabel jl = new JLabel("Enter the filename to use below.");
                final JTextField fileName = new JTextField();
                JButton useFile = new JButton("OK");
                useFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            controller.createRecurringEvents(fileName.getText());
                            frame.dispose();
                        } catch (Exception ex) {
                            errMsg.setText("Error: " + ex.getMessage());
                        }


                    }
                });

                errMsg.setForeground(Color.red);

                frame.add(jl);
                frame.add(fileName);
                frame.add(errMsg);

                frame.add(useFile);
            }
        });
        rightButtons.add(fromFileButton);

        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(curView, BorderLayout.CENTER);

        // add everything to current panel for viewing
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setTitle("Google Calendar");
    }

    /*
     * For testing purposes only... populate
     * the events treemap with events to display in calendar.
     */
    private void testEvents() {

        // year is from 0 to 8099. Month is from 0 to 11. Day is from 1 to 31
        Date date = new Date(2013 - 1900, 10, 3);
        DayEvents tempEvent = new DayEvents("Dentist Appointment", 8, 10, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Take cat to the vet", 13, 14, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Submit physics report", 19, 22, date);
        events.addEvent(date, tempEvent);

        tempEvent = new DayEvents("Zayna's surprise party", 15, 17, date);
        events.addEvent(date, tempEvent);

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

        date = new Date(2014 - 1900, 3, 18);
        tempEvent = new DayEvents("Final Exam", 16, 17, date);
        events.addEvent(date, tempEvent);

        date = new Date(2014 - 1900, 3, 20);
        tempEvent = new DayEvents("Some event with a really long name", 11, 14, date);
        events.addEvent(date, tempEvent);

        date = new Date(2013 - 1900, 11, 2);
        events.addEvent(date, new DayEvents("test yesterday", 1, 2, date));

        date = new Date(2013 - 1900, 11, 3);
        events.addEvent(date, new DayEvents("test today", 1, 2, date));

        date = new Date(2013 - 1900, 11, 30);
        for (int i = 0; i < 23; i++) {
            events.addEvent(date, new DayEvents("test agenda view " + i, i, i + 1, date));
        }
        date = new Date(2013 - 1900, 11, 31);
        for (int i = 0; i < 23; i++) {
            events.addEvent(date, new DayEvents("test more agenda view " + i + 1, i, i + 1, date));
        }

        Random rand = new Random();
        for (int i = 1; i <= 31; i++) {
            int num = rand.nextInt(5) + 0;
            for (int j = 0, n = 0; j < num; j++) {
                date = new Date(2014 - 1900, 0, i);
                events.addEvent(date, new DayEvents("random event " + j, n, n + 1, date));
                n += 2;
            }
        }

        for (int i = 1; i <= 31; i++) {
            int num = rand.nextInt(5) + 0;
            for (int j = 0, n = 0; j < num; j++) {
                date = new Date(2013 - 1900, 10, i);
                events.addEvent(date, new DayEvents("random event " + j, n, n + 1, date));
                n += 2;
            }
        }

        for (int i = 1; i <= 31; i++) {
            int num = rand.nextInt(3) + 0;
            for (int j = 0, n = 0; j < num; j++) {
                date = new Date(2013 - 1900, 11, i);
                events.addEvent(date, new DayEvents("random event " + j, n, n + 1, date));
                n += 2;
            }
        }

    }

    /**
     * If new events are added to model, refresh the current view automatically
     *
     * @param e The event that changed the state
     */
    public void stateChanged(ChangeEvent e) {
        if (!controller.getCurView().getClass().equals(AgendaView.class)) {
            controller.getCurView().showNext();
            controller.getCurView().showPrev();
        }
        rightPanel.removeAll();
        rightPanel.invalidate();
        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(curView, BorderLayout.CENTER);
        rightPanel.validate();
        rightPanel.repaint();
        this.repaint();
    }
}
