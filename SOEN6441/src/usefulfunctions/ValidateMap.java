package usefulfunctions;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * The ValidateMap class implemented three functions which being used to check
 * entry, exit and path of user-defined map.
 * 
 * @author Yichen LI
 * @version 1.0.3
 *
 */

public class ValidateMap {

	private static int entranceCount = 0;
	private static int exitCount = 0;
	
	private static int entryX, entryY, exitX, exitY;
	private static int width, height;
	private static int localMap [][];
	private static boolean correctPath [][];
	private static boolean wasHere [][];
	
	//======================================validate the entry point==========================================
	
	/**
	 * This function helps to validate whether only one entry exists.
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if an entry validates  
	 * 
	 */
	public static boolean validateEntrace(Tile [][] map)
	{
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.ENTRANCE)
				{
					entranceCount++;
				}	
		}
		
		if(entranceCount == 1) // if only one entrance exists
			return true;
		else 
			return false;
		
	}
	
	
	//=========================================validate the exit point==============================================
	
	/**
	 * This function helps to validate whether only one exit exists.  
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if an exit validates  
	 * 
	 */
	
	
	public static boolean validateExit(Tile [][] map)
	{
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.EXIT)
				{
					exitCount++;
				}	
		}
		
		if(exitCount == 1)  // if only one exit exists
			return true;
		else 
			return false;
	}
	
	//==========================================validate the path ========================================================
	
	/**
	 * This function helps to validate whether a user-defined path reaches the exit. 
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if a user-defined path reaches the exit 
	 * 
	 */
	
	public static boolean validatePath(Tile [][] map)
	{
		init(map); // initialize parameters 
		
		boolean result = recursiveSolve(entryX, entryY);
		// will leave with a boolean array(correctPath)
		// with the path indicated by true values.
		// if result is false, there is no path to the exit
		
		flag = result;
		
		return result;
	}
	
	/**
	 * This function recursively check if a user-defined path validates.
	 * 
	 * @param entryX and entryY coordinates 
	 * @return a boolean which indicates if a user-defined path reaches the exit 
	 * 
	 */
	
	private static boolean recursiveSolve(int x, int y) {
	
		if(x == exitX && y == exitY) return true; // if reached the exit
		if(localMap[x][y] == TileMap.GRASS || wasHere[x][y])  return false; // if encountered grass or already been here
		
		wasHere[x][y] = true;
		
		if(x != 0) // checks if not on Left edge
			if(recursiveSolve(x - 1, y)) // recall method one to the left
			{
				correctPath[x][y] = true;
				return true;
			}
		
		if(x != width - 1) // check if not on right edge
			if(recursiveSolve(x + 1, y)) // recall method one to the right
			{
				correctPath[x][y] = true;
				return true;
			}
		
		if(y != 0) // check if not on top edge
			if(recursiveSolve(x, y - 1)) // recall method one up
			{
				correctPath[x][y] = true;
				return true;
			}
		
		if(y != height -1) // check if not on bottom edge
			if(recursiveSolve(x, y + 1)) // recall method one bottom
			{
				correctPath[x][y] = true;
				return true;
			}
		
		return false;
	}

	/**
	 * This function initializes member variables.  
	 * 
	 * @param map which contains tiles information 
	 * 
	 */

	private static void init(Tile [][]map) {
			
		for(int row = 0; row < map.length; ++row)
			for(int col = 0; col < map[row].length; ++col)
			{
				if(map[row][col].getTileType() == TileMap.ENTRANCE)
				{
					entryX = row; // record entry coordinates 
					entryY = col;
				}
					
				if(map[row][col].getTileType() == TileMap.EXIT)
				{
					exitX = row;  // record exit coordinates
					exitY = col;
				}
				
				width = row + 1;
				height = col + 1;
			}

		// initialize localMap, wasHere and correctPath
		localMap = new int[width][height];
		
		// generate localMap
		generate(map);
		
		wasHere = new boolean[width][height];
		correctPath = new boolean[width][height];
		
		// set boolean Arrays to default values
		for(int row = 0; row < map.length; ++row)
			for(int col = 0; col < map[row].length; ++col)
			{
				wasHere[row][col] = false;
				correctPath[row][col] = false;
			}
		
		
	}

	/**
	 * This function copies [][]map to [][]localMap.  
	 * 
	 * @param map which contains tiles information 
	 * 
	 */
	
	private static void generate(Tile [][] map) {
		
		for(int row = 0; row < map.length; ++row)
			for(int col = 0; col < map[row].length; ++col)
			{
				localMap[row][col] = map[row][col].getTileType();
			}
	}
	
	/**
	 * This function returns correctPath array.
	 * 
	 * @return a boolean two dimensions array which contains correctPath information
	 * 
	 */
	
	public static boolean [][] getCorrectPath()
	{
		return correctPath;
	}
	

}


