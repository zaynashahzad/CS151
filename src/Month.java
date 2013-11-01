
/**
 * Zayna Shahzad
 * A single month in the calendar
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Month {

    private GregorianCalendar calendar;
    public final static int[] days = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Month() {
        calendar = new GregorianCalendar();
    }

    /**
     * Gets the current month of calendar
     * @return the current month of the calendar
     */
    public int getCurMonth() {
        return calendar.get(GregorianCalendar.MONTH);
    }

    /**
     * Gets the current year of calendar
     * @return the current year of the calendar
     */
    public int getCurYear() {
        return calendar.get(GregorianCalendar.YEAR);
    }

    /**
     * Determines the number of days in the month, and returns all the days in an arraylist
     * @return An arraylist of strings with all the days in the list to be
     * printed out in calendar format
     */
    public ArrayList<String> show() {
        ArrayList<String> arr = new ArrayList();
        int month = calendar.get(GregorianCalendar.MONTH);
        int blanks = 0;

        int current = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
        blanks = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;

        for (int j = 0; j < blanks; j++) {
            arr.add(" ");
        }

        int numDays = days[month];
        if (calendar.get(GregorianCalendar.YEAR) % 4 == 0 && month == 1) {
            numDays = 29;
        }

        for (int i = 1; i <= numDays; i++) {
            arr.add(String.valueOf(i));

        }
        calendar.set(GregorianCalendar.DAY_OF_MONTH, current);
        return arr;
    }

    /**
     * Increments the current calendar by one month
     */
    public void next() {
        calendar.add(GregorianCalendar.MONTH, 1);
    }

    /**
     * Decrements the current calendar by one month
     */
    public void prev() {
        calendar.add(GregorianCalendar.MONTH, -1);
    }
}