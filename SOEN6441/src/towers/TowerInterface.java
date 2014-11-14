package towers;

import java.util.ArrayList;

import critters.CritterBase;

/**
 * This the interface of Tower.
 * It contains two method.
 * <code>
 * void upgrade()
 * void fire(CritterBase critter)
 * </code>
 * Since every kind of tower can be upgrade as long as the coin is enough. 
 * And every kind of tower could attack critter if the critter satisfied some condition.
 * 
 * @author Xunrong Xia
 *
 */
public interface TowerInterface {
	
	/**
	 * in the upgrade method, some attributes's values of a tower may be increased. 
	 */
	void upgrade();
	/**
	 * The attack method of tower. 
	 * @param critter The critter which should be check if it is the attack target of the tower.
	 */
	void fire(CritterBase critter); 
}
