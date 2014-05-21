package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.itsaunixsystem.chunks.entities.EntityLiving;

import java.util.ArrayList;

public class World {
    private GameManager gameManager;
    protected TiledMap map;
    protected TiledMapRenderer renderer;
    protected ArrayList<AxisAlignedPolygon> solids;

    public World(TiledMap map, GameManager gameManager) {
        this.map = map;
        this.gameManager = gameManager;
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    public void draw(float delta) {
        renderer.setView((OrthographicCamera) gameManager.getCamera());
        renderer.render();
    }

    public void applyGravityAndCollision(EntityLiving entityLiving, float delta) {
        Vector2 velocity = entityLiving.getVelocity();
        Vector2 pos = entityLiving.getPos();

        float predX = pos.x + (velocity.x * delta);
        float predY = pos.y + (velocity.y * delta);

        for(AxisAlignedPolygon axisAlignedPolygon : solids) {
            AxisAlignedBoundingBox proposed = new AxisAlignedBoundingBox(
                                                    predX,
                                                    predY,
                                                    entityLiving.getWidth(),
                                                    entityLiving.getHeight());
            if(proposed.intersects(axisAlignedPolygon)) {
                velocity.y = 0;

            } else {
                pos.x = predX;
                pos.y = predY;
            }
        }
    }
}
