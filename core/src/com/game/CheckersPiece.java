package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class CheckersPiece extends GamePiece{

    private int[] current_position;
    private int color;
    private Texture checkers_img;


    CheckersPiece(int[] current_position, int color, Texture checkers_img){
        this.current_position = current_position;
        this.color = color;
        this.checkers_img = checkers_img;
    }

    @Override
    public Array<int[]> get_possible_moves(Array<int[]> square_coordinates) {
        return null;
    }

    @Override
    public int[] get_current_position() {
        return this.current_position;
    }

    @Override
    public int get_color() {
        return this.color;
    }

    @Override
    public void set_color(int color) {
        this.color = color;
    }

    @Override
    public void set_current_position(int[] new_position) {
        this.current_position = new_position;
    }

    @Override
    public void set_texture(Texture checkers_img) {
        this.checkers_img = checkers_img;
    }

    @Override
    public Texture get_texture() {
        return this.checkers_img;
    }
}
