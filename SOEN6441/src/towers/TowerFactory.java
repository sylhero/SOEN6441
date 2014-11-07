package towers;

/**
 * The tower part uses Factory Pattern. This is the Factory class. 
 *  
 * @author Xunrong Xia
 *
 */
public class TowerFactory {
	public TowerFactory(){
		
	}
	/**
	 * This method would return a Tower instance base on the parameter.
	 * 
	 * @param towerType It is the tower's name.
	 * @return Based on the tower's name, that is, the parameter of the class, the method would return the corresponding Tower. 
	 */
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
