package com.hunvik.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hunvik.game.Exercise1;
import com.hunvik.game.sprites.PongBall;
import com.hunvik.game.sprites.PongLine;
import com.hunvik.game.sprites.PongLineAI;

public class PlayTask4 extends State {
    private Texture bg;
    private PongBall ball;
    private PongLine linePlayer;
    private PongLineAI lineAI;
    private BitmapFont font;
    private int scoreAI;
    private int scorePlayer;
    float time = 0;
    float newtime = 0;

    public PlayTask4(GameStateManager gsm) {
        super(gsm);
        ball = PongBall.getInstance();
        lineAI = new PongLineAI(40, Exercise1.HEIGHT - 25);
        linePlayer = new PongLine(40, 5);
        font = new BitmapFont();
        //This sets the camera width and height. Currently it is the full screen
        //but in Flappy Bird it was half the screen.
        cam.setToOrtho(false, Exercise1.WIDTH, Exercise1.HEIGHT);
        bg = new Texture(Gdx.files.internal("bg.png"));
    }

    @Override
    protected void handleInput() {
        int touchX = Gdx.input.getX();
        //int touchY = Exercise1.HEIGHT - Gdx.input.getY();
        if(touchX < Exercise1.WIDTH / 2 && Gdx.input.isTouched()){
            linePlayer.moveLeft();
        }else if(touchX >= Exercise1.WIDTH / 2 && Gdx.input.isTouched()){
            linePlayer.moveRight();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        time += dt;
        ball.update(dt);
        lineAI.update(dt);
        linePlayer.update(dt);
        if(ball.getBounds().overlaps(lineAI.getBounds())){
            ball.changeDirection();
        }else if(ball.getBounds().overlaps(linePlayer.getBounds())){
            ball.changeDirection();
        }
        ball.addObserver((obj, arg) -> {
            if(arg == "aiscore" && time-newtime > 1){
                newtime = time;
                scoreAI++;
            }else if(arg == "playerscore" && time - newtime > 1){
                newtime = time;
                scorePlayer++;
            }
        });
        if(scoreAI > 20){
            gsm.set(new WinnerState(gsm, "ai"));
            dispose();
        }else if(scorePlayer > 20){
            gsm.set(new WinnerState(gsm, "player"));
            dispose();
        }
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Exercise1.WIDTH, Exercise1.HEIGHT);
        sb.draw(lineAI.getLine(), lineAI.getPosition().x, lineAI.getPosition().y);
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(linePlayer.getLine(), linePlayer.getPosition().x, linePlayer.getPosition().y);
        font.draw(sb, Integer.toString(scoreAI), 30, Exercise1.HEIGHT - 50);
        font.draw(sb, Integer.toString(scorePlayer), 30, 50);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        ball.dispose();
        linePlayer.dispose();
        lineAI.dispose();
        font.dispose();
    }
}
