
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MonthView extends JFrame implements ChangeListener {

    private ArrayList<JLabel> weeksTitle;
    private MonthController controller;
    private JPanel monthCal;
    private JLabel[][] daysLabels;
    private JLabel monthTitle;
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public MonthView() {
        this.setVisible(true);
        controller = new MonthController();
        weeksTitle = new ArrayList<JLabel>();
        monthCal = new JPanel();
        daysLabels = new JLabel[31][5];
        monthTitle = new JLabel();

        monthCal.setLayout(new GridLayout(0, 7));

        weeksTitle.add(new JLabel("Sun", JLabel.CENTER));
        weeksTitle.add(new JLabel("Mon", JLabel.CENTER));
        weeksTitle.add(new JLabel("Tue", JLabel.CENTER));
        weeksTitle.add(new JLabel("Wed", JLabel.CENTER));
        weeksTitle.add(new JLabel("Thu", JLabel.CENTER));
        weeksTitle.add(new JLabel("Fri", JLabel.CENTER));
        weeksTitle.add(new JLabel("Sat", JLabel.CENTER));

        setLayout(new BorderLayout());
        add(monthTitle, BorderLayout.NORTH);
        add(monthCal, BorderLayout.CENTER);
        showMonthCal();
    }

    private void showMonthCal() {

        ArrayList<String> days = controller.showCalendar();
        GregorianCalendar todaysDate = new GregorianCalendar();

        daysLabels = new JLabel[31][5];
        monthCal.removeAll();

        for (String s : days) {
            JPanel tempPanel = new JPanel();
            
               
        
        }
        for (JLabel jl : weeksTitle) {
            monthCal.add(jl);
        }

    
        monthTitle.setText(months[controller.getCurMonth() -1] + " " + controller.getCurYear());
        monthCal.validate();
        monthCal.repaint();
    }

    public void stateChanged(ChangeEvent e) {
    }
}

class MonthController extends Controller {

    public ArrayList<String> showCalendar() {

        ArrayList<String> arr = new ArrayList();
        int current = getCurDay();
        GregorianCalendar calendar = getCalendar();
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

        /**
         * add blanks to line up day of week
         */
        int blanks = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
       
        /**
         * append days from current month
         */
        calendar.add(GregorianCalendar.MONTH, 1);
        int numDays = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        for (int i = 1; i <= numDays; i++) {
            arr.add(String.valueOf(i));
        }

        return arr;
    }
}