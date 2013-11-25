
import java.util.GregorianCalendar;

public class Controller {

    private char currentView;
    private GregorianCalendar calendar;

    Controller() {
        // default day view
        calendar = new GregorianCalendar();
        currentView = 'd';
    }

    public GregorianCalendar getCal(){
        return calendar;
    }
    
    
    public char getCurrentView() {
        return currentView;
    }

    public void setCurrentView(char c) {
        currentView = c;
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
}