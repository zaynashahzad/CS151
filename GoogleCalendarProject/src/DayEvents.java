/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */
import java.util.Date;

public class DayEvents {
    private String eventName;
    private int startHour; 
    private int endHour;
    private Date startDate;
    
    public DayEvents(String name, int start, int end, Date date){
        eventName = name; 
        startHour = start; 
        endHour = end; 
        startDate = date;
    }
    
    public String getName(){
        return eventName;
    }

    public int getStartHour(){
        return startHour;
    }
    
    public int getEndHour(){
        return endHour;
    }

    public Date getDate(){
        return startDate;
    }
    public String toString(){
        return eventName + " " + startDate + " " + startHour + " " + endHour;
    }
}
