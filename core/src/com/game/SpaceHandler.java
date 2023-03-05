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

            if (!boardSpace.hasCheckersPiece()){
                BoardSpace previousSpace = getPreviouslyTouchedSpace(boardSpaces);
                previousSpace.setIsSelected(0);
                Players pieceOwner = previousSpace.getCheckersPieceOwner();
                previousSpace.removeCheckersPiece();
                boardSpace.setCheckersPiece(new CheckersPiece(pieceOwner));
            }
        }

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
