package com.joechamm.gdxtests.controls.view;

/**
 * File:    MenuScreen
 * Package: com.joechamm.gdxtests.controls.view
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/1/2023 at 1:21 PM
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.joechamm.gdxtests.controls.JCGdxTestControls;

public class MenuScreen implements Screen {

    private static final String TAG = MenuScreen.class.getName ();

    private Stage stage;
    private Skin skin;

    private JCGdxTestControls parent;

    public MenuScreen( JCGdxTestControls jcGdxTestControls ) {
        parent = jcGdxTestControls;
    }

    @Override
    public void show () {

        stage = new Stage (new ScreenViewport ());
        skin = new Skin ( Gdx.files.internal ( "skin/laser-planes-ui.json" ) );
        Gdx.input.setInputProcessor ( stage );

        Table table = new Table();
        table.setFillParent(true);

        Stack stack = new Stack();

        Image image = new Image( skin, "backgroundSunrise02");
        image.setScaling( Scaling.fillX);
        stack.addActor(image);

        Table table1 = new Table();
        table1.pad(30.0f);

        image = new Image(skin, "laser planes");
        image.setScaling(Scaling.fit);
        table1.add(image).pad(10.0f).spaceBottom(10.0f).grow().minWidth(800.0f).minHeight(54.0f).maxWidth(1540.0f).maxHeight(138.0f).prefWidth(1540.0f).prefHeight(138.0f);

        table1.row();
        Table table2 = new Table();

        Label label = new Label( "a game by", skin, "small-br");
        label.setAlignment( Align.center);
        label.setColor(skin.getColor("White"));
        table2.add(label).grow();

        table2.row();
        label = new Label("fearless rabbit solutions", skin, "large-br");
        label.setAlignment(Align.center);
        label.setColor(skin.getColor("SafehouseBlueMid"));
        table2.add(label).grow();
        table1.add(table2).pad(10.0f).grow().minWidth(400.0f).minHeight(42.0f).maxWidth(850.0f).maxHeight(90.0f).prefWidth(850.0f).prefHeight(90.0f);

        table1.row();
        table2 = new Table();
        table2.setBackground(skin.getDrawable("square_wh_28-10-bl"));
        table2.padLeft(40.0f);
        table2.padRight(40.0f);
        table2.padTop(20.0f);
        table2.padBottom(50.0f);

        TextButton newGameButton = new TextButton( "new game", skin, "subtitle-br-brd-shd-fr");
        newGameButton.setName("newGameButton");
        table2.add(newGameButton).padLeft(30.0f).padRight(30.0f).padTop(10.0f).padBottom(10.0f).grow();

        table2.row();
        TextButton	preferencesButton = new TextButton("preferences", skin, "subtitle-br-brd-shd-fr");
        preferencesButton.setName("prefButton");
        table2.add(preferencesButton).padLeft(30.0f).padRight(30.0f).padTop(10.0f).padBottom(10.0f).grow();

        table2.row();
        TextButton exitButton = new TextButton("exit", skin, "subtitle-br-brd-shd-fr");
        exitButton.setName("exitButton");
        table2.add(exitButton).padLeft(30.0f).padRight(30.0f).padTop(10.0f).padBottom(10.0f).grow();
        table1.add(table2).grow().align(Align.bottom).minWidth(500.0f).minHeight(280.0f).maxWidth(1000.0f).maxHeight(560.0f).prefWidth(800.0f).prefHeight(448.0f);
        stack.addActor(table1);
        table.add(stack);
        stage.addActor(table);


        // create button listeners
        newGameButton.addListener ( new ChangeListener () {
            @Override
            public void changed ( ChangeEvent event, Actor actor ) {
                Gdx.app.debug ( TAG, "New Game Pressed..." );
                // TODO: implement
            }
        } );

        preferencesButton.addListener ( new ChangeListener () {
            @Override
            public void changed ( ChangeEvent event, Actor actor ) {
                Gdx.app.debug ( TAG, "Preferences Pressed..." );
                // TODO: implement
            }
        } );

        exitButton.addListener ( new ChangeListener () {
            @Override
            public void changed ( ChangeEvent event, Actor actor ) {
                Gdx.app.debug ( TAG, "Exit Pressed..." );
                // TODO: implement
            }
        } );

    }

    @Override
    public void render ( float delta ) {

        Gdx.gl.glClearColor ( 0, 0, 0, 1 );
        Gdx.gl.glClear ( GL20.GL_COLOR_BUFFER_BIT );
        stage.act (Gdx.graphics.getDeltaTime ());
        stage.draw ();
    }

    @Override
    public void resize ( int width, int height ) {
        stage.getViewport ().update ( width, height, true );

    }

    /** @see ApplicationListener#pause() */
    @Override
    public void pause () {

    }

    /** @see ApplicationListener#resume() */
    @Override
    public void resume () {

    }

    /** Called when this screen is no longer the current screen for a {@link Game}. */
    @Override
    public void hide () {

    }

    @Override
    public void dispose () {
        stage.dispose ();
        skin.dispose ();
    }

}
