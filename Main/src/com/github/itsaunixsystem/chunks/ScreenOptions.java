package com.github.itsaunixsystem.chunks;

import com.github.itsaunixsystem.chunks.gui.*;

import java.awt.geom.Rectangle2D;

public class ScreenOptions extends GuiScreen {
    public ScreenOptions(ChunksGame game) {
        super(game);
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground(delta);
    }

    @Override
    public void init() {
        addElement(new GuiTextButton("Main Menu",
                new Rectangle2D.Float(0, -120, 300, 48),
                ElementPositionStyle.VH_CENTER,
                () -> game.setScreenAndInputProcessor(new ScreenMainMenu(game)),
                ButtonStyle.NORMAL));

        addElement(new GuiSlider("TESTERINO",
                new Rectangle2D.Float(-180, 130, 300, 48),
                ElementPositionStyle.VH_CENTER,
                () -> System.out.println()
                ));

        for(int i = 1; i < 8; i++) {
            final int x = i + 1;
            addElement(new GuiTextButton("Hypothetical button " + (i + 1),
                    new Rectangle2D.Float(i > 3 ? 180 : -180, 130 - (60 * (i > 3 ? i - 4 : i)), 300, 48),
                    ElementPositionStyle.VH_CENTER,
                    () -> System.out.println("YOU FUCKING PRESSED HYPOTHETICAL BUTTON " + x),
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
