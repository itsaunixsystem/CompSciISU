package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GuiButton extends GuiBase implements GuiClickable {
    protected Vector<Boolean> pointerHovering;
    protected GuiClickable onClick;
    protected float animatedVal;
    protected String text;
    protected ButtonStyle buttonStyle;

    public GuiButton(String text, Rectangle2D.Float outline, GuiClickable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, GuiClickable onClick) {
        this(text, outline, posStyle, onClick, ButtonStyle.NORMAL);
    }

    public GuiButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, GuiClickable onClick, ButtonStyle style) {
        super(outline, posStyle);
        this.onClick = onClick;
        pointerHovering = new Vector<>(0, 2);
        this.text = text;
        this.animatedVal = 0f;
        this.buttonStyle = style;
    }

    @Override
    public void draw(float delta) {
        buttonStyle.draw(delta, this);
        float animatedInc = delta * 8;
        if(hovering()) {
            if(animatedVal < 1) {
                animatedVal += animatedInc;
            }
        } else if(animatedVal > 0) {
            animatedVal -= animatedInc;
        }
    }

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

            Color solid = guiButton.isClicked() ? new Color(0.4f, 0.4f, 1.0f, 0.5f * guiButton.animatedVal)
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

    public boolean isClicked() {
        return hovering() && (Gdx.input.isButtonPressed(0) ||
                Gdx.app.getType() == Application.ApplicationType.Android ||
                Gdx.app.getType() == Application.ApplicationType.iOS);
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

    public boolean hovering() {
        for(boolean b : pointerHovering) if(b) return true;
        return false;
    }

    @Override
    public void click() {
        onClick.click();
    }
}
