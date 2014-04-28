package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Game;

public abstract class Screen implements com.badlogic.gdx.Screen {
    protected Game game;

    public Screen(Game game)
    {
        this.game = game;
    }
}
