package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is the class of creating Monster.
 * This class only define the basic movement of the monster.
 * The monster will have more properties in later builds.
 * 
 * @author Kun Wang
 * 
 */
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
	
	public MonsterTest(){
		tileMap      = TileMap.getTileMap();
		this.map     = tileMap.getMap();
		this.x       = tileMap.getOffSetX();
		this.y       = tileMap.getOffSetY();
		this.HP      = 500;
		this.image   = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
		
	}
	private void move(){
		x+=1;
		HP--;
		
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
