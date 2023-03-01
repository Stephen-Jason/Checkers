package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public abstract class GamePiece {

    private int[] current_position;
    private int color;


    public abstract Array<int[]> get_possible_moves(Array<int[]> square_coordinates);
    public abstract int[] get_current_position();
    public abstract int get_color();
    public abstract void set_color(int color);
    public abstract void set_current_position(int[] new_position);
    public abstract void set_texture(Texture checkers_img);
    public abstract Texture get_texture();
}
