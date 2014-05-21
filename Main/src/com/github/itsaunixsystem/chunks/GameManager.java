package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.HashMap;

public class GameManager {
    protected World currentWorld;
    protected OrthographicCamera camera;
    protected ChunksGame game;
    private HashMap<String, Object> values;
    private HashMap<String, Class> types;

    public GameManager(ChunksGame game) {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        camera.position.set(Gdx.graphics.getWidth() / 2, camera.position.y, camera.position.z);
        this.game = game;
        values = new HashMap<>();
        types = new HashMap<>();
    }

    public void map(String name, Object value) {
        values.put(name, value);
        types.put(name, value.getClass());
    }

    public <T> T get(String name) {
        return (T) values.get(name);
    }

    public void draw(float delta) {
        camera.update();
        currentWorld.draw(delta);
    }

    public void refreshAssets() {
        currentWorld = new World(get("maptest.tmx"), this);
    }

    public Camera getCamera() {
        return camera;
    }
}
