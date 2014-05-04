package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

public abstract class GuiBase implements GuiElement, FontDrawer {
    protected Rectangle2D.Float outline;
    protected GuiRenderer renderer;
    protected PositionStyle posStyle;
    protected Vector<Boolean> pointerHovering;


    public GuiBase(Rectangle2D.Float outline) {
        this.outline = outline;
        renderer = new GuiRenderer();
        pointerHovering = new Vector<>(0, 2);
    }

    public GuiBase(Rectangle2D.Float outline, PositionStyle posStyle) {
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

    @Override
    public void drawText(String text, float x, float y) {
        BitmapFont fontRenderer = renderer.getFontRenderer();
        BitmapFont.TextBounds bounds = fontRenderer.getBounds(text);
        x -= bounds.width / 2;
        y += bounds.height / 2;

        SpriteBatch spriteBatch = renderer.getSpriteBatch();
        spriteBatch.begin();
        fontRenderer.draw(spriteBatch, text, x, y);
        spriteBatch.end();
    }

    protected abstract void draw(float delta);

    public void refreshRenderer() {
        renderer.reset();
    }

    public void dispose() {
        renderer.dispose();
    }

    public float getXCenter() {
        return (float) (getX() + outline.getWidth() / 2);
    }

    public float getYCenter() {
        return (float) (getY() + outline.getHeight() / 2);
    }

    @Override
    public void onHover(int pointer) {
        setHoverState(pointer, true);
    }

    @Override
    public void offHover(int pointer) {
        setHoverState(pointer, false);
    }

    private void setHoverState(int pointer, boolean flag) {
        pointerHovering.ensureCapacity(pointer + 1);
        if(pointerHovering.size() < pointer + 1) {
            pointerHovering.add(flag);
        } else {
            pointerHovering.set(pointer, flag);
        }
    }

    protected boolean hovering() {
        for(boolean b : pointerHovering) if(b) return true;
        return false;
    }
}
