package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;

public class ChunksGame extends Game {
    @Override
    public void create() {
        game = this;

        ScreenMainMenu mainMenu = new ScreenMainMenu(this);
        setScreen(mainMenu);
        Gdx.input.setInputProcessor(mainMenu);
    }

    public static ChunksGame game;

    public void setScreenAndInputProcessor(GuiScreen screen) {
        setScreen(screen);
        Gdx.input.setInputProcessor(screen);
    }
}
