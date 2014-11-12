package towerstrategy;

import java.util.ArrayList;

import towers.TowerBase;
import critters.CritterBase;

/**
 * This class receives certain strategy parameter to execute pre-defined strategy.
 * 
 * @author Yulong SONG, Yichen LI
 *
 */

public class TowerStrategy 
{
	
	private Strategy strategy;
	
	
	/**
	 * To set member data strategy.
	 * 
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;		
	}
	
	/**
	 * To execute strategy and return certain critter.
	 * 
	 * @param al
	 * @param shooter
	 * @return CritterBase
	 */
	
	public CritterBase executeStrategy(ArrayList<CritterBase> al, TowerBase shooter)
	{
		return this.strategy.executeStrategy(al, shooter);
	}

}
