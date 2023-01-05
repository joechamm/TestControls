package com.joechamm.gdxtests.controls.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * File:    ButtonMapper
 * Package: com.joechamm.gdxtests.controls.controller
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 9:39 PM
 */
public class ButtonMapper<V extends Controls> {

    public static final String TAG = ButtonMapper.class.getName ();

    private final ObjectMap<V, Integer> mapping = new ObjectMap<> ();

    public ButtonMapper(){
        Gdx.app.debug ( TAG, "ctor" );
    }

    public void setMapping(V control, Integer mapping) {
        Gdx.app.debug ( TAG, "setMapping ( control: " + control.toString () + " , mapping: " + mapping.toString () + " )" );
        this.mapping.put ( control, mapping );
    }

    public final Integer get(V control) {
        Gdx.app.debug ( TAG, "get ( control: " + control.toString () + " )" );
        return mapping.get ( control, - 1 );
    }

}
