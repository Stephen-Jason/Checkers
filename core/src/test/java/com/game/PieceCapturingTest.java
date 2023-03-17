package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceCapturingTest {

    @Test
    public void isValidCaptureForRedPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace spaceWithEnemyPiece = boardSpaces.get(3).get(1);
        spaceWithEnemyPiece.setCheckersPiece(new CheckersPiece(Players.BLACK));
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(4).get(2);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidCapture = PieceCapturing.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

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
        boolean isValidCapture = PieceCapturing.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

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
        boolean isValidCapture = PieceCapturing.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

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
        boolean isValidCapture = PieceCapturing.isValidCapture(selectedSpace, spaceToMoveTo, player, boardSpaces);

        assertFalse(isValidCapture);
    }


}