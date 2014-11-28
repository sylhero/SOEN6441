package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import critters.NormalCritter;
import tilemap.Tile;
import tilemap.TileMap;
import towers.ArrowTower;
import usefulfunctions.ValidateMap;

/**
 * This class for used for test ArrowTower class in package entity.
 * The upgrade(), fire() method been test.
 * 
 * @author Xunrong Xia
 *
 */

public class ArrowTowerTest {
	private static ArrowTower arrowTower;
	private static NormalCritter critter;
	private static Tile[][] testMap;
	private static TileMap temp;

	/**
	 * The setUp method would used to initialize the arrowTower object. 
	 * It will have the default value of its features.
	 * 
	 * @throws Exception java.lang.Exception
	 */
	@Before
	public void setUp() {
		temp = TileMap.getTileMap();
		testMap = temp.loadMap("resources/gamemaps/test.xml");
		critter = new NormalCritter(ValidateMap.getCorrectRoute(testMap), 1);
		critter.setCurrentHp(100);
		
		arrowTower = new ArrowTower();
		arrowTower.setTileX(critter.getX());
		arrowTower.setTileY(critter.getY());
	}

	/**
	 * This is to test if the values of some features of the tower have increased the correct value after the tower upgrade one level.
	 */
	@Test
	public void testUpgrade() {
		arrowTower.upgrade();
		assertEquals(20, arrowTower.getPower());
		assertEquals(1,arrowTower.getLevel());
		assertEquals(20,arrowTower.getUpgradeCost());
		assertEquals(160, arrowTower.getValue());
	}
	/**
	 * This is to test the fire method in arrow tower class. 
	 */
	@Test
	public void testFire()
	{
		arrowTower.fire(critter);
		assertNotNull(arrowTower.getTarget());
	}
}
