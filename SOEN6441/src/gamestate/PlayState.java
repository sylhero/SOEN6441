package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

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
	//draw boolean
	private boolean isArrowTowerOnMenu;
	private boolean isArrowTowerEntered;
	
	private boolean isIceTowerOnMenu; 
	private boolean isIceTowerEntered;
	
	private boolean isCannonTowerOnMenu;
	private boolean isCannonTowerEntered;
	
	private boolean isMagicTowerOnMenu;
	private boolean isMagicTowerEntered;
	//inspect tower on map boolean
	
	private boolean isPressedTowerOnMap;
	private TowerBase selectedTower;
	//coin object
	private Coin coin;
	private boolean isEnoughCoinToBuild;
	private boolean isEnoughCoinToUpgrade;
	
	
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

	
	//font
	
	private Font font;
	
	//monster

	private ArrayList<MonsterTest> monsterBatch;
	private MonsterTest monster;
	
	//tower
	
	private ArrayList<TowerBase> towerList;
	
	//boolean isNextWave
	private boolean isNextWave;
	
	/**
	 * constructor
	 * @param gsm
	 */
	public PlayState(GameStateManager gsm){

		this.gsm      = gsm;
		
		isPaused      = false;
		
		isNextWave    = false;

		tileMap       = TileMap.getTileMap();
		
		towerFactory  = new TowerFactory();
		
		coin          = Coin.getCoinObject(); 
		
		monsterBatch  = new ArrayList<MonsterTest>();
		
		font          = new Font("Arial",Font.BOLD,12);
		
		towerList     = new ArrayList<TowerBase>();
		
	}

	
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
	 * detect the icetower icon is entered
	 * @param e
	 */
		private void detectIceTowerMoved(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			
			if(x >= 42 && x<=82 && y >= 5 && y <= 45){
				this.isIceTowerEntered = true;
			}else{
				this.isIceTowerEntered = false; 
			}
		}
		/**
		 * detect firetower icon is entered
		 * @param e
		 */
		
		private void detectCannonTowerMoved(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			
			if(x >= 1 && x<=41 && y >= 46 && y <= 86){
				this.isCannonTowerEntered = true;
			}else{
				this.isCannonTowerEntered = false; 
			}
		}
		/**
		 * detect magictower icon is entered
		 * @param e
		 */
		
		private void detectMagicTowerMoved(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			
			if(x >= 42 && x<=82 && y >= 46 && y <= 86){
				this.isMagicTowerEntered = true;
			}else{
				this.isMagicTowerEntered = false; 
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
	 * detect Tower icon
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
				this.isCannonTowerOnMenu = false;
				this.isIceTowerOnMenu = false;
				this.isMagicTowerOnMenu = false;
			}
			
		
		}
		else if(x >= 42 && x<=82 && y >= 5 && y <= 45){
			isEnoughMoneyToBuild(iceTower);
			if(isEnoughCoinToBuild){
				this.isArrowTowerOnMenu = false;
				this.isCannonTowerOnMenu = false;
				this.isIceTowerOnMenu = true;
				this.isMagicTowerOnMenu = false;
			}
		}
		else if(x >= 1 && x<=41 && y >= 46 && y <= 86){
			isEnoughMoneyToBuild(cannonTower);
			if(isEnoughCoinToBuild){
				this.isArrowTowerOnMenu = false;
				this.isCannonTowerOnMenu = true;
				this.isIceTowerOnMenu = false;
				this.isMagicTowerOnMenu = false;
			}
			
		}
		else if(x >= 42 && x<=82 && y >= 46 && y <= 86){
			isEnoughMoneyToBuild(magicTower);
			if(isEnoughCoinToBuild){
				this.isArrowTowerOnMenu = false;
				this.isCannonTowerOnMenu = false;
				this.isIceTowerOnMenu = false;
				this.isMagicTowerOnMenu = true;
			}
		}
			
		}
		
		
		
	
	/**
	 * set the arrow tower on map
	 * @param e
	 */
	private void setTowerOnMap(MouseEvent e){
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
	        //System.out.println("this is x: "+row);
	        //System.out.println("this is y: "+column);
	        //this should be in tower.class
	        
	        if( map[row][column].getTileType() == TileMap.GRASS){
				//get the original size
	        		int tileX = map[row][column].getTileX();
	        		int tileY = map[row][column].getTileY();
	        		int tileWidth = map[row][column].getTileWidth();
	        		int tileHeight = map[row][column].getTileHeight();
	        		//set the tile to arrow tower
	        		ArrowTower arrowTower = new ArrowTower(tileX,tileY,tileWidth,tileHeight);
	        		map[row][column] = arrowTower;
	        		//add tower to towerlist
	        		towerList.add(arrowTower);
	        		//decrease the money
	        		coin.decreaseCurrency(arrowTower.getCost());
	        		//click once set once
	        		this.isArrowTowerOnMenu = false;
	        }
		}else if(tempY >= tileMap.getUpperOffSet() && 
				tempY <= GamePanel.HEIGHT - tileMap.getLowerOffSet() &&
				isIceTowerOnMenu){
			int column = tempX / tileMap.getCellWidth();
			int temp = tempY - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        //System.out.println("this is x: "+row);
	        //System.out.println("this is y: "+column);
	        if( map[row][column].getTileType() == TileMap.GRASS){
				//get the original size
	        		int tileX = map[row][column].getTileX();
	        		int tileY = map[row][column].getTileY();
	        		int tileWidth = map[row][column].getTileWidth();
	        		int tileHeight = map[row][column].getTileHeight();
	        		//set the tile to arrow tower
	        		IceTower iceTower = new IceTower(tileX,tileY,tileWidth,tileHeight);
	        		map[row][column] = iceTower;
	        		//add tower to towerlist
	        		towerList.add(iceTower);
	        		//decrease the money
	        		coin.decreaseCurrency(iceTower.getCost());
	        		//click once set once
	        		this.isIceTowerOnMenu = false;
	        }
			
			
		}else if(tempY >= tileMap.getUpperOffSet() && 
				tempY <= GamePanel.HEIGHT - tileMap.getLowerOffSet() &&
				isCannonTowerOnMenu){
			int column = tempX / tileMap.getCellWidth();
			int temp = tempY - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        //System.out.println("this is x: "+row);
	        //System.out.println("this is y: "+column);
	        if( map[row][column].getTileType() == TileMap.GRASS){
				//get the original size
	        		int tileX = map[row][column].getTileX();
	        		int tileY = map[row][column].getTileY();
	        		int tileWidth = map[row][column].getTileWidth();
	        		int tileHeight = map[row][column].getTileHeight();
	        		//set the tile to arrow tower
	        		CannonTower cannonTower = new CannonTower(tileX,tileY,tileWidth,tileHeight);
	        		map[row][column] = cannonTower;
	        		//add tower to towerlist
	        		towerList.add(cannonTower);
	        		//decrease the money
	        		coin.decreaseCurrency(cannonTower.getCost());
	        		//click once set once
	        		this.isCannonTowerOnMenu = false;
	        }
			
			
		}else if(tempY >= tileMap.getUpperOffSet() && 
				tempY <= GamePanel.HEIGHT - tileMap.getLowerOffSet() &&
				isMagicTowerOnMenu){
			int column = tempX / tileMap.getCellWidth();
			int temp = tempY - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
//	        System.out.println("this is x: "+row);
//	        System.out.println("this is y: "+column);
	        if( map[row][column].getTileType() == TileMap.GRASS){
				//get the original size
	        		int tileX = map[row][column].getTileX();
	        		int tileY = map[row][column].getTileY();
	        		int tileWidth = map[row][column].getTileWidth();
	        		int tileHeight = map[row][column].getTileHeight();
	        		//set the tile to magic tower
	        		MagicTower magicTower = new MagicTower(tileX,tileY,tileWidth,tileHeight);
	        		map[row][column] = magicTower;
	        		//add tower to towerlist
	        		towerList.add(magicTower);
	        		//decrease the money
	        		coin.decreaseCurrency(magicTower.getCost());
	        		//click once set once
	        		this.isMagicTowerOnMenu = false;
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
//	        System.out.println("this is x: "+row);
//	        System.out.println("this is y: "+column);
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

		if(x >= 0 && x <= 400 && y >=500 && y<=600 ){

		this.pause(true);

		}

		if(x > 400 && x <= 800 && y >=500 && y<=600 ){

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
		if(tempX >= 450 && tempX <= 510 &&
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
	        
	        //remove tower from towerList
	        towerList.remove(towerTemp);
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
			if(tempX >= 450 && tempX <= 510 &&
					tempY >= 5 && tempY <= 45 && 
					isEnoughCoinToUpgrade && isPressedTowerOnMap){
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
		private void pressNextWave(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 2 && tempX <= 202 &&
					tempY >= 502 && tempY <= 598){
				this.isNextWave = true;
			}
		}
		/**
		 * press the weakest strategy
		 * @param e
		 */
		private void pressWeakestSrategy(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 568 && tempX <= 628 &&
					tempY >= 5 && tempY <= 45 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        //towerTemp.setStrategy();
		        	
			}
	        
			
		}
		/**
		 * mouse press event
		 * @param e
		 */
	@Override

	public void mousePressed(MouseEvent e) {
		
		towerPressed(e);
		setTowerOnMap(e);
		inspectTowerOnMap(e);
		pressedUpgrade(e);
		pressedSell(e);
		pressNextWave(e);
		//pausePressed(e);
	

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
		detectArrowTowerMoved(e);  
		detectIceTowerMoved(e);
		detectCannonTowerMoved(e);
		detectMagicTowerMoved(e);

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
	 * pause
	 * @param result
	 */
	@Override
	public void pause(boolean result) {
		
		this.isPaused = result;
	}
	/**
	 * pause
	 * @param e
	 */
	@Override
	
	public boolean pause(){
		return this.isPaused;
	}
	public void fillBatch(){
		LinkedList<Point> correctPath = tileMap.getCorrectPath();
		for(int i =0; i < 5;i++){
			monsterBatch.add(new MonsterTest(correctPath,i*35));	
		}
	}

	
	/**
	 * init
	 * @param e
	 */
	@Override
	public void init() {
		map     = tileMap.getMap();
		
		arrowTower  = towerFactory.getTower("arrowtower");
		
		iceTower    = towerFactory.getTower("icetower");
		
		cannonTower = towerFactory.getTower("cannontower");
		
		magicTower  = towerFactory.getTower("magictower");
		
		fillBatch();
			
	}
	
	/**
	 * update
	 * 
	 */

	@Override
	public void update() {
		if(isNextWave){	

			for(int i = 0;i < monsterBatch.size();i++){
				monsterBatch.get(i).update();
				if(monsterBatch.get(i).getIsExit()==true){
					gsm.switchState(GameStateManager.GAMEOVER);
				}
				for (int j = 0; j < towerList.size(); j++){
					
					if(monsterBatch.get(i).getCurrentHP()<=0){
						//remove dead monsters from the batch
						monsterBatch.remove(i);
						if(monsterBatch.size()==0){
							
							break;
						}
					}
						towerList.get(j).fire(monsterBatch.get(i));
					}
				if(monsterBatch.size()==0){
					isNextWave = false;
					fillBatch();
					break;
				}
				//TODO switch to game over if reach here one of the monsters reach the exit
				//
			}
//				System.out.println("outerest");
//				//middle loop the size of each monster batch
//				for(int j = 0; j< monsterBatch.get(i).size(); j++){
//					System.out.printf("i is %d, size is %d\n", i,monsterBatch.get(i).size());
//					System.out.println("innerest");
//					monsterBatch.get(i).get(j).update();
//					
//					//innerest loop the size of the towers
//					for (int k = 0; k< towerList.size();k++){
//						if(monsterBatch.get(i).get(j).getCurrentHP()<=0){
//							//remove dead monsters from the batch
//							monsterBatch.get(i).remove(j);
//							if(monsterBatch.get(i).size()==0){
//								isNextWave = false;
//								System.out.println("break1");
//								break;
//							}
//						
//					}	//fire on monsters
//						
//						}
//						//if the batch size == 0 end of the wave
//						if(monsterBatch.get(i).size()==0){
//							System.out.println("break2");
//							break;
//						}
//						
//				}
//					if(monsterBatch.get(i).size()==0){
//						monsterBatch.remove(i);
//						System.out.println("break3");
//						break;
//					}
//			}
			
		}
		
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
		//TODO here
		private void drawSelectedRange(Graphics2D g){
			g.setColor(Color.YELLOW);
			g.drawOval(selectedTower.getTileX()+tileMap.getCellWidth()/2-selectedTower.getRange(), 
					selectedTower.getTileY()+tileMap.getCellHeight()/2-selectedTower.getRange(), 
					2*selectedTower.getRange(), 2*selectedTower.getRange());
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
		g.fillRect(100, 0, 345, 98);
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
			g.fillRect(450, 5, 60, 40);
			//sell button location
			g.fillRect(450, 50, 60, 40);
			//weakest strategy button location
			g.fillRect(518, 5, 60, 40);
			//strongest strategy button location
			g.fillRect(518, 50, 60, 40);
			//farthest strategy button 
			g.fillRect(582, 5, 60,40);
			//nearest strategy button
			g.fillRect(582, 50, 60, 40);
			g.setColor(Color.BLACK);
			g.setFont(font);
			String upgrade = "Upgrade";
			g.drawString(upgrade, 455, 30);
			String sell = "Sell";
			g.drawString(sell, 465, 71);
			String weakest = "Weakest";
			g.drawString(weakest, 523, 30);
			String srongest = "Strongest";
			g.drawString(srongest, 521, 71);
			String farthest = "Farthest";
			g.drawString(farthest, 587, 30);
			String nearest ="Nearest";
			g.drawString(nearest, 587, 71);
			g.drawImage(Coin.coinImage, 650, 0, 30, 30, null);
			
			if(isPressedTowerOnMap){
				g.setColor(Color.GREEN);
				g.fillRect(450, 50, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(sell, 465, 71);
				
				isEnoughMoneyToUpgrate();
			}
			//check if the money is enough
			
			if(isPressedTowerOnMap && isEnoughCoinToUpgrade){
				g.setColor(Color.GREEN);
				g.fillRect(450, 5, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(upgrade, 455, 30);
				
			}
	}
		/**
		 * draw bottom menu
		 * @param g
		 */
		private void drawBottomMenu(Graphics2D g){
			g.setColor(Color.GRAY);
			g.fillRect(1, 501, GamePanel.WIDTH-2, 
					GamePanel.HEIGHT - tileMap.getUpperOffSet()-2);
			g.setColor(Color.BLACK);
			g.drawRect(0, 500, GamePanel.WIDTH-1,
					GamePanel.HEIGHT - tileMap.getUpperOffSet()-2);
			g.setColor(Color.GREEN);
			if(isNextWave){
				g.setColor(Color.WHITE);
			}
			g.fillRect(2, 502, 200, 96);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.RED);
			String firstLine = "NEXT WAVE";
			g.drawString(firstLine, 10, 550);
			
		}
	
	/**
	 * mouse drag event
	 * @param e
	*/
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
		if(isIceTowerOnMenu){
			drawTowerSelect(iceTower,g);
		}
		if(isCannonTowerOnMenu){
			drawTowerSelect(cannonTower,g);
			
		}
		if(isMagicTowerOnMenu){
			drawTowerSelect(magicTower,g);
		}
		//draw hover description
		if(isArrowTowerEntered){
			
			drawTowerDescription(arrowTower,g);
		}
		if(isIceTowerEntered){
			drawTowerDescription(iceTower,g);
		}
		if(isCannonTowerEntered){
			drawTowerDescription(cannonTower,g);
		}
		if(isMagicTowerEntered){
			drawTowerDescription(magicTower,g);
		}
		//draw selected tower
		if(selectedTower != null && isPressedTowerOnMap){
			//drawSelectedArea(g);
			drawTowerDescription(selectedTower,g);
			drawSelectedRange(g);
		}
		if(isNextWave){
			for(int i = 0; i<monsterBatch.size();i++){
				monsterBatch.get(i).draw(g);
				
				if(monsterBatch.size()==0){
					break;
				}
				
				
			}
			for(int j = 0; j< towerList.size(); j++){
				towerList.get(j).draw(g);
			}
			
			
		}
		
		
		
		
	}
	/**
	 * resume
	 * @param e
	 */
	
	@Override
	public void resume() {
	}

}
