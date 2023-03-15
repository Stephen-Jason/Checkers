package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardUtilsTest {

    @Test
    public void getPreviouslyTouchedSpaceNoSpace(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace previouslyTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);

        assertEquals(null, previouslyTouchedSpace);
    }


    @Test
    public void GetPreviouslyTouchedSpaceIsSpace(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace expected = boardSpaces.get(0).get(0);
        expected.setIsSelected(1);
        BoardSpace actual = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);

        assertEquals(expected, actual);
    }


    @Test
    public void noSelectedPieces(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();

        boolean actual = BoardUtils.prevSelectedPieces(boardSpaces);
        assertFalse(actual);
    }


    @Test
    public void areSelectedPieces(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace expected = boardSpaces.get(0).get(0);
        expected.setIsSelected(1);
        boolean actual = BoardUtils.prevSelectedPieces(boardSpaces);
        assertTrue(actual);
    }

}
