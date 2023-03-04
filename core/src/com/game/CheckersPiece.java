package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CheckersPiece {

    private final int playerNumber;

    CheckersPiece(int playerNumber){
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

}
