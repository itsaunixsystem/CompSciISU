package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiProgressBar extends GuiBase {
    private float concreteProgress = 0;
    private float travelDistance;
    private float startProgress;
    private float lastLoaded = 0f;

    public GuiProgressBar(Rectangle2D.Float outline) {
        super(outline);
    }

    public GuiProgressBar(Rectangle2D.Float outline, PositionStyle posStyle) {
        super(outline, posStyle);
    }

    @Override
    protected void draw(float delta) {

        travelDistance += delta * 40f;
        if(travelDistance + startProgress > concreteProgress) {
            travelDistance = concreteProgress - startProgress;
        }
        float endRect = travelDistance + startProgress;
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.8f);
        shapeRenderer.rect(getX(), getY(), outline.width, outline.height);

        shapeRenderer.setColor(0.1f, 0.7f, 0.1f, 0.9f);
        shapeRenderer.rect(getX() + 3, getY() + 3, (outline.width - 6) * (endRect / 100.0f), outline.height - 6);

        Color solid = new Color(1f, 1f, 1f, 0.4f * (endRect / 100.f));
        Color clear = new Color(0, 0, 0, 0);
        shapeRenderer.rect(getX() + 3, getY() + 3, (outline.width - 6) * (endRect / 100.0f), outline.height - 6,
                solid, clear, clear, solid);

        if(hovering()) {
            shapeRenderer.setColor(1f, 1f, 1f, 0.05f);
            shapeRenderer.rect(getX() + 3, getY() + 3, (outline.width - 6) * (endRect / 100.0f), outline.height - 6);
        }

        shapeRenderer.end();
    }

    public void updateProgress(float progress) {
        if(lastLoaded != progress) {
            this.startProgress = this.concreteProgress;
            this.concreteProgress = progress;
            lastLoaded = progress;
            travelDistance = 0;
        }
    }

    public void addProgress(float progress) {
        this.concreteProgress += progress;
    }
}
