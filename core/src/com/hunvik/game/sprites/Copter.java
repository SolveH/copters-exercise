package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hunvik.game.Exercise1;

public class Copter {
    private static final int VELOCITY = 5;
    private Vector2 position;
    private Vector2 velocity;

    private Texture copter;
    private Sprite sprite;

    private boolean left;
    private boolean down;

    public Copter(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        copter = new Texture(Gdx.files.internal("heli1.png"));
        sprite = new Sprite(copter);
        left = true;
        down = true;
    }

    public void update(float dt){
        if(position.y <= 0 || position.y >= Exercise1.HEIGHT - sprite.getHeight()){
            down = !down;
            //sprite.flip(false, true);
        }if(position.x <= 0 || position.x >= Exercise1.WIDTH - sprite.getWidth()){
            left = !left;
            sprite.flip(true, false);
        }
        if(left == true){
            position.add(-VELOCITY, 0);
        }else{
            position.add(VELOCITY, 0);
        }
        if(down == true){
            position.add(0, -VELOCITY);
        }else{
            position.add(0, VELOCITY);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getCopter() {
        return sprite;
    }
}
