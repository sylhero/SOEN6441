package junittest;

import static org.junit.Assert.*;

import org.dom4j.Element;
import org.junit.Test;

import xml.MapParser;

public class TestGetMapName {
	private MapParser mapparser = new MapParser();
	

	@Test
	public void testGetMapName() {
		mapparser.loadXMLFile("resources/gamemaps/testmap11426031226.xml");
		String mapName=mapparser.getMapName();
		assertEquals("testmap1",mapName);
		//fail("Not yet implemented");
	}

}
