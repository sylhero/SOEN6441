package towerstrategy;

import java.util.ArrayList;
import java.util.Collections;

import critters.CritterBase;

public class WeakestStrategy implements Strategy{
	

	@Override
	public CritterBase executeStrategy(ArrayList<CritterBase> al) {
		// TODO Auto-generated method stub
		
		Collections.sort(al, new ComparatorByHp());
			
		return al.get(al.size() - 1);
	}
}