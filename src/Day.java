import java.util.GregorianCalendar;

/**
 * Day class for a single day of the month
 *
 * @author Zayna Shahzad
 */
class Day {

    GregorianCalendar calendar;
    EventsList eventManager;
//    public final static int[] days = {
//        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
//    };

    public Day(EventsList eventManager) {
        // event manager is t\he one holding all the events in a map
        calendar = new GregorianCalendar();
        this.eventManager = eventManager;
    }

    /**
     * Gets all the events that are scheduled for a single day
     * @return a string with all the events of the current day
     */
    public String show() {
        String res = "";
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH);
        int year = calendar.get(GregorianCalendar.YEAR);
//        int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;

        EventDate date = new EventDate(year, month, day);
        res = eventManager.getOne(date);
        return res;
    }

    /**
     * Changes the date of the calendar to the date requested
     * @param y the year to go to 
     * @param m the month to go to 
     * @param d the day to go to 
     */
    public void goTo(int y, int m, int d) {
        EventDate date = new EventDate(y, m, d);
        calendar.add(GregorianCalendar.YEAR, y - getCurYear());
        calendar.add(GregorianCalendar.MONTH, m - getCurMonth());
        calendar.add(GregorianCalendar.DAY_OF_MONTH, d - getCurDay());
    }

    /**
     * Increments the day by one
     */
    public void next() {
        calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
    }

    /**
     * Decrements the day by one
     */
    public void prev() {
        calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
    }

    /**
     * Gets the current month of the calendar
     * @return the current month
     */
    public int getCurMonth() {
        return calendar.get(GregorianCalendar.MONTH);
    }

    /**
     * Gets the current year of the calendar
     * @return the current year
     */
    public int getCurYear() {
        return calendar.get(GregorianCalendar.YEAR);
    }

    /**
     * Getst the current day of the calendar
     * @return the current day
     */
    public int getCurDay() {
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }
}