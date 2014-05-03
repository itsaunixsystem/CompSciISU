package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

public class GuiButton extends GuiBase implements GuiClickable {
    protected boolean pointerHovering[];
    protected GuiClickable onClick;
    protected String text;

    public GuiButton(String text, Rectangle2D.Float outline, GuiClickable onClick) {
        super(outline);
        this.onClick = onClick;
        pointerHovering = new boolean[20]; //Max 20 fingers or game breaks
        this.text = text;
    }

    @Override
    public void render(float delta) {
        ShapeRenderer shapeRenderer = this.renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(hovering()) {
            shapeRenderer.setColor(0.6f, 0.6f, 0.6f, 0.1f);
            shapeRenderer.rect(outline.x, outline.y, (float) outline.getWidth(), (float) outline.getHeight());
            if(Gdx.input.isButtonPressed(0) ||
                    Gdx.app.getType() == Application.ApplicationType.Android ||
                    Gdx.app.getType() == Application.ApplicationType.iOS) {
                shapeRenderer.setColor(0.2f, 0.2f, 0.4f, 0.7f);
            } else {
                shapeRenderer.setColor(0.8f, 0.8f, 0.8f, 0.5f);
            }
            shapeRenderer.rect(outline.x + 2, outline.y + 2, (float) outline.getWidth() - 4, (float) outline.getHeight() - 4);
        } else {
            shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.9f);
            shapeRenderer.rect(outline.x, outline.y, (float) outline.getWidth(), (float) outline.getHeight());
            shapeRenderer.setColor(0.6f, 0.6f, 0.6f, 0.7f);
            shapeRenderer.rect(outline.x + 2, outline.y + 2, (float) outline.getWidth() - 4, (float) outline.getHeight() - 4);
        }
        shapeRenderer.end();

        SpriteBatch spriteBatch = renderer.getSpriteBatch();
        spriteBatch.begin();
        renderer.getFontRenderer().draw(spriteBatch, text,
                (float) (outline.x + outline.getWidth() / 2 - renderer.getFontRenderer().getSpaceWidth() * text.length() / 2),
                (float) (outline.y + outline.getHeight() / 2 + renderer.getFontRenderer().getCapHeight() / 2));
        spriteBatch.end();
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
