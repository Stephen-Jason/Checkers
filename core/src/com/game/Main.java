package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	Game checkersGame;
	SpriteBatch batch;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 800);
		checkersGame = new Game(this.batch, this.camera);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.checkersGame.runGame();
		this.batch.end();
	}
	
	@Override
	public void dispose () {


	}
}
