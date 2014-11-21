/**
 * 
 */
package junittest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;
import critters.CritterWaveBuilder;

/**
 * Test the function of class CritterWaveBuilder.
 * 
 * @author Yichen Li
 * @version 1.2.0
 *
 */
public class CritterWaveBuilderTest {

	private static TileMap tm;
	private static Tile [][] map;
	private static CritterWaveBuilder cwb;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		tm = TileMap.getTileMap();
		map = tm.loadMap("resources/gamemaps/test.xml");
		cwb = new CritterWaveBuilder();
		
		
	}

	/**
	 * Test function prepareCritterWave and check if is it null.
	 */
	@Test
	public void testPrepareCritterWave() 
	{
		assertNotNull(cwb.prepareCritterWave(ValidateMap.getCorrectRoute(map), 1));
	}

}
