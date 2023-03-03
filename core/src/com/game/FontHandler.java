package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class FontHandler {
    private static final BitmapFont player_1_font = new BitmapFont();
    private static final BitmapFont player_2_font = new BitmapFont();
    private static BitmapFont current_player_font = new BitmapFont();

    public static void draw_fonts(SpriteBatch batch){
        player_1_font.setColor(Color.BLACK);
        player_2_font.setColor(Color.RED);
        player_1_font.draw(batch, "Player 1 Score: " + PlayerInfo.get_player_score(0), 20, 20);
        player_2_font.draw(batch, "Player 2 Score: " + PlayerInfo.get_player_score(0), 670, 20);

        if (PlayerInfo.get_current_player() == 0){
            current_player_font.setColor(Color.BLACK);
        }
        else{
            current_player_font.setColor(Color.RED);
        }

        current_player_font.draw(batch, "Player " + (PlayerInfo.get_current_player() + 1) + "'s Turn", 356, 20);

    }
}
