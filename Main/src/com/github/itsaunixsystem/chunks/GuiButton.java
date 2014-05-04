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

    public GuiButton(String text, Rectangle2D.Float outline, GuiClickable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiButton(String text, Rectangle2D.Float outline, ElementPositionStyle posStyle, GuiClickable onClick) {
        super(outline, posStyle);
        this.onClick = onClick;
        pointerHovering = new boolean[20]; //Max 20 fingers or game breaks
        this.text = text;
        this.animatedVal = 0f;
    }

    @Override
    public void draw(float delta) {
        ShapeRenderer shapeRenderer = this.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(getX(), getY(), (float) outline.getWidth(), (float) outline.getHeight());

        float animatedMod = animatedVal * 0.15f;
        shapeRenderer.setColor(0.6f + animatedMod, 0.6f + animatedMod, 0.6f + animatedMod, 0.7f);
        float incAmt = delta * 4;

        if(hovering()) {
            if(animatedVal < 1) {
                animatedVal += incAmt;
            }
            if (Gdx.input.isButtonPressed(0) ||
                    Gdx.app.getType() == Application.ApplicationType.Android ||
                    Gdx.app.getType() == Application.ApplicationType.iOS) {
                shapeRenderer.setColor(0.2f, 0.2f, 0.4f, 0.7f);
            }
        }
        else if(animatedVal > 0) {
            animatedVal -= incAmt;
        }
        shapeRenderer.rect(getX() + 3, getY() + 3, (float) outline.getWidth() - 6, (float) outline.getHeight() - 6);
        shapeRenderer.end();

        drawText(text,
                (float) (getX() + outline.getWidth() / 2 - renderer.getFontRenderer().getSpaceWidth() * text.length() / 2),
                (float) (getY() + outline.getHeight() / 2 + renderer.getFontRenderer().getCapHeight() / 2));
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
