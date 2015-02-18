package com.mentormate.mmgame.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class BonusLogo extends Scrollable {

	private Random r;
	private Circle boundingCircle;

	private boolean isScored = false;

	// When BonusLogo's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public BonusLogo(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		boundingCircle = new Circle();
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);
		boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

	}

	public boolean collides(Logo logo) {
		if (position.x < logo.getX() + logo.getWidth()) {
			return (Intersector.overlaps(logo.getBoundingCircle(),
					boundingCircle));

		}
		return false;
	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		position.y = r.nextInt(90) + 15;
		isScored = false;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}

}
