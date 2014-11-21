package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import towers.ArrowTower;
import towerstrategy.NearestToExitStrategy;
import towerstrategy.NearestStrategy;
import towerstrategy.TowerStrategy;
import usefulfunctions.ValidateMap;
import critters.CritterBase;
import critters.CritterFactory;
import critters.NormalCritter;
/**
 * This class used for test the farthest strategy.
 * @author Hongrui Guan, Xunrong Xia
 *
 */
public class FarthestStrategyTest {

private static TowerStrategy strategy;
	
	private static ArrowTower tower;
	
	private static ArrayList<CritterBase> al;
	private static NormalCritter nearest;
	private static NormalCritter medium;
	private static NormalCritter farthest;
	private static TileMap tm;
	private static Tile[][] test_map;
	
	/**
	 * This method used to set up the object. 
	 * There are 3 critters, they have different distances between the tower�� one is nearest
	 * one is farthest, the rest has the medium distance. 
	 */
	@BeforeClass
	public static void init(){
		
		tm = TileMap.getTileMap();
		test_map = tm.loadMap("resources/gamemaps/test.xml");
		nearest =  (NormalCritter) CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(test_map), 1);
		nearest.setX(20);
		nearest.setY(20);
		medium =  (NormalCritter) CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(test_map), 2);
		medium.setX(50);
		medium.setY(50);
		farthest =  (NormalCritter) CritterFactory.getCritter("Normal", ValidateMap.getCorrectRoute(test_map), 3);
		farthest.setX(100);
		farthest.setY(100);
		
		al = new ArrayList<CritterBase>();
		al.add(nearest);
		al.add(medium);
		al.add(farthest);
				
		tower = new ArrowTower();
		tower.setTileX(10);
		tower.setTileY(10);
		
		strategy = new TowerStrategy();
		
	}
	
	/**
	 * This method test the farthest strategy.
	 * We test if the farthest strategy would return the critter with the farthest distance in the array list.
	 */
	@Test
	public void testFarestStrategy() {
		strategy.setStrategy(new NearestToExitStrategy());
		assertSame(farthest, strategy.executeStrategy(al, tower));
	}

}