/*
 * Name: MouseListener.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls what happens when return button is pressed
 */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseListener extends MouseAdapter {

    private final JFrame frame;
    private final Thread thread;

    /**
     * Name: MouseListener
     * Date: 10/12/2020
     * Functionality: The constructor of the class
     * @param frame: The frame
     * @param thread: The games thread
     */
    public MouseListener(JFrame frame, Thread thread){
        this.frame = frame;
        this.thread = thread;
    }


    /**
     * Name: mouseClicked
     * Date: 11/12/2020
     * Functionality: Controls what the return button does
     * @param e: The MouseEvent of the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() <= ReturnButton.BUTTON_DIMENSION && e.getY() <= ReturnButton.BUTTON_DIMENSION){
            //Stop the game thread so the game doesn't run in the background
            this.thread.stop();
            MenuPanel.changeScene(this.frame, new MenuPanel(this.frame));
        }
    }
}
