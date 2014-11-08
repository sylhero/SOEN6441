package towerstrategy;

import java.util.Comparator;
import critters.CritterBase;

public class ComparatorByHp implements Comparator<CritterBase>{

	@Override
	public int compare(CritterBase o1, CritterBase o2) {
		
		int temp = o1.getCurrentHp() - o2.getCurrentHp();
		
		return (int) (temp == 0 ? o1.getArmorRatio() - o2.getArmorRatio() : temp);
	}

	

}
