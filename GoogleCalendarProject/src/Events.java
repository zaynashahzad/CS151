
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

public class Events {   //model

    private TreeMap<Date, ArrayList<DayEvents>> eventsList;
    private ArrayList<ChangeListener> listeners;

    public Events() {
        eventsList = new TreeMap();
        listeners = new ArrayList();
    }

   
    public TreeMap<Date, ArrayList<DayEvents>> getTree() {
        return (TreeMap) eventsList.clone();
    }

    public void registerListener(ChangeListener cl) {
        listeners.add(cl);
    }

    public boolean addEvent(Date date, DayEvents dayEvents) {

        if (dayEvents.getStartHour() > dayEvents.getEndHour()) { return false; } //invalid format

        ArrayList <DayEvents> eventList;

        int[] dayEventsHours = new int[24];
        for (int i = dayEvents.getStartHour(); i <= dayEvents.getEndHour(); i++)
            dayEventsHours[i] = 1;

        // if events for this date already exist, add to that arraylist.
        if (eventsList.containsKey(date)) {
            eventList = eventsList.get(date);
            for (DayEvents e : eventList) {
                for (int i = e.getStartHour(); i <= e.getEndHour(); i++)
                    if (dayEventsHours[i] == 1)
                        return false;
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

    public void updateAllListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

    public boolean hasEntry(Date date) {
        if (eventsList.containsKey(date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     *
     * @param date
     * @return Sorted event list on given date
     */
    public ArrayList<DayEvents> getEventsForDate(Date date) {
        ArrayList<DayEvents> dayEvents = null ;
        
        
        for (Date d: eventsList.keySet()){
            if (d.equals(date)){
               dayEvents = eventsList.get(d);
            }
        }
        
   //     dayEvents = eventsList.get(date);
        if (dayEvents!= null) {
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
