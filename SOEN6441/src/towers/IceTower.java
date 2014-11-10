package towers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import critters.CritterBase;
import tilemap.TileMap;
import towerstrategy.FarthestStrategy;
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

public class IceTower extends TowerBase {

	public static final Image iceTower = LoadImage
			.loadImage("/images/icetower.png");

	public static final int ICETOWERTYPE = 6;

	/**
	 * 
	 * This is the constructor of IceTower class.
	 * 
	 * Some default values of the Ice Tower's features has been assigned.
	 */

	public IceTower() {

		this.name = "Ice Tower";

		this.map = TileMap.getTileMap().getMap();

		this.tileType = ICETOWERTYPE;

		this.tileImage = iceTower;

		this.level = 0;

		this.cost = 15;

		this.groupAttack = false;

		this.power = 10;

		this.range = 2 * TileMap.getTileMap().getCellWidth();

		this.refundRate = 0.5;

		this.towerSpeed = 3;

		this.upgradeCost = 10;

		this.value = level * upgradeCost + cost;

		this.specialEffect = "Freezing";

		this.targets = new ArrayList<CritterBase>();

		this.singleTarget = null;

		this.towerStratgyType = 0;

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

		this.name = "Ice Tower";

		this.map = TileMap.getTileMap().getMap();

		this.tileType = ICETOWERTYPE;

		this.tileImage = iceTower;

		this.tileX = tileX;

		this.tileY = tileY;

		this.tileHeight = tileHeight;

		this.tileWidth = tileWidth;

		this.level = 0;

		this.cost = 15;

		this.groupAttack = false;

		this.power = 10;

		this.range = tileWidth;

		this.refundRate = 0.5;

		this.towerSpeed = 3;

		this.upgradeCost = 10;

		this.value = level * upgradeCost + cost;

		this.specialEffect = "Freezing";

		this.targets = new ArrayList<CritterBase>();

		this.singleTarget = null;

		this.towerStratgyType = 0;

	}

	/**
	 * 
	 * The override method of upgrade().
	 * 
	 * When an Ice Tower upgrade, it's power would increase 5,
	 * 
	 * level would increase 1. The upgradeCose would increase 5.
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

		double distance = distance(critter);

		if (distance <= range)

		{

			if (!targets.contains(critter) && critter.getCurrentHp() > 0)

			{

				targets.add(critter);

			}

			TowerStrategy strategy = setStrategy();
			if (strategy == null && this.groupAttack == false
					&& this.singleTarget == null) {
				this.singleTarget = critter;
			} else if (strategy != null && this.groupAttack == false
					&& this.singleTarget == null) {

				this.singleTarget = strategy.executeStrategy(targets, this);

				// System.out.println(this.singleTarget==null);

			}

			singleTarget.setIsFrozen(true);

			singleTarget.decreaseHp(this.power);

			singleTarget.setSpeedOffset(1, 1);

			if (singleTarget.getCurrentHp() <= 0)

			{

				coin.increaseCurrency(singleTarget.getValue());

				targets.remove(singleTarget);

				singleTarget = null;

			}

		}

		else

		{

			if (targets.contains(critter)) {
				targets.remove(critter);

			}

			singleTarget = null;

		}

	}

}
