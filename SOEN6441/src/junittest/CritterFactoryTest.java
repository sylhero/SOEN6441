package junittest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import critters.CritterFactory;
import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;

/**
 * This class is to test CritterFacotry.
 * @author Yichen LI
 * @version 1.1.0
 *
 */
public class CritterFactoryTest {

	private static TileMap tm;
	private static Tile[][] map;
	
	/**
	 * To initialize some data members.
	 * @throws Exception java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		tm = TileMap.getTileMap();
		map = tm.loadMap("resources/gamemaps/test.xml");
	}

	/**
	 * To test getCritter function.
	 */
	@Test
	public void testGetCritter() 
	{	
		assertNotNull(CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(map), 1));
	}

}
