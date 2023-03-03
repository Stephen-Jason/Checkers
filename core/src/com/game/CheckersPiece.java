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
    public Array<PossibleMoveSpace> get_possible_moves_from(BoardSpace space) {
        Array<PossibleMoveSpace> possibleMoveSpaces = new Array<>();
        Rectangle left_move_space_rec = new Rectangle();
        Rectangle right_move_space_rec = new Rectangle();
        Rectangle left_board_space_rec = new Rectangle();
        Rectangle right_board_space_rec = new Rectangle();
        int space_x = (int)space.get_space_position().x;
        int space_y = (int)space.get_space_position().y;
        int piece_color = space.get_checkers_piece().get_color();

        if (piece_color == 0 && space.get_space_position().y <= 610){
            left_move_space_rec.set(space_x - 94 + 25, space_y + 95 + 35, 100, 100);
            right_move_space_rec.set(space_x + 94 + 25, space_y + 95 + 35, 100, 100);
            left_board_space_rec.set(space_x - 94, space_y + 95, 100, 100);
            right_board_space_rec.set(space_x + 94, space_y + 95, 100, 100);
            possibleMoveSpaces.add(new PossibleMoveSpace(left_move_space_rec, left_board_space_rec), new PossibleMoveSpace(right_move_space_rec, right_board_space_rec));
        }
        else if (piece_color == 1 && space.get_space_position().y >= 135){
            left_board_space_rec.set(space_x - 94, space_y - 95, 100, 100);
            right_board_space_rec.set(space_x + 94, space_y - 95, 100, 100);
            left_move_space_rec.set(space_x - 94 + 25, space_y - 95 + 35, 100, 100);
            right_move_space_rec.set(space_x + 94 + 25, space_y - 95 + 35, 100, 100);
            possibleMoveSpaces.add(new PossibleMoveSpace(left_move_space_rec, left_board_space_rec), new PossibleMoveSpace(right_move_space_rec, right_board_space_rec));
        }
        return possibleMoveSpaces;
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

    @Override
    public boolean is_touched(Rectangle mouse_rec) {
        return mouse_rec.overlaps(this.current_position);
    }
}
