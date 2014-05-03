package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.geom.Rectangle2D;

public class ScreenMainMenu extends GuiScreen {
    protected SpriteBatch batch;
    protected Texture background;

    public ScreenMainMenu(ChunksGame game) {
        super(game);
        System.out.println("New main menu!");
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Main/res/waterfall.png"));
    }

    @Override
    public void drawScreen(float delta) {
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void init() {
        addElement(new GuiButton("Prints \"Clicked 1!\"", new Rectangle2D.Float(200, 100, 400, 48), () -> System.out.println("Clicked 1!")));
        addElement(new GuiButton("Prints \"Clicked 2!\"", new Rectangle2D.Float(200, 300, 400, 48), () -> System.out.println("Clicked 2!")));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        batch = new SpriteBatch();
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

    //-------------GUI---------------
//
//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(int amount) {
//        return false;
//    }
}
