package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hunvik.game.Exercise1;

public class PongLine {
    private static final int VELOCITY = 3;
    private Vector2 position;
    //private Vector2 velocity;

    private Texture line;
    private Sprite sprite;
    private Rectangle bounds;

    private boolean left;

    public PongLine(int x, int y){
        position = new Vector2(x,y);
        //velocity = new Vector2(0,0);
        line = new Texture(Gdx.files.internal("paddle_new.png"));
        sprite = new Sprite(line);
        bounds = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        left = true;
    }

    public void update(float dt){

        bounds.setPosition(position.x, position.y);
    }

    public void moveLeft(){
        if(position.x > 0){
            position.add(-5, 0);
            bounds.setPosition(position.x, position.y);
        }
    }
    public void moveRight(){
        if(position.x < Exercise1.WIDTH - sprite.getWidth()){
            position.add(5, 0);
            bounds.setPosition(position.x, position.y);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getLine() {
        return sprite;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        line.dispose();
    }
}
