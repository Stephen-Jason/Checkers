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

    public void runGame(){
        this.handleTouchEvents();
        this.displayGame();
    }

    private void displayGame(){
        this.displayHandler.draw(this.checkersBoard.getBoardSpaces());
    }

    private void handleTouchEvents(){
        BoardSpace touchedSpace = this.eventHandler.getTouchedSpace(this.checkersBoard.getBoardSpaces());
        if (touchedSpace != null){
            SpaceHandler.handleSpace(touchedSpace, this.checkersBoard.getBoardSpaces());
        }

    }

}
