package net.asg.games.dante.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageProvider {

    private int SCREEN_WIDTH = 800;
    
    private int SCREEN_HEIGHT = 480;
	
	private TextureAtlas atlas;
	
	private TextureAtlas textAtlas;
	
	private Texture backgroundFire;
	
	private Texture sign;	
	
	public ImageProvider() {
	}
	
	public void load() {
		atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
		textAtlas  = new TextureAtlas(Gdx.files.internal("text_images.atlas"));
		
		backgroundFire = new Texture(Gdx.files.internal("Fire_bg.png"));		
	}
	
	public void dispose() {
		atlas.dispose();
		textAtlas.dispose();
		backgroundFire.dispose();
	}
	
	public int getScreenWidth() {
		return SCREEN_WIDTH;
	}
	
	public int getScreenHeight() {
		return SCREEN_HEIGHT;
	}
	
	public Texture getBackgroundFire() {
		return backgroundFire;
	}
	
	public Texture getBoard() {
		return sign;
	}
			
	public TextureRegion getHelp() {
		return atlas.findRegion("Help");
	}
	
	public TextureRegion getButton() {
		return atlas.findRegion("button");
	}
	
	public TextureRegion getBack() {
		return atlas.findRegion("back");
	}
	
	public TextureRegion getGrey2() {
		return atlas.findRegion("number_two_grey");
	}
	
	public TextureRegion getGrey3() {
		return atlas.findRegion("number_three_grey");
	}
	
	public TextureRegion getNumber(int number) {
		return atlas.findRegion("number", number);
	}

	public TextureRegion getBob() {
		return atlas.findRegion("bob");
	}

	public TextureRegion getClockBase() {
		return atlas.findRegion("base");
	}

	public TextureRegion getSecond(int second) {
		return atlas.findRegion("second", second);
	}
	
	public TextureRegion getSecondRed(int second) {
		return atlas.findRegion("second_red", second);
	}

	public TextureRegion getPause() {
		return atlas.findRegion("player_pause");
	}
	
	public TextureRegion getSoundImage(boolean on) {
		if (on) {
			return atlas.findRegion("sound_on");
		}
		return atlas.findRegion("sound_off");
	}
	
	public TextureRegion getFireball() {
		return atlas.findRegion("Fireball");
	}
	
	public TextureRegion getFireWall(int frame) {
		return atlas.findRegion("firewall", frame);
	}
	
	/*public int getFruitsCount() {
		return FruitType.fruitNames.length;
	}

	public TextureRegion getFruit(int fruitType) {
		return atlas.findRegion(FruitType.fruitNames[fruitType]);
	}
	
	public TextureRegion getFruitBig(int fruitType) {
		String name = FruitType.fruitNames[fruitType] + "2";
		return atlas.findRegion(name);
	}	*/
	
	public TextureRegion getBadAppleFrame(int frame) {
		return atlas.findRegion("bad_apple", frame);
	}
	
	public TextureRegion getStarFrame(int frame) {
		return atlas.findRegion("star", frame);
	}

	public TextureRegion getMinusSign() {
		return atlas.findRegion("minus");
	}
	
	public TextureRegion getRestart() {
		return atlas.findRegion("restart");
	}

	public TextureRegion getTimes2() {
		return atlas.findRegion("x2");
	}	

	/**
	 * Text images
	 */
	
	public TextureRegion getLogo() {
		return textAtlas.findRegion("CatchTheFruits");
	}	
	
	public TextureRegion getStart() {
		return textAtlas.findRegion("Start");
	}
	
	public TextureRegion getKids() {
		return textAtlas.findRegion("Kids");
	}
	
	public TextureRegion getScores() {
		return textAtlas.findRegion("Scores");
	}
	
	public TextureRegion getUnlockedLabel() {
		return textAtlas.findRegion("Unlocked");
	}	
	
	public TextureRegion getLevel() {
		return textAtlas.findRegion("Level");
	}
	
	public TextureRegion getLevelGrey() {
		return textAtlas.findRegion("Level_grey");
	}
	
	public TextureRegion getSpringLabel() {
		return textAtlas.findRegion("SpringLabel");
	}
	
	public TextureRegion getSummerLabel() {
		return textAtlas.findRegion("SummerLabel");
	}
	
	public TextureRegion getAutumnLabel() {
		return textAtlas.findRegion("AutumnLabel");
	}
	
	public TextureRegion getWinterLabel() {
		return textAtlas.findRegion("WinterLabel");
	}	

	public TextureRegion getFruitsLabel() {
		return textAtlas.findRegion("fruits");
	}	

	public TextureRegion getTotalLabel() {
		return textAtlas.findRegion("Total");
	}	
	
	public TextureRegion getGoalLabel() {
		return textAtlas.findRegion("Goal");
	}
	
	public TextureRegion getPointsLabel() {
		return textAtlas.findRegion("Points");
	}	
	
	public TextureRegion getYouLoseLabel() {
		return textAtlas.findRegion("YouLose");
	}
	
	public TextureRegion getSuccessLabel() {
		return textAtlas.findRegion("Success");
	}


}