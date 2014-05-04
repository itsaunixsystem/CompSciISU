package com.github.itsaunixsystem.chunks;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class GameLoader {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private Runnable finished;
    private final float spread = 1f / LoadStage.values().length;
    private int index = 0;
    private boolean loadNext;

    public GameLoader(DoubleConsumer progress, Consumer<String> updateText) {
        this.progress = progress;
        this.updateText = updateText;
        loadNext = true;
    }

    public void frameUpdate(float delta) {
        if(loadNext)
            loadNextStage();
    }

    public void updateProgress(float progress) {
        this.progress.accept((index - 1) * spread * 100 + progress * spread);
    }

    public void endInit() {
        finished.run();
    }

    public void startInit(Runnable finished) {
        this.finished = finished;
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
            for(int i = 0;i < 100; i++) {
                stage.gameLoader.updateProgress(i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
            new Thread(() -> {this.consumer.accept(this); gameLoader.scheduleStageLoad();}).start();
        }
    }
}
