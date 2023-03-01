package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Board {
    private Array<int[]> square_coordinates;
    private Array<CheckersPiece> checkers_pieces;
    private final Texture board_img;
    private final Texture red_piece_img;
    private final Texture black_piece_img;
    private final SpriteBatch batch;

    Board(){
        this.board_img = new Texture("checker-board.jpg");
        this.batch = new SpriteBatch();
        this.red_piece_img = new Texture("red_piece.png");
        this.black_piece_img = new Texture("black_piece.png");
        this.checkers_pieces = setup_pieces();


    }

    private Array<CheckersPiece> setup_pieces(){
        Array<CheckersPiece> pieces = new Array<>();
        Array<int[]> red_pieces_coords = new Array<int[]>();
        Array<int[]> black_pieces_coords = new Array<int[]>();

        red_pieces_coords.add(new int[]{32, 40}, new int[]{220, 40}, new int[]{408, 40}, new int[]{594, 40});
        red_pieces_coords.add(new int[]{126, 135}, new int[]{313, 135}, new int[]{501, 135}, new int[]{689, 135});
        red_pieces_coords.add(new int[]{32, 230}, new int[]{220, 230}, new int[]{408, 230}, new int[]{594, 230});

        black_pieces_coords.add(new int[]{126, 705}, new int[]{313, 705}, new int[]{500, 705}, new int[]{687, 705});
        black_pieces_coords.add(new int[]{32, 610}, new int[]{220, 610}, new int[]{406, 610}, new int[]{594, 610});
        black_pieces_coords.add(new int[]{126, 515}, new int[]{313, 515}, new int[]{500, 515}, new int[]{687, 515});

//        add the red pieces
        for (int[] piece_coord : red_pieces_coords){
            pieces.add(new CheckersPiece(piece_coord, 0, this.red_piece_img));
        }

//        add the black pieces
        for (int[] piece_coord : black_pieces_coords){
            pieces.add(new CheckersPiece(piece_coord, 1, this.black_piece_img));
        }

        return pieces;
    }

    public void render(){
        this.batch.begin();
        this.batch.draw(this.board_img, 0, 0);
        for(CheckersPiece piece : this.checkers_pieces){
            int x = piece.get_current_position()[0];
            int y = piece.get_current_position()[1];
            this.batch.draw(piece.get_texture(), x, y, 80, 80);
        }
        this.batch.end();
    }

    public void dispose(){
        this.batch.dispose();
        this.board_img.dispose();
    }

    public Texture get_img(){
        return this.board_img;
    }
}
