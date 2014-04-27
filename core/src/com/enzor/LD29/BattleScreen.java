package com.enzor.LD29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.enzor.LD29.Resources.SpriteName;
import com.enzor.LD29.controller.Controller;

public class BattleScreen implements Screen {

	private SpriteBatch batch;
	private PerspectiveCamera camera;

	private ShaderProgram shader;

	private LudumDare29 game;

	public BattleScreen(LudumDare29 ludumDare29) {
		this.game = ludumDare29;
	}

	@Override
	public void show() {

		// Create SpriteBatch and load necessary textures
		batch = new SpriteBatch();

		// Setup camera with with perspective
		camera = new PerspectiveCamera(67, 160, 100);
		camera.position.set(80, 0, 80);
		camera.lookAt(80, 90, 0);
		camera.near = 0.1f;
		camera.far = 300f;
		camera.update();

		// Initialize the ground shader
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/depthshade.vsh"), Gdx.files.internal("shaders/depthshade.fsh"));

		// Print test
		Gdx.app.log("Shader Log", shader.isCompiled() ? "Shader compiled successfully" : shader.getLog());

		// Set the sprite batch to use the shader
		batch.setShader(shader);

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
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		shader.begin();
		shader.setUniformf("u_resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shader.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int x = -5; x < 15; x++) {
			for (int y = 0; y < 7; y++) {
				batch.draw(Resources.getSprite(SpriteName.GRASS), x * 16, y * 16);
			}
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
	}

}
