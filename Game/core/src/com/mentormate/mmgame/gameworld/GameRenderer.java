package com.mentormate.mmgame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mentormate.mmgame.gameobjects.BonusLogo;
import com.mentormate.mmgame.gameobjects.Grass;
import com.mentormate.mmgame.gameobjects.Logo;
import com.mentormate.mmgame.gameobjects.Pipe;
import com.mentormate.mmgame.gameobjects.ScrollHandler;
import com.mentormate.mmgame.mmhelpers.AssetLoader;

public class GameRenderer {

	// Game Objects
	private Logo logo;

	// Game Assets
	private TextureRegion bg, grass;
	private Animation logoAnimation;
	private TextureRegion logoMid, logoDown, logoUp;
	private TextureRegion barTopUp, barTopDown, bar, bonusLogoAndroid,
			bonusLogoApple;

	private Logo bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2;
	private BonusLogo bLogo1, bLogo2;

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		// The word "this" refers to this instance.
		// We are setting the instance variables' values to be that of the
		// parameters passed in from GameScreen.
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		logo = myWorld.getLogo();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		bLogo1 = scroller.getBonusLogo1();
		bLogo2 = scroller.getBonusLogo2();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		logoAnimation = AssetLoader.logoAnimation;
		logoMid = AssetLoader.logo;
		logoDown = AssetLoader.logoDown;
		logoUp = AssetLoader.logoUp;
		barTopUp = AssetLoader.barTopUp;
		barTopDown = AssetLoader.barTopDown;
		bar = AssetLoader.bar;
		bonusLogoAndroid = AssetLoader.androidLogo;
		bonusLogoApple = AssetLoader.appleLogo;
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawBarTops() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.

		batcher.draw(barTopUp, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(barTopDown, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(barTopUp, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(barTopDown, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

	}

	private void drawPipes() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
				pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
				pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

	}

	private void drawLogos() {
		// Draw the first logo
		batcher.draw(bonusLogoAndroid, bLogo1.getX(), bLogo1.getY(),
				bLogo1.getWidth(), bLogo1.getHeight());

		// Draw the second logo
		batcher.draw(bonusLogoApple, bLogo2.getX(), bLogo2.getY(),
				bLogo2.getWidth(), bLogo2.getHeight());
	}

	public void render(float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(108 / 255.0f, 180 / 255.0f, 226 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Draw Grass
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();
		batcher.draw(bg, 0, midPointY + 23, 136, 43);

		// 1. Draw Grass
		drawGrass();

		// 2. Draw Pipes
		drawPipes();
		batcher.enableBlending();

		// 3. Draw Bar Tops (requires transparency)
		drawBarTops();

		if (logo.shouldntFlap()) {
			batcher.draw(logoMid, logo.getX(), logo.getY(),
					logo.getWidth() / 2.0f, logo.getHeight() / 2.0f,
					logo.getWidth(), logo.getHeight(), 1, 1, logo.getRotation());

		} else {
			batcher.draw(logoAnimation.getKeyFrame(runTime), logo.getX(),
					logo.getY(), logo.getWidth() / 2.0f,
					logo.getHeight() / 2.0f, logo.getWidth(), logo.getHeight(),
					1, 1, logo.getRotation());
		}

		batcher.end();
		batcher.begin();
		drawLogos();

		// Convert integer into String
		String score = myWorld.getScore() + "";

		// Draw shadow first
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length()), 12);
		// Draw text
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length() - 1), 11);
		batcher.end();

	}
}
