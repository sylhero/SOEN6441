package junittest;

import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;
import critters.CritterFactory;
import critters.NormalCritter;

/**
 * This class is to test functions of CritterBase class.
 * @author Yichen LI
 * @version 1.1.0
 *
 */

public class CritterBaseTest {

	private static NormalCritter testCritter;
	private static int expectedHp;
	private static int actualHp;
	private static int expectedOffSetX;
	private static int expectedOffSetY;
	private static int actualOffSetX;
	private static int actualOffSetY;
	private static TileMap tm;
	private static Tile [][] map;
	
	/**
	 * To initialize some data members.
	 * 
	 * @throws Exception java.lang.Exception
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		tm = TileMap.getTileMap();
		map = tm.loadMap("resources/gamemaps/test.xml");
		testCritter = (NormalCritter) CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(map), 1);
		expectedHp = 7300; 
		expectedOffSetX = 4;
		expectedOffSetY = 4;
	}

	/**
	 * To test decraseHp function.
	 */
	
	@Test
	public void testDecreaseHp()
	{
		testCritter.decreaseHp(1000);
		actualHp = testCritter.getCurrentHp();
		assertEquals(expectedHp, actualHp);
	}

	/**
	 * To test setSpeedOffSet function.
	 */
	
	@Test
	public void testSetSpeedOffSet()
	{
		testCritter.setSpeedOffset(4, 4);
		actualOffSetX = testCritter.getSpeedOffsetX();
		actualOffSetY = testCritter.getSpeedOffsetY();
		assertEquals(expectedOffSetX, actualOffSetX);
		assertEquals(expectedOffSetY, actualOffSetY);
	}
}
