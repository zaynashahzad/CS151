
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.util.Date;

/**
 * DayEvents holds the data for one event in calendar
 */
public class DayEvents {
    private String eventName;
    private int startHour; 
    private int endHour;
    private Date startDate;
    
    /**
     * Makes a new event with the given parameters
     * @param name the event name
     * @param start the start hour of event
     * @param end the end hour of event
     * @param date the event date
     */
    public DayEvents(String name, int start, int end, Date date){
        eventName = name; 
        startHour = start; 
        endHour = end; 
        startDate = date;
    }
    
    /**
     * Gets the event name
     * @return the event name
     */
    public String getName(){
        return eventName;
    }

    /**
     * Gets the events start hour (0-23)
     * @return the events start hour
     */
    public int getStartHour(){
        return startHour;
    }
    
    /**
     * Gets the events end hour (0-23)
     * @return the events end hour
     */
    public int getEndHour(){
        return endHour;
    }

    /**
     * Gets the date that event is on the calendar for
     * @return The scheduled date of event
     */
    public Date getDate(){
        return startDate;
    }
    
    /**
     * Gets a string representation of the event object
     * @return a string of event details
     */
    public String toString(){
        return eventName + " " + startDate + " " + startHour + " " + endHour;
    }
}
