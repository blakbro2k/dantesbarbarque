package net.asg.games.dante.manager;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.DantesBarbarqueGame.LevelState;
import net.asg.games.dante.view.FireWallMovingGameObject;
import net.asg.games.dante.view.GoalMovingGameObject;
import net.asg.games.dante.view.MovingGameObject;
import net.asg.games.dante.view.MovingGameObjectFactory;

public class LevelManager {
	public int stageType;
	// FIREBALL, STATIC_WALL, DYNAMIC_WALL,
	private float backgroundSpeed = 0.8f;
	private float objectSpeed;
	private long lastGameObjTime = 0;
	private int spawnTime;
	private boolean endless;
	private int roundCount;
	private long roundEndTime;
	private boolean isLevelStarted = false;
	//private long roundStartTime;
	private int roundDuration = 18000;

	// private

	public LevelManager(boolean stageType) {
		this.endless = stageType;
		this.roundEndTime = TimeUtils.millis() + roundDuration; // set round
																// length
		init();
	}

	private void init() {
		if (isEndless()) {
			roundCount = 0;
			stageType = 0;
			isLevelStarted = true;
			objectSpeed = 0.8f;
			spawnTime = 1800;
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
		return objectSpeed;
	}

	public float getObjectSpeed() {
		return backgroundSpeed;
	}

	public void nextRound() {
		roundCount++;
	}

	public String toString() {
		String out = "";
		out += "backgroundSpeed = " + backgroundSpeed + "\n";
		out += "objectSpeed = " + objectSpeed + "\n";
		out += "lastGameObjTime = " + (TimeUtils.millis() - lastGameObjTime) + "\n";
		out += "spawnTime = " + spawnTime + "\n";
		out += "endless = " + endless + "\n";
		out += "roundCount = " + roundCount + "\n";
		return out;
	}

	public MovingGameObject getNextObject(
			MovingGameObjectFactory movingGameObjectFactory) {

		MovingGameObject mObj = null;
		
		if (TimeUtils.millis() > roundEndTime) {
			stageType = 0;
			spawnTime = 1800;
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
			if (roundCount > 2)
			((FireWallMovingGameObject) mObj).isMobile = true;
		}
		
		if (mObj instanceof GoalMovingGameObject) {

		}
		

		
		return mObj;
	}

	public void doLevelTransition(LevelState state) {
		// TODO Auto-generated method stub
		if (state == LevelState.GOALHIT && !isLevelStarted) {
			stageType = (MathUtils.random(1, 3));
			//roundStartTime = TimeUtils.millis();
			roundEndTime = TimeUtils.millis() + roundDuration;
			roundCount ++;
			isLevelStarted = true;
		}
	}
}
