package com.amitrega01.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by amitr on 01.03.2017.
 */

public class CheckInput {

    public CheckInput() {
    }

    public static char checkInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) return 'A';
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) return 'D';
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) return 'W';
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) return 'S';
    }
}
