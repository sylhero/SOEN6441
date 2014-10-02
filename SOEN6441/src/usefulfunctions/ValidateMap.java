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
	
	private int entryX, entryY, exitX, exitY;
	private int width, height;
	private int localMap [][];
	private boolean correctPath [][];
	private boolean wasHere [][];
	
	//======================================validate the entry point==========================================
	
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
	
	public boolean validatePath(Tile [][] map)
	{
		init(map); // initialize parameters 
		
		boolean result = recursiveSolve(entryX, entryY);
		// will leave with a boolean array(correctPath)
		// with the path indicated by true values.
		// if result is false, there is no path to the exit
		
		return result;
	}
	
	
	private boolean recursiveSolve(int x, int y) {
	
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


	private void init(Tile [][]map) {
			
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

	// copy values from map to localMap
	private void generate(Tile [][] map) {
		
		for(int row = 0; row < map.length; ++row)
			for(int col = 0; col < map[row].length; ++col)
			{
				localMap[row][col] = map[row][col].getTileType();
			}
	}
	
	
	
	public static void main(String args[])
	{
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
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
		return done;
		
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
	}*/
}


