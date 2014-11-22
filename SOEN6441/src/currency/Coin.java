package currency;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

import usefulfunctions.LoadImage;
/**
 * 
 * @author kunwang
 * this class is the coin class 
 * so far it can only increase and decrease the money
 * 
 *
 */
public class Coin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1893854549547122290L;

	private volatile int coin;
	
	public transient static final Image coinImage = LoadImage.loadImage("/images/coins.png"); 
	
	private static Coin coinObject = new Coin();
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	private Coin(){
		
		coin = 3000;	
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
	public void setCurrency(int coin){
		this.coin = coin;
		
	}

	/**
	 * the amount of coin increased
	 * @param amount The amount should be add to the coin
	 */
	public void increaseCurrency(int amount) {
		coin += amount; 
		
	}

	/**
	 * the amount of coin decreased
	 * @param amount The amount should be decreased to the coin
	 */
	
	public void decreaseCurrency(int amount) {
		coin -= amount;
		if(coin < 0){
			coin = 0;
		}
		
	}

	/**
	 * Set the initial amount of the coin.
	 * 
	 */
	public void initializeCurrency() {
		this.coin = 300;
		
	}


	public void update() {
		
		
	}
	
	/**
	 * Draw graphics in 2D
	 * 
	 * @param g The graphic object.
	 */
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		String coinString = String.valueOf(coin);
		g.drawString(coinString, 690, 25);
	}
	

}
