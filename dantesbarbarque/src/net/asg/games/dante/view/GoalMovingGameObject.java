/**
 * 
 */
package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Blakbro2k
 *
 */
public class GoalMovingGameObject extends MovingGameObject{
	protected boolean isGoalHit = false;

	public GoalMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager,
			int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, soundManager, width, height,
				isHitboxActive);
		// TODO Auto-generated constructor stub
	}

}
