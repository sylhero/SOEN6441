package towers;

import java.awt.Graphics2D;
/**
 * This is the abstract Decorator class of Decorator pattern.
 * @author Xunrong Xia
 *
 */
public abstract class TowerDecorator extends TowerBase{
	
	protected final TowerBase decoratedTower;
	
	public TowerDecorator(TowerBase decoratedTower)
	{
		this.decoratedTower = decoratedTower;
	}
	
	public void update()
	{
		decoratedTower.update();
	}
	
	public void draw(Graphics2D g)
	{
		decoratedTower.draw(g);
	}
}
