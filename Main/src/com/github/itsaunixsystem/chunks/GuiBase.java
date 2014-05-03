package com.github.itsaunixsystem.chunks;

import java.awt.geom.Rectangle2D;

public abstract class GuiBase implements GuiElement {
    protected Rectangle2D.Float outline;
    protected GuiRenderer renderer;

    public GuiBase(Rectangle2D.Float outline) {
        this.outline = outline;
        renderer = new GuiRenderer();
    }

    public boolean withinBounds(int x, int y) {
        return outline.contains(x, y);
    }

    public void refreshRenderer() {
        renderer.reset();
    }

    public void dispose() {
        renderer.dispose();
    }
}
