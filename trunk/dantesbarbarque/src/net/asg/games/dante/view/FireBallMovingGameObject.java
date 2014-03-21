package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Blakbro2k
 *
 */
public class FireBallMovingGameObject extends MovingGameObject{

	public FireBallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, width, height, isHitboxActive);
		this.setMoveSpeed(820);

		rect = new Rectangle();
		rect.width = width;
		rect.height = height;

		rect.x = this.imageProvider.getScreenWidth();
		rect.y = MathUtils.random(0, imageProvider.getScreenHeight() - height);
	}	
}
