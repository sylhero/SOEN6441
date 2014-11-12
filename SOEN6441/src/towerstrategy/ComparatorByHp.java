package towerstrategy;

import java.util.Comparator;
import critters.CritterBase;

/**
 * This class implements comparator interface to compare two critters by Hp.
 * 
 * @author Yichen LI
 * @version 1.1.0
 *
 */
public class ComparatorByHp implements Comparator<CritterBase>{

	
	/**
	 * To compare two critters by Hp.
	 */
	@Override
	public int compare(CritterBase o1, CritterBase o2) {
		
		int temp = o1.getCurrentHp() - o2.getCurrentHp();
		
		return (int) (temp == 0 ? o1.getArmorRatio() - o2.getArmorRatio() : temp);
	}

	

}
