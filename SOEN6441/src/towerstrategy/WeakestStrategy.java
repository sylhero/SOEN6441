package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import towers.TowerBase;
import critters.CritterBase;

/**
 * This class implemented Strategy interface and return the weakest critter among an arrayList.
 * 
 * @author Yichen LI
 * @version 1.1.2
 *
 */

public class WeakestStrategy implements Strategy{

	/**
	 * To find the weakest critter among a critter arrayList.
	 */

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter)	{

		return Collections.min(al, new Comparator<CritterBase>(){

			@Override
			public int compare(CritterBase arg0, CritterBase arg1) 
			{
				int temp = arg0.getCurrentHp() - arg1.getCurrentHp();
				return (int) (temp == 0 ? arg0.getArmorRatio() - arg1.getArmorRatio() : temp);
			}
			
		});
	}


}
