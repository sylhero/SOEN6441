package currency;

import java.awt.Color;
import java.awt.Graphics2D;

public class Coin implements Currency{
	private int coin;
	
	public Coin(){
		coin = 300;	
	}
	
	

	@Override
	public int getCurrency() {
		return this.coin;
	}

	@Override
	public void increaseCurrency(int amount) {
		coin += amount; 
		
	}

	@Override
	public void decreaseCurrency(int amount) {
		coin -= amount;
		
	}

	@Override
	public void initializeCurrency() {
		this.coin = 300;
		
	}



	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		
	}
	

}
