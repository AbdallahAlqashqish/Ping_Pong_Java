/*
 * Name: ActionListener.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls what happens when the keys are pressed.
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class ActionListener extends KeyAdapter {

    //The players
    private final Player user;
    private final Player player2;

    /**
     * Name: ActionListener
     * Date: 10/12/2020
     * Functionality: The class constructor
     * @param user: The user player
     * @param player2: The second player
     */
    public ActionListener(Player user, Player player2){
        this.user = user;
        this.player2 = player2;
    }

    /**
     * Name: keyPressed
     * Date: 10/12/2020
     * Functionality: Gets the key event of key pressed and sends it to the players
     * @param e: The key event
     */
    @Override
    public void keyPressed(KeyEvent e){
        user.keyPressed(e);
        player2.keyPressed(e);
    }

    /**
     * Name: keyReleased
     * Date: 10/12/2020
     * Functionality: Gets the key event of key released and sends it to the players
     * @param e: The key event
     */
    @Override
    public void keyReleased(KeyEvent e){
        user.keyReleased(e);
        player2.keyReleased(e);
    }
}