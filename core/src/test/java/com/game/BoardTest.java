package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {



    @Test
    public void boardSpacesArrayLength(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        assertTrue(boardSpaces.size == 8, "Size of boardSpacesArray should be 8, got " + boardSpaces.size);

        for(Array<BoardSpace> innerSpace: boardSpaces){
            assertTrue(innerSpace.size == 8, "Size of each inner array should be 8, got " + innerSpace.size);
        }

    }


    @Test
    public void boardSpacesCoordinates(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        for(Array<BoardSpace> innerArray : boardSpaces){
            for (byte index = 0; index < 8; index++){
                System.out.println("X: " + innerArray.get(index).getX() + " Y: " + innerArray.get(index).getY());
            }
        }
    }




}