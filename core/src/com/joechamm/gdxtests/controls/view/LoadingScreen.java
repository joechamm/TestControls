package com.joechamm.gdxtests.controls.view;

/**
 * File:    LoadingScreen
 * Package: com.joechamm.gdxtests.controls.view
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/1/2023 at 5:10 PM
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.joechamm.gdxtests.controls.JCGdxTestControls;

import com.joechamm.gdxtests.controls.asset.JCGdxAssetManager;

public class LoadingScreen implements Screen {

    private static final String TAG = LoadingScreen.class.getName ();

    JCGdxTestControls parent;

    private Stage stage;
    private Skin skin;
    private ProgressBar progressBar;

    // loading stages
    public final int LOAD_IMAGES = 0;
    public final int LOAD_FONTS = 1;
    public final int LOAD_EFFECTS = 2;
    public final int LOAD_SOUNDS = 3;
    public final int LOAD_MUSIC = 4;
    public final int LOAD_FINISHED = 5;

    private int currentLoadingStage = 0;

    public float countDown = 5f;

    public LoadingScreen ( JCGdxTestControls jcGdxTestControls ) {
        Gdx.app.debug ( TAG, "ctor" );

        parent = jcGdxTestControls;

        stage = new Stage ( new ScreenViewport () );

        loadAssets();
    }

    private void loadAssets () {
        Gdx.app.debug ( TAG, "loadAssets" );

        // load skin
        parent.assetManager.queueAddSkin ();
        parent.assetManager.manager.finishLoading ();

        skin = parent.assetManager.manager.get ( parent.assetManager.skinJson );

        parent.assetManager.queueAddImages ();
        Gdx.app.debug ( TAG, "Loading images..." );
    }

    @Override
    public void show () {

        Gdx.app.debug ( TAG, "show" );

        Table table = new Table ( skin );
        table.setFillParent ( true );
        table.setDebug ( true );

        table.setBackground ( "backgroundSunrise02" );

        float padWidth = 0.15f * Gdx.graphics.getWidth ();
        float padHeight = 0.15f * Gdx.graphics.getHeight ();
        float progMin = 0.0f;
        float progMax = (float)LOAD_FINISHED;

        Image lpTitleImage = new Image ( skin, "laser planes" );
        lpTitleImage.setScaling ( Scaling.fit );

        progressBar = new ProgressBar ( progMin, progMax, 1f, false, skin, "loading-horizontal" );

        table.add ( lpTitleImage ).align ( Align.top ).pad ( padHeight / 2, padWidth / 2, padHeight / 2, padWidth / 2 );
        table.row ();
        table.add ( progressBar ).align ( Align.center ).pad ( padHeight / 2, padWidth / 2, padHeight / 2, padWidth );

        stage.addActor ( table );

        progressBar.setValue ( 1.0f );
    }

    @Override
    public void render ( float delta ) {

        Gdx.gl.glClearColor ( 0, 0, 0, 1 );
        Gdx.gl.glClear ( GL20.GL_COLOR_BUFFER_BIT );

        if ( parent.assetManager.manager.update () ) {
            currentLoadingStage++;

            progressBar.setValue ( (float)currentLoadingStage );

            switch ( currentLoadingStage ) {
                case LOAD_FONTS:
                    Gdx.app.debug ( TAG, "Loading fonts..." );
                    parent.assetManager.queueAddFonts ();
                    break;
                case LOAD_EFFECTS:
                    Gdx.app.debug ( TAG, "Loading effects..." );
                    parent.assetManager.queueAddParticleEffects ();
                    break;
                case LOAD_SOUNDS:
                    Gdx.app.debug ( TAG, "Loading sounds..." );
                    parent.assetManager.queueAddSounds ();
                    break;
                case LOAD_MUSIC:
                    Gdx.app.debug ( TAG, "Loading music..." );
                    parent.assetManager.queueAddMusic ();
                    break;
                case LOAD_FINISHED:
                    Gdx.app.debug ( TAG, "Finished loading..." );
                    break;
            }

            if ( currentLoadingStage >= LOAD_FINISHED ) {
                countDown -= delta;
                currentLoadingStage = LOAD_FINISHED;
                if(countDown < 0f) {
                    parent.changeScreen ( JCGdxTestControls.MENU );
                }
            }
        }

        stage.act (delta);
        stage.draw ();
    }

    @Override
    public void resize ( int width, int height ) {
        Gdx.app.debug ( TAG, "resize dimensions: " + width + "x" + height );
        stage.getViewport ().update ( width, height, true );
    }

    @Override
    public void pause () {
        Gdx.app.debug ( TAG, "pause" );
    }

    @Override
    public void resume () {
        Gdx.app.debug ( TAG, "resume" );
    }

    @Override
    public void hide () {
        Gdx.app.debug ( TAG, "hide" );
    }

    @Override
    public void dispose () {
        Gdx.app.debug ( TAG, "dispose" );
        stage.dispose ();
    }

}
