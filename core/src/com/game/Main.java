package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Board checkers_board;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		checkers_board = new Board();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		checkers_board.render();

	}
	
	@Override
	public void dispose () {
		checkers_board.dispose();

	}
}
