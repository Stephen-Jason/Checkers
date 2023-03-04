package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

public class BoardSpace {

    private CheckersPiece checkersPiece;
    private int isSelected;
    private int x;
    private int y;

    BoardSpace(int x, int y){
        this.x = x;
        this.y = y;
        this.isSelected = 0;
    }

    public void setCheckersPiece(CheckersPiece checkersPiece){
        this.checkersPiece = checkersPiece;
    }

    public boolean hasCheckersPiece(){
        return this.checkersPiece != null;
    }

    public void removeCheckersPiece(){
        this.checkersPiece = null;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getCheckersPieceOwner(){
        return this.checkersPiece.getPlayerNumber();
    }

    public int isSelected(){
        return this.isSelected;
    }

    public void setIsSelected(int isSelected){
        this.isSelected = isSelected;
    }
}
