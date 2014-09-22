package tilemap;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	private boolean isTowerSelected;
	//game path defined by the SelectMapState
	
	//image for menu background
	
	public static final Image menuBackground = LoadImage.loadImage("/images/submenubackground.png");
	
	//images for the map
	public static final Image grass       = LoadImage.loadImage("/images/grass.png");
	public static final Image pavement    = LoadImage.loadImage("/images/pavement.png");
	public static final Image entrance    = LoadImage.loadImage("/images/entrance.png");
	public static final Image exit        = LoadImage.loadImage("/images/destination.png");	
	//towers
	public static final Image arrowTower  = LoadImage.loadImage("/images/arrowtower.png");
	public static final Image cannonTower = LoadImage.loadImage("/images/cannontower.png");
	public static final Image iceTower    = LoadImage.loadImage("/images/icetower.png");
	public static final Image magicTower  = LoadImage.loadImage("/images/magictower.png");
			
	//monster
	public static final Image monster1    = LoadImage.loadImageIcon("/images/monster1.gif").getImage();
	public static final Image monster2    = LoadImage.loadImageIcon("/images/monster2.gif").getImage();
	
	//coins 
	public static final Image coin        = LoadImage.loadImage("/images/coins.png");
		
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
		
	public TileMap(){
	offSetX     = 0;
	offSetY     = 100;
	upperOffSet = 100;
	lowerOffSet = 100;
	this.isTowerSelected = true;
	}
	//Load map
	public Tile[][] loadMap(String path){
		
			BufferedReader br = null;
			try {
				String line;
				int rowTemp = 0;
				br = new BufferedReader(new FileReader(path));
				mapRow = Integer.parseInt(br.readLine().trim());
				mapCol = Integer.parseInt(br.readLine().trim());
				cellWidth  = GamePanel.WIDTH / mapCol;
				//top 100 for menu below 100 for buttons
				cellHeight = (GamePanel.HEIGHT - upperOffSet - lowerOffSet)/ mapRow;
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
					
		
		return map;
		
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
	
	private void drawTopMenu(Graphics2D g){
		
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, GamePanel.WIDTH-2, upperOffSet-2);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, GamePanel.WIDTH-1, upperOffSet-2);
		g.drawImage(arrowTower, 1, 5, 40, 40, null);
		g.drawImage(cannonTower, 1,48,40,40,null);
		g.drawImage(iceTower, 42, 5 ,40,40,null);
		g.drawImage(magicTower, 42, 48, 40,40,null);
		g.drawImage(coin, 700, 0, 30, 30, null);
		drawTime(g);
		
	}
	
	//draw time
	private void drawTime(Graphics2D g){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String time = (dateFormat.format(date)).toString();
		//System.out.println(time);
		g.setColor(Color.RED);
		g.drawString(time,550,50); //2014/08/06 15:59:48
	}
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
	//draw bottom menu
	private void drawBottomMenu(Graphics2D g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 500, 400, 100);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.setColor(Color.RED);
		String firstLine = "To be continued...";
		g.drawString(firstLine, 10, 550);
		g.setColor(Color.MAGENTA);
		g.fillRect(450, 500, 80, 40);
		g.setColor(Color.YELLOW);
		String pause = "Pause";
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString(pause, 455, 520);
		
			
	}
	//clear the screen
	private void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
	    g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	public void draw(Graphics2D g) {
		//clear screen
		clearScreen(g); 
		//draw top menu
		drawTopMenu(g);
		//draw map
		drawMap(g);	
		//draw bottom menu
		drawBottomMenu(g);

		
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
	        //this should be in tower.class
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
