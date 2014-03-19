/**
 * 
 */
package net.asg.games.dante.view;

/**
 * @author Blakbro2k
 *
 */
public enum MovingObjectType {
	
    	Fireball(0),
	    OpenWall(1),
	    CloseWall(2),
	    StaticWall(3);
	    
	    private final int value;
	    
	    public int getValue() {
	        return value;
	    }
	    
	    private MovingObjectType(int value) {
	        this.value = value;
	    }
	    
	    public static MovingObjectType fromValue(int value) {
	        switch(value) {
	            case 0:
	                return Fireball;
	            case 1:
	                return OpenWall;
	            case 2:
	                return CloseWall;
	            case 3:
	                return StaticWall;                
	        }
	        throw new ArrayIndexOutOfBoundsException("Wrong value");    
	    }
	}

