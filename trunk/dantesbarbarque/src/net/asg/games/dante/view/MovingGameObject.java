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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MovingGameObject {

    protected final int DROP_SPEED = 200;
    
    protected final float ANIMATION_PERIOD = 0.3f;

    protected int width = 48;
    
    protected int height = 48;
    
    protected Rectangle rect;
    
    protected int frame = 0;
    
    protected float time = 0;
    
    protected TextureRegion [] textureRegions;

	private ImageProvider imageProvider;
	
    protected Rectangle getPosition() {
        return rect;
    }

	public MovingGameObject(ImageProvider imageProvider, TextureRegion [] textureRegions) {
	        this.imageProvider = imageProvider;

    rect = new Rectangle();
    rect.width = width;
    rect.height = height;
    
    this.textureRegions = textureRegions;
    //this.state = state;
    
   /* if(state.getPosX() < 0 || state.getPosY() < 0) {
    	rect.x = MathUtils.random(0, imageProvider.getScreenWidth()-width);
    	rect.y = imageProvider.getScreenHeight();
    }
    else {
    	rect.x = state.getPosX();
    	rect.y = state.getPosY();
    }
    
    state.setPosX((int) rect.x);
    state.setPosY((int) rect.y);
}

public void moveDown(float delta) {
    rect.y -= DROP_SPEED * delta;
    state.setPosY((int) rect.y);
    time += delta;
    if (time > ANIMATION_PERIOD) {
    	time -= ANIMATION_PERIOD;
    	frame += 1;
    	if(frame >= textureRegions.length) {
    		frame = 0;
    	}
    }*/
}

public boolean isOffLeftScreen() {
    return rect.x + width < 0;
}
    
    public void draw(SpriteBatch batch) {
    	batch.draw(textureRegions[frame], rect.x, rect.y);
    }
    
    public boolean isOverlapping(Rectangle otherRect) {
        return rect.overlaps(otherRect);
    }
    
}
