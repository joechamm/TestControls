package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
/**
 * File:    ControllerFactory
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 10:19 PM
 */
public class ControllerFactory {

    public static final String TAG = ControllerFactory.class.getName ();

    public static KeyboardController<GameControls> buildKeyboardController() {

        Gdx.app.debug ( TAG, "buildKeyboardController" );

        final ButtonMapper<GameControls> buttonMapper = new ButtonMapper<> ();
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_UP, Input.Keys.UP);
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_DOWN, Input.Keys.DOWN );
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_LEFT, Input.Keys.LEFT );
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_RIGHT, Input.Keys.RIGHT );

        buttonMapper.setMapping ( GameControls.BUTTON_START, Input.Keys.ENTER );
        buttonMapper.setMapping ( GameControls.BUTTON_BACK, Input.Keys.BACKSPACE );

        buttonMapper.setMapping ( GameControls.BUTTON_A, Input.Keys.Z );
        buttonMapper.setMapping ( GameControls.BUTTON_B, Input.Keys.X );
        buttonMapper.setMapping ( GameControls.BUTTON_X, Input.Keys.A );
        buttonMapper.setMapping ( GameControls.BUTTON_Y, Input.Keys.S );

        buttonMapper.setMapping ( GameControls.BUTTON_L1, Input.Keys.Q );
        buttonMapper.setMapping ( GameControls.BUTTON_L2, Input.Keys.W );
        buttonMapper.setMapping ( GameControls.BUTTON_R1, Input.Keys.C );
        buttonMapper.setMapping ( GameControls.BUTTON_R2, Input.Keys.D );

        buttonMapper.setMapping ( GameControls.BUTTON_LEFT_STICK, Input.Keys.COMMA );
        buttonMapper.setMapping ( GameControls.BUTTON_RIGHT_STICK, Input.Keys.PERIOD );

        return new KeyboardController<> ( buttonMapper );
    }

    public static LogitechController<GameControls> buildLogitechController() {
        Gdx.app.debug ( TAG, "buildLogitechController" );

        final ButtonMapper<GameControls> buttonMapper = new ButtonMapper<> ();

        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_UP, 11);
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_DOWN, 12 );
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_LEFT, 13);
        buttonMapper.setMapping ( GameControls.BUTTON_DPAD_RIGHT, 14 );

        buttonMapper.setMapping ( GameControls.BUTTON_START, 6 );
        buttonMapper.setMapping ( GameControls.BUTTON_BACK, 4 );

        buttonMapper.setMapping ( GameControls.BUTTON_A, 0 );
        buttonMapper.setMapping ( GameControls.BUTTON_B, 1 );
        buttonMapper.setMapping ( GameControls.BUTTON_X, 2 );
        buttonMapper.setMapping ( GameControls.BUTTON_Y, 3 );

        buttonMapper.setMapping ( GameControls.BUTTON_L1, 9 );
        buttonMapper.setMapping ( GameControls.BUTTON_R1, 10 );

        buttonMapper.setMapping ( GameControls.BUTTON_LEFT_STICK, 7 );
        buttonMapper.setMapping ( GameControls.BUTTON_RIGHT_STICK, 8 );

        final AxisMapper<GameControls> axisMapper = new AxisMapper<> ();
        axisMapper.setMapping ( GameControls.AXIS_LEFT_X, new Axis ( 0 ));
        axisMapper.setMapping ( GameControls.AXIS_LEFT_Y, new Axis ( 1 ) );
        axisMapper.setMapping ( GameControls.AXIS_RIGHT_X, new Axis ( 2 ) );
        axisMapper.setMapping ( GameControls.AXIS_RIGHT_Y, new Axis ( 3 ) );
        axisMapper.setMapping ( GameControls.AXIS_TRIGGER_LEFT, new Axis ( 4, -0.75f ) );
        axisMapper.setMapping ( GameControls.AXIS_TRIGGER_RIGHT, new Axis ( 5, 0.75f ) );

        return new LogitechController<> ( 0, buttonMapper, axisMapper );
    }

    public static MultiplexedController<GameControls> buildMultiplexedController() {
        Gdx.app.debug ( TAG, "buildMultiplexedController" );

        return new MultiplexedController<> ( buildKeyboardController (), buildLogitechController () );
    }
}
