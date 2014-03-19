package net.asg.games.dante.view;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;


public abstract class MovingObjectState implements Serializable{
    public static final int FIREBALL = 1;
    public static final int FRUIT_IN_SEASON_POINTS = 2;
    public static final int BONUS_OBJECT_POINTS = 3;
    public static final int BAD_OBJECT_POINTS = -4;	
	
    private MovingObjectType type;
    
    private int index;
    
    private int points;
    
    private int posX = -1;
    
    private int posY = -1;

	public MovingObjectType getType() {
		return type;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
    
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void write(Json json) {
		json.writeValue("posX", posX);
		json.writeValue("posY", posY);
		json.writeValue("type", type.getValue());
		json.writeValue("points", points);
		json.writeValue("index", index);
	}

	public void read(Json json, JsonValue jsonData) {
		posX = json.readValue("posX", Integer.class, jsonData);
		posY = json.readValue("posY", Integer.class, jsonData);
		index = json.readValue("index", Integer.class, jsonData);
		points = json.readValue("points", Integer.class, jsonData);
		int val = json.readValue("type", Integer.class, jsonData);
		type = MovingObjectType.fromValue(val);
	}

}
