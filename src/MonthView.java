
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthView extends JFrame {

    public final static String[] daysOfWeek = {
        "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"
    };
    JPanel monthGrid = new JPanel();
    final ArrayList<JLabel> daysLabels = new ArrayList();
    GregorianCalendar calendar = new GregorianCalendar();
    Model eventManager = new Model();
    JPanel jp = new JPanel();

    MonthView() {
        jp.setLayout(new GridLayout(0, 7));

        for (String s : daysOfWeek) {
            jp.add(new JLabel(s));
        }

        Month monthView = new Month();
        ArrayList<String> daysArr = monthView.show();

        for (String s : daysArr) {
            if (!s.equals(" ")
                    && Integer.parseInt(s) == calendar.get(GregorianCalendar.DAY_OF_MONTH) && monthView.getCurMonth() == calendar.get(GregorianCalendar.MONTH) && monthView.getCurYear() == calendar.get(GregorianCalendar.YEAR)) {
                JLabel tempLabel = new JLabel(s);
                tempLabel.setForeground(Color.red);
                Font font = tempLabel.getFont();
                tempLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
                daysLabels.add(tempLabel);
            } else {
                final JLabel jl = new JLabel(s);
                jl.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Clicked on day:" + jl.getText());
                    }

                    public void mousePressed(MouseEvent e) {
                    }

                    public void mouseReleased(MouseEvent e) {
                    }

                    public void mouseEntered(MouseEvent e) {
                    }

                    public void mouseExited(MouseEvent e) {
                    }
                });
                daysLabels.add(jl);
            }
        }
        for (JLabel jl : daysLabels) {
            jp.add(jl);
        }
        jp.repaint();
        this.add(jp);
    }
}
