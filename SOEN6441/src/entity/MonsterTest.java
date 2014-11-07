package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

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
	private LinkedList<Point> correctPath;
	private LinkedList<Point> correctPathCopy;
	private Image image;
	private int currentHP;
	private int originalHP;
	private int x;
	private int y;
	private int speedOffsetX;
	private int speedOffsetY;
	//monster value
	private int value;
	//next step
	private Point nextPoint;
	//move priority
	private int movePoint;
	//benchmark for moving
	public static int moveStandard = 0;
	//check if the monster is in tower's range
	private boolean isExit;
	
	
	
	public MonsterTest(LinkedList<Point> correctPath,int movePoint){
		this.tileMap         = TileMap.getTileMap();
		this.map             = tileMap.getMap();
		this.correctPath     = correctPath;
		this.correctPathCopy = copyCorrectPath();
		//start position
		Point startPoint     = correctPathCopy.pollFirst();
		//initial position
		this.x               = startPoint.y*tileMap.getCellWidth();
		this.y               = tileMap.getUpperOffSet()+startPoint.x*tileMap.getCellHeight();
		//speed offset slow:1 normal:2 fast:4
		this.speedOffsetX    = 2;
		this.speedOffsetY    = 2;
		//set next point
		this.nextPoint       = correctPathCopy.pollFirst();
		this.originalHP      = 5000;
		this.currentHP       = 5000;
		this.image           = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
		
		this.value 	         = 100;
		//keep some space between multiple critters
		this.movePoint       = movePoint;
		//reach the exit
		
		this.isExit          = false;
		
	}
	
	private  LinkedList<Point> copyCorrectPath(){
		LinkedList<Point> temp = new LinkedList<Point>();
		for(int i=0;i<correctPath.size();i++){
			temp.add(correctPath.get(i));
		}
		return temp;
		
	}
	public void setSpeedOffset(int x, int y){
		this.speedOffsetX = x;
		this.speedOffsetY = y;
	}
	private void move(){
		//use double to prevent 79/40 = 1 80/40 = 2
		double column = (double)x / tileMap.getCellWidth();
		double temp = (double)y - tileMap.getUpperOffSet();
        double row =  temp / tileMap.getCellHeight();
        int mapRow = map.length;
		int mapCol = map[0].length;
		if(row < mapRow && column < mapCol && nextPoint!=null && currentHP > 0){
			if(row < nextPoint.x){
				y+=speedOffsetY;
			}
			
			if(column < nextPoint.y){
				x+=speedOffsetX;
			}
			if(row > nextPoint.x){
				y-=speedOffsetY;
			}
			if(column > nextPoint.y){
				x-=speedOffsetX;
			}
			if(row == nextPoint.x && column == nextPoint.y){
				nextPoint = correctPathCopy.pollFirst();
			}
			
		}
		System.out.println(this.correctPath.size());
		//System.out.println()
//		System.out.printf("this is x: %d\n",x);
//		System.out.printf("this is y: %d\n",y);
//		System.out.printf("this is the row %d\n",row);
//		System.out.printf("this is the column %d\n", column);
//		System.out.printf("this is the next point %d, %d\n",nextPoint.x,nextPoint.y);
//       
		
	}
	private void endOfGameCheck(){
		if(correctPathCopy.size()==0){
			this.isExit = true;
		}
	}
	
	public void update(){
		if(movePoint == moveStandard && currentHP > 0){
			move();
			endOfGameCheck();
			
		}else{
			movePoint --;
		}
			
		
		
		//System.out.println(HP);
	}
	private void drawHP(Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawRect(x, y,tileMap.getCellWidth(),5);
		
		double HPRate = (double)currentHP/originalHP;
		
		g.setColor(Color.GREEN);
		
		if(HPRate<0.5){
			g.setColor(Color.ORANGE);
		}
		if(HPRate<0.2){
			g.setColor(Color.RED);
		}
		g.fillRect(x, y, (int)(tileMap.getCellWidth()*HPRate),5);
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public boolean getIsExit(){
		return isExit;
	}
	public int getCurrentHP(){
		return currentHP;
	}
	public int getOriginalHP(){
		return originalHP;
	}
	public void setCurrentHP(int HP){
		this.currentHP = HP;
	}
	public void setoriginalHP(int HP){
		this.originalHP = HP;
	}
	public void setValue(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	 
	
	public void draw(Graphics2D g){
		if(currentHP>0){
			drawHP(g);
			g.drawImage(image, x, 
					y, 
					tileMap.getCellWidth(),
					tileMap.getCellHeight(),null);
			System.out.printf("this is monster:%d\n",x);
//			g.drawImage(fireEffect, x, 
//					y, 
//					tileMap.getCellWidth(),
//					tileMap.getCellHeight(),null);
//		
		}
		
	}

}
