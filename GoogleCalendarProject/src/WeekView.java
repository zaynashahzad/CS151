
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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeekView extends JPanel implements CalendarView {

    JPanel panel, headerPanel;
    JTable leftTable, rightTable;
    JScrollPane scrollPane;
    Events events;
    WeekController weekController;
    public static final String[] title = {
        "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
        "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };

    public WeekView(Events events) {

        panel = new JPanel(new BorderLayout());
        headerPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(panel);
        weekController = new WeekController();
        this.events = events;
        this.setLayout(new BorderLayout());
        showWeekView();
    }

    private void setLeftTable() {
        Object[][] obj = new Object[24][1];
        for (int i = 0; i < 24; i++) {
            obj[i][0] = title[i];
        }
        Object[] temp = {""};
        leftTable = new JTable(obj, temp);
        leftTable.setRowHeight(40);
        leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        leftTable.setGridColor(Color.lightGray);
        leftTable.setEnabled(false);
    }

    private void setRightTable() {
        Object[][] obj = new Object[24][7];
        String[] header = new String[7];
        boolean hasEvents = false;

        Calendar cal = weekController.getCalendar();
        for (int i = cal.get(GregorianCalendar.DAY_OF_WEEK); i > 1; i--) {
            weekController.prevDay();
        }

        final int[][] hrs = new int[24][7];
        for (int i = 0; i < 7; i++) {
            ArrayList<DayEvents> list = events.getEventsForDate(weekController.getDate());
            if (list != null) {
                hasEvents = true;
                for (DayEvents de : list) {
                    int startHr = de.getStartHour();
                    int endHr = de.getEndHour();
                    obj[startHr][i] = de.getName();
                    while (startHr <= endHr) {
                        hrs[startHr++][i] = 1;
                    }
                }
            }
            header[i] = new String(weekController.getDayOfWeek().substring(0, 3) + " " + weekController.getCurDay() + "/" + (weekController.getCurMonth() + 1));
            weekController.nextDay();
        }
        weekController.prevDay();

        if (hasEvents) {
            rightTable = new JTable(obj, header) {
                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                    Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                    if (hrs[Index_row][Index_col] == 1) {
                        comp.setBackground(new Color(135, 206, 250));
                    } else {
                        comp.setBackground(Color.white);
                    }
                    return comp;
                }
            };
        } else {
            rightTable = new JTable(obj, header);
        }
        rightTable.getTableHeader().setFont(new Font(rightTable.getFont().getFontName(), rightTable.getFont().getStyle(), rightTable.getFont().getSize() + 3));
        rightTable.setRowHeight(40);
        rightTable.setGridColor(Color.lightGray);
        rightTable.setEnabled(false);
    }

    private void showWeekView() {
        panel.removeAll();
        headerPanel.removeAll();

        setLeftTable();
        setRightTable();

        headerPanel.add(leftTable.getTableHeader(), BorderLayout.WEST);
        headerPanel.add(rightTable.getTableHeader(), BorderLayout.CENTER);
        panel.add(leftTable, BorderLayout.WEST);
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(rightTable, BorderLayout.CENTER);

        this.add(scrollPane, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    @Override
    public void showNext() {
        weekController.nextWeek();
        showWeekView();
    }

    @Override
    public void showPrev() {
        weekController.prevWeek();
        showWeekView();
    }

    @Override
    public void showToday() {
        weekController.todayDate();
        showWeekView();
    }

    @Override
    public void showView(int year, int month, int day) {
        weekController.setMonth(month);
        weekController.setCurDay(day);
        weekController.setYear(year);
        showWeekView();
    }
}

class WeekController extends Controller{

    public WeekController() {}

}