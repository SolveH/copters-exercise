package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.hunvik.game.Exercise1;

public class MoveCopter {
    private static final int VELOCITY = -5;
    private Vector2 position;
    private Vector2 velocity;

    private Texture copter;
    private Sprite sprite;

    public MoveCopter(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        copter = new Texture(Gdx.files.internal("heli1.png"));
        sprite = new Sprite(copter);
    }

    public void update(float dt){
        if(position.y <= 0){
            if(velocity.y < 0){
                velocity.y = 0;
            }
        }else if(position.y >= Exercise1.HEIGHT - sprite.getHeight()){
            if(velocity.y > 0){
                velocity.y = 0;
            }
        }
        if(position.x <= 0){
            if(velocity.x < 0){
                velocity.x = 0;
            }
        }else if(position.x >= Exercise1.WIDTH - sprite.getWidth()){
            if(velocity.x > 0){
                velocity.x = 0;
            }
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y);
        if(velocity.x > 0){
            sprite.setFlip(true, false);
        }else if(velocity.x < 0) {
            sprite.setFlip(false, false);
        }
        velocity.scl(1/dt);
    }

    public void moveUp() {
        velocity.y = 250;
    }
    public void moveDown() {
        velocity.y = -250;
    }
    public void moveLeft() {
        velocity.x = -250;
    }
    public void moveRight() {
        velocity.x = 250;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getCopter() {
        return sprite;
    }
}
