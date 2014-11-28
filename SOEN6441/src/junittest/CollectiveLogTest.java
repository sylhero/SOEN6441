/**
 * 
 */
package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import log.CollectiveLog;

import org.junit.Before;
import org.junit.Test;

import towers.ArrowTower;

/**
 * This class is used for test CollectiveLog
 * @author Xunrong Xia
 *
 */
public class CollectiveLogTest {
	private static CollectiveLog collectiveLog;
	private static Date date;
	private static String allLogResult;
	
	/**
	 * 
	 */
	@Before
	public void setUp(){
		collectiveLog = CollectiveLog.getObject();
		date = new Date();
		allLogResult = null;
		collectiveLog.collectivelLog.clear();
		collectiveLog.addToAllTowerLog("test collective tower log", date);
	}

	/**
	 * Test method for {@link log.CollectiveLog#addToAllTowerLog(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testAddToAllTowerLog() {
		
		assertNotNull(collectiveLog.collectivelLog);
	}

	/**
	 * Test method for {@link log.CollectiveLog#getAllTowerLog()}.
	 */
	@Test
	public void testGetAllTowerLog() {
		String result = date.toString()+"   "+"test collective tower log";
		allLogResult = collectiveLog.getAllTowerLog();
		assertEquals(result,allLogResult);
	}

}
