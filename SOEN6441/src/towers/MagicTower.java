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
		this.name = "Magic Tower";
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = MAGICTOWERTYPE;
		this.tileImage = magicTower;
		this.level = 0;
		this.cost  = 15;
		this.groupAttack = true;
		this.power = 10;
		this.range = 2*TileMap.getTileMap().getCellWidth();
		this.refundRate = 0.5;
		this.towerSpeed = 3;
		this.upgradeCost = 10;	
		this.value = level * upgradeCost + cost;
		this.specialEffect = "Splash";
		this.targets = new ArrayList<CritterBase>();
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
		this.name = "Magic Tower";
		this.map = TileMap.getTileMap().getMap();
		this.tileType  = MAGICTOWERTYPE;
		this.tileImage = magicTower;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.level = 0;
		this.cost  = 15;
		this.groupAttack = true;
		this.power = 10;
		this.range = 2*tileWidth;
		this.refundRate = 0.5;
		this.towerSpeed = 3;
		this.upgradeCost = 10;
		this.value = level * upgradeCost + cost;
		this.specialEffect = "None";
		this.targets = new ArrayList<CritterBase>();
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
		drawEffect(g,magicTowerEffect);
		
	}
	@Override
	public void fire(CritterBase critter) {
		// TODO Auto-generated method stub
		
		double distance = distance(critter);
		
		System.out.println(distance);
		
		if(distance <= range && critter.getCurrentHp()>0){
			
				critter.decreaseHp(this.power);
				critter.setIsSplash(true);
				
				if(critter.getCurrentHp()<=0){
					coin.increaseCurrency(critter.getValue());
				}				
			} else{
				
			critter.setIsSplash(false);
		}
	}
}
