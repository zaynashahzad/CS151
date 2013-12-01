import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {

    private CalendarView curView;
    private GregorianCalendar calendar;
    private Events events;

    public final static String[] dayOfWeek = {
            "", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    Controller(Events events) {
        this.events = events;
        calendar = new GregorianCalendar();
    }

    Controller() {
        // default day view
        calendar = new GregorianCalendar();
        this.events = new Events();
    }

    public GregorianCalendar getCalendar(){
        return calendar;
    }
    
    public void setCurView(CalendarView cv) {
        this.curView = cv;
    }

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
        for (int i = 0; i < 7; i++)
            prevDay();
    }

    public void nextWeek() {
        for (int i = 0; i < 7; i++)
            nextDay();
    }

    public void createRecurringEvents(String fileName){
        System.out.println(fileName + " heree");
    }
    
    public void setDayOfMonth(int day) {
        calendar.set(GregorianCalendar.DAY_OF_MONTH, day);
    }

    public void setMonth(int month) {
        calendar.set(GregorianCalendar.MONTH, month);
    }

    public void setYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
    }
    
    
}
