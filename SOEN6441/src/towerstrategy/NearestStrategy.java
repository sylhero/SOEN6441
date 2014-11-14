package towerstrategy;

import java.util.ArrayList;
import java.util.Iterator;

import tilemap.TileMap;
import towers.TowerBase;
import critters.CritterBase;

/**
 * This class returns the critter with the nearest distance in the array list.
 * @author Xunrong Xia, Hongrui Guan
 *
 */

public class NearestStrategy implements Strategy{
	private static final int NEAREST = 3;

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return NEAREST;
	}

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
