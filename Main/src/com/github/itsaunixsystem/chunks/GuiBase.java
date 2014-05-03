package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.awt.geom.Rectangle2D;

public abstract class GuiBase implements GuiElement {
    protected Rectangle2D.Float outline;
    protected GuiRenderer renderer;
    protected ElementPositionStyle posStyle;

    public GuiBase(Rectangle2D.Float outline) {
        this.outline = outline;
        renderer = new GuiRenderer();
    }

    public GuiBase(Rectangle2D.Float outline, ElementPositionStyle posStyle) {
        this(outline);
        this.posStyle = posStyle;
    }

    protected float getX() {
        return (float) (outline.x + posStyle.getXOffset(Gdx.graphics.getWidth()) - (outline.getWidth() / 2));
    }

    protected float getY() {
        return (float) (outline.y + posStyle.getYOffset(Gdx.graphics.getHeight()) - (outline.getHeight() / 2));
    }

    public boolean withinBounds(int x, int y) {
        return new Rectangle2D.Float(getX(), getY(), outline.width, outline.height).contains(x, y);
    }

    @Override
    public void render(float delta) {
        GL20 gl = Gdx.graphics.getGL20();
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        draw(delta);
        gl.glDisable(GL20.GL_BLEND);
    }

    protected abstract void draw(float delta);

    public void refreshRenderer() {
        renderer.reset();
    }

    public void dispose() {
        renderer.dispose();
    }
}
