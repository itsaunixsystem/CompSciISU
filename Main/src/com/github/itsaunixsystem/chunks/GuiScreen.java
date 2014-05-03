package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class GuiScreen extends Screen implements com.badlogic.gdx.InputProcessor {
    private ArrayList<GuiElement> elements;
    protected SpriteBatch batch;
    protected Texture defaultBackground;

    public GuiScreen(ChunksGame game) {
        super(game);
        elements = new ArrayList<>();
        cachedHeight = Gdx.graphics.getHeight();
        init();
        switch(Gdx.app.getType()) {
            case Desktop:
            case HeadlessDesktop:
            case Applet:
                mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            default:
        }

        batch = new SpriteBatch();
        defaultBackground = new Texture(Gdx.files.internal("Main/res/waterfall.png"));
    }

    @Override
    public void render(float delta) {
        drawScreen(delta);
        for(GuiElement element : elements)
        {
            element.render(delta);
        }
    }

    protected void drawDefaultBackground() {
        batch.begin();
        batch.draw(defaultBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    protected void addElement(GuiElement element)
    {
        elements.add(element);
    }

    public abstract void drawScreen(float delta);

    public abstract void init();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = flipY(screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = flipY(screenY);

        for(GuiElement element : elements)
        {
            if(element instanceof GuiBase && element instanceof GuiClickable) {
                if(((GuiBase)element).withinBounds(screenX, screenY)) {
                    ((GuiClickable)element).click();
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        final int yFlip = flipY(screenY);

        elements.stream().filter(element -> element instanceof GuiBase).forEach(element -> {
            if (((GuiBase) element).withinBounds(screenX, yFlip)) {
                element.onHover(pointer);
            } else {
                element.offHover(pointer);
            }
        });
        return true;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {
        touchDragged(screenX, screenY, 0);
        return true;
    }

    private int cachedHeight;
    private int flipY(int y)
    {
        return cachedHeight - y;
    }

    public void resize (int width, int height)
    {
        elements.stream().filter(element -> element instanceof GuiBase).forEach(element -> ((GuiBase) element).refreshRenderer());
        cachedHeight = height;
        batch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        for(GuiElement element : elements) {
            element.dispose();
        }
    }
}
