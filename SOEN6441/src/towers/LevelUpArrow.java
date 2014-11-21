package towers;

/**
 * This the class for levelling up arrow tower.
 * @author Xunrong Xia
 *
 */
public abstract class LevelUpArrow extends TowerDecorator{

	public LevelUpArrow(TowerBase decoratedTower) {
		super(decoratedTower);
		// TODO Auto-generated constructor stub
	}
	
	public void update()
	{
		decoratedTower.level+=1;
		decoratedTower.power+=10;
		decoratedTower.value+=decoratedTower.upgradeCost;
		decoratedTower.upgradeCost+=10;
	}
}
