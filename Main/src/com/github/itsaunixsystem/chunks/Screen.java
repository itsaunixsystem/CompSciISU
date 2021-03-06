package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;

public abstract class Screen implements com.badlogic.gdx.Screen {
    protected ChunksGame game;

    public Screen(ChunksGame game)
    {
        Gdx.graphics.getGL20().glClearColor(0f, 0f, 0f, 1f);
        this.game = game;
    }

    protected void showScreen(Screen screen)
    {
        game.setScreen(screen);
    }
}
