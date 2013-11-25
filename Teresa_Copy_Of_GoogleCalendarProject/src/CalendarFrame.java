import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame {

    private Controller controller;
    private Events events;

    public CalendarFrame() {
        controller = new Controller();
        events = new Events();

        final SmallMonthCalendar smallCal = new SmallMonthCalendar(controller);
        DayView dayview = new DayView();

        // leftPanel holds the small month calendar and "today", <, > buttons
        final JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setLayout(new BorderLayout());

        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(1, 3));

//        leftButtons.add(new JButton("Today"));
//        leftButtons.add(new JButton("<<"));
//        leftButtons.add(new JButton(">>"));
        JButton todayButton = new JButton("Today");
        smallCal.addActionListener(todayButton);
//        todayButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        smallCal.today();
//                        smallCal.showSmallMonthCal();
//                    }
//                }
//        );
        JButton preMonthButton = new JButton("<<");
        smallCal.addActionListener(preMonthButton);
//        preMonthButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        smallCal.prev();
//                        smallCal.showSmallMonthCal();
//                    }
//                }
//        );
        JButton nextMonthButton = new JButton(">>");
        smallCal.addActionListener(nextMonthButton);
//        nextMonthButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        smallCal.next();
//                        smallCal.showSmallMonthCal();
//                    }
//                }
//        );

        leftButtons.add(todayButton);
        leftButtons.add(preMonthButton);
        leftButtons.add(nextMonthButton);


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
