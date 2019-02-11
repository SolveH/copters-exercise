package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hunvik.game.Exercise1;

public class PongBall {
    private static final int VELOCITY = 5;
    private Vector2 position;
    private Vector2 velocity;

    private Texture ball;
    private Sprite sprite;

    private int initialX;
    private int initialY;

    private boolean left;
    private boolean down;
    private Rectangle bounds;

    private int scoreAI;
    private int scorePlayer;

    private static final PongBall INSTANCE = new PongBall(200, Exercise1.HEIGHT / 2);

    private PongBall(int x, int y){
        initialX = x;
        initialY = y;
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        ball = new Texture(Gdx.files.internal("soccerball_new.png"));
        sprite = new Sprite(ball);
        bounds = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        scorePlayer = 0;
        scoreAI = 0;
        left = true;
        down = true;

    }

    public void update(float dt){
        if(position.y <= 0) {
            scoreAI++;
            position.set(initialX, initialY);
        }else if(position.y >= Exercise1.HEIGHT - sprite.getHeight()){
            scorePlayer++;
            position.set(initialX, initialY);
        }
        if(position.x <= 0 || position.x >= Exercise1.WIDTH - sprite.getWidth()){
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
        bounds.setPosition(position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getBall() {
        return sprite;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void changeDirection(){
        down = !down;
    }
    public int getScorePlayer(){
        return scorePlayer;
    }
    public int getScoreAI(){
        return scoreAI;
    }

    public void dispose(){
        ball.dispose();
    }

    //From exercise 2
    public static PongBall getInstance(){
        return INSTANCE;
    }
}
