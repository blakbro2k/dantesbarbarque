
package net.asg.games.dante;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

/**
 * @author Blakbro2k
 * 
 * Main Desktop f   ile for libgdx. Loads the application in a desktop window
 * with configuration.  It creates a new <code>LwjglApplication</code> by
 * passing a new <code>DantesBarbarqueGame</code>.
 */
public class Main {
	public static void main(String[] args) {
		
		boolean copyImages = Constants.COPY_IMAGES;
		boolean debugOn = Constants.DEBUG;
		
		//Use TexturePacker to pack image files
	
		if (copyImages) {
			Settings settings = new Settings();
			settings.maxWidth = Constants.MAX_WIDTH;
			settings.maxHeight = Constants.MAX_HEIGHT;
			TexturePacker2.process(settings, Constants.GAME_IMAGES_TEXTURE_FOLDER_NAME, "../" + Constants.ASSETS_PATH, Constants.GAME_IMAGES_TEXTURE_NAME);
			TexturePacker2.process(settings, Constants.TEXT_IMAGES_TEXTURE_FOLDER_NAME, "../" + Constants.ASSETS_PATH, Constants.TEXT_IMAGES_TEXTURE_NAME);
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Constants.GAME_TITLE;
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new DantesBarbarqueGame(debugOn), cfg);
	}
}
