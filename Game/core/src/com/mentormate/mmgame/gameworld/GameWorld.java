package com.mentormate.mmgame.gameworld;

import com.mentormate.mmgame.gameobjects.Logo;

public class GameWorld {
	private Logo logo;

	public GameWorld(int midPointY) {
		logo = new Logo(33, midPointY - 5, 17, 12);
	}

	public void update(float delta) {
		logo.update(delta);
	}

	public Logo getLogo() {
		return logo;

	}
}
