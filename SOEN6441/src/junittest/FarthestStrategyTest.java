package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import towers.ArrowTower;
import towerstrategy.NearestToExitStrategy;
import towerstrategy.TowerStrategy;
import critters.CritterBase;
import critters.NormalCritter;
/**
 * This class used for test the farthest strategy.
 * @author Yichen LI
 * @verson 1.2.0
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
	/**
	 * This method used to set up the object. 
	 * There are 3 critters, they have different distances between the exit point�� one is nearest
	 * one is farthest, the rest has the medium distance. 
	 */
	@BeforeClass
	public static void init(){
		
		tm = TileMap.getTileMap();
		tm.loadMap("resources/gamemaps/test.xml");
		

		farthest = new NormalCritter();
		farthest.setX(20);
		farthest.setY(20);

		medium = new NormalCritter();
		medium.setX(50);
		medium.setY(50);

		nearest = new NormalCritter();
		nearest.setX(100);
		nearest.setY(100);
		
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
	 * We test if the farthest strategy would return the critter with the nearest distance in the array list.
	 */
	@Test
	public void testFarestStrategy() {
		strategy.setStrategy(new NearestToExitStrategy());
		assertSame(nearest, strategy.executeStrategy(al, tower));
	}

}
