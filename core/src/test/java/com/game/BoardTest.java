package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    private Array<Array<Point>> getDummyCoordinates(){
        Array<Array<Point>> dummyArray = new Array<>();
        int x = 20;
        int y = 20;

        for(byte count = 0; count < 8; count++){
            Array<Point> innerArray = new Array<>();

            for (byte index = 0; index < 8; index++){

                if (index == 0){
                    x = 20;
                }
                else{
                    x += 94;
                }

                innerArray.add(new Point(x, y));
            }
            y += 95;
            dummyArray.add(innerArray);
        }
        return dummyArray;
    }



    @Test
    public void boardSpacesArrayLength(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        assertEquals(8, boardSpaces.size, "Size of boardSpacesArray should be 8, got " + boardSpaces.size);

        for(Array<BoardSpace> innerSpace: boardSpaces){
            assertEquals( 8, innerSpace.size, "Size of each inner array should be 8, got " + innerSpace.size);
        }

    }


    @Test
    public void boardSpacesCoordinates(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        Array<Array<Point>> correctCoordinates = this.getDummyCoordinates();
        for(byte outerIndex = 0; outerIndex < boardSpaces.size; outerIndex++){
            for (byte innerIndex = 0; innerIndex < 8; innerIndex++){

                int boardSpaceX = boardSpaces.get(outerIndex).get(innerIndex).getX();
                int boardSpaceY = boardSpaces.get(outerIndex).get(innerIndex).getY();

                int correctX = correctCoordinates.get(outerIndex).get(innerIndex).x;
                int correctY = correctCoordinates.get(outerIndex).get(innerIndex).y;

                assertEquals(boardSpaceX, correctX, "BoardSpaceX should be : " + correctX + ", instead got : " + boardSpaceX);
                assertEquals(boardSpaceY, correctY, "BoardSpaceY should be : " + correctY + ", instead got : " + boardSpaceY);
            }
        }
    }




}