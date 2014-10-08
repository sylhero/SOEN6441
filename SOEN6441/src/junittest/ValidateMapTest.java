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
		System.out.println("before test path validatation");
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
		System.out.println("test no entrance case begins.");
		boolean actualResult = ValidateMap.validateEntrance(noEntry);
		assertEquals(expectedResult, actualResult);	
		System.out.println("no entrance case test ends.");
	}

	
	@Test
	public void testValidateExit() 
	{	
		System.out.println("test no exit case begins.");
		boolean actualResult = ValidateMap.validateExit(noExit);
		assertEquals(expectedResult, actualResult);
		System.out.println("no exit case test ends.");
	}
	
	@Test
	public void testValidatePath() 
	{	
		System.out.println("test no path case begins.");
		boolean actualResult = ValidateMap.validatePath(disconnectedPath);
		assertEquals(expectedResult, actualResult);		
		System.out.println("no path case test ends.");
	}
	
	@Test
	public void testValidateEntranceCase2()
	{
		System.out.println("test entrance adjacents to exit case begins.");
		boolean actualResult = ValidateMap.validateEntrance(entranceAdjacentToExit);
		assertEquals(expectedResult, actualResult);
		System.out.println("test entrance adjacents to exit case test ends.");
		
	}
}
