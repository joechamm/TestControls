package com.joechamm.gdxtests.controls.view;

/**
 * File:    GameScreen
 * Package: com.joechamm.gdxtests.controls.view
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/3/2023 at 12:14 AM
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.joechamm.gdxtests.controls.EnemyShip;
import com.joechamm.gdxtests.controls.Explosion;
import com.joechamm.gdxtests.controls.JCGdxTestControls;
import com.joechamm.gdxtests.controls.Laser;
import com.joechamm.gdxtests.controls.PlayerShip;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;

public class GameScreen implements Screen {

    public static final String TAG = GameScreen.class.getName ();

    // orchestrator
    private JCGdxTestControls parent;

    // screen
    private Camera camera;
    private Viewport viewport;

    // graphics
    private SpriteBatch sb;
    private TextureAtlas atlas;
    private Texture explosionTexture;

    private TextureRegion[] backgrounds;
    private float backgroundHeight; // height of background in World units

    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion,
            enemyShipTextureRegion, enemyShieldtextureRegion,
            playerLaserTextureRegion, enemyLaserTextureRegion;

    // timing
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;
    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;

    // world parameters
    private final float WORLD_WIDTH = 72.0f;
    private final float WORLD_HEIGHT = 128.0f;
    private final float TOUCH_MOVEMENT_THRESHOLD = 0.5f;

    // game objects
    private PlayerShip playerShip;
    private LinkedList<EnemyShip> enemyShipList;

    private LinkedList<Laser> playerLaserList;
    private LinkedList<Laser> enemyLaserList;
    private LinkedList<Explosion> explosionList;

    private int score = 0;

    // Heads-Up Display
    BitmapFont font;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCenterX, hudRow1Y, hudRow2Y, hudSectionWidth;

    public GameScreen( JCGdxTestControls jcGdxTestControls ) {
        Gdx.app.debug ( TAG, "ctor" );

        this.parent = jcGdxTestControls;

        camera = new OrthographicCamera ();
        viewport = new StretchViewport ( WORLD_WIDTH, WORLD_HEIGHT, camera );

        // setup texture atlas
 //       atlas = new TextureAtlas ( "images.atlas" );
        atlas = parent.assetManager.manager.get ( parent.assetManager.gameImages, TextureAtlas.class );

        // setup background
        backgrounds = new TextureRegion[ 4 ];
        backgrounds[ 0 ] = atlas.findRegion ( "Starscape00" );
        backgrounds[ 1 ] = atlas.findRegion ( "Starscape01" );
        backgrounds[ 2 ] = atlas.findRegion ( "Starscape02" );
        backgrounds[ 3 ] = atlas.findRegion ( "Starscape03" );

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed = (float)(WORLD_HEIGHT / 4);

        // initialize texture regions
        playerShipTextureRegion = atlas.findRegion ( "playerShip2_blue" );
        enemyShipTextureRegion = atlas.findRegion ( "enemyRed4" );
        playerShieldTextureRegion = atlas.findRegion ( "shield2" );
        enemyShieldtextureRegion = atlas.findRegion ( "shield1" );
        enemyShieldtextureRegion.flip ( false, true );
        playerLaserTextureRegion = atlas.findRegion ( "laserBlue03" );
        enemyLaserTextureRegion = atlas.findRegion ( "laserRed03" );

 //       explosionTexture = new Texture ( "explosion.png" );
        explosionTexture = parent.assetManager.manager.get ( parent.assetManager.explosionImages, Texture.class );

        // setup game objects
        playerShip = new PlayerShip ( WORLD_WIDTH / 2, WORLD_HEIGHT / 4,
                                      10, 10,
                                      48, 3,
                                      0.4f, 4, 45, 0.5f,
                                      playerShipTextureRegion, playerShieldTextureRegion, playerLaserTextureRegion);

        enemyShipList = new LinkedList<> ();


        playerLaserList = new LinkedList<> ();
        enemyLaserList = new LinkedList<> ();
        explosionList = new LinkedList<> ();

        sb = new SpriteBatch ();

        prepareHUD();
    }

   /* private void prepareHUD () {
        // Create Bitmapfont from our font file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator ( Gdx.files.internal ( "EdgeOfTheGalaxyRegular-OVEa6.otf" ) );
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter ();
        fontParameter.size = 72;
        fontParameter.borderWidth = 3.6f;
        fontParameter.color = new Color ( 1, 1, 1, 0.3f );
        fontParameter.borderColor = new Color ( 0, 0, 0, 0.3f );

        font = fontGenerator.generateFont ( fontParameter );

        // Scale the font to fit world
        font.getData ().setScale ( 0.08f );

        // calculate HUD margins, etc...
        hudVerticalMargin = font.getCapHeight () / 2;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCenterX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2Y = hudRow1Y - hudVerticalMargin - font.getCapHeight ();
        hudSectionWidth = WORLD_WIDTH / 3;

    }*/

    private void prepareHUD() {
        font = parent.assetManager.manager.get ( parent.assetManager.fontRegTransparent72, BitmapFont.class );

        // Scale the font to fit world
        font.getData ().setScale ( 0.08f );

        // calculate HUD margins, etc...
        hudVerticalMargin = font.getCapHeight () / 2;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCenterX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2Y = hudRow1Y - hudVerticalMargin - font.getCapHeight ();
        hudSectionWidth = WORLD_WIDTH / 3;
    }

    @Override
    public void show () {

    }

    @Override
    public void render ( float delta ) {

        //       Gdx.gl.glClearColor ( 0, 0, 0, 1 );
        //       Gdx.gl.glClear ( GL20.GL_COLOR_BUFFER_BIT );

        sb.begin ();

        // scrolling background
        renderBackground ( delta );

        detectInput ( delta );
        playerShip.update ( delta );

        spawnEnemyShips ( delta );

        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator ();
        while ( enemyShipListIterator.hasNext () ) {
            EnemyShip enemyShip = enemyShipListIterator.next ();
            moveEnemy(enemyShip, delta);
            enemyShip.update ( delta );
            enemyShip.draw ( sb );
        }

        // player ships
        playerShip.draw ( sb );

        // lasers
        renderLasers ( delta );

        // detect collisions between lasers and ships
        detectCollisions();


        // explosions
        updateAndRenderExplosions ( delta);

        // hud rendering
        updateAndRenderHUD();

        sb.end ();
    }

    private void updateAndRenderHUD () {
        // render top row labels
        font.draw ( sb, "Score", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false );
        font.draw ( sb, "Shield", hudCenterX, hudRow1Y, hudSectionWidth, Align.center, false );
        font.draw ( sb, "Lives", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false );

        // render second row values
        font.draw ( sb, String.format ( Locale.getDefault (), "%06d", score ), hudLeftX, hudRow2Y, hudSectionWidth, Align.left, false );
        font.draw ( sb, String.format ( Locale.getDefault (), "%02d", playerShip.shield ), hudCenterX, hudRow2Y, hudSectionWidth, Align.center, false );
        font.draw ( sb, String.format ( Locale.getDefault (), "%02d", playerShip.lives ), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false );
    }

    private void spawnEnemyShips ( float delta ) {
        enemySpawnTimer += delta;

        if ( enemySpawnTimer > timeBetweenEnemySpawns ) {
            enemyShipList.add ( new EnemyShip ( JCGdxTestControls.random.nextFloat () * (WORLD_WIDTH - 10) + 5, WORLD_HEIGHT - 5,
                                                10, 10,
                                                48, 1,
                                                0.3f, 5, 50, 0.8f,
                                                enemyShipTextureRegion, enemyShieldtextureRegion, enemyLaserTextureRegion));

            enemySpawnTimer -= timeBetweenEnemySpawns;
        }


    }

    private void moveEnemy ( EnemyShip enemyShip, float delta ) {
        // strategy: determine the max distance the ship can move

        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = - enemyShip.boundingBox.x;
        downLimit = (float)WORLD_HEIGHT / 2 - enemyShip.boundingBox.y;
        rightLimit = WORLD_WIDTH - enemyShip.boundingBox.x - enemyShip.boundingBox.width;
        upLimit = WORLD_HEIGHT - enemyShip.boundingBox.y - enemyShip.boundingBox.height;

        float xMove = enemyShip.getDirectionVector ().x * enemyShip.movementSpeed * delta;
        float yMove = enemyShip.getDirectionVector ().y * enemyShip.movementSpeed * delta;

        if ( xMove > 0 ) {
            xMove = Math.min ( xMove, rightLimit );
        } else {
            xMove = Math.max ( xMove, leftLimit );
        }

        if ( yMove > 0 ) {
            yMove = Math.min ( yMove, upLimit );
        } else {
            yMove = Math.max ( yMove, downLimit );
        }

        enemyShip.translate ( xMove, yMove );
    }

    private void detectInput ( float delta ) {
        // keyboard input

        // strategy: determine the max distance the ship can move
        // check each key that matters and move accordingly

        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = - playerShip.boundingBox.x;
        downLimit = - playerShip.boundingBox.y;
        rightLimit = WORLD_WIDTH - playerShip.boundingBox.x - playerShip.boundingBox.width;
        upLimit = (float)WORLD_HEIGHT / 2 - playerShip.boundingBox.y - playerShip.boundingBox.height;

        if ( Gdx.input.isKeyPressed ( Input.Keys.RIGHT ) && rightLimit > 0) {
            playerShip.translate ( Math.min ( playerShip.movementSpeed * delta, rightLimit ), 0f );
        }
        if ( Gdx.input.isKeyPressed ( Input.Keys.UP ) && upLimit > 0) {
            playerShip.translate ( 0f , Math.min ( playerShip.movementSpeed * delta, upLimit ) );
        }

        if ( Gdx.input.isKeyPressed ( Input.Keys.LEFT ) && leftLimit < 0) {
            playerShip.translate ( Math.max ( - playerShip.movementSpeed * delta, leftLimit ), 0f );
        }
        if ( Gdx.input.isKeyPressed ( Input.Keys.DOWN ) && downLimit < 0) {
            playerShip.translate (0f,  Math.max ( - playerShip.movementSpeed * delta, leftLimit ));
        }

        // touch input (also mouse)
        if ( Gdx.input.isTouched () ) {
            // get the screen position of the touch
            float xTouchPixels = Gdx.input.getX ();
            float yTouchPixels = Gdx.input.getY ();

            // convert to world position
            Vector2 touchPoint = new Vector2 ( xTouchPixels, yTouchPixels );
            touchPoint = viewport.unproject ( touchPoint );

            // calculate the x and y differences
            Vector2 playerShipCenter = new Vector2 ( playerShip.boundingBox.x + playerShip.boundingBox.width / 2,
                                                     playerShip.boundingBox.y + playerShip.boundingBox.height / 2 );

            float touchDistance = touchPoint.dst ( playerShipCenter );

            if ( touchDistance > TOUCH_MOVEMENT_THRESHOLD ) {
                float xTouchDifference = touchPoint.x - playerShipCenter.x;
                float yTouchDifference = touchPoint.y - playerShipCenter.y;

                // scale to the maximum speed of the ship
                float xMove = (xTouchDifference / touchDistance) * playerShip.movementSpeed * delta;
                float yMove = (yTouchDifference / touchDistance) * playerShip.movementSpeed * delta;

                if ( xMove > 0 ) {
                    xMove = Math.min ( xMove, rightLimit );
                } else {
                    xMove = Math.max ( xMove, leftLimit );
                }

                if ( yMove > 0 ) {
                    yMove = Math.min ( yMove, upLimit );
                } else {
                    yMove = Math.max ( yMove, downLimit );
                }

                playerShip.translate ( xMove, yMove );
            }

        }


    }

    private void detectCollisions () {
        // for each player laser, check whether it intersects an enemy ship
        ListIterator<Laser> laserListIterator = playerLaserList.listIterator ();
        while ( laserListIterator.hasNext () ) {
            Laser laser = laserListIterator.next ();
            ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator ();
            while ( enemyShipListIterator.hasNext () ) {
                EnemyShip enemyShip = enemyShipListIterator.next ();
                if ( enemyShip.intersects ( laser.boundingBox  ) ) {
                    // contact with enemy ship
                    if ( enemyShip.hitAndCheckDestroyed ( laser) ) {
                        enemyShipListIterator.remove ();
                        explosionList.add (
                                new Explosion ( explosionTexture,
                                                new Rectangle ( enemyShip.boundingBox ),
                                                0.7f ) );
                        score += 100;
                    }
                    laserListIterator.remove ();
                    break;
                }
            }
        }

        // for each enemy laser, check whether it intersects a player ship
        laserListIterator = enemyLaserList.listIterator ();
        while ( laserListIterator.hasNext () ) {
            Laser laser = laserListIterator.next ();
            if ( playerShip.intersects ( laser.boundingBox ) ) {
                // contact with enemy ship
                if ( playerShip.hitAndCheckDestroyed ( laser) ) {
                    explosionList.add ( new Explosion (
                            explosionTexture,
                            new Rectangle ( playerShip.boundingBox ),
                            1.6f
                    ) );

                    playerShip.shield = 10;
                    playerShip.lives--;
                }
                laserListIterator.remove ();
            }
        }
    }

    private void updateAndRenderExplosions ( float delta ) {
        ListIterator<Explosion> explosionListIterator = explosionList.listIterator ();
        while ( explosionListIterator.hasNext () ) {
            Explosion explosion = explosionListIterator.next ();
            explosion.update ( delta );
            if ( explosion.isFinished () ) {
                explosionListIterator.remove ();
            } else {
                explosion.draw ( sb );
            }
        }
    }

    private void renderLasers(float delta) {
        // create new lasers
        // player lasers
        if ( playerShip.canFireLaser () ) {
            Laser[] lasers = playerShip.fireLasers ();
            for ( Laser laser : lasers ) {
                playerLaserList.add ( laser );
            }
        }
        // enemy lasers
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator ();
        while ( enemyShipListIterator.hasNext () ) {
            EnemyShip enemyShip = enemyShipListIterator.next ();
            if ( enemyShip.canFireLaser () ) {
                Laser[] lasers = enemyShip.fireLasers ();
                for ( Laser laser : lasers ) {
                    enemyLaserList.add ( laser );
                }
            }
        }

        // draw lasers
        // remove old lasers
        ListIterator<Laser> iterator = playerLaserList.listIterator ();
        while ( iterator.hasNext () ) {
            Laser laser = iterator.next ();
            laser.draw ( sb );
            laser.boundingBox.y += laser.movementSpeed * delta;
            if ( laser.boundingBox.y > WORLD_HEIGHT ) {
                iterator.remove ();
            }
        }
        iterator = enemyLaserList.listIterator ();
        while ( iterator.hasNext () ) {
            Laser laser = iterator.next ();
            laser.draw ( sb );
            laser.boundingBox.y -= laser.movementSpeed * delta;
            if ( laser.boundingBox.y + laser.boundingBox.height < 0 ) {
                iterator.remove ();
            }
        }
    }

    private void renderBackground(float delta) {
        backgroundOffsets[0] += delta * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += delta * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += delta * backgroundMaxScrollingSpeed / 2;
        backgroundOffsets[3] += delta * backgroundMaxScrollingSpeed;

        for ( int layer = 0; layer < backgroundOffsets.length; layer++ ) {
            if(backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            sb.draw ( backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, backgroundHeight );
            //          sb.draw ( backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT );
        }

    }

    @Override
    public void resize ( int width, int height ) {
        viewport.update ( width, height, true );
        sb.setProjectionMatrix ( camera.combined );
    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {

    }

}
