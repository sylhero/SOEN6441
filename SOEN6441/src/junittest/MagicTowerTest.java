package junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import critters.CritterFactory;
import critters.NormalCritter;
import tilemap.Tile;
import tilemap.TileMap;
import towers.MagicTower;
import usefulfunctions.ValidateMap;

/**
 * This class for used for test MagicTower class in package towers.
 * The upgrade() and fire() features have been tested.
 * 
 * @author Hongrui Guan
 *
 */

public class MagicTowerTest {

	private static MagicTower magicTower;
	private static NormalCritter critter;
	private static Tile[][] testMap;
	private static TileMap temp;
	
	/**
	 * The setUp method would used to initialize the magicTower object. 
	 * It will have the default value of its features.
	 * 
	 */
	@BeforeClass
	public static void setUp() {
		
		temp = TileMap.getTileMap();
		testMap = temp.loadMap("resources/gamemaps/test.xml");
		critter = (NormalCritter)CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(testMap), 1);
		critter.setCurrentHp(100);

		
		magicTower = new MagicTower();
		
		magicTower.setTileX(critter.getX());
		magicTower.setTileY(critter.getY());
		
	}
		

	/**
	 * This is to test upgrade features.
	 */
	@Test
	public void testUpgrade() {
		
		magicTower.upgrade();
		
		assertEquals(20, magicTower.getPower());
		assertEquals(1,magicTower.getLevel());
		assertEquals(50,magicTower.getUpgradeCost());
		assertEquals(275, magicTower.getValue());
	}
	
	/**
	 * This is to test fire features.
	 */
	@Test
	public void testFire(){
		
		magicTower.fire(critter);
		
		assertNotNull(magicTower.getTarget());
		//assertEquals(86,critter.getCurrentHp());
		
	}
}
