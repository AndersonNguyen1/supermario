package com.mygdx.game.itsameeeemario;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.SuperMario;

public class Mario extends Sprite {
    public enum State {FALLING, STANDING, JUMPING, RUNNING}

    ;
    public State currState;
    public State prevState;

    public World world;
    public Body box2d;
    private TextureRegion marioStanding;

    private Animation marioRun;
    private Animation marioJump;
    private float stateTimer;
    private boolean runningRight;

    public Mario(GameScreen screen) {
        super(screen.getAtlas().findRegion("NES - Super Mario Bros - Mario & Luigi"));

        this.world = screen.getWorld();
        currState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 32 + 2, 165, 14, 16));
        marioRun = new Animation(0.01f, frames);

        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 32 + 7, 165, 14, 16));
        marioJump = new Animation(0.01f, frames);

        frames.clear();
        marioDefinition();
        marioStanding = new TextureRegion(getTexture(), 82, 165, 14, 16);
        setBounds(0, 0, 16 / SuperMario.PPM, 16 / SuperMario.PPM);
        setRegion(marioStanding);
    }

    public void update(float dt) {
        setPosition(box2d.getPosition().x - getWidth() / 2, box2d.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currState = getState();

        TextureRegion region;
        switch (currState) {
            case JUMPING:
                region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStanding;
                break;
        }

        if ((box2d.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((box2d.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currState == prevState ? stateTimer + dt : 0;
        prevState = currState;
        return region;
    }

    public State getState(){
        if(box2d.getLinearVelocity().y > 0 || (box2d.getLinearVelocity().y < 0 && prevState == State.JUMPING))
            return State.JUMPING;
        else if(box2d.getLinearVelocity().y <0)
            return State.FALLING;
        else if(box2d.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void marioDefinition(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(60 / SuperMario.PPM, 30 / SuperMario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        box2d = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / SuperMario.PPM);

        fdef.filter.categoryBits = SuperMario.MARIO_BIT;
        fdef.filter.maskBits = SuperMario.FLOOR_BIT | SuperMario.COIN_BIT | SuperMario.BRICK_BIT | SuperMario.PIPE_BIT;
        //fdef.filter.maskBits = SuperMario.FLOOR_BIT | SuperMario.COIN_BIT | SuperMario.BRICK_BIT;

        fdef.shape = shape;
        box2d.createFixture(fdef);

        EdgeShape marioHead = new EdgeShape();
        marioHead.set(new Vector2(-2/SuperMario.PPM, 9 / SuperMario.PPM), new Vector2(2/SuperMario.PPM, 9 / SuperMario.PPM));
        fdef.shape = marioHead;
        fdef.isSensor = true;
        box2d.createFixture(fdef).setUserData("head");

        EdgeShape marioFeet = new EdgeShape();
        marioFeet.set(new Vector2(-2/SuperMario.PPM, -9 / SuperMario.PPM), new Vector2(2/SuperMario.PPM, -9 / SuperMario.PPM));
        fdef.shape = marioFeet;
        fdef.isSensor = true;
        box2d.createFixture(fdef).setUserData("feet");
    }
}