package usefulfunctions;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * 
 * @author Yichen LI
 *
 *
 */
public class ValidateMap {

	private static int entranceCount = 0;
	private static int exitCount = 0;
	private static int [][] pathGrid;
	private final static int CHECKED = 20;
	private final static int VALID_PATH = 30;
	
	//======================================validate the entry point==========================================
	
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
	
	
	//=========================================validate the exit point==============================================
	
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
	
	//==========================================validate the path ========================================================
	
	public static boolean validatePath(int row, int column)
	{
		
		boolean done = false;
		
		if(valide (row, column))
		{
			pathGrid[row][column] = CHECKED; // indicate the tile is checked
			
			if(pathGrid[row][column] == TileMap.EXIT || (row == pathGrid.length - 1 && column == pathGrid[0].length - 1))
				done = true;
			else
			{
				done = validatePath(row + 1, column);  // down	
				if(!done)
					done = validatePath(row, column + 1); // right
				if(!done)
					done = validatePath(row - 1, column); // up
				if(!done)
					done = validatePath(row, column - 1); // left
			}
			
			if(done) // the location is part of the path
				pathGrid[row][column] = VALID_PATH; 
			
		}
		return false;
		
	}

	
	//---------------------------------------------
	// determine if specific location is valid
	//---------------------------------------------

	private static boolean valide(int row, int column) 
	{
		boolean result = false;
		
		// check if the tile is in the bounds of the map
		if(row >= 0 && row < pathGrid.length && column >= 0 && column < pathGrid[row].length)
		{
			// check if the tile is not blocked and not previously checked
			if(pathGrid[row][column] == TileMap.PAVEMENT)
				result = true;
		}
				
		return result;
	}
}


