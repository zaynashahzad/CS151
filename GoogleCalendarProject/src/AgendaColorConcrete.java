
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
import java.awt.Color;

/**
 * AgendaColorConcrete is used by AgendaView to determine what color to paint
 * jlabels
 */
public class AgendaColorConcrete implements ColorInterface {

    private Color c = null;

    public AgendaColorConcrete() {
        pickColor();
    }

    /**
     * Set the color to a light gray-ish tint
     */
    public void pickColor() {
        c = new Color(222, 222, 222);
    }

    /**
     * Gets the current color to use
     *
     * @return the current color
     */
    public Color getColor() {
        return c;
    }
}
