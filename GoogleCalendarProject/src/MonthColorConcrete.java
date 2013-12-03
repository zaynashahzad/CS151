
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.awt.Color;

/**
 * MonthColorConcrete is used by MonthView to determine what color to paint
 * jlabels
 */
public class MonthColorConcrete implements ColorInterface {

    private Color c = null;

    public MonthColorConcrete() {
        pickColor();
    }

    /**
     * Set the color to a red
     */
    @Override
    public void pickColor() {
        c = Color.red;
    }

    /**
     * Gets the current color to use
     *
     * @return the current color
     */
    @Override
    public Color getColor() {
        return c;
    }
}
