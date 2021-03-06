package com.brian.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brian.mygame.connect4.Connect4Game;

public class GameEndScreen implements Screen {

    private final MyGame game;
    Stage stage;

    OrthographicCamera camera;

    public GameEndScreen(final MyGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "YOU WON THE GAME!!! ", 250, 250, 300, 5, false);
        game.font.draw(game.batch, "Press SPACE to restart the match!", 250, 200, 300, 5, false);
        game.batch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
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
