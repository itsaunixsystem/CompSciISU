package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ChunksGame extends Game {
    @Override
    public void create() {
        game = this;

        ScreenMainMenu mainMenu = new ScreenMainMenu(this);
        setScreen(mainMenu);
        Gdx.input.setInputProcessor(mainMenu);
    }

    public static ChunksGame game;
}
