package com.game;

public enum Players {
    BLACK (1),
    RED (2);

    int playerNumber;

    Players(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
