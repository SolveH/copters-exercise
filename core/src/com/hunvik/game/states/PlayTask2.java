package com.hunvik.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hunvik.game.Exercise1;
import com.hunvik.game.sprites.MyButton;
import com.hunvik.game.sprites.MoveCopter;

public class PlayTask2 extends State {

    private MoveCopter copter;
    private Texture bg;
    private MyButton upBtn;
    private MyButton leftBtn;
    private MyButton downBtn;
    private MyButton rightBtn;
    private BitmapFont font;

    public PlayTask2(GameStateManager gsm) {
        super(gsm);
        copter = new MoveCopter(50, 300);
        upBtn = new MyButton(Gdx.files.internal("up-button.png"), 80, 80);
        leftBtn = new MyButton(Gdx.files.internal("left-button.png"), 20, 20);
        rightBtn = new MyButton(Gdx.files.internal("right-button.png"), 140, 20);
        downBtn = new MyButton(Gdx.files.internal("down-button.png"), 80, 20);
        font = new BitmapFont();
        //This sets the camera width and height. Currently it is the full screen
        //but in Flappy Bird it was half the screen.
        cam.setToOrtho(false, Exercise1.WIDTH, Exercise1.HEIGHT);
        bg = new Texture(Gdx.files.internal("bg.png"));
    }

    @Override
    protected void handleInput() {
        int touchX = Gdx.input.getX();
        int touchY = Exercise1.HEIGHT - Gdx.input.getY();
        if(upBtn.isHit(touchX, touchY) && Gdx.input.justTouched()){
            copter.moveUp();
        }if(leftBtn.isHit(touchX, touchY) && Gdx.input.justTouched()){
            copter.moveLeft();
        }if(rightBtn.isHit(touchX, touchY) && Gdx.input.justTouched()){
            copter.moveRight();
        }if(downBtn.isHit(touchX, touchY) && Gdx.input.justTouched()){
            copter.moveDown();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        copter.update(dt);
    }

    public String positionText(){
        Vector2 pos = copter.getPosition();
        return "x:" + Float.toString(pos.x) + "\ny:" + Float.toString(pos.y);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Exercise1.WIDTH, Exercise1.HEIGHT);
        sb.draw(copter.getCopter(), copter.getPosition().x,copter.getPosition().y);

        sb.draw(upBtn.getTexture(), upBtn.getPos().x, upBtn.getPos().y);
        sb.draw(leftBtn.getTexture(), leftBtn.getPos().x, leftBtn.getPos().y);
        sb.draw(rightBtn.getTexture(), rightBtn.getPos().x, rightBtn.getPos().y);
        sb.draw(downBtn.getTexture(), downBtn.getPos().x, downBtn.getPos().y);
        font.draw(sb, positionText(), 30, Exercise1.HEIGHT - 30);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
