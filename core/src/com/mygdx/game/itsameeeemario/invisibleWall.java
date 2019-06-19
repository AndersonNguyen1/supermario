package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class invisibleWall extends InteractiveTileObject {
    public invisibleWall(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK_BIT);
    }

    @Override
    public void headContact() {
        Gdx.app.log("invisible wall", "Collision");
    }

    @Override
    public void feetContact() {
    }
}
