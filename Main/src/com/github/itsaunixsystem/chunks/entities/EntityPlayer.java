package com.github.itsaunixsystem.chunks.entities;

import com.badlogic.gdx.math.Vector2;
import com.github.itsaunixsystem.chunks.control.MovementProcessor;
import com.github.itsaunixsystem.chunks.Imp;

import java.util.HashMap;

public class EntityPlayer extends EntityLiving implements MovementProcessor {
    private static HashMap<String, Imp> drawings;

    public static void loadDrawings(HashMap<String, Imp> drawings) {
        EntityPlayer.drawings = drawings;
    }

    public EntityPlayer(Vector2 pos, HashMap<String, Imp> drawings) {
        super(pos, drawings);
    }

    public EntityPlayer(float x, float y, HashMap<String, Imp> drawings) {
        super(x, y, drawings);
    }

    @Override
    public void die(Entity cause) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void collide(EntityLiving entityLivingOther) {

    }
}
