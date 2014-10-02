package xml;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author Hongrui Guan
 * @date 2014-09-24 
 * This class created for both saving a customized map into a XML File
 * and parsing XML File for reloading an existing map on the map editor panel
 */
public class MapParser {

	private Element xmlFile;

	public boolean createXMLFile(Tile[][] mapInformation, String mapName)
			throws IOException {
		// TODO not finish!!!!!!!!
		Document document = DocumentHelper.createDocument();
		Element mapFile = document.addElement("map");
		Element fileName = mapFile.addElement("fileName");
		fileName.setText(mapName);
		Element mapRow = mapFile.addElement("mapRow");
		mapRow.setText(String.valueOf(mapInformation.length));
		Element mapColumn = mapFile.addElement("mapColumn");
		mapColumn.setText(String.valueOf(mapInformation[0].length));
		Element mapData = mapFile.addElement("mapData");
		
		int mapSize = mapInformation.length*mapInformation[0].length;
		Element[] tiles = new Element[mapSize];
		for (int i = 0; i < mapInformation.length; i++) {
			for (int j = 0; j < mapInformation[0].length; j++) {
				int coordinate = i*mapInformation[0].length+j;
				tiles[coordinate] = mapData.addElement("tile");
				tiles[coordinate].addAttribute("id", String.valueOf(coordinate+1));
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
		Date createDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yymmddhhmm");
		String mapDate = dateFormat.format(createDate);

		return this.writeFormatXML(document, "mapdata/" + mapName + mapDate
				+ ".xml");
	}

	public void loadXMLFile(String mapDirectory) throws DocumentException {
		// TODO catch exception
		
		SAXReader reader = new SAXReader();
		Document xmlDocument = reader.read(mapDirectory);

		Element xmlRoot = xmlDocument.getRootElement();
		this.xmlFile = xmlRoot;

	}

	public String getMapName() throws DocumentException {
		String mapName = xmlFile.elementText("fileName");

		System.out.println("map name is:" + mapName);

		return mapName;
	}

	public Tile[][] getMapData() throws NumberFormatException,
	DocumentException {
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
		System.out.println("map data is: ");
		
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
				
			}
		}

		return tiles;
	}


	private boolean writeFormatXML(Document xmlDocument, String mapDirectory)
			throws IOException {
		// TODO catch exception
		boolean isTrue = false;
		XMLWriter writer = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		writer = new XMLWriter(new FileWriter(new File(mapDirectory)), format);
		writer.write(xmlDocument);
		writer.close();
		isTrue = true;

		return isTrue;
	}

}
