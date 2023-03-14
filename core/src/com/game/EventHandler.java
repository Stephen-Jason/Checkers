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

    private static void removeCapturedPiece(Players pieceOwner, Array<BoardSpace> boardSpaces, BoardSpace boardSpace, int direction){
        int boardSpaceIndex = boardSpaces.indexOf(boardSpace, false);

        if (pieceOwner == Players.RED){
//            captured left
            if (direction == 0){
                boardSpaces.get(boardSpaceIndex + 7).removeCheckersPiece();
            }
            else{
                boardSpaces.get(boardSpaceIndex + 9).removeCheckersPiece();
            }
        }

        else{
            if (direction == 0){
                boardSpaces.get(boardSpaceIndex - 7).removeCheckersPiece();
            }
            else{
                boardSpaces.get(boardSpaceIndex - 9).removeCheckersPiece();
            }
        }
    }


    private static void addPossibleMovementSpaces(BoardSpace previousSpace, Array<BoardSpace> boardSpaces){
        int[] validMovementIndexes = getValidMoveIndexes(previousSpace, boardSpaces);
        int[] validCaptureIndexes = getValidCaptureIndexes(previousSpace, boardSpaces);
        for(int index = 0; index < validMovementIndexes.length; index++){

            if(!boardSpaces.get(validMovementIndexes[index]).hasCheckersPiece()){
                boardSpaces.get(validMovementIndexes[index]).setIsPossibleMovementSpace(true);
            }

        }

        for(int index = 0; index < validCaptureIndexes.length; index++){
            if(validCaptureIndexes[index] == -1){
                continue;
            }
            if(!boardSpaces.get(validCaptureIndexes[index]).hasCheckersPiece()){
                boardSpaces.get(validCaptureIndexes[index]).setIsPossibleMovementSpace(true);
            }

        }
    }


    private static void removeAllPossibleMovementSpaces(Array<BoardSpace> boardSpaces){
        for (int index = 0; index < boardSpaces.size; index++){
            boardSpaces.get(index).setIsPossibleMovementSpace(false);
        }
    }


    private static int[] isValidCapture(BoardSpace previousSpace, BoardSpace newSpace, Array<BoardSpace> boardSpaces){
        int [] validCaptureIndexes = getValidCaptureIndexes(previousSpace, boardSpaces);
        int newSpaceIndex = boardSpaces.indexOf(newSpace, false);
        for (int index = 0; index < validCaptureIndexes.length; index++){
            if (validCaptureIndexes[index] == newSpaceIndex){
                return new int[] {1, index};
            }
        }
        return new int[]{-1,-1};
    }


    private static boolean isValidMovement(BoardSpace previousSpace, BoardSpace newSpace, Array<BoardSpace> boardSpaces){

        int [] validMoveIndexes = getValidMoveIndexes(previousSpace, boardSpaces);
        int newSpaceIndex = boardSpaces.indexOf(newSpace, false);
        if (ArrayFunc.contains(newSpaceIndex, validMoveIndexes)){
            return true;
        }
        return false;
    }


    private static int[] getValidCaptureIndexes(BoardSpace previousSpace,  Array<BoardSpace> boardSpaces){
        int previousSpaceIndex = boardSpaces.indexOf(previousSpace, false);
        int[] validCaptureIndexes = new int[]{-1,-1};
        boolean redPlayerBelowTopLine = previousSpace.getCheckersPieceOwner().playerNumber == Players.RED.playerNumber && previousSpaceIndex < 54;
        boolean blackPlayerAboveBottomLine = previousSpace.getCheckersPieceOwner().playerNumber == Players.BLACK.playerNumber && previousSpaceIndex >= 8;
        int[] invalidCapturingLeftIndexes = new int[] {0, 16, 32, 48, 56, 9, 25, 41, 57};
        int[] invalidCapturingRightIndexes = new int[] {7, 15, 23, 31, 39, 47, 55, 63, 6, 22, 38, 54};

//        red player
        if (redPlayerBelowTopLine){
            int captureRightSpaceIndex = previousSpaceIndex + 9;
            int captureLeftSpaceIndex = previousSpaceIndex + 7;
            int freeSpaceRightIndex = previousSpaceIndex + 18;
            int freeSpaceLeftIndex = previousSpaceIndex + 14;

//            capturing to the right
            if (boardSpaces.get(captureRightSpaceIndex).hasCheckersPiece() && boardSpaces.get(captureRightSpaceIndex).getCheckersPieceOwner() == Players.BLACK){
                System.out.println("found a place to capture");
                validCaptureIndexes[1] = freeSpaceRightIndex;
            }

//            capturing to the left
            if (boardSpaces.get(captureLeftSpaceIndex).hasCheckersPiece() && boardSpaces.get(captureLeftSpaceIndex).getCheckersPieceOwner() == Players.BLACK){
                System.out.println("found a place to capture");
                validCaptureIndexes[0] = freeSpaceLeftIndex;
            }
        }

//            black player
        if (blackPlayerAboveBottomLine){
            int captureRightSpaceIndex = previousSpaceIndex - 9;
            int captureLeftSpaceIndex = previousSpaceIndex - 7;
            int freeSpaceRightIndex = previousSpaceIndex - 18;
            int freeSpaceLeftIndex = previousSpaceIndex - 14;

//            capturing to the right
            if (boardSpaces.get(captureRightSpaceIndex).hasCheckersPiece() && boardSpaces.get(captureRightSpaceIndex).getCheckersPieceOwner() == Players.RED){
                System.out.println("found a place to capture");
                validCaptureIndexes[1] = freeSpaceRightIndex;
            }

//            capturing to the left
            if (boardSpaces.get(captureLeftSpaceIndex).hasCheckersPiece() && boardSpaces.get(captureLeftSpaceIndex).getCheckersPieceOwner() == Players.BLACK){
                System.out.println("found a place to capture");
                validCaptureIndexes[0] = freeSpaceLeftIndex;
            }
        }

//        can only move right from previous space
        if (ArrayFunc.contains(previousSpaceIndex, invalidCapturingLeftIndexes)){
            validCaptureIndexes[0] = -1;
        }
//        can only move left from previous space
        if (ArrayFunc.contains(previousSpaceIndex, invalidCapturingRightIndexes)){
            validCaptureIndexes[1] = -1;
        }

        return validCaptureIndexes;
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
