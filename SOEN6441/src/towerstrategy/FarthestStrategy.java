package towerstrategy;

import java.util.ArrayList;
import java.util.Iterator;

import tilemap.TileMap;
import towers.TowerBase;
import critters.CritterBase;
/**
 * 
 * @author Xunrong Xia
 *
 */
public class FarthestStrategy implements Strategy{
	private static final int FARTHEST = 4;

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

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return FARTHEST;
	}


}
