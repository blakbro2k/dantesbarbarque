package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MovingGameObjectFactory {

	private ImageProvider imageProvider;
	private SoundManager soundManager;

	public MovingGameObjectFactory(ImageProvider imageProvider, SoundManager soundManager){
        this.imageProvider = imageProvider;
        this.soundManager = soundManager;
		//System.out.println(soundManager);
	}
	
    public FireBallMovingGameObject getFireball() {
        TextureRegion [] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getFireball();

        return new FireBallMovingGameObject(imageProvider, textureRegions, soundManager, textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), false);
    }
    
    public FireWallMovingGameObject getFireWall() {
        TextureRegion [] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);
        
        return new FireWallMovingGameObject(imageProvider, textureRegions, soundManager, textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), false);
    }
    
    public DynamicFireWallMovingGameObject getDynamicFireWall() {
        TextureRegion [] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);

        return new DynamicFireWallMovingGameObject(imageProvider, textureRegions, soundManager, textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), false);
    }
}
