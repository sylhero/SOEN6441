package critters;

import java.awt.Point;
import java.util.LinkedList;

import tilemap.TileMap;
import usefulfunctions.LoadImage;

/**
 * Heavily armed critters which is a sub-class of CritterBase.
 * 
 * @author Yichen LI
 * @version 1.2.0
 *
 */
public class HeavilyArmedCritter extends CritterBase{

	
	/**
	 * Constructor
	 */
	public HeavilyArmedCritter()
	{
		this.tileMap = TileMap.getTileMap();
		this.map = tileMap.getMap();
		this.name = "critter"+NAMENUMBER++;
		//this.correctRoute = correctRoute;
		//this.correctRouteCopy = super.copyCorrectRoute();
		
		//start position
//		this.startPoint = correctRouteCopy.pollFirst();
		
		//initial position
//		this.x = startPoint.y * tileMap.getCellWidth();
//		this.y = tileMap.getUpperOffSet() + startPoint.x * tileMap.getCellHeight();
		
		//speed offset slow:1 normal:2 fast:4
		this.speedOffsetX = 2;
		this.speedOffsetY = 2;
		
		//set next point
//		this.nextPoint = correctRouteCopy.pollFirst();
		this.originalHp = 8000;
		this.currentHp  = 8000;
		this.image = LoadImage.loadImageIcon("/images/monster2.gif").getImage();
		
		this.value = 200;
		this.armorRatio = 0.8f;
		
		//keep some space between multiple critters
		//this.movePoint = movePoint;
		
		//set if reached the exit		
		this.atExit = false;
		
		//set status
		this.isFreezing = false;
		this.isBurning = false;
		this.isSplash = false;
		
		
		
		
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param correctRoute This is the correct route.
	 * @param movePoint to pass some space between critters.
	 */
	public HeavilyArmedCritter(LinkedList<Point> correctRoute, int movePoint)
	{
		this.tileMap = TileMap.getTileMap();
		this.map = tileMap.getMap();
		this.name = "critter"+NAMENUMBER++;
//		this.correctRoute = correctRoute;
//		this.correctRouteCopy = super.copyCorrectRoute();
		
		//start position
		this.startPoint = correctRouteCopy.pollFirst();
		
		//initial position
		this.x = startPoint.y * tileMap.getCellWidth();
		this.y = tileMap.getUpperOffSet() + startPoint.x * tileMap.getCellHeight();
		
		//speed offset slow:1 normal:2 fast:4
		this.speedOffsetX = 2;
		this.speedOffsetY = 2;
		
		//set next point
		this.nextPoint = correctRouteCopy.pollFirst();
		this.originalHp = 8000;
		this.currentHp  = 8000;
		this.image = LoadImage.loadImageIcon("/images/monster2.gif").getImage();
		
		this.value = 200;
		this.armorRatio = 0.8f;
		
		//keep some space between multiple critters
		this.movePoint = movePoint;
		
		//set if reached the exit		
		this.atExit = false;
		
		//set status
		this.isFreezing = false;
		this.isBurning = false;
		this.isSplash = false;
		
		
		
		
	}

}
