package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class EventHandler {


    public static void handleEvent(BoardSpace currentSpace, Array<Array<BoardSpace>> boardSpaces){
        BoardSpace prevTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);
        boolean currentTouchedSpaceHasPiece = currentSpace.hasCheckersPiece();
        boolean prevTouchedSpaceHasPiece = BoardUtils.prevSelectedPieces(boardSpaces);


        if(isSelectingPiece(currentTouchedSpaceHasPiece, prevTouchedSpaceHasPiece)){
            selectPiece(currentSpace);
        }

        if(isDeselectingPiece(currentTouchedSpaceHasPiece, currentSpace == prevTouchedSpace)){
            deselectPiece(currentSpace);
        }

        if(isMovingPiece(currentTouchedSpaceHasPiece, prevTouchedSpace)){
            Players player = prevTouchedSpace.getCheckersPieceOwner();
            if(isValidMove(currentSpace, prevTouchedSpace, player)){
                movePiece(currentSpace, prevTouchedSpace, player);// TODO
            }

        }

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


    public static boolean isMovingPiece(boolean touchedSpaceHasPiece, BoardSpace prevTouchedSpace){
        return !touchedSpaceHasPiece && prevTouchedSpace != null;
    }


    public static boolean isValidMove(BoardSpace prevTouchedSpace, BoardSpace currentSpace, Players player){
        if(currentSpace.hasCheckersPiece()){
            return false;
        }

        int[] selectedSpaceIndexes = prevTouchedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = currentSpace.getSpaceIndexes();

        return isValidVerticalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.MOVE)
                && isValidHorizontalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.MOVE);
    }


    public static boolean isValidCapture(BoardSpace selectedSpace, BoardSpace spaceToMoveTo, Players player, Array<Array<BoardSpace>> boardSpaces){
        if(spaceToMoveTo.hasCheckersPiece()){
            return false;
        }
        int[] selectedSpaceIndexes = selectedSpace.getSpaceIndexes();
        int[] spaceToMoveToIndexes = spaceToMoveTo.getSpaceIndexes();
        int[] capturedSpaceIndex = getCapturedSpaceIndexes(spaceToMoveToIndexes, selectedSpaceIndexes, player);

        BoardSpace capturedSpace = BoardUtils.getBoardSpaceByIndexes(capturedSpaceIndex, boardSpaces);

        return isValidVerticalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && isValidHorizontalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && isEnemyPiece(capturedSpace, player);
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


    private static boolean isValidVerticalMovement(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player, MoveValues moveValues){
        return player == Players.RED
                ? spaceToMoveToIndexes[0] - selectedSpaceIndexes[0] == moveValues.spaceDifference
                && selectedSpaceIndexes[0] < moveValues.upperBoundary
                : selectedSpaceIndexes[0] - spaceToMoveToIndexes[0] == moveValues.spaceDifference
                && selectedSpaceIndexes[0] > moveValues.lowerBoundary;
    }


    private static boolean isValidHorizontalMovement(int[] spaceToMoveToIndexes, int[] selectedSpaceIndexes, Players player, MoveValues moveValues){
        return spaceToMoveToIndexes[1] - selectedSpaceIndexes[1] == moveValues.spaceDifference
                || spaceToMoveToIndexes[1] + moveValues.spaceDifference == selectedSpaceIndexes[1];
    }


    private static boolean isEnemyPiece(BoardSpace capturedSpace, Players currentPlayer){
        return capturedSpace.hasCheckersPiece() && capturedSpace.getCheckersPieceOwner().playerNumber != currentPlayer.playerNumber;
    }


    private static void selectPiece(BoardSpace boardSpace){
        boardSpace.setIsSelected(1);
    }


    private static void deselectPiece(BoardSpace boardSpace){
        boardSpace.setIsSelected(0);
    }


    private static void movePiece(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player){
        prevTouchedSpace.removeCheckersPiece();
        prevTouchedSpace.setIsSelected(0);
        currentSpace.setCheckersPiece(new CheckersPiece(player));
    }


}
