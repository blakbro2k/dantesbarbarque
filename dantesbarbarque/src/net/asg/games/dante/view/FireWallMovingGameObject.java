package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class FireWallMovingGameObject  extends MovingGameObject{
	protected Rectangle upperWall;
	protected Rectangle lowerWall;

	public FireWallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, width, height, isHitboxActive);
		
		this.rect = new Rectangle();
		this.rect.width = width;
		this.rect.height = height;
		
		this.upperWall = new Rectangle();
		this.upperWall.width = width;
		this.upperWall.height = height;
		
		this.lowerWall = new Rectangle();
		this.lowerWall.width = width;
		this.lowerWall.height = height - 200;
		
		this.upperWall.x = this.imageProvider.getScreenWidth();
		this.upperWall.y = 0;
		
		this.lowerWall.x = this.imageProvider.getScreenWidth();
		this.lowerWall.y = 400;
		
		this.setAnimationSpeed(0.1f);
	}
	
	public void draw(SpriteBatch batch) {
		System.out.println("override");
		System.out.println("x: " + upperWall.x + " y:" + upperWall.y);
		batch.draw(super.textureRegions[super.frame], upperWall.x, upperWall.y);
		//batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
	}
}
