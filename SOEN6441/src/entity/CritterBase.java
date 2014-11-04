package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import tilemap.Tile;
import tilemap.TileMap;

/**
 * An abstract class that is the super-type of all types of objects produced by the CritterFactory.
 * 
 * @author Yichen LI
 * @version 1.1.0
 *
 */

public abstract class CritterBase {
	
	protected TileMap tilemap;
	protected Tile [][] map;
	protected LinkedList<Point> correctPath;
	protected LinkedList<Point> correctPathCopy;
	protected Image image;
	// next step
	protected Point nextPoint;
	protected int x, y;
	
	// status 
	protected int hp;
	protected int armorRatio;
	protected int speed;	
	protected boolean isAlive;
	
	
	/**
	 * an abstract method move.
	 */
	
	abstract void move();
	
	/**
	 * an abstract method update.
	 */
	
	abstract void update();
	
	/**
	 * an abstract method draw.
	 */
	
	abstract void draw(Graphics2D g);
	
	
	/**
	 * To decrease HP of targeted critter, set to death if hp is less than zero.
	 * 
	 * @param critter
	 * @param tower
	 * @return critter
	 */
	
	public CritterBase decreaseHp(CritterBase critter, TowerBase tower)
	{
		if(critter.getHp() - tower.getPower() * (1 - critter.getArmorRatio()) > 0)
			critter.setHp(critter.getHp() - tower.getPower() * critter.getArmorRatio());
		else
			critter.setAlive(false);
		
		return critter;
		
	}

//============================================setters and getters=========================================================
	
	/**
	 * To get hp.
	 * 
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * To set hp.
	 * 
	 * @param hp
	 */
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * To get armorRatio.
	 * 
	 * @return armorRatio
	 */
	
	public int getArmorRatio() {
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
	 * To get speed.
	 * 
	 * @return speed
	 */

	public int getSpeed() {
		return speed;
	}

	/**
	 * To set speed.
	 * 
	 * @param speed
	 */
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * To get is the critter still alive.
	 * 
	 * @return isAlive
	 */

	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * To set isAlive of critter.
	 * 
	 * @param isAlive
	 */
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
	
	

}
