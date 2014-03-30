package net.asg.games.dante.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private boolean isSoundOn;

	private Sound cannonSound;

	private Sound flameBurst;
	
	private Sound firewoosh;

	private Sound buzzSound;
	
	private Sound goalHitSound;
	
	public void setSoundOn(boolean isSoundOn) {
		this.isSoundOn = isSoundOn;
	}
	
	public SoundManager() {
		
	}
	
	public void load() {
		cannonSound = Gdx.audio.newSound(Gdx.files.internal("cannon.ogg"));
        flameBurst = Gdx.audio.newSound(Gdx.files.internal("flameBurst2.ogg"));
        firewoosh = Gdx.audio.newSound(Gdx.files.internal("firewoosh.ogg"));
        buzzSound = Gdx.audio.newSound(Gdx.files.internal("buzz.ogg"));
        goalHitSound = Gdx.audio.newSound(Gdx.files.internal("goalHit.ogg"));
	}
	
	public void dispose() {
		cannonSound.dispose();
        flameBurst.dispose();
        firewoosh.dispose();
        buzzSound.dispose();
        goalHitSound.dispose();
	}
	
	public void playCannonSound() {
		if (isSoundOn) {
			cannonSound.play();
		}
	}

	public void playflameBurstSound() {
		if (isSoundOn) {
			flameBurst.play();
		}
	}
	
	public void playfirewooshSound() {
		if (isSoundOn) {
			firewoosh.play();
		}
	}
	
	public void playBuzzSound() {
		if (isSoundOn) {
			buzzSound.play();
		}
	}	
	
	public void playGoalHitSound() {
		if (isSoundOn) {
			goalHitSound.play();
		}
	}
}
