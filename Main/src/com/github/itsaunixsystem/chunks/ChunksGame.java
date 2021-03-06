package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;

public class ChunksGame extends Game {
    public SoundManger sound;
    protected GameManager renderer;

    @Override
    public void create() {
        game = this;
        sound = new SoundManger();

        ScreenMainMenu mainMenu = new ScreenMainMenu(this);
        setScreen(mainMenu);
        Gdx.input.setInputProcessor(mainMenu);
        sound.loadMusic("main", Gdx.files.internal("Main/res/main.wav"));
        sound.playMusic("main", true);
    }

    public static ChunksGame game;

    public void setScreenAndInputProcessor(GuiScreen screen) {
        setScreen(screen);
        Gdx.input.setInputProcessor(screen);
    }
}
