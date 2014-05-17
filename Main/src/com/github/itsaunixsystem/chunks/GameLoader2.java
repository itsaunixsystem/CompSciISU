package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class GameLoader2 {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private GameManager gameManager;
    private AssetManager assetManager;
    private ChunksGame game;

    public GameLoader2(ChunksGame game, DoubleConsumer progress, Consumer<String> updateText) {
        this.game = game;
        this.progress = progress;
        this.updateText = updateText;
        gameManager = new GameManager(game);
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("Main/res/maps/maptest.tmx", TiledMap.class);
    }

    public void frameUpdate(float delta) {
        assetManager.update();
        float prog = assetManager.getProgress();
        progress.accept(assetManager.getProgress() * 100);
        if(prog == 1) {
            endInit();
        }
    }

    public void endInit() {
        game.setScreenAndInputProcessor(new ScreenMainMenu(game));
    }
}
