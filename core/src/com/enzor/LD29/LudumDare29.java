package com.enzor.LD29;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class LudumDare29 extends Game implements ApplicationListener {

	enum ScreenType {
		BATTLE(), OVERWORLD;
	}

	ScreenType currentScreen = ScreenType.BATTLE;

	OverworldScreen overworldScreen;
	BattleScreen battleScreen;

	@Override
	public void create() {
		Resources.initializeResources();
		overworldScreen = new OverworldScreen(this);
		battleScreen = new BattleScreen(this);
		setScreen(battleScreen);
	}

	@Override
	public void dispose() {
		Resources.dispose();
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

	public void changeScreen(ScreenType type) {
		currentScreen = type;
		switch (currentScreen) {
		case BATTLE:
			setScreen(battleScreen);
			break;
		case OVERWORLD:
			setScreen(overworldScreen);
			break;
		}
	}
}
