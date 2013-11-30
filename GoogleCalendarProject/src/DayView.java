import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class DayView extends JPanel implements ChangeListener, CalendarView {

    JLabel dateTitle;
    JScrollPane scrollPane;
    JTable leftTable, rightTable;
    JPanel panel;
    DayController dayController;
    Events events;
    public static final String[] title = {
        "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
        "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };
//    public static final Color[] colors = {new Color(135, 206, 250), new Color(255, 138, 202)};

    public DayView(Events events) {

        dateTitle = new JLabel();
        panel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(panel);
        dayController = new DayController();
        this.events = events;
        this.setLayout(new BorderLayout());
        showDayView(null);



    }

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

    private void setRightTable(ArrayList<DayEvents> list) {
        Object[][] obj = new Object[24][1];
        Object[] temp = {""};

        if (list != null) {
            final int[] hrs = new int[24];
            for (int i = 0; i < 24; i++) {
                hrs[i] = 0;
            }
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
                    if (hrs[Index_row] == 1) {
                        comp.setBackground(new Color(135, 206, 250));
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

    private void showDayView(ArrayList<DayEvents> list) {
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

    private void setDateTitle(String date) {
        this.dateTitle.setText(date);
    }

    public void viewToday() {
        Date date = new Date(dayController.getCurYear()-1900, (dayController.getCurMonth()), dayController.getCurDay());
        showDayView(events.getEventsForDate(date));
    }

    @Override
    public void showNext() {
        dayController.nextDay();
        Date date = new Date(dayController.getCurYear()-1900, (dayController.getCurMonth()), dayController.getCurDay());
        showDayView(events.getEventsForDate(date));
    }

    @Override
    public void showPrev() {
        dayController.prevDay();
        Date date = new Date(dayController.getCurYear()-1900, (dayController.getCurMonth()), dayController.getCurDay());
        showDayView(events.getEventsForDate(date));
    }

    @Override
    public void showToday() {
        dayController.todayDate();
        Date date = new Date(dayController.getCurYear()-1900, (dayController.getCurMonth()), dayController.getCurDay());
        showDayView(events.getEventsForDate(date));
    }

    public void stateChanged(ChangeEvent e) {
    }

}

class DayController extends Controller { //with listeners

    public DayController() {}

}