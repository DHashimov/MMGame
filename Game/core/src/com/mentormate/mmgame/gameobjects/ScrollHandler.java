package com.mentormate.mmgame.gameobjects;

public class ScrollHandler {// ScrollHandler will create all six objects that
							// we need.
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2;
	private BonusLogo bonusLogo1, bonusLogo2;

	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll and also determine
	// the size of the gap between each pair of pipes.

	// Capital letters are used by convention when naming constants.
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 143;

	// Constructor receives a float that tells us where we need to create our
	// Grass and Pipe objects.
	public ScrollHandler(float yPos) {

		frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11,
				SCROLL_SPEED);
		pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED);
		bonusLogo1 = new BonusLogo(pipe1.getTailX() + PIPE_GAP / 2, 50, 16, 11,
				SCROLL_SPEED);
		pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED);
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

}
