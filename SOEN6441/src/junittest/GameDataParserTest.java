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

public class GameDataParserTest extends GameDataParser {
	
	private static GameDataParser gameParser;
	
	private static TileMap tm;
	private static Tile[][] mapData;
	private static int currency;
	private static ArrayList<TowerBase> towerList;
	private static CollectiveLog cLog = CollectiveLog.getObject();
	private static GlobalLog gLog = GlobalLog.getObject();
	private static WaveLog wLog = WaveLog.getObject();
	
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
	

}
