package net.asg.games.dante.models;

import com.badlogic.gdx.math.Rectangle;

public class Bob {

	    private int SPEED = 510;

	    private int width = 72;
	    
	    private int height = 72;
	    
	    private int screenWidth;
	    
	    private int screenHeight;
	    
		private Rectangle bounds;

		public Bob(int screenHeight,int screenWidth, int posX, int posY) {
			bounds = new Rectangle();
			this.screenWidth = screenWidth;
			this.screenHeight = screenHeight;
			
	        if (posX < 0) {
	        	bounds.x = screenWidth / 2 - width / 2;
	        }
	        
	        else {
	        	bounds.x = posX;
	        }
	        
	       if (posY < 0) {
	        	bounds.y = screenHeight / 2 - height / 2;
	        }
	        
	        else {
	        	bounds.y = posX;
	        }
	       
	       // bounds.x = 20;
	        bounds.width = width;
	        bounds.height = height;
		}

	    public void setPositionX(float x) {
	    	bounds.x = x - width/2;
	        
	        keepOnScreen();
	    }
	    
	    public void setPositionY(float y) {
	    	bounds.y = y - height/2;
	        
	        keepOnScreen();
	    }

		public Rectangle getPosition() {
			return bounds;
		}

	    private void keepOnScreen() {
	        if (bounds.y < 0) {
	        	bounds.y = 0;
	        }
	       else if (bounds.y + height> screenHeight) {
	        	bounds.y = screenHeight - height;
	        }
	        if (bounds.x < 0) {
	        	bounds.x = 0;
	        }
	        else if (bounds.x + width > screenWidth) {
	        	bounds.x = screenWidth - width;
	        }
	    }
	    
	    public void moveX(float speedRatio, float delta) {
	    	bounds.x += speedRatio * SPEED * delta;
	        
	        keepOnScreen();
	    }
	    
	    public void moveY(float speedRatio, float delta) {
	    	bounds.y += speedRatio * SPEED * delta;
	        
	        keepOnScreen();
	    }


}
