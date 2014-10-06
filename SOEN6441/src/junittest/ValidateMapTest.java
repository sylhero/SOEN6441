package junittest;



import static org.junit.Assert.*;

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
		String userPath = System.getProperty("user.dir")+"/resources/gamemaps/";
		noEntry = tm.loadMap(userPath+"NOENTRANCE.xml");
		noExit = tm.loadMap(userPath+"NOEXIT.xml");
		disconnectedPath = tm.loadMap(userPath+"NOPATH.xml");
		
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
