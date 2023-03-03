package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class GamePiece {

    public abstract Array<PossibleMoveSpace> get_possible_moves_from(BoardSpace space);
    public abstract Rectangle get_current_position();
    public abstract int get_color();
    public abstract void set_color(int color);
    public abstract void set_current_position(int[] new_position);
    public abstract void set_selected_texture();
    public abstract void set_unselected_texture();
    public abstract Texture get_selected_texture();
    public abstract Texture get_unselected_texture();
    public abstract Texture get_current_texture();
    public abstract void dispose();
    public abstract boolean is_touched(Rectangle mouse_rec);
}
