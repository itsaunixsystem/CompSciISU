package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class World {
    private GameManager gameManager;
    protected TiledMap map;
    protected TiledMapRenderer renderer;

    public World(TiledMap map, GameManager gameManager) {
        this.map = map;
        this.gameManager = gameManager;
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    public void draw(float delta) {
        renderer.setView((OrthographicCamera) gameManager.getCamera());
        renderer.render();
    }
}
