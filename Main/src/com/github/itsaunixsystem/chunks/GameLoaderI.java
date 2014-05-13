package com.github.itsaunixsystem.chunks;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public abstract class GameLoaderI {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private Runnable finished;
    private ChunksGame game;
    private GameManager gameManager;

    public GameLoaderI(ChunksGame game, DoubleConsumer progress, Consumer<String> updateText) {
        this.progress = progress;
        this.updateText = updateText;
        this.game = game;
        this.gameManager = new GameManager();
    }

    public abstract void frameUpdate(float delta);

    public void updateProgress(float progress) {
        this.progress.accept(progress);
    }

    public void endInit() {
        game.setScreenAndInputProcessor(new GameScreenInGame(game, gameManager));
    }
}