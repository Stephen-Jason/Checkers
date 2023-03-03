package com.game;

public abstract class PlayerInfo {

    private static int current_player;
    private static final int[] players_score = new int[]{0, 0};

    public static void set_current_player(int player_number){
        current_player = player_number;
    }

    public static int get_current_player(){
        return current_player;
    }

    public static void set_player_score(int player_number, int score){
        players_score[player_number] += score;
    }

    public static int get_player_score(int player_number){
        return players_score[player_number];
    }
}
