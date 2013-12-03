
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */

/**
 * CalendarView interface will guarantee the basic operations needed by each view
 */
public interface CalendarView {
    public void showNext();
    public void showPrev();
    public void showToday();
    public void showView(int year, int month, int day);
}
