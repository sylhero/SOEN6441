package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import critters.CritterBase;

public class WeakestStrategy implements Strategy{
	private static final int WEAKEST = 1;

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		// TODO Auto-generated method stub
		
		return Collections.min(al, new ComparatorByHp());
	}

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return WEAKEST;
	}
}
