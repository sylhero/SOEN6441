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
 * An abstract class that is the super-type of all types of objects of critters.
 * @author Yichen LI
 * @version 1.2.1
 *
 */

public abstract class CritterBase {
	
	protected TileMap tileMap;
	protected Tile [][] map;
	protected String name;
	public static int NAMENUMBER;
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
	 * @param decHP The amount of HP should be decreased. 
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
	
	protected LinkedList<Point> copyCorrectRoute(LinkedList<Point> correctRoute)
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
	 * @param x The speed offset x. 
	 * @param y The speed offset y. 
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
	 * @param g The graphic object.
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
	 * @param g The graphic object. 
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
	 * To set movePoint
	 * 
	 * @param movePoint to keep some space between critters
	 */
	
	public void setMovePoint(int movePoint) 
	{
		this.movePoint = movePoint;
	}
	
	/**
	 * To get move point
	 * @return move point 
	 */
	
	public int getMovePoint(){
		
		return this.movePoint;
	}
	/**
	 * To set correctRoute of each critter
	 * 
	 * @param correctRoute to lead the critter from entry point to exit point
	 */
	public void setCorrectRouteCopy(LinkedList<Point> correctRoute)
	{
		this.correctRouteCopy = this.copyCorrectRoute(correctRoute);
	}

	/**
	 * To get correct route.
	 * 
	 * @return a linkedList of correct route
	 */
	public LinkedList<Point> getCorrectRouteCopy()
	{
		return this.correctRouteCopy;
	}

	/**
	 * To set the start point of the critter.
	 * 
	 * @param route of a critter
	 */
	public void setStartPoint(LinkedList<Point> route) {
		
		this.startPoint = route.pollFirst();
	}
	
	/**
	 * To get the start point.
	 * 
	 * @return startPoint of a critter
	 */
	
	public Point getStartPoint(){
		
		return this.startPoint;
	}
	/**
	 * To set the next point of a critter.
	 * 
	 * @param route of a critter
	 */
    public void setNextPoint(LinkedList<Point> route) {
		
		this.nextPoint = route.pollFirst();
	}

    /**
     * To get next point of a critter.
     * @return next point of a critter
     */
    
    public Point getNextPoint(){
    	
    	return this.nextPoint;
    }
    
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
	 * @param currentHp The current HP.
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
	 * @param atExit a boolean variable to indicate if it at the exit.
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
	 * @param armorRatio The armor ratio.
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
	 * @param new_x The new x coordinate.
	 */
	public void setX(){
		
		this.x = startPoint.y * tileMap.getCellWidth();
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
	 * @param new_y The new y coordinate.
	 */
	public void setY(){
		
		this.y = tileMap.getUpperOffSet() + startPoint.x * tileMap.getCellHeight();
	}
	
	/**
	 * To set X coordinate.
	 * 
	 * @param x axes.
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * To set Y coordinate.
	 * 
	 * @param y axes.
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * To get critter name. 
	 * 
	 * @return a string name 
	 */
	
	public String getName(){
		return this.name;
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
	 * @param result To indicate if it is freezing or not.
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
	 * @param result To indicate if it is freezing or not.
	 */
	
	public void setIsBurning(boolean result){
		isBurning = result;
	}
	
	/**
	 * To get burn status.
	 * 
	 * @return boolean isBurning.
	 */
	
	public boolean getIsBurning(){
		return isBurning;
	}
	

	/**
	 * To set affected times.
	 * 
	 * @param affected_times The affected times.
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
	 * @param freezingTime The freezing time.
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
	 * @param speedOffsetX The speed offset of X-coordinate.
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
	 * @param speedOffsetY The speed offset of Y-coordinate.
	 */
	
	public void setSpeedOffsetY(int speedOffsetY) {
		this.speedOffsetY = speedOffsetY;
	}
	

}
