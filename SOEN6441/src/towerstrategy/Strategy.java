package towerstrategy;

import java.util.ArrayList;
import towers.TowerBase;
import critters.CritterBase;

/**
 * This class provides an interface to be implemented by other classes.
 * The strategy part used Strategy Pattern.
 * @author Yulong SONG, Yichen LI
 *
 */

public interface Strategy {
	
	
	/**
	 * To execute specific strategy.
	 * 
	 * @param al The array list to save the critters which need to be check.
	 * @param towerShooter The Tower which use this strategy.
	 * @return CritterBase Return the critter which should be attacked. 
	 */
	CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter);
}
