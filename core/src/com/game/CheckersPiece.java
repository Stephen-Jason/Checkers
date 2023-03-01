package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CheckersPiece extends GamePiece{

    private Rectangle current_position;
    private int color;
    private Texture checkers_img;


    CheckersPiece(int[] current_position, int color, Texture checkers_img){
        this.current_position = new Rectangle();
        this.current_position.x = current_position[0];
        this.current_position.y = current_position[1];
        this.current_position.width = 80;
        this.current_position.height = 80;
        this.color = color;
        this.checkers_img = checkers_img;
    }

    @Override
    public Array<int[]> get_possible_moves(Array<int[]> square_coordinates) {
        return null;
    }

    @Override
    public Rectangle get_current_position() {
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
        this.current_position.x = new_position[0];
        this.current_position.y = new_position[1];
    }

    @Override
    public void set_texture(Texture checkers_img) {
        this.checkers_img = checkers_img;
    }

    @Override
    public Texture get_texture() {
        return this.checkers_img;
    }

    @Override
    public void dispose() {
        this.checkers_img.dispose();
    }
}
