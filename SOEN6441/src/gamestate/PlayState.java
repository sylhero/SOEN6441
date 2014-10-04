package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import currency.Coin;
import entity.ArrowTower;
import entity.CannonTower;
import entity.IceTower;
import entity.MagicTower;
import entity.MonsterTest;
import entity.TowerBase;
import entity.TowerFactory;
import gamepanel.GamePanel;
import tilemap.Tile;
import tilemap.TileMap;
/**
 * 
 * @author yulongsong
 * this class is responsible for all the operations on the map
 */
public class PlayState extends GameState{
	
	private TileMap  tileMap;
	//map is initialized in init()
	private Tile[][] map;
	// load the map path
	private String mapPath;
	
	//draw boolean
	private boolean isArrowTowerOnMenu;
	private boolean isArrowTowerEntered;
	private boolean isIceTowerOnMenu; 
	
	//inspect tower on map boolean
	
	private boolean isPressedTowerOnMap;
	private TowerBase selectedTower;
	//coin object
	private Coin coin;
	private boolean isEnoughCoinToBuild;
	private boolean isEnoughCoinToUpgrade;
	
	//boolean for sell and upgrade buttons
	private boolean isSell;
	private boolean isUpgrade;
	//factory
	TowerFactory towerFactory;
	
	//Arrow Tower object
	
	TowerBase arrowTower;
	
	//Ice Tower object
	
	TowerBase iceTower;
	
	//Cannon Tower 
	
	TowerBase cannonTower;
	
	// Magic Tower
	
	TowerBase magicTower;
	
	//pause

	private boolean isPaused;

	//control the speed

	private int speed;
	
	//font
	
	private Font font;
	
	/**
	 * constructor
	 * @param gsm
	 */
	public PlayState(GameStateManager gsm){

		this.gsm = gsm;
		
		isPaused = false;

		tileMap = TileMap.getTileMap();
		
		towerFactory = new TowerFactory();
		
		coin        = Coin.getCoinObject(); 
		
		arrowTower  = towerFactory.getTower("arrowtower");
		
		iceTower    = towerFactory.getTower("icetower");
		
		cannonTower = towerFactory.getTower("cannontower");
		
		magicTower  = towerFactory.getTower("magictower");
		
		font        = new Font("Arial",Font.BOLD,12);
		
	}

	//initialize the monsters
//	private void initializeMonsters(){
//
//
//	}
//===============FROM HERE MOUSE EVENT DETECTION================================
	/**
	 * detect the arrowtower icon is entered
	 * @param e
	 */
		private void detectArrowTowerMoved(MouseEvent e){
			int x = e.getX();

			int y = e.getY();
			
			if(x >= 1 && x<=41 && y >= 5 && y <= 45){
				this.isArrowTowerEntered = true;
			}else{
				this.isArrowTowerEntered = false;
			}
			
		}	


	
	/**
	 * detect if the money is enough for building tower
	 * @param towerBase
	 */
	private void isEnoughMoneyToBuild(TowerBase towerBase){
		if(towerBase.getCost() <= coin.getCurrency()){
			isEnoughCoinToBuild = true;	
		}else{
			isEnoughCoinToBuild = false;
		}
		
	}
	/**
	 * detect if the money is enough for upgrading
	 */
	private void isEnoughMoneyToUpgrate(){
		if(this.selectedTower.getUpgradeCost() <= coin.getCurrency()){
			this.isEnoughCoinToUpgrade = true;
		}else{
			this.isEnoughCoinToUpgrade = false;
		}
	}
	
	/**
	 * detect arrowTower icon
	 * @param e
	 */
	private void towerPressed(MouseEvent e){
		int x = e.getX();

		int y = e.getY();
		//arrow tower location
		if(x >= 1 && x<=41 && y >= 5 && y <= 45){
			isEnoughMoneyToBuild(arrowTower);
			if(isEnoughCoinToBuild){
				this.isArrowTowerOnMenu = true;
			}
			
		
		}
		// iceTower location TODO add more tower in the future
//		else if(x >= 42 && x<=82 && y >= 5 && y <= 45){
//			isEnoughMoneyToBuild(iceTower);
//			if(isEnoughCoinToBuild){
//				this.isIceTowerOnMenu = true;
//			}
			
		}
		
		
		
	
	/**
	 * set the arrow tower on map
	 * @param e
	 */
	private void setArrowTowerOnMap(MouseEvent e){
		// TODO change name to setTowerOnMap figure out
		// tower type based on the isBoolean;
		int tempX = e.getX();
		int tempY = e.getY();
		if(tempY >= tileMap.getUpperOffSet() && 
				tempY <= GamePanel.HEIGHT - tileMap.getLowerOffSet() &&
				isArrowTowerOnMenu){
			int column = tempX / tileMap.getCellWidth();
			int temp = tempY - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        System.out.println("this is x: "+row);
	        System.out.println("this is y: "+column);
	        //this should be in tower.class
	        
	        if( map[row][column].getTileType() == TileMap.GRASS){
				//get the original size
	        		int tileX = map[row][column].getTileX();
	        		int tileY = map[row][column].getTileY();
	        		int tileWidth = map[row][column].getTileWidth();
	        		int tileHeight = map[row][column].getTileHeight();
	        		//set the tile to arrow tower
	        		map[row][column] = new ArrowTower(tileX,tileY,tileWidth,tileHeight);
	        		//decrease the money
	        		coin.decreaseCurrency(arrowTower.getCost());
	        		//click once set once
	        		this.isArrowTowerOnMenu = false;
	        }
		}
		
	}
	
