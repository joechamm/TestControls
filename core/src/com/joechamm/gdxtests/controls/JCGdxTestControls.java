package com.joechamm.gdxtests.controls;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.joechamm.gdxtests.controls.asset.JCGdxAssetManager;
import com.joechamm.gdxtests.controls.audio.JCGdxAudioManager;
import com.joechamm.gdxtests.controls.view.LoadingScreen;
import com.joechamm.gdxtests.controls.view.MenuScreen;
import com.joechamm.gdxtests.controls.view.PreferencesScreen;

public class JCGdxTestControls extends Game {

	public static final String TAG = JCGdxTestControls.class.getName ();

	// game screens
	private MenuScreen menuScreen;
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;

	// game screen indice
	public static final int MENU = 0;
	public static final int PREFERENCES = 1;
	public static final int APPLICATION = 2;
	public static final int CREDITS = 3;

	// preferences
	private AppPreferences preferences;

	// asset manager
	public JCGdxAssetManager assetManager = new JCGdxAssetManager ();

	// audio manager
    public JCGdxAudioManager audioManager = null;

	public void changeScreen(int screen) {
		Gdx.app.debug ( TAG, "changeScreen" );

		switch ( screen ) {
			case MENU:
				Gdx.app.debug ( TAG, "MENU" );
				if (menuScreen == null) menuScreen = new MenuScreen (this);
				this.setScreen ( menuScreen );
				break;
			case PREFERENCES:
				Gdx.app.debug ( TAG, "PREFERENCES" );
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen ( this );
				this.setScreen ( preferencesScreen );
				// TODO
				break;
			case APPLICATION:
				Gdx.app.debug ( TAG, "APPLICATION" );
				// TODO
				break;
			case CREDITS:
				Gdx.app.debug ( TAG, "CREDITS" );
				// TODO
				break;
		}
	}

	@Override
	public void create () {
		Gdx.app.setLogLevel ( Application.LOG_DEBUG );
		Gdx.graphics.setWindowedMode ( 1920, 1080 );

		Gdx.app.debug ( TAG, "create" );

		preferences = new AppPreferences ();
		audioManager = JCGdxAudioManager.getInstance ( this );
		audioManager.initPrefs ();

		loadingScreen = new LoadingScreen ( this );
		setScreen ( loadingScreen );

//		menuScreen = new MenuScreen ( this );
//		setScreen ( menuScreen );
	}

	@Override
	public void dispose () {
		// TODO
		assetManager.manager.dispose ();
		audioManager.dispose ();
	}

	public AppPreferences getPreferences() {
		Gdx.app.debug ( TAG, "getPreferences" );
		return this.preferences;
	}
}
