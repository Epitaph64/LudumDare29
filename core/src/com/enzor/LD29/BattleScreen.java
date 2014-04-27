package com.enzor.LD29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enzor.LD29.controller.Controller;

public class BattleScreen implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private LudumDare29 game;

	public BattleScreen(LudumDare29 ludumDare29) {
		this.game = ludumDare29;
	}

	@Override
	public void show() {

		// Create SpriteBatch and load necessary textures
		batch = new SpriteBatch();

		// Setup camera with 2X zoom
		camera = new OrthographicCamera(160, 100);
		camera.translate(80, 50);
		camera.update();

		// Override the default input processor to get one-off key pushed events
		Gdx.input.setInputProcessor(new Controller() {
			@Override
			public boolean keyDown(int keyCode) {
				switch (keyCode) {
				case Input.Keys.NUM_2:
					game.changeScreen(LudumDare29.ScreenType.OVERWORLD);
					break;
				}
				return true;
			}
		});

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// TODO
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
