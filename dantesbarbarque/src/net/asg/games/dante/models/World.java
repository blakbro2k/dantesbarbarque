package net.asg.games.dante.models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	/** The blocks making up the world **/
	Array<Block> blocks = new Array<Block>();
	/** Our player controlled hero **/
	Bob bob;
	private Game game;

	// Getters -----------
	public Array<Block> getBlocks() {
		return blocks;
	}
	public Bob getBob() {
		return bob;
	}
	// --------------------

	public World(Game game) {
		this.game = game;
		createDemoWorld();
	}
	
	public Game getGame(){
		return game;
	}

	private void createDemoWorld() {
		bob = new Bob(new Vector2(7, 2));

		for (int i = 0; i < 10; i++) { 			 			
			blocks.add(new Block(new Vector2(i, 0))); 			 			
			blocks.add(new Block(new Vector2(i, 6))); 			 			
			if (i > 2)
				blocks.add(new Block(new Vector2(i, 1)));
		}
//		col, row
		blocks.add(new Block(new Vector2(9, 2)));
		blocks.add(new Block(new Vector2(9, 3)));
		blocks.add(new Block(new Vector2(9, 4)));
		blocks.add(new Block(new Vector2(9, 5)));

		blocks.add(new Block(new Vector2(6, 3)));
		blocks.add(new Block(new Vector2(6, 4)));
		blocks.add(new Block(new Vector2(6, 5)));
	}
}
