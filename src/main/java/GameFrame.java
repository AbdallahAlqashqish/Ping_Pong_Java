/*
 * Name: GameFrame.java
 * Date: 9/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls the frame of the game
 */

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Name: GameFrame Date: 9/12/2020 Functionality: The game frames constructor
     */
    public GameFrame(){
        this.setContentPane(new MenuPanel(this));
        this.setTitle("Ping Pong");
        this.setBackground(Color.BLACK);

        this.setResizable(false); //Want it to stay constant size
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack(); //Makes the size of frame fit the contents
        this.setVisible(true); //Display the frame
        this.setLocationRelativeTo(null); //Set it in the center of the screen

        //Set logo
        this.setIconImage(new ImageIcon(this.getClass().getResource("/logo.png")).getImage());
    }
}
