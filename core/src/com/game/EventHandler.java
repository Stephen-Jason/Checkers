package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class EventHandler {

    private Vector3 mousePoint;
    private Rectangle mouseRectangle;
    private final OrthographicCamera camera;
    private long lastTouchedTime;

    EventHandler(OrthographicCamera camera){
        this.mousePoint = new Vector3();
        this.mouseRectangle = new Rectangle();
        this.camera = camera;
        this.lastTouchedTime = TimeUtils.nanoTime();
    }

    public TouchEvent getTouchEvent(Array<BoardSpace> boardSpaces){

        if (Gdx.input.isTouched()){
            this.mousePoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePoint);
            this.mouseRectangle.set(this.mousePoint.x, this.mousePoint.y, 5, 5);

            for(BoardSpace space : boardSpaces){
                if (mouseRectangle.overlaps(space.getSpaceRectangle()) && TimeUtils.nanoTime() - this.lastTouchedTime > 300000000){
                    this.lastTouchedTime = TimeUtils.nanoTime();
                    return new TouchEvent(space);
                }
            }

        }
        return null;
    }


}
