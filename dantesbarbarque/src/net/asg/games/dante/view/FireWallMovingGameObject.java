package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class FireWallMovingGameObject extends MovingGameObject {
	protected Rectangle lowerWall;
	public final int NORMAL_HOLE_SIZE = 125;
	public final int MEDIUM_HOLE_SIZE = 145;
	public final int LARGE_HOLE_SIZE = 165;
	public final int POSITION_ONE = 0;
	public final int POSITION_TWO = 50;
	public final int POSITION_THREE = 150;
	public final int POSITION_FOUR = 200;
	public final int POSITION_FIVE = 250;
	public final int POSITION_SIX = 300;
	private final int WALL_BASE_OFFSET = 450;
	private int position;
	private int holeSize;
	public boolean isMobile = false;
	private int mobileSpeed = 200;
	private int isMovingDown = 0;

	public FireWallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager,
			int width, int height, boolean isHitboxActive) {
		super(imageProvider, textureRegions, soundManager, width, height,
				isHitboxActive);

		this.rect = new Rectangle();
		this.rect.width = width;
		this.rect.height = height;

		position = MathUtils.random(0, 6) * 50;
		holeSize = MEDIUM_HOLE_SIZE;

		this.lowerWall = new Rectangle();
		this.lowerWall.width = width;
		this.lowerWall.height = height;

		this.rect.x = this.imageProvider.getScreenWidth();
		this.rect.y = WALL_BASE_OFFSET - position;

		this.lowerWall.x = this.imageProvider.getScreenWidth();
		this.lowerWall.y = WALL_BASE_OFFSET - rect.height - holeSize - position;

		this.setAnimationSpeed(0.1f);
	}

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegions[frame], rect.x, rect.y);
		batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setHoleSize(int holeSize) {
		this.holeSize = holeSize;
	}

	public void moveLeft(float delta) {
		rect.x -= moveSpeed * delta;
		lowerWall.x -= moveSpeed * delta;

		if (isMobile) {
			switch (isMovingDown) {
			case 0:
				rect.y += mobileSpeed * delta;
				lowerWall.y += mobileSpeed * delta;
				if (rect.y + 50 > this.imageProvider.getScreenHeight())
					isMovingDown = 1;
				break;
			case 1:
				rect.y -= mobileSpeed * delta;
				lowerWall.y -= mobileSpeed * delta;
				if (lowerWall.y + lowerWall.height - 50 < 0)
					isMovingDown = 0;
				break;
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
