package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiButton extends GuiBase implements GuiClickable {
    protected boolean pointerHovering[];
    protected ClickAction onClick;

    public GuiButton(Rectangle2D.Float outline, ClickAction onClick) {
        super(outline);
        this.onClick = onClick;
        pointerHovering = new boolean[20]; //Max 20 fingers or game breaks
    }

    @Override
    public void render(float delta) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        if(hovering()) {
            renderer.setColor(0.6f, 0.6f, 0.6f, 0.1f);
            renderer.rect(outline.x, outline.y, (float) outline.getWidth(), (float) outline.getHeight());
            renderer.setColor(0.8f, 0.8f, 0.8f, 0.5f);
            renderer.rect(outline.x + 2, outline.y + 2, (float) outline.getWidth() - 4, (float) outline.getHeight() - 4);
        } else {
            renderer.setColor(0.5f, 0.5f, 0.5f, 0.9f);
            renderer.rect(outline.x, outline.y, (float) outline.getWidth(), (float) outline.getHeight());
            renderer.setColor(0.6f, 0.6f, 0.6f, 0.7f);
            renderer.rect(outline.x + 2, outline.y + 2, (float) outline.getWidth() - 4, (float) outline.getHeight() - 4);
        }
        renderer.end();
    }

    @Override
    public void mouseDown(int screenX, int screenY, int pointer) {
        mouseDragged(screenX, screenY, pointer);
    }

    @Override
    public void mouseDragged(int screenX, int screenY, int pointer) {

    }

    @Override
    public void onHover(int pointer) {
        pointerHovering[pointer] = true;
    }

    @Override
    public void offHover(int pointer) {
        pointerHovering[pointer] = false;
    }

    public boolean hovering() {
        for(boolean b : pointerHovering) if(b) return true;
        return false;
    }

    @Override
    public void click() {
        onClick.click();
    }
}
