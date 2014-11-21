package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import towers.IceTower;
import usefulfunctions.ValidateMap;
import critters.NormalCritter;

/**
 * This class is to test functions in IceTower class.
 * 
 * @author Yichen LI
 * @version 1.1.0
 *
 */

public class IceTowerTest {

	private static IceTower iceTower;
	private static NormalCritter critter;
	private static Tile[][] testMap;
	private static TileMap temp;
	
	/**
	 * To initialize data members.
	 * @throws Exception java.lang.Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception 
	{
		temp = TileMap.getTileMap();
		testMap = temp.loadMap("resources/gamemaps/test.xml");
		critter = new NormalCritter(ValidateMap.getCorrectRoute(testMap), 1);
		critter.setCurrentHp(100);
		
		iceTower = new IceTower();
		iceTower.setTileX(critter.getX());
		iceTower.setTileY(critter.getY());
	}

	/**
	 * To test upgrade function.
	 */
	@Test
	public void testUpgrade() 
	{
		iceTower.upgrade();
		assertEquals(15, iceTower.getPower());
		assertEquals(1, iceTower.getLevel());
		assertEquals(40, iceTower.getUpgradeCost());
		assertEquals(220, iceTower.getValue());
	}

	/**
	 * To test fire function.
	 */
	@Test
	public void testFire()
	{
		iceTower.fire(critter);
		assertNotNull(iceTower.getTarget());
		assertTrue(critter.getIsFreezing());
	}
	
	@Test
	public void testSpecialEffect()
	{
		iceTower.fire(critter);
		assertEquals(93, critter.getCurrentHp());
		assertEquals(1,critter.getSpeedOffsetX());
		assertEquals(1,critter.getSpeedOffsetY());
	}
}
