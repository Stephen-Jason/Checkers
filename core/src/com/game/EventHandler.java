package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class EventHandler {


    public static void handleEvent(BoardSpace boardSpace, Array<Array<BoardSpace>> boardSpaces){

////        selecting a piece to move
//        if (boardSpace.hasCheckersPiece() && !anyTouchedSpaces(boardSpaces)){
//            boardSpace.setIsSelected(1);
//            addPossibleMovementSpaces(getPreviouslyTouchedSpace(boardSpaces), boardSpaces);
//            return;
//        }

////        deselecting a piece that was already selected
//        if (boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
//            if (boardSpace.equals(getPreviouslyTouchedSpace(boardSpaces))){
//                boardSpace.setIsSelected(0);
//                removeAllPossibleMovementSpaces(boardSpaces);
//            }
//            return;
//        }
//
////        moving a selected piece to a valid empty space
//        if (!boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
//            BoardSpace previousSpace = getPreviouslyTouchedSpace(boardSpaces);
//
//            if (isValidMovement(previousSpace, boardSpace, boardSpaces)){
//
//                previousSpace.setIsSelected(0);
//                Players pieceOwner = previousSpace.getCheckersPieceOwner();
//                previousSpace.removeCheckersPiece();
//                boardSpace.setCheckersPiece(new CheckersPiece(pieceOwner));
//                removeAllPossibleMovementSpaces(boardSpaces);
//            }
//        }
//
////        capturing an enemy piece
//        if(!boardSpace.hasCheckersPiece() && getPreviouslyTouchedSpace(boardSpaces) != null){
//            BoardSpace previousSpace = getPreviouslyTouchedSpace(boardSpaces);
//            int[] captureInfo = isValidCapture(previousSpace, boardSpace, boardSpaces);
//            if(captureInfo[0] != -1){
//                Players pieceOwner = previousSpace.getCheckersPieceOwner();
//                previousSpace.setIsSelected(0);
//                previousSpace.removeCheckersPiece();
//                boardSpace.setCheckersPiece(new CheckersPiece(pieceOwner));
//                removeCapturedPiece(pieceOwner, boardSpaces, previousSpace, captureInfo[1]);
//                removeAllPossibleMovementSpaces(boardSpaces);
//            }
//        }

    }


    public static boolean isSelectingPiece(boolean touchedSpaceHasPiece, boolean prevSelectedPieces){
        return touchedSpaceHasPiece && !prevSelectedPieces;
    }


    public static boolean isDeselectingPiece(boolean touchedSpaceHasPiece, boolean prevTouchedSpaceIsCurrentSpace){
        return touchedSpaceHasPiece && prevTouchedSpaceIsCurrentSpace;
    }


    public static boolean isValidMove(BoardSpace selectedSpace, BoardSpace spaceToMoveTo, Players player){
        if(spaceToMoveTo.hasCheckersPiece()){
            return false;
        }

        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = spaceToMoveTo.getSpaceIndexes();

        boolean isValidUpwardsMovement = player == Players.RED
                ? spaceToMoveToIndexes[0] - selectedSpaceIndexes[0] == 1 && selectedSpaceIndexes[0] < 7
                : selectedSpaceIndexes[0] - spaceToMoveToIndexes[0] == 1 && selectedSpaceIndexes[0] > 0;

        boolean isValidDiagonalMovement = spaceToMoveToIndexes[1] - selectedSpaceIndexes[1] == 1
                || spaceToMoveToIndexes[1] + 1 == selectedSpaceIndexes[1];
        return isValidUpwardsMovement && isValidDiagonalMovement;
    }


    public static boolean isValidCapture(BoardSpace selectedSpace, BoardSpace spaceToMoveTo, Players player, Array<Array<BoardSpace>> boardSpaces){
        if(spaceToMoveTo.hasCheckersPiece()){
            return false;
        }
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = spaceToMoveTo.getSpaceIndexes();
        int[] capturedSpaceIndex = getCapturedSpaceIndexes(spaceToMoveToIndexes, selectedSpaceIndexes, player);



        boolean isValidUpwardsMovement = player == Players.RED
                ? spaceToMoveToIndexes[0] - selectedSpaceIndexes[0] == 2 && selectedSpaceIndexes[0] < 6
                : selectedSpaceIndexes[0] - spaceToMoveToIndexes[0] == 2 && selectedSpaceIndexes[0] > 1;

        boolean isValidDiagonalMovement = spaceToMoveToIndexes[1] - selectedSpaceIndexes[1] == 2
                || spaceToMoveToIndexes[1] + 2 == selectedSpaceIndexes[1];

        BoardSpace capturedSpace = BoardUtils.getBoardSpaceByIndexes(capturedSpaceIndex, boardSpaces);

        return isValidUpwardsMovement && isValidDiagonalMovement && isEnemyPiece(capturedSpace, player);
    }


    private static int[] getCapturedSpaceIndexes(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player){
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


    private static boolean isEnemyPiece(BoardSpace capturedSpace, Players currentPlayer){
        return capturedSpace.hasCheckersPiece() && capturedSpace.getCheckersPieceOwner().playerNumber != currentPlayer.playerNumber;
    }


}
