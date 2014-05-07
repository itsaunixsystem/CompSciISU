package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.github.itsaunixsystem.chunks.ChunksGame;

import java.awt.geom.Rectangle2D;

public class GuiTextButton extends GuiBase implements Runnable {
    protected Runnable onClick;
    protected String text;
    protected ButtonStyle buttonStyle;

    public GuiTextButton(String text, Rectangle2D.Float outline, Runnable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiTextButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick) {
        this(text, outline, posStyle, onClick, ButtonStyle.NORMAL);
    }

    public GuiTextButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick, ButtonStyle style) {
        super(outline, posStyle);
        this.onClick = onClick;
        this.text = text;
        this.buttonStyle = style;
    }

    @Override
    public void draw(float delta) {
        buttonStyle.draw(delta, this);
        drawText(text, getXCenter(), getYCenter());
    }

    public boolean isClicked() {
        return hovering() && (Gdx.input.isButtonPressed(0) ||
                Gdx.app.getType() == Application.ApplicationType.Android ||
                Gdx.app.getType() == Application.ApplicationType.iOS);
    }

    @Override
    public void run() {
        ChunksGame.game.sound.playSound("click");
        onClick.run();
    }
}
