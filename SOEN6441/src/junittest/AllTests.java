package junittest;

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
			   CritterFactoryTest.class,
			   IceTowerTest.class})
public class AllTests {

}

