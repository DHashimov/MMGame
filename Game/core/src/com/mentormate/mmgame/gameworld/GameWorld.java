package com.mentormate.mmgame.gameworld;

import com.mentormate.mmgame.gameobjects.Logo;
import com.mentormate.mmgame.gameobjects.ScrollHandler;
import com.mentormate.mmgame.zbhelpers.AssetLoader;

public class GameWorld {
	private Logo logo;
	private ScrollHandler scroller;
	private boolean isAlive = true;

	public GameWorld(int midPointY) {
		logo = new Logo(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(midPointY + 66);
	}

	public void update(float delta) {
		logo.update(delta);
		scroller.update(delta);

		if (scroller.collides(logo)) {
			// Clean up on game over
			scroller.stop();
		}

		if (isAlive && scroller.collides(logo)) {
			scroller.stop();
			AssetLoader.dead.play();
			isAlive = false;
		}
	}

	public Logo getLogo() {
		return logo;

	}

	public ScrollHandler getScroller() {
		return scroller;
	}
}
