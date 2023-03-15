package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventHandlerTest {

    @Test
    public void isSelectingAPieceTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace touchedSpace = boardSpaces.get(0).get(0);
        touchedSpace.setCheckersPiece(new CheckersPiece(Players.BLACK));
        boolean touchedSpaceHasPiece = touchedSpace.hasCheckersPiece();
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(touchedSpaceHasPiece, prevSelectedPieces);

        assertTrue(actual);
    }


    @Test
    public void isSelectingAPieceFalse1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace touchedSpace = boardSpaces.get(0).get(1);
        boolean touchedSpaceHasPiece = touchedSpace.hasCheckersPiece();
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(touchedSpaceHasPiece, prevSelectedPieces);

        assertFalse(actual);
    }


    @Test
    public void isSelectingAPieceFalse2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace prevSelectedSpace = boardSpaces.get(0).get(2);
        prevSelectedSpace.setIsSelected(1);
        BoardSpace touchedSpace = boardSpaces.get(0).get(0);
        boolean touchedSpaceHasPiece = touchedSpace.hasCheckersPiece();
        boolean prevSelectedPieces = BoardUtils.prevSelectedPieces(boardSpaces);
        boolean actual = EventHandler.isSelectingPiece(touchedSpaceHasPiece, prevSelectedPieces);

        assertFalse(actual);
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


    @Test
    public void isValidMoveForRedPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(3).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = EventHandler.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertTrue(isValidMove);
    }


    @Test
    public void isValidMoveForRedPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(0).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(1).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = EventHandler.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertFalse(isValidMove);
    }


    @Test
    public void isValidMoveForBlackPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(4).get(0);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = EventHandler.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertTrue(isValidMove);
    }


    @Test
    public void isValidMoveForBlackPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(6).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = EventHandler.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertFalse(isValidMove);
    }


    @Test
    public void isValidCaptureForRedPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace spaceWithEnemyPiece = boardSpaces.get(3).get(1);
        spaceWithEnemyPiece.setCheckersPiece(new CheckersPiece(Players.BLACK));
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(4).get(2);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidCapture = EventHandler.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

        assertTrue(isValidCapture);
    }


    @Test
    public void isValidCaptureForRedPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace spaceWithEnemyPiece = boardSpaces.get(3).get(1);
        spaceWithEnemyPiece.setCheckersPiece(new CheckersPiece(Players.RED));
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(4).get(2);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidCapture = EventHandler.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

        assertFalse(isValidCapture);
    }


    @Test
    public void isValidCaptureForBlackPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace spaceWithEnemyPiece = boardSpaces.get(4).get(2);
        spaceWithEnemyPiece.setCheckersPiece(new CheckersPiece(Players.RED));
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(3).get(3);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidCapture = EventHandler.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

        assertTrue(isValidCapture);
    }


    @Test
    public void isValidCaptureForBlackPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace spaceWithEnemyPiece = boardSpaces.get(4).get(2);
        spaceWithEnemyPiece.setCheckersPiece(new CheckersPiece(Players.BLACK));
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(3).get(3);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidCapture = EventHandler.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

        assertFalse(isValidCapture);
    }

}
