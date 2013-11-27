import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame {

    private Controller controller;
    private Events events;
    private JPanel curView; 

    public CalendarFrame() {
        controller = new Controller();
        events = new Events();

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
        rightPanel.add(dayview, BorderLayout.CENTER);

        setLayout(new GridLayout(1, 1));
        add(leftPanel);
        add(rightPanel);

    }

    
}
