package com.game;

public abstract class PieceUtils {

    public static boolean isValidVerticalMovement(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player, MoveValues moveValues){
        return player == Players.RED
                ? spaceToMoveToIndexes[0] - selectedSpaceIndexes[0] == moveValues.spaceDifference
                && selectedSpaceIndexes[0] < moveValues.upperBoundary
                : selectedSpaceIndexes[0] - spaceToMoveToIndexes[0] == moveValues.spaceDifference
                && selectedSpaceIndexes[0] > moveValues.lowerBoundary;
    }


    public static boolean isValidHorizontalMovement(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player, MoveValues moveValues){
        return spaceToMoveToIndexes[1] - selectedSpaceIndexes[1] == moveValues.spaceDifference
                || spaceToMoveToIndexes[1] + moveValues.spaceDifference == selectedSpaceIndexes[1];
    }


    public static boolean isEnemyPiece(BoardSpace capturedSpace, Players currentPlayer){
        return capturedSpace.hasCheckersPiece() && capturedSpace.getCheckersPieceOwner().playerNumber != currentPlayer.playerNumber;
    }
}