	/**
	 * inspect Tower on the Map
	 * @param e
	 */
	
	private void inspectTowerOnMap(MouseEvent e){
		int tempX = e.getX();
		int tempY = e.getY();
		if(tempY >= tileMap.getUpperOffSet() && 
				tempY <= GamePanel.HEIGHT - tileMap.getLowerOffSet()){
			int column = tempX / tileMap.getCellWidth();
			int temp = tempY - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        System.out.println("this is x: "+row);
	        System.out.println("this is y: "+column);
	        //this should be in tower.class
	        
	        if( map[row][column].getClass().getSuperclass() == TowerBase.class){
	        		selectedTower = (TowerBase) map[row][column];
	        		isPressedTowerOnMap = true;
	        }else{
				selectedTower = null;
				isPressedTowerOnMap = false;
				
			}
	        	
	        }
		
				
		
	}
	

	/**
	 * detect pause
	 * @param e
	 */
	private void pausePressed(MouseEvent e){

		int x = e.getX();

		int y = e.getY();

		System.out.println(x);

		System.out.println(y);

		if(x >= 0 && x <= 30 && y >=0 && y<=30 ){

		this.pause(true);

		}

		if(x > 30 && x <= 60 && y >=0 && y<=30 ){

		this.pause(false);

		}


		if(isPaused == false){

		tileMap.mousePressed(e);

		}

	}
	/**
	 * detect if the sell button is clicked
	 * @param e
	 */
	private void pressedSell(MouseEvent e){
		int tempX = e.getX();
		int tempY = e.getY();
		if(tempX >= 600 && tempX <= 660 &&
				tempY >= 50 && tempY <= 90 && isPressedTowerOnMap){
			//calculate row and column 
			// TODO should the tile object has these parameters?
			int column = selectedTower.getTileX() / tileMap.getCellWidth();
			int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        TowerBase towerTemp = ((TowerBase) map[row][column]);
	        //increase coin
	        coin.increaseCurrency((int)(towerTemp.getRefundRate()*towerTemp.getValue()));
	        //initialize the cell to grass
	        map[row][column] = new Tile(TileMap.GRASS,TileMap.grass,
	        		selectedTower.getTileX(),selectedTower.getTileY(), 
	        		selectedTower.getTileWidth(), selectedTower.getTileHeight());
	        //clear the selection
	        isPressedTowerOnMap = false;
		}
			
		}
		/**
		 * detect if the upgrade button is clicked
		 * @param e
		 */
		private void pressedUpgrade(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 600 && tempX <= 660 &&
					tempY >= 5 && tempY <= 45 && 
					isEnoughCoinToUpgrade){
				//calculate row and column 
				// TODO should the tile object has these parameters?
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        coin.decreaseCurrency(towerTemp.getUpgradeCost());
		        towerTemp.upgrade();
		        
				
			}
	        
		
	}
	@Override

	public void mousePressed(MouseEvent e) {
		
		towerPressed(e);
		setArrowTowerOnMap(e);
		inspectTowerOnMap(e);
		pressedUpgrade(e);
		pressedSell(e);
	

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
		detectArrowTowerMoved(e);    

	}



	@Override

	public void mouseClicked(MouseEvent e) {

	// TODO Auto-generated method stub


	}

	


	@Override
	public void pause(boolean result) {
		
		this.isPaused = result;
	}
	
	@Override
	
	public boolean pause(){
		return this.isPaused;
	}

	

	@Override
	public void init(String path) {
		this.mapPath = path;
		
		this.map = tileMap.loadMap(path);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
//============FROM HERE DRAWING BEGIN==================================
	/**
	 * draw selected tile area
	 * @param g
	 */
		private void drawSelectedArea(Graphics2D g){
			g.setColor(Color.WHITE);
			g.drawRect(selectedTower.getTileX(), 
					selectedTower.getTileY(), 
					selectedTower.getTileWidth(), 
					selectedTower.getTileHeight());
			
		}
	/**
	 * draw tower selection
	 * @param towerBase
	 * @param g
	 */
	private void drawTowerSelect(TowerBase towerBase,Graphics2D g){
		g.setColor(Color.red);
		if(towerBase == arrowTower){
			g.drawRect(1, 5, 41, 43);
		}else if(towerBase == cannonTower){
			g.drawRect(1, 49, 41, 43);
		}else if(towerBase == iceTower){
			g.drawRect(43, 5, 41, 43);
		}else if(towerBase == magicTower){
			g.drawRect(43, 49, 41, 43);
		}
		
	}
	/**
	 * draw tower description
	 * @param towerBase
	 * @param g
	 */
	private void drawTowerDescription(TowerBase towerBase, Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(100, 0, 400, 98);
		g.setColor(Color.BLACK);
		g.setFont(font);
		String name = towerBase.getName();
		g.drawString(name, 105, 20);
		String level = "(level: "+String.valueOf(towerBase.getLevel()) + ")";
		g.drawString(level, 185, 20);
		String cost = "Cost: "+ String.valueOf(towerBase.getCost());
		g.drawString(cost, 108, 40);
		String power = "Power: " + String.valueOf(towerBase.getPower());
		g.drawString(power, 108, 60);
		String groupAttack = "GroupAttack: "+ String.valueOf(towerBase.getGroupAttack());
		g.drawString(groupAttack, 108, 80);
		String range = "Range: "+String.valueOf(towerBase.getRange());
		g.drawString(range, 228, 40);
		String refundRate = "RefundRate: "+ String.valueOf(towerBase.getRefundRate());
		g.drawString(refundRate, 228, 60);
		String towerSpeed = "Attack speed: "+ String.valueOf(towerBase.getTowerSpeed());
		g.drawString(towerSpeed, 228, 80);
		String upgradeCost = "Upgrade cost: "+ String.valueOf(towerBase.getUpgradeCost());
		g.drawString(upgradeCost, 328, 40);
		String specialEffect = "Special effect: "+ towerBase.getSpecialEffect();
		g.drawString(specialEffect, 328, 60);
		String value = "Value: "+ towerBase.getValue();
		g.drawString(value, 328, 80);

	}
		/**
		 * draw top menu
		 * @param g
		 */
		private void drawTopMenu(Graphics2D g){
		
			g.setColor(Color.GRAY);
			g.fillRect(1, 1, GamePanel.WIDTH-2, tileMap.getUpperOffSet()-2);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, GamePanel.WIDTH-1, tileMap.getLowerOffSet()-2);
			g.drawImage(ArrowTower.arrowTower, 1, 5, 40, 40, null);
			g.drawImage(CannonTower.cannonTower, 1,48,40,40,null);
			g.drawImage(IceTower.iceTower, 42, 5 ,40,40,null);
			g.drawImage(MagicTower.magicTower, 42, 48, 40,40,null);
			g.setColor(Color.WHITE);
			//upgrade button location
			g.fillRect(600, 5, 60, 40);
			//sell button location
			g.fillRect(600, 50, 60, 40);
			g.setColor(Color.BLACK);
			g.setFont(font);
			String upgrade = "Upgrade";
			g.drawString(upgrade, 605, 30);
			String sell = "Sell";
			g.drawString(sell, 615, 71);
			g.drawImage(Coin.coinImage, 700, 0, 30, 30, null);
			
			if(isPressedTowerOnMap){
				g.setColor(Color.GREEN);
				g.fillRect(600, 50, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(sell, 615, 71);
				
				isEnoughMoneyToUpgrate();
			}
			//check if the money is enough
			
			if(isPressedTowerOnMap && isEnoughCoinToUpgrade){
				g.setColor(Color.GREEN);
				g.fillRect(600, 5, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(upgrade, 605, 30);
				
			}
	}
		/**
		 * draw bottom menu
		 * @param g
		 */
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
	

	@Override
	public void draw(Graphics2D g) {
		//first draw map
		tileMap.draw(g);
		//draw top menu
		drawTopMenu(g);
		//draw bottom menu
		drawBottomMenu(g);
		//draw the money
		coin.draw(g);
		//draw selection
		if(isArrowTowerOnMenu){
			drawTowerSelect(arrowTower,g);
		}
		//draw hover description
		if(isArrowTowerEntered){
			drawTowerDescription(arrowTower,g);
		}
		//draw selected tower
		if(selectedTower != null && isPressedTowerOnMap){
			drawSelectedArea(g);
			drawTowerDescription(selectedTower,g);
		}
		
	}
	
	@Override
	public void resume() {
	}

}
