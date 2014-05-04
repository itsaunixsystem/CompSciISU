package com.github.itsaunixsystem.chunks.gui;

import java.util.function.IntUnaryOperator;

public class PositionStyleImpl implements PositionStyle {
    private IntUnaryOperator x, y;

    public PositionStyleImpl(IntUnaryOperator x, IntUnaryOperator y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getXOffset(int screenWidth) {
        return x.applyAsInt(screenWidth);
    }

    @Override
    public int getYOffset(int screenHeight) {
        return y.applyAsInt(screenHeight);
    }
}
