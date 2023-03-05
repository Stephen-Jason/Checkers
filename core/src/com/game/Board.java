package com.game;

import com.badlogic.gdx.utils.Array;

public class Board {
    private Array<BoardSpace> boardSpaces;

    Board(){
        this.createBoardSpaces();
        this.addCheckersPiecesToBoard();
    }

    private void createBoardSpaces(){
        boardSpaces = new Array<>();
        int x = 20;
        int y = 20;

        for (byte count = 0; count < 64; count++){

            if(count % 8 == 0 && count != 0){
                y += 95;
                x = 20;
            }
            else if (count != 0){
                x += 94;
            }

            boardSpaces.add(new BoardSpace(x, y));
        }
    }

    public Array<BoardSpace> getBoardSpaces(){
        return this.boardSpaces;
    }

    private void addCheckersPiecesToBoard(){
        int[] redPieceIndexes = {0, 2, 4, 6, 9, 11, 13, 15, 16, 18, 20, 22};
        int[] blackPieceIndexes = {41, 43, 45, 47, 48, 50, 52, 54, 57, 59, 61, 63};

        for (byte index = 0; index < 64; index++){
            if (ArrayFunc.contains(index, redPieceIndexes)){
                this.boardSpaces.get(index).setCheckersPiece(new CheckersPiece(Players.RED));
            }
            else if (ArrayFunc.contains(index, blackPieceIndexes)){
                this.boardSpaces.get(index).setCheckersPiece(new CheckersPiece(Players.BLACK));
            }

        }
    }



}