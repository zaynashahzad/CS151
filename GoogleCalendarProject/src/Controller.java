import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {

//    private Date currentDate;
    private char currentView;
    private GregorianCalendar calendar;
    private Events events;

    public final static String[] dayOfWeek = {
            "", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    Controller(Events events) {
        this.events = events;
        calendar = new GregorianCalendar();
        currentView = 'd';

//        currentDate = new Date(getCurYear()-1900, (getCurMonth()), getCurDay());
    }

    Controller() {
        // default day view
        calendar = new GregorianCalendar();
        currentView = 'd';
        this.events = new Events();
//        currentDate = new Date(getCurYear()-1900, (getCurMonth()), getCurDay());
    }

    public GregorianCalendar getCalendar(){
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

    public void todayDate() {
        calendar.set(GregorianCalendar.YEAR, Calendar.getInstance().get(GregorianCalendar.YEAR));
        calendar.set(GregorianCalendar.MONTH, Calendar.getInstance().get(GregorianCalendar.MONTH));
        calendar.set(GregorianCalendar.DAY_OF_MONTH, Calendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH));
    }

    public void setCurDay(int day_of_month) {
        calendar.set(GregorianCalendar.DAY_OF_MONTH, day_of_month);
    }

    public String getDayOfWeek() {
//        System.out.println(calendar.get(GregorianCalendar.DAY_OF_WEEK));
        return dayOfWeek[calendar.get(GregorianCalendar.DAY_OF_WEEK)];
    }

    public Events getEvents() {
        return events;
    }

    public Date getDate() {
        return new Date(getCurYear()-1900, (getCurMonth()), getCurDay());
    }

    public void prevWeek() {
        System.out.println("pre" + calendar.get(GregorianCalendar.DAY_OF_MONTH));
//        calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
        for (int i = 0; i < 7; i++)
            prevDay();
        System.out.println("post" + calendar.get(GregorianCalendar.DAY_OF_MONTH));
    }

    public void nextWeek() {
//        calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 7; i++)
            nextDay();
    }

}
