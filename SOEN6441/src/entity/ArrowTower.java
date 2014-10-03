package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class ArrowTower extends TowerBase{
	public static final Image arrowTower  = LoadImage.loadImage("/images/arrowtower.png");
	public static final int ARROWTOWERTYPE  = 4;
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
	public void update(){
		
	}
	public void draw(Graphics2D g){

	}
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;
		this.value = level * upgradeCost + cost;
		
	}
	

}
