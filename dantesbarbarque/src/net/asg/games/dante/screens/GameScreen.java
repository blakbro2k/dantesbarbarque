package net.asg.games.dante.screens;

import net.asg.games.dante.DantesBarbarqueGame;
import net.asg.games.dante.models.Bob;
import net.asg.games.dante.models.Button;
import net.asg.games.dante.models.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends CommonScreen{

	protected SpriteBatch batch;
    
    protected OrthographicCamera camera;
    
    protected DantesBarbarqueGame game;
    
    protected Texture backgroundImage;
    
    protected TextureRegion bobRegion;
    
    protected Bob bob;
    
    protected Button backButton;
    
    private World world;
    
    private WorldRenderer renderer;

    /*
    private ImageProvider imageProvider;
    
    private SoundManager soundManager;
    
    
    private Texture backgroundImage;    
    
    private FallingObjectFactory fallingObjectFactory;
    
    private Array<FallingObject> fallingObjects;
    
    private Array<TimesTwoAnimation> timesTwoAnimations;
    
    private int gameDuration;
    
    private Basket basket;
    
    private AnalogueClock clock;

	private NumberBoard numberBoard;
	
	private StartGameMessage startGameMessage;
	
	private EndGameMessage endGameMessage;
    
	private Button backButton;
	
	private boolean tictacSoundPlaying;

	private boolean gameFinishedSoundPlaying;
	
	private int fruitPeriod;

	private int badObjectsPeriod;

	private int bonusObjectsPeriod;
	
	private TextureRegion pauseTexture;
	
	private int pauseX;
	
	private int pauseY;

	private GameModel gameModel;
	
    private int season;
    private int level;
    private int challenge;
    private int goal;
    */
    public GameScreen(DantesBarbarqueGame game){
		this.game = game;
    }
    
    public void show(){
		imageProvider = game.getImageProvider();
		imageProvider.load();
		backgroundImage = imageProvider.getBackgroundSpring();
		
        camera = new OrthographicCamera();
        camera.setToOrtho(false, imageProvider.getScreenWidth(), imageProvider.getScreenHeight());
        
        //batch = new SpriteBatch();
        
        bobRegion = imageProvider.getBob();
        bob = new Bob(new Vector2(3,4));
              
        backButton = new Button(imageProvider.getBack());
        backButton.setPos(10, 10);
        
        //Gdx.input.setInputProcessor(this);
        //Gdx.input.setCatchBackKey(true);
        
		world = new World(game);
		renderer = new WorldRenderer(world);
    }
    
	@Override
	public void render(float delta) {
        //camera.update();
       // batch.setProjectionMatrix(camera.combined);
       // batch.begin();
       // batch.draw(backgroundImage, 0, 0);
        //batch.end(); 
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}
	
	@Override
	public void dispose() {
        if (batch != null) {
        	batch.dispose();
        }
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			Gdx.app.exit();
			return true;
		}
		return false;
	}
}
