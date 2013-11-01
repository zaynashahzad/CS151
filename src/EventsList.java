
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 *
 * @author Zayna Shahzad
 */
public class EventsList {

    TreeMap<EventDate, ArrayList<Event>> eventMap = new TreeMap<>();
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    public final static int[] dayCounts = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    /**
     * Makes a new event and puts it into our eventList if no conflicts found
     *
     * @param name the name of a new event
     * @param m the month of event
     * @param d the day of event
     * @param y the year of event
     * @param start the start time of event
     * @param end the end time of event, it it has one
     * @return true if added, false if there was a conflict
     */
    public boolean create(String name, int m, int d, int y, int start, int end) {
        ArrayList<Event> eventList;

        EventDate date = new EventDate(y, m, d);
        Event event = new Event(start, end, name);

        if (eventMap.containsKey(date)) //if map already maps date to eventList
        {
            eventList = eventMap.get(date);
            for (Event e : eventList) {

                if (end == -1) {
                    if (start == e.getStart()) {
                        return false;
                    }
                } else { // event has an end time
                    if (start == e.getStart() || end == e.getEnd()) {
                        return false;
                    } else if (start < e.getEnd() && end > e.getStart()) {
                        return false;
                    }
                }
            }
            eventList.add(event);
            return true;
        } else {
            eventList = new ArrayList<Event>();
            eventList.add(event);
            eventMap.put(date, eventList);
            return true;
        }

    }

    /**
     * Removes all the currently scheduled events
     */
    public void deleteAll() {
        eventMap.clear();
    }

    /**
     * Removes all the events on a specific day
     *
     * @param y the year of event
     * @param m the month of event
     * @param d the day of event
     */
    public void deleteOne(int y, int m, int d) {

        ArrayList<Event> eventList;

        EventDate date = new EventDate(y, m, d);

        if (eventMap.containsKey(date)) {
            eventList = eventMap.get(date);
            eventList.clear();
            eventMap.remove(date);
        }
    }

    /**
     * Gets all the events that are scheduled on a single day
     *
     * @param date the date to get events of
     * @return A string with all the events on a specific date
     */
    public String getOne(EventDate date) {
        String res = "";
        ArrayList<Event> events = eventMap.get(date);

        if (events != null) {
            Collections.sort(events);
            for (Event e : events) {
                res += e.getStart();
                if (e.getEnd() != -1) {
                    res += " - " + e.getEnd();
                }
                res += " \t " + e.getTitle() + "\n";
            }
        } else {
            res = "No events yet!";
        }
        return res;
    }

    /**
     * Gets all the event that are scheduled.
     *
     * @return a string with all the events and their date/times
     */
    public String getAll() {
        String res = "All Scheduled Events\n----------------\n";
        ArrayList<Event> eventList;
        EventDate date;
        if (!eventMap.isEmpty()) {
            int year = eventMap.firstEntry().getKey().getYear();
            res += year + "\n";

            for (Map.Entry<EventDate, ArrayList<Event>> entry : eventMap.entrySet()) {
                eventList = entry.getValue();
                date = entry.getKey();

                if (date.getYear() != year) {
                    year = date.getYear();
                    res += "\n" + year + "\n";
                }

                Collections.sort(eventList);
                for (Event e : eventList) {
                    res += months[date.getMonth()] + " " + date.getDay() + " " + e.getStart();
                    if (e.getEnd() != -1) {
                        res += " - " + e.getEnd();
                    }
                    res += " \t " + e.getTitle() + "\n";
                }
            }
        }
        return res;
    }

    /**
     * If the program has come to an end, write all the events to a file
     *
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        FileWriter fwrite = new FileWriter("reservation.txt");
        BufferedWriter buff = new BufferedWriter(fwrite);

        ArrayList<Event> eventList;
        EventDate date;
        for (Map.Entry<EventDate, ArrayList<Event>> entry : eventMap.entrySet()) {
            eventList = entry.getValue();
            date = entry.getKey();
            String d = date.toString();
            buff.write(d + "\n");
            Collections.sort(eventList);
            for (Event event : eventList) {
                String output = "";
                output += event.getStart();

                if (event.getEnd() != -1) {
                    output += " - " + event.getEnd();
                }
                output += " : " + event.getTitle() + "\n";
                buff.write(output);
            }
        }
        buff.close();
    }

    /**
     * Determines if a date is already in the eventsList
     *
     * @param date the date to check in map
     * @return true if there is an event scheduled for that date, false if not
     * yet
     */
    public boolean hasEntry(EventDate date) {
        if (eventMap.containsKey(date)) {
            return true;
        } else {
            return false;
        }
    }

    // This can be called from controller contructor if you want the event list 
    //to already have events populated. 
    // makes repetitive runs a little faster instead of having to input
    //many different events manually.
    public void testerCases() {
        create("Dentist", 9, 24, 2011, 1400, 1900);
        create("Doctors", 9, 25, 2013, 1400, 1900);
        create("Future Event", 9, 24, 2014, 700, -1);
        create("CS 151 Midterm", 9, 26, 2010, 1400, 1900);
        create("Pick up Stuff", 9, 26, 2013, 2000, 2100);
        create("Some Event", 9, 26, 2013, 1100, 1200);
        create("Event with no end time", 3, 24, 2014, 1100, -1);

    }
}