package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import currency.Coin;

/**
 * This is a test class of Coin class
 * test method increaseCurrency(), and decreaseCurrency()
 * 
 * @author Xunrong Xia
 */

public class CoinTest {
	private static Coin coin = Coin.getCoinObject();

	@Before
	public void setUp() throws Exception {
		coin.initializeCurrency();
	}

	@Test
	public void testIncreaseCurrency() {
		coin.increaseCurrency(100);
		assertEquals(400,coin.getCurrency());
	}

	@Test
	public void testDecreaseCurrency() {
		coin.decreaseCurrency(50);
		assertEquals(250,coin.getCurrency());
		//fail("Not yet implemented");
	}

}
