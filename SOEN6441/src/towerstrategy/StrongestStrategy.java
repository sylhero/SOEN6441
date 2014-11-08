package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import critters.CritterBase;

public class StrongestStrategy implements Strategy{


	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		
		Collections.sort(al, new ComparatorByHp());
		
		return al.get(0);
	}
	

}