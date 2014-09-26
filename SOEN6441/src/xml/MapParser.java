package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Hongrui Guan
 * @date 2014-09-24 
 * This class created for both saving a customized map into a XML File
 * and parsing XML File for reloading an existing map on the map editor panel
 */
public class MapParser {

	private Element xmlFile;

	public boolean createXMLFile(Integer[][] mapInformation, String mapName)
			throws IOException {
		// TODO catch exception
		Document document = DocumentHelper.createDocument();
		Element mapFile = document.addElement("map");
		Element fileName = mapFile.addElement("fileName");
		fileName.setText(mapName);
		Element mapWidth = mapFile.addElement("row");
		mapWidth.setText(mapInformation[0][0].toString());
		Element mapHeight = mapFile.addElement("column");
		mapHeight.setText(mapInformation[1][0].toString());
		Element mapData = mapFile.addElement("mapData");
		String temp = "";
		for (int i = 0; i < mapInformation[0][0]; i++) {
			for (int j = 0; j < mapInformation[1][0]; j++) {
				temp += mapInformation[i + 2][j].toString();
			}
		}
		// System.out.print(temp);
		mapData.setText(temp);
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

	public Integer[][] getMapData() throws NumberFormatException,
			DocumentException {
		int row = Integer.parseInt(xmlFile.elementText("row"));
		int column = Integer.parseInt(xmlFile.elementText("column"));
		Integer[][] mapData = new Integer[row + 2][column];
		mapData[0][0] = row;
		mapData[1][0] = column;
		System.out.println("map height is equal to " + mapData[0][0]);
		System.out.println("map width is equal to " + mapData[1][0]);
		System.out.println("map data is: ");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				String temp = String.valueOf(xmlFile.elementText("mapData")
						.charAt(column * i + j));
				mapData[i + 2][j] = Integer.parseInt(temp);
				System.out.print(mapData[i + 2][j]);
			}
		}

		return mapData;
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
