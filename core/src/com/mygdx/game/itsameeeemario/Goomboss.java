package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Goomboss extends InteractiveTileObject {
    public Goomboss(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK_BIT);
    }

    @Override
    public void headContact() {
        Gdx.app.log("Goomboss", "Collision");
    }

    @Override
    public void feetContact() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(SuperMario.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
