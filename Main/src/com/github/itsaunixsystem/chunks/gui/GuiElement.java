package com.github.itsaunixsystem.chunks.gui;

public interface GuiElement {   //interface that all elements will implement, called when rendered etc
    public void render(float delta);

    public void mouseDown(int screenX, int screenY, int pointer);
    public void mouseDragged(int screenX, int screenY, int pointer);
    public void onHover(int pointer);
    public void offHover(int pointer);

    public void dispose();
}
