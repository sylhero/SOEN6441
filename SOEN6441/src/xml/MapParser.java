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

	private Element xml_file;
	
	/**
	 * This method saves a map information to a XML file.
	 * 
	 * @param map_information All tiles information in the map. 
	 * @param map_name The map name that user named for
	 * @return Return true if XML file creating succeed
	 * */
	
	public boolean createXMLFile(Tile[][] map_information, String map_name){
	
		//A XML document variable that point to the root of the document.
		Document document = DocumentHelper.createDocument();
		
		//add the first hierarchy "map "on document.
		Element map_file = document.addElement("map");
		
		//add tag "fileName" on "map"
		Element file_name = map_file.addElement("fileName");
		//set value for fileName.
		file_name.setText(map_name);
		
		//Element modifiedFile = mapFile.addElement("modified");
		//modifiedFile.setText("0");
		
		Element map_row = map_file.addElement("mapRow");
		map_row.setText(String.valueOf(map_information.length));
		Element map_column = map_file.addElement("mapColumn");
		map_column.setText(String.valueOf(map_information[0].length));
		
		//add tag "mapData" on "map"
		Element map_data = map_file.addElement("mapData");
		
		//Calculate the amount of tiles which would add into tag mapData
		int map_size = map_information.length*map_information[0].length;
		
		Element[] tiles = new Element[map_size];
		
		for (int i = 0; i < map_information.length; i++) {
			
			for (int j = 0; j < map_information[0].length; j++) {
				
				int coordinate = i*map_information[0].length+j;
				
				//add tag "tile" on "mapData"
				tiles[coordinate] = map_data.addElement("tile");
				//create an attribute id for tag tile
				tiles[coordinate].addAttribute("id", String.valueOf(coordinate+1));
				
				//add and set tags on "tile"
				Element tile_type = tiles[coordinate].addElement("tileType");
				tile_type.setText(String.valueOf(map_information[i][j].getTileType()));
				Element tile_x = tiles[coordinate].addElement("tileX");
				tile_x.setText(String.valueOf(map_information[i][j].getTileX()));
				Element tile_y = tiles[coordinate].addElement("tileY");
				tile_y.setText(String.valueOf(map_information[i][j].getTileY()));
				Element tile_width = tiles[coordinate].addElement("tileWidth");
				tile_width.setText(String.valueOf(map_information[i][j].getTileWidth()));
				Element tile_height = tiles[coordinate].addElement("tileHeight");
				tile_height.setText(String.valueOf(map_information[i][j].getTileHeight()));
				
			}
		}

		String user_path = System.getProperty("user.dir")+"/resources/gamemaps/";
			
		//create a XML file to save all map information
		return this.writeFormatXML(document, user_path + map_name + ".xml");

	}

	/**
	 * This method loads XML file and gets the root element of XML file. 
	 * This method should be invoked before methods getMapName and getMapData.
	 * The root element will be saved as a property of class.
	 * 
	 * @param map_directory
	 * @return Return true if loading xml file successfully
	 */
	
	public boolean loadXMLFile(String map_directory){
		
		boolean isTrue = false;
		
		//a SAX parser created for reading a XML file.
		SAXReader reader = new SAXReader();
		
		try{
			
			Document xml_document = reader.read(map_directory);

			// TODO check if xml file is empty
			
			//Get the root of xml file and set all elements of xml into the attribute xmlFile.
			Element xml_root = xml_document.getRootElement();
			this.xml_file = xml_root;
			
			isTrue = true;	
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;

	}
	
	/**
	 * This method acquires map name from the root element of XML file.
	 * 
	 * @return mapName The map name will be returned as a string.
	 * */
	
	public String getMapName(){
		
		//get map name from the class attribute xmlFile
		String map_name = xml_file.elementText("fileName");

		//System.out.println("map name is " + map_name);

		return map_name;
	}
	
	/**
	 * This method acquires map data from the root element of XML file.
	 * 
	 * @return tiles All tiles will be returned as a Tile array.
	 * */
	
	public Tile[][] getMapData(){

		//image will be saved into tiles based on tileType	
		Image grass       = LoadImage.loadImage("/images/grass.png");
		Image pavement    = LoadImage.loadImage("/images/pavement.png");
		Image entrance    = LoadImage.loadImage("/images/entrance.png");
		Image exit        = LoadImage.loadImage("/images/destination.png");	
		
		final int GRASS       = 0;
		final int ENTRANCE    = 1;
		final int PAVEMENT    = 2;
		final int EXIT        = 3;
		
		//row and column will be as x and y of a two-dimension array tiles
		int row = Integer.parseInt(xml_file.elementText("mapRow"));
		int column = Integer.parseInt(xml_file.elementText("mapColumn"));
		Tile[][] tiles = new Tile[row][column];

		//get all tiles from tag "mapData"
		Element map_data = xml_file.element("mapData");
		
		List<Element> tile_data = map_data.elements("tile");
//		
//		System.out.println("tile size is "+tile_data.size());		
//		System.out.println("map height is equal to " + row);
//		System.out.println("map width is equal to " + column);
//		System.out.println("map data is");
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < column; j++) {
				
				tiles[i][j] = new Tile();
				
				tiles[i][j].setTileType((Integer.parseInt(tile_data.get(i*column+j).elementText("tileType"))));
				
				//get image value into tiles based on tileType
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
				
				//set tile's information into tiles
				tiles[i][j].setTileX((Integer.parseInt(tile_data.get(i*column+j).elementText("tileX"))));
				tiles[i][j].setTileY((Integer.parseInt(tile_data.get(i*column+j).elementText("tileY"))));
				tiles[i][j].setTileWidth((Integer.parseInt(tile_data.get(i*column+j).elementText("tileWidth"))));
				tiles[i][j].setTileHeight((Integer.parseInt(tile_data.get(i*column+j).elementText("tileHeight"))));
				
				//System.out.println("Tile Type:"+tiles[i][j].getTileType()+",Tile Image:"+tiles[i][j].getTileImage().toString());
			}
		}

		return tiles;
	}

	/**
	 * This method writes a format XML file.
	 * This method will be invoked on method createXMLFile.
	 * 
	 * @param xml_document 
	 * @param map_directory
	 * @return Return true if xml file creating succeed
	 * */

	private boolean writeFormatXML(Document xml_document, String map_directory){
		
		boolean isTrue = false;
		
		XMLWriter writer = null;
		
		//write data into a XML file  follow a kind of format
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		try{
			
			writer = new XMLWriter(new FileWriter(new File(map_directory)), format);
			writer.write(xml_document);
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
	 * @param file_name 
	 * @return Return false if XML file does not exist on the directory.
	 * */
	
	public boolean checkFileName(String file_name){
		
		String user_path = System.getProperty("user.dir")+"/resources/gamemaps/"; 
		
		File map_file = new File(user_path+file_name);

		return map_file.exists();
	}
	
	
}
