package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;
import com.github.itsaunixsystem.chunks.entities.EntityPlayer;

import java.io.IOException;
import java.util.HashMap;

public class MapLoader {
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
                        HashMap<String, Imp> textures = new HashMap<>();
//                        String xml = gameManager.get("player.layout");   //TODO
                        XmlReader reader = new XmlReader();
                        XmlReader.Element root = null;
                        try {
                            root = reader.parse(Gdx.files.internal("Main/res/player.layout")); //HACKY
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(!root.getName().equalsIgnoreCase("layout"))
                            throw new GdxRuntimeException("player.layout contains invalid layout file!");
                        Texture sheet = gameManager.get(root.getAttribute("sheet"));

                        int length = root.getChildrenByName("animation").size; //cache
                        for(int i = 0; i < length; i++) {
                            XmlReader.Element child = root.getChildrenByName("animation").get(i);
                            TextureRegion[] frames = new TextureRegion[child.getChildrenByName("frame").size];
                            for(int j = 0; j < frames.length; j++) {
                                TextureRegion frame = new TextureRegion(sheet);
                                XmlReader.Element frameElement = child.getChildrenByName("frame").get(j);
                                frame.setRegion(
                                        Integer.parseInt(frameElement.getAttribute("x")),
                                        Integer.parseInt(frameElement.getAttribute("y")),
                                        Integer.parseInt(frameElement.getAttribute("width")),
                                        Integer.parseInt(frameElement.getAttribute("height"))
                                );
                                frames[j] = frame;
                            }
                            textures.put(child.getAttribute("name"), new Imp(frames));
                        }

                        world.setPlayer(new EntityPlayer(rectangle.getX(), rectangle.getY(), textures));
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
