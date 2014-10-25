package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Arrow Tower. 
 * The arrow tower has its own image and type and other common attributes.
 * 
 * @Yulong Song, Xunrong Xia
 */
public class ArrowTower extends TowerBase{
	public static final Image arrowTower  = LoadImage.loadImage("/images/arrowtower.png");
	public static final int ARROWTOWERTYPE  = 4;
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	public ArrowTower(){
		super.name = "Arrow Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ARROWTOWERTYPE;
		super.tileImage = arrowTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 1;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
		super.value = this.cost;
		super.specialEffect = "None";
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
		super.name = "Arrow Tower";
		super.tileType  = ARROWTOWERTYPE;
		super.tileImage = arrowTower;
		super.tileX = tileX;
		super.tileY = tileY;
		super.tileHeight = tileHeight;
		super.tileWidth = tileWidth;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 1;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;
		super.value = this.cost;
		super.specialEffect = "None";
	}
	
	//The above two method will be used in the later builds.
	public void update(){
		
	}
	public void draw(Graphics2D g){

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
	

}
