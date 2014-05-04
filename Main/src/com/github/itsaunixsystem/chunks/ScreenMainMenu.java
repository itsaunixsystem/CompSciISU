package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.github.itsaunixsystem.chunks.gui.ElementPositionStyle;
import com.github.itsaunixsystem.chunks.gui.GuiButton;
import com.github.itsaunixsystem.chunks.gui.GuiScreen;

import java.awt.geom.Rectangle2D;

public class ScreenMainMenu extends GuiScreen {
    public ScreenMainMenu(ChunksGame game) {
        super(game);
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground();
    }

    @Override
    public void init() {
        addElement(new GuiButton("Start Game",
                new Rectangle2D.Float(100, 30, 200, 48),
                ElementPositionStyle.combine((in) -> in / 50, ElementPositionStyle.V_CENTER),
                () -> System.out.println("Starting game!"),
                GuiButton.ButtonStyle.GRADIENT));

        addElement(new GuiButton("Options",
                new Rectangle2D.Float(100, -30, 200, 48),
                ElementPositionStyle.combine((in) -> in / 50, ElementPositionStyle.V_CENTER),
                () -> game.setScreenAndInputProcessor(new ScreenOptions(game)),
                GuiButton.ButtonStyle.GRADIENT));

        addElement(new GuiButton("Quit",
                new Rectangle2D.Float( -110, 34, 200, 48),
                ElementPositionStyle.RIGHT_OFFSET,
                () -> Gdx.app.exit()));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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

    @Override
    public void dispose() {

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
}
