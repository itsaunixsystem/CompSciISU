package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.itsaunixsystem.chunks.ChunksGame;
import com.github.itsaunixsystem.chunks.Imp;
import com.github.itsaunixsystem.chunks.Screen;

import java.util.ArrayList;

//Screen specifically for GUI creation

public abstract class GuiScreen extends Screen implements com.badlogic.gdx.InputProcessor {
    private ArrayList<GuiElement> elements; //ArrayList of GuiElement (buttons etc) that will be added and drawn per frame.
    private GuiRenderer guiRenderer;    //for "drawing" to screen
    private static Imp defaultBackground;   //static default background that will be used often.

    static {
        defaultBackground = new Imp(new Texture(Gdx.files.internal("Main/res/animatedbackground.png")), 2, 4);
    }

    public GuiScreen(ChunksGame game) {     //constructor, calls the Screen constructor with super() as well.
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
    public void render(float delta) {   //draws everything to screen.
        GL20 gl = Gdx.graphics.getGL20();   //openGL stuff
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        drawScreen(delta);
        for(GuiElement element : elements) {    //having all the elements render.
            element.render(delta);
        }
    }

    protected void drawDefaultBackground(float delta) {
        SpriteBatch batch = guiRenderer.getSpriteBatch();
        batch.begin();
        batch.draw(defaultBackground.getFrame(delta), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    //adding new elements to the elements on this screen
    protected void addElement(GuiElement element) {
        elements.add(element);
    }

    public abstract void drawScreen(float delta);

    public abstract void init();    //where desired objects of screens extending this are added

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {   //clicked
        screenY = flipY(screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { //unclick
        screenY = flipY(screenY);

        for(GuiElement element : elements) {
            if(element instanceof GuiBase && element instanceof Runnable) {
                if(((GuiBase)element).withinBounds(screenX, screenY)) {
                    ((Runnable)element).run();  //runnable GuiElements
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {    //when dragged
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

    public void resize (int width, int height)      //changing size , resetting renderer
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

    public GuiRenderer getGuiRenderer() {
        return guiRenderer;
    }
}
