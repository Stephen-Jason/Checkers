package com.game;

import com.badlogic.gdx.utils.Array;

public abstract class EventHandler {


    public static void handleEvent(BoardSpace currentSpace, Array<Array<BoardSpace>> boardSpaces){
        BoardSpace prevTouchedSpace = BoardUtils.getPreviouslyTouchedSpace(boardSpaces);
        boolean currentTouchedSpaceHasPiece = currentSpace.hasCheckersPiece();
        boolean prevTouchedSpaceHasPiece = BoardUtils.prevSelectedPieces(boardSpaces);


        if(isSelectingPiece(currentTouchedSpaceHasPiece, prevTouchedSpaceHasPiece)){
            selectPiece(currentSpace);
            Array<int[]> possibleMoveIndexes = PossibleMoves.getPossibleMovementIndexes(currentSpace, boardSpaces);
            PossibleMoves.addPossibleMoves(possibleMoveIndexes, boardSpaces);
            return;
        }

        if(isDeselectingPiece(currentTouchedSpaceHasPiece, currentSpace == prevTouchedSpace)){
            deselectPiece(currentSpace);
            PossibleMoves.removePossibleMoves(boardSpaces);
            return;
        }

        if(isMovingPiece(currentTouchedSpaceHasPiece, prevTouchedSpace)){
            Players player = prevTouchedSpace.getCheckersPieceOwner();

            if(PieceMoving.isValidMove(currentSpace, prevTouchedSpace, player)){
                PieceMoving.movePiece(currentSpace, prevTouchedSpace, player);
                PossibleMoves.removePossibleMoves(boardSpaces);
            }
            else if(PieceCapturing.isValidCapture(prevTouchedSpace, currentSpace, player, boardSpaces)){
                PieceCapturing.capturePiece(currentSpace, prevTouchedSpace, player, boardSpaces);
                PossibleMoves.removePossibleMoves(boardSpaces);
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


    private static void selectPiece(BoardSpace boardSpace){
        boardSpace.setIsSelected(1);
    }


    private static void deselectPiece(BoardSpace boardSpace){
        boardSpace.setIsSelected(0);
    }


}
