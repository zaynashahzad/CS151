
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * Controller class is the frontend (user interface) that will get all inputs
 *
 * @author Zayna Shahzad
 */
public final class Controller extends JFrame {

    GregorianCalendar calendar;
    EventsList eventManager;
    Month monthView;
    Day dayView;
    JPanel leftPanel, rightPanel;
    JPanel rightTopNav = new JPanel();
    JPanel dayPanel = new JPanel();
    JPanel monthPanel = new JPanel();
    // variables that are needed by inner classes and actionListeners
    final ArrayList<JLabel> daysLabels = new ArrayList();
    final JPanel monthCal = new JPanel();
    final JLabel leftTopLabel = new JLabel();
    final JLabel rightTopLabel = new JLabel();
    final JTextArea dayEvents = new JTextArea(5, 20);
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    public final static String[] daysOfWeek = {
        "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"
    };

    public Controller() {
        eventManager = new EventsList();
        monthView = new Month();
        dayView = new Day(eventManager);
        calendar = new GregorianCalendar();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());

        // set up left and right JPanels
        setUpLefty();
        setUpRighty();
        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
         //eventManager.testerCases(); 
        //uncomment above line out for sample events already in event list
    }

    /**
     * Sets up the month view and appropriate buttons for input handling
     */
    public void setUpLefty() {
        monthPanel.setLayout(new BorderLayout());
        JPanel leftTopNav = new JPanel();

        // TOP NAVIGATION AND LABEL
        leftTopNav.setLayout(new BorderLayout());

        // left anf right buttons to navigate in the month view
        JButton prevMonButton = new JButton("<");
        prevMonButton.addActionListener(newMListener('p'));
        JButton nextMonButton = new JButton(">");
        nextMonButton.addActionListener(newMListener('n'));

        leftTopNav.add(leftTopLabel, BorderLayout.CENTER);
        leftTopNav.add(prevMonButton, BorderLayout.WEST);
        leftTopNav.add(nextMonButton, BorderLayout.EAST);

        //ACTUAL MONTH CALENDAR PART
        monthCal.setLayout(new GridLayout(0, 7));
        paintMonth();
        monthPanel.add(leftTopNav, BorderLayout.NORTH);
        monthPanel.add(monthCal, BorderLayout.CENTER);
        // END OF MONTH PANEL

        // START OF KEYS PANEL
        // create all the different buttons for the different cases in assignment spec
        JPanel keysPanel = new JPanel();
        keysPanel.setLayout(new GridLayout(2, 1));
        JButton createButton = new JButton("Create Event");
        createButton.addActionListener(newKeysListener('c'));
        keysPanel.add(createButton);

        JButton goToButton = new JButton("Go To Day...");
        goToButton.addActionListener(newKeysListener('g'));
        keysPanel.add(goToButton);

        JButton eventsButton = new JButton("Event list");
        eventsButton.addActionListener(newKeysListener('e'));
        keysPanel.add(eventsButton);

        JButton deleteButton = new JButton("Delete Event(s)");
        deleteButton.addActionListener(newKeysListener('d'));
        keysPanel.add(deleteButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(newKeysListener('q'));
        keysPanel.add(quitButton);

        // add everything to the panel
        leftPanel.add(monthPanel, BorderLayout.NORTH);
        leftPanel.add(keysPanel, BorderLayout.SOUTH);

    }

    /**
     * Makes labels for each day of the current month and displays it Current
     * day is bolded
     */
    public void paintMonth() {
        // clear anything that might already be in the panel
        daysLabels.clear();
        monthCal.removeAll();
        leftTopLabel.setText(months[monthView.getCurMonth()] + " " + monthView.getCurYear());

        for (String s : daysOfWeek) {
            monthCal.add(new JLabel(s));
        }
        ArrayList<String> daysArr = monthView.show();

        for (String s : daysArr) {
            if (!s.equals(" ")
                    && Integer.parseInt(s) == calendar.get(GregorianCalendar.DAY_OF_MONTH) && monthView.getCurMonth() == calendar.get(GregorianCalendar.MONTH) && monthView.getCurYear() == calendar.get(GregorianCalendar.YEAR)) {
                JLabel tempLabel = new JLabel(s);
                tempLabel.setForeground(Color.red);
                Font font = tempLabel.getFont();
                tempLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
                daysLabels.add(tempLabel);
            } else {
                daysLabels.add(new JLabel(s));
            }
        }
        for (JLabel jl : daysLabels) {
            monthCal.add(jl);
        }
        monthPanel.repaint();
    }

    /**
     * Makes labels for the current day displays it; Shows all the events of the
     * day in sorted order
     */
    public void paintDay() {
        dayEvents.removeAll();
        // get the current day
        rightTopLabel.setText(months[dayView.getCurMonth()]
                + " " + dayView.getCurDay() + ", " + dayView.getCurYear());
        dayEvents.setEditable(false);
        dayEvents.setLineWrap(true);
        dayEvents.setOpaque(false);
        // get all the events for today
        dayEvents.setText(dayView.show());
        dayPanel.repaint();
    }

    /**
     * Makes an actionListener for the month view buttons
     *
     * @param c n for next, p for previous month
     * @return ActionListener that will be tied to a button
     */
    public ActionListener newMListener(final char c) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (c == 'p') {
                    monthView.prev();
                } else if (c == 'n') {
                    monthView.next();
                }
                paintMonth();
            }
        };
    }

    /**
     * Makes an actionListener for the day view buttons
     *
     * @param c n for next, p for previous day
     * @return ActionListener that will be tied to a button
     */
    public ActionListener newDListener(final char c) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (c == 'p') {
                    dayView.prev();
                } else if (c == 'n') {
                    dayView.next();
                }
                paintDay();
            }
        };
    }

    /**
     * Makes and returns an action listener for the main buttons and options of
     * program. Each opens a new popup window and performs some action
     *
     * @param c e for event list, d for deleting events, g for go to date, or
     * quit
     * @return ActionListener that will be tied to a button
     */
    public ActionListener newKeysListener(final char c) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (c == 'c') {
                    CreateEventFrame ef = new CreateEventFrame(eventManager);
                    ef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    ef.setSize(300, 300);
                    ef.setVisible(true);
                } else if (c == 'e') {
                    ViewEventsFrame vef = new ViewEventsFrame(eventManager);
                    vef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    vef.pack();
                    vef.setVisible(true);
                } else if (c == 'd') {
                    DeleteEventFrame def = new DeleteEventFrame(eventManager);
                    def.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    def.pack();
                    def.setVisible(true);
                } else if (c == 'g') {
                    ViewDayFrame vdf = new ViewDayFrame(eventManager);
                    vdf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    vdf.pack();
                    vdf.setVisible(true);
                } else {
                    try {
                        eventManager.writeToFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
            }
        };
    }

    /**
     * Sets up the day view on the right hand side of screen
     */
    public void setUpRighty() {
        rightTopNav.setLayout(new BorderLayout());

        paintDay();
        JButton prevDayButton = new JButton("<<");
        prevDayButton.addActionListener(newDListener('p'));

        JButton nextDayButton = new JButton(">>");
        nextDayButton.addActionListener(newDListener('n'));

        rightTopNav.add(rightTopLabel, BorderLayout.CENTER);
        rightTopNav.add(prevDayButton, BorderLayout.WEST);
        rightTopNav.add(nextDayButton, BorderLayout.EAST);

        dayPanel.add(dayEvents);
        rightPanel.add(rightTopNav, BorderLayout.NORTH);
        rightPanel.add(dayPanel, BorderLayout.CENTER);

    }


    final class ViewEventsFrame extends JFrame {

        EventsList events;

        ViewEventsFrame(EventsList ev) {
            this.events = ev;
            eventsView();
        }

        public void eventsView() {
            JPanel eventsViewPan = new JPanel();

            JTextArea showAllEvents = new JTextArea(30, 30);
            showAllEvents.setText(events.getAll());
            JScrollPane scroll = new JScrollPane(showAllEvents);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            eventsViewPan.add(scroll);
            showAllEvents.setEditable(false);

            this.add(eventsViewPan);
        }
    }

    final class ViewDayFrame extends JFrame implements ActionListener {

        EventsList events;
        final JLabel errorMsg = new JLabel("");
        final JTextField askDayField = new JTextField();

        ViewDayFrame(EventsList ev) {
            this.events = ev;
            askDay();
        }

        public void askDay() {
            JPanel askDayPan = new JPanel();
            askDayPan.setLayout(new GridLayout(0, 1));
            JLabel askDayLab = new JLabel("  Enter the day: Format must be [MM/DD/YYYY]  ");

            JButton askDayButt = new JButton("Go!");
            askDayButt.addActionListener(this);

            askDayPan.add(errorMsg);
            askDayPan.add(askDayLab);
            askDayPan.add(askDayField);
            askDayPan.add(askDayButt);

            this.add(askDayPan);
        }

        public void actionPerformed(ActionEvent e) {
            {
                errorMsg.setText("");

                String eventDate = askDayField.getText();
                Pattern regex = Pattern.compile("\\b[0-9]{2}/[0-9]{2}/[0-9]{4}\\b");
                Matcher match = regex.matcher(eventDate);
                if (match.matches()) {
                    int m = Integer.parseInt(eventDate.substring(0, 2)) - 1;
                    int d = Integer.parseInt(eventDate.substring(3, 5));
                    int y = Integer.parseInt(eventDate.substring(6));

                    int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                    if (y > 0 && m < 12 && m >= 0) {
                        int checkDay = days[m];
                        if (m == 1 && y % 4 == 0) {
                            checkDay = 29;
                        }
                        if (d <= checkDay && d > 0) {
                            dayView.goTo(y, m, d);
                            paintDay();

                            this.setVisible(false);
                            this.dispose();
                        } else {
                            errorMsg.setForeground(Color.red);
                            errorMsg.setText("Error: Incorrect day");
                        }

                    } else {
                        errorMsg.setForeground(Color.red);
                        errorMsg.setText("Error: Incorrect year or month");
                    }
                } else {
                    errorMsg.setForeground(Color.red);
                    errorMsg.setText("Error: Incorrect format");
                }
            }
        }
    }

    final class CreateEventFrame extends JFrame implements ActionListener {

        EventsList events;
        final JLabel errorMsg = new JLabel("Fill in the following: ");
        final JTextField eventNameField = new JTextField();
        final JTextField eventDateField = new JTextField("MM/DD/YYYY");
        final JTextField eventStartField = new JTextField("ex. 0600");
        final JTextField eventEndField = new JTextField("");

        CreateEventFrame(EventsList ev) {
            this.events = ev;
            eventDetails();
        }

        public void eventDetails() {
            JPanel askDetails = new JPanel();
            askDetails.setLayout(new GridLayout(0, 1));

            JLabel eventNameLab = new JLabel(" Event Name ");

            JLabel eventDateLab = new JLabel(" Event Date ");

            JLabel eventStartLab = new JLabel(" Start Time (24-hour format) ");

            JLabel eventEndLab = new JLabel(" End Time (optional)");

            JButton createEventBut = new JButton(" Create");
            createEventBut.addActionListener(this);

            askDetails.add(errorMsg);
            askDetails.add(eventNameLab);
            askDetails.add(eventNameField);
            askDetails.add(eventDateLab);
            askDetails.add(eventDateField);
            askDetails.add(eventStartLab);
            askDetails.add(eventStartField);
            askDetails.add(eventEndLab);
            askDetails.add(eventEndField);
            askDetails.add(createEventBut);

            this.add(askDetails);
        }

        public boolean validateInfo(String name, int m, int d, int y, int start, int end) {
            int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (start < 0 || end > 2359) {
                return false;
            }

            if (m > 12 || m < 1) {
                return false;
            }
            if (m == 2 && (y % 4 == 0)) {
                if (d > 29 || d < 1) {
                    return false;
                }
            } else if (days[m - 1] < d || d < 1) {
                return false;
            }

            if (name == null) {
                return false;
            }
            if (end != -1) {
                if (end < start) {
                    return false;
                }
            }
            return true;
        }

        public void actionPerformed(ActionEvent e) {
            errorMsg.setText(" ");

            String eName = eventNameField.getText();
            String eventDate = eventDateField.getText();

            Pattern regex = Pattern.compile("\\b[0-9]{2}/[0-9]{2}/[0-9]{4}\\b");
            Matcher match = regex.matcher(eventDate);
            if (match.matches()) {
                int month = Integer.parseInt(eventDate.substring(0, 2));
                int day = Integer.parseInt(eventDate.substring(3, 5));
                int year = Integer.parseInt(eventDate.substring(6));

                Pattern regex2 = Pattern.compile("\\b[0-9]{4}\\b");
                Matcher match2 = regex2.matcher(eventStartField.getText());

                if ((regex2.matcher(eventStartField.getText())).matches() && (eventEndField.getText().equals("") || regex2.matcher(eventEndField.getText()).matches())) {
                    int startTime = Integer.parseInt(eventStartField.getText());
                    int endTime;

                    if (!eventEndField.getText().equals("")) {
                        endTime = Integer.parseInt(eventEndField.getText());
                    } else {
                        endTime = -1;
                    }

                    if (validateInfo(eName, month, day, year, startTime, endTime)) {
                        if (events.create(eName, month - 1, day, year, startTime, endTime)) {

                            this.setVisible(false);

                        } else {
                            errorMsg.setForeground(Color.red);
                            errorMsg.setText(" ERROR: CONFLICT EXISTS ");
                        }
                    } else {
                        errorMsg.setForeground(Color.red);
                        errorMsg.setText(" ERROR: Verify all details & try again. ");
                    }
                } else {
                    errorMsg.setForeground(Color.red);
                    errorMsg.setText(" ERROR: Incorrect time format!");
                }

            } else {
                errorMsg.setForeground(Color.red);
                errorMsg.setText(" ERROR: Incorrect date format! ");
            }
            paintDay();

        }
    }

    final class DeleteEventFrame extends JFrame {

        EventsList events;

        DeleteEventFrame(EventsList ev) {
            this.events = ev;
            deleteOneOrAll();
        }

        public void deleteOneOrAll() {
            final JPanel deletePan = new JPanel();
            deletePan.setLayout(new GridLayout(0, 1));
            JLabel askDayLab = new JLabel("Delete events on specific date: ");
            final JLabel errorMsg = new JLabel("");
            final JTextField askDayField = new JTextField();
            JButton askDayButt = new JButton("Delete selected events");
            JButton deleteAllButt = new JButton("Delete all events");
            deleteAllButt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    errorMsg.setText("All events deleted!");
                    eventManager.deleteAll();
                    closeWindow();
                }
            });

            askDayButt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    errorMsg.setText("");
                    String eventDate = askDayField.getText();
                    Pattern regex = Pattern.compile("\\b[0-9]{2}/[0-9]{2}/[0-9]{4}\\b");
                    Matcher match = regex.matcher(eventDate);
                    if (match.matches()) {
                        int m = Integer.parseInt(eventDate.substring(0, 2)) - 1;
                        int d = Integer.parseInt(eventDate.substring(3, 5));
                        int y = Integer.parseInt(eventDate.substring(6));

                        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                        if (y > 0 && m < 12 && m >= 0) {
                            int checkDay = days[m];
                            if (m == 1 && y % 4 == 0) {
                                checkDay = 29;
                            }
                            if (d <= checkDay && d > 0) {
                                if (eventManager.hasEntry(new EventDate(y, m, d))) {
                                    eventManager.deleteOne(y, m, d);
                                    errorMsg.setText((m + 1) + "/" + d + "/" + y + " events deleted");
                                    paintDay();
                                    closeWindow();
                                } else {
                                    errorMsg.setText("No events for this day!");

                                }
                            } else {
                                errorMsg.setForeground(Color.red);
                                errorMsg.setText("Error: Incorrect day");
                            }

                        } else {
                            errorMsg.setForeground(Color.red);
                            errorMsg.setText("Error: Incorrect year or month");
                        }
                    } else {
                        errorMsg.setForeground(Color.red);
                        errorMsg.setText("Error: Incorrect format");
                    }
                }
            });

            deletePan.add(errorMsg);
            deletePan.add(askDayLab);
            deletePan.add(askDayField);
            deletePan.add(askDayButt);
            deletePan.add(deleteAllButt);

            this.add(deletePan);
        }

        public void closeWindow() {
            this.setVisible(false);
        }
    }
}