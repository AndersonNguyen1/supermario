package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Pipe extends InteractiveTileObject {
    public Pipe(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.PIPE_BIT);
    }

    @Override
    public void headContact() { Gdx.app.log("Pipe", "Collision"); }

    @Override
    public void feetContact() {
    }

}
