import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class DayView  extends JFrame implements ChangeListener{

    JLabel dateTitle;
//    DayController dayController;
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

        Object[][] obj = new Object[24][1];
        Object[][] obj1 = new Object[24][1];
        for (int i = 0; i < 24; i++) {
            obj[i][0] = title[i];
            obj1[i][0] = "none";
        }
        Object[] temp = {""};
        leftTable = new JTable(obj, temp);
        leftTable.setTableHeader(null);
        leftTable.setRowHeight(50);
        leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        leftTable.setGridColor(Color.lightGray);
        leftTable.setEnabled(false);


        rightTable = new JTable(obj1, temp);
        rightTable.setTableHeader(null);
        rightTable.setRowHeight(50);
        rightTable.setGridColor(Color.lightGray);

        this.setLayout(new BorderLayout());
        displayDayView();
    }


    public void displayDayView() {
//        hoursPanel.removeAll();
//        scrollPane.removeAll();

//        for (JLabel jl : hoursTitle) {
//            System.out.print(jl.getText() + " ");
//            hoursPanel.add(jl);
////            scrollPane.add(jl);
//
//        }
        panel.removeAll();

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

    DayController(DayView dayView) {
        this.dayView = dayView;
        dayView.setDateTitle(super.getDayOfWeek() + " " + (super.getCurMonth() + 1) + "/" + super.getCurDay());
        dayView.displayDayView();
    }


    
}