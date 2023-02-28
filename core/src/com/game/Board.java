package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Board {
    private Array<int[]> square_coordinates;
    private Texture board_img;
    private SpriteBatch batch;

    Board(){
        this.board_img = new Texture("checker-board.jpg");
        this.batch = new SpriteBatch();

    }

    public void render(){
        this.batch.begin();
        this.batch.draw(this.board_img, 0, 0);
        this.batch.end();
    }

    public void dispose(){
        this.batch.dispose();
        this.board_img.dispose();
    }

    public Texture get_img(){
        return this.board_img;
    }
}
