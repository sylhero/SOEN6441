package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import currency.Coin;

/**
 * This is a test class of Coin class
 * test method increaseCurrency(), and decreaseCurrency()
 * 
 * @author Xunrong Xia£¬ Hongrui Guan
 */

public class CoinTest {
	private static Coin coin = Coin.getCoinObject();

	/**
	 * Use the initializeCurrency method in the coin class, to set the value of coin to 300.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		coin.initializeCurrency();
	}

	/**
	 * Testing if the increase method would work correctly.
	 */
	@Test
	public void testIncreaseCurrency() {
		coin.increaseCurrency(100);
		assertEquals(400,coin.getCurrency());
	}

	/**
	 * Testing if the decrease method would work correctly.
	 */
	@Test
	public void testDecreaseCurrency() {
		coin.decreaseCurrency(50);
		assertEquals(250,coin.getCurrency());
	}
	
	/**
	 * Testing if the decrease method would work correctly when it be called multiple times. 
	 */
	@Test
	public void testDecreaseCurrency2(){
		for(int i=0;i<3;i++)
		{
			coin.decreaseCurrency(100);
		}
		assertEquals(0,coin.getCurrency());
	}

}
