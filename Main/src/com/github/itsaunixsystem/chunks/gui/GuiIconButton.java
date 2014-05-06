package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.geom.Rectangle2D;

public class GuiIconButton extends GuiTextButton {
    private Texture icon;

    public GuiIconButton(Texture icon, Rectangle2D.Float outline, Runnable onClick) {
        this(icon, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiIconButton(Texture icon, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick) {
        super("", outline, posStyle, onClick);

        this.icon = icon;
    }

    @Override
    public void draw(float delta) {
        SpriteBatch batch = renderer.getSpriteBatch();

        batch.begin();
        if(isClicked()) {
            batch.draw(icon, getX(), getY() - 2);
        } else {
            batch.draw(icon, this.getX(), this.getY());
        }
        batch.end();
    }

    protected float getX() {
        return (float) (outline.x + posStyle.getXOffset(Gdx.graphics.getWidth()));
    }

    protected float getY() {
        return (float) (outline.y + posStyle.getYOffset(Gdx.graphics.getHeight()));
    }
}
