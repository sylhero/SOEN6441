package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import towers.ArrowTower;

/**
 * This class for used for test ArrowTower class in package entity.
 * The upgrade() method been test.
 * 
 * @author Xunrong Xia
 *
 */

public class ArrowTowerTest {
	private static ArrowTower arrowTower;

	/**
	 * The setUp method would used to initialize the arrowTower object. 
	 * It will have the default value of its features.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		arrowTower = new ArrowTower();
	}

	/**
	 * This is to test if the values of some features of the tower have increased the correct value after the tower upgrade one level.
	 */
	@Test
	public void testUpgrade() {
		arrowTower.upgrade();
		assertEquals(15, arrowTower.getPower());
		assertEquals(1,arrowTower.getLevel());
		assertEquals(15,arrowTower.getUpgradeCost());
		assertEquals(30, arrowTower.getValue());
	}
	
}
