
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class CreateEvent extends JFrame implements ActionListener {

    CreateEvent(Events event) {

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        JLabel errorMsg = new JLabel("Enter all details below");

        JLabel eventNameLab = new JLabel("Event Name");
        JTextField eventNameTf = new JTextField();

        JLabel eventDateLab = new JLabel("Event Date");

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Oct", "Nov", "Dec"};
        JComboBox monthsPicker = new JComboBox(months);
        monthsPicker.setSelectedIndex(0);

        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = (i + 1) + "";
        }

        JComboBox daysPicker = new JComboBox(days);
        daysPicker.setSelectedIndex(0);

        String[] years = new String[120];
        for (int i = 0; i < years.length; i++) {
            years[i] = (i + 1900) + " ";
        }

        JComboBox yearsPicker = new JComboBox(years);
        yearsPicker.setSelectedIndex(years.length - 6);


        String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23"};


        JLabel startHourLab = new JLabel("Start Hour");
        JLabel endHourLab = new JLabel("End Hour");


        JComboBox startHourPicker = new JComboBox(hours);
        startHourPicker.setSelectedIndex(0);

        JComboBox endHourPicker = new JComboBox(hours);
        endHourPicker.setSelectedIndex(0);

        JButton submitButton = new JButton("Submit!");

        innerPanel.add(errorMsg);
        innerPanel.add(eventNameLab);
        innerPanel.add(eventNameTf);
        innerPanel.add(eventDateLab);
        innerPanel.add(monthsPicker);
        innerPanel.add(daysPicker);
        innerPanel.add(yearsPicker);
        innerPanel.add(startHourLab);
        innerPanel.add(startHourPicker);
        innerPanel.add(endHourLab);
        innerPanel.add(endHourPicker);
        innerPanel.add(submitButton);

        // create some padding
        innerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(innerPanel);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        // when the person hits submit, check for conflicts.
        // if successful, close window and add new events to model
        // if unsuccessful, keep open and get them to enter details
    }
}