package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.itsaunixsystem.chunks.gui.ElementPositionStyle;
import com.github.itsaunixsystem.chunks.gui.GuiIconButton;
import com.github.itsaunixsystem.chunks.gui.GuiTextBox;

import java.awt.geom.Rectangle2D;

public class GameScreenInGame extends GameScreen {
    public GameScreenInGame(ChunksGame game, GameManager gameManager) {
        super(game, gameManager);
    }

    @Override
    public void init() {
        addElement(new GuiTextBox("UI temporary", new Rectangle2D.Float(200, -35, 200, 30),
                ElementPositionStyle.TOP_OFFSET));
        addElement(new GuiIconButton(new Texture("Main/res/exit.png"),
                new Rectangle2D.Float(10, -60, 50, 50),
                ElementPositionStyle.TOP_OFFSET,
                () -> game.setScreenAndInputProcessor(new ScreenMainMenu(game))
        ));
    }

    @Override
    public void drawScreen(float delta) {
        ShapeRenderer shapeRenderer = this.getGuiRenderer().getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.3f);
        shapeRenderer.rect(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -70);
        shapeRenderer.end();
    }
}
