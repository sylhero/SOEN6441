package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import towers.ArrowTower;

/**
 * This class is used for testing individual tower log.
 * @author Xunrong Xia
 *
 */
public class IndividualTowerLogTest {

	private static ArrowTower tower;
	private static Date date;
	private static ArrayList log;
	private static String allLogResult;
	
	/**
	 * This is to set up some parameter for later test.
	 */	
	@Before
	public void setUp() {
		tower = new ArrowTower();
		date = new Date();
		log =null;
		allLogResult = null;
	}

	/**
	 * Test method for {@link towers.TowerBase#addIndevidualTowerLog(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testAddIndevidualTowerLog() {
		tower.addIndevidualTowerLog("test individual tower log", date);
		log = tower.getIndividualTowerLog();
		assertNotNull(log);
	}

	/**
	 * Test method for {@link towers.TowerBase#getAllIdividualTowerLog()}.
	 */
	@Ignore
	public void testGetAllIdividualTowerLog() {
		tower.addIndevidualTowerLog("test individual tower log", date);
		String result = date.toString()+"   "+"test indivudual tower log";
		allLogResult = tower.getAllIdividualTowerLog();
		assertEquals(result,allLogResult);
	}

}
