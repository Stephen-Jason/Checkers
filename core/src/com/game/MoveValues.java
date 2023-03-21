package com.game;

public enum MoveValues {

    CAPTURE (6, 1, 2),
    MOVE (7, 0, 1);

    int upperBoundary;
    int lowerBoundary;
    int spaceDifference;

    MoveValues(int upperBoundary, int lowerBoundary, int spaceDifference){
        this.upperBoundary = upperBoundary;
        this.lowerBoundary = lowerBoundary;
        this.spaceDifference = spaceDifference;
    }
}
