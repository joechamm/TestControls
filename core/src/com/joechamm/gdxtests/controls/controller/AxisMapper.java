package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * File:    AxisMapper
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:41 PM
 */
public class AxisMapper<V> {

    protected final ObjectMap<V, Axis> mapping = new ObjectMap<> ();

    public AxisMapper(){}

    public void setMapping(V control, Axis mapping) {
        this.mapping.put ( control, mapping );
    }

    public Axis get(V control) {
        return mapping.get ( control, null );
    }
}
