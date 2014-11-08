package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import critters.CritterBase;

public class StrongestStrategy implements Strategy{
	private static final int STRONGEST = 2;

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		
		
		return Collections.max(al, new ComparatorByHp());
		
	}

	@Override
	public int getStrategyType() {
		// TODO Auto-generated method stub
		return STRONGEST;
	}
	

}
