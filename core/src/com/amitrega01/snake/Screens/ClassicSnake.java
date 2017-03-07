package com.amitrega01.snake.Screens;

import com.amitrega01.snake.Fruit;
import com.amitrega01.snake.MyGame;
import com.amitrega01.snake.Player;
import com.amitrega01.snake.SimpleDirectionGestureDetector;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.amitrega01.snake.MyGame.HEIGHT;
import static com.amitrega01.snake.MyGame.WIDTH;
import static com.amitrega01.snake.MyGame.borders;
import static com.amitrega01.snake.MyGame.scl;
import static com.amitrega01.snake.MyGame.speed;
import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by amitr on 01.03.2017.
 */

public class ClassicSnake implements Screen {

    private MyGame game;
    private Stage stage;
    private Skin skin;

    public Player player;
    public Fruit fruit;
    static boolean state;
    float timeSpent;
    int score = 0;
    int r1, r2;
    final Label scoreL;

    public ClassicSnake(MyGame game) {
        this.game = game;

        skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        stage = new Stage();

        scoreL = new Label("Score: " + score, skin);
        scoreL.setPosition(scl + 5, scl + 5);


        stage.addActor(scoreL);

        player = new Player(game.col / 2 * scl, game.row / 2 * scl);
        random();
        if (r1 != game.col / 2 * scl && r2 != game.row / 2 * scl) fruit = new Fruit(r1, r2);
        else {
            random();
            fruit = new Fruit(r1, r2);
        }

        state = true;
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                System.out.println("up");
                if (player.dir != 2) player.setDir(1);
            }

            @Override
            public void onRight() {
                System.out.println("right");
                if (player.dir != 4) player.setDir(3);
            }

            @Override
            public void onLeft() {
                System.out.println("left");
                if (player.dir != 3) player.setDir(4);
            }

            @Override
            public void onDown() {
                System.out.println("down");
                if (player.dir != 1) player.setDir(2);

            }
        }));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.169f, 0.176f, 0.259f, 1); //bgColor
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        scoreL.setText("Score: " + score);
        stage.draw();
        if (state) {

            checkInput();
            //rysowanie
            if (borders) {
                game.shape.begin(ShapeRenderer.ShapeType.Filled);
                game.shape.setColor(1, 1, 1, 1);
                game.shape.rect(0, 0, WIDTH, scl);
                game.shape.rect(0, 0, scl, HEIGHT);
                game.shape.rect(WIDTH - scl, 0, scl, HEIGHT);
                game.shape.rect(0, HEIGHT - scl, WIDTH, scl);
                game.shape.end();
            }
            game.shape.begin(ShapeRenderer.ShapeType.Filled);

            player.draw(game.shape);
            fruit.draw(game.shape);
            game.shape.end();

            timeSpent += Gdx.graphics.getDeltaTime();
            if (timeSpent > 1) {
                //The following loop will try to catch up if you're not at 30 fps.
                //This code will reset the amount of time it needs to spend catching up if there's too
                //much to do (maybe because the device can't keep up).
                timeSpent = 1 / speed;
            }
            while (timeSpent >= 1 / speed) {


                player.move();
                timeSpent -= 1 / speed;
                eat();
                state = player.checkCollision();
            }
        } else {
            game.shape.begin(ShapeRenderer.ShapeType.Line);
            player.draw(game.shape);
            fruit.draw(game.shape);
            game.shape.end();
            if (borders) {
                game.shape.begin(ShapeRenderer.ShapeType.Filled);
                game.shape.setColor(1, 1, 1, 1);
                game.shape.rect(0, 0, WIDTH, scl);
                game.shape.rect(0, 0, scl, HEIGHT);
                game.shape.rect(WIDTH - scl, 0, scl, HEIGHT);
                game.shape.rect(0, HEIGHT - scl, WIDTH, scl);
                game.shape.end();
            }
        }

        debug();

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

    public void random() {
        if (borders) {
            r1 = (random.nextInt(game.col - 2) * scl) + scl;
            r2 = (random.nextInt(game.row - 2) * scl) + scl;
        } else {
            r1 = random.nextInt(game.col) * scl;
            r2 = random.nextInt(game.row) * scl;
        }
    }

    public void checkInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (player.dir != 3) player.setDir(4);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (player.dir != 4) player.setDir(3);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (player.dir != 2) player.setDir(1);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (player.dir != 1) player.setDir(2);

        }


    }

    public static void changeState(boolean set) {
        state = set;
    }

    void eat() {
        if (fruit.fruitX == player.playerX && fruit.fruitY == player.playerY) {
            random();
            for (int i = 0; i < player.length; i++) {
                if (r1 == player.tailX[i] && r2 == player.tailY[i]) random();
            }
            fruit.fruitX = r1;
            fruit.fruitY = r2;
            score += 11;
            player.addLength();
            System.out.println(player.length);
            return;
        }

    }


    void debug() {
        if (Gdx.input.isKeyPressed(Input.Keys.O)) player.addLength();
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) speed++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) pause();
    }
}
