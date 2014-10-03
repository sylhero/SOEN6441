package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;

/**
 * 
 * @author Xunrong Xia
 * Test of ValidateMap class
 */
public class ValidateMapTest {
	private static ValidateMap validateMap = new ValidateMap();
	private TileMap tileMap = TileMap.getTileMap();
	private Tile[][] validMapTest = tileMap.loadMap("resources/gamemaps/testMap1.map");
	private Tile[][] noEntranceTest = tileMap.loadMap("resources/gamemaps/noEntranceTest.map");
	private Tile[][] noExitTest = tileMap.loadMap("resources/gamemaps/noExitTest.map");
	private Tile[][] noPathTest = tileMap.loadMap("resources/gamemaps/noPathTest.map");
	boolean result;
	/*@Before
	public void setUp() throws Exception {
		boolean result = false;
	}*/

	@Test
	public void testValidateEntrace() {
		//result = validateMap.validateEntrace(validMapTest);		
		result = validateMap.validateEntrace(noEntranceTest);
		
		//assertEquals(true, result);
		assertEquals(false, result);
		//fail("Not yet implemented");
	}

	@Test
	public void testValidateExit() {
		//boolean result = validateMap.validateExit(validMapTest);
		//assertEquals(true, result);
		result = validateMap.validateExit(noExitTest);
		assertEquals(false, result);
		//fail("Not yet implemented");
	}

	@Ignore
	public void testValidatePath() {
		//boolean result = validateMap.validateEntrace(validMapTest);
		//assertNotSame(false, result);
		//result = validateMap.validatePath(0,0);
		assertEquals(false, result);
		//fail("Not yet implemented");
	}

}
