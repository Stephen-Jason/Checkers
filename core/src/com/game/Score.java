package com.game;

public abstract class Score {
    private static int redScore = 0;
    private static int blackScore = 0;

    public static int getScore(Players player){
        return player == Players.RED ? redScore : blackScore;
    }


    public static void givePoint(Players player){
        if(player == Players.RED){
            redScore++;
        }
        else{
            blackScore++;
        }
    }



}
