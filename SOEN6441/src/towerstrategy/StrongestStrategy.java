package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

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
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		// TODO Auto-generated method stub
		return Collections.max(al, new ComparatorByHp());
	}

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter) {
		// TODO Auto-generated method stub
		return null;
	}



}
