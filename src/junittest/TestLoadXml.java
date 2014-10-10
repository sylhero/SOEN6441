package junittest;

import static org.junit.Assert.*;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import xml.MapParser;

public class TestLoadXml {
	private MapParser mapparser=new MapParser();
	
	
	@Test
	public void testLoadXmlException(){
		try{
			mapparser.loadXMLFile("resources/gamemaps/testLoad.xml");
			fail("should raise an exception");
		}
		catch(Exception success){
			
		}
	
	}


}
