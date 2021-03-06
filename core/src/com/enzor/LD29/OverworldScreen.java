package com.enzor.LD29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.enzor.LD29.controller.Controller;
import com.enzor.LD29.helpers.NoiseGenerator;

public class OverworldScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;

	Resources.SpriteName[][] map;

	int w = 80;
	int h = 50;

	private LudumDare29 game;

	// On game start
	public OverworldScreen(LudumDare29 ludumDare29) {
		this.game = ludumDare29;

		// Create SpriteBatch and load necessary textures
		batch = new SpriteBatch();

		// Init variables
		map = new Resources.SpriteName[w][h];

		// Setup camera with 2X zoom
		camera = new OrthographicCamera(w << 3, h << 3);
		camera.translate(w << 3, h << 3);
		camera.update();

		genMap();

	}

	// Called each time screen is switched to
	@Override
	public void show() {

		// Override the default input processor to get one-off key pushed events
		Gdx.input.setInputProcessor(new Controller() {
			@Override
			public boolean keyDown(int keyCode) {
				switch (keyCode) {
				case Input.Keys.NUM_2:
					game.changeScreen(LudumDare29.ScreenType.BATTLE);
					break;
				case Input.Keys.SPACE:
					genMap();
					break;
				case Input.Keys.ESCAPE:
					Gdx.app.exit();
				}
				return true;
			}
		});
	}

	private void genMap() {
		NoiseGenerator noiseGen = new NoiseGenerator(w >> 4, h >> 4);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				float value = 0;
				value = (float) noiseGen.turbulence(x, y, 25);
				if (value < 0.36f) {
					map[x][y] = Resources.SpriteName.WATER;
				} else if (value < 0.43f) {
					map[x][y] = Resources.SpriteName.SAND;
				} else if (value < 0.55f) {
					map[x][y] = Resources.SpriteName.GRASS;
				} else if (value < 0.70f) {
					map[x][y] = Resources.SpriteName.FOREST;
				} else {
					map[x][y] = Resources.SpriteName.MOUNTAIN;
				}
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				batch.draw(Resources.getSprite(map[x][y]), x * 16, y * 16);
			}
		}

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera = new OrthographicCamera(w * 16, h * 16);
		camera.translate(w * 8, h * 8);
		camera.update();
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
		batch.dispose();
		this.dispose();
	}

}
