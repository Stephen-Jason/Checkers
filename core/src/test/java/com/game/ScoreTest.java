package com.game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {


    @Test
    public void getRedScore(){
        int actual = Score.getScore(Players.RED);
        assertEquals(0, actual);
    }


    @Test
    public void getBlackScore(){
        int actual = Score.getScore(Players.BLACK);
        assertEquals(0, actual);
    }


    @Test
    public void addToRedScore(){
        Score.givePoint(Players.RED);
        int actual = Score.getScore(Players.RED);
        assertEquals(1, actual);
    }


    @Test
    public void addToBlackScore(){
        Score.givePoint(Players.BLACK);
        int actual = Score.getScore(Players.BLACK);
        assertEquals(1, actual);
    }
}
