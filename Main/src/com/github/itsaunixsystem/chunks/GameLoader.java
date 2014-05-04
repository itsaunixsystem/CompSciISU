package com.github.itsaunixsystem.chunks;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class GameLoader {
    private DoubleConsumer progress;
    private Consumer<String> updateText;
    private Runnable finished;
    private final float spread = 1f / LoadStage.values().length;
    private int index = 0;

    public GameLoader(DoubleConsumer progress, Consumer<String> updateText) {
        this.progress = progress;
        this.updateText = updateText;
        loadNextStage();
    }

    public void updateProgress(float progress) {
        this.progress.accept(progress * spread);
    }

    public void endInit() {
        finished.run();
    }

    public void startInit(Runnable finished) {
        this.finished = finished;
    }

    private void loadNextStage() {
        System.out.println("Loading stage " + index);
        if(index >= LoadStage.values().length) {
            endInit();
            return;
        }
        LoadStage.values()[index++].start(this);
    }

    @SuppressWarnings(value = "unused")
    private enum LoadStage {
        WORLD("Loading world...", (stage) -> {
            System.out.println("Running...");
            for(int i = 0;i < 100; i++) {
                stage.gameRenderer.updateProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }),
        ENTITIES("Loading entities...", (stage) -> {}),
        SPAWNING("Spawning enemies...", (stage) -> {});

        private Consumer<LoadStage> consumer;
        private String text;
        private GameLoader gameRenderer;

        private LoadStage(String text, Consumer<LoadStage> consumer) {
            this.consumer = consumer;
            this.text = text;
        }

        public void start(GameLoader gameLoader) {
            this.gameRenderer = gameLoader;
            gameLoader.updateText.accept(text);
            new Thread(() -> this.consumer.accept(this)).start();
        }
    }
}
