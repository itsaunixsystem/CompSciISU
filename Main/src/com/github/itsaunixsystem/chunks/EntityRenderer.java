package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.itsaunixsystem.chunks.entities.EntityDrawable;

public class EntityRenderer {
    private SpriteBatch batch;

    public EntityRenderer() {
        batch = new SpriteBatch();
    }

    public void drawEntity(float delta, EntityDrawable drawable) {
        batch.begin();
        drawable.draw(delta, batch);
        batch.end();
    }

    public void drawEntities(float delta, Object[] entityDrawables) {
        batch.begin();
        for(Object entity : entityDrawables) {
            if(entity instanceof EntityDrawable)
                drawEntity(delta, (EntityDrawable) entity);
        }
        batch.end();
    }

    public void update(Camera camera) {
        batch.setProjectionMatrix(camera.combined);
    }
}
