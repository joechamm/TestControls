package com.joechamm.gdxtests.controls.controller;

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

    private ObjectMap<V, Long> lastPressed = new ObjectMap<> ();

    protected ButtonMapper<V> buttonMapper;

    protected AxisMapper<V> axisMapper;

    public abstract boolean isPressed(V control);

    public abstract float getAxis(V control);

    public long when(V control) {
        return lastPressed.get ( control, 0L );
    }

    public void record(V control) {
        lastPressed.put ( control, TimeUtils.millis () );
    }
}
