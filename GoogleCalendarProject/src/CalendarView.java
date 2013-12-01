/**
 * Created with IntelliJ IDEA.
 * User: maopeiyi
 * Date: 11/29/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CalendarView {
    public void showNext();
    public void showPrev();
    public void showToday();
    public void showView(int year, int month, int day);
}
