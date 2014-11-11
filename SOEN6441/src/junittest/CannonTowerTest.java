package junittest;

import towers.ArrowTower;
import towers.CannonTower;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class for used for test CannonTower class in package towers.
 * The upgrade() method been test.
 * 
 * @author Hongrui Guan
 *
 */

public class CannonTowerTest {
	
	private static CannonTower cannonTower;
	
	/**
	 * The setUp method would used to initialize the arrowTower object. 
	 * It will have the default value of its features.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cannonTower = new CannonTower();
	}

	/**
	 * This is to test upgrade features.
	 */
	@Test
	public void testUpgrade() {
		
		cannonTower.upgrade();
		
		assertEquals(25, cannonTower.getPower());
		assertEquals(1,cannonTower.getLevel());
		assertEquals(220,cannonTower.getUpgradeCost());
		assertEquals(420, cannonTower.getValue());
	}
	

}
