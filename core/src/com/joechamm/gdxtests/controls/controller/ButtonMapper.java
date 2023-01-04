package com.joechamm.gdxtests.controls.controller;

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

    private final ObjectMap<V, Integer> mapping = new ObjectMap<> ();

    public ButtonMapper(){
    }

    public void setMapping(V control, Integer mapping) {
        this.mapping.put ( control, mapping );
    }

    public final Integer get(V control) {
        return mapping.get ( control, - 1 );
    }

}
