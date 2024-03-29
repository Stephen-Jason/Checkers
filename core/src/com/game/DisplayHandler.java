package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class DisplayHandler {
    private final Array<Array<Texture>> pieceTextures;
    private final Texture possibleMovementTexture;
    private final Texture boardTexture;
    private final SpriteBatch batch;

    DisplayHandler(SpriteBatch batch){
        this.batch = batch;
        this.pieceTextures = new Array<>();
        this.pieceTextures.add(new Array<Texture>(), new Array<Texture>());
        this.pieceTextures.get(0).add(new Texture(Gdx.files.internal("black_piece.png")));
        this.pieceTextures.get(0).add(new Texture(Gdx.files.internal("black_piece_selected.png")));
        this.pieceTextures.get(1).add(new Texture(Gdx.files.internal("red_piece.png")));
        this.pieceTextures.get(1).add(new Texture(Gdx.files.internal("red_piece_selected.png")));
        this.boardTexture = new Texture(Gdx.files.internal("checkers_board.jpg"));
        this.possibleMovementTexture = new Texture(Gdx.files.internal("possible_move.png"));
    }

    public void draw(Array<Array<BoardSpace>> boardSpaces){
        this.drawCheckersBoard();
        this.drawCheckersPieces(boardSpaces);
    }

    private void drawCheckersBoard(){
        this.batch.draw(this.boardTexture, 0, 0);
    }


    private void drawCheckersPieces(Array<Array<BoardSpace>> boardSpaces){

        for(byte rowIndex = 0; rowIndex < 8; rowIndex++){

            Array<BoardSpace> boardRows = boardSpaces.get(rowIndex);

            for(byte columnIndex = 0; columnIndex < 8; columnIndex++){
                BoardSpace boardSpace = boardRows.get(columnIndex);
                if(boardSpace.hasCheckersPiece()){
                    int playerIndex = boardSpace.getCheckersPieceOwner().playerNumber -1;
                    int isSelected = boardSpace.isSelected();
                    this.batch.draw(this.pieceTextures.get(playerIndex).get(isSelected), boardSpace.getX() + 10, boardSpace.getY() + 20, 80, 80);
                }

                if(boardSpace.isPossibleMovementSpace()){
                    this.batch.draw(this.possibleMovementTexture, boardSpace.getX() +30, boardSpace.getY()+40, 40, 40);
                }
            }

        }

//        for (BoardSpace space : boardSpaces){
//
//            if(space.hasCheckersPiece()){
//                int ownerIndex = space.getCheckersPieceOwner().playerNumber - 1;
//                int isSelected = space.isSelected();
//                this.batch.draw(this.pieceTextures.get(ownerIndex).get(isSelected), space.getX() + 10, space.getY() + 20, 80, 80);
//            }
//
//            if(space.isPossibleMovementSpace()){
//                this.batch.draw(this.possibleMovementTexture, space.getX() + 30, space.getY() + 40, 40, 40);
//            }
//
//        }
        
    }
}
