package usefulfunctions;

import java.awt.Point;
import java.util.ArrayList;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * The ValidateMap class implemented three functions which being used to check
 * entry, exit and path of user-defined map.
 * 
 * @author Yichen LI
 * @version 1.0.5
 *
 */

public class ValidateMap {
	
	private static int entryX, entryY, exitX, exitY;
	private static int width, height;
	private static int localMap [][];
	private static boolean wasHere [][];
	private static ArrayList<Point> correctRoute = new ArrayList<Point>();
	
	//======================================validate the entry point==========================================
	
	/**
	 * This function helps to validate whether only one entry exists and is exit adjacent.
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if an entry validates  
	 * 
	 */
	public static boolean validateEntrance(Tile [][] map)
	{
		int x = 0, y = 0; // to save the coordinates of entry
		int entranceCount = 0;
		boolean isValid = false;
		boolean isExitAdjacent = false;
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.ENTRANCE)
				{
					entranceCount++;
					x = i;
					y = j;					
				}	
		}
		
		isExitAdjacent = isExitAdjacent(x, y, map);
		
		
		
		if(entranceCount == 1 && !isExitAdjacent) {// if only one entrance exists and no exit is adjacent
			entranceCount = 0;
			isValid = true;
			}
		else
		{
			isValid = false;
		}
			return isValid;
		
	}
	
	/**
	 * This function checks is the exit adjacent to the entry.
	 * 
	 * @param x
	 * @param y
	 * @param map
	 * @return isAdjacent
	 */
	private static boolean isExitAdjacent(int x, int y, Tile [][] map) {
		
		boolean isAdjacent = false;
		
		if (x != 0) // check if not on the left edge
			if(map[x - 1][y].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if (y != 0) // check if not on the top edge
			if(map[x][y - 1].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if(x != width - 1) // check if not on the right edge
			if(map[x + 1][y].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if(y != height - 1) // check if not on the bottom edge
			if(map[x][y + 1].getTileType() == TileMap.EXIT)
				isAdjacent = true;		
		
		return isAdjacent;
	}
	
	
	//=========================================validate the exit point==============================================

	/**
	 * This function helps to validate whether only one exit exists and is entry adjacent.  
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if an exit validates  
	 * 
	 */
	
	
	public static boolean validateExit(Tile [][] map)
	{
		int x = 0, y = 0; // to save the coordinates of exit 
		int exitCount = 0;
		boolean isValid = false;
		boolean isEntryAdjacent = false;
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.EXIT)
				{
					exitCount++;
					x = i;
					y = j;
				}	
		}
		
		isEntryAdjacent = isEntryAdjacent(x, y, map);
		
		
		
		if(exitCount == 1 && !isEntryAdjacent){  // if only one exit exists and no entry is adjacent
			isValid = true;
			exitCount = 0;
		}
		else 
		{
			isValid = false;			
		}
			return isValid;
	}
	
	/**
	 * This function checks is the entry adjacent to the exit. 
	 * 
	 * @param x
	 * @param y
	 * @param map
	 * @return isAdjacent
	 */
	
	private static boolean isEntryAdjacent(int x, int y, Tile[][] map) {
		
		boolean isAdjacent = false;
		
		if (x != 0) // check if not on the left edge
			if(map[x - 1][y].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if (y != 0) // check if not on the top edge
			if(map[x][y - 1].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if(x != width - 1) // check if not on the right edge
			if(map[x + 1][y].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if(y != height - 1) // check if not on the bottom edge
			if(map[x][y + 1].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;		
		
		return isAdjacent;
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
		boolean isValid = false;
		
		isValid = recursiveSolve(entryX, entryY);
		// will leave with a boolean array(correctPath)
		// with the path indicated by true values.
		// if result is false, there is no path to the exit
		
		if(isValid)
			addEntryAndExit();
		
		return isValid;
	}
	
	/**
	 * This function inserts the coordinates of entry and exit into the ArrayList 
	 */
	private static void addEntryAndExit() 
	{
		correctRoute.add(0, new Point(entryX, entryY));
		correctRoute.add(new Point(exitX, exitY));		
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
				correctRoute.add(new Point(x, y));
				return true;
			}
		
		if(x != width - 1) // check if not on right edge
			if(recursiveSolve(x + 1, y)) // recall method one to the right
			{
				correctRoute.add(new Point(x, y));
				return true;
			}
		
		if(y != 0) // check if not on top edge
			if(recursiveSolve(x, y - 1)) // recall method one up
			{
				correctRoute.add(new Point(x, y));
				return true;
			}
		
		if(y != height -1) // check if not on bottom edge
			if(recursiveSolve(x, y + 1)) // recall method one bottom
			{
				correctRoute.add(new Point(x, y));
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
		
		if(!(correctRoute.isEmpty()))
			correctRoute.clear();
		

		
		// set boolean Arrays to default values
		for(int row = 0; row < map.length; ++row)
			for(int col = 0; col < map[row].length; ++col)
			{
				wasHere[row][col] = false;
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
	 * This function returns an ArrayList which contains the path to exit 
	 * 
	 * @return an ArrayList  which contains correctRoute information
	 * 
	 */
	
	public static ArrayList<Point> getCorrectRoute()
	{
		return correctRoute;
	}
	


}


