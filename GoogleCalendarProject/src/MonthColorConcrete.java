
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
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
