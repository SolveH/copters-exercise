package com.hunvik.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hunvik.game.Exercise1;
import com.hunvik.game.sprites.MyButton;

public class MenuState extends State {
    private Texture background;
    private MyButton task1;
    private MyButton task2;
    private MyButton task3;
    private MyButton task4;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Exercise1.WIDTH, Exercise1.HEIGHT);
        background = new Texture(Gdx.files.internal("bg.png"));
        task1 = new MyButton(Gdx.files.internal("task1.png"), 140, 670);
        task2 = new MyButton(Gdx.files.internal("task2.png"), 140, 470);
        task3 = new MyButton(Gdx.files.internal("task3.png"), 140, 270);
        task4 = new MyButton(Gdx.files.internal("task4.png"), 140, 70);


    }

    @Override
    public void handleInput() {
        int touchX = Gdx.input.getX();
        int touchY = Exercise1.HEIGHT - Gdx.input.getY();
        if(task1.isHit(touchX, touchY) && Gdx.input.justTouched()){
            gsm.set(new PlayTask1(gsm));
            dispose();
        }if(task2.isHit(touchX, touchY) && Gdx.input.justTouched()){
            gsm.set(new PlayTask2(gsm));
            dispose();
        }if(task3.isHit(touchX, touchY) && Gdx.input.justTouched()){
            gsm.set(new PlayTask3(gsm));
            dispose();
        }if(task4.isHit(touchX, touchY) && Gdx.input.justTouched()){
            gsm.set(new PlayTask4(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, Exercise1.WIDTH, Exercise1.HEIGHT);
        sb.draw(task1.getTexture(), task1.getPos().x, task1.getPos().y);
        sb.draw(task2.getTexture(), task2.getPos().x, task2.getPos().y);
        sb.draw(task3.getTexture(), task3.getPos().x, task3.getPos().y);
        sb.draw(task4.getTexture(), task4.getPos().x, task4.getPos().y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        task1.dispose();
        task2.dispose();
        task3.dispose();
        task4.dispose();
    }
}
