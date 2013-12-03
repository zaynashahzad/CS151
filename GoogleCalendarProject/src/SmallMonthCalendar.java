
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * 
 */
public class SmallMonthCalendar extends JPanel {    //model and controller of small month calendar

    private Controller controller;
    private JPanel monthCal;
    private ArrayList<JLabel> daysLabels;
    private JLabel monthTitle;
    private ArrayList<JLabel> weeksTitle;
    private JButton leftArrow, rightArrow, createEvent;
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    SmallMonthCalendar(Controller c, final Events events) {
        controller = c;
        monthCal = new JPanel();
        monthCal.setLayout(new GridLayout(0, 7));
        daysLabels = new ArrayList();
        monthTitle = new JLabel();

        leftArrow = new JButton("<");
        leftArrow.setBorder(null);
        rightArrow = new JButton(">");
        rightArrow.setBorder(null);
        createEvent = new JButton("CREATE");

        createEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateEvent ce = new CreateEvent(events);
                ce.setSize(250, 350);
                ce.setVisible(true);
            }
        });

        createEvent.setForeground(Color.red);

        addButtonActionListener(leftArrow);
        addButtonActionListener(rightArrow);

        JPanel lrPanel = new JPanel(new FlowLayout());
        lrPanel.add(leftArrow);
        lrPanel.add(rightArrow);

        weeksTitle = new ArrayList<JLabel>();

        weeksTitle.add(new JLabel("S", JLabel.CENTER));
        weeksTitle.add(new JLabel("M", JLabel.CENTER));
        weeksTitle.add(new JLabel("T", JLabel.CENTER));
        weeksTitle.add(new JLabel("W", JLabel.CENTER));
        weeksTitle.add(new JLabel("T", JLabel.CENTER));
        weeksTitle.add(new JLabel("F", JLabel.CENTER));
        weeksTitle.add(new JLabel("S", JLabel.CENTER));

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.add(createEvent, BorderLayout.WEST);
        topPanel.add(tempPanel, BorderLayout.NORTH);
        topPanel.add(monthTitle, BorderLayout.WEST);
        topPanel.add(lrPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        add(monthCal, BorderLayout.CENTER);
        showSmallMonthCal();
    }

    public void showSmallMonthCal() {
        ArrayList<String> days = showSmallCalendar();
        GregorianCalendar todaysDate = new GregorianCalendar();

        daysLabels.clear();
        monthCal.removeAll();


        for (String s : days) {
            if (s.contains("*")) {
                JLabel tempLabel = new JLabel(s.substring(1), JLabel.CENTER);
                tempLabel.setForeground(Color.lightGray);
                Font font = tempLabel.getFont();
                tempLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
                daysLabels.add(tempLabel);
            } else {

                if (Integer.parseInt(s) == todaysDate.get(GregorianCalendar.DAY_OF_MONTH)
                        && todaysDate.get(GregorianCalendar.MONTH) == controller.getCurMonth()
                        && todaysDate.get(GregorianCalendar.YEAR) == controller.getCurYear()) {
                    JLabel tempLabel = new JLabel(s, JLabel.CENTER);
                    Font font = tempLabel.getFont();
                    tempLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                    daysLabels.add(tempLabel);

                } else {
                    JLabel tempLabel = new JLabel(s, JLabel.CENTER);
                    Font font = tempLabel.getFont();
                    daysLabels.add(tempLabel);
                }
            }
        }
        for (JLabel jl : weeksTitle) {
            monthCal.add(jl);
        }

        for (JLabel jl : daysLabels) {
            jl.setOpaque(true);
            jl.setBackground(Color.white);
            monthCal.add(jl);
        }

        monthTitle.setText(months[controller.getCurMonth()] + " " + controller.getCurYear());
        addDaysLabelListener();
        monthCal.validate();
        monthCal.repaint();
    }

    private ArrayList<String> showSmallCalendar() {

        ArrayList<String> arr = new ArrayList();
        int current = controller.getCurDay();
        GregorianCalendar calendar = controller.getCalendar();
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

        /**
         * append days from previous month
         */
        int blanks = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        calendar.add(GregorianCalendar.MONTH, -1);
        for (int i = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - blanks + 1;
                i <= calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                i++) {
            arr.add("*" + String.valueOf(i));
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
            arr.add("*" + String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)));
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        calendar.add(GregorianCalendar.MONTH, -1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, current);
        return arr;
    }

    private void addButtonActionListener(final JButton button) {
        button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText().equals("<")) {
                    controller.prevMonth();
                } else {
                    controller.nextMonth();
                }
                showSmallMonthCal();
            }
        });
    }
    
    public void showToday(){
        controller.todayDate();
         showSmallMonthCal();
    }

    /**
     * Attaches mouse clicked listener to daysLabel.
     */
    public void addDaysLabelListener() {

        JLabel prevDay = null;
        int tempMonth = controller.getCurMonth();
        int tempYear = controller.getCurYear();

        if (!daysLabels.get(0).getText().equals("1")) {
            tempMonth = (tempMonth - 1) % 12;
            if (tempMonth == -1) {
                tempMonth = 11;
            }
        }
        else {
            if (controller.getCurMonth() == 0)
                tempYear--;
        }

        for (final JLabel jl : daysLabels) {
            if (prevDay != null) {
                if (Integer.parseInt(jl.getText()) < Integer.parseInt(prevDay.getText())) {
                    tempMonth = (tempMonth + 1) % 12;
                    if (tempMonth == 0)  //changing from Dec to Jan
                        tempYear++;
                }
            }
            prevDay = jl;

            final int tempMonthCopy = tempMonth;
            final int tempYearCopy = tempYear;
            jl.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controller.getCurView().showView(tempYearCopy, tempMonthCopy, Integer.parseInt(jl.getText()));
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jl.setBackground(new Color(222, 222, 222));
                    jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jl.setBackground(Color.white);
                }
            });
        }
    }
}
