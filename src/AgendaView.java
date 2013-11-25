
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Buser
 */
public class AgendaView {//extends JPanel {
//    Calendar cal;
//    EventsList events;
//    public final static String[] months = {
//        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
//    };
//    public final static String[] daysOfWeek = {
//        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
//    };
//
//    public AgendaView (EventsList x)
//    {
//
//        events = x;
//        JPanel total = new JPanel(new GridLayout(events.eventMap.size(),1));
//        ArrayList<Event> eventList;
//        EventDate date;
//
//        if (!x.eventMap.isEmpty())
//        {
//            for (Map.Entry<EventDate, ArrayList<Event>> entry : x.eventMap.entrySet())
//            {
//                eventList = entry.getValue();
//                date = entry.getKey();
//
//                JPanel day = new JPanel(new FlowLayout());
//                Date dayOfWeek = new Date(date.getYear()-1900,date.getMonth()-1,date.getDay());
//                /*
//                 * Parameters:
//                 * year - the year minus 1900.
//                 * month - the month between 0-11.
//                 * date - the day of the month between 1-31.
//                 */
//               String label = daysOfWeek[dayOfWeek.getDay()]+" " + months[date.getMonth()-1]+" " +date.getDay();
//
//
//                JButton dayLabel = new JButton(label);
//                day.add(dayLabel);
//                Collections.sort(eventList);
//                for (Event e : eventList) {
//                    JPanel eventView = new JPanel();
//
//                    String timeLabel = e.getStart()+"-"+e.getEnd();
//                    JLabel time = new JLabel(timeLabel);
//                    eventView.add(time);
//                    JLabel descript = new JLabel(e.getTitle());
//                    eventView.add(descript);
//                    day.add(eventView);
//                }
//                total.add(day);
//            }
//            JScrollPane scroll = new JScrollPane(total);
//            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//            this.add(scroll);
//
//        }
//
//    }
//
}
