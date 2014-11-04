package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
/**
 * This is the class of creating Monster.
 * This class only define the basic movement of the monster.
 * The monster will have more properties in later builds.
 * 
 * @author Kun Wang
 * 
 */
public class MonsterTest {
	private TileMap tileMap;
	private Tile[][] map;
	private LinkedList<Point> correctPath;
	private LinkedList<Point> correctPathCopy;
	private Image image;
	private int HP;
	private int x;
	private int y;
	//monster status
	private boolean isAlive;
	//next step
	private Point nextPoint;
	
	//direction
	public static final int LEFT  = 0;
	public static final int RIGHT = 1;
	public static final int UP    = 2;
	public static final int DOWN  = 3;
	
	public MonsterTest(LinkedList<Point> correctPath){
		tileMap              = TileMap.getTileMap();
		this.map             = tileMap.getMap();
		this.correctPath     = correctPath;
		this.correctPathCopy = copyCorrectPath();
		//start position
		Point startPoint     = correctPathCopy.pollFirst();
		this.x               = startPoint.y*tileMap.getCellWidth();
		this.y               = tileMap.getUpperOffSet()+startPoint.x*tileMap.getCellHeight();
		//set next point
		this.nextPoint       = correctPathCopy.pollFirst();
		this.HP              = 500;
		this.image           = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
		
		isAlive 	         = true;
		
	}
	
	private  LinkedList<Point> copyCorrectPath(){
		LinkedList<Point> temp = new LinkedList<Point>();
		for(int i=0;i<correctPath.size();i++){
			temp.add(correctPath.get(i));
		}
		return temp;
		
	}
	private void move(){
		double column = (double)x / tileMap.getCellWidth();
		double temp = (double)y - tileMap.getUpperOffSet();
        double row =  temp / tileMap.getCellHeight();
        int mapRow = map.length;
		int mapCol = map[0].length;
		if(row < mapRow && column < mapCol && nextPoint!=null && isAlive){
			if(row < nextPoint.x){
				y++;
			}
			
			if(column < nextPoint.y){
				x++;
			}
			if(row > nextPoint.x){
				y--;
			}
			if(column > nextPoint.y){
				x--;
			}
			if(row == nextPoint.x && column == nextPoint.y){
				nextPoint = correctPathCopy.pollFirst();
			}
			
		}
		//System.out.println()
//		System.out.printf("this is x: %d\n",x);
//		System.out.printf("this is y: %d\n",y);
//		System.out.printf("this is the row %d\n",row);
//		System.out.printf("this is the column %d\n", column);
//		System.out.printf("this is the next point %d, %d\n",nextPoint.x,nextPoint.y);
//       
		
	}
	
	
	public void update(){
		move();
		//System.out.println(HP);
	}
	
	public void draw(Graphics2D g){
		if(HP>0){
			g.drawImage(image, x, 
					y, 
					tileMap.getCellWidth(),
					tileMap.getCellHeight(),null);
		}
		
	}

}
