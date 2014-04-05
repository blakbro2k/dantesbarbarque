
package net.asg.games.dante;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

/**
 * @author Blakbro2k
 * 
 * Main Desktop file for libgdx. Loads the application in a desktop window
 * with configuration.  It creates a new <code>LwjglApplication</code> by
 * passing a new <code>DantesBarbarqueGame</code>.
 */
public class Main {
	public static void main(String[] args) {
		
		boolean copyImages = false;
		
		//Use TexturePacker to pack image files
	
		if (copyImages) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			TexturePacker2.process(settings, "images", "../dantesbarbarque-android/assets", "game");
			TexturePacker2.process(settings, "text-images", "../dantesbarbarque-android/assets", "text_images");
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Jerry's Inferno";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new DantesBarbarqueGame(false), cfg);
	}
}
