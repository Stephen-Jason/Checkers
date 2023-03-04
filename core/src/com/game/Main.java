package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	Game checkersGame;
	SpriteBatch batch;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		checkersGame = new Game(this.batch);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		this.batch.begin();
		this.checkersGame.displayGame();
		this.batch.end();
	}
	
	@Override
	public void dispose () {


	}
}
