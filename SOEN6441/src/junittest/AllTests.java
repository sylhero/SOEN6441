package junittest;

import gamedata.GameDataParser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is the test suite for unit test classes.
 * Including all test classes.
 *  
 * @author Yichen LI
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	   		   GameDataParserTest.class,
			   ValidateMapTest.class, 
	           CoinTest.class, 
	           LoadImageTest.class,
	           LoadXmlTest.class,
	           ArrowTowerTest.class,
			   StrongestStrategyTest.class,
			   WeakestStrategyTest.class,
			   NearestStrategyTest.class,
			   CritterBaseTest.class,
			   CannonTowerTest.class,
			   MagicTowerTest.class,
			   IceTowerTest.class,
			   NearestToExitStrategyTest.class,
			   CritterWaveTest.class,
			   CritterWaveBuilderTest.class,
			   IndividualTowerLogTest.class,
			   CollectiveLogTest.class,
			   GlobalLogTest.class,
			   WaveLogTest.class
			   })
public class AllTests {

}

