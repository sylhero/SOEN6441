package junittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is the test suite for unit test. 
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ValidateMapTest.class, CoinTest.class, LoadImageTest.class
	,LoadXmlTest.class})
public class AllTests {

}
