package com.amitrega01.snake.Screens;

import com.amitrega01.snake.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.amitrega01.snake.MyGame.HEIGHT;
import static com.amitrega01.snake.MyGame.WIDTH;
import static com.amitrega01.snake.MyGame.borders;
import static com.amitrega01.snake.MyGame.scl;

/**
 * Created by amitr on 04.03.2017.
 */

public class OptionsScreen implements Screen {

    private MyGame game;

    private Skin skin;
    private Stage stage;

    public OptionsScreen(final MyGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        stage = new Stage();
        final Label bordersL = new Label("Borders", skin);
        bordersL.setPosition(3 * scl, MyGame.HEIGHT / 2 + 5 * scl);

        final Label speedL = new Label("Speed", skin);
        speedL.setPosition(3 * scl, MyGame.HEIGHT / 2 + scl);

        final Label sizeL = new Label("Size", skin);
        sizeL.setPosition(3 * scl, MyGame.HEIGHT / 2 - 3 * scl);

        int btnW = 6 * scl;
        int btnH = 2 * scl;

        final TextButton bordersBtn = new TextButton("Off", skin);
        bordersBtn.setWidth(btnW);
        bordersBtn.setHeight(btnH);
        bordersBtn.setPosition(WIDTH - 3 * scl / 2 - btnW, MyGame.HEIGHT / 2 + 5 * scl - btnH / 2);


        final TextButton backBtn = new TextButton("Back", skin);
        backBtn.setWidth(btnW);
        backBtn.setHeight(btnH);
        backBtn.setPosition(WIDTH/2 - btnW/2,scl*3);


        bordersBtn.addListener(new ClickListener() {
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (borders) {
                                           borders = false;
                                           bordersBtn.setText("Off");
                                       } else {
                                           borders = true;
                                           bordersBtn.setText("On");
                                       }
                                       System.out.println(borders);
                                   }
                               }
        );

        backBtn.addListener(new ClickListener() {
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       game.setScreen(new MenuScreen(game));
                                   }
                               }
        );



        stage.addActor(bordersL);
        stage.addActor(speedL);
        stage.addActor(sizeL);

        stage.addActor(bordersBtn);
        stage.addActor(backBtn);
        Gdx.input.setInputProcessor(stage);

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
        skin.dispose();
    }
}
