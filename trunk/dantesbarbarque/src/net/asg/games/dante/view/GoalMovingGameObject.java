/**
 * 
 */
package net.asg.games.dante.view;

import net.asg.games.dante.DantesBarbarqueGame.LevelState;
import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Blakbro2k
 * 
 */
public class GoalMovingGameObject extends MovingGameObject {
	protected boolean isSoundTriggered = false;
	private int velocityX = 1000;
	

	// private int animationPattern[];

	public GoalMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager,
			int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, soundManager, width, height,
				isHitboxActive);
	}

	public void moveLeft(float delta) {
		//velocityX += moveSpeed;
		rect.x -= moveSpeed * delta;
		// state.setPosY((int) rect.y);
		time += delta;
		if (time > animationPeriod) {
			time -= animationPeriod;
			frame += 1;
			if (frame >= textureRegions.length) {
				frame = 0;
			}
		}
	}

	public LevelState doCollision(float delta) {
		if(!isSoundTriggered){
			soundManager.playGoalHitSound();
			isSoundTriggered = true;
		}
		
		rect.y -= 1300 * delta;
		rect.x += velocityX  * delta;
		velocityX -= 1;
		return LevelState.GOALHIT;
	}
}
