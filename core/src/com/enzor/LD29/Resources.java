package com.enzor.LD29;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {

	// Texture related
	private Texture spriteSheet;
	private Map<SpriteName, Sprite> spriteMap;

	public Resources() {

		spriteMap = new HashMap<SpriteName, Sprite>();

		// Load the sprite sheet
		spriteSheet = new Texture("tex.png");

		/*
		 * 
		 * Initialize sub-textures
		 */

		// Row 1
		TextureRegion grassTexture = new TextureRegion(spriteSheet, 0, 0, 16, 16);
		spriteMap.put(SpriteName.GRASS, new Sprite(grassTexture));

		TextureRegion waterTexture = new TextureRegion(spriteSheet, 16, 0, 16, 16);
		spriteMap.put(SpriteName.WATER, new Sprite(waterTexture));

		TextureRegion forestTexture = new TextureRegion(spriteSheet, 32, 0, 16, 16);
		spriteMap.put(SpriteName.FOREST, new Sprite(forestTexture));

		// Row 2
		TextureRegion mountainTexture = new TextureRegion(spriteSheet, 0, 16, 16, 16);
		spriteMap.put(SpriteName.MOUNTAIN, new Sprite(mountainTexture));

		TextureRegion sandTexture = new TextureRegion(spriteSheet, 16, 16, 16, 16);
		spriteMap.put(SpriteName.SAND, new Sprite(sandTexture));
		//
	}

	// return sprite from map based on name given
	public Sprite getSprite(SpriteName spriteName) {
		return spriteMap.get(spriteName);
	}

	public enum SpriteName {
		WATER, SAND, GRASS, FOREST, MOUNTAIN, SNOW;
	}

	public void dispose() {
		spriteSheet.dispose();
	}

}
