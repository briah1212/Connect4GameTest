package com.brian.mygame.connect4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class YellowChip extends Chip{

    private Texture texture;
    private Rectangle rect;
    private int x;
    private int y;

    public YellowChip(int x, int y){
        super(x,y);
        this.texture = new Texture(Gdx.files.internal("Connect4Assets/yellow.png"));
        this.rect = new Rectangle(this.x*100,this.y*100,100f,100f);
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public Rectangle getRect() {
        System.out.println("yellow");
        return rect;
    }
}
