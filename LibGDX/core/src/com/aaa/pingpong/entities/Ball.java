package com.aaa.pingpong.entities;

import com.aaa.pingpong.utils.Constants;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    public final Vector2 postition;
    public final Vector2 velocity;

    /**
     * 
     * @param x: X-axis position of spawn location
     * @param y: Y-axis position of spawn location
     * @param initVelocity: Initial velocity of the ball
     */
    public Ball(float x, float y, Vector2 initVelocity) {
        this.postition = new Vector2(x, y);
        this.velocity = initVelocity;
    }

    /**
     * 
     * @param dimensions: Dimensions of the table
     * @param delta: Time difference between frames
     */
    public void update(Vector2 dimensions, float delta) {

        if (this.postition.x + Constants.BALL_RADIUS >= dimensions.x || this.postition.x - Constants.BALL_RADIUS <= 0) {
            this.velocity.x *= -1;
        }

        if (this.postition.y + Constants.BALL_RADIUS >= dimensions.y || this.postition.y - Constants.BALL_RADIUS <= 0) {
            this.velocity.y *= -1;
        }

        this.postition.x += this.velocity.x * delta;
        this.postition.y += this.velocity.y * delta;
    }

    /**
     * 
     * @param renderer: ShapeRenderer used to render the ball
     */
    public void render(ShapeRenderer renderer) {
        renderer.circle(this.postition.x, this.postition.y, Constants.BALL_RADIUS, Constants.BALL_SEGMENTS);
    }

    
}
