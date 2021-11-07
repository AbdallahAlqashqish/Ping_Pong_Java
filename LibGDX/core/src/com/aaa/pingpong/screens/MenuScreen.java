package com.aaa.pingpong.screens;

import com.aaa.pingpong.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MenuScreen implements Screen {

    public ShapeRenderer renderer;
    public ExtendViewport viewport;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.INIT_WIDTH, Constants.INIT_HEIGHT);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(100, 100, 100, 100);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
}
