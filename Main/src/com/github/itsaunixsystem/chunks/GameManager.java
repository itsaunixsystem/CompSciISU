package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameManager {
    protected World currentWorld;
    protected OrthographicCamera camera;

    public GameManager() {
        this.camera = new OrthographicCamera();
    }

    void loadMap(AssetManager assetManager) {
        loadMap(assetManager, "Main/res/maps/maptest.tmx");
    }

    void loadMap(AssetManager assetManager, String path) {
        currentWorld = WorldLoader.loadMap(assetManager, path);
    }

    void loadMapData(AssetManager assetManager) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        currentWorld.load();
    }

    public void setWorld(World world) {
        currentWorld = world;
    }
}
