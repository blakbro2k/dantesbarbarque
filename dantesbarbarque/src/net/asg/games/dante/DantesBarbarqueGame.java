package net.asg.games.dante;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.screens.GameScreen;

import com.badlogic.gdx.Game;



public class DantesBarbarqueGame extends Game{
	private ImageProvider imageProvider;
	private GameScreen gameScreen;
	//private TextResources textResources;

	public DantesBarbarqueGame(){
		
	}
	
	@Override
	public void create() {
		imageProvider = new ImageProvider();
        imageProvider.load();
        
        //soundManager = new SoundManager();
        //soundManager.load();
        
        //menuScreen = new MenuScreen(this);
        //levelScreen = new LevelScreen(this);
        gameScreen = new GameScreen(this);
        
        gotoGameScreen();
	}
	
	public void gotoGameScreen() {
		setScreen(new GameScreen(this));
	}
	
	public ImageProvider getImageProvider() {
		return imageProvider;
	}
	
	public void showHelp() {
		//setScreen(new HelpScreen(this));
	}
	
	public void gotoMenuScreen() {
		//setScreen(new MenuScreen(this));
	}
	
	//public TextResources getTextResources() {
	//	return textResources;
	//}
	
	@Override
	public void dispose() {
		imageProvider.dispose();
		//soundManager.dispose();
		
		//menuScreen.dispose();
		//levelScreen.dispose();
		gameScreen.dispose();
	}
}