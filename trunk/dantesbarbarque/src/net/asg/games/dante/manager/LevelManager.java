package net.asg.games.dante.manager;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.view.FireWallMovingGameObject;
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
	private int roundDuration = 6000;

	public LevelManager(boolean stageType) {
		this.endless = stageType;
		this.roundEndTime = TimeUtils.millis() + roundDuration;
		init();
	}

	private void init() {
		if (isEndless()) {
			roundCount = 0;
			stageType = (MathUtils.random(0, 2));
			switch (stageType) {
			case 0:
				objectSpeed = 0.8f;
				spawnTime = 360;
				break;
			case 1:
				objectSpeed = 0.8f;
				spawnTime = 1360;
				break;
			case 2:
				objectSpeed = 0.8f;
				spawnTime = 1100;
				break;
			}
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
		out += "lastGameObjTime = " + lastGameObjTime + "\n";
		out += "spawnTime = " + spawnTime + "\n";
		out += "endless = " + endless + "\n";
		out += "roundCount = " + roundCount + "\n";
		return out;
	}

	public MovingGameObject getNextObject(
			MovingGameObjectFactory movingGameObjectFactory) {

		MovingGameObject mObj = null;

		switch (stageType) {
		case 0:
			lastGameObjTime = TimeUtils.millis();
			mObj = movingGameObjectFactory.getFireball();
			break;
		case 1:
			lastGameObjTime = TimeUtils.millis();
			mObj = movingGameObjectFactory.getDynamicFireWall();
			//mObj.setMoveSpeed(100);
			break;
		case 2:
			lastGameObjTime = TimeUtils.millis();
			spawnTime = 1100;
			mObj = movingGameObjectFactory.getFireWall();
			break;
		}	
		return processLevelDesign(mObj);
	}

	private MovingGameObject processLevelDesign(MovingGameObject mObj) {
		if(mObj instanceof FireWallMovingGameObject){
			((FireWallMovingGameObject) mObj).isMobile = true;
		}
		
		return mObj;
	}
	/*
	 * private void spawnFireWallMovingGameObject() {
	 * movingObjects.add(movingGameObjectFactory.getFireWall());
	 * soundManager.playfirewooshSound();
	 * levelManager.setLastGameObjectTime(TimeUtils.millis()); }
	 * 
	 * private void spawnDynamicFireWallMovingGameObject() {
	 * movingObjects.add(movingGameObjectFactory.getDynamicFireWall());
	 * soundManager.playfirewooshSound();
	 * levelManager.setLastGameObjectTime(TimeUtils.millis()); }
	 * 
	 * private void spawnFireballMovingGameObject() {
	 * movingObjects.add(movingGameObjectFactory.getFireball());
	 * soundManager.playflameBurstSound();
	 * levelManager.setLastGameObjectTime(TimeUtils.millis()); }
	 */

}
