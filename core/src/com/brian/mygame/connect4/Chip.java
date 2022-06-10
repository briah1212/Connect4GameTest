package com.brian.mygame.connect4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Chip {

    private Texture texture;
    private Rectangle rect;
    private int x;
    private int y;

    public Chip(int x, int y){
        this.x = x;
        this.y = y;
        this.texture = null;
        this.rect = new Rectangle(this.x * 100,this.y * 100,100f,100f);
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRect() {
        System.out.println("CHIPCHIP");
        return rect;
    }
}
