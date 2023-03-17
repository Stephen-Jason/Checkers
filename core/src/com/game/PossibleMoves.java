package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class PossibleMoves {

    public static Array<int[]> getPossibleMoveIndexes(int[] spaceIndexes, Players player, Array<Array<BoardSpace>> boardSpaces){
        Array<int[]> possibleMoveIndexes = new Array<>();
        int[][] tempMoveIndexes = new int[2][2];
        int[][] tempCaptureIndexes = new int[2][2];

//            possible left
        tempMoveIndexes[0][1] = (spaceIndexes[1] -1) >= 0 ? spaceIndexes[1] -1 : -1;
        tempCaptureIndexes[0][1] = (spaceIndexes[1] -2) >= 0 ? spaceIndexes[1] -2 : -1;
//            possible right
        tempMoveIndexes[1][1] = (spaceIndexes[1] +1) <= 7 ? spaceIndexes[1] +1 : -1;
        tempCaptureIndexes[1][1] = (spaceIndexes[1] +2) <= 7 ? spaceIndexes[1] +2 : -1;

        if(player == Players.RED){

//            moving upwards
            tempMoveIndexes[0][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;
            tempMoveIndexes[1][0] = (spaceIndexes[0] + 1) <= 7 ? spaceIndexes[0] + 1 : -1;

            tempCaptureIndexes[0][0] = (spaceIndexes[0] + 2) <= 7 ? spaceIndexes[0] + 2 : -1;
            tempCaptureIndexes[1][0] = (spaceIndexes[0] + 2) <= 7 ? spaceIndexes[0] + 2 : -1;

        }
        else{
//            moving downwards
            tempMoveIndexes[0][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;
            tempMoveIndexes[1][0] = (spaceIndexes[0] - 1) >= 0 ? spaceIndexes[0] - 1 : -1;

            tempCaptureIndexes[0][0] = (spaceIndexes[0] - 2) >= 0 ? spaceIndexes[0] - 2 : -1;
            tempCaptureIndexes[1][0] = (spaceIndexes[0] - 2) >= 0 ? spaceIndexes[0] - 2 : -1;
        }

        for(byte index = 0; index < 2; index++){
            if(tempMoveIndexes[index][0] != -1 && tempMoveIndexes[index][1] != -1){
                if(!BoardUtils.getBoardSpaceByIndexes(tempMoveIndexes[index], boardSpaces).hasCheckersPiece())
                    possibleMoveIndexes.add(tempMoveIndexes[index]);
            }

            if(tempCaptureIndexes[index][0] != -1 && tempCaptureIndexes[index][1] != -1){
                boolean spaceToMoveToIsEmpty = !BoardUtils.getBoardSpaceByIndexes(tempCaptureIndexes[index], boardSpaces).hasCheckersPiece();
                boolean spaceToCaptureHasEnemyPiece = PieceUtils.isEnemyPiece(BoardUtils.getBoardSpaceByIndexes(tempMoveIndexes[index], boardSpaces), player) ;
                if(spaceToMoveToIsEmpty && spaceToCaptureHasEnemyPiece){
                    possibleMoveIndexes.add(tempCaptureIndexes[index]);
                }
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
