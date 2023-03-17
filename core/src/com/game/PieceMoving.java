package com.game;

public abstract class PieceMoving {


    public static boolean isValidMove(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player){
        if(currentSpace.hasCheckersPiece()){
            return false;
        }

        int[] selectedSpaceIndexes = prevTouchedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = currentSpace.getSpaceIndexes();

        return PieceUtils.isValidVerticalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.MOVE)
                && PieceUtils.isValidHorizontalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.MOVE);
    }


    public static void movePiece(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player){
        prevTouchedSpace.removeCheckersPiece();
        prevTouchedSpace.setIsSelected(0);
        currentSpace.setCheckersPiece(new CheckersPiece(player));
    }
}
