package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.itsaunixsystem.chunks.Imp;

import java.awt.geom.Rectangle2D;

public class GuiTextButtonTextured extends GuiTextButton {
    protected static Imp texture;
    static {
        texture = new Imp(new Texture(Gdx.files.internal("Main/res/stonebutton.png")));
    }

    public GuiTextButtonTextured(String text, Rectangle2D.Float outline, Runnable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiTextButtonTextured(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick) {
        this(text, outline, posStyle, onClick, ButtonStyle.NORMAL);
    }

    public GuiTextButtonTextured(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick, ButtonStyle style) {
        super(text, outline, posStyle, onClick, style);
        outline.width = texture.getWidth();
        outline.height = texture.getHeight();
    }

    @Override
    public void draw(float delta) {
        float x = getX();
        float y = getY() - (isClicked() ? 1 : 0);
        SpriteBatch batch = this.renderer.getSpriteBatch();
        batch.begin();
        batch.draw(texture.getFrame(delta), x, y);
        batch.end();

        GL20 gl = Gdx.graphics.getGL20();
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.6f, 0.6f, 0.6f, animatedVal * 0.2f);
        shapeRenderer.rect(x, y, (float)(outline.getWidth()), (float)(outline.getHeight()));
        shapeRenderer.end();
        gl.glDisable(GL20.GL_BLEND);
        drawText(text, getXCenter(), getYCenter() - (isClicked() ? 1 : 0));
    }
}
