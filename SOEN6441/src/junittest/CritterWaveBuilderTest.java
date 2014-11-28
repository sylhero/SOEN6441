package junittest;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import critters.CritterBase;
import critters.CritterWaveBuilder;


/**
 * The test case tests the functions of class CritterWaveBuilder.
 * @author Yichen LI
 * @version 1.2.0
 *
 */
public class CritterWaveBuilderTest {

	private static CritterWaveBuilder cwb;
	private static LinkedList<Point> route;
	private static ArrayList<CritterBase> critters;
	
	/**
	 * Initialize related data members.
	 */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		cwb = new CritterWaveBuilder();
		route = new LinkedList<Point>();
		
		// add some points 
		route.add(new Point(0,1));
		route.add(new Point(1,1));
		
		critters = new ArrayList<CritterBase>();
		
	}
	
	
	/**
	 * Test function prepareCritterWave.
	 */
	@Test
	public void testPrepareCritterWave() 
	{
		cwb.prepareCritterWave("mixed");
		assertNotNull(cwb.getWave());
	}

	
	/**
	 * Test function buildRoute.
	 */
	@Test
	public void testBuildRoute()
	{
		// start a wave
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(route);
		critters = cwb.getWave(); // fetch the wave of critters
		
		for(CritterBase critter : critters)
		{
			// test if each critter been set up the route
			assertNotNull(critter.getCorrectRouteCopy()); 
		}	
	}
	
	/**
	 * Test function buildStartPoint.
	 */
	@Test
	public void testStartPoint()
	{
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(route);
		critters = cwb.getWave(); // fetch the wave of critters
		
		cwb.buildStartPoint();
		
		for(CritterBase critter : critters)
		{
			// test if the start points are the same
			assertSame(route.getFirst(), critter.getStartPoint());
		}
		
	}
	
	/**
	 * Test function buildNextPoint
	 */
	@Test
	public void testBuildNextPoint()
	{
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(route);
		critters = cwb.getWave(); // fetch the wave of critters
		
		cwb.buildStartPoint();
		cwb.buildNextPoint();
		
		for(CritterBase critter : critters)
		{
			// test if the next points are the same
			assertSame(route.get(1), critter.getNextPoint());
		}
	}
	
	/**
	 * Test function buildMovePoint.
	 */
	@Test
	public void testBuildMovePoint()
	{
		cwb.prepareCritterWave("mixed");
		cwb.buildMovePoint(10);
		
		for(CritterBase critter : critters)
		{
			assertNotNull(critter.getMovePoint());
		}
	}
	
	
	/**
	 * Test function buildX
	 */
	@Test
	public void testBuildX()
	{
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(route);
		critters = cwb.getWave(); // fetch the wave of critters
		
		cwb.buildStartPoint();
		cwb.buildNextPoint();
		cwb.buildX();
		
		for(CritterBase critter : critters)
		{
			assertNotNull(critter.getX());
		}		
	}
	
	/**
	 * Test function buildY
	 */
	@Test
	public void testBuildY()
	{
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(route);
		critters = cwb.getWave(); // fetch the wave of critters
		
		cwb.buildStartPoint();
		cwb.buildNextPoint();
		cwb.buildX();
		cwb.buildY();
		
		for(CritterBase critter : critters)
		{
			assertNotNull(critter.getY());
		}		
	}
}
