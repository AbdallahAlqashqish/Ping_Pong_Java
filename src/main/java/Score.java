/*
 * Name: Score.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Prints the score in the center of the game
 */

import java.awt.*;

public class Score extends Rectangle {

    private static final long serialVersionUID = 1448105043844611787L;

    //The scores of the player
    protected int userScore;
    protected int player2Score;

    //Position of the middle line
    private final int linePos = GamePanel.GAME_X_CENTER - 1;

    /**
     * Name: Score
     * Date: 10/12/2020
     * Functionality: The constructor of the class
     */
    public Score(){
        this.userScore = 0;
        this.player2Score = 0;
    }

    /**
     * Name: draw
     * Date: 11/12/2020
     * Functionality: Draws the score
     * @param g: The graphics used to draw the score
     */
    protected void draw(Graphics g){
        //Set font
        g.setFont(new Font("Times New Roman", Font.PLAIN, 60));

        //Draw middle line
        g.fillRect(linePos, 0, 2, GamePanel.GAME_HEIGHT);

        //Draw the score
        g.drawString(String.format("%02d", this.userScore), (GamePanel.GAME_WIDTH/2)-85, 50);
        g.drawString(String.format("%02d", this.player2Score), (GamePanel.GAME_WIDTH/2)+20, 50);
    }

}
