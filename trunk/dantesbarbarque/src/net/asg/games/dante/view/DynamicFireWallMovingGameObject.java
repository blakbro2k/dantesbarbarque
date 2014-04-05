package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class DynamicFireWallMovingGameObject extends MovingGameObject {
	protected Rectangle lowerWall;

	public final int NORMAL_HOLE_SIZE = 100;
	public final int MEDIUM_HOLE_SIZE = 150;
	public final int LARGE_HOLE_SIZE = 200;
	public final int POSITION_ONE = 0;
	public final int POSITION_TWO = 50;
	public final int POSITION_THREE = 150;
	public final int POSITION_FOUR = 200;
	public final int POSITION_FIVE = 250;
	public final int POSITION_SIX = 300;
	private final int WALL_BASE_OFFSET = 450;
	private int position;
	private int wallSpeed = 280; // speed of the wall closing or opeing
	private boolean isClosingType;

	public DynamicFireWallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager,
			int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, soundManager, width, height,
				isHitboxActive);
		
		if (MathUtils.random(0, 1) == 0) {
			isClosingType = true;
		} else {
			isClosingType = false;
		}
		
		//isClosingType = true;
		
		position = MathUtils.random(1, 4) * 50;
		
		//position = 5 * 50;

		this.rect = new Rectangle();
		this.rect.width = width;
		this.rect.height = height;

		this.lowerWall = new Rectangle();
		this.lowerWall.width = width;
		this.lowerWall.height = height;

		this.rect.x = this.imageProvider.getScreenWidth();
		this.rect.y = WALL_BASE_OFFSET - position;

		this.lowerWall.x = this.imageProvider.getScreenWidth();
		this.lowerWall.y = WALL_BASE_OFFSET - rect.height - position;

		this.setAnimationSpeed(0.2f);
		
		setWallAsClosingType(isClosingType);
	}

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegions[frame], rect.x, rect.y);
		batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
	}

	public void drawDebug(ShapeRenderer debugRenderer) {
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.rect(lowerWall.x, lowerWall.y, lowerWall.width,
				lowerWall.height);
	}

	public void setWallAsClosingType(boolean wallType) {
		this.isClosingType = wallType;
		if (isClosingType) {
			this.rect.y = imageProvider.getScreenHeight() - position;
			this.lowerWall.y = 0 - height - position;
		}
	}

	public void moveLeft(float delta, float speedBonus) {
		rect.x -= moveSpeed * delta + speedBonus;
		lowerWall.x -= moveSpeed * delta + speedBonus;

		if (!isClosingType) {
			lowerWall.y -= wallSpeed * delta;
			rect.y += wallSpeed * delta;
			if (rect.y < imageProvider.getScreenHeight()
					|| lowerWall.y + lowerWall.height > 0) {
				soundManager.playBuzzSound();
			}
		} else {
			if (rect.y > lowerWall.y + lowerWall.height) {
				lowerWall.y += wallSpeed * delta;
				rect.y -= wallSpeed * delta;
				soundManager.playBuzzSound();
			}
		}
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

	public boolean isOverlapping(Rectangle otherRect) {
		return rect.overlaps(otherRect) || lowerWall.overlaps(otherRect);
	}
}
