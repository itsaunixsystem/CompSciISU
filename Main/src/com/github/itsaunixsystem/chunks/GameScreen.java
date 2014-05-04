package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.GuiScreen;

public abstract class GameScreen extends GuiScreen {
    protected GameRenderer gameRenderer;

    public GameScreen(ChunksGame game) {
        super(game);
        game.setScreenAndInputProcessor(new ScreenLoading(game));
    }

    public GameScreen(ChunksGame game, GameRenderer gameRenderer) {
        super(game);
        this.gameRenderer = gameRenderer;
    }

    @Override
    public void drawScreen(float delta) {
//        gameRenderer.render(delta);
    }
}
