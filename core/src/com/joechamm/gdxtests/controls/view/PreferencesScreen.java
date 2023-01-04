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
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
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

import java.awt.Checkbox;

public class PreferencesScreen implements Screen {

    public static final String TAG = PreferencesScreen.class.getName ();

    JCGdxTestControls parent;

    Stage stage;
    Skin skin;

    public PreferencesScreen ( JCGdxTestControls jcGdxTestControls ) {
        Gdx.app.debug ( TAG, "ctor" );
        parent = jcGdxTestControls;

        stage = new Stage ( new ScreenViewport () );
    }

    @Override
    public void show () {

  //      stage = new Stage ( new ScreenViewport () );
        Gdx.app.debug ( TAG, "show" );

        stage.clear ();
        Gdx.input.setInputProcessor ( stage );
        stage.setDebugAll ( true );

        skin = parent.assetManager.manager.get ( parent.assetManager.skinJson );

        Table table = new Table();
        table.setFillParent(true);

        Stack stack = new Stack();

        Image image = new Image(skin, "Starscape00");
  //      image.setTouchable(disabled);
        image.setScaling(Scaling.fill);
        stack.addActor(image);

        Table table1 = new Table();
        table1.setName("prefBaseTable");
   //     table1.setTouchable(disabled);
        table1.pad(5.0f);

        Label label = new Label("PREFERENCES", skin);
        label.setName("prefSceneLabel");
        label.setAlignment(Align.center);
        label.setColor(skin.getColor("CYAN"));
        table1.add(label).pad(5.0f).fill(true).uniform().colspan(3);

        table1.row();
        Table table2 = new Table();
        table2.setName("prefSliderTable");
 //       table2.setTouchable(enabled);

        label = new Label("Volume", skin);
        label.setAlignment(Align.center);
        table2.add(label).pad(5.0f).align(Align.top).colspan(3);

        table2.row();
        label = new Label("Sound Effects", skin);
        label.setAlignment(Align.center);
        table2.add(label).pad(5.0f).spaceRight(5.0f).align(Align.right);

        final Slider volumeSoundSlider = new Slider(0.0f, 1.0f, 0.1f, false, skin, "default-horizontal");
        volumeSoundSlider.setName("volumeSoundSlider");
        table2.add(volumeSoundSlider).pad(5.0f).colspan(2);

        table2.row();
        label = new Label("Music", skin);
        table2.add(label).pad(5.0f).spaceRight(5.0f).align(Align.right);

        final Slider volumeMusicSlider = new Slider(0.0f, 1.0f, 0.1f, false, skin, "default-horizontal");
        volumeMusicSlider.setName("volumeMusicSlider");
        table2.add(volumeMusicSlider).pad(5.0f).colspan(2);
        table1.add(table2).pad(5.0f).fill(true).uniform();

        table1.row();
        table2 = new Table();
        table2.setName("prefCheckboxTable");
 //       table2.setTouchable(enabled);

        label = new Label("Enable", skin);
        label.setName("enabledLabel");
        table2.add(label).pad(5.0f).uniform().colspan(2);

        table2.row();
        final CheckBox soundCheckBox = new CheckBox(" Sound Effects", skin);
        soundCheckBox.setName("soundCheckbox");
   //     soundCheckBox.setChecked(true);
        soundCheckBox.setColor(skin.getColor("WHITE"));
        table2.add(soundCheckBox).pad(5.0f).uniform();

        final CheckBox musicCheckbox = new CheckBox(" Music", skin);
        musicCheckbox.setName("musicCheckbox");
  //      musicCheckbox.setChecked(true);
        table2.add(musicCheckbox).pad(5.0f).uniform();
        table1.add(table2).pad(5.0f).fill(true).uniform();
        table1.row();

        // Add the game controls method table
        table2 = new Table ();
        table2.setName ( "prefRadioButtonTable" );

        label = new Label ( "Controls Method", skin );
        label.setName ( "controlsMethodLabel" );
        table2.add (label).pad ( 5.0f ).uniform ().align ( Align.top );

        table2.row ();

        // setup our radio buttons
        final CheckBox radioCheckboxControlsMethodTouch = new CheckBox ( "Touch", skin, "radio" );
        radioCheckboxControlsMethodTouch.setChecked ( true );
        radioCheckboxControlsMethodTouch.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "radioCheckboxControlsMethodTouch handle event" );
                if ( radioCheckboxControlsMethodTouch.isChecked () ) {
                    parent.getPreferences ().setControlsMethodTouch ();
                }
                return false;
            }
        } );

        final CheckBox radioCheckboxControlsMethodGamepad = new CheckBox ( "Gamepad", skin, "radio" );
        radioCheckboxControlsMethodGamepad.setChecked ( false );
        radioCheckboxControlsMethodGamepad.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "radioCheckboxControlsMethodGamepad handle event" );
                if ( radioCheckboxControlsMethodGamepad.isChecked () ) {
                    parent.getPreferences ().setControlsMethodGamepad ();
                }
                return false;
            }
        } );

        final ButtonGroup<CheckBox> controlsMethodButtonGroup = new ButtonGroup<>();
        controlsMethodButtonGroup.add ( radioCheckboxControlsMethodTouch );
        controlsMethodButtonGroup.add ( radioCheckboxControlsMethodGamepad );
        controlsMethodButtonGroup.setMinCheckCount ( 1 );
        controlsMethodButtonGroup.setMaxCheckCount ( 1 );

        table2.add ( radioCheckboxControlsMethodTouch ).pad ( 5.0f ).uniform ().align ( Align.left );
        table2.add ( radioCheckboxControlsMethodGamepad ).pad ( 5.0f ).uniform ().align ( Align.right );

        table1.add(table2).pad(5.0f).fill(true).uniform();
        table1.row();

        Container container = new Container();
        container.setName("backButtonContainer");

        final TextButton backButton = new TextButton("Back", skin);
        backButton.setName("backTextButton");
        container.setActor(backButton);
        table1.add(container).pad(5.0f).fill(true).uniform();
        stack.addActor(table1);
        table.add(stack);
        stage.addActor(table);

        // set values and add listeners
        volumeSoundSlider.setValue ( parent.getPreferences ().getSoundVolume () );
        volumeSoundSlider.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "volumeSoundSlider handle event" );
                parent.getPreferences ().setSoundVolume ( volumeSoundSlider.getValue () );
                parent.audioManager.updateSoundVolume ();
                return false;
            }
        } );

        volumeMusicSlider.setValue ( parent.getPreferences ().getMusicVolume () );
        volumeMusicSlider.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "volumeMusicSlider handle event" );
                parent.getPreferences ().setMusicVolume ( volumeMusicSlider.getValue () );
                parent.audioManager.updateMusicVolume ();
                return false;
            }
        } );

        soundCheckBox.setChecked ( parent.getPreferences ().isSoundEffectsEnabled () );
        soundCheckBox.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "soundCheckBox handle event" );
                boolean enabled = soundCheckBox.isChecked ();
                parent.getPreferences ().setSoundEffectsEnabled ( enabled );
                parent.audioManager.updateSoundEffectsOn ();
                return false;
            }
        } );

        musicCheckbox.setChecked ( parent.getPreferences ().isMusicEnabled () );
        musicCheckbox.addListener ( new EventListener () {
            @Override
            public boolean handle ( Event event ) {
                Gdx.app.debug ( TAG, "musicCheckbox handle event" );
                boolean enabled = musicCheckbox.isChecked ();
                parent.getPreferences ().setMusicEnabled ( enabled );
                parent.audioManager.updateMusicOn ();
                return false;
            }
        } );

        backButton.addListener ( new ChangeListener () {
            @Override
            public void changed ( ChangeEvent event, Actor actor ) {
                Gdx.app.debug ( TAG, "backButton handle event" );
                parent.changeScreen ( JCGdxTestControls.MENU );
            }
        } );


/*
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
        } );*/
    }

    @Override
    public void render ( float delta ) {

        Gdx.gl.glClearColor ( 0, 0, 0, 1 );
        Gdx.gl.glClear ( GL20.GL_COLOR_BUFFER_BIT );

//        stage.act (delta);
        stage.act ( Math.min ( Gdx.graphics.getDeltaTime (), 1 / 30f ) );
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
