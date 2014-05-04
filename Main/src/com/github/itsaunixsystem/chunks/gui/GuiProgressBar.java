package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiProgressBar extends GuiBase {
    private float progress = 0;

    public GuiProgressBar(Rectangle2D.Float outline) {
        super(outline);
    }

    public GuiProgressBar(Rectangle2D.Float outline, PositionStyle posStyle) {
        super(outline, posStyle);
    }

    @Override
    protected void draw(float delta) {
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.8f);
        shapeRenderer.rect(getX(), getY(), outline.width, outline.height);

        shapeRenderer.setColor(0.1f, 0.7f, 0.1f, 0.9f);
        shapeRenderer.rect(getX() + 3, getY() + 3, (outline.width - 6) * (progress / 100.0f), outline.height - 6);

        Color solid = new Color(1f, 1f, 1f, 0.4f * (progress / 100.f));
        Color clear = new Color(0, 0, 0, 0);
        shapeRenderer.rect(getX() + 3, getY() + 3, (outline.width - 6) * (progress / 100.0f), outline.height - 6,
                solid, clear, clear, solid);

        shapeRenderer.end();
    }

    public void updateProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public void mouseDown(int screenX, int screenY, int pointer) {

    }

    @Override
    public void mouseDragged(int screenX, int screenY, int pointer) {

    }

    @Override
    public void onHover(int pointer) {

    }

    @Override
    public void offHover(int pointer) {

    }

    public void addProgress(float progress) {
        this.progress += progress;
    }
}
