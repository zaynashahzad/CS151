import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeekView extends JPanel implements ChangeListener, CalendarView {

    JPanel panel, headerPanel;
    JTable leftTable, rightTable;
    JScrollPane scrollPane;
    Events events;
    WeekController weekController;
    public static final String[] title = {
            "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
            "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };
    public static final String[] dayOfWeek = {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

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

        //set current date to sunday
        boolean hasEvents = false;
        Calendar cal = weekController.getCalendar();
        Date curDate = weekController.getDate();
       cal.set(GregorianCalendar.DAY_OF_WEEK, 1);

        final int[][] hrs = new int[24][7];
        for (int i = 0; i < 7; i++) {
            ArrayList<DayEvents> list = events.getEventsForDate(weekController.getDate());
            if (list != null)
                hasEvents = true;
                for (DayEvents de : list) {
                    int startHr = de.getStartHour();
                    int endHr = de.getEndHour();
                    obj[startHr][i] = de.getName();
                    while(startHr <= endHr) {
                        hrs[startHr++][i] = 1;
                    }
                }
            header[i] = new String(weekController.getDayOfWeek().substring(0, 3) + " " + weekController.getCurDay() + "/" + (weekController.getCurMonth() + 1));
            weekController.nextDay();
        }

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

        weekController.setDate(curDate);

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

    public void stateChanged(ChangeEvent e) {
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
}

class WeekController extends Controller {

    Calendar calendar;
    private Calendar currentFirstDayOfWeek;

    public WeekController() {
        this.calendar = super.getCalendar();
    }

    public void setDate(Date date) {
        calendar.set(GregorianCalendar.YEAR, date.getYear() - 1900);
        calendar.set(GregorianCalendar.MONTH, date.getMonth());
        calendar.set(GregorianCalendar.DAY_OF_MONTH, date.getDate());
    }
}