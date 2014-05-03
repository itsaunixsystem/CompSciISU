package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public abstract class GuiBase implements GuiElement {
    protected Rectangle2D.Float outline;
    protected ShapeRenderer renderer;

    public GuiBase(Rectangle2D.Float outline) {
        this.outline = outline;
        refreshShapeRenderer();
    }

    public void refreshShapeRenderer()
    {
        renderer = new ShapeRenderer();
    }

    public boolean withinBounds(int x, int y) {
        return outline.contains(x, y);
    }
}
