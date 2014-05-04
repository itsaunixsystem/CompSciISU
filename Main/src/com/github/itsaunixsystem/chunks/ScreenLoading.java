package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.ElementPositionStyle;
import com.github.itsaunixsystem.chunks.gui.GuiProgressBar;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;
import com.github.itsaunixsystem.chunks.gui.GuiTextBox;

import java.awt.geom.Rectangle2D;

public class ScreenLoading extends GuiScreen {
    private GameRenderer gameRenderer;
    private GuiProgressBar progressBar;
    private GuiTextBox textBox;

    public ScreenLoading(ChunksGame game) {
        super(game);
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground();
    }

    @Override
    public void init() {
        addElement(textBox = new GuiTextBox("Loading...", new Rectangle2D.Float(0, 0, 200, 48), ElementPositionStyle.VH_CENTER));
        addElement(progressBar = new GuiProgressBar(new Rectangle2D.Float(0, -50, 200, 20), ElementPositionStyle.VH_CENTER));
        gameRenderer = new GameRenderer((double progress) -> progressBar.updateProgress((float) progress), (text) -> setText(text));
        gameRenderer.startInit(() -> game.setScreenAndInputProcessor(new GameScreenInGame(game, gameRenderer)));
    }

    private void setText(String text) {
        textBox.setText(text);
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
