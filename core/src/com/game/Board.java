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
    private Array<Rectangle> board_spaces;
    private Array<BoardSpace> boardSpaces;
    private Array<CheckersPiece> checkers_pieces;
    private final Texture board_img;
    private final Texture red_piece_img;
    private final Texture black_piece_img;
    private final Texture board_space_test_img;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private CheckersPiece last_touched_piece;
    private Rectangle new_position_space;
    private long last_clicked;
    private Rectangle temp_rec;

    Board(){
        this.last_clicked = TimeUtils.nanoTime();
        this.board_img = new Texture("checker-board.jpg");
        this.batch = new SpriteBatch();
        this.red_piece_img = new Texture("red_piece.png");
        this.black_piece_img = new Texture("black_piece.png");
        this.setup_board_grid();
        this.setup_pieces();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        this.board_space_test_img = new Texture("board_space.jpg");


    }

    public void render(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        this.handle_touch();
        this.batch.draw(this.board_img, 0, 0);
        this.draw_pieces();
        this.batch.end();
    }

    public void dispose(){
        this.batch.dispose();
        this.board_img.dispose();
        for (CheckersPiece piece : checkers_pieces){
            piece.dispose();
        }
    }

    private void setup_pieces(){

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


        this.boardSpaces.get(41).set_checkers_piece(new CheckersPiece(new int[]{126, 705}, 0, this.black_piece_img));
        this.boardSpaces.get(43).set_checkers_piece(new CheckersPiece(new int[]{313, 705}, 0, this.black_piece_img));
        this.boardSpaces.get(45).set_checkers_piece(new CheckersPiece(new int[]{500, 705}, 0, this.black_piece_img));
        this.boardSpaces.get(47).set_checkers_piece(new CheckersPiece(new int[]{687, 705}, 0, this.black_piece_img));

        this.boardSpaces.get(48).set_checkers_piece(new CheckersPiece(new int[]{32, 610}, 0, this.black_piece_img));
        this.boardSpaces.get(50).set_checkers_piece(new CheckersPiece(new int[]{220, 610}, 0, this.black_piece_img));
        this.boardSpaces.get(52).set_checkers_piece(new CheckersPiece(new int[]{406, 610}, 0, this.black_piece_img));
        this.boardSpaces.get(54).set_checkers_piece(new CheckersPiece(new int[]{594, 610}, 0, this.black_piece_img));

        this.boardSpaces.get(57).set_checkers_piece(new CheckersPiece(new int[]{126, 515}, 0, this.black_piece_img));
        this.boardSpaces.get(59).set_checkers_piece(new CheckersPiece(new int[]{313, 515}, 0, this.black_piece_img));
        this.boardSpaces.get(61).set_checkers_piece(new CheckersPiece(new int[]{500, 515}, 0, this.black_piece_img));
        this.boardSpaces.get(63).set_checkers_piece(new CheckersPiece(new int[]{687, 515}, 0, this.black_piece_img));


    }

    private void draw_pieces(){
        for(BoardSpace space : this.boardSpaces){
            if(space.has_piece()){
                this.batch.draw(space.get_piece_texture(), space.get_checkers_piece().get_current_position().x, space.get_checkers_piece().get_current_position().y, 80, 80);
            }
        }

    }

    private boolean handle_touch(){
        if(Gdx.input.isTouched()){
            Vector3 mouse_point = new Vector3();
            mouse_point.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mouse_point);
            Rectangle mouse_rec = new Rectangle();
            mouse_rec.set(mouse_point.x, mouse_point.y, 5, 5);

//            check if a game piece was touched


//            for (CheckersPiece piece : checkers_pieces){
//                if (piece.is_touched(mouse_rec) && last_touched_piece == null && TimeUtils.nanoTime() - this.last_clicked > 300000000){
//                    last_touched_piece = piece;
//                    this.last_clicked = TimeUtils.nanoTime();
//                    return true;
//                }
//            }
//
////            check if an empty space was touched
//            if(last_touched_piece != null && TimeUtils.nanoTime() - this.last_clicked > 300000000){
//                for (Rectangle space : this.board_spaces){
//                    if (space.overlaps(mouse_rec)){
//                        this.last_touched_piece.set_current_position(new int[]{(int)space.x + 10, (int)space.y + 20});
//                        this.last_touched_piece = null;
//                        break;
//                    }
//                }
//                this.last_clicked = TimeUtils.nanoTime();
//            }

        }
        return false;
    }

    private void setup_board_grid(){
        this.boardSpaces = new Array<>();
        this.temp_rec = new Rectangle();

        this.temp_rec.set(20, 20, 100, 100);

        for (int count = 0; count < 64; count++){
            Rectangle rec = new Rectangle();
            if (count % 8 == 0 && count != 0){
                temp_rec.y += 95;
                temp_rec.x = 20;
            }
            else if (count != 0){
                temp_rec.x += 94;
            }
            rec.set(temp_rec.x, temp_rec.y, 100, 100);
            this.boardSpaces.add(new BoardSpace(this.temp_rec));
        }
    }

    public Texture get_img(){
        return this.board_img;
    }
}
