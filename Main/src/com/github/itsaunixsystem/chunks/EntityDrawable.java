package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class EntityDrawable extends Entity {
    protected Imp imp;

    public EntityDrawable(Vector2 pos, Imp imp)
    {
        super(pos);
        this.imp = imp;
    }

    public EntityDrawable(float x, float y, Imp imp) {
        super(x, y);
        this.imp = imp;
    }

    public void draw(float delta, SpriteBatch batch)
    {
        batch.draw(imp.getFrame(delta).getTexture(), pos.x, pos.y);
    }
}
