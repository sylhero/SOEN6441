package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import critters.CritterBase;
import currency.Coin;
import tilemap.Tile;
import tilemap.TileMap;
import towerstrategy.NearestToExitStrategy;
import towerstrategy.NearestStrategy;
import towerstrategy.Strategy;
import towerstrategy.StrongestStrategy;
import towerstrategy.TowerStrategy;
import towerstrategy.WeakestStrategy;

/**
 * This is the abstract class for all kinds of towers.
 * This class includes the common attributes of tower. And it extends Tile class.
 * 
 * @author Xunrong Xia
 * 
 */

public abstract class TowerBase extends Tile implements TowerInterface{
		
	
	protected String name;
	protected int level;
	protected int cost;
	protected double refundRate; //the value is between 0 to 1, it is a percentage
	protected int range; //it is the radius of the attack range.
	protected int power; 
	protected int towerSpeed;
	protected boolean groupAttack;
	protected int upgradeCost;
	protected String specialEffect;
	protected int value; //The initial value will be same as the cost, but after the tower upgrade, the value would be changed.
	protected Tile[][] map;
	protected ArrayList<CritterBase> targets;
	protected CritterBase singleTarget;
	protected int towerStratgyType;
	protected Coin coin = Coin.getCoinObject();
	protected ArrayList<String> individualTowerLog;
	protected static ArrayList<String> ALLTOWERLOG = new ArrayList<String>();
	
	/**
	 * Get the tower's value. 
	 * The initial value will be same as the cost. 
	 * A tower's value would be changed if the tower upgrade.
	 * @return The value of the tower.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * set the value of the tower.
	 * @param value It is the value of the tower.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * get the special effect of the tower.
	 * @return The special effect of the tower.
	 */
	public String getSpecialEffect() {
		return specialEffect;
	}

	/**
	 * set the special effect of the tower.
	 * @param specialEffect It is the special effect of the tower.
	 */
	public void setSpecialEffect(String specialEffect) {
		this.specialEffect = specialEffect;
	}

	/**
	 * This method used for upgrade level, the value of the level would increase 1.
	 */
	public void UpgradeLevel(){
		level += 1;
	}

	/**
	 * Get the level of the tower.
	 * @return The level of the tower.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set the level of the tower.
	 * @param level The level of the tower.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Get the cost of the tower, the cost is same as the initial value of a tower before any upgrade of the tower. 
	 * @return The cost of the tower.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Set the cost of the tower.
	 * @param cost The cost of the tower.
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	public ArrayList<String> getIndividualTowerLog(){
		return individualTowerLog;
	}
	public static void addToAllTowerLog(String log){
		Date date = new Date();
		ALLTOWERLOG.add(date.toString()+"   "+ log);
	}
	public void addIndevidualTowerLog(String log){
		Date date = new Date();
		individualTowerLog.add(date.toString()+"   "+ log);
	}
	public String getAllIdividualTowerLog(){
		String result = "";
		for(String s: individualTowerLog){
			result += s;
		}
		return result;
	}
	public static String getAllTowerLog(){
		String result = "";
		for(String s : ALLTOWERLOG){
			result += s;
			
		}
		return result;
		
	}

	/**
	 * Get the refund rate of the tower. 
	 * The refund rate is used for calculate the coin can be get when the tower be sold. 
	 * @return The refund rate of the tower.
	 */
	public double getRefundRate() {
		return refundRate;
	}

	/**
	 * Set the refund rate of the tower.
	 * @param refundRate It is the refund rate of the tower.
	 */
	public void setRefundRate(double refundRate) {
		this.refundRate = refundRate;
	}

	/**
	 * Get the attack range of the tower.
	 * @return The attack range of the tower.
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Set the attack range of the tower.
	 * @param range The attack range of the tower.
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * Get the power of the tower.
	 * @return the power of the tower.
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Set the power of the tower.
	 * @param power the power of the tower.
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * Get the speed of the tower.
	 * @return The speed of the tower.
	 */
	public int getTowerSpeed() {
		return towerSpeed;
	}

	/**
	 * Set the speed of the tower.
	 * @param towerSpeed The speed of the tower.
	 */
	public void setTowerSpeed(int towerSpeed) {
		this.towerSpeed = towerSpeed;
	}

	/**
	 * Get if the tower can group attack.
	 * @return Return True, if the tower can group attack. Return False, if the tower cannot group attack.
	 */
	public boolean getGroupAttack() {
		return groupAttack;
	}

	/**
	 * Set the groupattack value.
	 * @param groupAttack The value is true if the Tower can attack a group of monster. The value is false, if the tower cannot do that.
	 */
	public void setGroupAttack(boolean groupAttack) {
		this.groupAttack = groupAttack;
	}

	/**
	 * Get the upgrade cost of the tower.
	 * @return The upgrade cost of the tower.
	 */
	public int getUpgradeCost() {
		return upgradeCost;
	}

	/**
	 * Set the upgrade cost of the tower.
	 * @param upgradeCost The upgrade cost of the tower.
	 */
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	/**
	 * Get the name of the tower.
	 * @return The name of the tower.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the tower.
	 * @param name The name of the tower.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	/**
	 * get the critters in the attack range
	 * @return an array list contain the critters in the attack range.
	 */
	public ArrayList<CritterBase> getTarget(){
		return targets;
	}
	/**
	 * set the strategy type of the tower.
	 * @param strategy The strategy type.
	 */
	public void setStrategyType(int strategy){
		this.towerStratgyType = strategy;
	}
	/**
	 * get the strategy type of the tower.
	 * @return the strategy type.
	 */
	public int getStrategyType(){
		return this.towerStratgyType;
	}
	/**
	 * Based on the strategy type to set the corresponding strategy in TowerStrategy.
	 * This uses the Strategy Pattern.
	 * 
	 * @return The strategy will be used by the tower.
	 */
	public TowerStrategy setStrategy() {

		TowerStrategy strategy = new TowerStrategy();

		if (this.towerStratgyType == 1)

			strategy.setStrategy(new WeakestStrategy());

		else if (this.towerStratgyType == 2)

			strategy.setStrategy(new StrongestStrategy());

		else if (this.towerStratgyType == 3)

			strategy.setStrategy(new NearestStrategy());

		else if (this.towerStratgyType == 4)

			strategy.setStrategy(new NearestToExitStrategy());

		else

			strategy = null;

		return strategy;

	}
	
	/**
	 * This method used to calculate the distance between the critter and the tower.
	 * @param critter This the critter whose distance with the tower want to be calculate.
	 * @return The distance between the tower and the critter.
	 */
	public double distance(CritterBase critter) {

		int critterX = critter.getX();

		int critterY = critter.getY();

		int critterCenterX = critterX + TileMap.getTileMap().getCellHeight()
				/ 2;

		int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;

		int towerCenterX = tileX + TileMap.getTileMap().getCellHeight() / 2;

		int towerCenterY = tileY + TileMap.getTileMap().getCellWidth() / 2;

		double distance = Math.sqrt(Math.pow(critterCenterX - towerCenterX, 2) +

		Math.pow(critterCenterY - towerCenterY, 2));

		return distance;

	}
	
	/**
	 * This method used to draw the special effect if the tower used its own special effect.
	 * 
	 * @param g  The graphic object.
	 * @param effect The special effect image of tower.
	 */
	public void drawEffect(Graphics2D g,Image effect){
		
		if(singleTarget!=null){
			g.drawImage(effect, singleTarget.getX(), 
					singleTarget.getY(), tileWidth,
				tileHeight,null);
		}
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);

	
	
}
