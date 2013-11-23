
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class GoogleCalendar {

    public static void main(String[] args) {
        Events events = new Events();

        CalendarFrame cf = new CalendarFrame();

        cf.setExtendedState(Frame.MAXIMIZED_BOTH);
        cf.setLayout(new GridLayout(1, 1));
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);


    }
}
