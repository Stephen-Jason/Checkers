package com.game;


public class CheckersPiece {

    private final Players playerNumber;

    CheckersPiece(Players playerNumber){
        this.playerNumber = playerNumber;
    }

    public Players getPlayerNumber(){
        return this.playerNumber;
    }

}
