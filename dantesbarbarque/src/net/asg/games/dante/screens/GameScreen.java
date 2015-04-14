package net.asg.games.dante.screens;

import java.util.Iterator;

import net.asg.games.dante.DantesBarbarqueGame;
import net.asg.games.dante.manager.LevelManager;
import net.asg.games.dante.models.Bob;
import net.asg.games.dante.models.Button;
import net.asg.games.dante.view.GameOverMessage;
import net.asg.games.dante.view.MovingGameObject;
import net.asg.games.dante.view.MovingGameObjectFactory;
import net.asg.games.dante.view.MovingGameObjectState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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

	private Sprite backgroundSprite;

	private Sprite foregroundSprite;

	private TextureRegion bobRegion;

	private Bob bob;

	private Button resetButton;
	
	private Button homeButton;

	private float bgScrollTimer;

	private float fgScrollTimer;

	private MovingGameObjectFactory movingGameObjectFactory;

	private Array<MovingGameObject> movingObjects;

	private LevelManager levelManager;

	private GameScreenState st;

	private String scoreName;

	private BitmapFont bitmapFontName;

	private Texture pauseScreen;
	
	private GameOverMessage gameOverMessage;

	public GameScreen(DantesBarbarqueGame game, GameScreenState state) {
		if (state != null) {
			st = state;
		}
		else {
			st = new GameScreenState();
			st.hardReset();
			st.isPaused = false;
		}
		this.game = game;
	}

	public void show() {
		imageProvider = game.getImageProvider();
		soundManager = game.getSoundManager();
		soundManager.setSoundOn(true);
		soundManager.playBgSound();

		backgroundSprite = imageProvider.getBackgroundSprite();

		foregroundSprite = imageProvider.getForegroundSprite();

		debugRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, imageProvider.getScreenWidth(),
				imageProvider.getScreenHeight());

		batch = new SpriteBatch();

		movingGameObjectFactory = new MovingGameObjectFactory(imageProvider,
				soundManager);

		bobRegion = imageProvider.getBob();
		bob = new Bob(imageProvider.getScreenHeight(),
				imageProvider.getScreenWidth(), st.bobX, st.bobY,
				bobRegion.getRegionHeight() - 20, bobRegion.getRegionWidth());

		movingObjects = new Array<MovingGameObject>();
		scoreName = "score: 0";
		bitmapFontName = new BitmapFont();

		pauseScreen = imageProvider.getPauseScreen();
		
		homeButton = new Button(imageProvider.getHomeButton());
		homeButton.setPos(imageProvider.getScreenWidth() / 2 + 20, imageProvider.getScreenHeight() / 2 - 60);
		
		resetButton = new Button(imageProvider.getResetButton());
		resetButton.setPos(imageProvider.getScreenWidth() / 2 - 90, imageProvider.getScreenHeight() / 2 - 60);
	
		levelManager = new LevelManager();

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//set up the background and forground scroll properties

		//Gdx.app.log("GameScreen", st.toString());

		if(!st.isPaused){
			bgScrollTimer += delta * st.getBackgroundSpeed();
			fgScrollTimer += delta * st.getForegroundSpeed();
		}

		if (bgScrollTimer > 1.0f)
			bgScrollTimer = 0.0f;

		if (fgScrollTimer > 1.0f)
			fgScrollTimer = 0.0f;

		backgroundSprite.setU(bgScrollTimer);
		backgroundSprite.setU2(bgScrollTimer + 1);

		foregroundSprite.setU(fgScrollTimer);
		foregroundSprite.setU2(fgScrollTimer + 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (game.isDebugOn) {
			debugRenderer.setProjectionMatrix(camera.combined);
			debugRenderer.begin(ShapeType.Line);
			debugRenderer.setColor(new Color(0, 1, 0, 1));
		}

		batch.begin();
		// Draw the Background
		backgroundSprite.draw(batch);
		// Draw the foreground
		foregroundSprite.draw(batch);

		scoreName = "score: " + st.score;
		bitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		bitmapFontName.draw(batch, scoreName, 10, imageProvider.getScreenHeight() - 15);

		batch.draw(bobRegion, bob.getPosition().x, bob.getPosition().y);
		if (game.isDebugOn) {
			debugRenderer.rect(bob.getPosition().x, bob.getPosition().y,
					bob.getPosition().width, bob.getPosition().height);
		}

		for (MovingGameObject movingObject : movingObjects) {
			movingObject.draw(batch);
			if (game.isDebugOn) {
				movingObject.drawDebug(debugRenderer);
			}

			if (movingObject.isCollided) {
				levelManager.doLevelTransition(movingObject.doCollision(delta), st);
			}
		}
		
		if (st.isDead){
			//Gdx.app.log(this.toString(), "YOU ARE DEAD!!!!!!!");
			//movingObjects.clear();
        	if (gameOverMessage == null) {
        		gameOverMessage = new GameOverMessage(imageProvider, st.score);
        	}
        	gameOverMessage.draw(batch);
        	resetButton.draw(batch);
        	homeButton.draw(batch);
		}

		if (st.isPaused) {
			batch.draw(pauseScreen, 0, 0);
		}

		batch.end();

		if (game.isDebugOn) {
			debugRenderer.end();
		}

		processInput(delta);

		if (st.isPaused || st.isDead) {
			return;
		}

		st.score += st.standardMovingBonus * delta;

		if (TimeUtils.millis() - st.lastGameObjTime > st.spawnTime) {
			movingObjects.add(levelManager.getNextObject(movingGameObjectFactory,st));
		}

		/*
		 * Using Iterator, we update all objects on screen to move, and discard
		 * all objects off screen All hit detection happens here also
		 */
		Iterator<MovingGameObject> iter = movingObjects.iterator();
		while (iter.hasNext()) {
			MovingGameObject fo = iter.next();

			fo.moveLeft(delta, st.gameSpeed);

			if (fo.isLeftOfScreen()) {
				iter.remove();
			}

			if (fo.isOverlapping(bob.getPosition())) {
				fo.isCollided = true;
			}
		}

	}

	private void processInput(float delta) {
		if (st.isLevelStarted && !st.isPaused && !st.isDead){
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
	}

	@Override
	public void pause() {
		if (!st.isPaused && st.isLevelStarted) {
			soundManager.togglePauseMusic();
			st.isPaused = true;

			long pausedTime = TimeUtils.millis();

			st.lastGameObjTime -= pausedTime;
			st.roundEndTime -= pausedTime;

			st.movingObjectStates = new Array<MovingGameObjectState>();
			for(MovingGameObject fo: movingObjects) {
				st.movingObjectStates.add(fo.getState());
			}
			st.bobX = (int) bob.getPosition().x;
			st.bobY = (int) bob.getPosition().y;
		}
		game.persist(st);
	}

	@Override
	public void resume() {
		if (st.isPaused) {
			soundManager.togglePauseMusic();
			st.isPaused = false;
			long now = TimeUtils.millis();
			st.lastGameObjTime += now;
			st.roundEndTime += now;		
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
			Gdx.app.exit();
		}
		if(keycode == Keys.P) {
			if (st.isPaused)
				resume();
			else
				pause();
		}	
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (st.isPaused && !st.isDead)
			resume();
		if (st.isDead){
			Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        camera.unproject(touchPos);    
	        
			if(homeButton.isPressed(touchPos)){
				Gdx.app.exit();
			}
			if(resetButton.isPressed(touchPos)){
				st.hardReset();
				movingObjects.clear();
				bob.setPositionX(st.bobX);
				bob.setPositionY(st.bobY);
			}
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(st.isLevelStarted && !st.isPaused && !st.isDead){
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
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(st.isLevelStarted && !st.isPaused && !st.isDead){

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
		}
		return true;
	}
}
