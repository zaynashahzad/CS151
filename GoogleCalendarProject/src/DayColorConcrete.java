
/**
 * COPYRIGHT (C) 2013. All Rights Reserved. A google calendar implementation.
 * Solves CS151 Google Calendar Project
 *
 * @author Peiyi Mao, Zayna Shahzad, Robert Buser
 * @version 1.01 2013/12/02
 */
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
