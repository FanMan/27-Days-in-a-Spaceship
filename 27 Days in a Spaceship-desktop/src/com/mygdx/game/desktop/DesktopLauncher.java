package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SpaceshipGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		SpaceshipGame ssg = new SpaceshipGame();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ssg.WIDTH;
		config.height = ssg.HEIGHT;
		config.title = ssg.TITLE;
		new LwjglApplication(ssg, config);
	}
}
