package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class IceTower extends TowerBase {
	public static final Image iceTower  = LoadImage.loadImage("/images/icetower.png");
	public static final int ICETOWERTYPE  = 6;
	public IceTower(){
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ICETOWERTYPE;
		super.tileImage = iceTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 1;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
	}
	public IceTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ICETOWERTYPE;
		super.tileImage = iceTower;
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
	}
	public void update(){
		
	}
	public void draw(Graphics2D g){
		
	}

}
