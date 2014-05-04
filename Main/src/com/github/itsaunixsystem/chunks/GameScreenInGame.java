package com.github.itsaunixsystem.chunks;

public class GameScreenInGame extends GameScreen {
    public GameScreenInGame(ChunksGame game) {
        super(game);
    }

    public GameScreenInGame(ChunksGame game, GameLoader gameLoader) {
        super(game, gameLoader);
    }

    @Override
    public void init() {

    }

    @Override
    public void drawScreen(float delta) {
        super.drawScreen(delta);
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
