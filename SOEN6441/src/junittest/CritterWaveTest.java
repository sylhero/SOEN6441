/**
 * 
 */
package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;
import critters.CritterBase;
import critters.CritterWave;
import critters.NormalCritter;

/**
 * Test functions of CritterWaveTest.
 * 
 * @author Yichen LI
 *
 */
public class CritterWaveTest {

	private static TileMap tm;
	private static Tile[][] map;
	private static CritterWave cw;
	private static NormalCritter nc;
	
	
	/**
	 * Initialize some data members.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		tm = TileMap.getTileMap();
		map = tm.loadMap("resources/gamemaps/test.xml");
		cw = new CritterWave();
		nc = new NormalCritter(ValidateMap.getCorrectRoute(map), 1);
	}

	/**
	 * Test getCritterWave function.
	 */
	
	@Test
	public void getCritterWave() 
	{
		cw.addCritter(nc);
		ArrayList<CritterBase> al = cw.getCritterWave();
		
		for (ListIterator<CritterBase> iterator = al.listIterator(); iterator.hasNext();) 
		{
			assertSame(nc, iterator.next());			
		}
		
	}
}
