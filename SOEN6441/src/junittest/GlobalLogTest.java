/**
 * 
 */
package junittest;

import static org.junit.Assert.*;

import java.util.Date;

import log.CollectiveLog;
import log.GlobalLog;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Xunrong Xia
 *
 */
public class GlobalLogTest {

	/**
	 * 
	 */
	private static GlobalLog globalLog;
	private static Date date;
	private static String allLogResult;
	@Before
	public void setUp() {
		globalLog = GlobalLog.getObject();
		date  = new Date();
		allLogResult = null;
		globalLog.globalLog.clear();
		globalLog.addToGlobalLog("test global tower log", date);
	}

	/**
	 * Test method for {@link log.GlobalLog#addToGlobalLog(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testAddToGlobalLog() {
		assertNotNull(globalLog.globalLog);
	}

	/**
	 * Test method for {@link log.GlobalLog#getAllGobalLog()}.
	 */
	@Test
	public void testGetAllGobalLog() {
		String result = date.toString()+"   "+"test global tower log";
		allLogResult = globalLog.getAllGobalLog();
		assertEquals(result,allLogResult);
	}

}
