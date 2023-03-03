package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class GamePiece {

    private Rectangle current_position;
    private int color;


    public abstract Array<PossibleMoveSpace> get_possible_moves_from(BoardSpace space);
    public abstract Rectangle get_current_position();
    public abstract int get_color();
    public abstract void set_color(int color);
    public abstract void set_current_position(int[] new_position);
    public abstract void set_texture(Texture checkers_img);
    public abstract Texture get_texture();
    public abstract void dispose();
    public abstract boolean is_touched(Rectangle mouse_rec);
}
