/*
 * Name: GamePanel.java
 * Date: 10/12/2020
 * @author: Abdallah Alqashqish
 * Functionality: Controls the panel of the game, which contains all the components of the game.
 */

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 8897849375996646345L;

    // UI size variables
    protected static final int GAME_WIDTH = 1200;
    protected static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.555555); //Uses 5:9 height to width ratio of actual ping pong table
    //https://pingpongguide.net/ping-pong-table-size-dimensions/#:~:text=Ping%20Pong%20Table%20Size%20%26%20Dimensions%20ITTF%20Standard,be%20on%20a%20level%20along%20the%20surface%20below.
    protected static final int GAME_X_CENTER = GAME_WIDTH / 2; //Middle of game in the x-axis
    protected static final int GAME_Y_CENTER = GAME_HEIGHT/2; //Middle of game in the y-axis
    protected static final Dimension SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT); //Dimensions of panel

    //The game frame
    JFrame frame;

    //UI look variable
    Graphics graphics;
    Image image;

    //Game variables
    Player user;
    Player player2;
    Ball gameBall;
    Score score;
    ReturnButton rtrnBtn;

    //Thread to control gameplay
    final Thread gameThread;

    /**
     * Name: GamePanel
     * Date: 10/12/2020
     * Functionality: The constructor of the class
     * @param userComp: Is the player on the left a computer?
     * @param player2Comp: Is the player on the right a computer?
     * @param frame: The frame that contains the panel
     */
    public GamePanel(boolean userComp, boolean player2Comp, JFrame frame){
        //Set up the game components
        user = new Player(Player.LEFT_PLAYER_X, Player.DEFAULT_Y_POS, userComp);
        player2 = new Player(Player.RIGHT_PLAYER_X, Player.DEFAULT_Y_POS, player2Comp);
        gameBall = new Ball(Ball.DEFAULT_X_POS, Ball.DEFAULT_Y_POS, Ball.BALL_DIAMETER);
        score = new Score();
        rtrnBtn = new ReturnButton(ReturnButton.BUTTON_DIMENSION);

        //Set up the game frame as a field
        this.frame = frame;

        //Set up the panel
        this.setPreferredSize(SIZE);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener(user, player2));

        //Initialize and start the game thread
        gameThread = new Thread(this);
        gameThread.start();

        //Add the mouse listener
        this.addMouseListener(new MouseListener(this.frame, gameThread));
    }

    /**
     * Name: paint
     * Date: 10/12/2020
     * Functionality: Paints the game every frame
     * @param g: The graphics used to draw the game
     */
    @Override
    public void paint(Graphics g){
        //Setup image and graphics
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();

        //Draw image
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Name: draw
     * Date: 10/12/2020
     * Functionality: Draws the components to the panel
     * @param g: The graphics used to draw the components
     */
    private void draw(Graphics g) {
        //Draw game components
        user.draw(g, gameBall);
        player2.draw(g, gameBall);
        gameBall.draw(g);
        score.draw(g);
        rtrnBtn.draw(g);
    }

    /**
     * Name: checkCollision
     * Date: 10/12/2020
     * Functionality: Checks if the game components have collided with anything
     * @throws InterruptedException: If the thread is already interrupted
     */
    private void checkCollision() throws InterruptedException {
        //Checks players collisions
        user.checkCollision();
        player2.checkCollision();
        gameBall.checkCollision(user, player2, score);
    }

    /**
     * Name: move
     * Date: 10/12/2020
     * Functionality: Moves the components
     */
    private void move(){
        //Move player
        user.move();
        player2.move();
        gameBall.move();
    }

    /**
     * Name: run
     * Date: 10/12/2020
     * Functionality: The game loop. Runs at around 67 FPS.
     */
    public void run() {
        //Time in milliseconds taken to run single frame
        long frameTime = 15;

        //Game loop, achieves approximately 67 FPS
        while (score.userScore < 11 && score.player2Score < 11) {
            try {
                //Refresh screen
                move();
                checkCollision();
                repaint();
                //Wait
                Thread.sleep(frameTime);
            } catch (InterruptedException e) {
                //Inform the user of the error
                JOptionPane.showMessageDialog(this, "An error occurred, please try again.", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Perform when game is over
        showGameEndDialog();
    }

    /**
     * Name: showGameEndDialog
     * Date: 12/12/2020
     * Functionality: Displays a popup at the end of the game asking the user if they would like to restart
     *                  Yes -> Restarts the game
     *                  No -> Send the user back to the main menu
     */
    private void showGameEndDialog(){
        //Get the selected button
        int selection = JOptionPane.showOptionDialog(this, "Would you like to restart?", "GAMEOVER",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);


        switch (selection){
            case 0: //If yes, restart
                restart();
                break;
            case 1://If no, send the user back to the main menu
            case -1: //If the user closed the popup
                MenuPanel.changeScene(this.frame, new MenuPanel(this.frame));

                //Stop the game thread so the game doesn't run in the background
                gameThread.stop();
                break;
            default:
                JOptionPane.showMessageDialog(null, "An error occured!");
        }
    }

    /**
     * Name: restart
     * Date: 12/12/2020
     * Functionality: Restarts the game
     */
    private void restart(){
        //Reset the score
        score.userScore = 0;
        score.player2Score = 0;

        //Rerun the game
        run();
    }
}
