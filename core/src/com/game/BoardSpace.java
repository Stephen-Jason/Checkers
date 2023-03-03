package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class BoardSpace {
    private final Rectangle space_position;
    private CheckersPiece checkers_piece;
    private PossibleMoveSpace moveSpace;

    BoardSpace(Rectangle position){
        this.space_position = new Rectangle();
        this.space_position.set(position);
    }

    public Array<PossibleMoveSpace> get_possible_moves_from_here(){
        return this.checkers_piece.get_possible_moves_from(this);
    }

    public void set_checkers_piece(CheckersPiece piece){
        this.checkers_piece = new CheckersPiece(new int[]{(int)this.space_position.x + 10, (int)this.space_position.y + 20}, piece.get_color(), piece.get_unselected_texture(), piece.get_selected_texture());
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

    public Rectangle get_move_space_position(){
        return this.moveSpace.get_move_space_position();
    }

    public boolean has_piece(){
        return this.checkers_piece != null;
    }

    public Texture get_piece_texture(){
        return this.checkers_piece.get_current_texture();
    }

    public boolean is_touched(Rectangle mouse_rec){
        return this.space_position.overlaps(mouse_rec);
    }

    public void set_move_space(PossibleMoveSpace moveSpace){
        this.moveSpace = moveSpace;
    }

    public void remove_move_space(){
        this.moveSpace = null;
    }

    public boolean has_move_space(){
        return this.moveSpace != null;
    }

    public Texture get_move_space_texture(){
        return this.moveSpace.get_texture();
    }

    public void dispose(){
        if (this.has_piece()){
            this.checkers_piece.dispose();
        }
    }

}
