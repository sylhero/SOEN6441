package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import towers.TowerBase;
import critters.CritterBase;

public class WeakestStrategy implements Strategy{
	private static final int WEAKEST = 1;

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
}
