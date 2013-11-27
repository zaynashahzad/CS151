
import javax.swing.*;
import java.awt.*;

public class GoogleCalendar {

    public static void main(String[] args) {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

//        Events events = new Events();

        CalendarFrame cf = new CalendarFrame();

        cf.setExtendedState(Frame.MAXIMIZED_BOTH);
//        cf.pack();
//        cf.setLayout(new GridLayout(1, 1));
//        cf.setLayout(new BorderLayout());
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);
//        cf.setResizable(false);
    }

    
}
