package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import towers.TowerBase;
import critters.CritterBase;

public class WeakestStrategy implements Strategy{
	private static final int WEAKEST = 1;

	/*@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		// TODO Auto-generated method stub
		return null;
		
	}*/

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return WEAKEST;
	}

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter) {
		// TODO Auto-generated method stub
		
		return Collections.min(al, new ComparatorByHp());
	}

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		// TODO Auto-generated method stub
		return null;
	}
}
