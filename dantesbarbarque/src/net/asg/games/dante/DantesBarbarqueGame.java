package net.asg.games.dante;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.screens.GameScreen;
import net.asg.games.dante.screens.GameScreenState;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.FPSLogger;



public class DantesBarbarqueGame extends Game{
	private ImageProvider imageProvider;
	private GameScreen gameScreen;
    //private GameState gameState;

	//private TextResources textResources;
	private SoundManager soundManager;
	public boolean isDebugOn = false;
	//private FPSLogger fpsLogger;
	private StateManager stateManager;

	public DantesBarbarqueGame(){
		this.isDebugOn = false;
	}
	
	public DantesBarbarqueGame(boolean isDebugOn){
		this.isDebugOn = isDebugOn;
	}
	
	
	
	@Override
	public void create() {
		imageProvider = new ImageProvider();
        imageProvider.load();
        
        soundManager = new SoundManager();
        soundManager.load();
        
        gameScreen = new GameScreen(this, null);

        stateManager = new StateManager();

        //menuScreen = new MenuScreen(this);
        //levelScreen = new LevelScreen(this);
        gotoGameScreen();
	}
	
	public void gotoGameScreen() {
		setScreen(new GameScreen(this, null));
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
	
    //public GameState getGameState() {
    //    return gameState;
    //}
	
	//public TextResources getTextResources() {
	//	return textResources;
	//}
	
	public void persist(GameScreenState gameScreenState) {
		stateManager.persist(gameScreenState);
	}
	
	@Override
	public void dispose() {
		imageProvider.dispose();
		soundManager.dispose();
		
		//menuScreen.dispose();
		//levelScreen.dispose();
		gameScreen.dispose();
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
}
