package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;
import com.mygdx.game.itsameeeemario.Brick;
import com.mygdx.game.itsameeeemario.Coin;
import com.mygdx.game.itsameeeemario.Flagpole;
import com.mygdx.game.itsameeeemario.Goomboss;
import com.mygdx.game.itsameeeemario.Pipe;
import com.mygdx.game.itsameeeemario.Unbreakable;
import com.mygdx.game.itsameeeemario.invisibleWall;

public class B2WorldCreator {
    public B2WorldCreator(GameScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bodyA = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // ground collision
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
        }

        // brick collision
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);
        }

        // coin collision
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
        }

        // pipe collision
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Pipe(screen, rect);
        }

        // unbreakable block collision
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Unbreakable(screen, rect);
        }

        // flagpole collision
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Flagpole(screen, rect);
        }

        // invisible wall collision
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new invisibleWall(screen, rect);
        }

        // Goomboss collision
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Goomboss(screen, rect);
        }
    }
}