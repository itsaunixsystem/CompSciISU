package com.github.itsaunixsystem.chunks.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.geom.Rectangle2D;

/**
* Created by alex on 5/29/2014.
*/
public class GuiSlider extends GuiBase implements Runnable{
    protected Runnable onDrag;
    protected String text;
    protected float sliderValue;
    protected Rectangle2D.Float slide;

    public GuiSlider(String text, Rectangle2D.Float outline, Runnable onDrag){
        this(text, outline, ElementPositionStyle.FIXED, onDrag);
    }

    public GuiSlider(String text, Rectangle2D.Float outline, PositionStyle posStyle, Runnable onDrag)
    {
        super(outline, posStyle);
        this.onDrag = onDrag;
        this.text = text;
        sliderValue = 1.0f;
        this.slide = new Rectangle2D.Float(getX() + (outline.width * sliderValue) - 18, getY() + 3, 15, outline.height - 6);
    }

    public float getSliderValue()
    {
        return sliderValue;
    }

    @Override
    public void draw(float delta) {
        //buttonStyle.draw(delta, this);
        //drawText(text, getXCenter(), getYCenter());
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.4f, 0.4f, 0.4f, 0.6f);
        shapeRenderer.rect(getX(), getY(), (float) outline.getWidth(), (float) outline.getHeight());
        shapeRenderer.rect(getX() + 3, getY() + 3, (float) outline.getWidth() - 6, (float) outline.getHeight() - 6);
        shapeRenderer.setColor(0.7f, 0.7f, 0.7f, 0.6f);
        shapeRenderer.rect(slide.x, slide.y, slide.width, slide.height);
        shapeRenderer.end();

        drawText(text, getXCenter(), getYCenter());
    }

//    @Override
//    public void mouseDragged()
//    {
//
//    }

    @Override
    public void run() {
        onDrag.run();
    }

}
