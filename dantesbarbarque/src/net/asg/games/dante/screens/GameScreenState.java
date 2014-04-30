/**
 * 
 */
package net.asg.games.dante.screens;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author Blakbro2k
 *
 */
public class GameScreenState implements Serializable {
	public int score;
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.utils.Json.Serializable#write(com.badlogic.gdx.utils.Json)
	 */
	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("score", score);

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.utils.Json.Serializable#read(com.badlogic.gdx.utils.Json, com.badlogic.gdx.utils.OrderedMap)
	 */
	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		score = json.readValue("score", Integer.class, jsonData);

	}

}
