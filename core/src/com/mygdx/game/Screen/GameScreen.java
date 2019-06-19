package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scene.Hud;
import com.mygdx.game.SuperMario;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;
import com.mygdx.game.itsameeeemario.Coin;
import com.mygdx.game.itsameeeemario.Goomba;
import com.mygdx.game.itsameeeemario.Mario;

public class GameScreen implements Screen {
    private SuperMario game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader mapcreator;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer Box2DDebugRenderer;

    //Box2d Mario
    private Mario player;

    // goomba
    private Goomba goomba;

    // creating instance of song
    private Music music;

    public GameScreen(SuperMario game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(SuperMario.V_WIDTH / SuperMario.PPM, SuperMario.V_HEIGHT / SuperMario.PPM, gamecam);
        hud = new Hud(game.batch);

        mapcreator = new TmxMapLoader();
        map = mapcreator.load("NES - Super Mario Bros - Tileset.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / SuperMario.PPM);

        gamecam.position.set((gamePort.getScreenWidth()+240) / SuperMario.PPM, (gamePort.getScreenHeight() + 100) / SuperMario.PPM, 0);

        // adds gravity to the game
        world = new World(new Vector2(0, -60), true);
        Box2DDebugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        //  create a objects
        player = new Mario(this);
        goomba = new Goomba(this, .32f, .32f);

        // creates music
        music = SuperMario.manager.get("Super Mario Bros. Original Theme by Nintendo.mp3", Music.class);
        music.setLooping(true);
        music.play();

        // detects for contact on mario's body
        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    // dt stands for change in time, aka delta t
    public void detectingInput (float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            player.box2d.applyLinearImpulse(new Vector2(0, 2f), player.box2d.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.box2d.getLinearVelocity().x <= 8)
            player.box2d.applyLinearImpulse(new Vector2(8.0f, 0),  player.box2d.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.box2d.getLinearVelocity().x >= -8)
            player.box2d.applyLinearImpulse(new Vector2(-8.0f, 0),  player.box2d.getWorldCenter(), true);
    }

    public void update(float dt){
        detectingInput(dt);

        world.step(1/60f, 6, 2);

        player.update(dt);
        goomba.update(dt);

        gamecam.position.x = player.box2d.getPosition().x;
        gamecam.update();

        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //render box2d
        Box2DDebugRenderer.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        //goomba.draw(game.batch);
        game.batch.end();

        // so the hud stays still
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // fixes the game viewport
        gamePort.update(width, height);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        hud.dispose();
    }
}