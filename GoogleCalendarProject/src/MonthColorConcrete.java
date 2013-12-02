
import java.awt.Color;

public class MonthColorConcrete implements ColorInterface {

    private Color c = null;

    public MonthColorConcrete() {
        pickColor();
    }

    @Override
    public void pickColor() {
        c = Color.red;
    }

    @Override
    public Color getColor() {
        return c;
    }
}
