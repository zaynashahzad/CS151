import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maopeiyi
 * Date: 11/27/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewController {
    void repaintCurrentView(Date date);
    void viewNext();
    void viewPrev();
    void viewToday();
}
