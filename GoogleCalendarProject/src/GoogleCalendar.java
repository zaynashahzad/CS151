
import javax.swing.*;
import java.awt.*;

public class GoogleCalendar {

    public static void main(String[] args) {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        CalendarFrame cf = new CalendarFrame();
        cf.setSize(new Dimension(1280, 800));
        cf.setResizable(true);
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);
    }

    
}
