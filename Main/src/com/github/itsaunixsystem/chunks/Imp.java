package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Imp {
    private Animation animation;
    private Vector2 size;
    private float stateTime = 0;

    public Imp(Texture tex) {
        animation = new Animation(1f, new TextureRegion(tex));
        size = new Vector2(tex.getWidth(), tex.getHeight());
    }

    public Imp(Animation animation, float width, float height) {
        this.animation = animation;
        size = new Vector2(width, height);
    }

    public Imp(Texture texture, int width, int height) {
        TextureRegion[][] textureRegions = TextureRegion.split(texture, texture.getWidth() / width, texture.getHeight() / height);
        TextureRegion[] textureRegions1 = new TextureRegion[width * height];
        for(int i = 0; i < width * height; i++) {
            textureRegions1[i] = textureRegions[i / width % height][i % width];
        }
        animation = new Animation(0.1f, textureRegions1);
    }

    public TextureRegion getFrame(float delta) {
        stateTime += delta;
        return animation.getKeyFrame(stateTime, true);
    }

    public void resetStateTime() {
        stateTime = 0;
    }

    public float getWidth() {
        return getFrame(0).getRegionWidth();
    }


    public float getHeight() {
        return getFrame(0).getRegionHeight();
    }
}
