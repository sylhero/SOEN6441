package entity;

import java.awt.Point;
import java.util.LinkedList;

import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class NormalCritter extends CritterBase {

	public NormalCritter(LinkedList<Point> correctRoute, int movePoint)
	{
		this.tileMap = TileMap.getTileMap();
		this.map = tileMap.getMap();
		this.correctRoute = correctRoute;
		this.correctRouteCopy = super.copyCorrectRoute();
		
		//start position
		Point startPoint = correctRoute.pollFirst();
		
		//initial position
		this.x = startPoint.y * tileMap.getCellWidth();
		this.y = tileMap.getUpperOffSet() + startPoint.x * tileMap.getCellHeight();
		
		//speed offset slow:1 normal:2 fast:4
		this.speedOffsetX = 1;
		this.speedOffsetY = 1;
		
		//set next point
		this.nextPoint = correctRouteCopy.pollFirst();
		this.originalHp = 5000;
		this.currentHp  = 5000;
		this.image = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
		
		this.value = 100;
		this.armorRatio = 0.3f;
		
		//keep some space between multiple critters
		this.movePoint = movePoint;
		
		//set if reached the exit		
		this.atExit = false;
		
	}

}
