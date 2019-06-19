package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Unbreakable extends InteractiveTileObject{
    public Unbreakable(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK_BIT);
    }

    @Override
    public void headContact() { Gdx.app.log("unbreakable block", "Collision"); }

    @Override
    public void feetContact() {
    }

}
