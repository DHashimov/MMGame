package com.mentormate.mmgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mentormate.mmgame.MMGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "MMGame";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new MMGame(), config);
	}
}
