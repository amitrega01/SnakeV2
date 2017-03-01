package com.amitrega01.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	public SpriteBatch batch;

	public static  int WIDTH = 450;
	public static  int HEIGHT = 800;
	static float scl2 =25;
	static int scl = 25;
	public static int fps = 8;
	int col,row;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//scalling system
		scl2 = scl*Gdx.graphics.getWidth() / WIDTH;
		scl = Math.round(scl2);
		System.out.println(scl2);
		System.out.println(scl);
		WIDTH = (Gdx.graphics.getWidth()/scl) *scl ;
		HEIGHT = (Gdx.graphics.getHeight()/scl) *scl ;
		col = WIDTH / scl;
		row = HEIGHT /scl;
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
