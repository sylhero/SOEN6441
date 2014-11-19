package towerstrategy;

import java.util.ArrayList;
import java.util.ListIterator;

import tilemap.TileMap;
import towers.TowerBase;
import usefulfunctions.ValidateMap;
import critters.CritterBase;
/**
 * This class returns the critter who is nearest to the exit.
 * 
 * @author Yichen LI
 * @version 1.2.0
 *
 */
public class NearestToExitStrategy implements Strategy{

	/**
	 * To return the critter closest to the exit point.
	 */
	
	
	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,TowerBase towerShooter) {
		
		CritterBase nearestCri = null;
		double nearestDis = 0;
		
		// To get exit coordinates
		int exit_X = ValidateMap.getExitY() * TileMap.getTileMap().getCellWidth();
		int exit_Y = ValidateMap.getExitX() * TileMap.getTileMap().getCellHeight();
		
		for (ListIterator<CritterBase> iterator = al.listIterator(); iterator.hasNext();)
		{
			
			CritterBase critter = (CritterBase) iterator.next();
			
			// To get critter coordinates
			int cri_X = critter.getX() + TileMap.getTileMap().getCellHeight() / 2;
			int cri_Y = critter.getY() + TileMap.getTileMap().getCellWidth() / 2;
						
			double currentDis = Math.sqrt(Math.pow(cri_X - exit_X, 2) + Math.pow(cri_Y - exit_Y, 2));
			
			if(nearestDis == 0) 
			{				
				nearestDis = currentDis;
				nearestCri = critter;
			}
			else if(nearestDis > currentDis)
			{
				nearestDis = currentDis;
				nearestCri = critter;
			}			
		}
		
		return nearestCri;
		
	}
}
