package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TouchEvent {

    private Vector3 mousePoint;
    private Rectangle mouseRectangle;
    private final OrthographicCamera camera;
    private long lastTouchedTime;
    private BoardSpace tempBoardSpace;
    private Array<BoardSpace> tempBoardSpaceRow;

    TouchEvent(OrthographicCamera camera){
        this.mousePoint = new Vector3();
        this.mouseRectangle = new Rectangle();
        this.camera = camera;
        this.lastTouchedTime = TimeUtils.nanoTime();
    }

    public BoardSpace getTouchedSpace(Array<Array<BoardSpace>> boardSpaces){

        if (!Gdx.input.isTouched()){
            return null;
        }

        this.createMouseRectangle();

        for(byte rowIndex = 0; rowIndex < 8; rowIndex++){
            this.tempBoardSpaceRow = boardSpaces.get(rowIndex);

            for(byte columnIndex = 0; columnIndex < 8; columnIndex++){
                this.tempBoardSpace = tempBoardSpaceRow.get(columnIndex);

                if (mouseOverlapsBoardSpace(this.tempBoardSpace) && isValidTimeBetweenTouches()){
                    this.lastTouchedTime = TimeUtils.nanoTime();
                    return this.tempBoardSpace;
                }
            }
        }
        return null;
    }


    private void createMouseRectangle(){
        this.mousePoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        this.camera.unproject(mousePoint);
        this.mouseRectangle.set(this.mousePoint.x, this.mousePoint.y, 5, 5);
    }


    private boolean mouseOverlapsBoardSpace(BoardSpace boardSpace){
        return mouseRectangle.overlaps(boardSpace.getSpaceRectangle());
    }


    private boolean isValidTimeBetweenTouches(){
        return TimeUtils.nanoTime() - this.lastTouchedTime > 300000000;
    }


}
