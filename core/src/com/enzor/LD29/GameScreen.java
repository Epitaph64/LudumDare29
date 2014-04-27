package com.enzor.LD29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.enzor.LD29.helpers.NoiseGenerator;

public class GameScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;

	// Texture related
	private Texture img;
	private TextureRegion grassTexture;
	private TextureRegion waterTexture;
	private TextureRegion roadTexture;
	private TextureRegion sandTexture;
	private TextureRegion forestTexture;

	enum Terrain {
		SPACE, WATER, SAND, GRASS, FOREST, MOUNTAIN, SNOW;
	}

	Terrain[][] map;
	int w = 192;
	int h = 128;

	@Override
	public void show() {
		// Create SpriteBatch and load necessary textures
		batch = new SpriteBatch();
		img = new Texture("tex.png");

		// Init variables
		map = new Terrain[w][h];

		// Setup camera with 2X zoom
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * 4,
				Gdx.graphics.getHeight() * 4);
		camera.translate(Gdx.graphics.getWidth() * 2,
				Gdx.graphics.getHeight() * 2);
		camera.update();

		/*
		 * 
		 * Initialize sub-textures
		 */

		// Row 1
		grassTexture = new TextureRegion(img, 0, 0, 16, 16);
		waterTexture = new TextureRegion(img, 16, 0, 16, 16);
		forestTexture = new TextureRegion(img, 32, 0, 16, 16);

		// Row 2
		roadTexture = new TextureRegion(img, 0, 16, 16, 16);
		sandTexture = new TextureRegion(img, 16, 16, 16, 16);

		// Generate a starting map
		genMap();
	}

	private void genMap() {
		NoiseGenerator noiseGen = new NoiseGenerator(w >> 2, h >> 2);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				float value = 0;
				value = (float) noiseGen.turbulence(x, y, 25);
				if (value < 0.36f) {
					map[x][y] = Terrain.WATER;
				} else if (value < 0.43f) {
					map[x][y] = Terrain.SAND;
				} else if (value < 0.55f) {
					map[x][y] = Terrain.GRASS;
				} else if (value < 0.70f) {
					map[x][y] = Terrain.FOREST;
				} else {
					map[x][y] = Terrain.MOUNTAIN;
				}
			}
		}
	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			camera.translate(new Vector2(-250 * Gdx.graphics.getDeltaTime(), 0));
			camera.update();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.translate(new Vector2(250 * Gdx.graphics.getDeltaTime(), 0));
			camera.update();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			camera.translate(new Vector2(0, 250 * Gdx.graphics.getDeltaTime()));
			camera.update();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			camera.translate(new Vector2(0, -250 * Gdx.graphics.getDeltaTime()));
			camera.update();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				switch (map[x][y]) {
				case GRASS:
					batch.draw(grassTexture, x * 16, y * 16);
					break;
				case WATER:
					batch.draw(waterTexture, x * 16, y * 16);
					break;
				case SAND:
					batch.draw(sandTexture, x * 16, y * 16);
					break;
				case FOREST:
					batch.draw(forestTexture, x * 16, y * 16);
					break;
				case MOUNTAIN:
					batch.draw(roadTexture, x * 16, y * 16);
				default:
					break;
				}
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			genMap();
		}
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
		img.dispose();
		batch.dispose();
		this.dispose();
	}

}
