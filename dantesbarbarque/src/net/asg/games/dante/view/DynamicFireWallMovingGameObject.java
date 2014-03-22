package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	private int holeSize;
	private int wallSpeed = 250;
	private boolean isClosingType;

	public DynamicFireWallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager, int width, int height,
			boolean isHitboxActive) {
		super(imageProvider, textureRegions, soundManager, width, height, isHitboxActive);

		isClosingType = false;

		this.rect = new Rectangle();
		this.rect.width = width;
		this.rect.height = height;

		position = MathUtils.random(1, 3) * 50;
		//position = POSITION_THREE;
	
		this.lowerWall = new Rectangle();
		this.lowerWall.width = width;
		this.lowerWall.height = height;

		this.rect.x = this.imageProvider.getScreenWidth();
		this.rect.y = WALL_BASE_OFFSET - position;

		this.lowerWall.x = this.imageProvider.getScreenWidth();
		this.lowerWall.y = WALL_BASE_OFFSET - rect.height - position;

		this.setAnimationSpeed(0.2f);
		
		int close = MathUtils.random(0, 1);
		
		if (close == 1){
		setWallAsClosingType(true);
		}
	}

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegions[frame], rect.x, rect.y);
		batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
	}

	public void setWallAsClosingType(boolean wallType) {
		this.isClosingType = wallType;
		if (isClosingType) {
			this.rect.y = imageProvider.getScreenHeight() - position;
			this.lowerWall.y = 0 - height - position;
		}
	}

	public void moveLeft(float delta) {
		soundManager.playBuzzSound();
		
		rect.x -= moveSpeed * delta;
		lowerWall.x -= moveSpeed * delta;

		if (!isClosingType) {
			lowerWall.y -= wallSpeed * delta;
			rect.y += wallSpeed * delta;
		} else {
			lowerWall.y += wallSpeed * delta;
			rect.y -= wallSpeed * delta;
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
}
