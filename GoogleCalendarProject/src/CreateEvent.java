
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class CreateEvent extends JFrame implements ActionListener {

    private Events events;
    private JPanel innerPanel;
    private JTextField eventNameTf;
    private JComboBox monthsPicker, daysPicker, yearsPicker;
    private JComboBox startHourPicker, endHourPicker;
    private JLabel errorMsg;

    CreateEvent(Events event) {
        events = event;
        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        JLabel instrucLabel = new JLabel("Enter all details below");

        JLabel eventNameLab = new JLabel("Event Name");
        eventNameTf = new JTextField();

        JLabel eventDateLab = new JLabel("Event Date");

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthsPicker = new JComboBox(months);
        monthsPicker.setSelectedIndex(0);

        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = (i + 1) + "";
        }

        daysPicker = new JComboBox(days);
        daysPicker.setSelectedIndex(0);

        String[] years = new String[120];
        for (int i = 0; i < years.length; i++) {
            years[i] = (i + 1900) + "";
        }

        yearsPicker = new JComboBox(years);
        yearsPicker.setSelectedIndex(years.length - 6);


        String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23"};


        JLabel startHourLab = new JLabel("Start Hour");
        JLabel endHourLab = new JLabel("End Hour");

        startHourPicker = new JComboBox(hours);
        startHourPicker.setSelectedIndex(0);

        endHourPicker = new JComboBox(hours);
        endHourPicker.setSelectedIndex(0);

        errorMsg = new JLabel();
        errorMsg.setForeground(Color.red);

        JButton submitButton = new JButton("Submit!");
        submitButton.addActionListener(this);

        innerPanel.add(instrucLabel);
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
        innerPanel.add(errorMsg);
        innerPanel.add(submitButton);

        // create some padding
        innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(innerPanel);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        errorMsg.setText("");
        String eventName = eventNameTf.getText();
        int eventYear = Integer.parseInt((String) yearsPicker.getSelectedItem()) - 1900;
        int eventMon = Integer.parseInt((String) monthsPicker.getSelectedItem()) - 1;
        int eventDay = Integer.parseInt((String) daysPicker.getSelectedItem());

        Date eventDate = new Date(eventYear, eventMon, eventDay);
        int eventStartHour = Integer.parseInt((String) startHourPicker.getSelectedItem());
        int eventEndHour = Integer.parseInt((String) endHourPicker.getSelectedItem());

        DayEvents newEvent = new DayEvents(eventName, eventStartHour, eventEndHour, eventDate);

        if (events.addEvent(eventDate, newEvent)) {
            this.setVisible(false);
            this.dispose();
        } else {
            errorMsg.setText("A conflict exists! Try again!");
        }
    }
}