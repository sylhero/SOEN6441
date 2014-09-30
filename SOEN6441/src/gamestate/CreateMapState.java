package gamestate;

import entity.ArrowTower;
import entity.CannonTower;
import entity.IceTower;
import entity.MagicTower;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import tilemap.Tile;
import tilemap.TileMap;

public class CreateMapState extends GameState{
	//map parameters
	private String mapName;
	private int mapRow;
	private int mapColumn;
	private int cellWidth;
	private int cellHeight;
	//map menu parameters
	private int menuWidth;
	private int menuHeight;
	private int buttonWidth;
	private int buttonHeight;
	private boolean isNameGiven;
	private boolean isRowGiven;
	private boolean isColumnGiven;
	private boolean isPavement;
	private boolean isEntrance;
	private boolean isExit;
	private boolean isGrass;
	//font
	private Font font;
	//map
	private Tile[][] map;
	
	
	

	public CreateMapState(GameStateManager gsm){
		this.gsm     = gsm;
		mapName      = null;
		menuWidth    = 100;
		menuHeight   = GamePanel.HEIGHT;
		buttonWidth  = menuWidth;
		buttonHeight = 40;
		map          = null;
		
		font         = new Font("Arial",Font.BOLD,12);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int column = x / cellWidth;
        int row =  y / cellHeight;
        int tileX = map[row][column].getTileX();
        int tileY = map[row][column].getTileY();
        
        System.out.println(column);
        System.out.println(row);
		
		if(isPavement && x >=0 && x <= GamePanel.WIDTH - menuWidth){	
		        
		        map[row][column] = new Tile(TileMap.PAVEMENT,TileMap.pavement, 
		        		tileX,tileY,cellWidth, cellHeight);
		}else if(isGrass && x >=0 && x <= GamePanel.WIDTH - menuWidth){
			map[row][column] = new Tile(TileMap.GRASS,TileMap.grass, 
	        		tileX,tileY,cellWidth, cellHeight);
			
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void setMapButton(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 0 &&
				y <= buttonHeight && !isNameGiven){
			mapName = JOptionPane.showInputDialog("Enter map name: ");
		}
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= buttonHeight + 5 &&
				y <= 2*buttonHeight + 5 && !isRowGiven){
			mapRow = Integer.parseInt(JOptionPane.showInputDialog("Enter map row: "));
		}
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 2*buttonHeight + 10 &&
				y <= 3 * buttonHeight + 10 && !isColumnGiven){
			mapColumn = Integer.parseInt(JOptionPane.showInputDialog("Enter map column: "));
		}
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 3*buttonHeight + 15 &&
				y <= 4 * buttonHeight + 15 ){
			//init
			init(null);
		}
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && 
				y >= 6*buttonHeight + 100 && y <=  
				7*buttonHeight + 110){
			//TODO generate the map
			System.out.println("generate");
			//init
			init(null);
		}
	}
	
	
	private void pavementPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH - menuWidth + buttonWidth/2 -1 
				&& y >= 4*buttonHeight + 20 +18 && y <= 5*buttonHeight +20 +18){
			this.isPavement = true;
			this.isGrass = false;
			this.isEntrance = false;
			this.isExit = false;
		}
		
		//press other buttons will deselect the image
		//setToFalse(e);
	}
	private void grassPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth + buttonWidth/2 -1 && 
				x <= GamePanel.WIDTH 
				&& y >= 4*buttonHeight + 20 +18 && y <= 5*buttonHeight +20 +18){
			this.isGrass = true;
			this.isPavement = false;
			this.isEntrance = false;
			this.isExit = false;
		}
		//setToFalse(e);
		
	}
	private void entrancePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH - menuWidth + buttonWidth/2
				&& y >= 5*buttonHeight + 25 +18 && y <= 6*buttonHeight +25 +18){
			this.isEntrance = true;
			this.isGrass = false;
			this.isPavement = false;
			
			this.isExit = false;
		}
		//setToFalse(e);
	}
	private void exitPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth + buttonWidth/2 && 
				x <= GamePanel.WIDTH 
				&& y >= 5*buttonHeight + 25 +18 && y <= 6*buttonHeight +25 +18){
			this.isExit = true;
			this.isGrass = false;
			this.isPavement = false;
			this.isEntrance = false;
			
		}
		//setToFalse(e);
	}
	private void backPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH 
				&& y >= 7*buttonHeight + 120 && y <= 7*buttonHeight + 180){
			gsm.switchState(GameStateManager.MENUSTATE, null);
		}
			
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
    			
		if(isEntrance && x >=0 && x <= GamePanel.WIDTH - menuWidth){	
			
			int column = x / cellWidth;
	        int row =  y / cellHeight;
	        int tileX = map[row][column].getTileX();
	        int tileY = map[row][column].getTileY();
		        
		        map[row][column] = new Tile(TileMap.ENTRANCE,TileMap.entrance, 
		        		tileX,tileY,cellWidth, cellHeight);
		}else if(isExit && x >=0 && x <= GamePanel.WIDTH - menuWidth){
			int column = x / cellWidth;
	        int row =  y / cellHeight;
	        int tileX = map[row][column].getTileX();
	        int tileY = map[row][column].getTileY();
			map[row][column] = new Tile(TileMap.EXIT,TileMap.exit, 
	        		tileX,tileY,cellWidth, cellHeight);
			
		}
		setMapButton(e);
		pavementPressed(e);
		grassPressed(e);
		entrancePressed(e);
		exitPressed(e);
		backPressed(e);
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
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause(boolean result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(String path) {
		mapName = null;
		mapRow = 0;
		mapColumn = 0;
		map = null;
		this.isNameGiven = false;
		this.isRowGiven = false;
		this.isColumnGiven = false;	
		this.isPavement = false;
		this.isGrass = false;
		this.isEntrance = false;
		this.isExit = false;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	//this method draw the background of the 
	//map area and menu area
	private void drawBackground(Graphics2D g){
		g.setColor(Color.WHITE);
		int mapAreaWidth = GamePanel.WIDTH - menuWidth;
	    g.fillRect(0, 0, mapAreaWidth, GamePanel.HEIGHT);
	    g.setColor(Color.GRAY);
	    g.fillRect(mapAreaWidth, 0, menuWidth, GamePanel.HEIGHT);
	}
	private void drawMenu(Graphics2D g){
		int GameButtonStartX = GamePanel.WIDTH - menuWidth;
		
		if(mapName == null && isNameGiven == false){
			g.setColor(Color.RED);
			
			g.fillRect(GameButtonStartX, 0, menuWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String name = "Set name";
			g.drawString(name, GameButtonStartX+2,18);
		}else{
			g.setColor(Color.GREEN);
			g.fillRect(GameButtonStartX, 0, buttonWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String name = "Name is given";
			g.drawString(name, GameButtonStartX+2, 18);
			isNameGiven = true;
		}
		if(mapRow == 0 && isRowGiven == false){
			
			g.setColor(Color.RED);
			g.fillRect(GameButtonStartX, buttonHeight+5, 
					menuWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String setRow = "Set row";
			g.drawString(setRow, GameButtonStartX+2, buttonHeight+5+18);
		}else{
			
			g.setColor(Color.GREEN);
			g.fillRect(GameButtonStartX, buttonHeight+5 , 
					buttonWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String rowGiven = "Row is given";
			g.drawString(rowGiven, GameButtonStartX+2,
					buttonHeight+5+18);
			isRowGiven = true;
		}
		if(mapColumn == 0 && isColumnGiven == false){
			
			g.setColor(Color.RED);
			g.fillRect(GameButtonStartX, 2*buttonHeight+10, 
					menuWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String setColumn = "Set column";
			g.drawString(setColumn, GameButtonStartX+2, 2*buttonHeight+10+18);
		}else{
			
			g.setColor(Color.GREEN);
			g.fillRect(GameButtonStartX, 2*buttonHeight+10 , 
					buttonWidth, buttonHeight);
			g.setColor(Color.BLACK);
			String columnGiven = "Column is given";
			g.drawString(columnGiven, GameButtonStartX+2, 2*buttonHeight+10+18);
			isColumnGiven = true;
		}
		//draw initializing button
		g.setColor(Color.BLUE);
		g.fillRect(GameButtonStartX, 3*buttonHeight+15 , 
					buttonWidth, buttonHeight);
		g.setColor(Color.ORANGE);
		String initialize = "initialize";
		g.drawString(initialize, GameButtonStartX+2, 3*buttonHeight+15+18);
		
		//draw pavement image
		
		g.drawImage(TileMap.pavement,GameButtonStartX, 
				4*buttonHeight+20+18,buttonWidth/2 -1, buttonHeight,null);
		
		//draw grass image
		g.drawImage(TileMap.grass,GameButtonStartX + buttonWidth/2, 
				4*buttonHeight+20+18,buttonWidth/2 +1, buttonHeight,null);
		
		//draw entrance image
		g.drawImage(TileMap.entrance,GameButtonStartX, 
				5*buttonHeight+25+18,buttonWidth/2 -1, buttonHeight,null);
		
		//draw exit image
		
		g.drawImage(TileMap.exit,GameButtonStartX + buttonWidth/2, 
				5*buttonHeight+25+18,buttonWidth/2 +1, buttonHeight,null);
		
		//draw generate button
		
		g.setColor(Color.MAGENTA);
		g.fillRect(GameButtonStartX, 6*buttonHeight + 100, 
					buttonWidth, buttonHeight+10);
		g.setColor(Color.ORANGE);
		String generate = "Generate";
		g.drawString(generate, GameButtonStartX+5, 6*buttonHeight+120);
		
		//draw back to menu button
		
		g.setColor(Color.BLACK);
		g.fillRect(GameButtonStartX, 7*buttonHeight + 120, 
					buttonWidth, buttonHeight+10);
		g.setColor(Color.ORANGE);
		String backToMenu = "Back";
		g.drawString(backToMenu, GameButtonStartX+5, 7*buttonHeight+140);
		
		    
	}
	private void drawMapArea(Graphics2D g){
		if(map == null && this.isNameGiven && this.isColumnGiven && this.isRowGiven){
			//700 map area 100 menu area
			cellWidth  = (GamePanel.WIDTH - menuWidth)/ mapColumn;
			cellHeight = GamePanel.HEIGHT/ mapRow;
			map = new Tile[mapRow][mapColumn];
			for(int i = 0; i<mapRow; i++){
				for(int j = 0;j<mapColumn; j++){
					map[i][j] = new Tile(TileMap.GRASS,TileMap.grass, j * cellWidth,
							i * cellHeight, cellWidth, cellHeight);
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null); 		
				}
		}
		}
	}
	private void drawMap(Graphics2D g){
		if(map != null){

			for(int i = 0; i<mapRow; i++){
				for(int j = 0;j<mapColumn; j++){
					//identify the tile type
					switch(map[i][j].getTileType()){
					
					case TileMap.GRASS: 
						
						map[i][j] = new Tile(TileMap.GRASS,TileMap.grass, j * cellWidth,
								i * cellHeight, cellWidth, cellHeight);
						g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
								map[i][j].getTileY(),map[i][j].getTileWidth(),
								map[i][j].getTileHeight(), null);
						break;
					
					case TileMap.ENTRANCE:
						map[i][j] = new Tile(TileMap.ENTRANCE,TileMap.entrance,  j * cellWidth,
								 i * cellHeight, cellWidth, cellHeight);
						//draw the background
						g.drawImage(TileMap.grass,map[i][j].getTileX(),
								map[i][j].getTileY(),map[i][j].getTileWidth(),
								map[i][j].getTileHeight(), null);
						//draw the entrance
						g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
								map[i][j].getTileY(),map[i][j].getTileWidth(),
								map[i][j].getTileHeight(), null);
						break;
						
					case TileMap.PAVEMENT:
						
						map[i][j] = new Tile(TileMap.PAVEMENT,TileMap.pavement,  j * cellWidth,
								 i * cellHeight, cellWidth, cellHeight);
						g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
								map[i][j].getTileY(),map[i][j].getTileWidth(),
								map[i][j].getTileHeight(), null);
						break;
					
					case TileMap.EXIT:
						
						map[i][j] = new Tile(TileMap.EXIT,TileMap.exit,  j * cellWidth,
								 i * cellHeight, cellWidth, cellHeight);
						
						//same as the entrance draw background
						g.drawImage(TileMap.grass,map[i][j].getTileX(),
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
		
		
	}
	private void drawEntranceSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth, 
				5*buttonHeight+25+18,buttonWidth/2 +1, buttonHeight);
		
	}
	private void drawExitSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth + buttonWidth/2, 
				5*buttonHeight+25+18,buttonWidth/2 +1, buttonHeight);
		
	}
	private void drawGrassSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth + buttonWidth/2, 
				4*buttonHeight+20+18,buttonWidth/2 +1, buttonHeight);
		
	}
	private void drawPavementSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth,
				4*buttonHeight+20+18,buttonWidth/2 -1, buttonHeight);
		
	}

	@Override
	public void draw(Graphics2D g) {
		//first draw background 
		drawBackground(g);
		//draw menu
		drawMenu(g);
		
		//initialize map area
		drawMapArea(g);

		//draw map
		drawMap(g);
		
		
		if(isPavement){
			drawPavementSelection(g);
		}
		else if(isEntrance){
			drawEntranceSelection(g);
		}
		else if(isExit){
			drawExitSelection(g);
		}
		else if(isGrass){
			drawGrassSelection(g);
		}
		
		
	}

}