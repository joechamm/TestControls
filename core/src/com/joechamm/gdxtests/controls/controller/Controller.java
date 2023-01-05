package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * File:    Controller
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:53 PM
 */
public abstract class Controller<V extends Controls> {

    public static final String TAG = Controller.class.getName ();

    private ObjectMap<V, Long> lastPressed = new ObjectMap<> ();

    protected ButtonMapper<V> buttonMapper;

    protected AxisMapper<V> axisMapper;

    public abstract boolean isPressed(V control);

    public abstract float getAxis(V control);

    public long when(V control) {
        Gdx.app.debug ( TAG, "when ( control: " + control.toString () + " )" );
        return lastPressed.get ( control, 0L );
    }

    public void record(V control) {
        Gdx.app.debug ( TAG, "record ( control: " + control.toString () + " )" );
        lastPressed.put ( control, TimeUtils.millis () );
    }
}
