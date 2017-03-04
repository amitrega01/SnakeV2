package com.amitrega01.snake.Screens;

import com.amitrega01.snake.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.amitrega01.snake.MyGame.scl;

/**
 * Created by amitr on 04.03.2017.
 */

public class OptionsScreen implements Screen {

    private MyGame game;

    private Skin skin;
    private Stage stage;

    public OptionsScreen(MyGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        stage = new Stage();
        final Label bordersL = new Label("Borders", skin);
        bordersL.setPosition(2*scl, MyGame.HEIGHT/2 + 3*scl);

        stage.addActor(bordersL);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.169f, 0.176f, 0.259f, 1); //bgColor
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
