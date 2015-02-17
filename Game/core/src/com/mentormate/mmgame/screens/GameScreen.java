package com.mentormate.mmgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mentormate.mmgame.gameworld.GameRenderer;
import com.mentormate.mmgame.gameworld.GameWorld;
import com.mentormate.mmgame.zbhelpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;

	// This is the constructor, not the class declaration
	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world);

		Gdx.input.setInputProcessor(new InputHandler(world.getLogo()));

	}

	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
		// Leave blank
	}
}