
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Displays all the events scheduled for a certain day
 */
public class DayView extends JPanel implements CalendarView {

    JLabel dateTitle;
    JScrollPane scrollPane;
    JTable leftTable, rightTable;
    JPanel panel;
    DayController dayController;
    Events events;
    ColorInterface color;
    public static final String[] title = {
        "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
        "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };

    /**
     * Sets up the GUI needed to display the events for a given day
     *
     * @param events contains all the events currently in the calendar
     */
    public DayView(Events events) {
        dateTitle = new JLabel();
        color = new DayColorConcrete();
        panel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(panel);
        dayController = new DayController();
        this.events = events;
        this.setLayout(new BorderLayout());
        showToday();

    }

    /**
     * Makes a left side of table that houses all the hours of the day
     */
    private void setLeftTable() {
        Object[][] obj = new Object[24][1];
        for (int i = 0; i < 24; i++) {
            obj[i][0] = title[i];
        }
        Object[] temp = {""};
        leftTable = new JTable(obj, temp);
        leftTable.setTableHeader(null);
        leftTable.setRowHeight(40);
        leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        leftTable.setGridColor(Color.lightGray);
        leftTable.setEnabled(false);
    }

    /**
     * Makes a right-hand side of table that houses all the events on the day,
     * and displays them in a graphical way as blocks of time
     *
     * @param list the list of events for the given day to display in panel
     */
    private void setRightTable(ArrayList<DayEvents> list) {
        Object[][] obj = new Object[24][1];
        Object[] temp = {""};

        if (list != null) {
            final int[] hrs = new int[24];
            for (DayEvents de : list) {
                int startHr = de.getStartHour();
                int endHr = de.getEndHour();

                obj[startHr][0] = de.getName();

                while (startHr <= endHr) {
                    hrs[startHr++] = 1;
                }
            }

            rightTable = new JTable(obj, temp) {
                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                    Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                    // if event at the given hour, color it in. Otherwise leave it white
                    if (hrs[Index_row] == 1) {
                        comp.setBackground(color.getColor());
                    } else {
                        comp.setBackground(Color.white);
                    }
                    return comp;
                }
            };
        } else {
            rightTable = new JTable(obj, temp);
        }

        rightTable.setTableHeader(null);
        rightTable.setRowHeight(40);
        rightTable.setGridColor(Color.lightGray);
        rightTable.setEnabled(false);
    }

    /**
     * Shows the events on a day in tabular format with left and right columns; sets the current date at the top
     * @param list the list of events for the given day to display in panel
     */
    private void showDayView(ArrayList<DayEvents> list) {
        this.invalidate();
        panel.removeAll();

        setLeftTable();
        setRightTable(list);
        setDateTitle(dayController.getDayOfWeek() + " " + (dayController.getCurMonth() + 1) + "/" + dayController.getCurDay());

        panel.add(leftTable, BorderLayout.WEST);
        panel.add(rightTable, BorderLayout.CENTER);

        this.add(dateTitle, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    /**
     * Sets the date to be displayed at the top of screen
     * @param date The date to display at the top of day view
     */
    private void setDateTitle(String date) {
        this.dateTitle.setText(date);
    }

    @Override
    /**
     * Shows the next day, and it's events
     */
    public void showNext() {
        dayController.nextDay();
        showDayView(events.getEventsForDate(dayController.getDate()));
    }

    @Override
    /**
     * Shows the previous day, and its events
     */
    public void showPrev() {
        dayController.prevDay();
        showDayView(events.getEventsForDate(dayController.getDate()));
    }

    @Override
    /**
     * Shows today's date and its events
     */
    public void showToday() {
        dayController.todayDate();
        showDayView(events.getEventsForDate(dayController.getDate()));
    }

    /**
     * Shows the day view for a specified date
     * @param year the year to display
     * @param month the month to display
     * @param day the day to display
     */
    @Override
    public void showView(int year, int month, int day) {
        dayController.setMonth(month);
        dayController.setDayOfMonth(day);
        dayController.setYear(year);
        showDayView(events.getEventsForDate(dayController.getDate()));
    }
}

/**
 * Implements extra functionality in Controller that is needed by the day view
 */
class DayController extends Controller {

    public DayController() {
    }
}