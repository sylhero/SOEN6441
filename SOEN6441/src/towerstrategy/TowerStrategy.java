package towerstrategy;

import java.util.ArrayList;

import critters.CritterBase;

public class TowerStrategy {
	
	private Strategy strategy;
	private ArrayList<CritterBase> al;
	
	public void setStrategy(Strategy strategy, ArrayList<CritterBase> al){
		this.strategy = strategy;
		this.al = al;
	}
	public CritterBase getStrategy(){
		return this.strategy.executeStrategy(al);
	}

}
