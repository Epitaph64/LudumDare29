package com.enzor.LD29;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class LudumDare29 extends Game implements ApplicationListener {

	public static Resources resources;

	OverworldScreen gameScreen;

	@Override
	public void create() {
		resources = new Resources();
		gameScreen = new OverworldScreen();
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		resources.dispose();
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
