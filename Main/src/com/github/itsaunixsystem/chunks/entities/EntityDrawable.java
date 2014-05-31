package com.github.itsaunixsystem.chunks.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.itsaunixsystem.chunks.ChunksException;
import com.github.itsaunixsystem.chunks.Imp;

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
        batch.draw(currentDraw.getFrame(delta), pos.x, pos.y);
    }

    public void play(String anim) throws ChunksException {
        if((currentDraw = drawings.get(anim)) == null) {
            throw new ChunksException("Animation is null");
        }
        else {
            currentDraw.resetStateTime();
        }
    }

    public boolean isCollided(EntityDrawable other) {
        return this.currentDraw.collides(other.currentDraw);
//        return this.currentDraw.
//        TextureRegion thisRegion = this.currentDraw.getCurrentFrame();
//        TextureRegion otherRegion = other.currentDraw.getCurrentFrame();
//
//        //Thirsty for classes
//        //But really only because it makes the last line clearer, basically just a temporary rectangle
//        class TempRect {
//            public float x1, x2, y1, y2;
//            public TempRect(float x1, float x2, float y1, float y2) {
//                this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
//            }
//        }
//
//        //Named 'a' and 'b' for spareness of algorithm below
//        TempRect a = new TempRect(thisRegion.getRegionX(),
//                                  thisRegion.getRegionX() + thisRegion.getRegionWidth(),
//                                  thisRegion.getRegionY(),
//                                  thisRegion.getRegionY() + thisRegion.getRegionHeight());
//
//        TempRect b = new TempRect(otherRegion.getRegionX(),
//                                  otherRegion.getRegionX() + otherRegion.getRegionWidth(),
//                                  otherRegion.getRegionY(),
//                                  otherRegion.getRegionY() + otherRegion.getRegionHeight());
//
//        //Quick and dirty collision check
//        return (a.x1 < b.x2 && a.x2 > b.x1 && a.y1 < b.y2 && a.y2 > b.y1);
    }

    public float getWidth() {
        return currentDraw.getCurrentFrame().getRegionWidth();
    }

    public float getHeight() {
        return currentDraw.getCurrentFrame().getRegionHeight();
    }
}
