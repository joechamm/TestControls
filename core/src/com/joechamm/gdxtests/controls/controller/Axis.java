package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;

/**
 * File:    Axis
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:45 PM
 */
public class Axis {

    public static final String TAG = Axis.class.getName ();

    public final int id;

    public final float threshold;

    public Axis(final int id) {
        this ( id, 0f );
    }

    public Axis ( final int id, final float threshold ) {
        Gdx.app.debug ( TAG, "id: " + id + " threshold: " + String.valueOf ( threshold ) );
        this.id = id;
        this.threshold = threshold;
    }
}
