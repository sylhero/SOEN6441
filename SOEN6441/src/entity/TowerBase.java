package entity;

import tilemap.Tile;

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
	
	
}
