package junittest;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;


public class ValidateMapTest {

	private static TileMap tm;
	private static Tile [][] noEntry;
	private static Tile [][] noExit;
	private static Tile [][] disconnectedPath;
	private static Tile [][] entranceAdjacentToExit;
	private static boolean expectedResult;
	
	
	@BeforeClass
	public static void init()
	{
		tm = TileMap.getTileMap();
		noEntry = tm.loadMap("resources/gamemaps/testmapNoEntrance.xml");
		noExit = tm.loadMap("resources/gamemaps/testmapNoExit.xml");
		disconnectedPath = tm.loadMap("resources/gamemaps/testmapDisconnectedPath.xml");		
		entranceAdjacentToExit = tm.loadMap("resources/gamemaps/testmapEntranceAdjacentToExit.xml");
		expectedResult = false;
	}
	
	
	
	@Test
	public void testValidateEntranceCase1() 
	{		
		boolean actualResult = ValidateMap.validateEntrance(noEntry);
		assertEquals(expectedResult, actualResult);		
	}

	
	@Test
	public void testValidateExit() 
	{		
		boolean actualResult = ValidateMap.validateExit(noExit);
		assertEquals(expectedResult, actualResult);		
	}
	
	@Test
	public void testValidatePath() 
	{		
		boolean actualResult = ValidateMap.validatePath(disconnectedPath);
		assertEquals(expectedResult, actualResult);		
	}
	
	@Test
	public void testValidateEntranceCase2()
	{
		boolean actualResult = ValidateMap.validateEntrance(entranceAdjacentToExit);
		assertEquals(expectedResult, actualResult);
	}
}
