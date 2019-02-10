package com.hunvik.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hunvik.game.Exercise1;

public class WinnerState extends State{

    private BitmapFont font;
    private String winnerText;

    public WinnerState(GameStateManager gsm, String winner) {
        super(gsm);
        font = new BitmapFont();
        if(winner == "ai"){
            winnerText = "The winner is AI!!";
        }else {
            winnerText = "The winner is you!!";
        }
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, winnerText, Exercise1.WIDTH / 2 - 50, Exercise1.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
