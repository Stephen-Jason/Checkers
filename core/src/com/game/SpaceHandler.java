package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class SpaceHandler {


    public static void handleSpace(BoardSpace boardSpace, Array<BoardSpace> boardSpaces){

//        selecting a piece to move
        if (boardSpace.hasCheckersPiece() && !anyTouchedSpaces(boardSpaces)){
            boardSpace.setIsSelected(1);
            return;
        }

//        deselecting a piece that was already selected
        if (boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
            if (boardSpace.equals(getPreviouslyTouchedSpace(boardSpaces))){
                boardSpace.setIsSelected(0);
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
            }
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
        int[] validMoveIndexes = new int[2];
        int previousSpaceIndex = boardSpaces.indexOf(previousSpace, false);

        if (previousSpace.getCheckersPieceOwner().playerNumber == Players.RED.playerNumber && previousSpaceIndex <= 54){
            validMoveIndexes[0] = previousSpaceIndex + 7;
            validMoveIndexes[1] = previousSpaceIndex + 9;
        }

        else if (previousSpace.getCheckersPieceOwner().playerNumber == Players.BLACK.playerNumber && previousSpaceIndex > 8){
            validMoveIndexes[0] = previousSpaceIndex - 7;
            validMoveIndexes[1] = previousSpaceIndex - 9;
        }
        return validMoveIndexes;
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
