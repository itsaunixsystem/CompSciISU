package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.GuiScreen;

public abstract class GameScreen extends GuiScreen {
    protected GameLoader gameLoader;

    public GameScreen(ChunksGame game) {
        super(game);
        game.setScreenAndInputProcessor(new ScreenLoading(game));
    }

    public GameScreen(ChunksGame game, GameLoader gameLoader) {
        super(game);
        this.gameLoader = gameLoader;
    }

    @Override
    public void drawScreen(float delta) {
//        gameLoader.render(delta);
    }
}
