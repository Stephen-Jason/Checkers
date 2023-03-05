package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class SpaceHandler {


    public static void handleSpace(BoardSpace boardSpace, Array<BoardSpace> boardSpaces){

        if (boardSpace.hasCheckersPiece() && !anyTouchedSpaces(boardSpaces)){
            boardSpace.setIsSelected(1);
            return;
        }

        if (boardSpace.hasCheckersPiece() && anyTouchedSpaces(boardSpaces) && alreadyTouchedSpace(boardSpaces) != null){
            if (boardSpace.equals(alreadyTouchedSpace(boardSpaces))){
                boardSpace.setIsSelected(0);
                return;
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

    private static BoardSpace alreadyTouchedSpace(Array<BoardSpace> boardSpaces){
        for (BoardSpace boardSpace : boardSpaces){
            if (boardSpace.isSelected() == 1){
                return boardSpace;
            }
        }
        return null;
    }


}
