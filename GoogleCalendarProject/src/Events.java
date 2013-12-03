
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

/**
 * Contains all the events that are currently scheduled in the calendar
 */
public class Events {

    private TreeMap<Date, ArrayList<DayEvents>> eventsList;
    private ArrayList<ChangeListener> listeners;

    public Events() {
        eventsList = new TreeMap();
        listeners = new ArrayList();
    }

    /**
     * Gets the underlying data structure that has all events
     *
     * @return the treemap object containing all events
     */
    public TreeMap<Date, ArrayList<DayEvents>> getTree() {
        return (TreeMap) eventsList.clone();
    }

    /**
     * Adds a change listener to list so that it will be notified of any changes
     * in the treemap
     *
     * @param cl the change listener to add to list
     */
    public void registerListener(ChangeListener cl) {
        listeners.add(cl);
    }

    /**
     * Adds a new event to the calendar
     *
     * @param date the date on which the new event will be scheduled
     * @param dayEvents the new events details
     * @return true if event was successfully added, false if there was a
     * conflict with another event
     */
    public boolean addEvent(Date date, DayEvents dayEvents) {

        if (dayEvents.getStartHour() > dayEvents.getEndHour()) {
            return false;
        } //invalid format

        ArrayList<DayEvents> eventList;

        int[] dayEventsHours = new int[24];
        for (int i = 0; i < dayEventsHours.length; i++) {
            dayEventsHours[i] = -1;
        }
        for (int i = dayEvents.getStartHour(); i <= dayEvents.getEndHour(); i++) {
            dayEventsHours[i] = 1;
        }

        // if events for this date already exist, add to that arraylist.
        if (eventsList.containsKey(date)) {
            eventList = eventsList.get(date);
            for (DayEvents e : eventList) {
                for (int i = e.getStartHour(); i <= e.getEndHour(); i++) {
                    if (dayEventsHours[i] == 1) {
                        return false;
                    }
                }
            }
            eventList.add(dayEvents);
            updateAllListeners();
            return true;
        } else {
            eventList = new ArrayList();
            eventList.add(dayEvents);
            eventsList.put(date, eventList);
            updateAllListeners();
            return true;
        }
    }

    /**
     * Update all listeners with new information if state of treemap changes
     */
    public void updateAllListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

    /**
     * Determines whether a date has at least one scheduled event
     *
     * @param date the date to look for in treemap
     * @return true if the date has an associate event, false otherwise
     */
    public boolean hasEntry(Date date) {
        if (eventsList.containsKey(date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves all events in sorted order by start hour for a given date
     *
     * @param date the date to retrieve all events for
     * @return Sorted event list on given date
     */
    public ArrayList<DayEvents> getEventsForDate(Date date) {
        ArrayList<DayEvents> dayEvents = null;


        for (Date d : eventsList.keySet()) {
            if (d.equals(date)) {
                dayEvents = eventsList.get(d);
            }
        }

        if (dayEvents != null) {
            Collections.sort(dayEvents, new Comparator<DayEvents>() {
                @Override
                public int compare(DayEvents o1, DayEvents o2) {
                    return o1.getStartHour() - o2.getStartHour();
                }
            });
        }
        return dayEvents;
    }
}
