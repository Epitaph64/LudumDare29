package com.enzor.LD29.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.enzor.LD29.LudumDare29;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Ludum Dare 29";
		config.useGL30 = false;
		config.width = 1280;
		config.height = 800;
		new LwjglApplication(new LudumDare29(), config);
	}
}
