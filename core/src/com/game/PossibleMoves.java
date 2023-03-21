package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class PossibleMoves {

//    public static Array<int[]> getPossibleMoveIndexes(int[] spaceIndexes, Players player, Array<Array<BoardSpace>> boardSpaces){
//        Array<int[]> possibleMoveIndexes = new Array<>();
//        int[][] tempMoveIndexes = new int[2][2];
//        int[][] tempCaptureIndexes = new int[2][2];
//
////            possible left
//        tempMoveIndexes[0][1] = (spaceIndexes[1] -1) >= 0 ? spaceIndexes[1] -1 : -1;
//        tempCaptureIndexes[0][1] = (spaceIndexes[1] -2) >= 0 ? spaceIndexes[1] -2 : -1;
////            possible right
//        tempMoveIndexes[1][1] = (spaceIndexes[1] +1) <= 7 ? spaceIndexes[1] +1 : -1;
//        tempCaptureIndexes[1][1] = (spaceIndexes[1] +2) <= 7 ? spaceIndexes[1] +2 : -1;
//
//        if(player == Players.RED){
//
////            moving upwards
//            tempMoveIndexes[0][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;
//            tempMoveIndexes[1][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;
//
//            tempCaptureIndexes[0][0] = (spaceIndexes[0] + 2) <= 7 ? spaceIndexes[0] + 2 : -1;
//            tempCaptureIndexes[1][0] = (spaceIndexes[0] + 2) <= 7 ? spaceIndexes[0] + 2 : -1;
//
//        }
//        else{
////            moving downwards
//            tempMoveIndexes[0][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;
//            tempMoveIndexes[1][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;
//
//            tempCaptureIndexes[0][0] = (spaceIndexes[0] - 2) >= 0 ? spaceIndexes[0] - 2 : -1;
//            tempCaptureIndexes[1][0] = (spaceIndexes[0] - 2) >= 0 ? spaceIndexes[0] - 2 : -1;
//        }
//
//        for(byte index = 0; index < 2; index++){
//            if(tempMoveIndexes[index][0] != -1 && tempMoveIndexes[index][1] != -1){
//                if(!BoardUtils.getBoardSpaceByIndexes(tempMoveIndexes[index], boardSpaces).hasCheckersPiece())
//                    possibleMoveIndexes.add(tempMoveIndexes[index]);
//            }
//
//            if(tempCaptureIndexes[index][0] != -1 && tempCaptureIndexes[index][1] != -1){
//                boolean spaceToMoveToIsEmpty = !BoardUtils.getBoardSpaceByIndexes(tempCaptureIndexes[index], boardSpaces).hasCheckersPiece();
//                boolean spaceToCaptureHasEnemyPiece = PieceUtils.isEnemyPiece(BoardUtils.getBoardSpaceByIndexes(tempMoveIndexes[index], boardSpaces), player) ;
//                if(spaceToMoveToIsEmpty && spaceToCaptureHasEnemyPiece){
//                    possibleMoveIndexes.add(tempCaptureIndexes[index]);
//                }
//            }
//        }
//
//        return possibleMoveIndexes;
//    }


    public static Array<int[]> getPossibleMovementIndexes(BoardSpace boardSpace, Array<Array<BoardSpace>> boardSpaces){
        Array<int[]> possibleMovementIndexes = new Array<>();
        Players player = boardSpace.getCheckersPieceOwner();
        int moveNum = player == Players.RED ? 1 : -1;
        int captureNum = player == Players.RED ? 2 : -2;
        int[][] calculatedMoveIndexes = calculateMoveIndex(boardSpace.getSpaceIndexes(), moveNum, 1);
        int[][] calculatedCaptureIndexes = calculateMoveIndex(boardSpace.getSpaceIndexes(), captureNum, 2);

        for (int index = 0; index < calculatedCaptureIndexes.length; index++){
            if(isValidCaptureIndex(calculatedMoveIndexes[index], calculatedCaptureIndexes[index], boardSpaces, player)){
                possibleMovementIndexes.add(calculatedCaptureIndexes[index]);
            }
        }

        if(possibleMovementIndexes.size > 0){
            return possibleMovementIndexes;
        }

        for (int index = 0; index < calculatedMoveIndexes.length; index++){
            if(isValidMoveIndex(calculatedMoveIndexes[index], boardSpaces)){
                possibleMovementIndexes.add(calculatedMoveIndexes[index]);
            }
        }

        return possibleMovementIndexes;
    }


    private static int[][] calculateMoveIndex(int[] boardSpaceIndexes, int verticalNum, int horizontalNum){
        return new int[][] {{boardSpaceIndexes[0]+verticalNum, boardSpaceIndexes[1]-horizontalNum},
                {boardSpaceIndexes[0]+verticalNum, boardSpaceIndexes[1]+horizontalNum}};
    }


    private static boolean isValidMoveIndex(int[] calculatedMove, Array<Array<BoardSpace>> boardSpaces){
        return !ArrayFunc.contains(-1, calculatedMove)
                && !ArrayFunc.contains(8, calculatedMove)
                && !BoardUtils.getBoardSpaceByIndexes(calculatedMove, boardSpaces).hasCheckersPiece();
    }


    private static boolean isValidCaptureIndex(int[] calculatedMove, int[] calculatedCapture, Array<Array<BoardSpace>> boardSpaces, Players player){
        return !ArrayFunc.contains(-1, calculatedMove)
                && !ArrayFunc.contains(8, calculatedMove)
                && !ArrayFunc.contains(-1, calculatedCapture)
                && !ArrayFunc.contains(8, calculatedCapture)
                && BoardUtils.getBoardSpaceByIndexes(calculatedMove, boardSpaces).hasCheckersPiece()
                && !BoardUtils.getBoardSpaceByIndexes(calculatedCapture, boardSpaces).hasCheckersPiece()
                && PieceUtils.isEnemyPiece(BoardUtils.getBoardSpaceByIndexes(calculatedMove, boardSpaces), player);
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


    public static boolean hasPossibleMoves(BoardSpace boardSpace, Array<Array<BoardSpace>> boardSpaces){
        return getPossibleMovementIndexes(boardSpace, boardSpaces).size > 0;
    }




}
