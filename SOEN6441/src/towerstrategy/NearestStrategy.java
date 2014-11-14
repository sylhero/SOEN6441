package towerstrategy;

import java.util.ArrayList;
import java.util.Iterator;

import tilemap.TileMap;
import towers.TowerBase;
import critters.CritterBase;

/**
 * This class implemented Strategy interface and return the nearest critter among an arrayList.
 * 
 * @author Xunrong Xia, Hongrui Guan
 *
 */

public class NearestStrategy implements Strategy{


	/**
	 * To find the nearest critter among a critter arrayList.
	 */
	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,TowerBase towerShooter) {
		// TODO Auto-generated method stub
		Iterator<CritterBase> it = al.iterator();
		double nearestDistance = 0;
		CritterBase nearestCritter = null;
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
			
			if (nearestDistance == 0) 
			{
				nearestDistance = distance;
				nearestCritter = critter;
				
			} else if (distance < nearestDistance)
			{
				nearestDistance = distance;
				nearestCritter = critter;
			}
			
		}
		return nearestCritter;
	}
}
