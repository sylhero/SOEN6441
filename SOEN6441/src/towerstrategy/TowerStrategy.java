package towerstrategy;

import java.util.ArrayList;

import towers.TowerBase;
import critters.CritterBase;

public class TowerStrategy {
	
	private Strategy strategy;
	//private ArrayList<CritterBase> al;
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
		//this.al = al;
	}
	public CritterBase excuteStrategy(ArrayList<CritterBase> al)
	{
		return this.strategy.executeStrategy(al);
	}
	
	public CritterBase executeStrategy(ArrayList<CritterBase> al, TowerBase shooter){
		return this.strategy.executeStrategy(al, shooter);
	}

}
