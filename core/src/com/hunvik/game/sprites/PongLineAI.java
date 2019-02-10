package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hunvik.game.Exercise1;

public class PongLineAI {
    private static final int VELOCITY = 200;
    private Vector2 position;
    private Vector2 velocity;

    private Texture line;
    private Sprite sprite;
    private Rectangle bounds;

    private boolean left;

    public PongLineAI(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        line = new Texture(Gdx.files.internal("paddle_new.png"));
        sprite = new Sprite(line);
        bounds = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        left = true;
    }

    public void update(float dt){
        if(position.x <= 0 || position.x >= Exercise1.WIDTH - sprite.getWidth()){
            left = !left;
        }
        if(left == true){
            velocity.x = -VELOCITY;
        }else{
            velocity.x = VELOCITY;
        }
        velocity.scl(dt);
        position.add(velocity.x, 0);
        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
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