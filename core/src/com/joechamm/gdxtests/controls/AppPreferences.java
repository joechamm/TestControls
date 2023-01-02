package com.joechamm.gdxtests.controls;

/**
 * File:    AppPreferences
 * Package: com.joechamm.gdxtests.controls
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/1/2023 at 1:16 PM
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {

    public static final String TAG = AppPreferences.class.getName ();

    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "JCGdxTestControls";

    protected Preferences getPrefs () {
        Gdx.app.debug ( TAG, "getPrefs" );
        return Gdx.app.getPreferences ( PREFS_NAME );
    }

    public float getMusicVolume () {
        Gdx.app.debug ( TAG, "getMusicVolume" );
        return getPrefs ().getFloat ( PREF_MUSIC_VOLUME, 0.5f );
    }

    public void setMusicVolume ( float volume ) {
        Gdx.app.debug ( TAG, "setMusicVolume" );
        getPrefs ().putFloat ( PREF_MUSIC_VOLUME, volume );
        getPrefs ().flush ();
    }

    public boolean isSoundEffectsEnabled () {
        Gdx.app.debug ( TAG, "isSoundEffectsEnabled" );
        return getPrefs ().getBoolean ( PREF_SOUND_ENABLED, true );
    }

    public void setSoundEffectsEnabled ( boolean soundEffectsEnabled ) {
        Gdx.app.debug ( TAG, "setSoundEffectsEnabled" );
        getPrefs ().putBoolean ( PREF_SOUND_ENABLED, soundEffectsEnabled );
        getPrefs ().flush ();
    }

    public boolean isMusicEnabled () {
        Gdx.app.debug ( TAG, "isMusicEnabled" );
        return getPrefs ().getBoolean ( PREF_MUSIC_ENABLED, true );
    }

    public void setMusicEnabled ( boolean musicEnabled ) {
        Gdx.app.debug ( TAG, "setMusicEnabled" );
        getPrefs ().putBoolean ( PREF_MUSIC_ENABLED, musicEnabled );
        getPrefs ().flush ();
    }

    public float getSoundVolume () {
        Gdx.app.debug ( TAG, "getSoundVolume" );
        return getPrefs ().getFloat ( PREF_SOUND_VOL, 0.5f );
    }

    public void setSoundVolume ( float volume ) {
        Gdx.app.debug ( TAG, "setSoundVolume" );
        getPrefs ().putFloat ( PREF_SOUND_VOL, volume );
        getPrefs ().flush ();
    }
}
