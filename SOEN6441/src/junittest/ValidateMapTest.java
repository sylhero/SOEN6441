package junittest;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;


public class ValidateMapTest {

	private static TileMap tm;
	private static Tile [][] noEntry;
	private static Tile [][] noExit;
	private static Tile [][] disconnectedPath;
	private static boolean expectedResult;
	
	
	@BeforeClass
	public static void init()
	{
		tm = TileMap.getTileMap();
		noEntry = tm.loadMap("resources/gamemaps/noEntranceTest.map");
		noExit = tm.loadMap("resources/gamemaps/noExitTest.map");
		disconnectedPath = tm.loadMap("resources/gamemaps/disconnectedPathTest.map");
		expectedResult = false;
	}
	
	
	
	@Test
	public void testValidateEntrance() 
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
}
