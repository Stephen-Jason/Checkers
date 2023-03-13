package com.game;

import com.badlogic.gdx.utils.Array;

public class Board {

    private Array<Array<BoardSpace>> boardSpaces;
    Board(){
        this.createBoardSpaces();
        this.addCheckersPiecesToBoard();
    }


    private void createBoardSpaces(){
        this.boardSpaces = new Array<>();
        int x = 20;
        int y = 20;

        for(byte count = 0; count < 8; count++){
            Array<BoardSpace> innerBoardSpaceArray = new Array<>();

            for (byte index = 0; index < 8; index++){

                if (index == 0){
                    x = 20;
                }
                else{
                    x += 94;
                }

                innerBoardSpaceArray.add(new BoardSpace(x, y));
            }
            y += 95;
            this.boardSpaces.add(innerBoardSpaceArray);
        }
    }

    public Array<Array<BoardSpace>> getBoardSpaces(){
        return this.boardSpaces;
    }

    private void addCheckersPiecesToBoard(){
        for(byte rowIndex = 0; rowIndex < 8; rowIndex++){
            Array<BoardSpace> boardRow = this.boardSpaces.get(rowIndex);

            for(byte columnIndex = 0; columnIndex < 8; columnIndex++){
                BoardSpace boardSpace = boardRow.get(columnIndex);

//                red rows 1 and 3
                if ((rowIndex == 0 || rowIndex == 2) && (columnIndex % 2 == 0)){
                    boardSpace.setCheckersPiece(new CheckersPiece(Players.RED));
                }
//                red row 2
                if (rowIndex == 1 && (columnIndex+1) % 2 == 0){
                    boardSpace.setCheckersPiece(new CheckersPiece(Players.RED));
                }
//                black rows 1 and 3
                if ((rowIndex == 5 || rowIndex == 7) && ((columnIndex+1) % 2 == 0)){
                    boardSpace.setCheckersPiece(new CheckersPiece(Players.BLACK));
                }
//                black row 2
                if (rowIndex == 6 && columnIndex % 2 == 0){
                    boardSpace.setCheckersPiece(new CheckersPiece(Players.BLACK));
                }
            }

        }
    }



}