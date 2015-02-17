package com.mentormate.mmgame.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;
	public static TextureRegion bg;
	public static TextureRegion grass;

	public static Animation logoAnimation;
	public static TextureRegion logo, logoDown, logoUp;

	public static TextureRegion barTopUp, barTopDown, bar;

	public static void load() {

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		logoDown = new TextureRegion(texture, 136, 0, 17, 12);
		logoDown.flip(false, true);

		logo = new TextureRegion(texture, 153, 0, 17, 12);
		logo.flip(false, true);

		logoUp = new TextureRegion(texture, 170, 0, 17, 12);
		logoUp.flip(false, true);

		TextureRegion[] birds = { logoDown, logo, logoUp };
		logoAnimation = new Animation(0.06f, birds);
		logoAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		barTopUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing skullUp
		barTopDown = new TextureRegion(barTopUp);
		barTopDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
	}

}