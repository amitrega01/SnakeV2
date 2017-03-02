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

import static com.amitrega01.snake.MyGame.scl;
import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by amitr on 01.03.2017.
 */

public class ClassicSnake implements Screen {

    private MyGame game;
    public Player player;
    public Fruit fruit;
    boolean state;
    float timeSpent;
    int score = 0;
    int r1,r2;

    public ClassicSnake(MyGame game) {
        this.game = game;
        player = new Player(game.col / 2 * scl, game.row / 2 * scl);
        random();
        if (r1 != game.col / 2 * scl && r2 != game.row / 2 * scl) fruit = new Fruit(r1, r2);
        else random();

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
        Gdx.gl.glClearColor(0.169f, 0.176f, 0.259f, 1f); //bgColor
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (state) {
            checkInput();
            //rysowanie

            game.shape.begin(ShapeRenderer.ShapeType.Filled);
            player.draw(game.shape);
            fruit.draw(game.shape);
            game.shape.end();

            timeSpent += Gdx.graphics.getDeltaTime();
            if (timeSpent > 1) {
                //The following loop will try to catch up if you're not at 30 fps.
                //This code will reset the amount of time it needs to spend catching up if there's too
                //much to do (maybe because the device can't keep up).
                timeSpent = 1 / 8F;
            }
            while (timeSpent >= 1 / 8F) {

                player.move();
                timeSpent -= 1 / 8f;
                eat();
                state = player.checkCollision();
            }
        } else {
            game.shape.begin(ShapeRenderer.ShapeType.Line);
            player.draw(game.shape);
            fruit.draw(game.shape);
            game.shape.end();
        }

        game.batch.begin();
        game.font.draw(game.batch, "Score: " + score, 20, 30);
        game.batch.end();
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

    }

    public void random() {
        r1 = random.nextInt(game.col - 1) * scl;
        r2 = random.nextInt(game.row - 1) * scl;
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

    void eat() {
        if (fruit.fruitX == player.playerX && fruit.fruitY == player.playerY) {
            random();
            for (int i = 0; i<player.length; i++) {
                if (r1 == player.tailX[i] && r2 == player.tailY[i]) random();
            }
            fruit.fruitX = r1;
            fruit.fruitY = r2;
            score += 10;
            player.addLength();
            System.out.println(player.length);
            return;
        }

    }


    void debug() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) player.addLength();

    }
}
