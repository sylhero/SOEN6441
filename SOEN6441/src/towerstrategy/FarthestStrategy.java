package towerstrategy;

import java.util.ArrayList;
import java.util.Iterator;

import tilemap.TileMap;
import towers.TowerBase;
import critters.CritterBase;
/**

 * This class returns the critter with the farthest distance in the array list.
 * @author Xunrong Xia, Kun Wang

 *
 */
public class FarthestStrategy implements Strategy{

	/**
	 * This method is used to find the critter which is farthest with the tower,
	 * and return it to the tower. 
	 * <p>
	 * This method use the simple algorithm to find the biggest one. 
	 * First, set a temp variable equal to zero.
	 * Then, compare the distance of each critter in the array list with the temp variable in order. 
	 * If the critter's distance bigger than the temp distance, set the temp equal to the critter's distance.
	 * After compare all the critter in the array list, the temp would save the farthest distance.
	 * Then return the critter which is the farthest. 
	 *  
	 */

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,TowerBase towerShooter) {
		// TODO Auto-generated method stub
		Iterator<CritterBase> it = al.iterator();
		double farthestDistance = 0;
		CritterBase farthestCritter = null;
		while(it.hasNext())
		{
			CritterBase critter = (CritterBase) it.next();
			int critterX = critter.getX();
			int critterY = critter.getY();
			int critterCenterX = critterX + TileMap.getTileMap().getCellHeight() / 2;
			int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;
			int towerCenterX   = towerShooter.getTileX() + TileMap.getTileMap().getCellHeight() / 2;
			int towerCenterY   = towerShooter.getTileY() + TileMap.getTileMap().getCellWidth() / 2;
			double distance = Math.sqrt(Math.pow(critterCenterX-towerCenterX, 2) + 
					Math.pow(critterCenterY-towerCenterY, 2));
			
			if (distance>farthestDistance)
			{
				farthestDistance = distance;
				farthestCritter = critter;
			}
		}
		return farthestCritter;
	}
}
