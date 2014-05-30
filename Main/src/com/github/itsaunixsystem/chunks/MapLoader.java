package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.itsaunixsystem.chunks.entities.EntityPlayer;

public class MapLoader {
    @SuppressWarnings(value = "unused")
    private static enum Types {
        SOLID,
        PLAYER;
    }

    public static World loadMap(TiledMap map, GameManager gameManager) {
        World world = new World(map, gameManager);
        for(MapObject object : map.getLayers().get("data").getObjects()) {
            MapProperties properties = object.getProperties();
            properties.get("type");

            switch((String) properties.get("type")) {
                case "solid":
                    if(object instanceof RectangleMapObject) {
                        world.solids.add(new AxisAlignedBoundingBox(((RectangleMapObject) object).getRectangle()));
                    } else if(object instanceof PolygonMapObject) {
                        world.solids.add(new AxisAlignedPolygon(((PolygonMapObject)object).getPolygon()));
                    }
                    break;
                case "player":
                    if(object instanceof RectangleMapObject) {
                        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                        world.getGameManager().setPlayer(new EntityPlayer(rectangle.getX(), rectangle.getY(), null));
                    }
                    else {
                        throw new GdxRuntimeException("Player must be rectangle!");
                    }
                    break;
            }
        }
        return world;
    }



}
