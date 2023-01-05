package com.joechamm.gdxtests.controls;

import com.badlogic.gdx.math.Rectangle;

/**
 * File:    Constants
 * Package: com.joechamm.gdxtests.controls
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/4/2023 at 6:29 PM
 */
public class Constants {

    public static final String TAG = Constants.class.getName ();

    public static final float WORLD_WIDTH = 72.0f;
    public static final float WORLD_HEIGHT = 128.0f;
    public static final float TOUCH_MOVEMENT_THRESHOLD = 0.5f;

    public static final int VIEWPORT_WIDTH = 800;
    public static final int VIEWPORT_HEIGHT = 600;

    public static final int CONTROLS_WIDTH = VIEWPORT_WIDTH;
    public static final int CONTROLS_HEIGHT = 200;

    public static final int VIEWPORT_CONTROLS_WIDTH = Math.max ( VIEWPORT_WIDTH, CONTROLS_WIDTH);
    public static final int VIEWPORT_CONTROLS_HEIGHT = VIEWPORT_HEIGHT + CONTROLS_HEIGHT;

}
