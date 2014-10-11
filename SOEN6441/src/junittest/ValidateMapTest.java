package junittest;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;

/**
 * This class imposes Junit test to check correction of class ValidateMap functions
 * 
 */


public class ValidateMapTest {

	private static TileMap tm;
	private static Tile [][] noEntry;
	private static Tile [][] noExit;
	private static Tile [][] disconnectedPath;
	private static Tile [][] entranceAdjacentToExit;
	private static boolean expectedResult;
	private static boolean adjacentExpectedResult;
	
	/**
	 * To initialize some member variables.
	 */
	
	@BeforeClass
	public static void init()
	{
		System.out.println("before test path validatation");
		tm = TileMap.getTileMap();
		noEntry = tm.loadMap("resources/gamemaps/testmapNoEntrance.xml");
		noExit = tm.loadMap("resources/gamemaps/testmapNoExit.xml");
		disconnectedPath = tm.loadMap("resources/gamemaps/testmapDisconnectedPath.xml");		
		entranceAdjacentToExit = tm.loadMap("resources/gamemaps/testmapEntranceAdjacentToExit.xml");
		expectedResult = false;
		adjacentExpectedResult = true;
	}
	
	/**
	 * To reset the InitFlag value back to false.
	 */
	
	@After
	public void reset()
	{
		ValidateMap.setInitFlag(false);
	}
	
	/**
	 * To test no entry case
	 */
	
	@Test
	public void testValidateEntranceCase1() 
	{	
		System.out.println("test no entrance case begins.");
		boolean actualResult = ValidateMap.validateEntry(noEntry);
		assertEquals(expectedResult, actualResult);	
		System.out.println("no entrance case test ends.");
	}

	/**
	 * To test no exit case.
	 */
	
	@Test
	public void testValidateExit() 
	{	
		System.out.println("test no exit case begins.");
		boolean actualResult = ValidateMap.validateExit(noExit);
		assertEquals(expectedResult, actualResult);
		System.out.println("no exit case test ends.");
	}
	
	/**
	 * To test disconnectedPath case.
	 */
	
	@Test
	public void testValidatePath() 
	{	
		System.out.println("test no path case begins.");
		boolean actualResult = ValidateMap.validatePath(disconnectedPath);
		assertEquals(expectedResult, actualResult);		
		System.out.println("no path case test ends.");
	}
	
	/**
	 * To test entry is adjacent to exit case.
	 */
	
	@Test
	public void testValidateEntranceCase2()
	{
		System.out.println("test entry adjacents to exit case begins.");
		boolean actualResult = ValidateMap.validateEntry(entranceAdjacentToExit);
		assertEquals(adjacentExpectedResult, actualResult);
		System.out.println("test entrance adjacents to exit case test ends.");
		
	}
}
