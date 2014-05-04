package com.github.itsaunixsystem.chunks.gui;

import java.util.function.IntUnaryOperator;

@SuppressWarnings(value = "unused")
public enum ElementPositionStyle implements PositionStyle {
    FIXED(screenWidth -> 0, screenHeight -> 0),

    H_CENTER(screenWidth -> screenWidth / 2, screenHeight -> 0),
    V_CENTER(screenWidth -> 0, screenHeight -> screenHeight / 2),
    VH_CENTER(screenWidth -> screenWidth / 2, screenHeight -> screenHeight / 2),

    TOP_OFFSET(screenWidth -> 0, screenHeight -> screenHeight),
    RIGHT_OFFSET(screenWidth -> screenWidth, screenHeight -> 0),
    TOP_RIGHT_OFFSET(screenWidth -> screenWidth, screenHeight -> screenHeight);

    private IntUnaryOperator x, y;

    private ElementPositionStyle(IntUnaryOperator x, IntUnaryOperator y) {
        this.x = x;
        this.y = y;
    }

    public int getXOffset(int screenWidth) {
        return x.applyAsInt(screenWidth);
    }

    public int getYOffset(int screenHeight) {
        return y.applyAsInt(screenHeight);
    }

    public static PositionStyle combine(PositionStyle x, PositionStyle y) {
        return new PositionStyle() {
            @Override
            public int getXOffset(int screenWidth) {
                return x.getXOffset(screenWidth);
            }

            @Override
            public int getYOffset(int screenHeight) {
                return y.getYOffset(screenHeight);
            }
        };
    }

    public static PositionStyle combine(IntUnaryOperator x, PositionStyle y) {
        return combine(new PositionStyleImpl(x, null), y);
    }

    public static PositionStyle combine(PositionStyle x, IntUnaryOperator y) {
        return combine(x, new PositionStyleImpl(null, y));
    }
}