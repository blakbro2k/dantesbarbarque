package net.asg.games.dante.view;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import net.asg.games.dante.images.ImageProvider;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Blakbro2k This class defines the moving object on the screen of the
 *         game.
 * 
 *         It requires an image provider to handle the textures. It returns an
 *         array of texture regions if the object has animations
 */
public class MovingGameObject {

	protected int moveSpeed = 750;

	protected float animationPeriod = 0.3f;

	protected int width;

	protected int height;

	protected Rectangle rect;

	protected int frame = 0;

	protected float time = 0;

	protected boolean isHitboxActive;

	protected TextureRegion[] textureRegions;

	protected ImageProvider imageProvider;

	protected Rectangle getPosition() {
		return rect;
	}

	public MovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, int width, int height, boolean isHitboxActive) {
		this.imageProvider = imageProvider;
		this.textureRegions = textureRegions;
		this.isHitboxActive = isHitboxActive;
		this.height = height;
		this.width = width;
		// this.state = state;

		/*
		 * if(state.getPosX() < 0 || state.getPosY() < 0) { rect.x =
		 * MathUtils.random(0, imageProvider.getScreenWidth()-width); rect.y =
		 * imageProvider.getScreenHeight(); } else { rect.x = state.getPosX();
		 * rect.y = state.getPosY(); }
		 * 
		 * state.setPosX((int) rect.x); state.setPosY((int) rect.y);
		 */
	}

	public void moveLeft(float delta) {
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

	public boolean isLeftOfScreen() {
		return rect.x + width < 0;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegions[frame], rect.x, rect.y);
	}

	public boolean isOverlapping(Rectangle otherRect) {
		return rect.overlaps(otherRect);
	}

	public boolean isHitboxActive() {
		return isHitboxActive;
	}

	public void setHitboxActive(boolean bool) {
		isHitboxActive = bool;
	}
	public void setMoveSpeed(int moveSpeed){
		this.moveSpeed = moveSpeed;
	}
	
	public void setAnimationSpeed(float f){
		this.animationPeriod = f;
	}
}
