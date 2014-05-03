package com.github.itsaunixsystem.chunks;

import java.util.HashMap;

public class PointerManager {
    private HashMap<Integer, GuiElement> tracker;

    public PointerManager()
    {
        tracker = new HashMap<Integer, GuiElement>();
    }

    public void track(int pointer, GuiElement element)
    {
        tracker.put(pointer, element);
    }


    public void release(int pointer)
    {
        tracker.remove(pointer);
    }
}
