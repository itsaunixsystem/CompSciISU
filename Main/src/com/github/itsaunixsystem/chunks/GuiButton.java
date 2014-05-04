package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiButton extends GuiBase implements GuiClickable {
    protected boolean pointerHovering[];
    protected GuiClickable onClick;
    protected float animatedVal;
    protected String text;
    protected ButtonStyle buttonStyle;

    public GuiButton(String text, Rectangle2D.Float outline, GuiClickable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiButton(String text, Rectangle2D.Float outline, ElementPositionStyle posStyle, GuiClickable onClick) {
        this(text, outline, posStyle, onClick, ButtonStyle.NORMAL);
    }

    public GuiButton(String text, Rectangle2D.Float outline, ElementPositionStyle posStyle, GuiClickable onClick, ButtonStyle style) {
        super(outline, posStyle);
        this.onClick = onClick;
        pointerHovering = new boolean[20]; //Max 20 fingers or game breaks
        this.text = text;
        this.animatedVal = 0f;
        this.buttonStyle = style;
    }

    @Override
    public void draw(float delta) {
        buttonStyle.draw(delta, this);
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
            float incAmt = delta * 8;

            if(guiButton.hovering()) {
                if(guiButton.animatedVal < 1) {
                    guiButton.animatedVal += incAmt;
                }
                if (Gdx.input.isButtonPressed(0) ||
                        Gdx.app.getType() == Application.ApplicationType.Android ||
                        Gdx.app.getType() == Application.ApplicationType.iOS) {
                    shapeRenderer.setColor(0.2f, 0.2f, 0.4f, 0.7f);
                }
            }
            else if(guiButton.animatedVal > 0) {
                guiButton.animatedVal -= incAmt;
            }
            shapeRenderer.rect(guiButton.getX() + 3, guiButton.getY() + 3, (float) guiButton.outline.getWidth() - 6, (float) guiButton.outline.getHeight() - 6);
            shapeRenderer.end();

            guiButton.drawText(guiButton.text,
                    (float) (guiButton.getX() + guiButton.outline.getWidth() / 2 - guiButton.renderer.getFontRenderer().getSpaceWidth() * guiButton.text.length() / 2),
                    (float) (guiButton.getY() + guiButton.outline.getHeight() / 2 + guiButton.renderer.getFontRenderer().getCapHeight() / 2));
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
