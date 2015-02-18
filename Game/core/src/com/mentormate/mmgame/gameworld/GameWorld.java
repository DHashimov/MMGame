package com.mentormate.mmgame.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mentormate.mmgame.gameobjects.Logo;
import com.mentormate.mmgame.gameobjects.ScrollHandler;
import com.mentormate.mmgame.mmhelpers.AssetLoader;

public class GameWorld {
	private Logo logo;
	private ScrollHandler scroller;
	private boolean isAlive = true;
	private Rectangle ground;

	private int score = 0;

	public GameWorld(int midPointY) {
		logo = new Logo(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}

	public void update(float delta) {
		// Add a delta cap so that if our game takes too long
		// to update, we will not break our collision detection.

		if (delta > .15f) {
			delta = .15f;
		}

		logo.update(delta);
		scroller.update(delta);

		if (scroller.collides(logo) && logo.isAlive()) {
			scroller.stop();
			logo.die();
			AssetLoader.dead.play();
		} else if (scroller.scored(logo) && logo.isAlive()) {
			// TODO add logic to hide bonus Logo
		}

		if (Intersector.overlaps(logo.getBoundingCircle(), ground)) {
			scroller.stop();
			logo.die();
			logo.decelerate();
		}
	}

	public Logo getLogo() {
		return logo;

	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}
}
