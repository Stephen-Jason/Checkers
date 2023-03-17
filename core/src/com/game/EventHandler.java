package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class EventHandler {


    public static void handleEvent(BoardSpace currentSpace, Array<Array<BoardSpace>> boardSpaces){
        BoardSpace prevTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);
        boolean currentTouchedSpaceHasPiece = currentSpace.hasCheckersPiece();
        boolean prevTouchedSpaceHasPiece = BoardUtils.prevSelectedPieces(boardSpaces);


        if(isSelectingPiece(currentTouchedSpaceHasPiece, prevTouchedSpaceHasPiece)){
            selectPiece(currentSpace);
            Array<int[]> possibleMoveIndexes = getPossibleMoveIndexes(currentSpace.getSpaceIndexes(), currentSpace.getCheckersPieceOwner(), boardSpaces);
            addPossibleMoves(possibleMoveIndexes, boardSpaces);
            return;
        }

        if(isDeselectingPiece(currentTouchedSpaceHasPiece, currentSpace == prevTouchedSpace)){
            deselectPiece(currentSpace);
            removePossibleMoves(boardSpaces);
            return;
        }

        if(isMovingPiece(currentTouchedSpaceHasPiece, prevTouchedSpace)){
            Players player = prevTouchedSpace.getCheckersPieceOwner();

            if(isValidMove(currentSpace, prevTouchedSpace, player)){
                movePiece(currentSpace, prevTouchedSpace, player);
                removePossibleMoves(boardSpaces);
            }
            else if(isValidCapture(prevTouchedSpace, currentSpace, player, boardSpaces)){
                capturePiece(currentSpace, prevTouchedSpace, player, boardSpaces);
                removePossibleMoves(boardSpaces);
            }

        }

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


    public static boolean isValidMove(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player){
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
        int[] capturedSpaceIndex = getCapturedSpaceIndex(spaceToMoveToIndexes, selectedSpaceIndexes, player);

        BoardSpace capturedSpace = BoardUtils.getBoardSpaceByIndexes(capturedSpaceIndex, boardSpaces);

        return isValidVerticalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && isValidHorizontalMovement(spaceToMoveToIndexes, selectedSpaceIndexes, player, MoveValues.CAPTURE)
                && isEnemyPiece(capturedSpace, player);
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


    private static void capturePiece(BoardSpace currentSpace, BoardSpace prevTouchedSpace, Players player, Array<Array<BoardSpace>> boardSpaces){
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


    public static Array<int[]> getPossibleMoveIndexes(int[] spaceIndexes, Players player, Array<Array<BoardSpace>> boardSpaces){
        Array<int[]> possibleMoveIndexes = new Array<>();
        int[][] tempMoveIndexes = new int[2][2];


//            possible left
        tempMoveIndexes[0][1] = (spaceIndexes[1] -1) >= 0 ? spaceIndexes[1] -1 : -1;
//            possible right
        tempMoveIndexes[1][1] = (spaceIndexes[1] +1) <= 7 ? spaceIndexes[1] +1 : -1;

        if(player == Players.RED){

//            moving upwards
            tempMoveIndexes[0][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;
            tempMoveIndexes[1][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;

        }
        else{
//            moving downwards
            tempMoveIndexes[0][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;
            tempMoveIndexes[1][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;
        }

        for(byte index = 0; index < 2; index++){
            if(tempMoveIndexes[index][0] != -1 && tempMoveIndexes[index][1] != -1){
                if(!BoardUtils.getBoardSpaceByIndexes(tempMoveIndexes[index], boardSpaces).hasCheckersPiece())
                    possibleMoveIndexes.add(tempMoveIndexes[index]);
            }
        }

        return possibleMoveIndexes;
    }



    public static void addPossibleMoves(Array<int[]> possibleMoveIndexes, Array<Array<BoardSpace>> boardSpaces){
        for(byte index = 0; index < possibleMoveIndexes.size; index++){
            BoardUtils.getBoardSpaceByIndexes(possibleMoveIndexes.get(index), boardSpaces).setIsPossibleMovementSpace(true);
        }
    }


    public static void removePossibleMoves(Array<Array<BoardSpace>> boardSpaces){
        for(byte rowIndex = 0; rowIndex < 8; rowIndex++){

            for(byte columnIndex = 0; columnIndex < 8; columnIndex++){
                boardSpaces.get(rowIndex).get(columnIndex).setIsPossibleMovementSpace(false);
            }
        }
    }










}
