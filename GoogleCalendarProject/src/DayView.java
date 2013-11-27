import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


public class DayView  extends JFrame implements ChangeListener{

    JLabel dateTitle;
    JScrollPane scrollPane;
    JTable leftTable, rightTable;
    JPanel panel;
    public static final String[] title = {
            "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
            "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };

    public DayView(JButton dayButton) {

        dateTitle = new JLabel();
        panel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(panel);

        this.setLayout(new BorderLayout());
        displayDayView(null);
    }

    private void setLeftTable() {
        Object[][] obj = new Object[24][1];
        for (int i = 0; i < 24; i++)
            obj[i][0] = title[i];
        Object[] temp = {""};
        leftTable = new JTable(obj, temp);
        leftTable.setTableHeader(null);
        leftTable.setRowHeight(50);
        leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        leftTable.setGridColor(Color.lightGray);
        leftTable.setEnabled(false);

    }

    private void setRightTable(ArrayList<DayEvents> list) {
        Object[][] obj = new Object[24][1];
        Object[] temp = {""};
        rightTable = new JTable(obj, temp);
        rightTable.setTableHeader(null);
        rightTable.setRowHeight(50);
        rightTable.setGridColor(Color.lightGray);

        if (list != null) {
            for (DayEvents dv : list) {
                int startHr = dv.getStartHour();
                int endHr = dv.getEndHour();


            }
        }

//        rightTable.setEnabled(false);
    }


    public void displayDayView(ArrayList<DayEvents> list) {
        panel.removeAll();

        setLeftTable();
        setRightTable(list);

        panel.add(leftTable, BorderLayout.WEST);
        panel.add(rightTable, BorderLayout.CENTER);

        this.add(dateTitle, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    public void setDateTitle(String date) {
        this.dateTitle.setText(date);
    }



    public void stateChanged(ChangeEvent e) {

    }

}

class DayController extends Controller{ //with listeners

    DayView dayView;
    private Events events;

    DayController(DayView dayView, Events events) {

//        this.calendar = controller.getCalendar();
        this.events = events;

        this.dayView = dayView;
        dayView.setDateTitle(getDayOfWeek() + " " + (getCurMonth() + 1) + "/" + getCurDay());
//        prevMonth();
        prevDay();prevDay();prevDay();
        System.out.println((getCurMonth() + 1) + "/" + getCurDay() + " " + getCurYear() + getDayOfWeek());
        Date date = new Date(2013, 10, 27);

        ArrayList<DayEvents> dayEvents = events.getEventsForDate(date);
        dayView.displayDayView(dayEvents);

//        dayView.displayDayView(null);
        for (DayEvents de : dayEvents)
            System.out.println(de.getName() + " " + de.getStartHour());
    }

    
}