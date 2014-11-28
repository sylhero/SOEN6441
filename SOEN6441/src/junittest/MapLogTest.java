package junittest;

import static org.junit.Assert.*;

import java.io.File;

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
	private static String name;
	private static MapLog mapLog;
	public static final String PATH = System.getProperty("user.dir")+"/resources/maplog/";
	private static File file;
	/**
	 * set up the map log object before testing
	 */
	@BeforeClass
	public static void setUpMapLog(){
		name = "MapLogTestOnly";
		MapLog.getMapLogObject().createMapLog(name);
	}

	/**
	 * test the top five score
	 */
	
	@Test
	public void testReadTopFiveScore(){
		int size = MapLog.getMapLogObject().getTopFive(name).size();
		assertEquals(6,size);
	}
	/**
	 * test get all logs
	 */
	
	@Test
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
	
	@Test 
	public void testCreateMapLog()
	{
		file = new File(PATH+name+".txt");
		assertTrue(file.exists());
	}
	
	

}
