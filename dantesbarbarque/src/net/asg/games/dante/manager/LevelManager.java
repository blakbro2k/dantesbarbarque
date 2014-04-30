package net.asg.games.dante.manager;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.DantesBarbarqueGame.LevelState;
import net.asg.games.dante.view.FireBallMovingGameObject;
import net.asg.games.dante.view.FireWallMovingGameObject;
import net.asg.games.dante.view.MovingGameObject;
import net.asg.games.dante.view.MovingGameObjectFactory;

public class LevelManager {
	public int stageType;
	private int lastStageType;
	// FIREBALL, STATIC_WALL, DYNAMIC_WALL,
	private float backgroundSpeed = 0.3f;
	private float foregroundSpeed = 0.7f;
	private float speedBonus = 1.3f;
	private long lastGameObjTime = 0;
	private int spawnTime;
	private boolean endless;
	private int roundCount;
	private long roundEndTime;
	private boolean isLevelStarted = false;
	//private long roundStartTime;
	private int roundDuration = 21000;

	// private

	public LevelManager(boolean stageType) {
		this.endless = stageType;
		this.roundEndTime = TimeUtils.millis() + roundDuration; // set round
		lastStageType = 0;											// length
		init();
	}

	private void init() {
		if (isEndless()) {
			roundCount = 0;
			stageType = 0;
			spawnTime = 2200;
		}
	}

	public boolean isEndless() {
		return endless;
	}

	public int getSpawnTime() {
		return spawnTime;
	}

	public long getLastGameObjectTime() {
		return lastGameObjTime;
	}

	public void setLastGameObjectTime(long lastGameObjTime) {
		this.lastGameObjTime = lastGameObjTime;
	}

	public float getBackgroundSpeed() {
		return backgroundSpeed * speedBonus;
	}
	
	public float getForegroundSpeed() {
		return foregroundSpeed * speedBonus;
	}

	public float getSpeedBonus() {
		return speedBonus;
	}

	public void nextRound() {
		roundCount++;
	}

	public String toString() {
		String out = "";
		out += "backgroundSpeed = " + backgroundSpeed + "\n";
		out += "objectSpeed = " + speedBonus + "\n";
		out += "lastGameObjTime = " + (TimeUtils.millis() - lastGameObjTime) + "\n";
		out += "spawnTime = " + spawnTime + "\n";
		out += "stageType = " + stageType + "\n";
		out += "roundCount = " + roundCount + "\n";
		return out;
	}

	public MovingGameObject getNextObject(
			MovingGameObjectFactory movingGameObjectFactory) {

		MovingGameObject mObj = null;
		
		if (TimeUtils.millis() > roundEndTime) {
			lastStageType = stageType;
			stageType = 0;
			spawnTime = 2200;
			isLevelStarted = false;
		}

		switch (stageType) {
		case 0:
			lastGameObjTime = TimeUtils.millis();
			mObj = movingGameObjectFactory.getGoal();
			break;
		case 1:
			lastGameObjTime = TimeUtils.millis();
			mObj = movingGameObjectFactory.getFireball();
			spawnTime = 360;
			break;
		case 2:
			lastGameObjTime = TimeUtils.millis();
			mObj = movingGameObjectFactory.getDynamicFireWall();
			spawnTime = 1360;
			// mObj.setMoveSpeed(100);
			break;
		case 3:
			lastGameObjTime = TimeUtils.millis();
			spawnTime = 1100;
			mObj = movingGameObjectFactory.getFireWall();
			break;
		}
	
		return processLevelDesign(mObj);
	}

	private MovingGameObject processLevelDesign(MovingGameObject mObj) {
		if (mObj instanceof FireWallMovingGameObject) {
			if (roundCount > 2 && (MathUtils.random(0, 2) == 1))
			((FireWallMovingGameObject) mObj).isMobile = true;
		}
		
		if (mObj instanceof FireBallMovingGameObject) {
			if (roundCount > 6)
			((FireBallMovingGameObject) mObj).setAnimationSpeed(500);
		}
	
		return mObj;
	}

	public void doLevelTransition(LevelState state) {
		// TODO Auto-generated method stub
		if (state == LevelState.GOALHIT && !isLevelStarted) {

			Array <Integer>temp  = new Array<Integer>();
			for(int x = 1; x < 4; x++){
				if (x != lastStageType){
					temp.add(x);
				}
			}
			stageType = temp.get(MathUtils.random(0, temp.size - 1));
			//stageType = 1;
			roundEndTime = TimeUtils.millis() + roundDuration;
			roundCount ++;
			isLevelStarted = true;
		}
	}
}
