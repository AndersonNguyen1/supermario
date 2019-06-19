package com.mygdx.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SuperMario;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;  //I use a new camera for the hud because I do not want it to move with the character and makes sure it stays still
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    private Integer score;

    Label scoreLabel;
    Label levelLabel;
    Label worldLabel;
    Label livesLabel;
    Label marioLabel;
    Label numberLives;
    Label up;
    Label down;
    Label left;
    Label right;
    Label blank;

    public Hud(SpriteBatch sb) {
        score = 0;
        viewport = new FitViewport(SuperMario.V_WIDTH, SuperMario.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        livesLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        numberLives = new Label(String.format("3", score), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        up = new Label("                                      UP", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        down = new Label("                                      DOWN", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        left = new Label("LEFT", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        right = new Label("RIGHT", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        blank = new Label(" ", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));

        table.add(marioLabel).expandX();    //expand is used because it handles the spacing of the text
        table.add(worldLabel).expandX();
        table.add(livesLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(numberLives).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        table.add(blank).expandX();
        table.row();
        //table.add(up).expandX();
        table.row();
        //table.add(left).expandX();
        //table.add(right).expandX();
        table.row();
        //table.add(down).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}