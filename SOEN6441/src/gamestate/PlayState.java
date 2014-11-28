package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import log.CollectiveLog;
import log.GlobalLog;
import log.MapLog;
import log.WaveLog;
import critters.CritterBase;
import critters.CritterWave;
import critters.CritterWaveBuilder;
import currency.Coin;
import gamedata.GameDataParser;
import gamepanel.GamePanel;
import tilemap.Tile;
import tilemap.TileMap;
import towers.ArrowTower;
import towers.CannonTower;
import towers.IceTower;
import towers.MagicTower;
//import towers.MonsterTest;
import towers.TowerBase;
import towers.TowerFactory;
import usefulfunctions.ShowLog;
import usefulfunctions.ShowWaveLog;
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
	private boolean pauseResult;

	
	//font
	
	private Font font;
	
	//monster

	private ArrayList<CritterBase> critterBatch;
	private CritterBase critter;
	
	//tower
	
	public static ArrayList<TowerBase> towerList = new ArrayList<TowerBase>();
	
	//boolean isNextWave
	private boolean isNextWave;
	
	
	
	/**
	 * constructor
	 * @param gsm The game state manager.
	 */
	public PlayState(GameStateManager gsm){

		this.gsm      = gsm;
		
		isPaused      = false;
		
		isNextWave    = false;

		tileMap       = TileMap.getTileMap();
		
		towerFactory  = new TowerFactory();
		
		coin          = Coin.getCoinObject(); 
		
		critterBatch  = new ArrayList<CritterBase>();
		
		font          = new Font("Arial",Font.BOLD,12);
		
		
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
				//GlobalLog.addToGlobalLog("There is enough money to build tower."+"\n");
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
	 * set the tower on map
	 * @param e
	 */
	private void setTowerOnMap(MouseEvent e){
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
	        		//add log
	        		Date date = new Date();
	        		arrowTower.addIndevidualTowerLog(arrowTower.getName()+"  "+"is created\n",date);
	        		CollectiveLog.getObject().addToAllTowerLog(arrowTower.getName()+"  "+"is created\n",date);
	        		GlobalLog.getObject().addToGlobalLog(arrowTower.getName()+"  "+"is created\n",date);
	        		WaveLog.getObject().addToWaveLog(arrowTower.getName()+"  "+"is created\n",date);
	        		//add tower to towerlist
	        		towerList.add(arrowTower);
	        		//decrease the money
	        		coin.decreaseCurrency(arrowTower.getCost());
	        		Date dateCoin = new Date();
	        		GlobalLog.getObject().addToGlobalLog("user build "+arrowTower.getName()+", the coin -"+arrowTower.getCost()+"\n",dateCoin);
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
	        		//add log
	        		Date date = new Date();
	        		iceTower.addIndevidualTowerLog(iceTower.getName()+"  "+"is created\n",date);
	        		CollectiveLog.getObject().addToAllTowerLog(iceTower.getName()+"  "+"is created\n",date);
	        		GlobalLog.getObject().addToGlobalLog(iceTower.getName()+"  "+"is created\n",date);
	        		WaveLog.getObject().addToWaveLog(iceTower.getName()+"  "+"is created\n",date);
	        		//add tower to towerlist
	        		towerList.add(iceTower);
	        		//decrease the money
	        		coin.decreaseCurrency(iceTower.getCost());
	        		Date dateCoin = new Date();
	        		GlobalLog.getObject().addToGlobalLog("user build "+iceTower.getName()+", the coin -"+iceTower.getCost()+"\n",dateCoin);
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
	        		//add log
	        		Date date = new Date();
	        		cannonTower.addIndevidualTowerLog(cannonTower.getName()+"  "+"is created\n",date);
	        		CollectiveLog.getObject().addToAllTowerLog(cannonTower.getName()+"  "+"is created\n",date);
	        		GlobalLog.getObject().addToGlobalLog(cannonTower.getName()+"  "+"is created\n",date);
	        		WaveLog.getObject().addToWaveLog(cannonTower.getName()+"  "+"is created\n",date);
	        		
	        		//decrease the money
	        		Date dateCoin = new Date();
	        		coin.decreaseCurrency(cannonTower.getCost());
	        		GlobalLog.getObject().addToGlobalLog("user build "+cannonTower.getName()+", the coin -"+cannonTower.getCost()+"\n",dateCoin);
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
	        		//add to log
	        		Date date = new Date();
	        		magicTower.addIndevidualTowerLog(magicTower.getName()+" "+"is created\n",date);
	        		CollectiveLog.getObject().addToAllTowerLog(magicTower.getName()+"  "+"is created\n",date);
	        		GlobalLog.getObject().addToGlobalLog(magicTower.getName()+"  "+"is created\n",date);
	        		WaveLog.getObject().addToWaveLog(magicTower.getName()+"  "+"is created\n",date);
	        		//add tower to towerlist
	        		towerList.add(magicTower);
	        		//decrease the money
	        		Date dateCoin = new Date();
	        		coin.decreaseCurrency(magicTower.getCost());
	        		GlobalLog.getObject().addToGlobalLog("user build "+magicTower.getName()+", the coin -"+magicTower.getCost()+"\n",dateCoin);
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
	        		Date date = new Date();
	        		selectedTower.addIndevidualTowerLog(selectedTower.getName()+" "+"is inspected\n",date);
	        		CollectiveLog.getObject().addToAllTowerLog(selectedTower.getName()+"  "+"is inspected\n",date);
	        		GlobalLog.getObject().addToGlobalLog(selectedTower.getName()+"  "+"is inspected\n",date);
	        		WaveLog.getObject().addToWaveLog(selectedTower.getName()+"  "+"is inspected\n",date);
	        		
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
		if(x >= 204 && x <= 304 && y >=502 && y<=598 ){
			pauseResult = !pauseResult;
			Date date = new Date();
			WaveLog.getObject().addToWaveLog("user pauses the game\n",date);
			GlobalLog.getObject().addToGlobalLog("user pauses the game\n",date);
		this.pause(pauseResult);

		}

		if(isPaused == false){

		tileMap.mousePressed(e);

		}

	}
	private void savePressed(MouseEvent e){
		int x = e.getX();

		int y = e.getY();

		System.out.println(x);

		System.out.println(y);
		if(x >= 308 && x <= 408 && y >=502 && y<=598 ){
			System.out.println("save");
			GameDataParser gameDataSaver =new GameDataParser(map,towerList,new Integer(Coin.getCoinObject().getCurrency()));
			
			System.out.println("Removed suffix of file, file name is "+ tileMap.getMapName().split("\\.")[0]);
			
			gameDataSaver.WriteGameData(tileMap.getMapName().split("\\.")[0]);	
			
			Date date = new Date();
			WaveLog.getObject().addToWaveLog("user saves the game\n",date);
			GlobalLog.getObject().addToGlobalLog("user saves the game\n",date);
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
			
			int column = selectedTower.getTileX() / tileMap.getCellWidth();
			int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
	        int row =  temp / tileMap.getCellHeight();
	        TowerBase towerTemp = ((TowerBase) map[row][column]);
	        //write to log
	        Date date = new Date();
	        towerTemp.addIndevidualTowerLog(towerTemp.getName()+" "+"is sold\n",date);
	        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"is sold\n",date);
    		//GlobalLog.addToGlobalLog(towerTemp.getName()+"  "+"is sold\n");
	        //increase coin
	        coin.increaseCurrency((int)(towerTemp.getRefundRate()*towerTemp.getValue()));
	        Date dateCoin = new Date();
	        GlobalLog.getObject().addToGlobalLog("user sells "+towerTemp.getName()+", coin + "+((int)(towerTemp.getRefundRate()*towerTemp.getValue()))+"\n",dateCoin);
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
			
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        Date date = new Date();
		        towerTemp.addIndevidualTowerLog(towerTemp.getName()+" "+"is upgraded\n",date);
		        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"is upgraded\n",date);
	    		//GlobalLog.addToGlobalLog(towerTemp.getName()+"  "+"is upgraded\n");
	    		WaveLog.getObject().addToWaveLog(towerTemp.getName()+"  "+"is upgraded\n",date);
		        
		        coin.decreaseCurrency(towerTemp.getUpgradeCost());
		        Date dateCoin = new Date();
		        GlobalLog.getObject().addToGlobalLog(towerTemp.getName()+"  "+"is upgraded, the coin - "+towerTemp.getUpgradeCost()+"\n",dateCoin);
		        towerTemp.upgrade();
		        
				
			}
	        
		
	}
		/**
		 * press next wave button
		 * @param e
		 */
		private void pressNextWave(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 2 && tempX <= 202 &&
					tempY >= 502 && tempY <= 598){
				this.isNextWave = true;
				Date date = new Date();
				GlobalLog.getObject().addToGlobalLog("user clicks the next wave button\n",date);
				WaveLog.getObject().addToWaveLog("user clicks the next wave button\n",date);
				
			}
		}
		/**
		 * press the weakest strategy
		 * @param e
		 */
		private void pressWeakestSrategy(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 518 && tempX <= 578 &&
					tempY >= 5 && tempY <= 45 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        Date date = new Date();
		        towerTemp.addIndevidualTowerLog(towerTemp.getName()+"  "+"selects weakest strategy\n",date);
		        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"selects weakest strategy\n",date);
	    		GlobalLog.getObject().addToGlobalLog(towerTemp.getName()+"  "+"selects weakest strategy\n",date);
	    		WaveLog.getObject().addToWaveLog(towerTemp.getName()+"  "+"selects weakest strategy\n",date);
		        int weakestStrategy = 1;
		        towerTemp.setStrategyType(weakestStrategy);
		        	
			}
	        
			
		}
		/**
		 * press strongest strategy button
		 * @param e
		 */
		private void pressStrongestSrategy(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 518 && tempX <= 568 &&
					tempY >= 50 && tempY <= 90 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        Date date = new Date();
		        towerTemp.addIndevidualTowerLog(towerTemp.getName()+"  "+"selects strongest strategy\n",date);
		        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"selects strongest strategy\n",date);
	    		GlobalLog.getObject().addToGlobalLog(towerTemp.getName()+"  "+"selects strongest strategy\n",date);
	    		WaveLog.getObject().addToWaveLog(towerTemp.getName()+"  "+"selects strongestest strategy\n",date);
		        int strongestStrategy = 2;
		        towerTemp.setStrategyType(strongestStrategy);
		        	
			}
	        
			
		}
		/**
		 * press the farthest strategy button
		 * @param e
		 */
		private void pressFarthestSrategy(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 582 && tempX <= 642 &&
					tempY >= 5 && tempY <= 45 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        Date date = new Date(); 
		        towerTemp.addIndevidualTowerLog(towerTemp.getName()+"  "+"selects farthest strategy\n",date);
		        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"selects farthest strategy\n",date);
	    		GlobalLog.getObject().addToGlobalLog(towerTemp.getName()+"  "+"selects farthest strategy\n",date);
	    		WaveLog.getObject().addToWaveLog(towerTemp.getName()+"  "+"selects farthest strategy\n",date);
		        int farthestStrategy = 4;
		        towerTemp.setStrategyType(farthestStrategy);
		        	
			}

		}
		/**
		 * press nearest strategy button
		 * @param e
		 */
		private void pressNearestSrategy(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 582 && tempX <= 642 &&
					tempY >= 50 && tempY <= 90 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
		        Date date = new Date();
		        towerTemp.addIndevidualTowerLog(towerTemp.getName()+"  "+"selects nearest strategy\n",date);
		        CollectiveLog.getObject().addToAllTowerLog(towerTemp.getName()+"  "+"selects nearest strategy\n",date);
	    		GlobalLog.getObject().addToGlobalLog(towerTemp.getName()+"  "+"selects nearest strategy\n",date);
	    		WaveLog.getObject().addToWaveLog(towerTemp.getName()+"  "+"selects nearest strategy\n",date);
		        int nearestStrategy = 3;
		        towerTemp.setStrategyType(nearestStrategy);
		        	
			}
	        
			
		}
		/**
		 * show individual logs
		 * @param e
		 */
		
		private void pressIndividualTowerLog(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 450 && tempX <= 600 &&
					tempY >= 502 && tempY <= 547 && isPressedTowerOnMap){
				//calculate row and column 
				int column = selectedTower.getTileX() / tileMap.getCellWidth();
				int temp = selectedTower.getTileY() - tileMap.getUpperOffSet();
		        int row =  temp / tileMap.getCellHeight();
		        TowerBase towerTemp = ((TowerBase) map[row][column]);
	    		
	    		//CollectiveLog.addToAllTowerLog("user clicks the individual tower log button\n");
		        Date date = new Date();
	    		GlobalLog.getObject().addToGlobalLog("user check the individual tower log \n",date);
	    		WaveLog.getObject().addToWaveLog("user check the individual tower log \n",date);
		        String towerLog = towerTemp.getAllIdividualTowerLog();
		        ShowLog.showLog(towerLog);
		        
			}
			
		}
		/**
		 * press collective tower log
		 * @param e
		 */
		private void pressCollectiveTowerLog(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 605 && tempX <= 755 &&
					tempY >= 502 && tempY <= 547 ){ 
		        //CollectiveLog.addToAllTowerLog("user clicks the collective tower log button\n");
				Date date = new Date();
	    		GlobalLog.getObject().addToGlobalLog("user check the collective tower log \n",date);
	    		WaveLog.getObject().addToWaveLog("user check the collective tower log \n",date);
		        String towerLog = CollectiveLog.getAllTowerLog();
		        ShowLog.showLog(towerLog);
		        
			}
			
		}
		/**
		 * press wave log
		 * @param e
		 */
		
		private void pressWaveLog(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 450 && tempX <= 600 &&
					tempY >= 550 && tempY <= 595 ){
				//calculate row and column 
				
		        //CollectiveLog.addToAllTowerLog("user clicks the wave log button\n");
				Date date = new Date();
	    		GlobalLog.getObject().addToGlobalLog("user check the wave log \n",date);
	    		WaveLog.getObject().addToWaveLog("user check the wave log \n",date);
		        ShowWaveLog.showWaveLog(WaveLog.tpane);
		        
			}
			
		}
		/**
		 * press global log
		 * @param e
		 */
		private void pressGlobalLog(MouseEvent e){
			int tempX = e.getX();
			int tempY = e.getY();
			if(tempX >= 605 && tempX <= 755 &&
					tempY >= 550 && tempY <= 595 ){
				
				//CollectiveLog.addToAllTowerLog("user clicks the global log button\n");
				Date date = new Date();
	    		GlobalLog.getObject().addToGlobalLog("user check the global log \n",date);
	    		WaveLog.getObject().addToWaveLog("user check the global log \n",date);
	    		
		        String towerLog = GlobalLog.getAllGobalLog();
		        ShowLog.showLog(towerLog);
			}
			
		}
		
		/**
		 * mouse press event
		 */
	@Override

	public void mousePressed(MouseEvent e) {
		
		towerPressed(e);
		setTowerOnMap(e);
		inspectTowerOnMap(e);
		pressedUpgrade(e);
		pressedSell(e);
		pressNextWave(e);
		pausePressed(e);
		pressWeakestSrategy(e);
		pressStrongestSrategy(e);
		pressNearestSrategy(e);
		pressFarthestSrategy(e);
		pressIndividualTowerLog(e);
		pressCollectiveTowerLog(e);
		pressWaveLog(e);
		pressGlobalLog(e);
		savePressed(e);
	

	}
	/**
	 * mouse release event
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse enter event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse exit event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse drag event
	 */
	@Override

	public void mouseDragged(MouseEvent e) {

	// TODO Auto-generated method stub


	}

	/**
	 * mouse move event
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
	 */
	@Override

	public void mouseClicked(MouseEvent e) {

	// TODO Auto-generated method stub


	}

	

	/**
	 * pause
	 */
	@Override
	public void pause(boolean result) {
		
		this.isPaused = result;
	}
	/**
	 * pause
	 */
	@Override
	
	public boolean pause(){
		return this.isPaused;
	}
	/**
	 * fill critter batch
	 */
	public void fillBatch(){
		LinkedList<Point> correctPath = tileMap.getCorrectPath();
		
		CritterWaveBuilder cwb = new CritterWaveBuilder();
		cwb.prepareCritterWave("mixed");
		cwb.buildRoute(correctPath);
		cwb.buildStartPoint();
		cwb.buildX();
		cwb.buildY();
		cwb.buildNextPoint();
		cwb.buildMovePoint(35);
		
		critterBatch = cwb.getWave();
		
		
		
		
		//CritterWave cw = cwb.prepareCritterWave(correctPath, 35);
		
//		critterBatch = cw.getCritterWave();
		
//		for(int i =0; i < 5;i++){
//			critterBatch.add(CritterFactory.getCritter("Normal", correctPath, i * 35));	
//		}
		WaveLog.init();
	}

	
	/**
	 * Initialize method.
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
			//use this to prevent removing which is troublesome
			int deadCritterCounter = 0;
			Iterator<CritterBase> critterIterator = critterBatch.iterator();
			while(critterIterator.hasNext()){
				CritterBase critter = critterIterator.next();
				if(critter.getCurrentHp()<=0){
					deadCritterCounter++;
				}
				critter.update();
				if(critter.isAtExit()){
					Date date = new Date();
					WaveLog.getObject().addToWaveLog("one critter arrived exit"+"\n",date);
					MapLog.getMapLogObject().playMapLog(getScore());
					gsm.switchState(GameStateManager.GAMEOVER);
				}
				for(int i = 0; i< towerList.size();i++){
					towerList.get(i).fire(critter);
				}
			
			}
			
			if(critterBatch.size()==deadCritterCounter){
				Date date = new Date();
				WaveLog.getObject().addToWaveLog("this wave ends"+"\n",date);
				WaveLog.addWaveTab();
				fillBatch();
				isNextWave = false;
				
			}
					
		}
		
	}
	/**
	 * get score coin + value of towers
	 * @return result
	 */
	private int getScore(){
		int result = coin.getCurrency();
		for( int i =0; i<towerList.size();i++){
			result += towerList.get(i).getValue();
		}
		return result;
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
		 * draw the range of the tower
		 * @param g
		 */
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
		g.drawString(level, 200, 20);
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
			String nearestToExit = "Farthest";
			g.drawString(nearestToExit, 587, 30);
			String nearest ="Nearest";
			g.drawString(nearest, 587, 71);
			g.drawImage(Coin.coinImage, 650, 0, 30, 30, null);
			//this is for selling a tower
			if(isPressedTowerOnMap){
				g.setColor(Color.GREEN);
				g.fillRect(450, 50, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(sell, 465, 71);
				
				isEnoughMoneyToUpgrate();
			}
			//check if the money is enough
			//this is for upgrade
			if(isPressedTowerOnMap && isEnoughCoinToUpgrade){
				g.setColor(Color.GREEN);
				g.fillRect(450, 5, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(upgrade, 455, 30);
				
			}
			if(isPressedTowerOnMap && selectedTower.getStrategyType()==1){
				
				g.setColor(Color.GREEN);
				g.fillRect(518, 5, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(weakest, 523, 30);	
			}
			if(isPressedTowerOnMap && selectedTower.getStrategyType()==2){
				
				g.setColor(Color.GREEN);
				g.fillRect(518, 50, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(srongest, 521, 71);
			}
			if(isPressedTowerOnMap && selectedTower.getStrategyType()==3){
				
				g.setColor(Color.GREEN);
				g.fillRect(582, 50, 60, 40);
				g.setColor(Color.BLACK);
				g.drawString(nearest, 587, 71);
				
			}
			if(isPressedTowerOnMap && selectedTower.getStrategyType()==4){
				
				g.setColor(Color.GREEN);
				g.fillRect(582, 5, 60,40);
				g.setColor(Color.BLACK);
				g.drawString(nearestToExit, 587, 30);
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
			
			g.fillRect(204, 502, 100, 96);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.BLACK);
			String pause = "Pause";
			g.drawString(pause, 214, 550);
			g.setColor(Color.magenta);
			g.fillRect(308, 502, 100, 96);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.BLACK);
			String save = "SAVE";
			g.drawString(save, 318, 550);
			
			
			if(isPressedTowerOnMap){
				g.setColor(Color.GREEN);
				g.fillRect(450, 502, 150, 45);
			}
			
			else{
				g.setColor(Color.WHITE);
				g.fillRect(450, 502, 150, 45);
			}
				g.setColor(Color.GREEN);
				g.fillRect(605, 502, 150, 45);
				g.fillRect(450, 550, 150, 45);
				g.fillRect(605, 550, 150, 45);
			
			
			g.setColor(Color.BLACK);
			String individualLog = "Individual";
			String CollectiveLog = "Collective";
			String WaveLog       = "Wave";
			String GlobalLog     = "Global";
			g.drawString(individualLog, 460, 530);
			g.drawString(CollectiveLog, 610, 530);
			g.drawString(WaveLog, 460, 580);
			g.drawString(GlobalLog, 615, 580);
			
			
		}
	
	/**
	 * mouse drag event
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
			for(int i = 0; i<critterBatch.size();i++){
				critterBatch.get(i).draw(g);
				
				
			}
			for(int j = 0; j< towerList.size(); j++){
				towerList.get(j).draw(g);
			}
			
			
			
		}
		
		
		
		
	}
	/**
	 * resume
	 */
	
	@Override
	public void resume() {
	}

}
