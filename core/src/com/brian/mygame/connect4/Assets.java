package com.brian.mygame.connect4;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static final AssetManager manager = new AssetManager();

    public static final String SFX_TXT = "assets/Connect4Assets/sfxButton.png";
    public static final String API_TXT = "assets/Connect4Assets/apiButton.png";
    public static final String GAME_TXT = "assets/Connect4Assets/gameButton.png";
    public static final String BTN_DOWN_TXT = "assets/Connect4Assets/buttonDown.png";
    public static final String MENU_TXT = "assets/Connect4Assets/menu.png";
    public static final String RESET_TXT = "assets/Connect4Assets/reset.png";
    public static final String AI_TXT = "assets/Connect4Assets/AIGame.png";
    public static final String SQUARE_TXT = "assets/Connect4Assets/square.png";
    public static final String RED_TXT = "assets/Connect4Assets/red.png";
    public static final String YELLOW_TXT = "assets/Connect4Assets/yellow.png";
    public static final String CLICK_SOUND = "assets/Connect4Assets/button_click.wav";
    public static final String FONT = "assets/Connect4Assets/font.fnt";

    public static void load() {
        manager.load(SFX_TXT, Texture.class);
        manager.load(API_TXT, Texture.class);
        manager.load(GAME_TXT, Texture.class);
        manager.load(AI_TXT, Texture.class);
        manager.load(BTN_DOWN_TXT, Texture.class);
        manager.load(MENU_TXT, Texture.class);
        manager.load(RESET_TXT, Texture.class);
        manager.load(SQUARE_TXT, Texture.class);
        manager.load(RED_TXT, Texture.class);
        manager.load(YELLOW_TXT, Texture.class);
        manager.load(CLICK_SOUND, Sound.class);
        manager.load(FONT, BitmapFont.class);
    }

    public static void dispose() {
        manager.dispose();
    }

}