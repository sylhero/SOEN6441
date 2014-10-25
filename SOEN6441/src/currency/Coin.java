package currency;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import usefulfunctions.LoadImage;
/**
 * 
 * @author kunwang
 * this class is the coin class 
 * so far it can only increase and decrease the money
 * 
 *
 */
public class Coin {
	
	private volatile int coin;
	
	public static final Image coinImage = LoadImage.loadImage("/images/coins.png"); 
	
	private static Coin coinObject = new Coin();
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	private Coin(){
		
		coin = 30;	
	}
	
	public static Coin getCoinObject(){
		return coinObject;
	}
	
	/**
	 * Get the amount of the coin.
	 * @return The amount of the coin.
	 */
	
	public int getCurrency() {
		return this.coin;
	}

	/**
	 * the amount of coin increased
	 */
	public void increaseCurrency(int amount) {
		coin += amount; 
		
	}

	/**
	 * the amount of coin decreased
	 */
	
	public void decreaseCurrency(int amount) {
		coin -= amount;
		
	}

	/**
	 * Set the initial amount of the coin.
	 * @param coin The initial amount of coin.
	 */
	public void initializeCurrency() {
		this.coin = 300;
		
	}


	public void update() {
		
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		String coinString = String.valueOf(coin);
		g.drawString(coinString, 740, 25);
	}
	

}
