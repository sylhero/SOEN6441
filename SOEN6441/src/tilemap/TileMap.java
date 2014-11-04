package tilemap;
import entity.ArrowTower;
import entity.CannonTower;
import entity.IceTower;
import entity.MagicTower;
import entity.TowerBase;
import entity.TowerFactory;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import currency.Coin;
import usefulfunctions.LoadImage;
import xml.MapParser;
/**
 * the entire map consists three parts
 * towers, description, money
 * map
 * console buttons(start, save/load)
 * singleton design pattern
 * @author yulongsong
 */
public class TileMap implements MouseMotionListener,MouseListener{
	
	//the map
	private Tile[][] map;
	private int mapRow;
	private int mapCol;
	private int cellWidth;
	private int cellHeight;
	
	//correct path
	
	private LinkedList<Point> correctPath;
	//start point
	private int offSetX;
	private int offSetY;
	//padding
	private int upperOffSet;
	private int lowerOffSet;
	
	//map parser
	private MapParser mapParser;
	
	//singleton map object
	private static TileMap tileMap = new TileMap();
	
	
	//image for menu background
	
	public static final Image menuBackground = LoadImage.loadImage("/images/submenubackground.png");
	
	//images for the map
	public static final Image grass       = LoadImage.loadImage("/images/grass.png");
	public static final Image pavement    = LoadImage.loadImage("/images/pavement.png");
	public static final Image entrance    = LoadImage.loadImage("/images/entrance.png");
	public static final Image exit        = LoadImage.loadImage("/images/destination.png");	
	
	//types 
	public static final int GRASS       = 0;
	public static final int ENTRANCE    = 1;
	public static final int PAVEMENT    = 2;
	public static final int EXIT        = 3;

	/**
	 * private constructor	
	 */
	private TileMap(){
	offSetX     = 0;
	offSetY     = 100;
	upperOffSet = 100;
	lowerOffSet = 100;
	mapParser   = new MapParser();
	}
	/**
	 * load map
	 * @param path
	 * @return map
	 */
	public Tile[][] loadMap(String path){
		mapParser.loadXMLFile(path);
		map = mapParser.getMapData(); 
		mapRow = map.length;
		mapCol = map[0].length;
		cellWidth  = GamePanel.WIDTH / mapCol;
        //top 100 for menu below 100 for buttons
		cellHeight = (GamePanel.HEIGHT - upperOffSet - lowerOffSet)/ mapRow;		
		return map;
		
	}
	/**
	 * set the correct path
	 * @param path
	 */
	public void setCorrectPath(LinkedList<Point> path){
		this.correctPath = path;
	}
	/**
	 * get correct path
	 * @return correct path
	 */
	public LinkedList<Point> getCorrectPath(){
		return correctPath;
	}
	/**
	 * get map
	 * @return
	 */
	public Tile[][] getMap(){
		return this.map;
	}
	/**
	 * get map row
	 * @return
	 */
	public int getRow(){
		return this.mapRow;
	}
	/**
	 * get map column
	 * @return
	 */
	public int getCol(){
		return this.mapCol;
	}
	/**
	 * get map row
	 * @return
	 */
	public int getMapRow() {
		return mapRow;
	}
	/**
	 * get map column
	 * @return
	 */
	public int getMapCol() {
		return mapCol;
	}
	/**
	 * get cell width
	 * @return
	 */
	public int getCellWidth() {
		return cellWidth;
	}
	/**
	 * get cell height
	 * @return
	 */
	public int getCellHeight() {
		return cellHeight;
	}
	/**
	 * get offset x
	 * @return
	 */
	public int getOffSetX() {
		return offSetX;
	}
	/**
	 * get offset y
	 * @return
	 */
	public int getOffSetY() {
		return offSetY;
	}
	
	/**
	 * get the tile map object
	 * @return
	 */
	public static TileMap getTileMap(){
		return tileMap;
	}
	
	
	public void update() {

		
	}
	
	
	
	//draw time
//	private void drawTime(Graphics2D g){
//		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//		Date date = new Date();
//		String time = (dateFormat.format(date)).toString();
//		//System.out.println(time);
//		g.setColor(Color.RED);
//		g.drawString(time,550,50); //2014/08/06 15:59:48
//	}
	/**
	 * draw the map
	 * @param g
	 */
	private void drawMap(Graphics2D g){
		for(int i = 0; i<mapRow; i++){
			for(int j = 0;j<mapCol; j++){
				//identify the tile type
				switch(map[i][j].getTileType()){
				
				case GRASS: 
					
					map[i][j] = new Tile(GRASS,grass, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case ENTRANCE:
					map[i][j] = new Tile(ENTRANCE,entrance, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					//draw the entrance
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
					
				case PAVEMENT:
					
					map[i][j] = new Tile(PAVEMENT,pavement, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case EXIT:
					
					map[i][j] = new Tile(EXIT,exit, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					
					//same as the entrance draw background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case ArrowTower.ARROWTOWERTYPE:
					
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
					
				case IceTower.ICETOWERTYPE:
					
					
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
					
				case MagicTower.MAGICTOWERTYPE:
					
					
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case CannonTower.CANNONTOWERTYPE:
					
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				}
					
				
					
			}
			
		}
	}
	/**
	 * get upper offset
	 * @return
	 */
	public int getUpperOffSet() {
		return upperOffSet;
	}
	/**
	 * set upper offset
	 * @param upperOffSet
	 */
	public void setUpperOffSet(int upperOffSet) {
		this.upperOffSet = upperOffSet;
	}
	/**
	 * get lower off set
	 * @return
	 */
	public int getLowerOffSet() {
		return lowerOffSet;
	}
	/**
	 * set lower off set
	 * @param lowerOffSet
	 */
	public void setLowerOffSet(int lowerOffSet) {
		this.lowerOffSet = lowerOffSet;
	}
	
	/**
	 * clear the screen
	 * @param g
	 */
	private void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
	    g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	/**
	 * draw
	 * @param g
	 */
	public void draw(Graphics2D g) {
		//clear screen
		clearScreen(g); 
		//draw time
		//drawTime(g);
		//draw map
		drawMap(g);	
		
		
	}
	/**
	 * mouse click event
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse press event
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {	
		
	}
	/**
	 * mouse release event
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse enter event
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse exit event
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse drag event
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse move event
	 * @param e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
