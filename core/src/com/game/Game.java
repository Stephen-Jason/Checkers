package com.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private final Board checkersBoard;
    private final SpriteBatch batch;
    private final DisplayHandler displayHandler;
    private final TouchEvent touchEvent;
    private final OrthographicCamera camera;

    Game(SpriteBatch batch, OrthographicCamera camera){
        this.batch = batch;
        this.checkersBoard = new Board();
        this.displayHandler = new DisplayHandler(this.batch);
        this.camera = camera;
        this.touchEvent = new TouchEvent(camera);
    }

    public void runGame(){
        this.handleTouchEvents();
        this.displayGame();
    }

    private void displayGame(){
        this.displayHandler.draw(this.checkersBoard.getBoardSpaces());
    }

    private void handleTouchEvents(){
        BoardSpace touchedSpace = this.touchEvent.getTouchedSpace(this.checkersBoard.getBoardSpaces());
//        if (touchedSpace != null){
//            EventHandler.handleSpace(touchedSpace, this.checkersBoard.getBoardSpaces());
//        }

    }

}
