package usefulfunctions;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * 
 * @author Yichen LI
 *
 */
public class ValidateMap {

	private static int entranceCount = 0;
	private static int exitCount = 0;
	
	public static boolean validateEntrace(Tile [][] map)
	{
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.ENTRANCE)
				{
					entranceCount++;
				}
			}		
		}
		
		if(entranceCount == 1) // if only one entrance exists
			return true;
		else 
			return false;
		
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	public static boolean validateExit(Tile [][] map)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.EXIT)
				{
					exitCount++;
				}
			}		
		}
		
		if(exitCount == 1)  // if only one exit exists
			return true;
		else 
			return false;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
}


