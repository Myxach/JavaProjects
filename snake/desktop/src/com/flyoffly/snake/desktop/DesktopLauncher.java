package com.flyoffly.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flyoffly.snake.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ( 1280 / 64 ) * 64;
		config.height = ( 760 / 64 ) * 64 ;
		new LwjglApplication(new SnakeGame(), config);
		
	}
}
