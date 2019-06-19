package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Goomba extends Monster{
    private float stateTime;
    private Animation animation;
    private Array<TextureRegion> frames = new Array<TextureRegion>();

    public Goomba(GameScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 30; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("NES - Super Mario Bros - Enemies"), i*0, 0, 16, 30));
        animation = new Animation(0.1f, frames);
        stateTime = 0;
        setBounds(0, 0, 16/ SuperMario.PPM, 16 / SuperMario.PPM);
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        setRegion((TextureRegion) animation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineMonster() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / SuperMario.PPM, 30 / SuperMario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / SuperMario.PPM);

        fdef.filter.categoryBits = SuperMario.MONSTER_BIT;
        fdef.filter.maskBits = SuperMario.FLOOR_BIT | SuperMario.COIN_BIT | SuperMario.BRICK_BIT | SuperMario.MONSTER_BIT | SuperMario.OBJECT_BIT;

        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
