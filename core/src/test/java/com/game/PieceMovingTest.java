package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceMovingTest {


    @Test
    public void isValidMoveForRedPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(3).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = PieceMoving.isValidMove(spaceToMoveTo, selectedSpace, player);

        assertTrue(isValidMove);
    }


    @Test
    public void isValidMoveForRedPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(0).get(0);
        BoardSpace spaceToMoveTo = boardSpaces.get(1).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = PieceMoving.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertFalse(isValidMove);
    }


    @Test
    public void isValidMoveForBlackPlayerTrue(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(4).get(0);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = PieceMoving.isValidMove(spaceToMoveTo, selectedSpace, player);

        assertTrue(isValidMove);
    }


    @Test
    public void isValidMoveForBlackPlayerFalse(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        BoardSpace spaceToMoveTo = boardSpaces.get(6).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        boolean isValidMove = PieceMoving.isValidMove(selectedSpace, spaceToMoveTo, player);

        assertFalse(isValidMove);
    }

}