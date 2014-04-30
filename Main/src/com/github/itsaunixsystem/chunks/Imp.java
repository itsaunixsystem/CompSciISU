package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Imp {
    private Animation animation;
    private Vector2 size;
    private float stateTime = 0;

    public Imp(Texture tex)
    {
        animation = new Animation(1f, new TextureRegion(tex));
        size = new Vector2(tex.getWidth(), tex.getHeight());
    }

    public Imp(Animation animation, float width, float height)
    {
        this.animation = animation;
        size = new Vector2(width, height);
    }

    public TextureRegion getFrame(float delta)
    {
        stateTime += delta;
        return animation.getKeyFrame(stateTime, true);
    }
}
