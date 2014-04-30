package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    private Vector2 pos;

    public Entity(Vector2 pos)
    {
        this.pos = pos;
    }

    public Entity(float x, float y)
    {
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
}
