package com.joechamm.gdxtests.controls.controller;

/**
 * File:    MultiplexedController
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 10:06 PM
 */
public class MultiplexedController<V extends Controls> extends Controller<V> {

    private final Controller[] controllers;

    public MultiplexedController(final Controller... controllers) {
        this.controllers = controllers;
    }

    @Override
    public boolean isPressed(final V control) {
        for ( Controller controller : controllers ) {
            if ( controller.isPressed ( control ) ) {
                return true;
            }
        }

        return false;
    }

    @Override
    public float getAxis ( V control ) {
        for ( final Controller controller : controllers ) {
            final float axis = controller.getAxis ( control );
            if ( 0.0f != axis ) {
                return axis;
            }
        }
        return 0.0f;
    }

    @Override
    public long when(final V control) {
        for ( final Controller controller : controllers ) {
            final long when = controller.when ( control );
            if ( when > 0 ) {
                return when;
            }
        }
        return 0L;
    }
}
