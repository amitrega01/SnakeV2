package com.amitrega01.snake;

import com.amitrega01.snake.Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGame extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shape;
    public static int WIDTH= 450;
    public static int HEIGHT = 800;
    static float scl2;
    public static int scl;
    public static int fps = 8;

    public static float speed = 8;
    public static int col;
    public static int row;

    public static boolean borders = false;

    @Override
    public void create() {
        //scalling system
        setSize();
        //end of scalling system
        batch = new SpriteBatch();

        shape = new ShapeRenderer();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
//asd
    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
    }
    public void setSize() {
        WIDTH = 450;
        HEIGHT = 800;
        scl2 = 25;
        scl = 25;
        scl2 = scl * Gdx.graphics.getWidth() / WIDTH;
        scl = Math.round(scl2);
        System.out.println(scl2);
        System.out.println(scl);
        WIDTH = (Gdx.graphics.getWidth() / scl) * scl;
        HEIGHT = (Gdx.graphics.getHeight() / scl) * scl;
        col = WIDTH / scl;
        row = HEIGHT / scl;
    }
}
