package com.github.itsaunixsystem.chunks.entities;

import com.badlogic.gdx.math.Vector2;
import com.github.itsaunixsystem.chunks.Imp;
import com.github.itsaunixsystem.chunks.World;

import java.util.HashMap;

public abstract class EntityLiving extends EntityDrawable {
    protected World world;
    private float health; //from 0.0f to 1.0f
    private Vector2 velocity;
    public static final float GRAVITY = 1.f;

    public abstract void die(Entity cause);

    public EntityLiving(Vector2 pos, HashMap<String, Imp> drawings) {
        this(pos.x, pos.y, drawings);
    }

    public EntityLiving(float x, float y, HashMap<String, Imp> drawings) {
        super(x, y, drawings);
        this.world = world;
        velocity = new Vector2();
    }

    public boolean isDead() {
        return health <= 0.0f;
    }

    @Override
    public void update(float delta) {
        velocity.y -= delta * GRAVITY;
        world.applyGravityAndCollision(this, delta);
    }

    public abstract void collide(EntityLiving entityLivingOther);

    public Vector2 getVelocity() {
        return velocity;
    }
}
