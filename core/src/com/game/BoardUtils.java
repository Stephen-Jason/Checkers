package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class BoardUtils {


    public static BoardSpace getPreviouslyTouchedSpace(Array<Array<BoardSpace>> boardSpaces){
        for(byte rowIndex = 0; rowIndex < 8; rowIndex++){
            Array<BoardSpace> boardRow = boardSpaces.get(rowIndex);

            for (byte columnIndex = 0; columnIndex < 8; columnIndex++){
                BoardSpace boardSpace = boardRow.get(columnIndex);

                if(boardSpace.isSelected() == 1){
                    return boardSpace;
                }

            }
        }
        return null;
    }


    public static boolean prevSelectedPieces(Array<Array<BoardSpace>> boardSpaces){
        if (getPreviouslyTouchedSpace(boardSpaces) == null){
            return false;
        }
        return true;
    }


    public static BoardSpace getBoardSpaceByIndexes(int[] boardSpaceIndexes, Array<Array<BoardSpace>> boardSpaces){
        return boardSpaces.get(boardSpaceIndexes[0]).get(boardSpaceIndexes[1]);
    }

}
