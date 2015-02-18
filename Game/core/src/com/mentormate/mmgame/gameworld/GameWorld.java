package com.mentormate.mmgame.gameworld;

import com.mentormate.mmgame.gameobjects.Logo;
import com.mentormate.mmgame.gameobjects.ScrollHandler;

public class GameWorld {
	private Logo logo;
	private ScrollHandler scroller;

	public GameWorld(int midPointY) {
		logo = new Logo(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(midPointY + 66);
	}

	public void update(float delta) {
		logo.update(delta);
		scroller.update(delta);
	}

	public Logo getLogo() {
		return logo;

	}

	public ScrollHandler getScroller() {
		return scroller;
	}
}
