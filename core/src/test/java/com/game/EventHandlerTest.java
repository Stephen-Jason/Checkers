package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventHandlerTest {

    @Test
    public void isSelectingAPieceFalse1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace currentPiece = boardSpaces.get(0).get(0);
        boolean anyPrevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(currentPiece, anyPrevSelectedPieces, boardSpaces);

        assertFalse(actual);
    }


    @Test
    public void isSelectingAPieceFalse2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace currentSpace = boardSpaces.get(1).get(0);
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(currentSpace, prevSelectedPieces, boardSpaces);

        assertFalse(actual);
    }


    @Test
    public void isSelectingAPieceFalse3(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace currentSpace = boardSpaces.get(6).get(2);
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(currentSpace, prevSelectedPieces, boardSpaces);

        assertFalse(actual);
    }


    @Test
    public void isSelectingAPieceFalse4(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace currentSpace = boardSpaces.get(2).get(0);
        boardSpaces.get(2).get(2).setIsSelected(1);
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(currentSpace, prevSelectedPieces, boardSpaces);

        assertFalse(actual);
    }

    @Test
    public void isSelectingAPieceTrue1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace currentSpace = boardSpaces.get(2).get(2);
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(currentSpace, prevSelectedPieces, boardSpaces);

        assertTrue(actual);
    }


    @Test
    public void isDeselectingAPieceTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace prevSelectedSpace = boardSpaces.get(0).get(0);
        prevSelectedSpace.setIsSelected(1);
        BoardSpace touchedSpace = boardSpaces.get(0).get(0);
        boolean touchedSpaceHasPiece = touchedSpace.hasCheckersPiece();
        BoardSpace prevTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);
        boolean prevTouchedSpaceIsCurrentSpace = prevTouchedSpace == touchedSpace;
        boolean actual = EventHandler.isDeselectingPiece(touchedSpaceHasPiece, prevTouchedSpaceIsCurrentSpace);

        assertTrue(actual);
    }


    @Test
    public void isDeselectingAPieceFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace prevSelectedSpace = boardSpaces.get(0).get(1);
        prevSelectedSpace.setIsSelected(1);
        BoardSpace touchedSpace = boardSpaces.get(0).get(0);
        boolean touchedSpaceHasPiece = touchedSpace.hasCheckersPiece();
        BoardSpace prevTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);
        boolean prevTouchedSpaceIsCurrentSpace = prevTouchedSpace == touchedSpace;
        boolean actual = EventHandler.isDeselectingPiece(touchedSpaceHasPiece, prevTouchedSpaceIsCurrentSpace);

        assertFalse(actual);
    }


}


















