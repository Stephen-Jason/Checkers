package com.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private final Board checkersBoard;
    private final SpriteBatch batch;
    private final DisplayHandler displayHandler;

    Game(SpriteBatch batch){
        this.batch = batch;
        checkersBoard = new Board();
        displayHandler = new DisplayHandler(this.batch);
    }

    public void displayGame(){
        this.displayHandler.draw(this.checkersBoard.getBoardSpaces());
    }



}
