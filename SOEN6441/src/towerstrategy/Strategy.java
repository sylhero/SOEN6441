package towerstrategy;

import java.util.ArrayList;
import critters.CritterBase;

public interface Strategy {
	
	CritterBase executeStrategy(ArrayList<CritterBase> al);
}
