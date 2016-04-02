package com.alesegdia.famjam6.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alesegdia.famjam6.GameConfig;
import com.alesegdia.famjam6.GdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConfig.WINDOW_WIDTH;
		config.height = GameConfig.WINDOW_HEIGHT;
		config.title = "Digistruct";
		new LwjglApplication(new GdxGame(), config);
	}
}
