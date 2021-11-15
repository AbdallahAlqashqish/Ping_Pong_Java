package com.aaa.pingpong.screens;

import com.aaa.pingpong.entities.Ball;
import com.aaa.pingpong.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen implements Screen {

    private ShapeRenderer renderer;
    private ExtendViewport viewport;

    private Ball ball; 

    @Override
    public void show() {
        
        renderer = new ShapeRenderer();

        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        
        ball = new Ball(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, new Vector2(50f, 50f));
    }

    private void update(float delta) {
        ball.update(new Vector2(viewport.getWorldWidth(), viewport.getWorldHeight()), delta);
    }

    @Override
    public void render(float delta) {

        // Limit delta
        delta = Math.min(0.1f, delta);


        Gdx.gl.glClearColor(Constants.WINDOW_BACKGROUND_COLOR.r, 
                            Constants.WINDOW_BACKGROUND_COLOR.g, 
                            Constants.WINDOW_BACKGROUND_COLOR.b, 
                            Constants.WINDOW_BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   

        viewport.apply();

        this.update(delta);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        
        renderer.begin(ShapeType.Filled);

        ball.render(renderer);

        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.dispose();        
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }    
}
