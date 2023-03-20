package com.game;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PossibleMovesTest {


//    @Test
//    public void getPossibleMovesIndexesRedPlayerRightOnly(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{3, 1});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//    }
//
//
//    @Test
//    public void getPossibleMovesIndexesRedPlayerLeftOnly(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(1).get(7);
//        boardSpaces.get(2).get(6).removeCheckersPiece();
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{2, 6});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//    }
//
//
//    @Test
//    public void getPossibleMovesIndexesRedPlayerLeftAndRight(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{3, 1});
//        expected.add(new int[]{3, 3});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//        assertArrayEquals(expected.get(1), actual.get(1));
//    }
//
//
//    @Test
//    public void getPossibleMovesIndexesBlackPlayerRightOnly(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(6).get(0);
//        boardSpaces.get(5).get(1).removeCheckersPiece();
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{5, 1});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//    }
//
//
//    @Test
//    public void getPossibleMovesIndexesBlackPlayerLeftOnly(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(5).get(7);
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{4, 6});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//    }
//
//
//    @Test
//    public void getPossibleMovesIndexesBlackPlayerLeftAndRight(){
//        Board board = new Board();
//        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
//        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
//        Players player = selectedSpace.getCheckersPieceOwner();
//        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
//        Array<int[]> actual = PossibleMoves.getPossibleMoveIndexes(selectedSpaceIndexes, player, boardSpaces);
//        Array<int[]> expected = new Array<>();
//        expected.add(new int[]{4, 0});
//        expected.add(new int[]{4, 2});
//
//        assertArrayEquals(expected.get(0), actual.get(0));
//        assertArrayEquals(expected.get(1), actual.get(1));
//    }


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
    public void getPossibleMovementIndexesRedLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1}, new int[]{3, 3});

        assertArrayEquals(expected.get(0), actual.get(0));
        assertArrayEquals(expected.get(1), actual.get(1));
    }


    @Test
    public void getPossibleMovementIndexesRedLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(1).get(7);
        boardSpaces.get(2).get(6).removeCheckersPiece();
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{2, 6});

        assertArrayEquals(expected.get(0), actual.get(0));
    }




    @Test
    public void getPossibleMovementIndexesRedRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(0);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovementIndexesRedNoMoves1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(1).get(7);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleMovementIndexesRedNoMoves2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(1).get(1);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleMovementIndexesRedNoMoves3(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        board.getBoardSpaces().get(4).get(2).setCheckersPiece(new CheckersPiece(Players.RED));
        BoardSpace selectedSpace = boardSpaces.get(4).get(2);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }



    @Test
    public void getPossibleMovementIndexesBlackLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 0}, new int[]{4, 2});

        assertArrayEquals(expected.get(0), actual.get(0));
        assertArrayEquals(expected.get(1), actual.get(1));
    }


    @Test
    public void getPossibleMovementIndexesBlackLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(7);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 6});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovementIndexesBlackRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(0);
        boardSpaces.get(5).get(1).removeCheckersPiece();
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{5, 1});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleMovementIndexesBlackNoMoves1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(7).get(7);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleMovementIndexesBlackNoMoves2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(0);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleMovementIndexesBlackNoMoves3(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        board.getBoardSpaces().get(3).get(1).setCheckersPiece(new CheckersPiece(Players.BLACK));
        BoardSpace selectedSpace = boardSpaces.get(3).get(1);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }



    @Test
    public void getPossibleCaptureIndexesRedLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(3).get(1).setCheckersPiece(new CheckersPiece(Players.BLACK));
        boardSpaces.get(3).get(3).setCheckersPiece(new CheckersPiece(Players.BLACK));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 0}, new int[]{4, 4});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesRedLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(3).get(1).setCheckersPiece(new CheckersPiece(Players.BLACK));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 0});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesRedRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(3).get(3).setCheckersPiece(new CheckersPiece(Players.BLACK));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{4, 4});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesRedNoCaptures1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(3).get(1).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(3).get(3).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleCaptureIndexesRedNoCaptures2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(3).get(1).setCheckersPiece(new CheckersPiece(Players.BLACK));
        boardSpaces.get(3).get(3).setCheckersPiece(new CheckersPiece(Players.BLACK));
        boardSpaces.get(4).get(0).setCheckersPiece(new CheckersPiece(Players.BLACK));
        boardSpaces.get(4).get(4).setCheckersPiece(new CheckersPiece(Players.BLACK));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleCaptureIndexesRedNoCaptures3(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(2).get(2);
        boardSpaces.get(4).get(0).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(4).get(4).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> onlyMoves = new Array<>();
        onlyMoves.add(new int[]{3, 1}, new int[]{3, 3});

        assertArrayEquals(onlyMoves.get(0), actual.get(0));
        assertArrayEquals(onlyMoves.get(1), actual.get(1));
    }


    @Test
    public void getPossibleCaptureIndexesBlackLeftAndRight(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(3);
        boardSpaces.get(4).get(2).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(4).get(4).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 1}, new int[]{3, 5});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesBlackLeftOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(7);
        boardSpaces.get(4).get(6).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 5});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesBlackRightOnly(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(5).get(1);
        boardSpaces.get(4).get(2).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> expected = new Array<>();
        expected.add(new int[]{3, 3});

        assertArrayEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getPossibleCaptureIndexesBlackNoCaptures1(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(2);
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleCaptureIndexesBlackNoCaptures2(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(2);
        boardSpaces.get(5).get(1).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(5).get(3).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(4).get(0).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(4).get(4).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);

        assertEquals(0, actual.size);
    }


    @Test
    public void getPossibleCaptureIndexesBlackNoCaptures3(){
        Board board = new Board();
        Array<Array<BoardSpace>> boardSpaces = board.getBoardSpaces();
        BoardSpace selectedSpace = boardSpaces.get(6).get(2);
        boardSpaces.get(5).get(1).removeCheckersPiece();
        boardSpaces.get(5).get(3).removeCheckersPiece();
        boardSpaces.get(4).get(0).setCheckersPiece(new CheckersPiece(Players.RED));
        boardSpaces.get(4).get(4).setCheckersPiece(new CheckersPiece(Players.RED));
        Array<int[]> actual = PossibleMoves.getPossibleMovementIndexes(selectedSpace, boardSpaces);
        Array<int[]> onlyMoves = new Array<>();
        onlyMoves.add(new int[]{5, 1}, new int[]{5, 3});

        assertArrayEquals(onlyMoves.get(0), actual.get(0));
    }

}