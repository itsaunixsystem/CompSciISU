package com.github.itsaunixsystem.chunks;

public interface GuiElement {
    public void render(float delta);

    public void mouseDown(int screenX, int screenY, int pointer);
    public void mouseDragged(int screenX, int screenY, int pointer);
    public void onHover(int pointer);
    public void offHover(int pointer);
}
