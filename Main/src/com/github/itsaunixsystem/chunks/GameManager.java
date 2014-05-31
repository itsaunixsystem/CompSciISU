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
    private EntityRenderer entityRenderer;

    public GameManager(ChunksGame game) {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth(), camera.position.y, camera.position.z);
        this.game = game;
        values = new HashMap<>();
        types = new HashMap<>();
        entityRenderer = new EntityRenderer();
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
        camera.position.x -= delta * 100;
        if(camera.position.x < Gdx.graphics.getWidth() / 2) {
            camera.position.x = Gdx.graphics.getWidth() / 2;
        }
        entityRenderer.update(camera);
        currentWorld.draw(delta);
    }

    public EntityRenderer getEntityRenderer() {
        return entityRenderer;
    }

    public void refreshAssets() {
        currentWorld = MapLoader.loadMap(get("maptest.tmx"), this);
    }

    public Camera getCamera() {
        return camera;
    }

}
