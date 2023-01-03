package com.joechamm.gdxtests.controls.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * File:    JCGdxAssetManager
 * Package: com.joechamm.gdxtests.controls.asset
 * Project: TestControls
 *
 * @author joechamm
 * Created  1/1/2023 at 3:20 PM
 */
public class JCGdxAssetManager {
    private static final String TAG = JCGdxAssetManager.class.getName ();

    public final AssetManager manager = new AssetManager ();

    // Textures
    public final String gameImages = "images/game.atlas";
//    public final String loadingImages = "images/loading.atlas";

    // Sounds
    public final String laserSound01 = "sounds/sfx_laser1.ogg";
    public final String laserSound02 = "sounds/sfx_laser2.ogg";
    public final String loseSound = "sounds/sfx_lose.ogg";
    public final String shieldDownSound = "sounds/sfx_shieldDown.ogg";
    public final String shieldUpSound = "sounds/sfx_shieldUp.ogg";
    public final String twoToneSound = "sounds/sfx_twoTone.ogg";
    public final String zapSound = "sounds/sfx_zap.ogg";

    // Music
    public final String playingSong = "music/music_rolemusic.mp3";

    // Skin
    public final String skinJson = "skin/controls-clean-crispy.json";
    public final String skinAtlas = "skin/controls-clean-crispy.atlas";

    // Particle Effects
//    public final String smokeEffect = "particles/smoke.pe";
//    public final String waterEffect = "particles/water.pe";
//    public final String fireEffect = "particles/fire.pe";

    public void queueAddImages () {
        manager.load ( gameImages, TextureAtlas.class );
    }

//    public void queueAddLoadingImages () {
//       manager.load ( loadingImages, TextureAtlas.class );
//    }

    public void queueAddSounds () {
        manager.load ( laserSound01, Sound.class );
        manager.load ( laserSound02, Sound.class );
        manager.load ( loseSound, Sound.class );
        manager.load ( shieldUpSound, Sound.class );
        manager.load ( shieldDownSound, Sound.class );
        manager.load ( twoToneSound, Sound.class );
        manager.load ( zapSound, Sound.class );
    }

    public void queueAddMusic () {
        manager.load ( playingSong, Music.class );
    }

    public void queueAddSkin () {
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter ( skinAtlas );
        manager.load ( skinJson, Skin.class, params );
    }

    public void queueAddFonts () {
        // TODO
    }

    public void queueAddParticleEffects () {

//        ParticleEffectLoader.ParticleEffectParameter pep = new ParticleEffectLoader.ParticleEffectParameter ();
//        pep.atlasFile = "images/game.atlas";
//        manager.load ( smokeEffect, ParticleEffect.class, pep );
//        manager.load ( waterEffect, ParticleEffect.class, pep );
//        manager.load ( fireEffect, ParticleEffect.class, pep );
    }
}
