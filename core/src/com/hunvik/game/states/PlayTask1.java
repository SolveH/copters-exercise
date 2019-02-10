package com.hunvik.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hunvik.game.Exercise1;
import com.hunvik.game.sprites.Copter;

public class PlayTask1 extends State {
    private Copter copter;
    private Texture bg;

    public PlayTask1(GameStateManager gsm) {
        super(gsm);
        copter = new Copter(50, 300);
        //This sets the camera width and height. Currently it is the full screen
        //but in Flappy Bird it was half the screen.
        cam.setToOrtho(false, Exercise1.WIDTH, Exercise1.HEIGHT);
        bg = new Texture(Gdx.files.internal("bg.png"));
    }

    @Override
    protected void handleInput() {
        /*if(Gdx.input.justTouched()){

        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
        copter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Exercise1.WIDTH, Exercise1.HEIGHT);
        sb.draw(copter.getCopter(), copter.getPosition().x,copter.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
