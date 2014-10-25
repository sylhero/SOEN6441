package junittest;

import static org.junit.Assert.*;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import xml.MapParser;
/**
 * This is the test class for MapParser class in the xml package.
 * It tests the method getMapName,getMapData and writeFormatXML
 * @author Kun Wang
 *
 */
public class LoadXmlTest {
	
	private static MapParser mapparser;
	private static String user_directory;
	

	@BeforeClass
	public static void setUp(){
		mapparser = new MapParser();
		user_directory = System.getProperty("user.dir")+"/resources/gamemaps/";
		mapparser.loadXMLFile(user_directory+"testmap11426031226.xml");
	}
	
	@Test
	public void testGetMapName() {
		String mapName = mapparser.getMapName();
		assertEquals("testmap1",mapName);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetMapData(){
		Tile[][] test_map_data = mapparser.getMapData();
		assertNotNull(test_map_data);
	}
	
	@Test
	public void testCheckFileName(){
		String file_name = "testmap11426031226.xml";
		boolean file_name_result = mapparser.checkFileName(file_name);
		assertTrue(file_name_result);
	}
		
	@Test
	public void testWriteXML(){
		String map_name = "annaGuan";
		boolean xml_file = mapparser.createXMLFile(mapparser.getMapData(), map_name);
		assertTrue(xml_file);
	}

}
