package net.asg.games.dante;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
	public static void main(String[] args) {
		boolean copyImages = false;	
		
		if (copyImages) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			TexturePacker2.process(settings, "images", "../dantesbarbarque-android/assets", "game");
			TexturePacker2.process(settings, "text-images", "../dantesbarbarque-android/assets", "text_images");
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Dante's Barbarque";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new DantesBarbarqueGame(), cfg);
	}
}
