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
	 * @param strategy The strategy which should be set.
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;		
	}
	
	/**
	 * To execute strategy and return certain critter.
	 * 
	 * @param al The array list which contains the critter which should be check.
	 * @param shooter The tower which use this strategy.
	 * @return CritterBase Return the critter which should be attacked.
	 */
	
	public CritterBase executeStrategy(ArrayList<CritterBase> al, TowerBase shooter)
	{
		return this.strategy.executeStrategy(al, shooter);
	}

}
