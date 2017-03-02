package com.amitrega01.snake;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.amitrega01.snake.MyGame.scl;

/**
 * Created by amitr on 02.03.2017.
 */

public class Fruit {
    public int fruitX;
    public int fruitY;

    public Fruit(int x, int y) {
        this.fruitX = x;
        this.fruitY = y;
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(0.937f, 0.137f, 0.235f, 1); //fruit color
        shape.rect(fruitX, fruitY, scl, scl); //owoc
    }

}
