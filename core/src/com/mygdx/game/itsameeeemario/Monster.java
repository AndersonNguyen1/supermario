package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screen.GameScreen;

public abstract class Monster extends Sprite {
    protected World world;
    protected GameScreen screen;
    public Body body;

    public Monster(GameScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineMonster();
    }

    protected abstract void defineMonster();
}
