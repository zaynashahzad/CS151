/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AgendaView extends JFrame implements ActionListener, CalendarView {

    Border blackline = BorderFactory.createLineBorder(Color.black);
    JLabel dayTitle;
    JScrollPane scrollPane;
    JTable leftTable, rightTable, jTable;
    JPanel panel;
    TreeMap eventTree;
    Controller controller;
    Events events;
    ArrayList<DayEvents> eventList;
    Date date;
    public final static String[] months = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    public final static String[] daysOfWeek = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

//    private Events events;
    private JPanel innerPanel;
//    private JTextField eventNameTf;
    private JComboBox startMonthsPicker, startDaysPicker, startYearPicker;
    private JComboBox endMonthPicker, endDayPicker, endYearPicker;
//    private JComboBox startHourPicker, endHourPicker;
    private JLabel errorMsg;

    public AgendaView(Events event) {
//        eventTree = x.getTree();
//        dayTitle = new JLabel();
//        panel = new JPanel(new GridLayout(0, 1));
//        scrollPane = new JScrollPane(panel);
//        controller = new Controller();
//        this.events = x;
//
//        this.setLayout(new BorderLayout());
//        setUpAgenda();
        events = event;
        innerPanel  = new JPanel(new GridLayout(1, 9));

//        JLabel instructionLabel = new JLabel("Select events period");
        JLabel toLabel = new JLabel("to");

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        startMonthsPicker = new JComboBox(months);
        startMonthsPicker.setSelectedIndex(0);
        endMonthPicker = new JComboBox(months);
        endMonthPicker.setSelectedIndex(0);

        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = (i + 1) + "";
        }
        startDaysPicker = new JComboBox(days);
        startDaysPicker.setSelectedIndex(0);
        endDayPicker = new JComboBox(days);
        endDayPicker.setSelectedIndex(0);

        String[] years = new String[120];
        for (int i = 0; i < years.length; i++) {
            years[i] = (i + 1900) + "";
        }
        startYearPicker = new JComboBox(years);
        startYearPicker.setSelectedIndex(years.length - 6);
        endYearPicker = new JComboBox(years);
        endYearPicker.setSelectedIndex(years.length - 6);

        errorMsg = new JLabel("error", JLabel.CENTER);
        errorMsg.setForeground(Color.red);

        JButton submitButton = new JButton("Search");
        submitButton.addActionListener(this);

//        innerPanel.add(instrucLabel);
//        innerPanel.add(eventNameLab);
//        innerPanel.add(eventNameTf);
//        innerPanel.add(instructionLabel);
        innerPanel.add(startMonthsPicker);
        innerPanel.add(startDaysPicker);
        innerPanel.add(startYearPicker);
        innerPanel.add(toLabel);
        innerPanel.add(endMonthPicker);
        innerPanel.add(endDayPicker);
        innerPanel.add(endYearPicker);
        innerPanel.add(submitButton);

        // create some padding
        this.setLayout(new BorderLayout());

        innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(innerPanel, BorderLayout.CENTER);
        add(errorMsg, BorderLayout.SOUTH);

//        setSize(1000, 100);
        setTitle("Select agenda period");
        pack();
        setVisible(true);
    }

//    private void setUpAgenda() {
//
//        if (!eventTree.isEmpty()) {
//
//            Set keys = eventTree.keySet();
//            for (Iterator i = keys.iterator(); i.hasNext();) {
//                date = (Date) i.next();
//                eventList = (ArrayList<DayEvents>) eventTree.get(date);
//                System.out.println(date + " = ");
//
//                System.out.print(date.toString());
//                JPanel day = new JPanel(new BorderLayout());
//                String label = daysOfWeek[date.getDay()] + " " + months[date.getMonth()] + " " + date.getDay();
//                JButton dayLabel = new JButton(label);
//                day.add(dayLabel, BorderLayout.WEST);
//
//                day.setBorder(blackline);
//                JPanel eventView = new JPanel(new GridLayout(eventList.size(), 1));
//                eventView.setBorder(blackline);
//                for (DayEvents de : eventList) {
//                    JPanel thisEvent = new JPanel(new BorderLayout());
//                    thisEvent.setBorder(blackline);
//                    String timeLabel = de.getStartHour() + "-" + de.getEndHour() + "       ";
//                    JLabel time = new JLabel(timeLabel);
//                    thisEvent.add(time, BorderLayout.WEST);
//                    JLabel descript = new JLabel(de.getName());
//                    thisEvent.add(descript);
//                    eventView.add(thisEvent, BorderLayout.EAST);
//                    //day.add(eventView,BorderLayout.CENTER);
//                }
//                day.add(eventView, BorderLayout.CENTER);
//                panel.add(day);
//            }
//            JScrollPane scroll = new JScrollPane(panel);
//            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//            this.add(scroll, BorderLayout.CENTER);
//
//        }
//
//    }

    private void setStartDate(Date date) {}

    private void setEndDate(Date date) {}

    @Override
    public void showNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showPrev() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showToday() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showView(int year, int month, int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        errorMsg.setText("");
//        String eventName = eventNameTf.getText();
        int eventYear = Integer.parseInt((String) startYearPicker.getSelectedItem()) - 1900;
        int eventMon = Integer.parseInt((String) startMonthsPicker.getSelectedItem()) - 1;
        int eventDay = Integer.parseInt((String) startDaysPicker.getSelectedItem());

        Date eventDate = new Date(eventYear, eventMon, eventDay);
//        int eventStartHour = Integer.parseInt((String) startHourPicker.getSelectedItem());
//        int eventEndHour = Integer.parseInt((String) endHourPicker.getSelectedItem());

//        DayEvents newEvent = new DayEvents(eventName, eventStartHour, eventEndHour, eventDate);

//        if (events.addEvent(eventDate, newEvent)) {
//            errorMsg.setText("Success! New event created!");
//            this.setVisible(false);
//            this.dispose();
//        } else {
//            errorMsg.setText("A conflict exists! Try again!");
//        }
    }
}
