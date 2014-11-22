package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import log.CollectiveLog;
import log.GlobalLog;
import log.WaveLog;
import critters.CritterBase;
import tilemap.TileMap;
import towerstrategy.NearestToExitStrategy;
import towerstrategy.NearestStrategy;
import towerstrategy.StrongestStrategy;
import towerstrategy.TowerStrategy;
import towerstrategy.WeakestStrategy;
import usefulfunctions.LoadImage;

/**
 * 
 * This is one kind of tower named Ice Tower.
 * 
 * The Ice Tower has its own image and type and other common attributes.
 * 
 * It will be used in the later builds
 * 
 * 
 * 
 * @author Yulong Song, Xunrong Xia
 * 
 * 
 */

public class IceTower extends TowerBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441784156335381889L;
	
	public transient static final Image iceTower = LoadImage
			.loadImage("/images/icetower.png");
	public transient static final Image iceTowerEffect   = LoadImage.
			loadImageIcon("/images/icetowereffect.gif").getImage();
	public static final int ICETOWERTYPE = 6;
	public static int NAMENUMBER = 0;

	/**
	 * 
	 * This is the constructor of IceTower class.
	 * 
	 * Some default values of the Ice Tower's features has been assigned.
	 */

	public IceTower() {

		this.name = "Ice Tower"+NAMENUMBER++;

		this.map = TileMap.getTileMap().getMap();

		this.tileType = ICETOWERTYPE;

		this.tileImage = iceTower;

		this.level = 0;

		this.cost = 200;


		this.groupAttack = false;

		this.power = 10;

		this.range = 2 * TileMap.getTileMap().getCellWidth();

		this.refundRate = 0.3;

		this.towerSpeed = 2;

		this.upgradeCost = 20;
		
		this.value = this.cost;
		//this.value = level * upgradeCost + cost;

		

		this.specialEffect = "freeze";

		this.targets = new ArrayList<CritterBase>();

		this.singleTarget = null;

		this.towerStratgyType = 0;
		
		this.individualTowerLog = new ArrayList<String>();

	}

	/**
	 * 
	 * This constructor would assign the value of tile's attributes.
	 * 
	 * 
	 * 
	 * @param tileX
	 *            The X coordinate of the tile.
	 * 
	 * @param tileY
	 *            The Y coordinate of the tile.
	 * 
	 * @param tileWidth
	 *            The width of the tile.
	 * 
	 * @param tileHeight
	 *            The height of the tile.
	 */

	public IceTower(int tileX, int tileY,

	int tileWidth, int tileHeight) {

		this.name = "Ice Tower"+NAMENUMBER++;

		this.map = TileMap.getTileMap().getMap();

		this.tileType = ICETOWERTYPE;

		this.tileImage = iceTower;

		this.tileX = tileX;

		this.tileY = tileY;

		this.tileHeight = tileHeight;

		this.tileWidth = tileWidth;

		this.level = 0;


		//this.cost = 30;

		this.cost = 200;


		this.groupAttack = false;

		this.power = 10;

		this.range = 2*tileWidth;

		this.refundRate = 0.3;


		this.towerSpeed = 2;

		//this.towerSpeed = 3;

		this.upgradeCost = 20;

		this.value = this.cost;
		//this.value = level * upgradeCost + cost;

		this.specialEffect = "Freezing";

		this.targets = new ArrayList<CritterBase>();

		this.singleTarget = null;

		this.towerStratgyType = 0;
		
		this.individualTowerLog = new ArrayList<String>();

	}

	/**
	 * 
	 * The override method of upgrade().
	 * 
	 * When an Ice Tower upgrade, it's power would increase 5,
	 * 
	 * level would increase 1. The upgradeCost would increase 110.
	 */

	@Override
	public void upgrade() {

		this.power += 5;

		this.level += 1;
		
		this.value+=upgradeCost;

		this.upgradeCost += 20;
		
		//this.value = level * upgradeCost + cost;

	}

	@Override
	public void update() {

		// TODO Auto-generated method stub

	}

	/**
	 * To draw special effect on critter if it was attacked by ice tower.
	 */
	@Override
	public void draw(Graphics2D g) {

		drawEffect(g,iceTowerEffect);

	}

	/**
	 * <p>
	 * This is the attack method of tower. It has the similar way as other tower's attack method.
	 * It will attack if a critter should be attacked based on the critter's location, the critter's hp
	 * and the tower's strategy.
	 * <p>
	 * The ice tower would make the critter move slowly, and decrease the critter's hp. 
	 */
	@Override
	public void fire(CritterBase critter) {

		double distance = distance(critter);

		if (distance <= range && critter.getCurrentHp()>0)

		{

			if (!targets.contains(critter) )

			{

				targets.add(critter);

			}

			TowerStrategy strategy = setStrategy();
			if (strategy == null && this.groupAttack == false && singleTarget ==null) {
				this.singleTarget = critter;
			} else if (strategy != null && this.groupAttack == false) {

				this.singleTarget = strategy.executeStrategy(targets, this);

				// System.out.println(this.singleTarget==null);

			}

			singleTarget.setIsFreezing(true);
			this.addIndevidualTowerLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			CollectiveLog.addToAllTowerLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			GlobalLog.addToGlobalLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			WaveLog.addToWaveLog(this.name + " attacks "+singleTarget.getName()+", HP - "+this.power+"\n");
			singleTarget.decreaseHp(this.power);
			this.addIndevidualTowerLog(this.name + " freezes "+singleTarget.getName()+"\n");
			CollectiveLog.addToAllTowerLog(this.name + " freezes "+singleTarget.getName()+"\n");
			GlobalLog.addToGlobalLog(this.name + " freezes "+singleTarget.getName()+"\n");
			WaveLog.addToWaveLog(this.name + " freezes "+singleTarget.getName()+"\n");
			singleTarget.setSpeedOffset(1, 1);

			if (singleTarget.getCurrentHp() <= 0)

			{
				this.addIndevidualTowerLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				CollectiveLog.addToAllTowerLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				GlobalLog.addToGlobalLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				WaveLog.addToWaveLog(this.name + " kills "+singleTarget.getName()+", coin + "+" "+singleTarget.getValue()+"\n");
				coin.increaseCurrency(singleTarget.getValue());

				targets.remove(singleTarget);

				singleTarget = null;

			}

		}

		else
		{

			if (targets.contains(critter)) {
				singleTarget = null;
				targets.remove(critter);

			}

			

		}

	}

	
}
