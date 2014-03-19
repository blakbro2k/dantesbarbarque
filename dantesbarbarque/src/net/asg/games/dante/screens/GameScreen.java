package net.asg.games.dante.screens;

import net.asg.games.dante.DantesBarbarqueGame;
import net.asg.games.dante.images.ImageProvider;
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
import com.badlogic.gdx.math.Vector3;

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

    private ImageProvider imageProvider;
    
    //private boolean isPressed;
    
    /*private SoundManager soundManager;
    
    
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
	    //isPressed = false;

		//bob.getPosition().x = 10;
		//bob.getPosition().y = 10;
    }
    
    public void show(){
		imageProvider = game.getImageProvider();
		//imageProvider.load();
		
		backgroundImage = imageProvider.getBackgroundFire();
		
        camera = new OrthographicCamera();
        camera.setToOrtho(false, imageProvider.getScreenWidth(), 
        						 imageProvider.getScreenHeight());

        batch = new SpriteBatch();
        
        bobRegion = imageProvider.getBob();
        bob = new Bob(imageProvider.getScreenHeight(),
        				imageProvider.getScreenWidth(),20,-1);
              
        //backButton = new Button(imageProvider.getBack());
        //backButton.setPos(10, 10);
        
		//world = new World(imageProvider);
		//renderer = new WorldRenderer(world,imageProvider);
        
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }
    
	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
       	batch.begin();
       	batch.draw(backgroundImage, 0, 0);
       	
       	batch.draw(bobRegion, bob.getPosition().x, bob.getPosition().y);
		
       	//renderer.render();

        batch.end(); 
		//Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
        processInput(); 

	}
	
    private void processInput() {
   
	        float delta = Gdx.graphics.getDeltaTime();
	        if(Gdx.input.isKeyPressed(Keys.UP)) {
	            bob.moveY(1, delta);
	        }
	        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
	            bob.moveY(-1, delta);
	        }
	        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	            bob.moveX(-1, delta);
	        }
	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	            bob.moveX(1, delta);
	        }
	        
	        bob.moveX(Gdx.input.getAccelerometerX(), delta);
	        bob.moveY(Gdx.input.getAccelerometerY(), delta);
    }
	
	@Override
	public void dispose() {
        if (batch != null) {
        	batch.dispose();
        }
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK || keycode == Keys.BACKSPACE){
			Gdx.app.exit();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float delta = Gdx.graphics.getDeltaTime();

    		Vector3 touchPos = new Vector3();
	        touchPos.set(screenX, screenY, 0);
	        camera.unproject(touchPos);            
	        
	        if(bob.getPosition().x < touchPos.x)
	        	bob.moveX(1,delta);
	        if(bob.getPosition().x > touchPos.x)
	        	bob.moveX(-1,delta);
	        if(bob.getPosition().y < touchPos.y)
	        	bob.moveY(1,delta);
	        if(bob.getPosition().y > touchPos.y)
	        	bob.moveY(-1,delta);

		return true;
	}
	
	
	
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
        float delta = Gdx.graphics.getDeltaTime();

			Vector3 touchPos = new Vector3();
	        touchPos.set(screenX, screenY, 0);
	        camera.unproject(touchPos);            
	        
	        if(bob.getPosition().x < touchPos.x)
	        	bob.moveX(1,delta);
	        if(bob.getPosition().x > touchPos.x)
	        	bob.moveX(-1,delta);
	        if(bob.getPosition().y < touchPos.y)
	        	bob.moveY(1,delta);
	        if(bob.getPosition().y > touchPos.y)
	        	bob.moveY(-1,delta);
	
        return true;
	}
}
