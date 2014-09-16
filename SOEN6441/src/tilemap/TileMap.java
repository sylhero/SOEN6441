package tilemap;

import gameState.SelectMapState;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import usefulfunctions.LoadImage;
/*
 * the entire map consists three parts
 * towers, description, money
 * map
 * console buttons(start, save/load)
 * 
 * 
 */
public class TileMap implements MouseMotionListener,MouseListener{
	private Tile[][] map;
	private int mapRow;
	private int mapCol;
	private int cellWidth;
	private int cellHeight;
	private int offSetX;
	private int offSetY;
	private int upperOffSet;
	private int lowerOffSet;
	private Point selectedTile;
	//images for the map
	public static Image grass       = LoadImage.loadImage("/images/grass.png");
	public static Image pavement    = LoadImage.loadImage("/images/pavement.png");
	public static Image entrance    = LoadImage.loadImage("/images/entrance.png");
	public static Image exit        = LoadImage.loadImage("/images/destination.png");	
	//towers
	public static Image arrowTower  = LoadImage.loadImage("/images/arrowtower.png");
	public static Image cannonTower = LoadImage.loadImage("/images/cannontower.png");
	public static Image iceTower    = LoadImage.loadImage("/images/icetower.png");
	public static Image magicTower  = LoadImage.loadImage("/images/magictower.png");
			
	//monster
	public static Image monster1    = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
	public static Image monster2    = LoadImage.loadImageIcon("/images/monster2.gif").getImage();
		
		
	//numbers 
	public static final int GRASS       = 0;
	public static final int ENTRANCE    = 1;
	public static final int PAVEMENT    = 2;
	public static final int EXIT        = 3;
	public static final int ARROWTOWER  = 4;
	public static final int CANNONTOWER = 5;
	public static final int ICETOWER    = 6;
	public static final int MAGICTOWER  = 7;
	public static final int MONSTER1    = 8;
	public static final int MONSTER2    = 9;
		
	public TileMap(String path){
	loadMap(path);	
	offSetX     = 0;
	offSetY     = 100;
	upperOffSet = 100;
	lowerOffSet = 100;
	cellWidth  = GamePanel.WIDTH / mapCol;
	//top 100 for menu below 100 for buttons
	cellHeight = (GamePanel.HEIGHT - upperOffSet - lowerOffSet)/ mapRow;
	}
	//Load map
	private void loadMap(String path){
		BufferedReader br = null;
		try {
			String line;
			int rowTemp = 0;
			br = new BufferedReader(new FileReader(path));
			mapRow = Integer.parseInt(br.readLine().trim());
			mapCol = Integer.parseInt(br.readLine().trim());
			map = new Tile[mapRow][mapCol];
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				String[] temp = line.split(" ");
				for(int i = 0; i< temp.length; i++){
					map[rowTemp][i] = new Tile(Integer.parseInt(temp[i]));	
				}
				rowTemp++;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 
	}
	public Tile[][] getMap(){
		return this.map;
	}
	//return row
	public int getRow(){
		return this.mapRow;
	}
	//return col
	public int getCol(){
		return this.mapCol;
	}
	
	public int getMapRow() {
		return mapRow;
	}
	
	public int getMapCol() {
		return mapCol;
	}
	
	public int getCellWidth() {
		return cellWidth;
	}
	
	public int getCellHeight() {
		return cellHeight;
	}
	
	public int getOffSetX() {
		return offSetX;
	}
	
	public int getOffSetY() {
		return offSetY;
	}
	
	public Point getSelectedTile() {
		return selectedTile;
	}
	
	
	public void update() {
//		
//		if(selectedTile != null){
//			map[selectedTile.y][selectedTile.x].setTileType(PAVEMENT);
//		}
		
	}
	//draw menu
	

	private void drawMap(Graphics2D g){
		for(int i = 0; i<mapRow; i++){
			for(int j = 0;j<mapCol; j++){
				//identify the tile type
				switch(map[i][j].getTileType()){
				
				case GRASS: 
					
					map[i][j].setTheRest(grass, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case ENTRANCE:
					map[i][j].setTheRest(entrance, offSetX + j * cellWidth,
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
					
					map[i][j].setTheRest(pavement, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case EXIT:
					
					map[i][j].setTheRest(exit, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					
					//same as the entrance draw background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case ARROWTOWER:
					
					map[i][j].setTheRest(arrowTower, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
					
				case ICETOWER:
					
					map[i][j].setTheRest(iceTower, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				case MAGICTOWER:
					
					map[i][j].setTheRest(magicTower, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
					//draw the background
					g.drawImage(grass,map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null);
					break;
				
				case CANNONTOWER:
					
					map[i][j].setTheRest(cannonTower, offSetX + j * cellWidth,
							offSetY + i * cellHeight, cellWidth, cellHeight);
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
	//clear the screen
	public void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
	    g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	public void draw(Graphics2D g) {
		//clear screen
		clearScreen(g); 
		//draw map
		drawMap(g);	

		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		int tempX = e.getX();
		int tempY = e.getY();
		
		if(tempY >= upperOffSet && tempY <= GamePanel.HEIGHT - lowerOffSet){
			int  column = tempX / cellWidth;
			int temp = tempY - upperOffSet;
	        int row =  temp / cellHeight;
	        System.out.println("this is x: "+row);
	        System.out.println("this is y: "+column);
	        selectedTile = new Point(column, row);
	        if(selectedTile != null &&
	        		map[selectedTile.y][selectedTile.x].getTileType() == GRASS){
				map[selectedTile.y][selectedTile.x].setTileType(MAGICTOWER);
			}
			
		}
		
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
