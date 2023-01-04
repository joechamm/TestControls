package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.controllers.Controllers;

/**
 * File:    LogitechController
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:58 PM
 */
public class LogitechController<V extends Controls> extends Controller<V> {

    private final com.badlogic.gdx.controllers.Controller controller;

    public LogitechController(final int controllerNumber,
                              final ButtonMapper<V> buttonMapper,
                              final AxisMapper<V> axisMapper) {
        super.buttonMapper = buttonMapper;
        super.axisMapper = axisMapper;

        if( Controllers.getControllers ().size < (controllerNumber + 1)) {
            controller = null;
            return;
        }

        controller = Controllers.getControllers ().get ( controllerNumber );
    }

    @Override
    public boolean isPressed(V control) {
        if(null == controller) return false;

        boolean pressed = false;
        if ( null != axisMapper.get ( control ) ) {
            final Axis axis = this.axisMapper.get ( control );
            if ( axis.threshold < 0 ) {
                pressed = controller.getAxis ( axis.id ) < axis.threshold;
            }
            if ( axis.threshold > 0 ) {
                pressed = controller.getAxis ( axis.id ) > axis.threshold;
            }
        } else {
            pressed = controller.getButton ( buttonMapper.get ( control ) );
        }

        if ( pressed ) {
            record ( control );
        }

        return pressed;
    }

    @Override
    public float getAxis ( V control ) {
        final Axis axis = this.axisMapper.get ( control );
        if ( null == axis ) {
            return 0f;
        }

        final float axisValue = controller.getAxis ( axis.id );
        if ( Math.abs ( axisValue ) > axis.threshold ) {
            return axisValue;
        }
        return 0f;
    }
}
