package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class World {
    private GameManager gameManager;
    protected TiledMap map;

    public World() {

    }

    public World(TiledMap map) {
        this.map = map;
    }
}
