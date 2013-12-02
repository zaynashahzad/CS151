/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.*;
import javax.swing.border.Border;

public class AgendaView extends JPanel implements CalendarView {

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

    public AgendaView(Events x) {
        eventTree = x.getTree();
        dayTitle = new JLabel();
        panel = new JPanel(new GridLayout(0, 1));
        scrollPane = new JScrollPane(panel);
        controller = new Controller();
        this.events = x;

        this.setLayout(new BorderLayout());
        setUpAgenda();
    }

    private void setUpAgenda() {

        if (!eventTree.isEmpty()) {

            Set keys = eventTree.keySet();
            for (Iterator i = keys.iterator(); i.hasNext();) {
                date = (Date) i.next();
                eventList = (ArrayList<DayEvents>) eventTree.get(date);
                System.out.println(date + " = ");

                System.out.print(date.toString());
                JPanel day = new JPanel(new BorderLayout());
                String label = daysOfWeek[date.getDay()] + " " + months[date.getMonth()] + " " + date.getDay();
                JButton dayLabel = new JButton(label);
                day.add(dayLabel, BorderLayout.WEST);

                day.setBorder(blackline);
                JPanel eventView = new JPanel(new GridLayout(eventList.size(), 1));
                eventView.setBorder(blackline);
                for (DayEvents de : eventList) {
                    JPanel thisEvent = new JPanel(new BorderLayout());
                    thisEvent.setBorder(blackline);
                    String timeLabel = de.getStartHour() + "-" + de.getEndHour() + "       ";
                    JLabel time = new JLabel(timeLabel);
                    thisEvent.add(time, BorderLayout.WEST);
                    JLabel descript = new JLabel(de.getName());
                    thisEvent.add(descript);
                    eventView.add(thisEvent, BorderLayout.EAST);
                    //day.add(eventView,BorderLayout.CENTER);
                }
                day.add(eventView, BorderLayout.CENTER);
                panel.add(day);
            }
            JScrollPane scroll = new JScrollPane(panel);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(scroll, BorderLayout.CENTER);

        }

    }


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
}
