package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSpaceTest {

    @Test
    public void getIndexesOfBoardSpace(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace boardSpace = boardSpaces.get(0).get(0);
        int[] actual = boardSpace.getSpaceIndexes();
        int[] expected = new int[]{0,0};

        assertArrayEquals(expected, actual);
    }
}
