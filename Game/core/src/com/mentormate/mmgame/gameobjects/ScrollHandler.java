package com.mentormate.mmgame.gameobjects;

import com.mentormate.mmgame.gameworld.GameWorld;
import com.mentormate.mmgame.mmhelpers.AssetLoader;

public class ScrollHandler {// ScrollHandler will create all six objects that
							// we need.
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2;
	private BonusLogo bonusLogo1, bonusLogo2;
	private GameWorld gameWorld;

	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll and also determine
	// the size of the gap between each pair of pipes.

	// Capital letters are used by convention when naming constants.
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 143;

	// Constructor receives a float that tells us where we need to create our
	// Grass and Pipe objects.
	public ScrollHandler(GameWorld gameWorld, float yPos) {
		this.gameWorld = gameWorld;
		frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11,
				SCROLL_SPEED);
		pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
		bonusLogo1 = new BonusLogo(pipe1.getTailX() + PIPE_GAP / 2, 50, 16, 11,
				SCROLL_SPEED);
		pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,
				yPos);
		bonusLogo2 = new BonusLogo(pipe2.getTailX() + PIPE_GAP / 2, 50, 16, 11,
				SCROLL_SPEED);
	}

	public void update(float delta) {
		// Update our objects
		frontGrass.update(delta);
		backGrass.update(delta);
		pipe1.update(delta);
		bonusLogo1.update(delta);
		pipe2.update(delta);
		bonusLogo2.update(delta);

		// Check if any of the pipes are scrolled left,
		// and reset accordingly the bonusLogo
		if (pipe1.isScrolledLeft()) {
			pipe1.reset(pipe2.getTailX() + PIPE_GAP);
			bonusLogo2.reset(pipe2.getTailX() + PIPE_GAP / 2);

		} else if (pipe2.isScrolledLeft()) {
			pipe2.reset(pipe1.getTailX() + PIPE_GAP);
			bonusLogo1.reset(pipe1.getTailX() + PIPE_GAP / 2);
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());

		}

	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();
		pipe1.stop();
		pipe2.stop();
		bonusLogo1.stop();
		bonusLogo2.stop();
	}

	// Return true if ANY pipe hits the logo.
	public boolean collides(Logo logo) {
		return (pipe1.collides(logo) || pipe2.collides(logo));
	}

	public boolean scored(Logo logo) {

		if (bonusLogo1.collides(logo)) {
			if (!bonusLogo1.isScored()) {
				bonusLogo1.setScored(true);
				AssetLoader.coin.play();
				addScore(1);
				return true;
			} else {
				return true;
			}
		} else if (bonusLogo2.collides(logo)) {
			if (!bonusLogo2.isScored()) {
				bonusLogo2.setScored(true);
				AssetLoader.coin.play();
				addScore(1);
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	public void onRestart() {
		frontGrass.onRestart(0, SCROLL_SPEED);
		backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
		pipe1.onRestart(210, SCROLL_SPEED);
		bonusLogo1.onRestart(pipe1.getTailX() + PIPE_GAP / 2, SCROLL_SPEED);
		pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
		bonusLogo2.onRestart(pipe2.getTailX() + PIPE_GAP / 2, SCROLL_SPEED);
	}

	// The getters for our five instance variables
	public Grass getFrontGrass() {
		return frontGrass;
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public Pipe getPipe1() {
		return pipe1;
	}

	public Pipe getPipe2() {
		return pipe2;
	}

	public BonusLogo getBonusLogo1() {
		return bonusLogo1;
	}

	public BonusLogo getBonusLogo2() {
		return bonusLogo2;
	}

	private void addScore(int increment) {
		gameWorld.addScore(increment);
	}

}
