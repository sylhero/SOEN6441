package tower;

import entity.TowerBase;

public class TowerFactory {
	public TowerFactory(){
		
	}
	
	public TowerBase getTower(String towerType){
		TowerBase result = null;
		if (towerType == null)
		{
			result = null;
		}
		else if (towerType.equalsIgnoreCase("arrowTower"))
		{
			result = new Tower();
		}
		/*else if (towerType.equalsIgnoreCase("cannonTower"))
		{
			result = new Tower();
		}
		else if (towerType.equalsIgnoreCase("iceTower"))
		{
			result = new Tower();
		}
		else if(towerType.equalsIgnoreCase("magicTower"))
		{
			result = new Tower();
		}*/
		return result;
		
	}
}
