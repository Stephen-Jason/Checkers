package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PossibleMoveSpace {

    private final Texture possible_move_img;
    private final Rectangle move_space_rec;
    private final Rectangle board_space_rec;

    PossibleMoveSpace(Rectangle move_space_rec, Rectangle board_space_rec){
        this.possible_move_img = new Texture("possible_move.png");
        this.move_space_rec = new Rectangle();
        this.board_space_rec = new Rectangle();
        this.move_space_rec.set(move_space_rec);
        this.board_space_rec.set(board_space_rec);
    }

    public Texture get_texture(){
        return this.possible_move_img;
    }

    public Rectangle get_move_space_position(){
        return this.move_space_rec;
    }

    public Rectangle get_board_space_position(){
        return this.board_space_rec;
    }
}
