package com.joechamm.gdxtests.controls.view;

/**
 * File:    PreferencesScreen
 * Package: com.joechamm.gdxtests.controls.view
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/1/2023 at 10:22 PM
 */

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.joechamm.gdxtests.controls.JCGdxTestControls;

public class PreferencesScreen implements Screen {

    public static final String TAG = PreferencesScreen.class.getName ();

    JCGdxTestControls parent;

    Stage stage;
    Skin skin;

    public PreferencesScreen ( JCGdxTestControls jcGdxTestControls ) {
        Gdx.app.debug ( TAG, "ctor" );
        parent = jcGdxTestControls;

        stage = new Stage (new ScreenViewport ());

    }

    @Override
    public void show () {

        skin = parent.assetManager.manager.get ( parent.assetManager.skinJson );

        Table table = new Table();
        table.setFillParent(true);

        Stack stack = new Stack();

        Image image = new Image( skin, "backgroundSunrise02");
        image.setScaling( Scaling.stretchX);
        stack.addActor(image);

        Table table1 = new Table();
        table1.setTouchable(enabled);

        Label label = new Label( "preferences", skin, "title-br-brd-shd");
        label.setTouchable(disabled);
        label.setAlignment( Align.center);
        table1.add(label).pad(5.0f).expand().uniform().colspan(3);

        table1.row();
        Table table2 = new Table();
        table2.setBackground(skin.getDrawable("square_wh_28-10-bl"));
        table2.pad(10.0f);

        label = new Label("music volume", skin, "med-br");
        label.setAlignment(Align.center);
        label.setWrap(true);
        table2.add(label).growX().uniform();

        // volume
        final Slider musicVolumeSlider = new Slider( 0.0f, 1.0f, 0.1f, false, skin, "default-horizontal");
        musicVolumeSlider.setName("musicVolumeSlider");
        musicVolumeSlider.setRound(false);
        table2.add(musicVolumeSlider).pad(5.0f).growX().align(Align.left).uniform();

        table2.row();
        label = new Label("sound volume", skin, "med-br");
        label.setAlignment(Align.center);
        label.setWrap(true);
        table2.add(label).growX().uniform();

        final Slider soundVolumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, skin, "default-horizontal");
        soundVolumeSlider.setName("soundVolumeSlider");
        soundVolumeSlider.setRound(false);
        table2.add(soundVolumeSlider).pad(5.0f).growX().align(Align.left).uniform();

        table2.row();
        final CheckBox soundEffectsCheckBox = new CheckBox( " Enable Sound Effects", skin);
        soundEffectsCheckBox.setName("soundEffectsEnabledCheckbox");
        table2.add(soundEffectsCheckBox).growX().align(Align.left).uniform();

        final CheckBox musicEnabledCheckBox = new CheckBox(" Enable Music", skin);
        musicEnabledCheckBox.setName("musicEnabledCheckbox");
        table2.add(musicEnabledCheckBox).expand().align(Align.left).uniform();
        table1.add(table2).pad(5.0f).expand();

        table1.row();
        TextButton backButton = new TextButton( "back", skin, "large-br-brd-shd-fr");
        table1.add(backButton).pad(5.0f).expand().uniform();
        stack.addActor(table1);
        table.add(stack);
        stage.addActor(table);

        // set values and add listeners
        musicVolumeSlider.setValue ( parent.getPreferences ().getMusicVolume () );
        musicVolumeSlider.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                parent.getPreferences ().setMusicVolume ( musicVolumeSlider.getValue () );
                parent.audioManager.updateMusicVolume ();
                return false;
            }
        } );

        soundVolumeSlider.setValue ( parent.getPreferences ().getSoundVolume () );
        soundVolumeSlider.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                parent.getPreferences ().setSoundVolume ( soundVolumeSlider.getValue () );
                parent.audioManager.updateSoundVolume ();
                return false;
            }
        } );

        musicEnabledCheckBox.setChecked ( parent.getPreferences ().isMusicEnabled () );
        musicEnabledCheckBox.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                boolean enabled = musicEnabledCheckBox.isChecked ();
                parent.getPreferences ().setMusicEnabled ( enabled );
                parent.audioManager.updateMusicOn ();
                return false;
            }
        } );

        soundEffectsCheckBox.setChecked ( parent.getPreferences ().isSoundEffectsEnabled () );
        soundEffectsCheckBox.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                boolean enabled = soundEffectsCheckBox.isChecked ();
                parent.getPreferences ().setSoundEffectsEnabled ( enabled );
                parent.audioManager.updateSoundEffectsOn ();
                return false;
            }
        } );

        backButton.addListener ( new ChangeListener () {
            @Override
            public void changed ( ChangeEvent event, Actor actor ) {
                parent.changeScreen ( JCGdxTestControls.MENU );
            }
        } );
    }

    @Override
    public void render ( float delta ) {

        Gdx.gl.glClearColor ( 0, 0, 0, 1 );
        Gdx.gl.glClear ( GL20.GL_COLOR_BUFFER_BIT );

        stage.act (delta);
        stage.draw ();

    }

    @Override
    public void resize ( int width, int height ) {
        Gdx.app.debug ( TAG, "resize" );
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
