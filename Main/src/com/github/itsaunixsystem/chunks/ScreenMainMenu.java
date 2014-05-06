package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.github.itsaunixsystem.chunks.gui.*;

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

        addElement(new GuiTextButton("Start Game",
                new Rectangle2D.Float(0, -100, 200, 48),
                ElementPositionStyle.VH_CENTER,
                () -> game.setScreenAndInputProcessor(new ScreenLoading(game)),
                ButtonStyle.NORMAL));

        addElement(new GuiIconButton(new Texture("Main/res/cog.png"),
                new Rectangle2D.Float(-60, -60, 50, 50),
                ElementPositionStyle.TOP_RIGHT_OFFSET,
                () -> game.setScreenAndInputProcessor(new ScreenOptions(game))
        ));

        addElement(new GuiIconButton(new Texture("Main/res/exit.png"),
                new Rectangle2D.Float(10, -60, 50, 50),
                ElementPositionStyle.TOP_OFFSET,
                () -> Gdx.app.exit()
        ));
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
