
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.awt.Color;
/**
 * DayColorConcrete is used by DayView to determine what color to paint jlabels
 */
public class DayColorConcrete implements ColorInterface {

    private Color c = null;

    public DayColorConcrete() {
        pickColor();
    }

    /**
     * Set the color to a blue-ish tint
     */
    public void pickColor() {
        c = new Color(135, 206, 250);
    }
    
    /**
     * Gets the current color to use
     * @return the current color
     */
    public Color getColor(){
        return c;
    }
}
