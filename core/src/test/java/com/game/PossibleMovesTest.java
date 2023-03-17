package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PossibleMovesTest {


    @Test
    public void getPossibleMovesIndexesRedPlayerRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovesIndexesRedPlayerLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(1).get(7);
        boardSpaces.get(2).get(6).removeCheckersPiece();
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{2, 6});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovesIndexesRedPlayerLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1});
        expected.add(new int[]{3, 3});

        assertArrayEquals(expected.get(0), actual.get(0));
        assertArrayEquals(expected.get(1), actual.get(1));
    }


    @Test
    public void getPossibleMovesIndexesBlackPlayerRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(0);
        boardSpaces.get(5).get(1).removeCheckersPiece();
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{5, 1});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovesIndexesBlackPlayerLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(7);
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 6});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovesIndexesBlackPlayerLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 0});
        expected.add(new int[]{4, 2});

        assertArrayEquals(expected.get(0), actual.get(0));
        assertArrayEquals(expected.get(1), actual.get(1));
    }


    @Test
    public void addPossibleMovementsToSpaces(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        Players player = selectedSpace.getCheckersPieceOwner();
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        Array<int[]> possibleMoveIndexes = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
        PossibleMoves.addPossibleMoves(possibleMoveIndexes, boardSpaces);
        boolean isLeftPossibleMove = boardSpaces.get(3).get(1).isPossibleMovementSpace();
        boolean isRightPossibleMove = boardSpaces.get(3).get(3).isPossibleMovementSpace();

        assertTrue(isLeftPossibleMove);
        assertTrue(isRightPossibleMove);
    }


    @Test
    public void getPossibleMovementIndexesLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1}, new int[]{3, 3});

        assertArrayEquals(expected.get(0), actual.get(0));
        assertArrayEquals(expected.get(1), actual.get(1));
    }

}