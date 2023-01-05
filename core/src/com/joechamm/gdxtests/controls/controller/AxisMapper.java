package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;

import javax.tools.JavaCompiler;

/**
 * File:    AxisMapper
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:41 PM
 */
public class AxisMapper<V> {

    public static final String TAG = AxisMapper.class.getName ();

    protected final ObjectMap<V, Axis> mapping = new ObjectMap<> ();

    public AxisMapper(){
        Gdx.app.debug ( TAG, "ctor");
    }

    public void setMapping(V control, Axis mapping) {
        Gdx.app.debug ( TAG, "setMapping ( control: " + control.toString () + " , mapping: " + mapping.toString () + " )" );
        this.mapping.put ( control, mapping );
    }

    public Axis get(V control) {
        Gdx.app.debug ( TAG, "get ( control: " + control.toString () + " )" );
        return mapping.get ( control, null );
    }
}
