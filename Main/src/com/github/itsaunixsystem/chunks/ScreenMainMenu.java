package com.github.itsaunixsystem.chunks;

import java.awt.geom.Rectangle2D;

public class ScreenMainMenu extends GuiScreen {


    public ScreenMainMenu(ChunksGame game) {
        super(game);
        System.out.println("New main menu!");
    }

    @Override
    public void drawScreen(float delta) {
        drawDefaultBackground();
    }

    @Override
    public void init() {
        addElement(new GuiButton("Prints \"Clicked 1!\"", new Rectangle2D.Float(200, 100, 400, 48), () -> System.out.println("Clicked 1!")));
        addElement(new GuiButton("Prints \"Clicked 2!\"", new Rectangle2D.Float(200, 300, 400, 48), () -> System.out.println("Clicked 2!")));
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
