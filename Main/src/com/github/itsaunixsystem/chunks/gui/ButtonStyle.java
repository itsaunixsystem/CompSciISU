package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

@SuppressWarnings(value = "unused")
public enum ButtonStyle {
    NORMAL((float delta, GuiTextButton guiTextButton) -> {
        ShapeRenderer shapeRenderer = guiTextButton.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(guiTextButton.getX(), guiTextButton.getY(), (float) guiTextButton.outline.getWidth(), (float) guiTextButton.outline.getHeight());

        float animatedMod = guiTextButton.animatedVal * 0.15f;
        shapeRenderer.setColor(0.6f + animatedMod, 0.6f + animatedMod, 0.6f + animatedMod, 0.7f);

        if(guiTextButton.isClicked())
            shapeRenderer.setColor(0.2f, 0.2f, 0.4f, 0.7f);

        shapeRenderer.rect(guiTextButton.getX() + 3, guiTextButton.getY() + 3, (float) guiTextButton.outline.getWidth() - 6, (float) guiTextButton.outline.getHeight() - 6);
        shapeRenderer.end();
    }),
    GRADIENT((float delta, GuiTextButton guiTextButton) -> {
        ShapeRenderer shapeRenderer = guiTextButton.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(guiTextButton.getX(), guiTextButton.getY(), (float) guiTextButton.outline.getWidth(), (float) guiTextButton.outline.getHeight());

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.9f);
        shapeRenderer.rect(guiTextButton.getX(), guiTextButton.getY(), 3, (float) guiTextButton.outline.getHeight());
        shapeRenderer.rect(guiTextButton.getX(), guiTextButton.getY(), (float) guiTextButton.outline.getWidth(), 3);
        shapeRenderer.rect((float) (guiTextButton.getX() + guiTextButton.outline.getWidth() - 3), guiTextButton.getY(), 3, (float) guiTextButton.outline.getHeight());
        shapeRenderer.rect(guiTextButton.getX(), (float) (guiTextButton.getY() + guiTextButton.outline.getHeight() - 3), (float) guiTextButton.outline.getWidth(), 3);

        Color solid = guiTextButton.isClicked() ? new Color(0.4f, 0.2f, 0.8f, 0.5f * guiTextButton.animatedVal)
                : new Color(1.0f, 1.0f, 1.0f, 0.5f * guiTextButton.animatedVal);
        Color clear = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        shapeRenderer.rect(guiTextButton.getX() + 3, guiTextButton.getY() + 3, (float) guiTextButton.outline.getWidth() - 6, (float) guiTextButton.outline.getHeight() - 6,
                solid,
                clear,
                clear,
                solid);
        shapeRenderer.end();
    });

    private Drawable consumer;
    private ButtonStyle(Drawable consumer) {
        this.consumer = consumer;
    }

    public void draw(float delta, GuiTextButton guiTextButton) {
        consumer.accept(delta, guiTextButton);
    }

    private interface Drawable {
        public void accept(float delta, GuiTextButton guiTextButton);
    }
}