package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.assets.AssetManager;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class GameLoader2 extends GameLoaderI {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private GameManager gameManager;
    private AssetManager assetManager;
    private ChunksGame game;

    public GameLoader2(ChunksGame game, DoubleConsumer progress, Consumer<String> updateText) {
        super(game, progress, updateText);
        this.progress = progress;
        this.updateText = updateText;
        gameManager = new GameManager();
        assetManager = new AssetManager();
        this.game = game;
    }

    @Override
    public void frameUpdate(float delta) {

    }

    @Override
    public void updateProgress(float progress) {

    }

    @Override
    public void endInit() {

    }
}
