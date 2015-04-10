/**
 * 
 */
package net.asg.games.dante.screens;

import net.asg.games.dante.Constants;
import net.asg.games.dante.view.MovingObjectState;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author Blakbro2k
 *
 */
public class GameScreenState implements Serializable {
	public enum LevelState{
		GOALHIT, FIREBALLHIT, WALLHIT, ISPAUSED, GAMEOVER
	}
	
	public int score;
    public int bobX;
    public int bobY;
	public int spawnTime;
	public int roundCount;
    public int stageType;
	public int lastStageType;
	public int standardMovingBonus = 1000;

    public boolean isPaused;
    public boolean isLevelStarted;
    public boolean isFinished;
	public boolean isEndless;


	public float gameSpeed = Constants.DEFAULT_GAME_SPEED;
	
	public long lastGameObjTime  = 0;
	public long roundEndTime;
    
	public Array<MovingObjectState> movingObjectStates;

    public GameScreenState() {
    	movingObjectStates = new Array<MovingObjectState>();
    }
    
	public float getBackgroundSpeed() {
		return Constants.BACKGROUND_SPEED * gameSpeed;
	}
	
	public float getForegroundSpeed() {
		return Constants.FOREGROUND_SPEED * gameSpeed;
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("score", score);
		json.writeValue("bobX", bobX);
		json.writeValue("bobY", bobY);
		json.writeValue("isPaused", isPaused);
		json.writeValue("isStarted", isLevelStarted);
		json.writeValue("isFinished", isFinished);
		
		json.writeArrayStart("movingObjects");
		for(MovingObjectState movingObjectState: movingObjectStates) {
			json.writeValue(movingObjectState, MovingObjectState.class);
		}
		json.writeArrayEnd();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		score = json.readValue("score", Integer.class, jsonData);
		bobX = json.readValue("bobX", Integer.class, jsonData);
		bobY = json.readValue("bobY", Integer.class, jsonData);
		isPaused = json.readValue("isPaused", Boolean.class, jsonData);
		isLevelStarted = json.readValue("isStarted", Boolean.class, jsonData);
		isFinished = json.readValue("isFinished", Boolean.class, jsonData);
		
		movingObjectStates = json.readValue( "movingObjects", Array.class,
				MovingObjectState.class, jsonData ); 
	}

}
