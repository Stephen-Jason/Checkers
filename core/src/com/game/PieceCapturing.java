package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class PieceCapturing {


    public static boolean isValidCapture(BoardSpace selectedSpace, BoardSpace spaceToMoveTo, Players player, Array<Array<BoardSpace>> boardSpaces){
        if(spaceToMoveTo.hasCheckersPiece()){
            return false;
        }
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = spaceToMoveTo.getSpaceIndexes();
        int[] capturedSpaceIndex = getCapturedSpaceIndex(spaceToMoveToIndexes, selectedSpaceIndexes, player);

        if(capturedSpaceIndex[0] < 0 || capturedSpaceIndex[1] < 0){
            return false;
        }

        BoardSpace capturedSpace = BoardUtils.getBoardSpaceByIndexes(capturedSpaceIndex, boardSpaces);

        return PieceUtils.isValidVerticalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && PieceUtils.isValidHorizontalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && PieceUtils.isEnemyPiece(capturedSpace, player);
    }


    public static void capturePiece(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player, Array<Array<BoardSpace>> boardSpaces){
        int[] capturedSpaceIndex = getCapturedSpaceIndex(currentSpace.getSpaceIndexes(), prevTouchedSpace.getSpaceIndexes(), player);
        BoardUtils.getBoardSpaceByIndexes(capturedSpaceIndex, boardSpaces).removeCheckersPiece();
        currentSpace.setCheckersPiece(new CheckersPiece(player));
        prevTouchedSpace.setIsSelected(0);
        prevTouchedSpace.removeCheckersPiece();
    }


    private static int[] getCapturedSpaceIndex(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player){
        int[] capturedSpaceIndex;

        //        capturing to the right
        if(spaceToMoveToIndexes[1] > selectedSpaceIndexes[1]){
            capturedSpaceIndex = player == Players.RED
                    ? new int[] {selectedSpaceIndexes[0]+1, selectedSpaceIndexes[1]+1}
                    : new int[] {selectedSpaceIndexes[0]-1, selectedSpaceIndexes[1]+1};

        }
//        capturing to the left
        else {
            capturedSpaceIndex = player == Players.RED
                    ? new int[] {selectedSpaceIndexes[0]+1, selectedSpaceIndexes[1]-1}
                    : new int[] {selectedSpaceIndexes[0]-1, selectedSpaceIndexes[1]-1};
        }

        return capturedSpaceIndex;
    }
}
