/**
 * 
 */
package junittest;

import static org.junit.Assert.*;

import java.util.Date;

import log.WaveLog;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used for testing WaveLog
 * @author Xunrong Xia
 *
 */
public class WaveLogTest {

	private static WaveLog waveLog;
	private static Date date;
	private static String allLogResult;
	/**
	 * 
	 */
	@Before
	public void setUp(){
		waveLog = WaveLog.getObject();
		date = new Date();
		allLogResult = null;
		waveLog.waveLog.clear();
		waveLog.addToWaveLog("test wave log", date);
	}

	/**
	 * Test method for {@link log.WaveLog#addToWaveLog(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testAddToWaveLog() {
		String result = date.toString()+"   "+"test wave log";
		assertNotNull(waveLog.waveLog);
		assertEquals(result,waveLog.waveLog.get(0));
	}

}
