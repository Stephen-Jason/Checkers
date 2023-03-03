package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Board {
    private Array<BoardSpace> boardSpaces;
    private final Texture board_img;
    private final Texture red_piece_img;
    private final Texture black_piece_img;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private BoardSpace last_touched_space;
    private long last_clicked;
    private Rectangle temp_rec;

    Board() {
        this.last_clicked = TimeUtils.nanoTime();
        this.board_img = new Texture("checker-board.jpg");
        this.batch = new SpriteBatch();
        this.red_piece_img = new Texture("red_piece.png");
        this.black_piece_img = new Texture("black_piece.png");
        this.setup_board_grid();
        this.setup_pieces();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        this.handle_touch();
        this.batch.draw(this.board_img, 0, 0);
        this.draw_pieces();
        this.batch.end();
    }

    public void dispose() {
        this.batch.dispose();
        this.board_img.dispose();
        for (BoardSpace space : this.boardSpaces) {
            space.dispose();
        }
    }

    private void setup_pieces() {

        this.boardSpaces.get(0).set_checkers_piece(new CheckersPiece(new int[]{32, 40}, 0, this.red_piece_img));
        this.boardSpaces.get(2).set_checkers_piece(new CheckersPiece(new int[]{220, 40}, 0, this.red_piece_img));
        this.boardSpaces.get(4).set_checkers_piece(new CheckersPiece(new int[]{408, 40}, 0, this.red_piece_img));
        this.boardSpaces.get(6).set_checkers_piece(new CheckersPiece(new int[]{594, 40}, 0, this.red_piece_img));

        this.boardSpaces.get(9).set_checkers_piece(new CheckersPiece(new int[]{126, 135}, 0, this.red_piece_img));
        this.boardSpaces.get(11).set_checkers_piece(new CheckersPiece(new int[]{313, 135}, 0, this.red_piece_img));
        this.boardSpaces.get(13).set_checkers_piece(new CheckersPiece(new int[]{501, 135}, 0, this.red_piece_img));
        this.boardSpaces.get(15).set_checkers_piece(new CheckersPiece(new int[]{689, 135}, 0, this.red_piece_img));

        this.boardSpaces.get(16).set_checkers_piece(new CheckersPiece(new int[]{32, 230}, 0, this.red_piece_img));
        this.boardSpaces.get(18).set_checkers_piece(new CheckersPiece(new int[]{220, 230}, 0, this.red_piece_img));
        this.boardSpaces.get(20).set_checkers_piece(new CheckersPiece(new int[]{408, 230}, 0, this.red_piece_img));
        this.boardSpaces.get(22).set_checkers_piece(new CheckersPiece(new int[]{594, 230}, 0, this.red_piece_img));


        this.boardSpaces.get(41).set_checkers_piece(new CheckersPiece(new int[]{126, 705}, 1, this.black_piece_img));
        this.boardSpaces.get(43).set_checkers_piece(new CheckersPiece(new int[]{313, 705}, 1, this.black_piece_img));
        this.boardSpaces.get(45).set_checkers_piece(new CheckersPiece(new int[]{500, 705}, 1, this.black_piece_img));
        this.boardSpaces.get(47).set_checkers_piece(new CheckersPiece(new int[]{687, 705}, 1, this.black_piece_img));

        this.boardSpaces.get(48).set_checkers_piece(new CheckersPiece(new int[]{32, 610}, 1, this.black_piece_img));
        this.boardSpaces.get(50).set_checkers_piece(new CheckersPiece(new int[]{220, 610}, 1, this.black_piece_img));
        this.boardSpaces.get(52).set_checkers_piece(new CheckersPiece(new int[]{406, 610}, 1, this.black_piece_img));
        this.boardSpaces.get(54).set_checkers_piece(new CheckersPiece(new int[]{594, 610}, 1, this.black_piece_img));

        this.boardSpaces.get(57).set_checkers_piece(new CheckersPiece(new int[]{126, 515}, 1, this.black_piece_img));
        this.boardSpaces.get(59).set_checkers_piece(new CheckersPiece(new int[]{313, 515}, 1, this.black_piece_img));
        this.boardSpaces.get(61).set_checkers_piece(new CheckersPiece(new int[]{500, 515}, 1, this.black_piece_img));
        this.boardSpaces.get(63).set_checkers_piece(new CheckersPiece(new int[]{687, 515}, 1, this.black_piece_img));


    }

    private void draw_pieces() {
        for (BoardSpace space : this.boardSpaces) {
            if (space.has_piece()) {
                this.batch.draw(space.get_piece_texture(), space.get_checkers_piece().get_current_position().x, space.get_checkers_piece().get_current_position().y, 80, 80);
            }
            if (space.has_move_space()){
                this.batch.draw(space.get_move_space_texture(), space.get_move_space_position().x, space.get_move_space_position().y, 50, 50);
            }
        }

    }


    private void add_move_spaces(Array<PossibleMoveSpace> moveSpaces){
        for (PossibleMoveSpace moveSpace : moveSpaces){
            for (BoardSpace boardSpace : this.boardSpaces){
                if (boardSpace.get_space_position().equals(moveSpace.get_board_space_position()) && !boardSpace.has_piece()){
                    boardSpace.set_move_space(moveSpace);
                }
            }
        }
    }

    private void remove_move_spaces(){
        for (BoardSpace space : this.boardSpaces){
            if (space.has_move_space()){
                space.remove_move_space();
            }
        }
    }

    private void handle_touch() {
        if (Gdx.input.isTouched()) {
            Vector3 mouse_point = new Vector3();
            mouse_point.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mouse_point);
            Rectangle mouse_rec = new Rectangle();
            mouse_rec.set(mouse_point.x, mouse_point.y, 5, 5);

            for (BoardSpace space : this.boardSpaces) {

            if (space.is_touched(mouse_rec)){
                if (space.has_piece() && this.last_touched_space == null && TimeUtils.nanoTime() - this.last_clicked > 1000000000) {
                    this.last_touched_space = space;
                    this.last_clicked = TimeUtils.nanoTime();
                    Array<PossibleMoveSpace> moveSpaces = space.get_possible_moves_from_here();
                    this.add_move_spaces(moveSpaces);
                    break;
                }

                else if (!space.has_piece() && space.has_move_space() && this.last_touched_space != null && TimeUtils.nanoTime() - this.last_clicked > 1000000000){
                    space.set_checkers_piece(this.last_touched_space.get_checkers_piece());
                    this.last_touched_space.remove_checkers_piece();
                    this.last_touched_space = null;
                    this.last_clicked = TimeUtils.nanoTime();
                    this.remove_move_spaces();
                    break;
                }
            }
            }
        }
    }

        private void setup_board_grid() {

            this.boardSpaces = new Array<>();
            this.temp_rec = new Rectangle();

            this.temp_rec.set(20, 20, 100, 100);

            for (int count = 0; count < 64; count++) {
                Rectangle rec = new Rectangle();
                if (count % 8 == 0 && count != 0) {
                    temp_rec.y += 95;
                    temp_rec.x = 20;
                } else if (count != 0) {
                    temp_rec.x += 94;
                }
                rec.set(temp_rec.x, temp_rec.y, 100, 100);
                this.boardSpaces.add(new BoardSpace(this.temp_rec));
            }
        }


    }

