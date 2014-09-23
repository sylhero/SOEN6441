package entity;

import tilemap.Tile;

public abstract class TowerBase extends Tile implements Entity{
	private int level;
	private double cost;
	private double refund; //0-1 it is a percentage
	private float range; // radius
	private int power; 
	private int towerSpeed;
	private boolean groupAttack;
	private double upgradeCostRate;
	private int towerTpye; // is it same as tile's tpye
	
	public TowerBase(){
		level = 1;
		refund = 0.5;
		groupAttack = false;
		upgradeCostRate = 0.3;
	}
	
	public int UpgradeLevel(){
		int oldLevel = getLevel();
		int newLevel = oldLevel + 1;
		return newLevel;		
	}
	
	public double RefundCost(){
		double refundRate = getRefund();
		double cost = getCost();
		double refCost = cost*refundRate;
		return refCost;		
	} 
	
	public double UpgradeCost(){
		double upRate = getUpgradeCostRate();
		double cost = getCost();
		double upCost = cost*upRate;
		return upCost;		
	}
	
	public void Upgrade(){
		level = UpgradeLevel();
		cost = cost + UpgradeCost();
	}
	
	public double RangeArea(){
		double r = getRange();
		double area = 3.14*r*r;
		return area;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getRefund() {
		return refund;
	}
	public void setRefund(double d) {
		this.refund = d;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getSpeed() {//entity interface
		return towerSpeed;
	}
	public void setTowerSpeed(int towerSpeed) {
		this.towerSpeed = towerSpeed;
	}
	public boolean isGroupAttack() {
		return groupAttack;
	}
	public void setGroupAttack(boolean groupAttack) {
		this.groupAttack = groupAttack;
	}
	public double getUpgradeCostRate() {
		return upgradeCostRate;
	}
	public void setUpgradeCostRate(double upgradeCostRate) {
		this.upgradeCostRate = upgradeCostRate;
	}
	public int getTowerTpye() {
		return towerTpye;
	}
	public void setTowerTpye(int towerTpye) {
		this.towerTpye = towerTpye;
	}

}
