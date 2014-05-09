package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.itsaunixsystem.chunks.ChunksGame;
import com.github.itsaunixsystem.chunks.Imp;
import com.github.itsaunixsystem.chunks.Screen;

import java.util.ArrayList;

public abstract class GuiScreen extends Screen implements com.badlogic.gdx.InputProcessor {
    private ArrayList<GuiElement> elements;
    private GuiRenderer guiRenderer;
    private static Imp defaultBackground;
    protected static boolean upBrightness;

    static {
        defaultBackground = new Imp(new Texture(Gdx.files.internal("Main/res/animatedbackground.png")), 2, 4);
    }

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

        guiRenderer = new GuiRenderer();
    }

    @Override
    public void render(float delta) {
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        drawScreen(delta);
        for(GuiElement element : elements) {
            element.render(delta);
        }
        if(upBrightness)
        {
            gl.glEnable(GL20.GL_BLEND);
            gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);

            ShapeRenderer upShape = guiRenderer.getShapeRenderer();
            upShape.begin(ShapeRenderer.ShapeType.Filled);
            upShape.setColor(1f,1f,1f,0.075f);
            upShape.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            upShape.end();

            gl.glDisable(GL20.GL_BLEND);
        }
    }

    protected void drawDefaultBackground(float delta) {
        SpriteBatch batch = guiRenderer.getSpriteBatch();
        batch.begin();
        batch.draw(defaultBackground.getFrame(delta), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

        for(GuiElement element : elements) {
            if(element instanceof GuiBase && element instanceof Runnable) {
                if(((GuiBase)element).withinBounds(screenX, screenY)) {
                    ((Runnable)element).run();
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
        guiRenderer.reset();
    }

    @Override
    public void dispose() {
        for(GuiElement element : elements) {
            element.dispose();
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
