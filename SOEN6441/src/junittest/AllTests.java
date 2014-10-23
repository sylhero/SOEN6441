package junittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateMapTest.class, CoinTest.class, LoadImageTest.class
	,LoadXMLTest.class})
public class AllTests {

}
