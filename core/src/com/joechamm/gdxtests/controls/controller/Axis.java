package com.joechamm.gdxtests.controls.controller;

/**
 * File:    Axis
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:45 PM
 */
public class Axis {

    public final int id;

    public final float threshold;

    public Axis(final int id) {
        this ( id, 0f );
    }

    public Axis ( final int id, final float threshold ) {
        this.id = id;
        this.threshold = threshold;
    }
}
