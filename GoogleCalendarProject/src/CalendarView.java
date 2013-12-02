/**
 * Authors: Peiyi Mao, Zayna Shahzad, Robert Buser
 * CS 151 - Object Oriented Design
 * Google Calendar Project
 * Due: December 2, 2013
 */
public interface CalendarView {
    public void showNext();
    public void showPrev();
    public void showToday();
    public void showView(int year, int month, int day);
}
