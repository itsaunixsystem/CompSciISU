package com.github.itsaunixsystem.chunks;

import java.util.function.DoubleConsumer;

public class GameRenderer {
    private DoubleConsumer progress;

    public GameRenderer(DoubleConsumer progress) {
        this.progress = progress;
    }

    public void render(float delta) {
        progress.accept(delta * 10);
    }
}
