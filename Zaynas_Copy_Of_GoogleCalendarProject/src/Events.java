
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Events {

    private TreeMap<Date, ArrayList<DayEvents>> eventsList;
    private ArrayList<ChangeListener> listeners;
    

    public Events() {
        eventsList = new TreeMap();
        listeners = new ArrayList();
    }

    // TODO: 
    // get cal month, day, year
    // set cal month, day, year, etc
    
    
    public TreeMap<Date, ArrayList<DayEvents>> getTree() {
        return (TreeMap) eventsList.clone();
    }

    public void registerListener(ChangeListener cl) {
        listeners.add(cl);
    }

    public boolean addEvent(Date date, DayEvents dayEvents) {

        ArrayList<DayEvents> eventList;

        // if events for this date already exist, add to that arraylist.
        if (eventsList.containsKey(date)) {
            eventList = eventsList.get(date);
            for (DayEvents e : eventList) {
                // check for conflicts here and return false
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
}
