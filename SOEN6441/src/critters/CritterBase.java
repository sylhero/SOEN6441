package critters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

import currency.Coin;
import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;

/**
 * An abstract class that is the super-type of all types of objects produced by the CritterFactory.
 * 
 * @author Yichen LI
 * @version 1.1.5
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
	public static final Image freezingImage = LoadImage
			.loadImage("/images/iceicon.png");
	public static final Image burningImage = LoadImage
			.loadImage("/images/fireicon.png");
	
//	public static final Image lighteningImage = LoadImage
//			.loadImage("/images/lighteningicon.png");
	
	
	
	// status 
	protected int currentHp;
	protected int originalHp;
	protected float armorRatio;
	protected boolean atExit;
	protected int value; // value of critter
	protected boolean isFreezing;
	protected boolean isBurning;
	protected boolean isSplash;
	
	public int burningTime;
	public int freezingTime;
	
	
	

	
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
			if(isFreezing){
				
				g.drawImage(freezingImage, x, 
						 
								y, 
								tileMap.getCellWidth(),
								tileMap.getCellHeight(),null);
		
			}
			if(isBurning){
				
				g.drawImage(burningImage, x, 
						y, 
						tileMap.getCellWidth(),
						tileMap.getCellHeight(),null);
				
			}
			
		}
		

		}
	


//============================================setters and getters=========================================================
	
	/**
	 * To get Hp.
	 * 
	 * @return int Hp
	 */
	
	public int getCurrentHp() {
		
		return currentHp;
	}
	
	/**
	 * To set Hp.
	 * 
	 * @param currentHp
	 */

	public void setCurrentHp(int currentHp) {
		
		this.currentHp = currentHp;
	}
	
	/**
	 * To return isAtExit.
	 * 
	 * @return isAtExit
	 */

	public boolean isAtExit() {
		
		return atExit;
	}

	/**
	 * To set isAtExit.
	 * 
	 * @param atExit
	 */
	
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

	/**
	 * To get X.
	 * 
	 * @return int X
	 */
	
	public int getX() {
		
		return x;
	}
	
	/**
	 * To set X.
	 * 
	 * @param new_x
	 */
	public void setX(int new_x){
		
		this.x = new_x;
	}

	/**
	 * To get Y.
	 * 
	 * @return int Y
	 */
	
	public int getY() {
		
		return y;
	}
	
	/**
	 * To set Y.
	 * 
	 * @param new_y
	 */
	public void setY(int new_y){
		
		this.y = new_y;
	}
	
	/**
	 * To get critter value.
	 * 
	 * @return int value
	 */

	public int getValue() {
		
		return value;
	}
	
	/**
	 * To set freezing status.
	 * 
	 * @param result
	 */
	
	public void setIsFreezing(boolean result){
		
		isFreezing = result;
	}
	
	/**
	 * To get freezing status.
	 * 
	 * @return boolean isfreezing
	 */
	
	public boolean getIsFreezing(){
		return isFreezing;
	}
	
	/**
	 * To set burn status.
	 * 
	 * @param result
	 */
	
	public void setIsBurning(boolean result){
		isBurning = result;
	}
	
	/**
	 * To get burn status.
	 * 
	 * @return boolean isBurning
	 */
	
	public boolean getIsBurning(){
		return isBurning;
	}
	

	/**
	 * To set affected times.
	 * 
	 * @param affected_times
	 */
	
	public void setAffectedTimes(int affected_times){
		
		this.burningTime = affected_times;
		
	}
	
	
	/**
	 * To get affected times.
	 * 
	 * @return int affectedTimes.
	 */
	public int getAffectedTimes(){
		
		
		return this.burningTime;
	}
	
	/**
	 * To get freezing time.
	 * 
	 * @return int freezingTime
	 */

	public int getFreezingTime() {
		return freezingTime;
	}

	
	/**
	 * To set freezing time.
	 * 
	 * @param freezingTime
	 */
	public void setFreezingTime(int freezingTime) {
		this.freezingTime = freezingTime;
	}
	
	/**
	 * To return speed offset x.
	 * 
	 * @return int speed offset x
	 */
	
	public int getSpeedOffsetX() {
		return speedOffsetX;
	}

	/**
	 * To set speed offset X.
	 * 
	 * @param speedOffsetX
	 */
	
	public void setSpeedOffsetX(int speedOffsetX) {
		this.speedOffsetX = speedOffsetX;
	}
	
	/**
	 * To return speed offset Y.
	 * 
	 * @return int speed offset Y
	 */

	public int getSpeedOffsetY() {
		return speedOffsetY;
	}

	/**
	 * To set speed offset Y.
	 * 
	 * @param speedOffsetY
	 */
	
	public void setSpeedOffsetY(int speedOffsetY) {
		this.speedOffsetY = speedOffsetY;
	}
	

}
