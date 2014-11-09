package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import critters.CritterBase;
import critters.NormalCritter;
import currency.Coin;
import tilemap.TileMap;
import towerstrategy.FarthestStrategy;
import towerstrategy.NearestStrategy;
import towerstrategy.StrongestStrategy;
import towerstrategy.TowerStrategy;
import towerstrategy.WeakestStrategy;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Arrow Tower. 
 * The arrow tower has its own image and type and other common attributes.
 * 
 * @author Yulong Song, Xunrong Xia
 */
public class ArrowTower extends TowerBase{
	public static final Image arrowTower         = LoadImage.loadImage("/images/arrowtower.png");
	public static final Image arrowTowerEffect   = LoadImage.loadImageIcon("/images/arrowtowereffect.gif").getImage();
	public static final int ARROWTOWERTYPE  = 4;
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	public ArrowTower(){
		this.name = "Arrow Tower";
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = ARROWTOWERTYPE;
		this.tileImage = arrowTower;
		this.level = 0;
		this.cost  = 15;
		this.groupAttack = false;
		this.power = 10;
		this.range = 2*TileMap.getTileMap().getCellWidth();
		this.refundRate = 0.5;
		this.towerSpeed = 3;
		this.upgradeCost = 10;	
		this.value = this.cost;
		this.specialEffect = "None";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
	}
	
	/**
	 * This constructor can assign the value of tile's attributes besides the tower's common attributes.
	 * 
	 * @param tileX The X coordinate of the tile
	 * @param tileY The Y coordinate of the tile
	 * @param tileWidth The width of the tile
	 * @param tileHeight The height of the tile
	 */
	
	public ArrowTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		this.name = "Arrow Tower";
		this.tileType  = ARROWTOWERTYPE;
		this.tileImage = arrowTower;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.level = 0;
		this.cost  = 15;
		this.groupAttack = false;
		this.power = 10;
		this.range = 2*tileWidth;
		this.refundRate = 0.5;
		this.towerSpeed = 3;
		this.upgradeCost = 10;
		this.value = this.cost;
		this.specialEffect = "None";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
		
	}
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 * The tower's value would be changed too.
	 */
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;
		this.value = level * upgradeCost + cost;
		
	}
	public double distance (CritterBase critter){
		int critterX   = critter.getX();
		int critterY   = critter.getY();
		int critterCenterX = critterX + TileMap.getTileMap().getCellHeight() / 2;
		int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;
		int towerCenterX   = tileX + TileMap.getTileMap().getCellHeight() / 2;
		int towerCenterY   = tileY + TileMap.getTileMap().getCellWidth() / 2;
		double distance = Math.sqrt(Math.pow(critterCenterX-towerCenterX, 2) + 
				Math.pow(critterCenterY-towerCenterY, 2));
		
		return distance;		
	}
	
	public TowerStrategy setStrategy(){
		TowerStrategy strategy = new TowerStrategy();
		if (this.towerStratgyType == 1)
			strategy.setStrategy(new WeakestStrategy());
		else if(this.towerStratgyType==2)
			strategy.setStrategy(new StrongestStrategy());
		else if(this.towerStratgyType==3)
			strategy.setStrategy(new NearestStrategy());
		else if(this.towerStratgyType==4)
			strategy.setStrategy(new FarthestStrategy());
		else
			strategy = null;
		return strategy;
		
	}
	@Override
	public void fire(CritterBase critter) {	
		
		Iterator<CritterBase> it = targets.iterator();
		while(it.hasNext())
		{
			CritterBase temp = (CritterBase)it.next();
			double tempDistance = distance(temp);
			if(temp.getCurrentHp()<=0)
			{
				it.remove();
			}
			System.out.println("tempDistance");
			if(tempDistance>=range)
			{
				it.remove();
			}			
		}
		
		double distance = distance(critter);
		
			if(distance <= range)
			{
				
			if(!targets.contains(critter) && critter.getCurrentHp() > 0)
			{
				targets.add(critter);
			}
			
			System.out.printf("target size:%d\n",targets.size());
			
			TowerStrategy strategy = setStrategy();	
			if (strategy ==null && this.groupAttack==false && this.singleTarget == null)
			{
				this.singleTarget = critter;
			}
			else if(strategy!=null && this.groupAttack==false && this.singleTarget == null){
				this.singleTarget = strategy.executeStrategy(targets, this);
			}
//				if(this.groupAttack==false && targets.size()>0){
//					this.singleTarget = targets.get(0);
//				}		
			if(singleTarget!=null )
			{				
				singleTarget.decreaseHp(this.power);
				
				if(singleTarget.getCurrentHp()<=0)
				{
					coin.increaseCurrency(singleTarget.getValue());
					//targets.remove(singleTarget);
					singleTarget = null;
				}	
			}		
		}
			//
		else
		{
			singleTarget = null;
		}		
	
	}
		
		
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	private void drawEffect(Graphics2D g){
		if(singleTarget!=null){
			System.out.printf("this is draw: %d\n",singleTarget.getX());
			g.drawImage(arrowTowerEffect, singleTarget.getX(), 
					singleTarget.getY(), TileMap.getTileMap().getCellWidth(),
					TileMap.getTileMap().getCellHeight(),null);
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		drawEffect(g);
		
	}
}
