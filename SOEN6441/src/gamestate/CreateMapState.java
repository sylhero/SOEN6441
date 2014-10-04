package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;
import xml.MapParser;
/**
 * 
 * @author yulongsong
 * 
 * this class is the map editor, user can load/save/modify 
 * the map.
 * 
 */

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
	private boolean isLoaded;
	
	//font
	private Font font;
	
	//map
	private Tile[][] map;
	
	//map parser
	private MapParser mapParser;
	
	
	/**
	 * constructor
	 * @param gsm
	 */
	
	public CreateMapState(GameStateManager gsm){
		this.gsm     = gsm;
		mapName      = null;
		menuWidth    = 100;
		menuHeight   = GamePanel.HEIGHT;
		buttonWidth  = menuWidth;
		buttonHeight = 40;
		map          = null;
		
		font         = new Font("Arial",Font.BOLD,12);
		
		mapParser    = new MapParser();
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >=0 && x <= GamePanel.WIDTH - menuWidth){
			int column = x / cellWidth;
	        int row =  y / cellHeight;
	        int tileX = map[row][column].getTileX();
	        int tileY = map[row][column].getTileY();
	        if(isPavement){
	        	 map[row][column] = new Tile(TileMap.PAVEMENT,TileMap.pavement, 
			        		tileX,tileY,cellWidth, cellHeight);
	        }else if(isGrass){
	        	map[row][column] = new Tile(TileMap.GRASS,TileMap.grass, 
		        		tileX,tileY,cellWidth, cellHeight);
	        }
			
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
	
	
	
	/**
	 * set name button
	 * @param e
	 */
	private void setNamePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 0 &&
				y <= buttonHeight && !isNameGiven){
			mapName = JOptionPane.showInputDialog("Enter map name: ");
		}
		
		
	}
	/**
	 * set row button
	 * @param e
	 */
	private void setRowPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= buttonHeight + 5 &&
				y <= 2*buttonHeight + 5 && !isRowGiven){
			String row = JOptionPane.showInputDialog("Enter map row: ");
			if(row == null){
				return;
			}else {
				mapRow = Integer.parseInt(row);
			}
			
		}
		
	}
	/**
	 * set column button
	 * @param e
	 */
	private void setColumnPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 2*buttonHeight + 10 &&
				y <= 3 * buttonHeight + 10 && !isColumnGiven){
			String column = JOptionPane.showInputDialog("Enter map column: ");
			if(column == null){
				return;
			}else{
				mapColumn = Integer.parseInt(column);
			}
			
		}
	}
	/**
	 * initialize map button
	 * @param e
	 */
	private void initializePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && y >= 3*buttonHeight + 15 &&
				y <= 4 * buttonHeight + 15 ){
			init(null);
		}
		
	}
	/**
	 * pavement button
	 * @param e
	 */
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
		
	}
	/**
	 * grass button
	 * @param e
	 */
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
		
		
	}
	/**
	 * entrance button
	 * @param e
	 */
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
		
	}
	/**
	 * exit button
	 * @param e
	 */
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
		
	}
	/**
	 * load map button
	 * @param e
	 */
	private void loadMapPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && 
				y >= 6*buttonHeight + 30+18 && y <=  
				7*buttonHeight + 30+18){
			init(null);
			fileChooser();
			isLoaded = true;
			System.out.println("load button");
			
		}

	}
	/**
	 * this is the file chooser for the load map
	 */
	private void fileChooser(){
	      
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		chooser.setFileFilter(filter);
		String userPath = System.getProperty("user.dir")+"/resources/gamemaps/";
		chooser.setCurrentDirectory(new File(userPath));
		int returnValue = chooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			java.io.File file = chooser.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			//set the tilemap path
			String path = file.getAbsolutePath();
			//load map
			mapParser.loadXMLFile(path);
			map = mapParser.getMapData();
			this.mapName = mapParser.getMapName();
			this.mapRow = mapParser.getRow();
			this.mapColumn = mapParser.getColumn();
		} else if(returnValue == JFileChooser.CANCEL_OPTION){
			//cancel set none
			
		}
	       
	   }
	//print path
	/**
	 * test use
	 * @param path
	 */
	private void printPath(boolean [][] path){
		for(int i = 0; i<mapRow; i++){
			for(int j = 0;j<mapColumn; j++){
				if(path[i][j] == true){
					System.out.printf("%d  %d\n",i,j);
				}
			}
			}
	}
	
	
	/**
	 * generate button
	 * @param e
	 */
	private void generatePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		
		//generate map
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH && 
				y >= 7*buttonHeight + 35+18 && y <=  
				8*buttonHeight + 35+18){
			
			if(map == null){
				JOptionPane.showMessageDialog(null,"MAP IS NULL ERROR!");
			}else{
				boolean isEntrance    = ValidateMap.validateEntrance(map);
				boolean isExit        = ValidateMap.validateExit(map);
				boolean isCorrectPath = ValidateMap.validatePath(map);
				if(isEntrance == false ){
					JOptionPane.showMessageDialog(null,"NO ENTRANCE ERROR!");
					
				}
				else if(isExit == false ){
					JOptionPane.showMessageDialog(null,"NO EXIT ERROR!");
					
				}
				else if(isCorrectPath == false ){
					JOptionPane.showMessageDialog(null,"NO PATH ERROR!");
				}
				else if(isEntrance && isExit && isCorrectPath){
					mapParser.createXMLFile(map, mapName);
					System.out.println("generate");
					//init
					printPath(ValidateMap.getCorrectRoute());
					init(null);
				}
				
				
			}
			
			
			
		}
		
	}
	/**
	 * back button
	 * @param e
	 */
	private void backPressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH - menuWidth && 
				x <= GamePanel.WIDTH 
				&& y >= 8*buttonHeight + 40+18 && y <= 9*buttonHeight + 40+18){
			gsm.switchState(GameStateManager.MENUSTATE, null);
		}
			
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
    		// draw on the map area by pressing the button
		// in order to make a difference with the drag
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
		setNamePressed(e);
		setRowPressed(e);
		setColumnPressed(e);
		initializePressed(e);
		pavementPressed(e);
		grassPressed(e);
		entrancePressed(e);
		exitPressed(e);
		loadMapPressed(e);
		generatePressed(e);
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
		this.isLoaded = false;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * draw the background of the map and menu
	 * @param g
	 */
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
		
		//draw load map
		g.setColor(Color.PINK);
		g.fillRect(GameButtonStartX, 6*buttonHeight+30+18 , 
					buttonWidth, buttonHeight);
		g.setColor(Color.YELLOW);
		String load = "Load Map";
		g.drawString(load, GameButtonStartX+4, 6*buttonHeight+65);
		
		//draw generate button
		
		g.setColor(Color.MAGENTA);
		g.fillRect(GameButtonStartX, 7*buttonHeight + 35+18, 
					buttonWidth, buttonHeight);
		g.setColor(Color.ORANGE);
		String generate = "Generate";
		g.drawString(generate, GameButtonStartX+5, 7*buttonHeight+70);
		
		//draw back to menu button
		
		g.setColor(Color.BLACK);
		g.fillRect(GameButtonStartX, 8*buttonHeight + 40+18, 
					buttonWidth, buttonHeight);
		g.setColor(Color.ORANGE);
		String backToMenu = "Back";
		g.drawString(backToMenu, GameButtonStartX+5, 8*buttonHeight+80);
		
		    
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
	private void drawLoadMapArea(Graphics2D g){
		if(map != null && isLoaded == true){
			//700 map area 100 menu area
			cellWidth  = (GamePanel.WIDTH - menuWidth)/ mapColumn;
			cellHeight = GamePanel.HEIGHT/ mapRow;
			for(int i = 0; i<mapRow; i++){
				for(int j = 0;j<mapColumn; j++){
					g.drawImage(map[i][j].getTileImage(),map[i][j].getTileX(),
							map[i][j].getTileY(),map[i][j].getTileWidth(),
							map[i][j].getTileHeight(), null); 		
				}
		}
			
		}
	}
	/**
	 * draw the map
	 * @param g
	 */
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
	/**
	 * draw the rect of the selection (entrance)
	 * @param g
	 */
	private void drawEntranceSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth, 
				5*buttonHeight+25+18,buttonWidth/2 +1, buttonHeight);
		
	}
	/**
	 * draw the rect of the selection (exit)
	 * @param g
	 */
	private void drawExitSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth + buttonWidth/2, 
				5*buttonHeight+25+18,buttonWidth/2 +1, buttonHeight);
		
	}
	/**
	 * draw the rect of the selection (grass)
	 * @param g
	 */
	private void drawGrassSelection(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH - menuWidth + buttonWidth/2, 
				4*buttonHeight+20+18,buttonWidth/2 +1, buttonHeight);
		
	}
	/**
	 * draw the rect of the selection (pavement)
	 * @param g
	 */
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
		//draw loaded map
		drawLoadMapArea(g);
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
