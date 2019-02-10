package com.hunvik.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.hunvik.game.Exercise1;
import com.hunvik.game.sprites.CopterAnimate;

public class PlayTask3 extends State {
    private Array<CopterAnimate> copters;
    private Texture bg;

    public PlayTask3(GameStateManager gsm) {
        super(gsm);
        copters = new Array<CopterAnimate>();
        copters.add(new CopterAnimate(50, 300));
        copters.add(new CopterAnimate(100, 500));
        copters.add(new CopterAnimate(300, 500));

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
        for(CopterAnimate copter : copters){
            copter.update(dt);
        }
        for(int i = 0; i < copters.size - 1; i++){
            CopterAnimate copter = copters.get(i);
            for(int j = i+1; j < copters.size; j++){
                CopterAnimate copter2 = copters.get(j);
                if(copter.collides(copter2.getBounds())){
                    copter.changeDirection();
                    copter2.changeDirection();
                }
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Exercise1.WIDTH, Exercise1.HEIGHT);
        for(CopterAnimate copter : copters){
            sb.draw(copter.getCopter(), copter.getPosition().x,copter.getPosition().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
