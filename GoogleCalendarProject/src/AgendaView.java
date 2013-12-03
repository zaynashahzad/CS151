/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
/**
 * The AgendaView class houses all the events to be displayed during a given date range
 */
public class AgendaView extends JPanel implements CalendarView {

    Events events;
    int sYear, sMonth, sDay, eYear, eMonth, eDay;
    ArrayList<DayEvents> eventsList;
    AgendaController agendaController;
    JTable leftTable, rightTable;
    JPanel panel;
    JScrollPane scrollPane;

    public final static String[] months = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    public final static String[] daysOfWeek = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

    /**
     * Sets up and displays all the events for a given period of time
     * @param event A treemap of all the events currently in the calendar
     */
    public AgendaView(Events event) {
        agendaController = new AgendaController();
        eventsList = new ArrayList<DayEvents>();
        panel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(panel);
        this.events = event;
        this.setLayout(new BorderLayout());
        new AgendaViewFrame(events);
    }

    /**
     * Puts all the events in the time frame into eventsList arrayList
     */
    private void getEventsList() {
        eventsList.clear();
        while (agendaController.getCurYear() != eYear ||
                agendaController.getCurMonth() != eMonth ||
                agendaController.getCurDay() != eDay) {

            ArrayList<DayEvents> tempList = events.getEventsForDate(agendaController.getDate());
            if (tempList != null) { // found events for this day
                for (DayEvents de : tempList) {
                    eventsList.add(de);
                }
            }

            agendaController.nextDay();
        }
        // get events for the last date in time frame
        ArrayList<DayEvents> tempList = events.getEventsForDate(agendaController.getDate());
        if (tempList != null) {
            for (DayEvents de : tempList) {
                eventsList.add(de);
            }
        }

        agendaController.todayDate();
    }

    /**
     * 
     */
    private void setLeftTable() {
        Object[][] obj = new Object[eventsList.size()][1];
        Object[] temp = {""};

        int i = 0;
        Date date = new Date();
        for (DayEvents de : eventsList) {
            if (de.getDate().equals(date)) {
                obj[i][0] = "";
            } else {
                date = de.getDate();
                String s = daysOfWeek[de.getDate().getDay()]
                        + " " + months[de.getDate().getMonth()]
                        + " " + de.getDate().getDate();
                obj[i][0] = s;
            }
            i++;
        }

        leftTable = new JTable(obj, temp);
        leftTable.setTableHeader(null);
        leftTable.setRowHeight(40);
        leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        leftTable.setShowGrid(false);
        leftTable.setEnabled(false);
    }

    private void setRightTable() {
        Object[][] obj = new Object[24][1];
        Object[] temp = {""};

        int i = 0;
        for (DayEvents de : eventsList) {
            obj[i][0] = de.getName();
            i++;
        }
        rightTable = new JTable(obj, temp);
        if (eventsList.size() == 0) {
            obj[0][0] = "None";
            rightTable.setShowGrid(false);
        }
        else
            rightTable.setShowGrid(true);

        rightTable.setTableHeader(null);
        rightTable.setRowHeight(40);
        rightTable.setGridColor(Color.lightGray);
        rightTable.setEnabled(false);
    }

    public void showAgendaView() {
        this.invalidate();
        panel.removeAll();

        getEventsList();
        setLeftTable();
        setRightTable();

        panel.add(leftTable, BorderLayout.WEST);
        panel.add(rightTable, BorderLayout.CENTER);

        this.add(scrollPane, BorderLayout.CENTER);
        this.validate();
        this.repaint();

    }

    class AgendaViewFrame extends JFrame implements ActionListener {

        private JPanel innerPanel;
        private JComboBox startMonthsPicker, startDaysPicker, startYearPicker;
        private JComboBox endMonthPicker, endDayPicker, endYearPicker;
        private JLabel errorMsg;
        private Events events;

        AgendaViewFrame(Events event) {

            this.events = event;
            innerPanel  = new JPanel(new GridLayout(1, 9));

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

            errorMsg = new JLabel("   ", JLabel.CENTER);
            errorMsg.setForeground(Color.red);

            JButton submitButton = new JButton("Search");
            submitButton.addActionListener(this);

            innerPanel.add(startMonthsPicker);
            innerPanel.add(startDaysPicker);
            innerPanel.add(startYearPicker);
            innerPanel.add(toLabel);
            innerPanel.add(endMonthPicker);
            innerPanel.add(endDayPicker);
            innerPanel.add(endYearPicker);
            innerPanel.add(submitButton);

            this.setLayout(new BorderLayout());
            innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            add(innerPanel, BorderLayout.CENTER);
            add(errorMsg, BorderLayout.SOUTH);
            setTitle("Select agenda period");
            pack();
            setVisible(true);
        }

        private boolean validateInput(int sYear, int sMonth, int sDay, int eYear, int eMonth, int eDay) {
            System.out.println("in validateInput " + sMonth + "/" + sDay + " " + sYear + " to " + eMonth + "/" + eDay + " " + eYear);
            if (sYear > eYear)
                return false;
            else if (sMonth > eMonth)
                return false;
            else if (sDay > eDay)
                return false;
            else
                return true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            errorMsg.setText("");
            int startYear = Integer.parseInt((String) startYearPicker.getSelectedItem());// - 1900;
            int startMonth = Integer.parseInt((String) startMonthsPicker.getSelectedItem()) - 1;
            int startDay = Integer.parseInt((String) startDaysPicker.getSelectedItem());
            int endYear = Integer.parseInt((String) endYearPicker.getSelectedItem());// - 1900;
            int endMonth = Integer.parseInt((String) endMonthPicker.getSelectedItem()) - 1;
            int endDay = Integer.parseInt((String) endDayPicker.getSelectedItem());

            System.out.println(startMonth + "/" + startDay + " " + startYear + " to " + endMonth + "/" + endDay + " " + endYear);

            if (validateInput(startYear, startMonth, startDay, endYear, endMonth, endDay)) {
                System.out.println(validateInput(startYear, startMonth, startDay, endYear, endMonth, endDay));
                setStartDate(startYear, startMonth, startDay);
                setEndDate(endYear, endMonth, endDay);
                showAgendaView();
                this.setVisible(false);
                this.dispose();
            }
            else {
                errorMsg.setText("Invalid period! Try again!");
            }

        }
    }

    private void setStartDate(int y, int m, int d) {
        sYear = y;
        sMonth = m;
        sDay = d;
        agendaController.setYear(y);
        agendaController.setMonth(m);
        agendaController.setDayOfMonth(d);
    }

    private void setEndDate(int y, int m, int d) {
        eYear = y;
        eMonth = m;
        eDay = d;
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

class AgendaController extends Controller{
}
