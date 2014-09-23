package entity;

import java.awt.Image;

import tilemap.Tile;
import usefulfunctions.LoadImage;
/**
 * 
 * @author xunrong xia
 * this is the abstract class for all the towers
 * 
 */
public abstract class TowerBase extends Tile implements Entity{
	protected int level;
	protected int cost;
	protected double refundRate; //0-1 it is a percentage
	protected int range; // radius
	protected int power; 
	protected int towerSpeed;
	protected boolean groupAttack;
	protected int upgradeCost;
	protected Tile[][] map;
	
	
	public void UpgradeLevel(){
		level += 1;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public double getRefundRate() {
		return refundRate;
	}


	public void setRefundRate(double refundRate) {
		this.refundRate = refundRate;
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	public int getPower() {
		return power;
	}


	public void setPower(int power) {
		this.power = power;
	}


	public int getTowerSpeed() {
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


	public int getUpgradeCost() {
		return upgradeCost;
	}


	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	
}
