package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import log.CollectiveLog;
import log.GlobalLog;
import log.WaveLog;
import critters.CritterBase;
import critters.NormalCritter;
import currency.Coin;
import tilemap.TileMap;
import towerstrategy.NearestToExitStrategy;
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
public class ArrowTower extends TowerBase implements Serializable{

	private static final long serialVersionUID = 2806295576326098798L;
	
	public transient static Image arrowTower         = LoadImage.loadImage("/images/arrowtower.png");
	public transient static Image arrowTowerEffect   = LoadImage.loadImageIcon("/images/arrowtowereffect.gif").getImage();
	public static final int ARROWTOWERTYPE  = 4;
	
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	public ArrowTower(){
		this.name = "Arrow Tower" + NAMENUMBER++;
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = ARROWTOWERTYPE;
		this.tileImage = arrowTower;
		this.level = 0;
		this.cost  = 150;
		this.groupAttack = false;
		this.power = 10;
		this.range = 3*TileMap.getTileMap().getCellWidth();
		this.refundRate = 0.3;
		this.towerSpeed = 3;
		this.upgradeCost = 10;	
		this.value = this.cost;
		this.specialEffect = "None";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
		this.individualTowerLog = new ArrayList<String>();
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
		this.name = "Arrow Tower"+NAMENUMBER++;
		this.tileType  = ARROWTOWERTYPE;
		this.tileImage = arrowTower;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.level = 0;
		this.cost  = 150;
		this.groupAttack = false;
		this.power = 10;
		this.range = 3*tileWidth;
		this.refundRate = 0.3;
		this.towerSpeed = 3;
		this.upgradeCost = 10;
		this.value = this.cost;
		this.specialEffect = "None";
		this.targets = new ArrayList<CritterBase>();
		this.singleTarget = null;
		this.towerStratgyType = 0;
		this.individualTowerLog = new ArrayList<String>();
		
	}
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 * The tower's value would be changed too.
	 */
	@Override
	public void upgrade() {
		this.power += 10;
		this.level += 1;

		this.value += upgradeCost;
		//this.value = level * upgradeCost + cost;
		this.upgradeCost += 10;

		
	}
	/**
	 * <p>
	 * This method used for tower attack critters. 
	 * This method will save the critters which in the attack range in an array list. 
	 * Then based on different strategy to get the critter which should be attacked.
	 * After the tower attack the critter, it will check if the critter has died or not in the attack range,
	 * then decided if the critter should move from the array list. 
	 * <p>
	 * The arrow tower would only make the critter lose hp. 
	 * 
	 */	
	@Override
	public void fire(CritterBase critter) {	
		
		double distance = distance(critter);
		
		if(distance <= range && critter.getCurrentHp()>0)
			
		{			
			if(!targets.contains(critter) )
			{
				targets.add(critter);
			}
						
			TowerStrategy strategy = setStrategy();	
			if (strategy ==null && this.groupAttack==false && singleTarget == null)
			{
				this.singleTarget = critter;
			}
			else if(strategy!=null && this.groupAttack==false )
			{
				
				this.singleTarget = strategy.executeStrategy(targets, this);
								
			}
			//this.addIndevidualTowerLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			//CollectiveLog.addToAllTowerLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			//GlobalLog.addToGlobalLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			Date date = new Date();
			WaveLog.getObject().addToWaveLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n",date);
			singleTarget.decreaseHp(this.power);
			
			if(singleTarget.getCurrentHp()<=0)
			{
				//this.addIndevidualTowerLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				//CollectiveLog.addToAllTowerLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				//GlobalLog.addToGlobalLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				Date date1 = new Date();
				WaveLog.getObject().addToWaveLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n",date1);
				coin.increaseCurrency(singleTarget.getValue());
				//remove the dead critter
				targets.remove(singleTarget);
				singleTarget = null;
			}	
					
		}
		else
		{
			//remove the critter from targets if it is out of the range
			if(targets.contains(critter)){
				singleTarget = null;
				targets.remove(critter);
				
			}
			
		}		
	
	}
		
		
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * To draw special effect on critter if it was attacked by arrow tower.
	 */		
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		drawEffect(g,arrowTowerEffect);		
	}
}
