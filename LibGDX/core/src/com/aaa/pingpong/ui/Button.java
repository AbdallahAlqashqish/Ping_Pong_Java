package com.aaa.pingpong.ui;

import com.aaa.pingpong.ui.Listeners.OnClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button {

    public final Vector2 position;
    public final float width;
    public final float height;

    Color color;

    private OnClickListener listener;

    public Button(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        this.color = Color.WHITE;
    }

    private boolean isCLicked(float x, float y) {
        
        boolean isXOverlapping = x <= (this.position.x + this.width) && x >= this.position.x;
        boolean isYOverlapping = y <= (this.position.y + this.height) && y >= this.position.y;

        return isXOverlapping && isYOverlapping;
    }



    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }    
}
