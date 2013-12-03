import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: maopeiyi
 * Date: 12/2/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class AgendaColorConcrete implements ColorInterface{

    private Color c = null;

    public AgendaColorConcrete() {
        pickColor();
    }

    public void pickColor() {
        c = new Color(222, 222, 222);
    }

    public Color getColor(){
        return c;
    }
}
