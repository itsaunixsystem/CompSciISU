package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiTextBox extends GuiBase {
    private String text;

    public GuiTextBox(String text, Rectangle2D.Float outline) {
        this(text, outline, ElementPositionStyle.FIXED);
    }

    public GuiTextBox(String text, Rectangle2D.Float outline, PositionStyle posStyle) {
        super(outline, posStyle);
        this.text = text;
    }

    @Override
    protected void draw(float delta) {
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.9f);
        shapeRenderer.rect(getX(), getY(), (float) outline.getWidth(), (float) outline.getHeight());

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(getX() + 3, getY() + 3, (float) (outline.getWidth() - 6), (float) (outline.getHeight() - 6));
        shapeRenderer.end();

        drawText(text, getXCenter(), getYCenter());
    }

    @Override
    public void mouseDown(int screenX, int screenY, int pointer) {

    }

    @Override
    public void mouseDragged(int screenX, int screenY, int pointer) {

    }

    public void setText(String text) {
        this.text = text;
    }
}
