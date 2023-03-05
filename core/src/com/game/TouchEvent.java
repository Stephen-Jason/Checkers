package com.game;

public class TouchEvent {

    private BoardSpace spaceTouched;

    TouchEvent(BoardSpace spaceTouched){
        this.spaceTouched = spaceTouched;
    }


    public BoardSpace getSpaceTouched(){
        return this.spaceTouched;
    }


    public boolean spaceHasPiece(){
        return this.spaceTouched.hasCheckersPiece();
    }


    public int getSpacePieceOwner(){
        return this.spaceTouched.getCheckersPieceOwner();
    }


    public int getSpaceX(){
        return this.spaceTouched.getX();
    }


    public int getSpaceY(){
        return this.spaceTouched.getY();
    }

}
