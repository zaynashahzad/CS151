
/**
 * @author Zayna Shahzad October 19, 213 CS 151 - Assignment #3 A Calendar Java
 * Application
 */
import java.io.IOException;
import javax.swing.JFrame;

/**
 * The main method. Calendar begins from here
 */
public class Calendar {

    public static void main(String[] args) throws IOException {

        // make a new controller. It does all the front end work in this 
        // application
        JFrame frame = new Controller();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        frame.setVisible(true);
    }
}