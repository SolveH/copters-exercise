package com.hunvik.game.sprites;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MyButton {

    private Vector2 pos;
    private Texture button;
    private float top, right, bottom, left;

    public MyButton(FileHandle fileName, int x, int y) {
        pos = new Vector2(x, y);

        button = new Texture(fileName);

        left = x;
        bottom = y;
        right = x + button.getWidth();
        top = y + button.getHeight();
    }

    public boolean isHit(int x, int y){
        return top >= y && bottom <= y && left <= x && right >= x;
    }

    public Vector2 getPos(){
        return pos;
    }

    public Texture getTexture() {
        return button;
    }

    public void dispose(){
        button.dispose();
    }
}
