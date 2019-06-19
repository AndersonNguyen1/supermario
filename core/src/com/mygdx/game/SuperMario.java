package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.GameScreen;

public class SuperMario extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;
	public static final float PPM = 10;

	public static final short FLOOR_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short PIPE_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short OBJECT_BIT = 64;
	public static final short MONSTER_BIT = 128;

	public SpriteBatch batch;		//container that holds all of our images and textures. When the program is ran, it loads all of the images/textures
	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("Super Mario Bros. Original Theme by Nintendo.mp3", Music.class);
		manager.finishLoading();
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render(); 	//makes the play screen handle the render method
	}
}