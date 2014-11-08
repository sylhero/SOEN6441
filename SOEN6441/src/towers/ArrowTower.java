package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import critters.CritterBase;
import currency.Coin;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is one kind of tower named Arrow Tower. 
 * The arrow tower has its own image and type and other common attributes.
 * 
 * @author Yulong Song, Xunrong Xia
 */
public class ArrowTower extends TowerBase{
	public static final Image arrowTower         = LoadImage.loadImage("/images/arrowtower.png");
	public static final Image arrowTowerEffect   = LoadImage.loadImageIcon("/images/arrowtowereffect.gif").getImage();
	public static final int ARROWTOWERTYPE  = 4;
	
	/**
	 * This is the constructor with no parameter, assign the initial value of the attributes.
	 */
	
	public ArrowTower(){
		super.name = "Arrow Tower";
		super.map = TileMap.getTileMap().getMap();
		super.tileType  = ARROWTOWERTYPE;
		super.tileImage = arrowTower;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 2*TileMap.getTileMap().getCellWidth();
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;	
		super.value = this.cost;
		super.specialEffect = "None";
		super.targets = new ArrayList<CritterBase>();
		super.singleTarget = null;
	}
	
	/**
	 * This constructor can assign the value of tile's attributes besides the tower's common attributes.
	 * 
	 * @param tileX The X coordinate of the tile
	 * @param tileY The Y coordinate of the tile
	 * @param tileWidth The width of the tile
	 * @param tileHeight The height of the tile
	 */
	
	public ArrowTower(int tileX, int tileY, 
			int tileWidth, int tileHeight){
		super.name = "Arrow Tower";
		super.tileType  = ARROWTOWERTYPE;
		super.tileImage = arrowTower;
		super.tileX = tileX;
		super.tileY = tileY;
		super.tileHeight = tileHeight;
		super.tileWidth = tileWidth;
		super.level = 0;
		super.cost  = 15;
		super.groupAttack = false;
		super.power = 10;
		super.range = 2*tileWidth;
		super.refundRate = 0.5;
		super.towerSpeed = 3;
		super.upgradeCost = 10;
		super.value = this.cost;
		super.specialEffect = "None";
		super.targets = new ArrayList<CritterBase>();
		super.singleTarget = null;
		
	}
	
	/**
	 * When a tower be upgraded, its power, level and upgradeCose would be increased. 
	 * The tower's value would be changed too.
	 */
	@Override
	public void upgrade() {
		this.power += 5;
		this.level += 1;
		this.upgradeCost += 5;
		this.value = level * upgradeCost + cost;
		
	}

	
	@Override
	public void fire(CritterBase critter) {
			
			int critterX   = critter.getX();
			int critterY   = critter.getY();
			int critterCenterX = critterX + TileMap.getTileMap().getCellHeight() / 2;
			int critterCenterY = critterY + TileMap.getTileMap().getCellWidth() / 2;
			int towerCenterX   = tileX + TileMap.getTileMap().getCellHeight() / 2;
			int towerCenterY   = tileY + TileMap.getTileMap().getCellWidth() / 2;
			double distance = Math.sqrt(Math.pow(critterCenterX-towerCenterX, 2) + 
					Math.pow(critterCenterY-towerCenterY, 2));
			//System.out.println(distance);
			if(distance <= range){
				
				if(!targets.contains(critter) && critter.getCurrentHp() > 0)
					targets.add(critter);
				
//				if(!targets.contains(monster)&& monster.getCurrentHP()>0){
//					targets.add(monster);
//				}
				System.out.printf("target size:%d\n",targets.size());
				if(this.groupAttack==false && super.singleTarget == null){
					super.singleTarget = critter;
				}
//				if(this.groupAttack==false && targets.size()>0){
//					super.singleTarget = targets.get(0);
//				}
				
				
				if(singleTarget!=null){
					
					singleTarget.decreaseHp(this.power);
					
//					int targetHP = singleTarget.getCurrentHp();
//					singleTarget.setCurrentHp(targetHP-this.power);
					if(singleTarget.getCurrentHp()<=0){
						coin.increaseCurrency(singleTarget.getValue());
						//targets.remove(singleTarget);
						singleTarget = null;
					}
					
				}
				
				
			}else{
				singleTarget = null;
			}
		
			
		}
		
		
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	private void drawEffect(Graphics2D g){
		if(singleTarget!=null){
			System.out.printf("this is draw: %d\n",singleTarget.getX());
			g.drawImage(arrowTowerEffect, singleTarget.getX(), 
					singleTarget.getY(), TileMap.getTileMap().getCellWidth(),
					TileMap.getTileMap().getCellHeight(),null);
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		drawEffect(g);
		
	}
}
