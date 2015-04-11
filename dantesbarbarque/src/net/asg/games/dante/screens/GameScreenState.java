/**
 * 
 */
package net.asg.games.dante.screens;

import net.asg.games.dante.Constants;
import net.asg.games.dante.view.MovingObjectState;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
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
	
	public void softReset(){
		lastStageType = stageType;
		stageType = 0;
		spawnTime = 2200;
		isLevelStarted = false;
	}
	
	public void hardReset(){
		score = 0;
		roundCount = 0;
		stageType = 0;
		spawnTime = 2200;
		isLevelStarted = false;
		lastStageType = 0;
		lastGameObjTime = 0;
		bobX = 20;
		bobY = -1;
		isFinished = false;
		roundEndTime = TimeUtils.millis() + Constants.ROUND_TIME_DURATION;
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("score", score);
		json.writeValue("bobX", bobX);
		json.writeValue("bobY", bobY);
		json.writeValue("spawnTime", spawnTime);
		json.writeValue("roundCount", roundCount);
		json.writeValue("stageType", stageType);
	    json.writeValue("lastStageType", lastStageType);
		json.writeValue("standardMovingBonus", standardMovingBonus);
		json.writeValue("isPaused", isPaused);
		json.writeValue("isLevelStarted", isLevelStarted);
		json.writeValue("isFinished", isFinished);
		json.writeValue("isEndless", isEndless);
		json.writeValue("gameSpeed", gameSpeed);
		json.writeValue("lastGameObjTime", lastGameObjTime);
		json.writeValue("roundEndTime", roundEndTime);
		
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
		spawnTime = json.readValue("spawnTime", Integer.class, jsonData);
		roundCount = json.readValue("roundCount", Integer.class, jsonData);
		stageType = json.readValue("stageType", Integer.class, jsonData);
		lastStageType = json.readValue("lastStageType", Integer.class, jsonData);
		standardMovingBonus = json.readValue("standardMovingBonus", Integer.class, jsonData);
		isPaused = json.readValue("isPaused", Boolean.class, jsonData);
		isLevelStarted = json.readValue("isLevelStarted", Boolean.class, jsonData);
		isFinished = json.readValue("isFinished", Boolean.class, jsonData);
		isEndless = json.readValue("isEndless", Boolean.class, jsonData);
		gameSpeed = json.readValue("gameSpeed", Long.class, jsonData);
		lastGameObjTime = json.readValue("lastGameObjTime", Long.class, jsonData);
		roundEndTime = json.readValue("roundEndTime", Long.class, jsonData);
		
		movingObjectStates = json.readValue( "movingObjects", Array.class,
				MovingObjectState.class, jsonData ); 
	}

}
