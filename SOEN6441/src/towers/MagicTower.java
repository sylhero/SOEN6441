package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

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
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	public MagicTower(){
		super.name = "Magic Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = MAGICTOWERTYPE;
		super.tileImage = magicTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = true;
		super.power = 10;
		super.range = 2*TileMap.getTileMap().getCellWidth();
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
		super.value = level * upgradeCost + cost;
		super.specialEffect = "Splash";
		super.targets = new ArrayList<CritterBase>();
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
		super.name = "Magic Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = MAGICTOWERTYPE;
		super.tileImage = magicTower;
		super.tileX = tileX;
		super.tileY = tileY;
		super.tileHeight = tileHeight;
		super.tileWidth = tileWidth;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = true;
		super.power = 10;
		super.range = 2*tileWidth;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;
		super.value = level * upgradeCost + cost;
		super.specialEffect = "None";
		super.targets = new ArrayList<CritterBase>();
	}
	
	//The above two method will be used in the later builds.
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 */
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fire(CritterBase critter) {
		// TODO Auto-generated method stub
		int critterX   = critter.getX();
		int critterY   = critter.getY();
		int critterCenterX = critterX + TileMap.getTileMap().getCellHeight() / 2;
		int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;
		int towerCenterX   = tileX + TileMap.getTileMap().getCellHeight() / 2;
		int towerCenterY   = tileY + TileMap.getTileMap().getCellWidth() / 2;
		double distance = Math.sqrt(Math.pow(critterCenterX-towerCenterX, 2) + 
				Math.pow(critterCenterY-towerCenterY, 2));
		
		System.out.println(distance);
		
		if(distance <= range){
			
			if(!targets.contains(critter) && critter.getCurrentHp() > 0)
				targets.add(critter);
			
//			if(!targets.contains(monster)&& monster.getCurrentHP()>0){
//				targets.add(monster);
//			}
			
			System.out.printf("target size:%d\n",targets.size());
			
			//if(this.groupAttack==false && super.singleTarget == null){
			//	super.singleTarget = critter;
			//}
			
			if(this.groupAttack==false && targets.size()>0){
				super.singleTarget = targets.get(0);
			}
			
			
			if(singleTarget!=null){
				
				singleTarget.decreaseHp(this.power);
				
//				int targetHP = singleTarget.getCurrentHp();
//				singleTarget.setCurrentHp(targetHP-this.power);
				if(singleTarget.getCurrentHp()<=0){
					coin.increaseCurrency(singleTarget.getValue());
					//targets.remove(singleTarget);
					singleTarget = null;
				}
				
			}			
		}else{
			singleTarget = null;
		}		
	}


}
