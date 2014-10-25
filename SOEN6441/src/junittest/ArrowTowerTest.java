package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.ArrowTower;

/**
 * This class for used for test ArrowTower class in package entity.
 * The upgrade() method been test.
 * 
 * @author Xunrong Xia
 *
 */

public class ArrowTowerTest {
	private static ArrowTower arrowTower;

	@Before
	public void setUp() throws Exception {
		arrowTower = new ArrowTower();
	}

	@Test
	public void testUpgrade() {
		arrowTower.upgrade();
		assertEquals(15, arrowTower.getPower());
		assertEquals(1,arrowTower.getLevel());
		assertEquals(15,arrowTower.getUpgradeCost());
		assertEquals(30, arrowTower.getValue());
	}

}
