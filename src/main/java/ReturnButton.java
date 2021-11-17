/*
 * Name: ReturnButton.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: The button that when pressed will send the user back to the main menu of the game
 */

import javax.swing.*;
import java.awt.*;

public class ReturnButton extends Rectangle {

    private static final long serialVersionUID = 1231428958361744629L;

    protected static int BUTTON_DIMENSION = 50;

    //The return button
    private final Image returnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();

    /**
     * Name: ReturnButton
     * Date: 10/12/2020
     * Functionality: The constructor of the class
     * @param dimension: The dimension of the button
     */
    public ReturnButton(int dimension){
        super(0, 0, dimension, dimension);
    }

    /**
     * Name: draw
     * Date: 11/12/2020
     * Functionality: Draws the return button
     * @param g: The graphics used to draw the button
     */
    protected void draw(Graphics g) {
        //Draw the image
        g.drawImage(returnImg, this.x, this.y, null);
    }

}
