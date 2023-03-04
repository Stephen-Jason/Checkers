package com.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private final Board checkersBoard;
    private final SpriteBatch batch;
    private final DisplayHandler displayHandler;
    private final EventHandler eventHandler;
    private final OrthographicCamera camera;

    Game(SpriteBatch batch, OrthographicCamera camera){
        this.batch = batch;
        this.checkersBoard = new Board();
        this.displayHandler = new DisplayHandler(this.batch);
        this.camera = camera;
        this.eventHandler = new EventHandler(camera);
    }

    public void displayGame(){
        this.displayHandler.draw(this.checkersBoard.getBoardSpaces());
    }

    public void handleTouchEvents(){
        this.eventHandler.handleTouch(this.checkersBoard.getBoardSpaces());
    }

}
