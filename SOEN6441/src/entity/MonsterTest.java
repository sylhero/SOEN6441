package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class MonsterTest {
	private TileMap tileMap;
	private Tile[][] map;
	private Image image;
	private int HP;
	private int x;
	private int y;
	// offset
	private int dx;
	private int dy;
	//direction
	public static final int LEFT  = 0;
	public static final int RIGHT = 1;
	public static final int UP    = 2;
	public static final int DOWN  = 3;
	
	public MonsterTest(TileMap tileMap){
		this.tileMap = tileMap;
		this.map     = tileMap.getMap();
		this.x       = tileMap.getOffSetX();
		this.y       = tileMap.getOffSetY();
		this.HP      = 500;
		this.image   = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
		
	}
	private void move(){
//		int direction = nextStepValidation();
//		switch(direction){
//		
//		case LEFT:  x -= dx; 
		//break;
//		case RIGHT: x += dx; break;
//		case UP:    y -= dy; break;
//		case DOWN:  y += dy; break;
//		
//		}
//		
		if(x<x+tileMap.getCellWidth()){
			x += 1;
			HP--;
			
		}
		else if(y<100+9*tileMap.getCellHeight()){
			y+=1;
			HP--;
		}else if(y>=100+9*tileMap.getCellHeight()){
			HP--;
			x+=1;
		}
		
		
		
	}
	private int nextStepValidation(){
		return 1;
		
		
	}
	public void update(){
		move();
		System.out.println(HP);
	}
	public void draw(Graphics2D g){
		if(HP>0){
			g.drawImage(image, x, 
					y, 
					tileMap.getCellWidth(),
					tileMap.getCellHeight(),null);
		}
		
	}

}
