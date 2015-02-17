package com.mentormate.mmgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mentormate.mmgame.screens.GameScreen;

public class MMGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("MMGame", "created");
		setScreen(new GameScreen());
	}

}
