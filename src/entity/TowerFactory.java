package entity;

/**
 * The tower part uses Factory Pattern. This is the Factory class. 
 *  
 * @author Xunrong Xia
 *
 */
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
			result = new ArrowTower();
		}
		else if (towerType.equalsIgnoreCase("cannonTower"))
		{
			result = new CannonTower();
		}
		else if (towerType.equalsIgnoreCase("iceTower"))
		{
			result = new IceTower();
		}
		else if(towerType.equalsIgnoreCase("magicTower"))
		{
			result = new MagicTower();
		}
		return result;
		
	}
}
