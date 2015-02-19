package com.mentormate.mmgame.mmhelpers;

import com.badlogic.gdx.InputProcessor;
import com.mentormate.mmgame.gameobjects.Logo;
import com.mentormate.mmgame.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

	private Logo myLogo;
	private GameWorld myWorld;

	// Ask for a reference to the Bird when InputHandler is created.
	public InputHandler(GameWorld myWorld) {
		// myLogo now represents the gameWorld's logo.
		this.myWorld = myWorld;
		myLogo = myWorld.getLogo();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (myWorld.isReady()) {
			myWorld.start();
		}

		myLogo.onClick();

		if (myWorld.isGameOver() || myWorld.isHighScore()) {
		    // Reset all variables, go to GameState.READ
		    myWorld.restart();
		}
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
