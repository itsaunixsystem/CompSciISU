package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.GuiScreen;

public abstract class GameScreen extends GuiScreen {
    protected GameManager gameManager;

    public GameScreen(ChunksGame game, GameManager gameManager) {
        super(game);
        this.gameManager = gameManager;
    }
}
