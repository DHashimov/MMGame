package com.mentormate.mmgame.mmhelpers;

import com.badlogic.gdx.InputProcessor;
import com.mentormate.mmgame.gameobjects.Logo;

public class InputHandler implements InputProcessor {

	private Logo myLogo;

	// Ask for a reference to the Bird when InputHandler is created.
	public InputHandler(Logo logo) {
		// myBird now represents the gameWorld's bird.
		myLogo = logo;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		myLogo.onClick();
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
