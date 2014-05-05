package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

@SuppressWarnings(value = "unused")
public enum ButtonStyle {
    NORMAL((float delta, GuiButton guiButton) -> {
        ShapeRenderer shapeRenderer = guiButton.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(guiButton.getX(), guiButton.getY(), (float) guiButton.outline.getWidth(), (float) guiButton.outline.getHeight());

        float animatedMod = guiButton.animatedVal * 0.15f;
        shapeRenderer.setColor(0.6f + animatedMod, 0.6f + animatedMod, 0.6f + animatedMod, 0.7f);

        if(guiButton.isClicked())
            shapeRenderer.setColor(0.2f, 0.2f, 0.4f, 0.7f);

        shapeRenderer.rect(guiButton.getX() + 3, guiButton.getY() + 3, (float) guiButton.outline.getWidth() - 6, (float) guiButton.outline.getHeight() - 6);
        shapeRenderer.end();

        guiButton.drawText(guiButton.text, guiButton.getXCenter(), guiButton.getYCenter());
    }),
    GRADIENT((float delta, GuiButton guiButton) -> {
        ShapeRenderer shapeRenderer = guiButton.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(guiButton.getX(), guiButton.getY(), (float) guiButton.outline.getWidth(), (float) guiButton.outline.getHeight());

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.9f);
        shapeRenderer.rect(guiButton.getX(), guiButton.getY(), 3, (float) guiButton.outline.getHeight());
        shapeRenderer.rect(guiButton.getX(), guiButton.getY(), (float) guiButton.outline.getWidth(), 3);
        shapeRenderer.rect((float) (guiButton.getX() + guiButton.outline.getWidth() - 3), guiButton.getY(), 3, (float) guiButton.outline.getHeight());
        shapeRenderer.rect(guiButton.getX(), (float) (guiButton.getY() + guiButton.outline.getHeight() - 3), (float) guiButton.outline.getWidth(), 3);

        Color solid = guiButton.isClicked() ? new Color(0.4f, 0.2f, 0.8f, 0.5f * guiButton.animatedVal)
                : new Color(1.0f, 1.0f, 1.0f, 0.5f * guiButton.animatedVal);
        Color clear = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        shapeRenderer.rect(guiButton.getX() + 3, guiButton.getY() + 3, (float) guiButton.outline.getWidth() - 6, (float) guiButton.outline.getHeight() - 6,
                solid,
                clear,
                clear,
                solid);
        shapeRenderer.end();
        guiButton.drawText(guiButton.text, guiButton.getXCenter(), guiButton.getYCenter());
    });

    private Drawable consumer;
    private ButtonStyle(Drawable consumer) {
        this.consumer = consumer;
    }

    public void draw(float delta, GuiButton guiButton) {
        consumer.accept(delta, guiButton);
    }

    private interface Drawable {
        public void accept(float delta, GuiButton guiButton);
    }
}