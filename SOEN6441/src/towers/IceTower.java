package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import critters.CritterBase;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Ice Tower. 
 * The Ice Tower has its own image and type and other common attributes.
 * It will be used in the later builds 
 * 
 * @author Yulong Song, Xunrong Xia
 * 
 */
public class IceTower extends TowerBase {
	public static final Image iceTower  = LoadImage.loadImage("/images/icetower.png");
	public static final int ICETOWERTYPE  = 6;
	
	/**
	 * This is the constructor of IceTower class. 
	 * Some default values of the Ice Tower's features has been assigned.
	 */
	
	public IceTower(){
		super.name = "Ice Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ICETOWERTYPE;
		super.tileImage = iceTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = TileMap.getTileMap().getCellWidth();
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
		super.value = level * upgradeCost + cost;
		super.specialEffect = "Freezing";
		super.targets = new ArrayList<CritterBase>();
		super.singleTarget = null;
	}
	
	/**
	 * This constructor would assign the value of tile's attributes.
	 * 
	 * @param tileX The X coordinate of the tile.
	 * @param tileY The Y coordinate of the tile.
	 * @param tileWidth The width of the tile.
	 * @param tileHeight The height of the tile.
	 */
	
	public IceTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		super.name = "Ice Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ICETOWERTYPE;
		super.tileImage = iceTower;
		super.tileX = tileX;
		super.tileY = tileY;
		super.tileHeight = tileHeight;
		super.tileWidth = tileWidth;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = tileWidth;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;
		super.value = level * upgradeCost + cost;
		super.specialEffect = "Freezing";
		super.targets = new ArrayList<CritterBase>();
		super.singleTarget = null;
	}
	
	/**
	 * The override method of upgrade().
	 * When an Ice Tower upgrade, it's power would increase 5,
	 *  level would increase 1. The upgradeCose would increase 5.
	 */
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fire(CritterBase critter) {
		// TODO Auto-generated method stub
		int critterX = critter.getX();
		int critterY = critter.getY();
		int critterCenterX = critterX + TileMap.getTileMap().getCellHeight() / 2;
		int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;
		int towerCenterX   = tileX + TileMap.getTileMap().getCellHeight() / 2;
		int towerCenterY   = tileY + TileMap.getTileMap().getCellWidth() / 2;
		double distance = Math.sqrt(Math.pow(critterCenterX-towerCenterX, 2) + 
				Math.pow(critterCenterY-towerCenterY, 2));
		
		if(distance <= range)
		{
			if(this.groupAttack==false && super.singleTarget == null)
			{
				super.singleTarget = critter;
			}

			if(singleTarget!=null)
			{
				singleTarget.decreaseHp(this.power);
				
				singleTarget.setSpeedOffset(1, 1);
				if(singleTarget.getCurrentHp()<=0)
				{
					coin.increaseCurrency(singleTarget.getValue());
					//targets.remove(singleTarget);
					singleTarget = null;
				}
				
			}	
		}
		else
		{
			singleTarget = null;
		}	
	}
}
