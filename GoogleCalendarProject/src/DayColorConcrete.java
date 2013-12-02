
import java.awt.Color;

public class DayColorConcrete implements ColorInterface {

    private Color c = null;

    public DayColorConcrete() {
        pickColor();
    }

    public void pickColor() {
        c = new Color(135, 206, 250);
    }
    
    public Color getColor(){
        return c;
    }
}
