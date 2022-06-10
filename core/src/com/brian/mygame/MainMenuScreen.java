package com.brian.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brian.mygame.connect4.Connect4Game;

public class MainMenuScreen implements Screen {

    private final MyGame game;
    Texture background;
    Stage stage;

    OrthographicCamera camera;

    public MainMenuScreen(final MyGame game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Connect4Assets/back.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0, 0.4f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 800, 590);
        game.font.draw(game.batch, "Welcome to My Game!!! ", 250, 250, 300, 5, false);
        game.font.draw(game.batch, "Tap anywhere to begin!", 250, 200, 300, 5, false);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new Connect4Game(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
