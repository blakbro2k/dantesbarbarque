package net.asg.games.dante;


import net.asg.games.dante.screens.GameScreen;
import net.asg.games.images.ImageProvider;

import com.badlogic.gdx.Game;

public class DantesBarbarqueGame extends Game{
	protected ImageProvider imageProvider;
	protected GameScreen gameScreen;
	
	public DantesBarbarqueGame(){
		
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
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
	
	@Override
	public void dispose() {
		imageProvider.dispose();
		//soundManager.dispose();
		
		//menuScreen.dispose();
		//levelScreen.dispose();
		gameScreen.dispose();
	}
}
