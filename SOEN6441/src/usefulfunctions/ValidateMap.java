package usefulfunctions;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * The ValidateMap class implemented five functions which being used to check
 * entry, exit and path of user-defined map, also detects a correct route of given map.
 * 
 * @author Yichen LI
 * @version 1.1.0
 *
 */

public class ValidateMap {
	
	private static int entryX, entryY, exitX, exitY;
	private static int width, height;
	private static boolean initFlag = false;
	private static int localMap [][];
	private static boolean wasHere [][];
	private static LinkedList<Point> correctRoute = new LinkedList<Point>();
	
	
	private ValidateMap(){};
	
	//======================================initialize member variables==========================================
	
	/**
	 * function to initialize some member variables  
	 * 
	 * @param map
	 */
	
	private static void init(Tile [][] map)
	{
		
		for (int row = 0; row < map.length; row++)
			for (int col = 0; col < map[row].length; col++) 
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
		
			initFlag = true; // set the flag
			
		
	}
	
	
	
	//======================================validate the entry point==========================================
	
	/**
	 * This function helps to validate whether only one entry exists.
	 * 
	 * @param map which contains tiles information
	 * @return a boolean which indicates if an entry validates  
	 * 
	 */
	public static boolean validateEntry(Tile [][] map)
	{
		int entranceCount = 0;
		boolean isValid = false;
		
		// initialize some member variables 
		if(!initFlag)
			init(map);
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.ENTRANCE)
				{
					entranceCount++;				
				}	
		}
		
				
		if(entranceCount == 1) {// if only one entrance exists and no exit is adjacent
			entranceCount = 0;
			isValid = true;
			}
		else
		{
			isValid = false;
		}
			return isValid;
		
	}
	
	//======================================validate is the entry point adjacent to exit==========================================
	
	/**
	 * This function checks is the exit adjacent to the entry.
	 * 
	 * @param x
	 * @param y
	 * @param map
	 * @return boolean indicates isAdjacent
	 */
	public static boolean isExitAdjacent(Tile [][] map) {
		
		boolean isAdjacent = false;
		
		// initialize some member variables 
		if(!initFlag)
			init(map);
		
		
		if (entryX != 0) // check if not on the left edge
			if(map[entryX - 1][entryY].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if (entryY != 0) // check if not on the top edge
			if(map[entryX][entryY - 1].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if(entryX != width - 1) // check if not on the right edge
			if(map[entryX + 1][entryY].getTileType() == TileMap.EXIT)
				isAdjacent = true;
		
		if(entryY != height - 1) // check if not on the bottom edge
			if(map[entryX][entryY + 1].getTileType() == TileMap.EXIT)
				isAdjacent = true;		
		
		return isAdjacent;
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
		int exitCount = 0;
		boolean isValid = false;
		
		// initialize some member variables 
		if(!initFlag)
			init(map);
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
			{			
				if(map[i][j].getTileType() == TileMap.EXIT)
				{
					exitCount++;
				}	
		}
		
		
		
		
		if(exitCount == 1){  // if only one exit exists and no entry is adjacent
			isValid = true;
			exitCount = 0;
		}
		else 
		{
			isValid = false;			
		}
			return isValid;
	}
	
	
	//======================================validate is the exit point adjacent to entry==========================================
	/**
	 * This function checks is the entry adjacent to the exit. 
	 * 
	 * @param x
	 * @param y
	 * @param map
	 * @return boolean indicates isAdjacent
	 */
	
	public static boolean isEntryAdjacent(Tile[][] map) {
		
		boolean isAdjacent = false;
		
		// initialize some member variables 
		if(!initFlag)
			init(map);
		
		if (exitX != 0) // check if not on the left edge
			if(map[exitX - 1][exitY].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if (exitY != 0) // check if not on the top edge
			if(map[exitX][exitY - 1].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if(exitX != width - 1) // check if not on the right edge
			if(map[exitX + 1][exitY].getTileType() == TileMap.ENTRANCE)
				isAdjacent = true;
		
		if(exitY != height - 1) // check if not on the bottom edge
			if(map[exitX][exitY + 1].getTileType() == TileMap.ENTRANCE)
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
		boolean isValid = false;
		
		// initialize some member variables 
		if(!initFlag)
			init(map);
		
		// initialize  related parameters 
		initPathPara(map);
		
		if(!(correctRoute.isEmpty()))
			correctRoute.clear();
		
		isValid = recursiveSolve(entryX, entryY);
		// will leave with a boolean array(correctPath)
		// with the path indicated by true values.
		// if result is false, there is no path to the exit
		
		if(isValid)
			addExitPoint();
		
		initFlag = false; // set the flag back to false;
			
		
		return isValid;
	}
	
	/**
	 * This function inserts the coordinates of exit into the ArrayList 
	 */
	private static void addExitPoint() 
	{
		correctRoute.addLast(new Point(exitX, exitY));			
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
				correctRoute.addFirst(new Point(x, y));
				return true;
			}
		
		if(x != width - 1) // check if not on right edge
			if(recursiveSolve(x + 1, y)) // recall method one to the right
			{
				correctRoute.addFirst(new Point(x, y));
				return true;
			}
		
		if(y != 0) // check if not on top edge
			if(recursiveSolve(x, y - 1)) // recall method one up
			{
				correctRoute.addFirst(new Point(x, y));
				return true;
			}
		
		if(y != height -1) // check if not on bottom edge
			if(recursiveSolve(x, y + 1)) // recall method one bottom
			{
				correctRoute.addFirst(new Point(x, y));
				return true;
			}
		
		return false;
	}

	/**
	 * This function initializes some path related member variables.  
	 * 
	 * @param map which contains tiles information 
	 * 
	 */

	private static void initPathPara(Tile [][]map) {

		// initialize localMap, wasHere and correctPath
		localMap = new int[width][height];
		
		// generate localMap
		generate(map);
		
		wasHere = new boolean[width][height];
			
		// set boolean Arrays to default values		
		for(boolean subArr[] : wasHere)
			Arrays.fill(subArr, false);
		
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
	 * This function is exclusively used by Junit test to set the flag back to false
	 * when each test case ends execution
	 * 
	 * @param initFlag
	 */
	public static void setInitFlag(boolean initFlag) {
		
		ValidateMap.initFlag = initFlag;
	}
	
	
	//==========================================get correct route ========================================================

	
	
	 /**
	 * This function returns an linkedList contains necessary information to reach the exit  
	 * 
	 * @param map
	 * @return a LinkedList contains correct route information.
	 * @throws InvalidPathException 
	 */
	
	public static LinkedList<Point> getCorrectRoute(Tile [][] map) 
	{
				
		if(validatePath(map))
			return correctRoute;
		else
			return null;
	}
	
}


