package junittest;

import tilemap.Tile;
import tilemap.TileMap;
import towers.ArrowTower;
import towers.CannonTower;
import usefulfunctions.ValidateMap;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import critters.CritterFactory;
import critters.NormalCritter;

/**
 * This class for used for test CannonTower class in package towers.
 * The upgrade() and fire() features have been tested.
 * 
 * @author Hongrui Guan
 *
 */

public class CannonTowerTest {
	
	private static CannonTower cannonTower;
	private static NormalCritter critter;
	private static Tile[][] testMap;
	private static TileMap temp;
	
	/**
	 * The setUp method would used to initialize the cannonTower object. 
	 * It will have the default value of its features.
	 * 
	 */
	@Before
	public void setUp() {
		
		temp = TileMap.getTileMap();
		testMap = temp.loadMap("resources/gamemaps/test.xml");
		critter = (NormalCritter)CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(testMap), 1);
		critter.setCurrentHp(100);
		
		cannonTower = new CannonTower();
		cannonTower.setTileX(critter.getX());
		cannonTower.setTileY(critter.getY());
		
	}
		

	/**
	 * This is to test upgrade features.
	 */
	@Test
	public void testUpgrade() {
		
		cannonTower.upgrade();
		
		assertEquals(25, cannonTower.getPower());
		assertEquals(1,cannonTower.getLevel());
		assertEquals(40,cannonTower.getUpgradeCost());
		assertEquals(220, cannonTower.getValue());
	}
	
	/**
	 * This is to test fire features.
	 */
	@Test
	public void testFire(){
		
		cannonTower.fire(critter);
		
		assertNotNull(cannonTower.getTarget());
		
		//assertEquals(82,critter.getCurrentHp());
		
	}
	
	@Test
	public void testSpecialEffect()
	{
		cannonTower.fire(critter);
		assertTrue(critter.getIsBurning());
		assertEquals(86, critter.getCurrentHp());
		assertEquals(80,critter.getAffectedTimes());
	}
	

}
