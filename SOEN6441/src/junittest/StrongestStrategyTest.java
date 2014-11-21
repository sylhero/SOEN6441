package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import towerstrategy.StrongestStrategy;
import towerstrategy.TowerStrategy;
import usefulfunctions.ValidateMap;
import critters.CritterBase;
import critters.NormalCritter;

/**
 * This class is to test StrongestStrategy class.
 * 
 * @author Yichen LI
 * @version 1.1.0
 *
 */

public class StrongestStrategyTest {

	
	private static ArrayList<CritterBase> al;
	private static NormalCritter lowest;
	private static NormalCritter medium;
	private static NormalCritter highest;
	private static TowerStrategy ts;
	private static TileMap tm;
	private static Tile[][] map;
	
	/**
	 * To initialize data members.
	 */
	
	@BeforeClass
	public static void init()
	{
		tm = TileMap.getTileMap();
		map = tm.loadMap("resources/gamemaps/test.xml");
		
		lowest =  new NormalCritter(ValidateMap.getCorrectRoute(map), 1);
		lowest.setCurrentHp(100);
		medium =  new NormalCritter(ValidateMap.getCorrectRoute(map), 2);
		medium.setCurrentHp(200);
		highest = new NormalCritter(ValidateMap.getCorrectRoute(map), 3);
		highest.setCurrentHp(300);
		 
		al = new ArrayList<CritterBase>();	
		al.add(lowest);
		al.add(medium);
		al.add(highest);		 
		 
		ts = new TowerStrategy();
	}
	
	/**
	 * To test executeStrategy function.
	 */
	
	@Test
	public void testStrongestStrategy() {
		
		ts.setStrategy(new StrongestStrategy());
		assertSame(highest, ts.executeStrategy(al, null));
		
		
		
		
	}

}
