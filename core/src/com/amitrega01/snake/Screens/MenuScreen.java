package com.amitrega01.snake.Screens;

import com.amitrega01.snake.MyGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.awt.Menu;

import static com.amitrega01.snake.MyGame.HEIGHT;
import static com.amitrega01.snake.MyGame.WIDTH;
import static com.amitrega01.snake.MyGame.scl;

/**
 * Created by amitr on 01.03.2017.
 */

public class MenuScreen implements Screen {

    public MyGame game;
    private Skin skin;
    private Stage stage;

    public MenuScreen(final MyGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        stage = new Stage();
        int btnW = 10 * scl;
        int btnH = 3 * scl;

        final TextButton startBtn = new TextButton("Start", skin);
        startBtn.setWidth(btnW);
        startBtn.setHeight(btnH);
        startBtn.setPosition(WIDTH / 2 - 10 * scl / 2, HEIGHT / 2);

        final TextButton optionBtn = new TextButton("Options", skin);
        optionBtn.setWidth(btnW);
        optionBtn.setHeight(btnH);
        optionBtn.setPosition(WIDTH / 2 - 10 * scl / 2, HEIGHT / 2 - btnH - scl);

        final TextButton exitBtn = new TextButton("Exit", skin);
        exitBtn.setWidth(btnW);
        exitBtn.setHeight(btnH);
        exitBtn.setPosition(WIDTH / 2 - 10 * scl / 2, HEIGHT / 2 - 2 * btnH - 2 * scl);

        final Image logo = new Image(new Texture("logo.png"));
        logo.setWidth(WIDTH - 100);
        logo.setHeight(WIDTH / (logo.getWidth() + 200) * logo.getHeight());
        logo.setPosition(3 * scl, HEIGHT - logo.getHeight() - scl);

        startBtn.addListener(new ClickListener() {
                                 @Override
                                 public void clicked(InputEvent event, float x, float y) {
                                     game.setScreen(new OptionsScreen(game));
                                 }
                             }
        );

        optionBtn.addListener(new ClickListener() {
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      game.setScreen(new OptionsScreen(game));
                                  }
                              }
        );
        exitBtn.addListener(new ClickListener() {
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      Gdx.app.exit();
                                  }
                              }
        );

        stage.addActor(startBtn);
        stage.addActor(optionBtn);
        stage.addActor(exitBtn);
        stage.addActor(logo);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.169f, 0.176f, 0.259f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) game.setScreen(new ClassicSnake(game));
        game.batch.begin();
        stage.draw();
        game.batch.end();
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
        skin.dispose();
    }
}
