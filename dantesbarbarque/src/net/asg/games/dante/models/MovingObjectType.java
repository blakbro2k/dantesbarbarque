package net.asg.games.dante.models;

public enum MovingObjectType {
	    Fireball(0),
	    FireWall(1),
	    DynamicFireWall(2),
	    Goal(3);
	    
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
	                return FireWall;
	            case 2:
	                return DynamicFireWall;
	            case 3:
	                return Goal;                
	        }
	        throw new ArrayIndexOutOfBoundsException("Wrong value");    
	    }
}