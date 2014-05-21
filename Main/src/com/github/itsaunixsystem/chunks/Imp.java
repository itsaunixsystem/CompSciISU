package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Imp {
    private Animation animation;
    private float stateTime = 0;
    private AxisAlignedBoundingBox boundingBox;

    public Imp(Texture tex) {
        animation = new Animation(1f, new TextureRegion(tex));
        boundingBox = new AxisAlignedBoundingBox(tex.getWidth(), tex.getHeight());
    }

    public Imp(Animation animation, float width, float height) {
        this.animation = animation;
        boundingBox = new AxisAlignedBoundingBox(animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());
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

    public TextureRegion getCurrentFrame() {
        return getFrame(0); //0 adds no state time so gets image as is
    }

    public void resetStateTime() {
        stateTime = 0;
    }

    public float getWidth() {
        return getCurrentFrame().getRegionWidth();
    }

    public boolean collides(Imp other) {
        return this.boundingBox.intersects(other.boundingBox);
    }

    public float getHeight() {
        return getCurrentFrame().getRegionHeight();
    }
}
