package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import log.GlobalLog;
import log.WaveLog;
import critters.CritterBase;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Magic Tower. 
 * The magic tower has its own image and type and other common attributes.
 * This tower will be used in the later builds.
 * 
 * @author Yulong Song, Xunrong Xia, Hongrui Guan
 */

public class MagicTower extends TowerBase{
	
	public static final Image magicTower         = LoadImage.loadImage("/images/magictower.png");
	public static final Image magicTowerEffect   = LoadImage.loadImageIcon("/images/magictowereffect.gif").getImage();
	public static final int MAGICTOWERTYPE  = 7;
	public static int NAMENUMBER = 0;
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	public MagicTower(){
		this.name = "Magic Tower"+NAMENUMBER++;
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = MAGICTOWERTYPE;
		this.tileImage = magicTower;
		this.level = 0;

		this.cost  = 250;
		this.groupAttack = true;
		this.power = 10;
		this.range = 2*TileMap.getTileMap().getCellWidth();
		this.refundRate = 0.3;
		this.towerSpeed = 3;
		this.upgradeCost = 10;	
		this.value = cost;

		this.specialEffect = "Splash";
		this.targets = new ArrayList<CritterBase>();
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
	
	public MagicTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		this.name = "Magic Tower"+NAMENUMBER++;
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = MAGICTOWERTYPE;
		this.tileImage = magicTower;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.level = 0;
		this.cost  = 250;

		this.groupAttack = true;
		this.power = 10;
		this.range = 2*tileWidth;

		this.value = this.cost;
		//this.value = level * upgradeCost + cost;
		
		this.refundRate = 0.3;
		this.towerSpeed = 3;
		this.upgradeCost = 25;
		
		this.specialEffect = "Splash";

		this.targets = new ArrayList<CritterBase>();
		
		this.individualTowerLog = new ArrayList<String>();
	}
	
	//The above two method will be used in the later builds.
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 */
	@Override
	public void upgrade() {
		this.power += 10;
		this.level += 1;
		this.value +=upgradeCost;
		this.upgradeCost += 5;
		//this.value = level * upgradeCost + cost;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * To draw special effect on critter if it was attacked by magic tower.
	 */
	@Override
	public void draw(Graphics2D g) {
		if(targets.size()!=0){
			for(int i =0; i< targets.size(); i++){
				g.drawImage(magicTowerEffect, targets.get(i).getX(), 
						targets.get(i).getY(), tileWidth,
					tileHeight,null);
			}
		}
	}
	
	/**
	 * This function is used for decreasing critter's life depends on different strategies.
	 */
	@Override
	public void fire(CritterBase critter) {
		// TODO Auto-generated method stub

		double distance = distance(critter);
		
		//System.out.println(distance);
		
		if(distance <= range && critter.getCurrentHp()>0){
			
			if(!targets.contains(critter) )
			{
				targets.add(critter);
			}
			this.addIndevidualTowerLog(this.name + " attacks "+critter.getName()+", HP - "+this.power+"\n");
			addToAllTowerLog(this.name + " attacks "+critter.getName()+", HP - "+this.power+"\n");
			GlobalLog.addToGlobalLog(this.name + " attacks "+critter.getName()+", HP - "+this.power+"\n");
			WaveLog.addToWaveLog(this.name + " attacks "+critter.getName()+", HP - "+this.power+"\n");
			critter.decreaseHp(this.power);
				
			if(critter.getCurrentHp()<=0){
				this.addIndevidualTowerLog(this.name + " kills "+critter.getName()+", coin + "+" "+critter.getValue()+"\n");
				addToAllTowerLog(this.name + " kills "+critter.getName()+", coin + "+" "+critter.getValue()+"\n");
				GlobalLog.addToGlobalLog(this.name + " kills "+critter.getName()+", coin + "+" "+critter.getValue()+"\n");
				WaveLog.addToWaveLog(this.name + " kills "+critter.getName()+", coin + "+" "+critter.getValue()+"\n");
				coin.increaseCurrency(critter.getValue());
			}				
		} else{
			
			if(targets.contains(critter)){
				
				targets.remove(critter);
			}
		}
	}
}
