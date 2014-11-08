package towerstrategy;

import java.util.ArrayList;
import critters.CritterBase;

public interface Strategy {
	
	int getStrategyType();
	CritterBase executeStrategy(ArrayList<CritterBase> al);
}
