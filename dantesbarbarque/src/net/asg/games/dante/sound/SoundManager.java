package net.asg.games.dante.sound;

import net.asg.games.dante.manager.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private boolean isSoundOn;

	private Sound cannonSound;

	private Sound flameBurst;
	
	private Sound firewoosh;

	private Sound buzzSound;
	
	private Sound goalHitSound;
	
	private Music bgStart;

	private Music bgLoop;
	
	private boolean bgStarted = false;
	
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
        bgStart = Gdx.audio.newMusic(Gdx.files.internal("btoad-start.ogg"));
        bgLoop = Gdx.audio.newMusic(Gdx.files.internal("btoad-loop.ogg"));
        bgStart.setLooping(false);
        bgLoop.setLooping(true);
	}
	
	public void dispose() {
		cannonSound.dispose();
        flameBurst.dispose();
        firewoosh.dispose();
        buzzSound.dispose();
        goalHitSound.dispose();
        bgStart.dispose();
        bgLoop.dispose();
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
	        long buzzSoundId = buzzSound.play();
	        buzzSound.setVolume(buzzSoundId, 0.1f);
		}
	}	
	
	public void playGoalHitSound() {
		if (isSoundOn) {
			goalHitSound.play();
		}
	}
	
	public void playBgSound() {
		if (isSoundOn) {
			if (!bgStarted){
			bgStart.play();
			bgStart.setOnCompletionListener(new Music.OnCompletionListener() {
		          @Override
		          public void onCompletion(Music music) {
		        	  bgLoop.play();
		          }
			});
			bgStarted = true;
			}
		}
	}
	

}
