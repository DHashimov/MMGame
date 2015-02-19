package com.mentormate.mmgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mentormate.mmgame.mmhelpers.AssetLoader;
import com.mentormate.mmgame.screens.SplashScreen;

public class MMGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("MMGame", "created");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
