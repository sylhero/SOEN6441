package towerstrategy;

import java.util.ArrayList;
import towers.TowerBase;
import critters.CritterBase;

/**
 * This class provides an interface to be implemented by other classes.
 * @author Yulong Song, Yichen LI
 *
 */

public interface Strategy {
	
	
	/**
	 * To execute specific strategy.
	 * @param al
	 * @param towerShooter
	 * @return
	 */
	CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter);
}
