package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameManager {
    protected World currentWorld;
    protected OrthographicCamera camera;
    protected ChunksGame game;

    public GameManager(ChunksGame game) {
        this.camera = new OrthographicCamera();
        this.game = game;
    }
}
