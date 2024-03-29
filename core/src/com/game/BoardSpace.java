package com.game;

import com.badlogic.gdx.math.Rectangle;

public class BoardSpace {

    private CheckersPiece checkersPiece;
    private int isSelected;
    private boolean isPossibleMovementSpace;
    private int x;
    private int y;
    private Rectangle spaceRectangle;
    private int[] spaceIndexes;

    BoardSpace(int x, int y, int[] spaceIndexes){
        this.x = x;
        this.y = y;
        this.spaceIndexes = spaceIndexes;
        this.isSelected = 0;
        this.spaceRectangle = new Rectangle();
        this.spaceRectangle.set(x, y, 100, 100);
        this.isPossibleMovementSpace = false;
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

    public Rectangle getSpaceRectangle(){
        return this.spaceRectangle;
    }

    public Players getCheckersPieceOwner(){
        return this.checkersPiece.getPlayerNumber();
    }

    public int isSelected(){
        return this.isSelected;
    }

    public void setIsSelected(int isSelected){
        this.isSelected = isSelected;
    }

    public void setIsPossibleMovementSpace(boolean isPossibleMovementSpace){
        this.isPossibleMovementSpace = isPossibleMovementSpace;
    }

    public boolean isPossibleMovementSpace(){
        return this.isPossibleMovementSpace;
    }


    public int[] getSpaceIndexes(){
        return this.spaceIndexes;
    }
}
