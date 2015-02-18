package com.mentormate.mmgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mentormate.mmgame.mmhelpers.AssetLoader;
import com.mentormate.mmgame.screens.GameScreen;

public class MMGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("MMGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
