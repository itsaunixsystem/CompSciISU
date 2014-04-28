package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Game;

public class ChunksGame extends Game {
    @Override
    public void create() {
        setScreen(new ScreenMainMenu(this));
    }
}
