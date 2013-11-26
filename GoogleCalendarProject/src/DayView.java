import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;


public class DayView  extends JPanel implements ChangeListener{

    JLabel dateTitle;
    ArrayList<JLabel> hoursLabel;
    ArrayList<JLabel> hoursTitle;
    DayController dayController;
    JPanel hoursPanel;
    JScrollPane scrollPane;

    public DayView(JButton dayButton) {

        dateTitle = new JLabel();
        hoursLabel = new ArrayList<JLabel>();

        hoursTitle = new ArrayList<JLabel>(24);
        int count = 0, curHour = 11;
        String period = "am";
        for (int i = 0; i < 24; i++) {
            curHour = (curHour % 12) + 1;
            JLabel jl = new JLabel(curHour + period);
            if (++count == 12) {
                count = 0;
                period = "pm";
            }

            jl.setBorder(BorderFactory.createLineBorder(Color.lightGray));
//            jl.setBackground(Color.white);
//            jl.setOpaque(true);
            hoursTitle.add(jl);
        }

        hoursPanel = new JPanel();
        hoursPanel.setLayout(new BoxLayout(hoursPanel, BoxLayout.Y_AXIS));
//        hoursPanel.setBackground(Color.white);
//        hoursPanel.setOpaque(true);

//        scrollPane = new JScrollPane();
//        scrollPane.setSize(10, 10);
//        scrollPane.add(hoursPanel);
        this.setLayout(new BorderLayout());
    }


    public void displayDayView() {
//        this.scrollPane.removeAll();
        hoursPanel.removeAll();
        System.out.println(hoursTitle.size());
        for (JLabel jl : hoursTitle) {
            System.out.print(jl.getText() + " ");
            hoursPanel.add(jl);
        }

        this.add(dateTitle, BorderLayout.NORTH);
//        this.scrollPane.add(hoursPanel);
        this.add(hoursPanel, BorderLayout.CENTER);
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