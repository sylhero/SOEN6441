package junittest;

import static org.junit.Assert.*;
import log.MapLog;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
/**
 * this class is responsible for testing the map log class
 * @author yulongsong
 *
 */
public class MapLogTest {
	private String name;
	/**
	 * set up the mag log object before testing
	 */
	@BeforeClass
	public static void setUpMapLog(){
		String name = "MapLogTestOnly";
		MapLog.getMapLogObject().createMapLog(name);
	}

	/**
	 * set up the name
	 */
	@Before
	public void setUp(){
		name = "MapLogTestOnly";
		
	}
	/**
	 * test the top five scrore
	 */
	
	@Ignore
	public void testReadTopFiveScore(){
		int size = MapLog.getMapLogObject().getTopFive(name).size();
		assertEquals(6,size);
	}
	/**
	 * test get all logs
	 */
	
	@Ignore
	public void testGetAllLogs(){
		assertNotNull(MapLog.getMapLogObject().getAllMapLog(name));
	}
	/**
	 * test get map log name
	 */
	
	@Test 
	public void testGetMapLogName(){
		assertEquals("MapLogTestOnly",MapLog.getMapLogObject().getMapName());	
	}
	
	
	

}
