package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.ButtonStyle;
import com.github.itsaunixsystem.chunks.gui.ElementPositionStyle;
import com.github.itsaunixsystem.chunks.gui.GuiButton;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;

import java.awt.geom.Rectangle2D;

public class ScreenOptions extends GuiScreen {
    public ScreenOptions(ChunksGame game) {
        super(game);
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground();
    }

    @Override
    public void init() {
        addElement(new GuiButton("Main Menu",
                new Rectangle2D.Float(0, -120, 300, 48),
                ElementPositionStyle.VH_CENTER,
                () -> game.setScreenAndInputProcessor(new ScreenMainMenu(game)),
                ButtonStyle.NORMAL));

        for(int i = 0; i < 8; i++) {
            addElement(new GuiButton("Hypothetical button " + (i + 1),
                    new Rectangle2D.Float(i > 3 ? 180 : -180, 130 - (60 * (i > 3 ? i - 4 : i)), 300, 48),
                    ElementPositionStyle.VH_CENTER,
                    () -> {
                    },
                    ButtonStyle.NORMAL));
        }
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
