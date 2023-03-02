package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BoardSpace {
    private final Rectangle space_position;
    private CheckersPiece checkers_piece;

    BoardSpace(Rectangle position){
        this.space_position = new Rectangle();
        this.space_position.set(position);
    }

    public void set_checkers_piece(CheckersPiece piece){
        this.checkers_piece = new CheckersPiece(new int[]{(int)piece.get_current_position().x, (int)piece.get_current_position().y}, piece.get_color(), piece.get_texture());
    }

    public void remove_checkers_piece(){
        this.checkers_piece = null;
    }

    public CheckersPiece get_checkers_piece(){
        return this.checkers_piece;
    }

    public Rectangle get_space_position(){
        return this.space_position;
    }

    public boolean has_piece(){
        return this.checkers_piece != null;
    }

    public Texture get_piece_texture(){
        return this.checkers_piece.get_texture();
    }

    public boolean is_touched(Rectangle mouse_rec){
        return this.space_position.overlaps(mouse_rec);
    }

}
