package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Board {
    private Array<Rectangle> board_spaces;
    private Array<CheckersPiece> checkers_pieces;
    private final Texture board_img;
    private final Texture red_piece_img;
    private final Texture black_piece_img;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private CheckersPiece last_touched_piece;
    private Rectangle temp_rec;

    Board(){
        this.board_img = new Texture("checker-board.jpg");
        this.batch = new SpriteBatch();
        this.red_piece_img = new Texture("red_piece.png");
        this.black_piece_img = new Texture("black_piece.png");
        this.checkers_pieces = setup_pieces();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

    }

    public void render(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
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

    private Array<CheckersPiece> setup_pieces(){
        Array<CheckersPiece> pieces = new Array<>();
        Array<int[]> red_pieces_coords = new Array<int[]>();
        Array<int[]> black_pieces_coords = new Array<int[]>();

        red_pieces_coords.add(new int[]{32, 40}, new int[]{220, 40}, new int[]{408, 40}, new int[]{594, 40});
        red_pieces_coords.add(new int[]{126, 135}, new int[]{313, 135}, new int[]{501, 135}, new int[]{689, 135});
        red_pieces_coords.add(new int[]{32, 230}, new int[]{220, 230}, new int[]{408, 230}, new int[]{594, 230});

        black_pieces_coords.add(new int[]{126, 705}, new int[]{313, 705}, new int[]{500, 705}, new int[]{687, 705});
        black_pieces_coords.add(new int[]{32, 610}, new int[]{220, 610}, new int[]{406, 610}, new int[]{594, 610});
        black_pieces_coords.add(new int[]{126, 515}, new int[]{313, 515}, new int[]{500, 515}, new int[]{687, 515});

//        add the red pieces
        for (int[] piece_coord : red_pieces_coords){
            pieces.add(new CheckersPiece(piece_coord, 0, this.red_piece_img));
        }

//        add the black pieces
        for (int[] piece_coord : black_pieces_coords){
            pieces.add(new CheckersPiece(piece_coord, 1, this.black_piece_img));
        }

        return pieces;
    }

    private void draw_pieces(){
        for(CheckersPiece piece : this.checkers_pieces){
            float x = piece.get_current_position().x;
            float y = piece.get_current_position().y;
            this.batch.draw(piece.get_texture(), x, y, 80, 80);
        }

    }

    private boolean piece_was_touched(){
        if(Gdx.input.isTouched()){
            Vector3 mouse_point = new Vector3();
            mouse_point.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Rectangle mouse_rec = new Rectangle();
            mouse_rec.set(mouse_point.x, mouse_point.y, 5, 5);

            for (CheckersPiece piece : checkers_pieces){
                if (piece.is_touched(mouse_rec)){
                    last_touched_piece = piece;
                    return true;
                }
            }
        }
        return false;
    }

    private void setup_board_grid(){
        this.board_spaces = new Array<>();
        this.temp_rec = new Rectangle();
        this.temp_rec.set(20, 20, 100, 100);
        for (int count = 0; count < 64; count++){
            if (count == 0 || count % 8 == 0){
                temp_rec.y += 95;
                temp_rec.x = 20;
            }
            else{
                temp_rec.x += 188;
            }
        }
    }

    public Texture get_img(){
        return this.board_img;
    }
}
