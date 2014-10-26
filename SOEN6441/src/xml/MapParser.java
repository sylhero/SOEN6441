package xml;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import tilemap.Tile;
import usefulfunctions.LoadImage;

/**
 * This class implements XML write and parse functions.
 * This class will use for saving a customized map into a XML file and transform XML file
 * for reloading an existing map on the map editor panel
 *  
 * @author Hongrui Guan
 * @date 2014-09-24 
 *
 */

public class MapParser {

	private Element xmlFile;
	
	/**
	 * This method saves a map information to a XML file.
	 * 
	 * @param mapInformation All tiles information in the map. 
	 * @param mapName The map name that user named for
	 * @return Return true if XML file creating succeed
	 * */
	
	public boolean createXMLFile(Tile[][] mapInformation, String mapName){
	
		//A XML document variable that point to the root of the document.
		Document document = DocumentHelper.createDocument();
		
		//add the first hierarchy "map "on document.
		Element mapFile = document.addElement("map");
		
		//add tag "fileName" on "map"
		Element fileName = mapFile.addElement("fileName");
		//set value for fileName.
		fileName.setText(mapName);
		
		//Element modifiedFile = mapFile.addElement("modified");
		//modifiedFile.setText("0");
		
		Element mapRow = mapFile.addElement("mapRow");
		mapRow.setText(String.valueOf(mapInformation.length));
		Element mapColumn = mapFile.addElement("mapColumn");
		mapColumn.setText(String.valueOf(mapInformation[0].length));
		
		//add tag "mapData" on "map"
		Element mapData = mapFile.addElement("mapData");
		
		//Calculate the amount of tiles which would add into tag mapData
		int mapSize = mapInformation.length*mapInformation[0].length;
		
		Element[] tiles = new Element[mapSize];
		
		for (int i = 0; i < mapInformation.length; i++) {
			for (int j = 0; j < mapInformation[0].length; j++) {
				
				int coordinate = i*mapInformation[0].length+j;
				
				//add tag "tile" on "mapData"
				tiles[coordinate] = mapData.addElement("tile");
				//create an attribute id for tag tile
				tiles[coordinate].addAttribute("id", String.valueOf(coordinate+1));
				
				//add and set tags on "tile"
				Element tileType = tiles[coordinate].addElement("tileType");
				tileType.setText(String.valueOf(mapInformation[i][j].getTileType()));
				Element tileX = tiles[coordinate].addElement("tileX");
				tileX.setText(String.valueOf(mapInformation[i][j].getTileX()));
				Element tileY = tiles[coordinate].addElement("tileY");
				tileY.setText(String.valueOf(mapInformation[i][j].getTileY()));
				Element tileWidth = tiles[coordinate].addElement("tileWidth");
				tileWidth.setText(String.valueOf(mapInformation[i][j].getTileWidth()));
				Element tileHeight = tiles[coordinate].addElement("tileHeight");
				tileHeight.setText(String.valueOf(mapInformation[i][j].getTileHeight()));
				
			}
		}

		String userPath = System.getProperty("user.dir")+"/resources/gamemaps/";
			
		//create a XML file to save all map information
		return this.writeFormatXML(document, userPath + mapName + ".xml");

	}

	/**
	 * This method loads XML file and gets the root element of XML file. 
	 * This method should be invoked before methods getMapName and getMapData.
	 * The root element will be saved as a property of class.
	 * 
	 * @param mapDirectory
	 */
	public void loadXMLFile(String mapDirectory){
		SAXReader reader = new SAXReader();
		try{
			Document xmlDocument = reader.read(mapDirectory);

			// TODO check if xml file is empty
			Element xmlRoot = xmlDocument.getRootElement();
			this.xmlFile = xmlRoot;
		}catch(Exception e){
			e.printStackTrace();
		}		

	}
	/**
	 * This method acquires map name from the root element of XML file.
	 * 
	 * @return mapName The map name will be returned as a string.
	 * */
	public String getMapName(){
		String mapName = xmlFile.elementText("fileName");

		System.out.println("map name is " + mapName);

		return mapName;
	}
	
	/**
	 * This method acquires map data from the root element of XML file.
	 * 
	 * @return tiles All tiles will be returned as a Tile array.
	 * */
	public Tile[][] getMapData(){
		

		Image grass       = LoadImage.loadImage("/images/grass.png");
		Image pavement    = LoadImage.loadImage("/images/pavement.png");
		Image entrance    = LoadImage.loadImage("/images/entrance.png");
		Image exit        = LoadImage.loadImage("/images/destination.png");	
		final int GRASS       = 0;
		final int ENTRANCE    = 1;
		final int PAVEMENT    = 2;
		final int EXIT        = 3;
		
		int row = Integer.parseInt(xmlFile.elementText("mapRow"));
		int column = Integer.parseInt(xmlFile.elementText("mapColumn"));
		Tile[][] tiles = new Tile[row][column];

		Element mapData = xmlFile.element("mapData");
		List<Element> tileData = mapData.elements("tile");
		System.out.println("tile size is "+tileData.size());
		
		System.out.println("map height is equal to " + row);
		System.out.println("map width is equal to " + column);
		System.out.println("map data is");
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				tiles[i][j] = new Tile();
				tiles[i][j].setTileType((Integer.parseInt(tileData.get(i*column+j).elementText("tileType"))));
				switch(tiles[i][j].getTileType()) {
				
				case GRASS:
					tiles[i][j].setTileImage(grass);
					break;
				case ENTRANCE:
					tiles[i][j].setTileImage(entrance);
					break;
				case PAVEMENT:
					tiles[i][j].setTileImage(pavement);
					break;
				case EXIT:
					tiles[i][j].setTileImage(exit);
					break;
					
				}
				
				tiles[i][j].setTileX((Integer.parseInt(tileData.get(i*column+j).elementText("tileX"))));
				tiles[i][j].setTileY((Integer.parseInt(tileData.get(i*column+j).elementText("tileY"))));
				tiles[i][j].setTileWidth((Integer.parseInt(tileData.get(i*column+j).elementText("tileWidth"))));
				tiles[i][j].setTileHeight((Integer.parseInt(tileData.get(i*column+j).elementText("tileHeight"))));
				System.out.println("Tile Type:"+tiles[i][j].getTileType()+",Tile Image:"+tiles[i][j].getTileImage().toString());
			}
		}

		return tiles;
	}

	/**
	 * This method writes a format XML file.
	 * This method will be invoked on method createXMLFile.
	 * 
	 * @param xmlDocument 
	 * @param mapDirectory
	 * @return Return true if xml file creating succeed
	 * */

	private boolean writeFormatXML(Document xmlDocument, String mapDirectory){
		boolean isTrue = false;
		XMLWriter writer = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		try{
			writer = new XMLWriter(new FileWriter(new File(mapDirectory)), format);
			writer.write(xmlDocument);
			writer.close();
			isTrue = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		

		return isTrue;
	}
	
	/**
	 * This method check if created XML file has exists in the user directory.
	 * This method will be invoked on method createXMLFile.
	 * 
	 * @param fileName 
	 * @return Return false if XML file does not exist on the directory.
	 * @throws DocumentException 
	 * */
	public boolean checkFileName(String fileName){
		
		String userPath = System.getProperty("user.dir")+"/resources/gamemaps/"; 
		File mapFile = new File(userPath+fileName);

		return mapFile.exists();
	}
	
	
}
