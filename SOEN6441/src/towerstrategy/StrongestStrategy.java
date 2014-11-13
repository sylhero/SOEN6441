package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import towers.TowerBase;
import critters.CritterBase;

public class StrongestStrategy implements Strategy{
	private static final int STRONGEST = 2;

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return STRONGEST;
	}

	

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter) {
	
		return Collections.max(al, new Comparator<CritterBase>(){

			@Override
			public int compare(CritterBase arg0, CritterBase arg1) 
			{		
				int temp = arg0.getCurrentHp() - arg1.getCurrentHp();				
				return (int) (temp == 0 ? arg0.getArmorRatio() - arg1.getArmorRatio() : temp);
			}
			
		});
	}



}
