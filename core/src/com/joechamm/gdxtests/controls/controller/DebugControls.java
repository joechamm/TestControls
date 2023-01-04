package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerMapping;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.StringBuilder;
import com.joechamm.gdxtests.controls.JCGdxTestControls;

/**
 * File:    DebugControls
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 5:01 PM
 */
public class DebugControls {
    private static final String TAG = DebugControls.class.getName ();

    // SINGLETON
    private static DebugControls thisInstance;

    // Access to parent
    private JCGdxTestControls parent;

    private DebugControls ( JCGdxTestControls jcGdxTestControls ) {
        Gdx.app.debug ( TAG, "ctor" );
        parent = jcGdxTestControls;
    }

    public static DebugControls getInstance ( JCGdxTestControls jcGdxTestControls ) {
        if ( thisInstance == null ) {
            thisInstance = new DebugControls ( jcGdxTestControls );
        } else {
            thisInstance.parent = jcGdxTestControls;
        }

        return thisInstance;
    }

    public static void logAvailablePeripherals() {
        boolean isHardwareKeyboardAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.HardwareKeyboard );
        boolean isOnscreenKeyboardAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.OnscreenKeyboard );
        boolean isMultitouchScreenAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.MultitouchScreen );
        boolean isAccelerometerAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.Accelerometer );
        boolean isCompassAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.Compass );
        boolean isVibratorAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.Vibrator );
        boolean isGyroscopeAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.Gyroscope );
        boolean isRotationVectorAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.RotationVector );
        boolean isPressureAvailable = Gdx.input.isPeripheralAvailable ( Input.Peripheral.Pressure );

        Gdx.app.log ( TAG, "HardwareKeyboard: " + (isHardwareKeyboardAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "OnscreenKeyboard: " + (isOnscreenKeyboardAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "MultitouchScreen: " + (isMultitouchScreenAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "Accelerometer: " + (isAccelerometerAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "Compass: " + (isCompassAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "Vibrator: " + (isVibratorAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "Gyroscope: " + (isGyroscopeAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "RotationVector: " + (isRotationVectorAvailable ? "AVAILABLE" : "NOT AVAILABLE") );
        Gdx.app.log ( TAG, "Pressure: " + (isPressureAvailable ? "AVAILABLE" : "NOT AVAILABLE") );

    }

    public static void logAvailableControllers() {
        int idx = 0;
        StringBuilder stringBuilder = new StringBuilder ();
        for( Controller  controller : Controllers.getControllers ()) {
            String name = controller.getName ();
            String uniqueId = controller.getUniqueId ();
            boolean supportsPlayerIdx = controller.supportsPlayerIndex ();
            int axisCount = controller.getAxisCount ();
            int maxButtonIndex = controller.getMaxButtonIndex ();
            int minButtonIndex = controller.getMinButtonIndex ();
            ControllerMapping mapping = controller.getMapping ();
            stringBuilder.append ( "Controller at index " )
                   .append ( idx++ )
                   .appendLine ( " found!" )
                   .append ( "  Name: " )
                   .appendLine ( name )
                   .append ( "  Unique Id: " )
                   .appendLine ( uniqueId )
                   .append ( "  Player Index: " )
                   .appendLine ( ( supportsPlayerIdx ? String.valueOf ( controller.getPlayerIndex () ) : "N/A" ) )
                   .append ( "  Axis Count: " )
                   .appendLine ( String.valueOf ( axisCount ) )
                   .append ( "  Button Range: " )
                   .append ( String.valueOf ( minButtonIndex ) )
                   .append ( " to " )
                   .appendLine ( String.valueOf ( maxButtonIndex ) )
                   .appendLine ( "  Mapping:" )
                   .append ( "    A: " )
                   .append ( String.valueOf ( mapping.buttonA ) )
                   .append ( ", B: " )
                   .append ( String.valueOf ( mapping.buttonB ) )
                   .append ( ", X: " )
                   .append ( String.valueOf ( mapping.buttonX ) )
                   .append ( ", Y :" )
                   .appendLine ( String.valueOf ( mapping.buttonY ) )
                   .append ( "    D-Pad UP: " )
                   .append ( String.valueOf ( mapping.buttonDpadUp ))
                   .append ( ", D-Pad DOWN: " )
                   .append ( String.valueOf ( mapping.buttonDpadDown ))
                   .append ( ", D-Pad RIGHT: " )
                   .append ( String.valueOf ( mapping.buttonDpadRight ))
                   .append ( ", D-Pad LEFT: " )
                   .appendLine ( String.valueOf ( mapping.buttonDpadLeft ))
                   .append ( "    L1: " )
                   .append ( String.valueOf ( mapping.buttonL1 ))
                   .append ( ", L2: " )
                   .append ( String.valueOf ( mapping.buttonL2 ))
                   .append ( ", R1: " )
                   .append ( String.valueOf ( mapping.buttonR1 ))
                   .append ( ", R2: " )
                   .appendLine ( String.valueOf ( mapping.buttonR2 ))
                   .append ( "    Left-Stick Button: " )
                   .append ( String.valueOf ( mapping.buttonLeftStick ))
                   .append ( ", Right-Stick Button: " )
                   .append ( String.valueOf ( mapping.buttonRightStick ))
                   .append ( ", Back Button: " )
                   .append ( String.valueOf ( mapping.buttonBack ))
                   .append ( ", Start Button: " )
                   .appendLine ( String.valueOf ( mapping.buttonStart ))
                   .append ( "    Axis-Left X: " )
                   .append ( String.valueOf ( mapping.axisLeftX ))
                   .append ( ", Axis-Left Y: " )
                   .appendLine ( String.valueOf ( mapping.axisLeftY ) )
                   .append ( "    Axis-Right X: " )
                   .append ( String.valueOf ( mapping.axisRightX ))
                   .append ( ", Axis-Right Y: " )
                   .appendLine ( String.valueOf ( mapping.axisRightY ) );

            Gdx.app.log ( TAG, stringBuilder.toStringAndClear () );
        }
    }
}
