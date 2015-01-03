package vu.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import vu.snake.Snake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Snake.WIDTH / 2;
		config.height = Snake.HEIGHT / 2;
		new LwjglApplication(new Snake(), config);
	}
}
