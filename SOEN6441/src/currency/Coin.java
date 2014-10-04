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
	
	private Coin(){
		
		coin = 30;	
	}
	
	public static Coin getCoinObject(){
		return coinObject;
	}
	
	public int getCurrency() {
		return this.coin;
	}

	
	public void increaseCurrency(int amount) {
		coin += amount; 
		
	}


	public void decreaseCurrency(int amount) {
		coin -= amount;
		
	}

	
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
