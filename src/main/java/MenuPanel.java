/*
 * Name: MenuPanel.java
 * Date: 9/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls the menu. The menu contains
 *                  friendBtn -> Opens the game so user can play with another person
 *                  userCompBtn -> Opens the game so the user can play against the computer
 *                  compBtn -> Opens the game so the user can see the computer play against itself
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JFrame frame;

    private final String fontName = "Cansalas";


    /**
     * Name: MenuPanel
     * Date: 9/12/2020
     * Functionality: The constructor of the class
     * @param frame: The frame in which this panel is located
     */
    public MenuPanel(JFrame frame){
        //Set up the panel
        this.setPreferredSize(GamePanel.SIZE);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(2, 3, 2, 3));

        //Set up the frame as field
        this.frame = frame;

        //Create panel layouts
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBackground(Color.BLACK);
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 10, 30));
        btnPanel.setBackground(Color.BLACK);

        //Create JPanel title
        JLabel title = new JLabel("Pong");
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(fontName, Font.PLAIN, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.NORTH);
        btnPanel.add(title);

        //The font of the buttons
        Font buttonFont = new Font(fontName, Font.PLAIN, 50);

        //Create play against friend button
        JButton friendBtn = new JButton("Play vs Friend");
        friendBtn.setBackground(Color.GRAY);
        friendBtn.setForeground(Color.BLACK);
        friendBtn.setSelected(false);
        friendBtn.setFont(buttonFont);

        //ActionListener for the button
        friendBtn.addActionListener(e ->
                changeScene(this.frame, new GamePanel(false, false, this.frame)));

        //Add the button to the panel
        btnPanel.add(friendBtn);

        //Create play against computer button
        JButton userCompBtn = new JButton("Play vs Computer");
        userCompBtn.setBackground(Color.GRAY);
        userCompBtn.setForeground(Color.BLACK);
        userCompBtn.setFont(buttonFont);

        //ActionListener for the button
        userCompBtn.addActionListener(e ->
                changeScene(this.frame, new GamePanel(false, true, this.frame)));

        //Add the button to the panel
        btnPanel.add(userCompBtn);

        //Create the computer vs computer button
        JButton compBtn = new JButton("Computer vs Computer");
        compBtn.setBackground(Color.GRAY);
        compBtn.setForeground(Color.BLACK);
        compBtn.setFont(buttonFont);

        //ActionListener for the button
        compBtn.addActionListener(e ->
                changeScene(this.frame, new GamePanel(true, true, this.frame)));

        //Add the button to the panel
        btnPanel.add(compBtn);

        //Developer name label
        JLabel devName = new JLabel("Made by: Abdallah Alqashqish");
        devName.setFont(new Font(fontName, Font.PLAIN, 30));

        //Add the developer name label to the panel
        this.add(devName, BorderLayout.SOUTH);

        //Add the layouts to the panel
        layout.add(btnPanel);
        this.add(layout, BorderLayout.CENTER);
    }

    /**
     * Name: changeScene
     * Date: 9/12/2020
     * Functionality: Changes the panel of the frame
     * @param panel: The panel we want to change to
     */
    protected static void changeScene(JFrame frame, JPanel panel){
        frame.setContentPane(panel);
        frame.revalidate(); //Refresh the view
        panel.requestFocusInWindow(); //Request focus to the panel so the game will run as intended
    }

}
