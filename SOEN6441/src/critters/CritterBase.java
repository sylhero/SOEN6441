package critters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;

/**
 * An abstract class that is the super-type of all types of objects produced by the CritterFactory.
 * 
 * @author Yichen LI
 * @version 1.1.1
 *
 */

public abstract class CritterBase {
	
	protected TileMap tileMap;
	protected Tile [][] map;
	protected LinkedList<Point> correctRoute;
	protected LinkedList<Point> correctRouteCopy;
	protected Image image;
	protected Point nextPoint; 	// move priority
	protected int speedOffsetX, speedOffsetY; 
	protected int x, y;
	protected int movePoint;
	public static int moveStandard = 0;	// benchmark of moving
	protected Point startPoint;
	public static final Image frozenImage = LoadImage
			.loadImage("/images/iceicon.png");
	public static final Image burningImage = LoadImage
			.loadImage("/images/fireicon.png");
	
	public static final Image lighteningImage = LoadImage
			.loadImage("/images/lighteningicon.png");
	
	
	
	// status 
	protected int currentHp;
	protected int originalHp;
	protected float armorRatio;
	protected boolean atExit;
	protected int value; // value of critter
	protected boolean isFrozen;
	protected boolean isBurning;
	protected boolean isSplash;
	
	public int burnTimes;
	
	
	

	
	/**
	 *To decrease HP of critters.
	 * 
	 * @param decHP
	 */
	
	public void decreaseHp(int decHP)
	{
		this.currentHp -= (1 - this.armorRatio) * decHP;		
	}
	
	/**
	 * To copy correctRoute to a new linkedList.
	 * 
	 * @return temp a linkedList 
	 */
	
	protected LinkedList<Point> copyCorrectRoute()
	{
		LinkedList<Point> temp = new LinkedList<Point>();
		
		for(ListIterator<Point> iterator = correctRoute.listIterator(); iterator.hasNext();)
		{
			temp.add(iterator.next());
		}
	
		return temp;
		
	}
	
	
	/**
	 * To set speed offset.
	 * 
	 * @param x
	 * @param y
	 */
	
	public void setSpeedOffset(int x, int y)
	{
		this.speedOffsetX = x;
		this.speedOffsetY = y;
	}

	
	/**
	 * To make critters moving.
	 * 
	 */
	
	protected void move()
	{
		//use double to prevent 79/40 = 1 80/40 = 2
		double column = (double)x / tileMap.getCellWidth();
		double temp = (double)y - tileMap.getUpperOffSet();
        double row =  temp / tileMap.getCellHeight();
        int mapRow = map.length;
		int mapCol = map[0].length;
		
		if(row < mapRow && column < mapCol && nextPoint != null && currentHp > 0)
		{
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
			
			if(row == nextPoint.x && column == nextPoint.y)
			{
				nextPoint = correctRouteCopy.pollFirst();
			}
		}
		
			//System.out.println(this.correctRoute.size());
			
		}
	
	
	/**
	 * To check if the critters reach the exit.
	 */
	
	protected void endOfGameCheck()
	{
		if(correctRouteCopy.size() == 0)
		{
			this.atExit = true;
		}
	}
	
	
	/**
	 * To update critters movements.
	 */
	
	public void update()
	{
		
		if(movePoint == moveStandard && currentHp > 0)
		{	
			move();
			endOfGameCheck();
			System.out.println(currentHp);
			
		}else
		{
			movePoint --;
		}
			
		
		
		//System.out.println(HP);
	}
	
	
	/**
	 * To draw critters HP status on the map.
	 * 
	 * @param g
	 */
	
	public void drawHP(Graphics2D g){
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y,tileMap.getCellWidth(),5);
		
		double HPRate = (double)currentHp/originalHp;
		
		g.setColor(Color.GREEN);
		
		if(HPRate<0.5){
			g.setColor(Color.ORANGE);
		}
		
		if(HPRate<0.2){
			g.setColor(Color.RED);
		}
		
		g.fillRect(x, y, (int)(tileMap.getCellWidth() * HPRate), 5);
	} 
	
	/**
	 * To draw critters on the map.
	 * 
	 * @param g
	 */
	
	public void draw(Graphics2D g){
		
		if(currentHp>0){
			drawHP(g);
			g.drawImage(image, x, 
					y, 
					tileMap.getCellWidth(),
					tileMap.getCellHeight(),null);
			//System.out.printf("this is critter:%d\n",x);
			if(isFrozen){
				g.drawImage(frozenImage, x, 
						 
								y, 
								tileMap.getCellWidth(),
								tileMap.getCellHeight(),null);
		
			}
			if(isBurning){
				isFrozen = false;
				g.drawImage(burningImage, x, 
						y, 
						tileMap.getCellWidth(),
						tileMap.getCellHeight(),null);
				
			}
			if(isSplash){
				g.drawImage(lighteningImage, x, 
						y, 
						tileMap.getCellWidth(),
						tileMap.getCellHeight(),null);
				
			}
		}
		

		}
	


//============================================setters and getters=========================================================
	

	
	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public boolean isAtExit() {
		return atExit;
	}

	public void setAtExit(boolean atExit) {
		this.atExit = atExit;
	}



	/**
	 * To get armorRatio.
	 * 
	 * @return armorRatio
	 */
	
	public float getArmorRatio() {
		return armorRatio;
	}

	/**
	 * To set armorRatio.
	 * 	
	 * @param armorRatio
	 */
	
	public void setArmorRatio(int armorRatio) {
		this.armorRatio = armorRatio;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	public void setIsFrozen(boolean result){
		isFrozen = result;
	}
	public boolean getIsFrozen(){
		return isFrozen;
	}
	
	public void setIsSplash(boolean result){
		this.isSplash = result;
	}
	public boolean getIsSplash(){
		return this.isSplash;
	}
	
	public void setIsBurning(boolean result){
		isBurning = result;
	}
	
	
	public boolean getIsBurning(){
		return isBurning;
	}
	/**
	 * To get speed.
	 * 
	 * @return speed
	 */

	
	
	public void setBurnTimes(int burn_times){
		
		this.burnTimes = burn_times;
		
	}
	
	public int getBurnTimes(){
		
		
		return this.burnTimes;
	}


	
	
	

}
