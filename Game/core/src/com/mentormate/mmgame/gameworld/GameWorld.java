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
	private GameState currentState;
	public int midPointY;

	public enum GameState {

		READY, RUNNING, GAMEOVER, HIGHSCORE

	}

	public GameWorld(int midPointY) {
		logo = new Logo(33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
		currentState = GameState.READY;
		this.midPointY = midPointY;
	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		// Do nothing for now
	}

	public void updateRunning(float delta) {
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
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
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

	public void restart() {
		currentState = GameState.READY;
		score = 0;
		logo.onRestart(midPointY - 5);
		scroller.onRestart();
		currentState = GameState.READY;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

}
