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
	 * @param al
	 * @param towerShooter
	 * @return CritterBase a critter
	 */
	CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter);
}
