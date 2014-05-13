package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class GameLoader extends GameLoaderI {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private Runnable finished;
    private final float spread = 1f / LoadStage.values().length;
    private int index = 0;
    private boolean loadNext;
    private GameManager gameManager;
    private AssetManager assetManager;
    private ChunksGame game;

    public GameLoader(ChunksGame game, DoubleConsumer progress, Consumer<String> updateText) {
        super(game, progress, updateText);
        loadNext = true;
        assetManager = new AssetManager();
    }

    public void frameUpdate(float delta) {
        if(loadNext)
            loadNextStage();
    }

    public void updateProgress(float progress) {
        this.progress.accept((index - 1) * spread * 100 + progress * spread);
    }

    public void endInit() {
        game.setScreenAndInputProcessor(new GameScreenInGame(game, gameManager));
    }

    private void loadNextStage() {
        loadNext = false;
        System.out.println("Loading stage " + index);
        if(index >= LoadStage.values().length) {
            endInit();
            return;
        }
        LoadStage.values()[index++].start(this);
    }

    protected void scheduleStageLoad() {
        this.loadNext = true;
    }

    @SuppressWarnings(value = "unused")
    private enum LoadStage {
        WORLD("Loading world...", (stage) -> {
            GameManager gameManager = stage.gameLoader.gameManager;
            AssetManager assetManager = stage.gameLoader.assetManager;
            assetManager.load("Main/res/maps/maptest.tmx", TiledMap.class);

//            assetManager.update()
            gameManager.loadMap(assetManager);
            stage.updateProgress(10);

            gameManager.loadMapData(stage.gameLoader.assetManager);
            stage.updateProgress(50);
        }),
        ENTITIES("Loading entities...", (stage) -> {
            for(int i = 0;i < 100; i++) {
                stage.gameLoader.updateProgress(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }),
        SPAWNING("Spawning enemies...", (stage) -> {
            for(int i = 0;i < 100; i++) {
                stage.gameLoader.updateProgress(i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        private Consumer<LoadStage> consumer;
        private String text;
        private GameLoader gameLoader;

        private LoadStage(String text, Consumer<LoadStage> consumer) {
            this.consumer = consumer;
            this.text = text;
        }

        public void start(GameLoader gameLoader) {
            this.gameLoader = gameLoader;
            gameLoader.updateText.accept(text);
            this.consumer.accept(this);
            gameLoader.scheduleStageLoad();
        }

        private void updateProgress(float prog) {
            gameLoader.updateProgress(prog);
        }
    }
}
