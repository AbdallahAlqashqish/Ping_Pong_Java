/*
 * Name: Player.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls the players movement and collisions
 */

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {

    private static final long serialVersionUID = 6227345920960327500L;

    //Player dimensions to use in constructor
    protected static final int PLAYER_WIDTH = 25; //Players width in pixels
    protected static final int PLAYER_HEIGHT = 100; //Players height in pixels
    protected static final int SPEED = 10; //Players speed in pixels
    protected static final int LEFT_PLAYER_X = 0; //X position of player on the left
    protected static final int RIGHT_PLAYER_X = GamePanel.GAME_WIDTH - Player.PLAYER_WIDTH; //X position of the player on the right
    protected static final int DEFAULT_Y_POS = GamePanel.GAME_Y_CENTER-(Player.PLAYER_HEIGHT/2); //Default y position of any player, which is in the center

    //Is the player controlled by the computer?
    protected boolean isComp;

    //Velocity of the ball
    private int yVelocity;

    //Bottom of the player
    int BottomCheck = GamePanel.GAME_HEIGHT - PLAYER_HEIGHT;

    /**
     * Name: Player
     * Date: 10/12/2020
     * Functionality: The class constructor
     * @param x: x position of the player
     * @param y: y position of the player
     * @param isComp: Is the player a computer player?
     */
    public Player(int x, int y, boolean isComp){
        //Use the super constructor
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        //Set the id of the player
        this.isComp = isComp;
    }

    /**
     * Name: draw
     * Date: 10/12/2020
     * Functionality: Draws the player on the panel
     * @param g: The graphics to use
     */
    protected void draw(Graphics g, Ball ball){
        //Set up the graphics
        g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, PLAYER_WIDTH, PLAYER_HEIGHT);

        if(this.isComp) { //Is the player a computer?
            //Get the position to set the player in comparison to the ball
            int playerToBallPos = ball.y - (Player.PLAYER_HEIGHT / 2) + Ball.BALL_DIAMETER;

            if(this.x < GamePanel.GAME_X_CENTER) { //Is the player on the left?
                if (ball.x <= GamePanel.GAME_X_CENTER) //Is the ball in the players vicinity?
                    //Set the players position
                    this.y = playerToBallPos;
            }

            if (this.x > GamePanel.GAME_X_CENTER){ //Is the player on the right?
                if (ball.x >= GamePanel.GAME_X_CENTER) //Is the ball in the players vicinity?
                    //Set the players position
                    this.y = playerToBallPos;
            }
        }
    }

    /**
     * Name: resetPosition
     * Date: 10/12/2020
     * Functionality: Resets the position of the player
     */
    protected void resetPosition(){
        this.y = DEFAULT_Y_POS;
    }

    /**
     * Name: checkCollision
     * Date: 10/12/2020
     * Functionality: Checks if the player has reached the edge of the game area
     */
    protected void checkCollision(){
        if(this.y <= 0) //Has the player reached the top?
            this.y = 0;
        if(this.y >= BottomCheck) //Has the player reached the bottom?
            this.y = BottomCheck;
    }

    /**
     * Name: setYDirection
     * Date: 10/12/2020
     * Functionality: Sets the velocity of the player in the y-axis
     * @param direction: The direction in which to send the player
     *                      -ive -> up
     *                      +ive -> down
     */
    private void setYDirection(int direction) {
        yVelocity = direction;
    }

    /**
     * Name: move
     * Date: 10/12/2020
     * Functionality: Moves the player
     */
    protected void move(){
        this.y += yVelocity;
    }

    /**
     * Name: keyPressed
     * Date: 10/12/2020
     * Functionality: The controls of the player. Moves the player depending on key pressed.
     * @param e: The key event
     */
    protected void keyPressed(KeyEvent e){
        if(this.x == LEFT_PLAYER_X && !this.isComp){ //The user controls
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_W)
                setYDirection(-SPEED);

            if (keyCode == KeyEvent.VK_S)
                setYDirection(SPEED);
        } 
        
        if(this.x == RIGHT_PLAYER_X && !this.isComp){ //The second player controls
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_UP)
                setYDirection(-SPEED);

            if (keyCode == KeyEvent.VK_DOWN)
                setYDirection(SPEED);
        }
    }

    /**
     * Name: keyReleased
     * Date: 10/12/2020
     * Functionality: The controls of the player. Stops the player depending on the key released.
     * @param e: The key event
     */
    protected void keyReleased(KeyEvent e){
        if(this.x == LEFT_PLAYER_X){ //The user controls
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_S)
                setYDirection(0);
        } else if(this.x == RIGHT_PLAYER_X && !this.isComp){ //The second user controls
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_DOWN)
                setYDirection(0);
        }
    }

}
