import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;


public class DayView  extends JFrame implements ChangeListener{

    JLabel dateTitle;
    ArrayList hoursLabel; // showing events
//    JList<JLabel> hoursTitle; // showing each hour
    DayController dayController;
    JPanel hoursPanel; //contains hourLabel
    JPanel hoursTitlePanel;
    JScrollPane scrollPane;
    String[] title = {
            "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am",
            "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"
    };
    JTable table;

    public DayView(JButton dayButton) {

        dateTitle = new JLabel();
        hoursLabel = new ArrayList<JLabel>();

////        hoursTitlePanel = new JPanel();
//        hoursTitle = new JList(title);
//        hoursTitle.setVisibleRowCount(12);
//        for (int i = 0; i < 24; i++) {
//            JLabel tempLable = new JLabel(title[i]);
////            hoursTitle.add(tempLable);
//        }

        Object[][] obj = new Object[24][2];
        for (int i = 0; i < 24; i++) {
            obj[i][0] = title[i];
            obj[i][1] = new String("none");
        }
        Object[] temp = {"", ""};
        table = new JTable(obj, temp);
        table.setTableHeader(null);
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        for (int i = 0; i < 24; i++) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
//        }

        hoursPanel = new JPanel();
        hoursPanel.setLayout(new FlowLayout());



        scrollPane = new JScrollPane(table,
                ScrollPaneLayout.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneLayout.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVisibleR
//        scrollPane.setViewportView(hoursPanel);
        this.setLayout(new BorderLayout());
    }


    public void displayDayView() {
        hoursPanel.removeAll();
//        scrollPane.removeAll();

//        for (JLabel jl : hoursTitle) {
//            System.out.print(jl.getText() + " ");
//            hoursPanel.add(jl);
////            scrollPane.add(jl);
//
//        }

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