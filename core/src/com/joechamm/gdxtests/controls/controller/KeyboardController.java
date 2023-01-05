package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;

/**
 * File:    KeyboardController
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:55 PM
 */
public class KeyboardController<V extends Controls> extends Controller<V> {

    public static final String TAG = KeyboardController.class.getName ();

    public KeyboardController(final ButtonMapper<V> buttonMapper) {
        Gdx.app.debug ( TAG, "ctor; buttonMapper: " + buttonMapper.toString () );
        super.buttonMapper = buttonMapper;
    }

    @Override
    public boolean isPressed(V control) {
        Gdx.app.debug ( TAG, "isPressed ( control: " + control.toString () + " )" );
        final boolean pressed = Gdx.input.isKeyPressed ( buttonMapper.get ( control ) );
        if ( pressed ) {
            record ( control );
        }
        return pressed;
    }

    @Override
    public float getAxis ( V control ) {
        Gdx.app.debug ( TAG, "getAxis ( control: " + control.toString () + " )" );
        return 0;
    }
}
