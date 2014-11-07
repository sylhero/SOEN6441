package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Cannon Tower. 
 * The cannon tower has its own image and type and other common attributes.
 * This class will be used in the later builds.
 * 
 * @author Yulong Song, Xunrong Xia
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
		super.name = "Cannon Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = CANNONTOWERTYPE;
		super.tileImage = cannonTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = TileMap.getTileMap().getCellWidth();
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
		super.value = level * upgradeCost + cost;
		super.specialEffect = "None";
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
		super.name = "Cannon Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = CANNONTOWERTYPE;
		super.tileImage = cannonTower;
		super.tileX = tileX;
		super.tileY = tileY;
		super.tileHeight = tileHeight;
		super.tileWidth = tileWidth;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = tileWidth;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;
		super.value = level * upgradeCost + cost;
		super.specialEffect = "None";
	}
	
	//The above two method will be used in the later builds.
	
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 * The method override the upgrade() method of TowerInterface.
	 */
	
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;
		
	}

	@Override
	public void fire(MonsterTest monster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
