package net.asg.games.dante.screens;

import java.util.Iterator;

import net.asg.games.dante.DantesBarbarqueGame;
import net.asg.games.dante.models.Bob;
import net.asg.games.dante.models.Button;
import net.asg.games.dante.sound.SoundManager;
import net.asg.games.dante.view.MovingGameObjectFactory;
import net.asg.games.dante.view.MovingGameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * @author Blakbro2k
 * 
 *         The main screen for the game. This screen is where the main game is
 *         played. All sprites and game objects are drawn here. This screen
 *         extends <code>CommonScreen</code> to minimize having to write
 *         unimplemented methods.
 */
public class GameScreen extends CommonScreen {

	protected Texture backgroundImage;

	protected TextureRegion bobRegion;

	protected Bob bob;

	protected Button backButton;

	private float scrollTimer;
	
	private SoundManager soundManager;

	// private World world;

	// private WorldRenderer renderer;

	private MovingGameObjectFactory movingGameObjectFactory;

	private Array<MovingGameObject> movingObjects;

	private long lastGameObjTime = 0;

	// private boolean isPressed;


	/*
	 * private Array<FallingObject> fallingObjects;
	 * 
	 * private Array<TimesTwoAnimation> timesTwoAnimations;
	 * 
	 * private int gameDuration;
	 * 
	 * private Basket basket;
	 * 
	 * private AnalogueClock clock;
	 * 
	 * private NumberBoard numberBoard;
	 * 
	 * private StartGameMessage startGameMessage;
	 * 
	 * private EndGameMessage endGameMessage;
	 * 
	 * private Button backButton;
	 * 
	 * private boolean tictacSoundPlaying;
	 * 
	 * private boolean gameFinishedSoundPlaying;
	 * 
	 * private int fruitPeriod;
	 * 
	 * private int badObjectsPeriod;
	 * 
	 * private int bonusObjectsPeriod;
	 * 
	 * private TextureRegion pauseTexture;
	 * 
	 * private int pauseX;
	 * 
	 * private int pauseY;
	 * 
	 * private GameModel gameModel;
	 * 
	 * private int season; private int level; private int challenge; private int
	 * goal;
	 */
	public GameScreen(DantesBarbarqueGame game) {
		this.game = game;
	}

	public void show() {
		imageProvider = game.getImageProvider();
		soundManager = game.getSoundManager();
		soundManager.setSoundOn(true);

		backgroundImage = imageProvider.getBackgroundFire();
		backgroundSprite = imageProvider.getBackgroundSprite();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, imageProvider.getScreenWidth(),
				imageProvider.getScreenHeight());

		batch = new SpriteBatch();

		movingGameObjectFactory = new MovingGameObjectFactory(imageProvider,
				soundManager);

		bobRegion = imageProvider.getBob();
		bob = new Bob(imageProvider.getScreenHeight(),
				imageProvider.getScreenWidth(), 20, -1);

		movingObjects = new Array<MovingGameObject>();
		// movingObjects.add(movingGameObjectFactory.getFireball());

		// backButton = new Button(imageProvider.getBack());
		// backButton.setPos(10, 10);

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		scrollTimer += delta * imageProvider.backgroundSpeed;
	     if(scrollTimer>1.0f)
	         scrollTimer = 0.0f;
	     
	    backgroundSprite.setU(scrollTimer);
	    backgroundSprite.setU2(scrollTimer+1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		//batch.draw(backgroundImage, 0, 0);
		backgroundSprite.draw(batch);

		batch.draw(bobRegion, bob.getPosition().x, bob.getPosition().y);

		for (MovingGameObject movingObject : movingObjects) {
			movingObject.draw(batch);
		}

		batch.end();

		processInput();

		if (TimeUtils.millis() - lastGameObjTime > 860) {
			
			//spawnFireballMovingGameObject();
			spawnFireWallMovingGameObject();
			//spawnDynamicFireWallMovingGameObject();
		}

		/* Using Iterator, we update all objects on screen to move, and
		 * discard all objects off screen
		 * All hit detection happens here also
		 */
		Iterator<MovingGameObject> iter = movingObjects.iterator();
		while (iter.hasNext()) {
			MovingGameObject fo = iter.next();
			fo.moveLeft(Gdx.graphics.getDeltaTime());
			if (fo.isLeftOfScreen()) {
				iter.remove();
			}
			
			 if(fo.isOverlapping(bob.getPosition())) { 
				 System.out.println("!!!CRASH!!!");
			 } 
		}

	}

	private void spawnFireWallMovingGameObject() {
		movingObjects.add(movingGameObjectFactory.getFireWall());
		soundManager.playfirewooshSound();
		lastGameObjTime = TimeUtils.millis();
	}

	private void spawnDynamicFireWallMovingGameObject() {
		movingObjects.add(movingGameObjectFactory.getDynamicFireWall());
		soundManager.playfirewooshSound();
		lastGameObjTime = TimeUtils.millis();
	}

	private void spawnFireballMovingGameObject() {
		movingObjects.add(movingGameObjectFactory.getFireball());
		soundManager.playflameBurstSound();
		lastGameObjTime = TimeUtils.millis();
	}

	private void processInput() {
		float delta = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			bob.moveY(1, delta);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			bob.moveY(-1, delta);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			bob.moveX(-1, delta);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
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
		if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
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

		if (bob.getPosition().x < touchPos.x)
			bob.moveX(1, delta);
		if (bob.getPosition().x > touchPos.x)
			bob.moveX(-1, delta);
		if (bob.getPosition().y < touchPos.y)
			bob.moveY(1, delta);
		if (bob.getPosition().y > touchPos.y)
			bob.moveY(-1, delta);

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float delta = Gdx.graphics.getDeltaTime();

		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		camera.unproject(touchPos);

		if (bob.getPosition().x < touchPos.x)
			bob.moveX(1, delta);
		if (bob.getPosition().x > touchPos.x)
			bob.moveX(-1, delta);
		if (bob.getPosition().y < touchPos.y)
			bob.moveY(1, delta);
		if (bob.getPosition().y > touchPos.y)
			bob.moveY(-1, delta);

		return true;
	}
}
