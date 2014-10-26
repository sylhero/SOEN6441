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
 * @author Hongrui Guan, Kun Wang
 *
 */
public class LoadXmlTest {
	
	private static MapParser mapparser;
	private static String user_directory;
	
	/**
	 * To initialize a MapParser attribute and some useful variables.
	 */
	@BeforeClass
	public static void setUp(){
		mapparser = new MapParser();
		user_directory = System.getProperty("user.dir")+"/resources/gamemaps/";
		mapparser.loadXMLFile(user_directory+"testmap11426031226.xml");
	}
	
	/**
	 * To test if the map name is as same as the filename
	 */
	@Test
	public void testGetMapName() {
		String mapName = mapparser.getMapName();
		assertEquals("testmap1",mapName);
	}
	
	/**
	 * To test if the map data is not null
	 */
	@Test
	public void testGetMapData(){
		Tile[][] test_map_data = mapparser.getMapData();
		assertNotNull(test_map_data);
	}
	/**
	 * To test if the filename is true
	 */
	@Test
	public void testCheckFileName(){
		String file_name = "testmap11426031226.xml";
		boolean file_name_result = mapparser.checkFileName(file_name);
		assertTrue(file_name_result);
	}
		
	/**
	 * To test if the xmlfile written successfully
	 */
	@Test
	public void testWriteXML(){
		String map_name = "annaGuan";
		boolean xml_file = mapparser.createXMLFile(mapparser.getMapData(), map_name);
		assertTrue(xml_file);
	}

}
