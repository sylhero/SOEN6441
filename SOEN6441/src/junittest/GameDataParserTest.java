package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import log.CollectiveLog;
import log.GlobalLog;
import log.WaveLog;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import towers.TowerBase;

import gamedata.GameDataParser;
/**
 * This class used for test the GameDataParser.
 * @author Yulong Song
 *
 */
public class GameDataParserTest extends GameDataParser {
	


	private static GameDataParser gameParser;
	
	private static TileMap tm;
	private static Tile[][] mapData;
	private static int currency;
	private static ArrayList<TowerBase> towerList;
	private static ArrayList<String> cLog = CollectiveLog.getObject().collectivelLog;
	private static ArrayList<String> gLog = GlobalLog.getObject().globalLog;
	private static ArrayList wLog = WaveLog.getObject().totalWaveLog;
	
	@BeforeClass
	public static void init(){
				
		tm = TileMap.getTileMap();
		mapData = tm.loadMap("resources/gamemaps/demoMap.xml");
		
		currency = 1000;
		
		towerList = new ArrayList<TowerBase>();
				
		gameParser = new GameDataParser(mapData, towerList, currency);

				
	}
	

	@Test
	public void testWriteGameData() {
		
		
		assertTrue(gameParser.WriteGameData("demoMap.xml"));
	}
	
	@Test
	public void testReadGameData(){
		
		assertTrue(gameParser.ReadGameData("demoMap.xml.ser"));
	}
	
	@Test
	public void testGetMoney(){
		
		gameParser.ReadGameData("demoMap.xml.ser");
		
		assertEquals(gameParser.getMoney(),new Integer(1000));
	}
	
	@Test
	public void testGetMapData(){
		
		gameParser.ReadGameData("demoMap.xml.ser");
		
		assertNotNull(gameParser.getMapData());

	}
	
	@Test
	public void testGetTowerList(){
		
		gameParser.ReadGameData("demoMap.xml.ser");
		
		assertNotNull(gameParser.getTowerList());
	}
	
	@Test
	public void testGetTotalWaveLog(){
		
		gameParser.ReadGameData("demoMap.xml.ser");

		assertNotNull(gameParser.getTotalWaveLog());		
		
	}
	
	
	@Test
	public void testGetGlobalLog(){
		
		gameParser.ReadGameData("demoMap.xml.ser");
		
		assertNotNull(gameParser.getGlobalLog());

	}
	
	@Test
	public void testGetCollectiveLog(){
		
		gameParser.ReadGameData("demoMap.xml.ser");
		
		assertNotNull(gameParser.getCollectiveLog());

	}
}
