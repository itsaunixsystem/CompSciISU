package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public abstract class EntityDrawable extends Entity {
    protected Imp currentDraw;
    protected HashMap<String, Imp> drawings;

    public EntityDrawable(Vector2 pos, HashMap<String, Imp> drawings) {
        this(pos.x, pos.y, drawings);
    }

    public EntityDrawable(float x, float y, HashMap<String, Imp> drawings) {
        super(x, y);
        this.drawings = drawings;
        currentDraw = drawings.entrySet().iterator().next().getValue(); //Get first
    }

    public void draw(float delta, SpriteBatch batch) {
        batch.draw(currentDraw.getFrame(delta).getTexture(), pos.x, pos.y);
    }

    public void play(String anim) throws ChunksException {
        if((currentDraw = drawings.get(anim)) == null) {
            throw new ChunksException("Animation is null");
        }
        else {
            currentDraw.resetStateTime();
        }
    }
}
