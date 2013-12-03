
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The main controller class; handles operations needed by views that are common
 * to every view. Views that require additional features will extend this class
 */
public class Controller {

    private CalendarView curView;
    private GregorianCalendar calendar;
    private Events events;
    public final static String[] dayOfWeek = {
        "", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    /**
     * Makes new Controller object
     *
     * @param events contains underlying treemap that holds all scheduled
     * calendar events
     */
    Controller(Events events) {
        this.events = events;
        calendar = new GregorianCalendar();
    }

    /**
     * Makes new controller object with todays date and an empty list of
     * scheduled events
     */
    Controller() {
        // default day view
        calendar = new GregorianCalendar();
        this.events = new Events();
    }

    /**
     * Gets the calendar in it's current state; Can obtain the current day,
     * month, year, etc from calendar
     *
     * @return the state of the calendar
     */
    public GregorianCalendar getCalendar() {
        return calendar;
    }

    /**
     * Makes the active view to the view specified
     *
     * @param cv the view to change the right hand side of screen to
     */
    public void setCurView(CalendarView cv) {
        this.curView = cv;
    }

    /**
     * Gets the current view being displayed on screen
     *
     * @return the current view being displayed
     */
    public CalendarView getCurView() {
        return this.curView;
    }

    /**
     * Gets the current month of calendar
     *
     * @return the current month of the calendar
     */
    public int getCurMonth() {
        return calendar.get(GregorianCalendar.MONTH);
    }

    /**
     * Gets the current year of calendar
     *
     * @return the current year of the calendar
     */
    public int getCurYear() {
        return calendar.get(GregorianCalendar.YEAR);
    }

    /**
     * Gets the current day of calendar
     *
     * @return the current day of the calendar
     */
    public int getCurDay() {
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    /**
     * Increments the current calendar by one month
     */
    public void nextMonth() {
        calendar.add(GregorianCalendar.MONTH, 1);
    }

    /**
     * Decrements the current calendar by one month
     */
    public void prevMonth() {
        calendar.add(GregorianCalendar.MONTH, -1);
    }

    /**
     * Increments the current calendar by one day
     */
    public void nextDay() {
        calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
    }

    /**
     * Decrements the current calendar by one day
     */
    public void prevDay() {
        calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
    }

    /**
     * Sets the calendar date to today's date
     */
    public void todayDate() {
        calendar.set(GregorianCalendar.YEAR, Calendar.getInstance().get(GregorianCalendar.YEAR));
        calendar.set(GregorianCalendar.MONTH, Calendar.getInstance().get(GregorianCalendar.MONTH));
        calendar.set(GregorianCalendar.DAY_OF_MONTH, Calendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH));
    }

//    public void setDayOfMonth(int day_of_month) {
//        calendar.set(GregorianCalendar.DAY_OF_MONTH, day_of_month);
//    }
    /**
     * Gets the current day of the week of calendar
     * @return A string of the current day of the week ie. Sunday, Monday, etc
     */
    public String getDayOfWeek() {
        return dayOfWeek[calendar.get(GregorianCalendar.DAY_OF_WEEK)];
    }

    /**
     * Gets the current day(1-31), month(0-11) and year (0 - 8099)
     * @return the current day, month, and year in a Date Object
     */
    public Date getDate() {
        return new Date(getCurYear() - 1900, (getCurMonth()), getCurDay());
    }

    /**
     * Moves calendar back one week from current date
     */
    public void prevWeek() {
        for (int i = 0; i < 7; i++) {
            prevDay();
        }
    }

    /**
     * Moves calendar forward by one week from current date
     */
    public void nextWeek() {
        for (int i = 0; i < 7; i++) {
            nextDay();
        }
    }

    /**
     * Opens a given file, parses it and added recurring events for the given dates and times
     * @param fileName the filename to retrieve recurring events from
     * @throws Exception if file cannot be found, throw exception. Handled by caller
     */
    public void createRecurringEvents(String fileName) throws Exception {
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(fileName));
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            int begin = 0;
            int end = strLine.indexOf(';', begin);
            String eventName = strLine.substring(begin, end);
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            int eventYear = Integer.parseInt(strLine.substring(begin, end));
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            int startMonth = Integer.parseInt(strLine.substring(begin, end)) - 1;
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            int endMonth = Integer.parseInt(strLine.substring(begin, end)) - 1;
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            String repeatDays = strLine.substring(begin, end);
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            int beginHour = Integer.parseInt(strLine.substring(begin, end));
            begin = end + 1;
            end = strLine.indexOf(';', begin);
            int endHour = Integer.parseInt(strLine.substring(begin, end));

            GregorianCalendar calStart = new GregorianCalendar();
            calStart.set(eventYear, startMonth, 1);

            ArrayList<Integer> daysRepeated = new ArrayList();
            for (int i = 0; i < repeatDays.length(); i++) {
                char c = repeatDays.charAt(i);
                switch (c) {
                    case 'S':
                        daysRepeated.add(1);
                        break;
                    case 'M':
                        daysRepeated.add(2);
                        break;
                    case 'T':
                        daysRepeated.add(3);
                        break;
                    case 'W':
                        daysRepeated.add(4);
                        break;
                    case 'H':
                        daysRepeated.add(5);
                        break;
                    case 'F':
                        daysRepeated.add(6);
                        break;
                    case 'A':
                        daysRepeated.add(7);
                        break;
                }
            }

            // go through every day in the range given and determine if it falls on the appropriate day of week. 
            // if so, add the event on that date. if not, continue
            while (calStart.get(GregorianCalendar.MONTH) <= endMonth) {
                if (daysRepeated.contains(calStart.get(GregorianCalendar.DAY_OF_WEEK))) {
                    Date d = new Date(calStart.get(GregorianCalendar.YEAR) - 1900, calStart.get(GregorianCalendar.MONTH), calStart.get(GregorianCalendar.DAY_OF_MONTH));
                    DayEvents newEvent = new DayEvents(eventName, beginHour, endHour, d);
                    events.addEvent(d, newEvent);
                }
                calStart.add(GregorianCalendar.DAY_OF_MONTH, 1);
            }
        }

    }

    /**
     * Changes the current calendar day to the one specified
     * @param day the day to move calendar to 
     */
    public void setDayOfMonth(int day) {
        calendar.set(GregorianCalendar.DAY_OF_MONTH, day);
    }

    /**
     * Changes the current calendar month to the one specified
     * @param month the month to move calendar to 
     */
    public void setMonth(int month) {
        calendar.set(GregorianCalendar.MONTH, month);
    }

    /**
     * Changes the current calendar year to the one specified
     * @param year the year to move calendar to
     */
    public void setYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
    }
}
