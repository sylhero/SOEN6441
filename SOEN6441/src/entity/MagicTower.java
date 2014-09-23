package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class MagicTower extends TowerBase{
	public static final Image magicTower  = LoadImage.loadImage("/images/magictower.png");
	public static final int MAGICTOWERTYPE  = 7;
	public MagicTower(){
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = MAGICTOWERTYPE;
		super.tileImage = magicTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 1;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
	}
	public MagicTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = MAGICTOWERTYPE;
		super.tileImage = magicTower;
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
