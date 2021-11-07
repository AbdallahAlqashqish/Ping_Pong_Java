/*
 * Name: Ball.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls the ball of the game
 */

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    /**
     *
     */
    private static final long serialVersionUID = -7626462666325275663L;
    
    // Ball variables
    protected static int BALL_DIAMETER = 20;
    protected static int SPEED = 3;

    //Default ball position
    protected static final int DEFAULT_X_POS = (GamePanel.GAME_X_CENTER)-(Ball.BALL_DIAMETER/2);
    protected static final int DEFAULT_Y_POS = (GamePanel.GAME_Y_CENTER)-(Ball.BALL_DIAMETER/2);

    //to select random position
    Random random = new Random();

    //Balls velocity
    protected int xVelocity;
    protected int yVelocity;

    /**
     * Name: Ball
     * Date: 10/12/2020
     * Functionality: The constructor of the class
     * @param x: The original x position of the ball
     * @param y: The original y position of the ball
     * @param diameter: The diameter of the ball
     */
    public Ball(int x, int y, int diameter){
        super(x, y, diameter, diameter);

        setRandomVelocity();
    }

    /**
     * Name: setXDirection
     * Date: 10/12/2020
     * Functionality: Sets the velocity of the ball in the x-axis
     * @param xDirection: Direction in which we want the ball to travel
     */
    private void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    /**
     * Name: setYDirection
     * Date: 10/12/2020
     * Functionality: Sets the velocity of the ball in the y-axis
     * @param yDirection: Direction in which we want the ball to travel
     */
    private void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    /**
     * Name: move
     * Date: 10/12/2020
     * Functionality: Moves the ball
     */
    protected void move(){
        this.x += xVelocity;
        this.y += yVelocity;
    }

    /**
     * Name: checkCollision
     * Date: 10/12/2020
     * Functionality: Checks if the ball hits anything
     * @param user: The users player
     * @param player2: The second player
     * @param score: The score instance
     * @throws InterruptedException: Thrown if the game thread is already interrupted
     */
    protected void checkCollision(Player user, Player player2, Score score) throws InterruptedException {
        //Check if the ball hit the wall
        checkWallCollision();

        //Check if the ball hit a player
        checkPlayerCollision(user, player2);

        //Check if the ball has been scored
        checkScoreCollision(user, player2, score);
    }

    /**
     * Name: checkWallCollision
     * Date: 10/12/2020
     * Functionality: Checks if the ball has hit the wall and reflects the ball
     */
    private void checkWallCollision(){
        //Bottom of ball
        int BottomCheck = GamePanel.GAME_HEIGHT - Ball.BALL_DIAMETER;

        if(this.y <= 0) //Has the ball hit the top?
            //Reflect the ball
            setYDirection(-yVelocity);
         else if(this.y >= BottomCheck) //Has the ball hit the bottom
            //Reflect the ball
            setYDirection(-yVelocity);
    }

    /**
     * Name: checkPlayerCollision
     * Date: 10/12/2020
     * Functionality: Checks if the ball has hit a player and reflects it if it does
     * @param user: The users player
     * @param player2: The second player
     */
    private void checkPlayerCollision(Player user, Player player2){
        if(this.intersects(user)){ //Did the ball hit the user?
            //Change the x-axis velocity and increase it
            xVelocity = Math.abs(xVelocity);
            xVelocity++;

            //Increase the y-axis velocity
            if(yVelocity > 0) //Is the ball moving down?
                yVelocity++;
            else //Is the ball moving up?
                yVelocity--;
        } else if(this.intersects(player2)){ //Did the ball hit the second player?
            //Change the x-axis velocity
            xVelocity = -Math.abs(xVelocity);
            xVelocity--;

            //Increase the y-axis velocity
            if(yVelocity > 0) //Is the ball moving down?
                yVelocity++;
            else //Is the ball moving up?
                yVelocity--;
        }

        //Set the velocities
        setXDirection(xVelocity);
        setYDirection(yVelocity);
    }

    /**
     * Name: checkScoreCollision
     * Date: 11/12/2020
     * Functionality: Checks if the ball has been scored
     * @param score: The score instance
     * @throws InterruptedException: If the game thread has already been interrupted
     */
    private void checkScoreCollision(Player user, Player player2, Score score) throws InterruptedException {
        if(this.x < -BALL_DIAMETER) { //Did the second player score?
            score.player2Score++;
            resetBall();

            //Reset players position
            user.resetPosition();
            player2.resetPosition();
        }
        else if(this.x > GamePanel.GAME_WIDTH) { //Did the user score?
            score.userScore++;
            resetBall();

            //Reset player position
            user.resetPosition();
            player2.resetPosition();
        }

    }

    /**
     * Name: resetBall
     * Date: 11/12/2020
     * Functionality: Resets the balls position
     * @throws InterruptedException: If the game thread has already been interrupted
     */
    private void resetBall() throws InterruptedException {
        //Reset the ball
        this.x = DEFAULT_X_POS;
        this.y = DEFAULT_Y_POS;

        //Wait for 500 milliseconds
        Thread.sleep(500);

        //Set the random direction
        setRandomVelocity();
    }

    /**
     * Name: getRandomVelocity
     * Date: 11/12/2020
     * Functionality: Set the ball to travel randomly in the x-axis
     * @return: Generated random velocity
     */
    private int getRandomVelocity(){
        //Get random int, 0 or 1
        int randomInt = random.nextInt(2);

        if(randomInt == 0) //Is the number 0?
            //Make it -1
            randomInt--;

        //Return velocity
        return randomInt;
    }

    /**
     * Name: setRandomVelocity
     * Date: 11/12/2020
     * Functionality: Sets the random velocity of the ball
     */
    private void setRandomVelocity(){
        //Get random velocity
        int randomVelocity = getRandomVelocity();

        //Set random velocity
        setYDirection(randomVelocity * SPEED);
        setXDirection(randomVelocity * SPEED);
    }

    /**
     * Name: draw
     * Date: 11/12/2020
     * Functionality: Draws the ball
     * @param g: The graphics to use to draw
     */
    protected void draw(Graphics g){
        //Set the color
        g.setColor(Color.WHITE);
        //Fill the ball
        g.fillOval(this.x, this.y, this.width, this.height);
    }
}
