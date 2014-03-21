package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MovingGameObjectFactory {

	private ImageProvider imageProvider;

	public MovingGameObjectFactory(ImageProvider imageProvider){
        this.imageProvider = imageProvider;
	}
	
    public FireBallMovingGameObject getFireball() {
        TextureRegion [] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getFireball();

        return new FireBallMovingGameObject(imageProvider, textureRegions, textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), false);
    }
    
    public FireWallMovingGameObject getFireWall() {
        TextureRegion [] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);
        
        return new FireWallMovingGameObject(imageProvider, textureRegions, textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), false);
    }
}
