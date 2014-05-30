package com.github.itsaunixsystem.chunks.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 pos;

    public Entity(Vector2 pos)
    {
        this.pos = pos;
    }

    public Entity(float x, float y)
    {
        this(new Vector2());
        pos.set(x, y);
    }

    public void setX(float x)
    {
        pos.x = x;
    }

    public void setY(float y)
    {
        pos.y = y;
    }

    public void setPos(Vector2 pos)
    {
        this.pos = pos;
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public float getX()
    {
        return pos.x;
    }

    public float getY()
    {
        return pos.y;
    }

    public abstract void update(float delta);
}
