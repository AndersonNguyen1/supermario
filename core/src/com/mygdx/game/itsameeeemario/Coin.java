package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Coin extends InteractiveTileObject {
    public Coin(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.COIN_BIT);
    }

    @Override
    public void headContact() {
        Gdx.app.log("Coin", "Collision");

    }

    @Override
    public void feetContact() {
    }
}