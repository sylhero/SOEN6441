package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import critters.CritterBase;
import tilemap.TileMap;
import towerstrategy.NearestToExitStrategy;
import towerstrategy.NearestStrategy;
import towerstrategy.StrongestStrategy;
import towerstrategy.TowerStrategy;
import towerstrategy.WeakestStrategy;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Cannon Tower. 
 * The cannon tower has its own image and type and other common attributes.
 * This class will be used in the later builds.
 * 
 * @author Yulong Song, Xunrong Xia, Hongrui Guan
 * 
 */

public class CannonTower extends TowerBase{
	public static final Image cannonTower         = LoadImage.loadImage("/images/cannontower.png");
	public static final Image cannonTowerEffect   = LoadImage.loadImageIcon("/images/cannontowereffect.gif").getImage();
	public static final int CANNONTOWERTYPE  = 5;
	
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	public CannonTower(){
		this.name = "Cannon Tower";
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = CANNONTOWERTYPE;
		this.tileImage = cannonTower;
		this.level = 0;
		this.cost  = 200;
		this.groupAttack = false;
		this.power = 20;
		this.range = 2*TileMap.getTileMap().getCellWidth();
		this.refundRate = 0.3;
		this.towerSpeed = 4;
		this.upgradeCost = 20;	
		this.value = this.cost;
		this.specialEffect = "Burn";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
	}
	
	/**
	 * This constructor can assign the value of tile's attributes besides the tower's common attributes.
	 * 
	 * @param tileX The X coordinate of the tile.
	 * @param tileY The Y coordinate of the tile.
	 * @param tileWidth The width of the tile.
	 * @param tileHeight The height of the tile.
	 */	
	public CannonTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		this.name = "Cannon Tower";
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = CANNONTOWERTYPE;
		this.tileImage = cannonTower;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.level = 0;
		this.cost  = 200;
		this.groupAttack = false;
		this.power = 20;
		this.range = 2*tileWidth;
		this.refundRate = 0.3;
		this.towerSpeed = 4;
		this.upgradeCost = 20;
		
		this.value = this.cost;
		//this.value = level * upgradeCost + cost;

		this.specialEffect = "Burn";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
	}
	
	//The above two method will be used in the later builds.
	
	
	/**
	 * When a tower will be upgraded, its power, level and upgradeCose would be increased. 
	 * The method override the upgrade() method of TowerInterface.
	 */
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.value += upgradeCost;
		this.upgradeCost += 20;
		//this.value = level * upgradeCost + cost;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * To draw special effect on critter if it was attacked by cannon tower. 
	 * 
	 */
	@Override
	public void draw(Graphics2D g) {
		drawEffect(g,cannonTowerEffect);
		
	}
		
	/**
	 * This function is used for decreasing critter's life depends on different strategies.
	 * 
	 */
	@Override
	public void fire(CritterBase critter) {
				
		double distance = distance(critter);
		
		if(distance <= range && critter.getCurrentHp()>0)
		{
			
			if(!targets.contains(critter))
			{
				targets.add(critter);
			}
		
			//System.out.printf("target size:%d\n",targets.size());
		
			TowerStrategy strategy = setStrategy();	

			
			if (strategy ==null && this.groupAttack==false && singleTarget == null)

			{
				this.singleTarget = critter;
				
			} else if(strategy!=null && this.groupAttack==false ){
				
				this.singleTarget = strategy.executeStrategy(targets, this);
			}
	
			singleTarget.decreaseHp(this.power);
			singleTarget.setIsBurning(true);
			singleTarget.setAffectedTimes(80);
								
			if(singleTarget.getCurrentHp()<=0)
			{
				coin.increaseCurrency(singleTarget.getValue());
				targets.remove(singleTarget);
				singleTarget = null;
			}	
		} else { 
			
			if(targets.contains(critter)){
				singleTarget = null;
				targets.remove(critter);
			}
			
			if(critter.getAffectedTimes() > 0 && critter.getIsBurning()){
				int burn_times = critter.getAffectedTimes();
				
				System.out.print("Burning Times!!!!!!!!!!!!!!!!!!!!!!");
				
				System.out.println(burn_times);
				
	//			critter.setIsBurning(true);
				critter.setAffectedTimes(--burn_times);
				critter.decreaseHp(this.power);
				if(critter.getCurrentHp()<=0){
					coin.increaseCurrency(critter.getValue());
					critter.setIsBurning(false);
				}
				System.out.println("Critter HP:" + critter.getCurrentHp());
			} else {
				critter.setIsBurning(false);
			}

		}
			
	}

}
