package com.joechamm.gdxtests.controls.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.joechamm.gdxtests.controls.Constants;
import com.joechamm.gdxtests.controls.JCGdxTestControls;
import com.sun.org.apache.bcel.internal.Const;

/**
 * File:    GameTouchScreen
 * Package: com.joechamm.gdxtests.controls.view
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/4/2023 at 7:29 PM
 */
public class GameTouchScreen extends BaseTouchScreen{
    public static final String TAG = GameTouchScreen.class.getName ();

    private Label playerLabel;

    private Touchpad touchpad;

    public GameTouchScreen ( JCGdxTestControls jcGdxTestControls ) {
        super ( jcGdxTestControls );
    }

    @Override
    public void initialize () {
        // TODO: ...

        // user interface code
        // playerLabel = new Label ( "Player", getParent ().assetManager.manager.get ( getParent ().assetManager.skinJson, Skin.class ) );

        // TODO:...

        Gdx.graphics.setWindowedMode ( Constants.VIEWPORT_CONTROLS_WIDTH, Constants.VIEWPORT_CONTROLS_HEIGHT );
        initializeControlArea ();

        // TODO:...

        // on-screen touchpad
        // TODO: TouchpadStyle
        // deadZoneRadius measured in pixels
        touchpad = new Touchpad ( 5f, getParent ().assetManager.manager.get ( getParent ().assetManager.skinJson, Skin.class ) );

        controlTable.toFront ();
        controlTable.pad ( 50 );
        controlTable.add ().colspan ( 3 ).height ( (float)Constants.VIEWPORT_HEIGHT );
        controlTable.row ();
        controlTable.add (touchpad);
        controlTable.add ().expandX();
        // TODO: add other buttons
    }

    @Override
    public void update ( float dt ) {
        Vector2 direction = new Vector2 ( touchpad.getKnobPercentX (), touchpad.getKnobPercentY () );
        // TODO: ...

    }
}
