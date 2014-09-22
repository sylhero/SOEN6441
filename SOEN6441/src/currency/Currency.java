package currency;

import java.awt.Graphics2D;

//this is the common interface for currency
public interface Currency {
	//get the currency
	int getCurrency();
	//increase the currency
	void increaseCurrency(int amount);
	//decrease the currency
	void decreaseCurrency(int amount);
	//initialize the currency
	void initializeCurrency();
	//draw the coin
	void draw(Graphics2D g);
	

}
