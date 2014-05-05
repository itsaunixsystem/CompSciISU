package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.github.itsaunixsystem.chunks.ChunksGame;

import java.awt.geom.Rectangle2D;

public class GuiButton extends GuiBase implements Runnable {
    protected Runnable onClick;
    protected float animatedVal;
    protected String text;
    protected ButtonStyle buttonStyle;

    public GuiButton(String text, Rectangle2D.Float outline, Runnable onClick) {
        this(text, outline, ElementPositionStyle.FIXED, onClick);
    }

    public GuiButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick) {
        this(text, outline, posStyle, onClick, ButtonStyle.NORMAL);
    }

    public GuiButton(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onClick, ButtonStyle style) {
        super(outline, posStyle);
        this.onClick = onClick;
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
    public void run() {
        ChunksGame.game.sound.playSound("click");
        onClick.run();
    }
}
