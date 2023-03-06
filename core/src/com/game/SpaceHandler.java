package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class SpaceHandler {


    public static void handleSpace(BoardSpace boardSpace, Array<BoardSpace> boardSpaces){

//        selecting a piece to move
        if (boardSpace.hasCheckersPiece() && !anyTouchedSpaces(boardSpaces)){
            boardSpace.setIsSelected(1);
            addPossibleMovementSpaces(getPreviouslyTouchedSpace(boardSpaces), boardSpaces);
            return;
        }

//        deselecting a piece that was already selected
        if (boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
            if (boardSpace.equals(getPreviouslyTouchedSpace(boardSpaces))){
                boardSpace.setIsSelected(0);
                removeAllPossibleMovementSpaces(boardSpaces);
            }
            return;
        }

//        moving a selected piece to a valid empty space
        if (!boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
            BoardSpace previousSpace = getPreviouslyTouchedSpace(boardSpaces);

            if (isValidMovement(previousSpace, boardSpace, boardSpaces)){

                previousSpace.setIsSelected(0);
                Players pieceOwner = previousSpace.getCheckersPieceOwner();
                previousSpace.removeCheckersPiece();
                boardSpace.setCheckersPiece(new CheckersPiece(pieceOwner));
                removeAllPossibleMovementSpaces(boardSpaces);
            }
        }

//        capturing an enemy piece


    }


    private static void addPossibleMovementSpaces(BoardSpace previousSpace, Array<BoardSpace> boardSpaces){
        int[] validMovementIndexes = getValidMoveIndexes(previousSpace, boardSpaces);

        for(int index = 0; index < validMovementIndexes.length; index++){

            if(!boardSpaces.get(validMovementIndexes[index]).hasCheckersPiece()){
                boardSpaces.get(validMovementIndexes[index]).setIsPossibleMovementSpace(true);
            }

        }
    }


    private static void removeAllPossibleMovementSpaces(Array<BoardSpace> boardSpaces){
        for (int index = 0; index < boardSpaces.size; index++){
            boardSpaces.get(index).setIsPossibleMovementSpace(false);
        }
    }


    private static boolean isValidMovement(BoardSpace previousSpace, BoardSpace newSpace, Array<BoardSpace> boardSpaces){

        int [] validMoveIndexes = getValidMoveIndexes(previousSpace, boardSpaces);
        int newSpaceIndex = boardSpaces.indexOf(newSpace, false);
        if (ArrayFunc.contains(newSpaceIndex, validMoveIndexes)){
            return true;
        }
        return false;
    }

    private static int[] getValidMoveIndexes(BoardSpace previousSpace,  Array<BoardSpace> boardSpaces){
        int previousSpaceIndex = boardSpaces.indexOf(previousSpace, false);
        int[] validMovementIndexes = new int[2];
        boolean redPlayerBelowTopLine = previousSpace.getCheckersPieceOwner().playerNumber == Players.RED.playerNumber && previousSpaceIndex < 54;
        boolean blackPlayerAboveBottomLine = previousSpace.getCheckersPieceOwner().playerNumber == Players.BLACK.playerNumber && previousSpaceIndex >= 8;
        int[] invalidMovingLeftIndexes = new int[] {0, 16, 32, 48, 56};
        int[] invalidMovingRightIndexes = new int[] {7, 15, 23, 31, 39, 47, 55, 63};

//        red player can move left or right
        if (redPlayerBelowTopLine){
            validMovementIndexes[0] = previousSpaceIndex + 7;
            validMovementIndexes[1] = previousSpaceIndex + 9;
        }

//        black player can move left or right
        if (blackPlayerAboveBottomLine){
            validMovementIndexes[0] = previousSpaceIndex - 9;
            validMovementIndexes[1] = previousSpaceIndex - 7;
        }

//        can only move right from previous space
        if (ArrayFunc.contains(previousSpaceIndex, invalidMovingLeftIndexes)){
            return new int[] {validMovementIndexes[1]};
        }
//        can only move left from previous space
        if (ArrayFunc.contains(previousSpaceIndex, invalidMovingRightIndexes)){
            return new int[] {validMovementIndexes[0]};
        }

        return validMovementIndexes;
    }

    private static boolean anyTouchedSpaces(Array<BoardSpace> boardSpaces){

        for (BoardSpace boardSpace : boardSpaces){
            if (boardSpace.isSelected() == 1){
                return true;
            }
        }
        return false;
    }

    private static BoardSpace getPreviouslyTouchedSpace(Array<BoardSpace> boardSpaces){
        for (BoardSpace boardSpace : boardSpaces){
            if (boardSpace.isSelected() == 1){
                return boardSpace;
            }
        }
        return null;
    }


}
