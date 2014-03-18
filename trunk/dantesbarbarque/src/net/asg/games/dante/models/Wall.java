package net.asg.games.dante.models;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Blakbro2k
 *
 */
public class Wall {
	protected final int WALL_TYPE_STATIC = 0;
	protected final int WALL_TYPE_CLOSE = 1;
	protected final int WALL_TYPE_OPEN = 2;
	private Block [] wall;
	private int wallType;
	
	/**
	 * @param wall
	 * @param wallType
	 */
	public Wall (int position, int wallType){
		wall = new Block [7];
		this.wallType = wallType;
		for(int i = 0; i < wall.length; i++){
			if (i != position)
				wall[i] = new Block(new Vector2(i, 5));
			else
				wall[i] = null;
		}
//		this.wall = wall;
	}
	
	/**
	 * @return
	 */
	public Block [] getWall(){
		return wall;
	}
	
	public int getWallType(){
		return wallType;
	}
}
