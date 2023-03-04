package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class EventHandler {

    private Vector3 mousePoint;
    private Rectangle mouseRectangle;
    private final OrthographicCamera camera;

    EventHandler(OrthographicCamera camera){
        this.mousePoint = new Vector3();
        this.mouseRectangle = new Rectangle();
        this.camera = camera;
    }

    public void handleTouch(Array<BoardSpace> boardSpaces){

        if (Gdx.input.isTouched()){
            this.mousePoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePoint);
            this.mouseRectangle.set(this.mousePoint.x, this.mousePoint.y, 5, 5);

            for(BoardSpace space : boardSpaces){
                if (mouseRectangle.overlaps(space.getSpaceRectangle())){
                    System.out.println("X: " + space.getX() + " Y: " + space.getY());
                }
            }
        }

    }
}
