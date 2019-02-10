package com.hunvik.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hunvik.game.Exercise1;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class CopterAnimate {
    private Vector3 position;
    private Vector3 velocity;


    //private Sprite sprite;
    private Animation copterAnimation;
    private Texture animateSheet;
    private Sprite animateSheetSprite;
    private boolean down;
    private boolean left;
    private int velocityX;
    private int velocityY;

    private Rectangle bounds;


    public CopterAnimate(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        animateSheet = new Texture(Gdx.files.internal("animate_sheet.png"));
        animateSheetSprite = new Sprite(animateSheet);
        copterAnimation = new Animation(new TextureRegion(animateSheetSprite), 4, 0.1f);

        bounds = new Rectangle(x, y, animateSheet.getWidth() / 4, animateSheet.getHeight());

        down = true;
        left = true;

        velocityX = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        velocityY = ThreadLocalRandom.current().nextInt(1, 10 + 1);
    }

    public void update(float dt){
        copterAnimation.update(dt);
        if(position.y <= 0 || position.y >= Exercise1.HEIGHT - animateSheetSprite.getHeight()){
            down = !down;
            //sprite.flip(false, true);
        }if(position.x <= 0 || position.x >= Exercise1.WIDTH - animateSheetSprite.getWidth() / 4){
            left = !left;
            //animateSheetSprite.flip(true, false);
            copterAnimation.flip();
        }
        velocity.scl(dt);
        if(left == true){
            position.add(-velocityX, 0, 0);
        }else{
            position.add(velocityX, 0, 0);
        }
        if(down == true){
            position.add(0, -velocityY, 0);
        }else{
            position.add(0, velocityY,0);
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCopter() {
        return copterAnimation.getFrame();
    }

    public Rectangle getBounds(){
        return bounds;
    }
    public boolean collides(Rectangle player){
        return bounds.overlaps(player);
    }
    public void changeDirection(){
        left = !left;
        down = !down;
        copterAnimation.flip();
    }

}