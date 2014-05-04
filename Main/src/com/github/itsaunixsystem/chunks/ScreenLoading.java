package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.ElementPositionStyle;
import com.github.itsaunixsystem.chunks.gui.GuiProgressBar;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;
import com.github.itsaunixsystem.chunks.gui.GuiTextBox;

import java.awt.geom.Rectangle2D;

public class ScreenLoading extends GuiScreen {
    private GameRenderer gameRenderer;
    private int progress;
    private GuiProgressBar  progressBar;

    public ScreenLoading(ChunksGame game) {
        super(game);
        progress = 0;
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground();
        gameRenderer.render(delta);
    }

    @Override
    public void init() {
        addElement(new GuiTextBox("Loading...", new Rectangle2D.Float(0, 0, 200, 48), ElementPositionStyle.VH_CENTER));
        addElement(progressBar = new GuiProgressBar(new Rectangle2D.Float(0, -50, 200, 20), ElementPositionStyle.VH_CENTER));
        gameRenderer = new GameRenderer((double progress) -> progressBar.addProgress((float) progress));
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
