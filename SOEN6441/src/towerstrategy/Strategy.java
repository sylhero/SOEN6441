package towerstrategy;

import java.util.ArrayList;

import towers.TowerBase;
import critters.CritterBase;

public interface Strategy {
	
	int getStrategyType();
	//CritterBase executeStrategy(ArrayList<CritterBase> al);
	CritterBase executeStrategy(ArrayList<CritterBase> al,
			TowerBase towerShooter);
}
