package towerstrategy;

import java.util.ArrayList;

import towers.TowerBase;
import critters.CritterBase;

public class NearestStrategy implements Strategy{
	private static final int NEAREST = 3;

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return NEAREST;
	}

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter) {
		// TODO Auto-generated method stub
		return null;
	}


}
