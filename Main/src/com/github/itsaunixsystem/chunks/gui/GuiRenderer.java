package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GuiRenderer {
    protected ShapeRenderer shapeRenderer;
    protected BitmapFont fontRenderer;
    protected SpriteBatch spriteBatch;

    public GuiRenderer() {
        reset();
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public BitmapFont getFontRenderer() {
        return fontRenderer;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void dispose() {
        shapeRenderer.dispose();
        fontRenderer.dispose();
        spriteBatch.dispose();
    }

    public void reset() {
        shapeRenderer = new ShapeRenderer();
        fontRenderer = new BitmapFont(Gdx.files.internal("Main/res/chenier.fnt"), Gdx.files.internal("Main/res/chenier.png"), false);
        spriteBatch = new SpriteBatch();
    }
}
