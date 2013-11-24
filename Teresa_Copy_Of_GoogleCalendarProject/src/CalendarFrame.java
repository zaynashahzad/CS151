
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarFrame extends JFrame {

    private Controller controller;
    private Events events;

    CalendarFrame() {
        controller = new Controller();
        events = new Events();

        SmallMonthCalendar smallCal = new SmallMonthCalendar(controller);
        DayView dayview = new DayView();

        // leftPanel holds the small month calendar and "today", <, > buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setLayout(new BorderLayout());

        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(1, 3));

        leftButtons.add(new JButton("Today"));
        leftButtons.add(new JButton("<<"));
        leftButtons.add(new JButton(">>"));

        leftPanel.add(leftButtons, BorderLayout.NORTH);
        leftPanel.add(smallCal, BorderLayout.CENTER);

        // rightPanel holds day, week, month, agenda views and the current view, file
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayout(1,5));

        rightButtons.add(new JButton("Day"));
        rightButtons.add(new JButton("Week"));
        rightButtons.add(new JButton("Month"));
        rightButtons.add(new JButton("Agenda"));
        rightButtons.add(new JButton("From File"));

        rightPanel.add(rightButtons, BorderLayout.NORTH);
        rightPanel.add(dayview, BorderLayout.CENTER);

        add(leftPanel);
        add(rightPanel);

    }
}
